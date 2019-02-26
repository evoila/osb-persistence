package de.evoila.cf.broker.persistence.repository;


import de.evoila.cf.broker.model.ServiceInstanceBinding;
import de.evoila.cf.broker.persistence.mongodb.repository.MongoDBBindingRepository;
import de.evoila.cf.broker.repository.BindingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Patrick Weber, Johannes Hiemer.
 */
@Service
public class BindingRepositoryImpl implements BindingRepository {

	private MongoDBBindingRepository bindingRepository;

	public BindingRepositoryImpl(MongoDBBindingRepository bindingRepository) {
		this.bindingRepository = bindingRepository;
	}

	@Override
	public String getInternalBindingId(String bindingId) {
		return bindingRepository.findById(bindingId).get().getServiceInstanceId();
	}

	@Override
	public void addInternalBinding(ServiceInstanceBinding binding) {
		bindingRepository.save(binding);
	}

	@Override
	public boolean containsInternalBindingId(String bindingId) {
		return bindingRepository.existsById(bindingId);
	}

	@Override
	public void unbindService(String bindingId) {
		bindingRepository.deleteById(bindingId);
	}

	@Override
	public ServiceInstanceBinding findOne(String bindingId) {
		ServiceInstanceBinding findOne = bindingRepository.findById(bindingId).get();
		return findOne;
	}

	@Override
	public List<ServiceInstanceBinding> getBindingsForServiceInstance (String serviceInstanceId) {
		return bindingRepository.findByServiceInstanceId(serviceInstanceId);
	}

}
