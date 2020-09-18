package de.evoila.cf.broker.persistence.repository;

import de.evoila.cf.broker.exception.ServiceDefinitionDoesNotExistException;
import de.evoila.cf.broker.exception.ServiceDefinitionPlanDoesNotExistException;
import de.evoila.cf.broker.model.catalog.ServiceDefinition;
import de.evoila.cf.broker.model.catalog.plan.Plan;
import de.evoila.cf.broker.repository.ServiceDefinitionRepository;
import de.evoila.cf.broker.service.CatalogService;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Christian Brinker, Johannes Hiemer.
 */
@Repository
public class ServiceDefinitionRepositoryImpl implements ServiceDefinitionRepository {

	private CatalogService catalogService;

	public ServiceDefinitionRepositoryImpl(CatalogService catalogService) {
	    this.catalogService = catalogService;
	}

    @Override
    public List<ServiceDefinition> getServiceDefinitions() {
        return catalogService.getCatalog().getServices();
    }

    @Override
    public ServiceDefinition getServiceDefinition(String serviceId) throws ServiceDefinitionDoesNotExistException {
        return getServiceDefinitions().stream()
                .filter(serviceDefinition -> serviceId.equals(serviceDefinition.getId()))
                .findFirst().orElseThrow(() -> new ServiceDefinitionDoesNotExistException(serviceId));
    }

    @Override
    public void validateServiceId(String serviceDefinitionId) throws ServiceDefinitionDoesNotExistException {
        getServiceDefinition(serviceDefinitionId);
    }

    @Override
    public Plan getPlan(String serviceId, String planId) throws ServiceDefinitionDoesNotExistException, ServiceDefinitionPlanDoesNotExistException {
        return getServiceDefinition(serviceId).getPlans().stream()
                .filter(plan -> planId.equals(plan.getId()))
                .findFirst().orElseThrow(() -> new ServiceDefinitionPlanDoesNotExistException(serviceId, planId));
    }

}
