package de.evoila.cf.broker.persistence.repository;

import de.evoila.cf.broker.model.Platform;
import de.evoila.cf.broker.repository.PlatformRepository;
import de.evoila.cf.broker.service.PlatformService;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Christian Brinker.
 */
@Repository
public class PlatformRepositoryImpl implements PlatformRepository {

	private Map<Platform, PlatformService> platformServices = new ConcurrentHashMap<Platform, PlatformService>();

	@Override
	public void addPlatform(Platform platform, PlatformService platformService) {
		if (platformServices.get(platform) == null)
			platformServices.put(platform, platformService);
		else
			throw new BeanCreationException("Cannot add multiple instances of platform service to PlatformRepository");
	}

	@Override
	public PlatformService getPlatformService(Platform platform) {
		return platformServices.get(platform);
	}

}
