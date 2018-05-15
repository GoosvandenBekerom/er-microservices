package com.s63d.vehicle.service;

import com.s63d.generic.DomainService;
import com.s63d.vehicle.domain.SimpleUser;
import com.s63d.vehicle.domain.Vehicle;
import com.s63d.vehicle.repository.VehicleRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.apache.commons.codec.binary.Hex.encodeHexString;

@Stateless
public class VehicleService extends DomainService<Vehicle, String, VehicleRepository> {
    private OwnershipService ownershipService;
    @Inject
    public VehicleService(VehicleRepository repo, OwnershipService ownershipService) {
        super(repo);
        this.ownershipService = ownershipService;
    }
    public VehicleService() { super(); }

    public List<Vehicle> getAllVehicles(SimpleUser user) {
        return repo.getAllVehicles(user);
    }

    public Vehicle save(String license, String type, String brand, String color, int weight, SimpleUser owner) {
        String hashedLicense = md5(license);

        if (repo.exists(hashedLicense)) {
            Vehicle v = repo.getById(hashedLicense);
            if (ownershipService.getLatestOfVehicle(v, owner.getId()) != null) return v;
            else throw new NotAuthorizedException("This car is currently owned by someone else");
        }

        char rate = getRateForWeight(weight);
        Vehicle vehicle = super.save(new Vehicle(md5(license), type, brand, color, weight, rate));
        ownershipService.create(owner, vehicle);
        return vehicle;
    }

    public Vehicle getVehicleByLicense(String license) {
        return repo.getById(md5(license));
    }

    public void suspend(String vehicleId, SimpleUser owner) {
        Vehicle vehicle = getById(vehicleId);
        ownershipService.suspend(vehicle, owner);
    }

    private char getRateForWeight(int weight) {
        char offset = 'A';
        int cat = (int) Math.min(5, Math.ceil(weight / 1000));
        return (char) (offset + cat);
    }

    private String md5(String s) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            return encodeHexString(md5.digest(s.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
