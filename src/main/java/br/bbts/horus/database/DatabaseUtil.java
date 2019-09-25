package br.bbts.horus.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseUtil {
	
	private static final String PERSISTENCE_UNIT = "psim";
	
	private static EntityManagerFactory emf;
	
	private static EntityManager em;
	
	public static EntityManager getEntityManager() {
		try {
			if(emf == null) {
				emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
			}
			
			return (em == null || !em.isOpen()) ? emf.createEntityManager() : em;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
