package de.evoila.cf.broker.persistence.repository;

import de.evoila.cf.broker.exception.ServiceInstanceDoesNotExistException;
import de.evoila.cf.broker.model.ServiceInstance;
import de.evoila.cf.broker.persistence.mongodb.repository.MongoDBServiceInstanceRepository;
import de.evoila.cf.broker.repository.ServiceInstanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Patrick Weber, Marco Di Martino.
 */
@Service
public class ServiceInstanceRepositoryImpl implements ServiceInstanceRepository {

    private MongoDBServiceInstanceRepository serviceInstanceRepository;

    public ServiceInstanceRepositoryImpl(MongoDBServiceInstanceRepository mongoDBServiceInstanceRepository) {
        this.serviceInstanceRepository = mongoDBServiceInstanceRepository;
    }

    @Override
    public ServiceInstance getServiceInstance(String instanceId) throws ServiceInstanceDoesNotExistException {
        return getServiceInstanceOptional(instanceId).orElseThrow(() -> new ServiceInstanceDoesNotExistException(instanceId));
    }

    public Optional<ServiceInstance> getServiceInstanceOptional(String instanceId) {
        return serviceInstanceRepository.findById(instanceId);
    }

	@Override
	public List<ServiceInstance> getServiceInstancesByServiceDefinitionId(String serviceDefinitionId) {
		return serviceInstanceRepository.findByServiceDefinitionId(serviceDefinitionId);
	}

	@Override
	public boolean containsServiceInstanceId(String serviceInstanceId) {
		return serviceInstanceRepository.existsById(serviceInstanceId);
	}

	@Override
	public void addServiceInstance(String id, ServiceInstance serviceInstance) {
		if (!id.equals(serviceInstance.getId())) {
			serviceInstance = new ServiceInstance(id, serviceInstance.getServiceDefinitionId(),
					serviceInstance.getPlanId(), serviceInstance.getOrganizationGuid(), serviceInstance.getSpaceGuid(),
					serviceInstance.getParameters(), serviceInstance.getDashboardUrl(),
					serviceInstance.getInternalId());
		}
		serviceInstanceRepository.save(serviceInstance);
	}

	@Override
	public void deleteServiceInstance(String serviceInstanceId) {
		serviceInstanceRepository.deleteById(serviceInstanceId);
	}

	@Override
	public void updateServiceInstance(ServiceInstance serviceInstance){
		serviceInstanceRepository.save(serviceInstance);

	}

}
