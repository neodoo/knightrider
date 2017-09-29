package es.neodoo.knightrider.services.renting.model.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.neodoo.knightrider.services.renting.exceptions.DAOException;
import es.neodoo.knightrider.services.renting.model.vo.Customer;
import es.neodoo.knightrider.services.renting.model.vo.Vehicle;
import es.neodoo.knightrider.services.renting.model.vo.VehicleTravel;
import es.neodoo.knightrider.services.renting.model.vo.VehicleTraveling;
import es.neodoo.knightrider.services.renting.web.PersistenceListener;

public class VehicleTravelDAOImpl implements VehicleTravelDAO {

	private static final Log log = LogFactory.getLog(VehicleTravelDAOImpl.class);

	private EntityManager em;
	
	public VehicleTravelDAOImpl() {
		super();
	}

	@Override
	public void createTravel(VehicleTraveling vehicleTraveling, Vehicle vehicle, Timestamp dateEnd, Double cost, double time) throws DAOException {

		try {

			em = PersistenceListener.createEntityManager();
			em.getTransaction().begin();
			
			Customer customer = em.getReference(Customer.class, vehicleTraveling.getCustomer().getEmail());
			Vehicle vehicleReferenced = em.getReference(Vehicle.class, vehicle.getId()); 

			VehicleTravel vehicleTravel = new VehicleTravel();
			vehicleTravel.setCustomer(customer);
			vehicleTravel.setVehicle(vehicleReferenced);
			vehicleTravel.setDateStart(vehicleTraveling.getDateStart());
			vehicleTravel.setLatitudeStart(vehicleTraveling.getLatitudeStart());
			vehicleTravel.setLongitudeStart(vehicleTraveling.getLongitudeStart());
			vehicleTravel.setDateEnd(dateEnd);
			vehicleTravel.setLatitudeEnd(vehicle.getLatitude());
			vehicleTravel.setLongitudeEnd(vehicle.getLongitude());
			vehicleTravel.setCost(cost);
			vehicleTravel.setTime(time);
			
			em.persist(vehicleTravel);

			log.debug("insert into vehicle travel: " + vehicleTraveling.toString() + dateEnd + vehicle.toString() + cost + time);

		} catch(IllegalStateException | IllegalArgumentException | PersistenceException e) {
			log.error("Error inserting vehicleTraveling " + e);
			throw new DAOException(e);
		} finally {
			em.getTransaction().commit();
			em.close();
		}

	}

	@Override
	public long countTravels(String username) throws DAOException {

		long numTravels = 0;

		try {

			em = PersistenceListener.createEntityManager();

			String jpql = "SELECT count(*) FROM VehicleTravel v WHERE v.customer.email = :username";

			Query query= em.createQuery(jpql);
			query.setParameter("username", username);		

			numTravels =  (Long) query.getSingleResult();

			log.debug("geting numTravels of user: "+ username +" = " + numTravels);

		} catch (NoResultException e) {
			log.debug("No travels for user:" + username, e);

		} catch (NonUniqueResultException | IllegalStateException | IllegalArgumentException e) {
			log.error("Error getting  num travels: " + e);
			throw new DAOException(e);
		} finally {
			em.close();
		}

		return numTravels;

	}

	@Override
	public double getCost(String username) throws DAOException {

		double cost = 0;

		try {

			em = PersistenceListener.createEntityManager();

			String jpql = "SELECT SUM(v.cost) FROM VehicleTravel v WHERE v.customer.email = :username";

			Query query= em.createQuery(jpql);
			query.setParameter("username", username);

			cost =  (Double) query.getSingleResult();

			log.debug("getting cost of user: "+ username + " = " + cost);

		} catch (NoResultException e) {
			log.debug("No travels for user" + username, e);

		} catch (NonUniqueResultException | IllegalStateException | IllegalArgumentException e) {
			log.error("Error getting cost: " + e);
			throw new DAOException(e);
		} finally {
			em.close();
		}

		return cost;

	}

	@Override
	public double getTime(String username) throws DAOException {

		double time = 0;

		try {

			em = PersistenceListener.createEntityManager();

			String jpql = "SELECT SUM(v.time) FROM VehicleTravel v WHERE v.customer.email = :username";

			Query query= em.createQuery(jpql);
			query.setParameter("username", username);		

			time =  (Double) query.getSingleResult();

		} catch (NoResultException e) {
			log.debug("No travels for user" + username, e);

		} catch (NonUniqueResultException | IllegalStateException | IllegalArgumentException e) {
			log.error("Error getting time: " + e);
			throw new DAOException(e);
		} finally {
			em.close();
		}

		return time;

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<VehicleTravel> getTravels(String username) throws DAOException {

		List<VehicleTravel> travels = null;

		try{
			
			em = PersistenceListener.createEntityManager();

			String jpql = "SELECT v FROM VehicleTravel v WHERE v.customer.email = :username";

			Query query= em.createQuery(jpql);
			query.setParameter("username", username);	

			travels = query.getResultList();

			log.debug("geting Travels of user: " + " = " + travels.toString());

		} catch (NoResultException e) {
			log.debug("user dont has tarvels:" + username, e);

		} catch (NonUniqueResultException | IllegalStateException | IllegalArgumentException e) {
			log.error("Error getting travels: " + e);
			throw new DAOException(e);
		} finally {
			em.close();
		}

		return travels;

	}

}
