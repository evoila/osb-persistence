/**
 * 
 */
package de.evoila.cf.broker.persistence.repository;


import de.evoila.cf.broker.model.ServiceInstanceBinding;
import de.evoila.cf.broker.persistence.mongodb.repository.MongoDBBindingRepository;
import de.evoila.cf.broker.repository.BindingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Patrick Weber, evoila.
 *
 */
@Service
public class BindingRepositoryImpl implements BindingRepository {

	@Autowired
	private MongoDBBindingRepository bindingRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.evoila.cf.broker.repository.BindingRepository#getInternalBindingId(
	 * java.lang.String)
	 */
	@Override
	public String getInternalBindingId(String bindingId) {
		return bindingRepository.findById(bindingId).get().getServiceInstanceId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.evoila.cf.broker.repository.BindingRepository#addInternalBinding(java.
	 * lang.String, java.lang.String)
	 */
	@Override
	public void addInternalBinding(ServiceInstanceBinding binding) {
		bindingRepository.save(binding);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.evoila.cf.broker.repository.BindingRepository#
	 * containsInternalBindingId(java.lang.String)
	 */
	@Override
	public boolean containsInternalBindingId(String bindingId) {
		return bindingRepository.existsById(bindingId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.evoila.cf.broker.repository.BindingRepository#deleteBinding(java.lang.
	 * String)
	 */
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
