/**
 * 
 */
package de.evoila.cf.broker.persistence.repository;

import de.evoila.cf.broker.model.JobProgress;
import de.evoila.cf.broker.persistence.mongodb.repository.JobProgressRepository;
import de.evoila.cf.broker.repository.JobRepository;
import org.springframework.stereotype.Service;

/**
 * @author Patrick Weber, evoila.
 *
 */
@Service
public class JobRepositoryImpl implements JobRepository {

	JobProgressRepository jobProgressRepository;

	public JobRepositoryImpl(JobProgressRepository jobProgressRepository) {
		this.jobProgressRepository = jobProgressRepository;
	}

	/* (non-Javadoc)
	 * @see de.evoila.cf.broker.persistence.repository.JobRepository#getJobProgress(java.lang.String)
	 */
	@Override
	public JobProgress getJobProgress(String id) {
		return jobProgressRepository.findById(id).get();
	}

	/* (non-Javadoc)
	 * @see de.evoila.cf.broker.persistence.repository.JobRepository#saveOrUpdateJobProgress(java.lang.String, java.lang.String)
	 */
	@Override
	public void saveJobProgress(String id, String progress, String description, String operation) {
		JobProgress jobProgress = new JobProgress(id, progress, description, operation);
        jobProgressRepository.save(jobProgress);
	}
	@Override
	public void updateJobProgress(String id, String progress, String description) {
		JobProgress jobProgress = jobProgressRepository.findById(id).get();
		jobProgress.setState(progress);
		jobProgress.setDescription(description);
		jobProgressRepository.save(jobProgress);
	}
	/* (non-Javadoc)
	 * @see de.evoila.cf.broker.persistence.repository.JobRepository#containsJobProgress(java.lang.String)
	 */
	@Override
	public boolean containsJobProgress(String id) {
		return jobProgressRepository.existsById(id);
	}

	/* (non-Javadoc)
	 * @see de.evoila.cf.broker.persistence.repository.JobRepository#deleteJobProgress(java.lang.String)
	 */
	@Override
	public void deleteJobProgress(String id) {
        jobProgressRepository.deleteById(id);
	}

}
