package com.neodoo.knigthrider.auth;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

import org.keycloak.admin.client.*;
import org.keycloak.representations.idm.*;
import org.keycloak.admin.client.resource.*;

public class ServerManagement {

	private Keycloak keycloak;
	
	public ServerManagement(String server, String realm, String username, String password, String client) {
		this.keycloak = Keycloak.getInstance(server, realm, username, password, client);
	}
	
	private RealmResource getRealm(String realmName) {
		
		RealmResource realm = keycloak.realm(realmName);
		
		return realm;
		
	}

	public void createRealm(String realmName) {
		
		RealmRepresentation realm = new RealmRepresentation();
		realm.setRealm(realmName);

		keycloak.realms().create(realm);
		
	}

	public void createClient(String realmName, String clientId) {
		
		ClientRepresentation client = new ClientRepresentation();
		client.setClientId(clientId);
		//client.setRootUrl("http://foo");
		//client.setProtocol("openid-connect");
		
		getRealm(realmName).clients().create(client);
	
	}
	
	public void searchUser(String realmName, String username, String firstName, String lastName, String email) {
		
		List<UserRepresentation> users = getRealm(realmName).users().search(username, firstName, lastName, email, 0, 10);
		
		users.forEach(user -> System.out.println(user.getUsername()));
		
	}

	public void createUser(String realmName, String username, String password, String firstName, String lastName, String email) {
		
		UserRepresentation user = new UserRepresentation();
		user.setUsername(username);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setRequiredActions(Collections.<String>emptyList());
		
		CredentialRepresentation credential = new CredentialRepresentation();
		credential.setType(CredentialRepresentation.PASSWORD);
		credential.setValue(password);
		user.setCredentials(Arrays.asList(credential));
		
		user.setEnabled(true);

		getRealm(realmName).users().create(user);
		
	}
	
	public void updateUser(String realmName, String userUuid, String firstName, String lastName) {
	
		UserResource userResource = getRealm(realmName).users().get(userUuid);
		UserRepresentation user = userResource.toRepresentation();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		userResource.update(user);
		
	}
	
	public void deleteUser(String realmName, String userUuid) {
		
		UserResource userResource = getRealm(realmName).users().get(userUuid);
		userResource.remove();
	
	}
	
}
