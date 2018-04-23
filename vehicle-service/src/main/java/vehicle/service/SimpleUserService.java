package vehicle.service;

import com.s63d.generic.DomainService;
import vehicle.domain.SimpleUser;
import vehicle.repository.SimpleUserRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class SimpleUserService extends DomainService<SimpleUser, Long, SimpleUserRepository> {
    @Inject
    public SimpleUserService(SimpleUserRepository repository) { super(repository); }
    public SimpleUserService() {}
}
