/**
 * 
 */
package de.evoila.cf.broker.persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.evoila.cf.broker.model.ServiceInstance;
import de.evoila.cf.broker.repository.ServiceInstanceRepository;

import java.util.ArrayList;
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
		return serviceInstanceRepository.findById(instanceId).get();
	}

	@Override
	public List<ServiceInstance> getServiceInstancesByServiceDefinitionId(String serviceDefinitionId) {
		List<ServiceInstance> serviceInstances = new ArrayList<>();

		serviceInstanceRepository.findAll().forEach(serviceInstance -> {
			if(serviceInstance.getServiceDefinitionId() != null && serviceInstance.getServiceDefinitionId().equals(serviceDefinitionId)) {
				serviceInstances.add(serviceInstance);
			}
		});

		return serviceInstances;
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
