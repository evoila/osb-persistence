package de.evoila.cf.broker.persistence.repository;

import de.evoila.cf.broker.model.credential.Credential;
import de.evoila.cf.broker.persistence.mongodb.repository.MongoDBCredentialRepository;
import de.evoila.cf.broker.repository.CredentialRepository;
import org.springframework.stereotype.Service;

/**
 * @author Johannes Hiemer.
 */
@Service
public class CredentialRepositoryImpl implements CredentialRepository {

    private MongoDBCredentialRepository mongoDBCredentialRepository;

    public CredentialRepositoryImpl(MongoDBCredentialRepository mongoDBCredentialRepository) {
        this.mongoDBCredentialRepository = mongoDBCredentialRepository;
    }

    @Override
    public void save(Credential credential) {
        this.mongoDBCredentialRepository.save(credential);
    }

    @Override
    public Credential getById(String identifier) {
        return this.mongoDBCredentialRepository.findById(identifier).get();
    }

    @Override
    public void deleteById(String identifier) {
        this.mongoDBCredentialRepository.deleteById(identifier);
    }

    @Override
    public void deleteByIdContains(String identifier) {
        this.mongoDBCredentialRepository.deleteById(identifier);
    }

    @Override
    public void delete(Credential credential) {
        this.mongoDBCredentialRepository.delete(credential);
    }
}
