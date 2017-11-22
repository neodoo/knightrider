package es.neodoo.knightrider.car.services.mqtt;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import es.neodoo.knightrider.car.services.simulation.hardware.VehicleHardwareInvoker;

public class Subscriber {
	
	private static final Log log = LogFactory.getLog(Subscriber.class);

	public static final String BROKER_URL = "tcp://192.168.1.144:1883";
	
	private MqttClient client;
	
	private int TestSystemVehicleId = 1;

	public Subscriber() {
		super();
		
	}

	public void unlockDoors(int vehicleId) {
		
		try {
			
            MemoryPersistence persistence = new MemoryPersistence();
			client = new MqttClient(BROKER_URL, "publisher1", persistence);
			client.connect();
			MqttMessage message = new MqttMessage();
			message.setPayload("unlock_door".getBytes());
			client.publish("/unlock_door/" + TestSystemVehicleId, message);
			client.disconnect();
			
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void lockDoors(int vehicleId) {
		
		try {
			
            MemoryPersistence persistence = new MemoryPersistence();
			client = new MqttClient(BROKER_URL, "publisher1", persistence);
			client.connect();
			MqttMessage message = new MqttMessage();
			message.setPayload("lock_door".getBytes());
			client.publish("/lock_door/" + TestSystemVehicleId, message);
			client.disconnect();
			
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
