package vehicle.service;

import com.s63d.generic.DomainService;
import vehicle.domain.Ownership;
import vehicle.domain.SimpleUser;
import vehicle.domain.Vehicle;
import vehicle.repository.OwnershipRepository;
import vehicle.repository.VehicleRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Stateless
public class OwnershipService extends DomainService<Ownership, Long, OwnershipRepository> {
    private VehicleRepository vehicleRepo;
    @Inject
    public OwnershipService(OwnershipRepository repository, VehicleRepository vehicleRepo) {
        super(repository);
        this.vehicleRepo = vehicleRepo;
    }
    public OwnershipService() { super(); }

    public void create(SimpleUser owner, Vehicle vehicle) {
        Ownership ownership = new Ownership(new Date(), owner, vehicle);
        owner.getOwnerships().add(ownership);
        repo.save(ownership);
    }

    public List<Ownership> getAllForUser(SimpleUser user) {
        return repo.getAllForUser(user);
    }
}
