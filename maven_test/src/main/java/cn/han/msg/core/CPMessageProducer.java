package cn.han.msg.core;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

class CPMessageProducer {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private KafkaProducer<String, byte[]> producer;

	CPMessageProducer(Properties props) {
		producer = new KafkaProducer<>(props);
	}

	void send(CPMessage msg) {
		send(msg, null);
	}

	void send(CPMessage msg, Callback callback) {
		ProducerRecord<String, byte[]> record = new ProducerRecord<>(
				msg.getTopic(), msg.getBatchNo(), SerializationUtils.serialize(msg));
		if (callback != null) {
			producer.send(record, callback);
		} else {
			try {
				producer.send(record).get();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

}
