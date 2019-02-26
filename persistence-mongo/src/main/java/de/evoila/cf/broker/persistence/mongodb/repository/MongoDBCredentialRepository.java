package de.evoila.cf.broker.persistence.mongodb.repository;

import de.evoila.cf.broker.model.credential.Credential;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Johannes Hiemer.
 */
public interface MongoDBCredentialRepository extends MongoRepository<Credential, String> {}
