package es.neodoo.knightrider.car.services.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class PublisherCallback implements MqttCallback {

    public void connectionLost(Throwable cause) {}

    public void messageArrived(String topic, MqttMessage message) {
         System.out.println(topic + ": " + message.toString());
    }

    public void deliveryComplete(IMqttDeliveryToken token) {}

}