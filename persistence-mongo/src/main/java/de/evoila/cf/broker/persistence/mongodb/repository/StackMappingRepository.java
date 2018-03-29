package de.evoila.cf.broker.persistence.mongodb.repository;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.mongodb.repository.MongoRepository;

<<<<<<< HEAD
=======



>>>>>>> 2a819ad6767177e0bf1b1d303edd4bae509e717b
/**
 * @author Christian Brinker, evoila.
 *
 */
@ConditionalOnProperty(prefix = "openstack", name = { "endpoint" }, havingValue = "")
<<<<<<< HEAD
public interface StackMappingRepository extends MongoRepository<ServiceStackMapping, String> {
=======
public interface StackMappingRepository extends MongoRepository<ClusterStackMapping, String> {
>>>>>>> 2a819ad6767177e0bf1b1d303edd4bae509e717b

}
