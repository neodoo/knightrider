package es.neodoo.knightrider.car.services.mqtt;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Publisher {
	
	private static final Log log = LogFactory.getLog(Publisher.class);

	public static final String BROKER_URL = "tcp://192.168.1.40:1883";
	
	private MqttClient client;
	
	private int TestSystemVehicleId = 1;

	public Publisher() {
		super();
		
	}

	public void unlockDoors(int vehicleId) {
		
		try {
			
            MemoryPersistence persistence = new MemoryPersistence();
			client = new MqttClient(BROKER_URL, "publisher", persistence);
			client.connect();
			MqttMessage message = new MqttMessage();
			message.setPayload("unlock_door".getBytes());
			client.publish("/unlock_door/" + TestSystemVehicleId, message);
			client.disconnect();
			
		} catch (MqttException e) {
			e.printStackTrace();
		}
		

	}

	public void lockDoors(int vehicleId) {
		
		try {
			log.info("DENTRO DEL LOCK");
            MemoryPersistence persistence = new MemoryPersistence();
			client = new MqttClient(BROKER_URL, "publisher", persistence);
			client.connect();
			MqttMessage message = new MqttMessage();
			message.setPayload("lock_door".getBytes());
			client.publish("/lock_door/" + TestSystemVehicleId, message);
			client.disconnect();
			
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

}
