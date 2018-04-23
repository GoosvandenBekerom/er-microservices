package vehicle.repository;

import com.s63d.generic.Repository;
import vehicle.domain.SimpleUser;

import javax.ejb.Stateless;

@Stateless
public class SimpleUserRepository extends Repository<SimpleUser, Long> {
    public SimpleUserRepository() { super(SimpleUser.class); }
}
