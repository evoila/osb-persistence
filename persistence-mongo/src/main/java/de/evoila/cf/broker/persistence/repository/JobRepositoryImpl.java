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
	public JobProgress getJobProgress(String serviceInstanceId) {
		return jobProgressRepository.findById(serviceInstanceId).get();
	}

	/* (non-Javadoc)
	 * @see de.evoila.cf.broker.persistence.repository.JobRepository#saveOrUpdateJobProgress(java.lang.String, java.lang.String)
	 */
	@Override
	public void saveOrUpdateJobProgress(String serviceInstanceId, String progress) {
		JobProgress jobProgress = new JobProgress(serviceInstanceId, progress);
        jobProgressRepository.save(jobProgress);
	}

	/* (non-Javadoc)
	 * @see de.evoila.cf.broker.persistence.repository.JobRepository#containsJobProgress(java.lang.String)
	 */
	@Override
	public boolean containsJobProgress(String serviceInstanceId) {
		return jobProgressRepository.existsById(serviceInstanceId);
	}

	/* (non-Javadoc)
	 * @see de.evoila.cf.broker.persistence.repository.JobRepository#deleteJobProgress(java.lang.String)
	 */
	@Override
	public void deleteJobProgress(String serviceInstanceId) {
        jobProgressRepository.deleteById(serviceInstanceId);
	}

}
