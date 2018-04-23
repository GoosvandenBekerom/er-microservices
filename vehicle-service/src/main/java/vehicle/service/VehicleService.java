package vehicle.service;

import com.s63d.generic.DomainService;
import vehicle.domain.Ownership;
import vehicle.domain.SimpleUser;
import vehicle.domain.Vehicle;
import vehicle.repository.VehicleRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;
import java.security.MessageDigest;
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

    public Vehicle save(String license, String type, String brand, String color) {
        return super.save(new Vehicle(md5(license), type, brand, color));
    }

    private String md5(String s) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            return encodeHexString(md5.digest(s.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
