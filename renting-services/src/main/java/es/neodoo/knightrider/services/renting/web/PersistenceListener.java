package es.neodoo.knightrider.services.renting.web;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

// https://blogs.sap.com/2012/12/11/put-jpa-in-your-web-app-tomcat-eclipselink/
// https://www.journaldev.com/2905/hibernate-tomcat-jndi-datasource-example-tutorial
// http://www.objectdb.com/tutorial/jpa/eclipse/web
// http://www.programering.com/a/MjN4kzMwATA.html

@WebListener
public class PersistenceListener implements ServletContextListener {

	private String KNIGHTRIDER_PERSISTENCE_UNIT_NAME = "KnightriderPU";
	
    private static EntityManagerFactory emf;

    @Override
	public void contextInitialized(ServletContextEvent event){
    	emf = Persistence.createEntityManagerFactory(KNIGHTRIDER_PERSISTENCE_UNIT_NAME);
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent event) {
    	emf.close();
    }
    
    public static EntityManager createEntityManager() {
    
    	if (emf == null) {
            throw new IllegalStateException("Context is not initialized yet.");
        }

        return emf.createEntityManager();
        
    }
    
}
