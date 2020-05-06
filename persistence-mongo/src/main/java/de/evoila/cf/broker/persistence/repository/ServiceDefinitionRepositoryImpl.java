package de.evoila.cf.broker.persistence.repository;

import de.evoila.cf.broker.exception.ServiceDefinitionDoesNotExistException;
import de.evoila.cf.broker.model.catalog.plan.Plan;
import de.evoila.cf.broker.model.catalog.ServiceDefinition;
import de.evoila.cf.broker.repository.ServiceDefinitionRepository;
import de.evoila.cf.broker.service.CatalogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Christian Brinker, Johannes Hiemer.
 */
@Repository
public class ServiceDefinitionRepositoryImpl implements ServiceDefinitionRepository {

	private Logger log = LoggerFactory.getLogger(getClass());

	private CatalogService catalogService;

	public ServiceDefinitionRepositoryImpl(CatalogService catalogService) {
	    this.catalogService = catalogService;
	}

	@Override
	public List<ServiceDefinition> getServiceDefinition() {
		return catalogService.getCatalog().getServices();
	}

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
			log.info("Service Definition: " + serviceDefinition.getId());
			for (Plan currentPlan : serviceDefinition.getPlans()) {
				log.info("Plan: " + currentPlan.getId());
				if (currentPlan.getId().equals(planId)) {
					return currentPlan;
				}
			}
		}
		throw new ServiceDefinitionDoesNotExistException("Missing plan for id: " + planId);
	}

}
