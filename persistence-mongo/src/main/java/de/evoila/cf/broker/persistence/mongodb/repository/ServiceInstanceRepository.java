/**
 * 
 */
package de.evoila.cf.broker.persistence.mongodb.repository;

import de.evoila.cf.broker.model.ServiceInstance;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author Christian Brinker, evoila.
 *
 */
public interface ServiceInstanceRepository extends MongoRepository<ServiceInstance, String> {

    List<ServiceInstance> findByServiceDefinitionId(String serviceDefinitionId);

}
