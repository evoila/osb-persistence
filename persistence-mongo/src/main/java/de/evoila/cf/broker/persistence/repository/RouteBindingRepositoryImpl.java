package de.evoila.cf.broker.persistence.repository;

import de.evoila.cf.broker.model.RouteBinding;
import de.evoila.cf.broker.persistence.mongodb.repository.MongoDBRouteBindingRepository;
import de.evoila.cf.broker.repository.RouteBindingRepository;
import org.springframework.stereotype.Service;

/**
 * @author Patrick Weber.
 */
@Service
public class RouteBindingRepositoryImpl implements RouteBindingRepository {

    private MongoDBRouteBindingRepository routeBindingRepository;

    public RouteBindingRepositoryImpl(MongoDBRouteBindingRepository mongoDBRouteBindingRepository) {
        this.routeBindingRepository = mongoDBRouteBindingRepository;
    }

	@Override
	public String getRouteBindingId(String bindingId) {
		return routeBindingRepository.findById(bindingId).get().getServiceInstanceId();
	}

	@Override
	public void addRouteBinding(RouteBinding binding) {
		routeBindingRepository.save(binding);
	}

	@Override
	public boolean containsRouteBindingId(String bindingId) {
		return routeBindingRepository.existsById(bindingId);
	}

	@Override
	public void deleteRouteBinding(String bindingId) {
		routeBindingRepository.deleteById(bindingId);
	}

	@Override
	public RouteBinding findOne(String bindingId) {
		return routeBindingRepository.findById(bindingId).get();
	}
}
