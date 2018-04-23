package vehicle.repository;

import com.s63d.generic.Repository;
import vehicle.domain.Ownership;
import vehicle.domain.SimpleUser;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class OwnershipRepository extends Repository<Ownership, Long> {
    public OwnershipRepository() { super(Ownership.class); }

    public List<Ownership> getAllForUser(SimpleUser user) {
        TypedQuery<Ownership> q = em.createNamedQuery("user.getOwnerships", Ownership.class);
        q.setParameter("user", user);
        return q.getResultList();
    }
}
