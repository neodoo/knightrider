#!/bin/bash

if [ $# -ne 1 ]; then
  echo "Por facor introduce la dirección IP"	
fi

IP=$1

sed -i -e "s/public static final String BROKER_URL = \"tcp:\/\/.*:1883\"/public static final String BROKER_URL = \"tcp:\/\/$IP:1883\"/" car-services/src/main/java/es/neodoo/knightrider/car/services/mqtt/Publisher.java

sed -i -e "s/\"auth-server-url\": \"http:\/\/.*:9080/\"auth-server-url\": \"http:\/\/$IP:9080/" car-services/src/main/webapp/WEB-INF/keycloak.json
	
sed -i -e "s/\"auth-server-url\": \"http:\/\/.*:9080/\"auth-server-url\": \"http:\/\/$IP:9080/" renting-services/src/main/webapp/WEB-INF/keycloak.json

sed -i -e "s/\"auth-server-url\": \"http:\/\/.*:9080/\"auth-server-url\": \"http:\/\/$IP:9080/" renting-services/src/main/webapp/WEB-INF/keycloak.json

sed -i -e "s/https:\/\/.*:/https:\/\/$IP:/" renting-mobile-app/drivip/src/pages/carInfo/carInfo.ts

sed -i -e "s/http:\/\/.*:/http:\/\/$IP:/" renting-mobile-app/drivip/src/pages/carInfo/carInfo.ts

sed -i -e "s/https:\/\/.*:/https:\/\/$IP:/" renting-mobile-app/drivip/src/pages/carRent/carRent.ts

sed -i -e "s/http:\/\/.*:/http:\/\/$IP:/" renting-mobile-app/drivip/src/pages/carRent/carRent.ts

sed -i -e "s/https:\/\/.*:/https:\/\/$IP:/" renting-mobile-app/drivip/src/pages/home/home.ts

sed -i -e "s/http:\/\/.*:/http:\/\/$IP:/" renting-mobile-app/drivip/src/pages/home/home.ts

sed -i -e "s/http:\/\/.*:/http:\/\/$IP:/" renting-mobile-app/drivip/src/pages/login/login.ts

sed -i -e "s/https:\/\/.*:/https:\/\/$IP:/" renting-mobile-app/drivip/src/pages/myProfile/myProfile.ts

sed -i -e "s/http:\/\/.*:/http:\/\/$IP:/" renting-mobile-app/drivip/src/pages/myProfile/myProfile.ts

sed -i -e "s/https:\/\/.*:/https:\/\/$IP:/" renting-mobile-app/drivip/src/pages/register4/register4.ts

sed -i -e "s/tcp:\/\/.*:1883\"/tcp:\/\/$IP:1883\"/" OSVehicle-client/src/main/java/es/neodoo/knightrider/osvehicle/Subscriber.java

mvn clean compile package -DskipTests

echo "Actualizadas las IP a $IP"
