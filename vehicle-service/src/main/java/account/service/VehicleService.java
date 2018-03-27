package account.service;

import account.domain.SimpleUser;
import account.domain.Vehicle;
import account.repository.VehicleRepository;
import com.s63d.generic.DomainService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.security.MessageDigest;

import static org.apache.commons.codec.binary.Hex.encodeHexString;

@Stateless
public class VehicleService extends DomainService<Vehicle, String, VehicleRepository> {
    @Inject
    public VehicleService(VehicleRepository repo) { super(repo); }
    public VehicleService() { super(); }

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
