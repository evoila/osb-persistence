/**
 * 
 */
package de.evoila.cf.broker.persistence.mongodb.repository;

import de.evoila.cf.broker.model.JobProgress;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Christian Brinker, evoila.
 *
 */
public interface JobProgressRepository extends MongoRepository<JobProgress, String> {

}
