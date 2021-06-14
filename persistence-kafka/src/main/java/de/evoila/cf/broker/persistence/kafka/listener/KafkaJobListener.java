package de.evoila.cf.broker.persistence.kafka.listener;


import de.evoila.cf.broker.persistence.kafka.model.JobMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author Michael Hahn
 */
@Component
public class KafkaJobListener {

    private static final Logger log = LoggerFactory.getLogger(KafkaJobListener.class);

    @KafkaListener(topics = "backup-job", groupId = "jobMessage_json2", containerFactory = "jobMessageKafkaListenerFactory")
    public void listen(JobMessage jobMessage) {
        log.info("Job Status:" + jobMessage.jobStatus);
    }
}
