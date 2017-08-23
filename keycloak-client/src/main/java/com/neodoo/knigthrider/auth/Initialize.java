package com.neodoo.knigthrider.auth;

public class Initialize {

	public static final String KEYCLOAK_SERVER = "http://localhost:8080/auth";
	
	public static final String KEYCLOAK_REALM = "master";

	public static final String KEYCLOAK_CLIENT = "admin-cli";
	
	public static final String KEYCLOAK_ADMIN_USERNAME = "admin";
	
	public static final String KEYCLOAK_ADMIN_PASSWORD = ".admin8$";
	
	public static final String KEYCLOAK_KNIGHTRIDER_REALM = "knightrider_realm";
	
	public static final String KEYCLOAK_KNIGHTRIDER_CLIENT = "knightrider_client";

	public static final String KEYCLOAK_KNIGHTRIDER_USERNAME = "demo";
	
	public static final String KEYCLOAK_KNIGHTRIDER_PASSWORD = ".demo8$";
	
	
	private static void init(String server, String realm, String username, String password, String client) {
		
		// Log to server
		ServerManagement serverManagement = new ServerManagement(server, realm, username, password, client);
		
		// Create realm
		serverManagement.createRealm(KEYCLOAK_KNIGHTRIDER_REALM);
		
		// Create client
		serverManagement.createClient(KEYCLOAK_KNIGHTRIDER_REALM, KEYCLOAK_KNIGHTRIDER_CLIENT);

		// Create user
		serverManagement.createUser(KEYCLOAK_KNIGHTRIDER_REALM, KEYCLOAK_KNIGHTRIDER_USERNAME, KEYCLOAK_KNIGHTRIDER_PASSWORD, null, null, null);
		
	}
	
	public static void main( String[] args ) {
	
		if (args.length == 1) { 
			
			String server = args[0];
			String urlServer = KEYCLOAK_SERVER.replace("localhost", server);
			 
			init(urlServer, KEYCLOAK_REALM, KEYCLOAK_ADMIN_USERNAME, KEYCLOAK_ADMIN_PASSWORD, KEYCLOAK_CLIENT);
		
		} else {
		
			System.err.println("Use: Initialize <hostname> where <ip> is Keycloak hostname server.\n");
			System.exit(1);
		
		}
		
	}

}
