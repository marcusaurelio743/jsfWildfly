package posjava;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
	public static EntityManagerFactory factory = null;
	
	static {
		Init();
	}
	
	private static void Init() {
		try {
			if(factory == null) {
				factory = Persistence.createEntityManagerFactory("pos-java-hibernate");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();//prover uma persistencia no banco de dados
	}
	
	public static  Object getPrimaryKey(Object entidade) {//retorna primary key
		return factory.getPersistenceUnitUtil().getIdentifier(entidade);
	}

}
