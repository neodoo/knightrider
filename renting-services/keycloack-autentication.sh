#!/bin/bash

echo "> Calling Keycloak server ..."
RESULT=`curl --data "grant_type=password&client_id=knightrider_client&username=demo&password=.demo8$" http://192.168.1.36:9080/auth/realms/knightrider_realm/protocol/openid-connect/token`

TOKEN=`echo $RESULT | sed 's/.*access_token":"//g' | sed 's/".*//g'`

echo "Token: " $TOKEN "\n\n"

echo "> Calling  a rest from renting-services ..."
RESULT=`curl -X POST http://192.168.1.36:8080/renting-services/api/1/vehicles/test -H "Authorization: bearer $TOKEN"`

echo $RESULT





