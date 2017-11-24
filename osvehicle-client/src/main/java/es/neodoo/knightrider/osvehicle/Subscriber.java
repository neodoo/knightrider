package es.neodoo.knightrider.osvehicle;

import java.util.ArrayList;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Subscriber {
	
		
	public static void main(String[] args) throws MqttException {
		
		String mosquittoIp = args[0];
		String OSVehicleId = args[1];
	    System.out.println("== START SUBSCRIBER ==");

	    MqttClient client=new MqttClient("tcp://" + mosquittoIp + ":1883", MqttClient.generateClientId());
	    client.setCallback( new SimpleMqttCallBack() );
	    client.connect();
	    client.subscribe("/unlock_door/" + OSVehicleId);
	    client.subscribe("/lock_door/" + OSVehicleId);

	  }
}
