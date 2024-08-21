package prova;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("prova");

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }
}
