package com.s63d.vehicle.service;

import com.s63d.generic.DomainService;
import com.s63d.vehicle.domain.Ownership;
import com.s63d.vehicle.domain.SimpleUser;
import com.s63d.vehicle.domain.Vehicle;
import com.s63d.vehicle.repository.VehicleRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.apache.commons.codec.binary.Hex.encodeHexString;

@Stateless
public class VehicleService extends DomainService<Vehicle, String, VehicleRepository> {
    @Inject
    public VehicleService(VehicleRepository repo) { super(repo); }
    public VehicleService() { super(); }

    public List<Vehicle> getAllVehicles(SimpleUser user) {
        return repo.getAllVehicles(user);
    }

    public Vehicle save(String license, String type, String brand, String color, int weight) {
        char rate = getRateForWeight(weight);
        return super.save(new Vehicle(md5(license), type, brand, color, weight, rate));
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
