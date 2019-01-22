/**
 * 
 */
package de.evoila.cf.broker.persistence.repository;

import de.evoila.cf.broker.model.ServiceInstance;
import de.evoila.cf.broker.repository.ServiceInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Patrick Weber, evoila.
 * @author Marco Di Martino
 *
 */
@Service
public class ServiceInstanceRepositoryImpl implements ServiceInstanceRepository {

	@Autowired
	de.evoila.cf.broker.persistence.mongodb.repository.ServiceInstanceRepository serviceInstanceRepository;

	@Override
	public ServiceInstance getServiceInstance(String instanceId) {
		return serviceInstanceRepository.findById(instanceId).orElse(null);
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
