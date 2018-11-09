/**
 * 
 */
package de.evoila.cf.broker.persistence.mongodb.repository;

import de.evoila.cf.broker.model.RouteBinding;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Christian Brinker, evoila.
 *
 */
public interface RouteBindingRepository extends MongoRepository<RouteBinding, String> {

}
