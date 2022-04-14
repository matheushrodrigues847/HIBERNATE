package br.com.matheus.loja.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class JPAutil {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("loja");

    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
}
