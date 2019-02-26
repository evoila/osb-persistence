package de.evoila.cf.broker.persistence.mongodb.repository;

import de.evoila.cf.broker.model.RouteBinding;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Christian Brinker.
 */
public interface MongoDBRouteBindingRepository extends MongoRepository<RouteBinding, String> {

}
