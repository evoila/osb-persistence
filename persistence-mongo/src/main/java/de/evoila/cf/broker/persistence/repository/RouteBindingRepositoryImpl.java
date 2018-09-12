/**
 * 
 */
package de.evoila.cf.broker.persistence.repository;

import de.evoila.cf.broker.model.RouteBinding;
import de.evoila.cf.broker.repository.RouteBindingRepository;
import org.springframework.stereotype.Service;

/**
 * @author Patrick Weber, evoila.
 *
 */
@Service
public class RouteBindingRepositoryImpl implements RouteBindingRepository {

	de.evoila.cf.broker.persistence.mongodb.repository.RouteBindingRepository routeBindingRepository;

	public RouteBindingRepositoryImpl(de.evoila.cf.broker.persistence.mongodb.repository.RouteBindingRepository routeBindingRepository) {
		this.routeBindingRepository = routeBindingRepository;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.evoila.cf.broker.repository.BindingRepository#getInternalBindingId(
	 * java.lang.String)
	 */
	@Override
	public String getRouteBindingId(String bindingId) {
		return routeBindingRepository.findById(bindingId).get().getServiceInstanceId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.evoila.cf.broker.repository.BindingRepository#addInternalBinding(java.
	 * lang.String, java.lang.String)
	 */
	@Override
	public void addRouteBinding(RouteBinding binding) {
		routeBindingRepository.save(binding);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.evoila.cf.broker.repository.BindingRepository#
	 * containsInternalBindingId(java.lang.String)
	 */
	@Override
	public boolean containsRouteBindingId(String bindingId) {
		return routeBindingRepository.existsById(bindingId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.evoila.cf.broker.repository.BindingRepository#deleteBinding(java.lang.
	 * String)
	 */
	@Override
	public void deleteRouteBinding(String bindingId) {
		routeBindingRepository.deleteById(bindingId);
	}

	@Override
	public RouteBinding findOne(String bindingId) {
		return routeBindingRepository.findById(bindingId).get();
	}
}
