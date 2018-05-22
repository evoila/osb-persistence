/**
 * 
 */
package de.evoila.cf.broker.persistence.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.evoila.cf.broker.exception.ServiceBrokerException;
import de.evoila.cf.broker.exception.ServiceDefinitionDoesNotExistException;
import de.evoila.cf.broker.model.Catalog;
import de.evoila.cf.broker.model.Plan;
import de.evoila.cf.broker.model.ServiceDefinition;
import de.evoila.cf.broker.repository.ServiceDefinitionRepository;

/**
 * @author Christian Brinker & Johannes Hiemer, evoila.
 *
 */
@Repository
public class ServiceDefinitionRepositoryImpl implements ServiceDefinitionRepository {

	@Autowired
	private Catalog catalog;

	@Override
	public List<ServiceDefinition> getServiceDefinition() {
		return catalog.getServices();
	}

	// public Map<String, ServiceInstance> getServiceInstances() {
	// return serviceInstances;
	// }

	@Override
	public void validateServiceId(String serviceDefinitionId) throws ServiceDefinitionDoesNotExistException {
		for(ServiceDefinition serviceDefinition : catalog.getServices()) {
			if (serviceDefinitionId.equals(serviceDefinition.getId())) {
				return;
			}
		}

		throw new ServiceDefinitionDoesNotExistException(serviceDefinitionId);
	}

	@Override
	public Plan getPlan(String planId) throws ServiceDefinitionDoesNotExistException {
		for(ServiceDefinition serviceDefinition : catalog.getServices()) {
			for (Plan currentPlan : serviceDefinition.getPlans()) {
				if (currentPlan.getId().equals(planId)) {
					return currentPlan;
				}
			}
		}
		throw new ServiceDefinitionDoesNotExistException("Missing plan for id: " + planId);
	}

}
