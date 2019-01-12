/**
 * 
 */
package de.evoila.cf.broker.persistence.mongodb.repository;

import de.evoila.cf.broker.model.JobProgress;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * @author Christian Brinker, Johannes Hiemer.
 */
public interface JobProgressRepository extends MongoRepository<JobProgress, String> {

    Optional<JobProgress> findFirstByReferenceIdOrderByDateDesc(String referenceId);
}
