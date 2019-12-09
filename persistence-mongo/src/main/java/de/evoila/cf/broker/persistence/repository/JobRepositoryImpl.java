package de.evoila.cf.broker.persistence.repository;

import de.evoila.cf.broker.model.JobProgress;
import de.evoila.cf.broker.persistence.mongodb.repository.MongoDBJobProgressRepository;
import de.evoila.cf.broker.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

/**
 * @author Patrick Weber, Johannes Hiemer.
 */
@Service
public class JobRepositoryImpl implements JobRepository {

	MongoDBJobProgressRepository jobProgressRepository;

	public JobRepositoryImpl(MongoDBJobProgressRepository jobProgressRepository) {
		this.jobProgressRepository = jobProgressRepository;
	}

	@Override
	public JobProgress getJobProgressById(String id) {
		return jobProgressRepository.findById(id).get();
	}

    @Override
    public JobProgress getJobProgressByReferenceId(String serviceInstanceId) {
        return jobProgressRepository.findFirstByReferenceIdOrderByDateDesc(serviceInstanceId).get();
    }

	@Override
	public JobProgress saveJobProgress(String id, String referenceId, String progress, String description, String operation) {
        JobProgress jobProgress = jobProgressRepository.findById(id).orElseThrow(NoSuchElementException::new);
        jobProgressRepository.save(jobProgress);

        return jobProgress;
	}

	@Override
	public JobProgress updateJobProgress(String id, String progress, String description) {
		JobProgress jobProgress = jobProgressRepository.findById(id).get();
		jobProgress.setState(progress);
		jobProgress.setDescription(description);
		jobProgressRepository.save(jobProgress);

		return jobProgress;
	}

	@Override
	public boolean containsJobProgress(String id) {
		return jobProgressRepository.existsById(id);
	}

	@Override
	public void deleteJobProgress(String id) {
        jobProgressRepository.deleteById(id);
	}

	@Override
	public void deleteJobProgressByReferenceId(String referenceId) {
		jobProgressRepository.deleteByReferenceId(referenceId);
	}
}
