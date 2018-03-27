package account.repository;

import account.domain.Vehicle;
import com.s63d.generic.Repository;

import javax.ejb.Stateless;

@Stateless
public class VehicleRepository extends Repository<Vehicle, String> {
    public VehicleRepository() { super(Vehicle.class); }
}
