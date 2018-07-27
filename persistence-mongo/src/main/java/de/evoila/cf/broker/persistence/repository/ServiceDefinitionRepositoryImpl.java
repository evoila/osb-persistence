/**
 * 
 */
package de.evoila.cf.broker.persistence.repository;

import de.evoila.cf.broker.exception.ServiceDefinitionDoesNotExistException;
import de.evoila.cf.broker.model.Plan;
import de.evoila.cf.broker.model.ServiceDefinition;
import de.evoila.cf.broker.repository.ServiceDefinitionRepository;
import de.evoila.cf.broker.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Christian Brinker & Johannes Hiemer, evoila.
 *
 */
@Repository
public class ServiceDefinitionRepositoryImpl implements ServiceDefinitionRepository {

	@Autowired
	private CatalogService catalogService;

	@Override
	public List<ServiceDefinition> getServiceDefinition() {
		return catalogService.getCatalog().getServices();
	}

	// public Map<String, ServiceInstance> getServiceInstances() {
	// return serviceInstances;
	// }

	@Override
	public void validateServiceId(String serviceDefinitionId) throws ServiceDefinitionDoesNotExistException {
		for(ServiceDefinition serviceDefinition : catalogService.getCatalog().getServices()) {
			if (serviceDefinitionId.equals(serviceDefinition.getId())) {
				return;
			}
		}

		throw new ServiceDefinitionDoesNotExistException(serviceDefinitionId);
	}

	@Override
	public Plan getPlan(String planId) throws ServiceDefinitionDoesNotExistException {
		for(ServiceDefinition serviceDefinition : catalogService.getCatalog().getServices()) {
			for (Plan currentPlan : serviceDefinition.getPlans()) {
				if (currentPlan.getId().equals(planId)) {
					return currentPlan;
				}
			}
		}
		throw new ServiceDefinitionDoesNotExistException("Missing plan for id: " + planId);
	}

}
