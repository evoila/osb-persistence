/**
 * 
 */
package de.evoila.cf.broker.persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.evoila.cf.broker.model.JobProgress;
import de.evoila.cf.broker.persistence.mongodb.repository.JobProgressRepository;
import de.evoila.cf.broker.repository.JobRepository;

/**
 * @author Patrick Weber, evoila.
 *
 */
@Service
public class JobRepositoryImpl implements JobRepository {
	
	@Autowired
	JobProgressRepository jobProgressRepository;
	

	/* (non-Javadoc)
	 * @see de.evoila.cf.broker.persistence.repository.JobRepository#getJobProgress(java.lang.String)
	 */
	@Override
	public JobProgress getJobProgress(String serviceInstanceId) {
		return jobProgressRepository.findOne(serviceInstanceId);
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
		return jobProgressRepository.exists(serviceInstanceId);
	}

	/* (non-Javadoc)
	 * @see de.evoila.cf.broker.persistence.repository.JobRepository#deleteJobProgress(java.lang.String)
	 */
	@Override
	public void deleteJobProgress(String serviceInstanceId) {
        jobProgressRepository.delete(serviceInstanceId);
	}

}
