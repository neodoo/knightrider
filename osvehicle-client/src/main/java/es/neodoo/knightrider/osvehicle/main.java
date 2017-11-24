package es.neodoo.knightrider.osvehicle;

import org.eclipse.paho.client.mqttv3.MqttException;

public class main {
	
	public static void main(String[] args) throws MqttException {

	    if (args.length != 2) {
	      throw new IllegalArgumentException("Primer parámetro IP del servidor mosquitto /n Segundo parámetyro ID del vehículo");
	    }
	        Subscriber.main(args);

	  }
}
