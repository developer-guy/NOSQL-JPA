/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nosql_jpa.service;

import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Batuhan
 */
@Stateless
public class PersistenceService implements Serializable{

    protected static EntityManager entityManager = null;
    private static EntityManagerFactory entityManagerFactory = null;

    public PersistenceService() {

    }

    public static EntityManager getEntityManager() {
        try {
            if (entityManagerFactory == null) {
                entityManagerFactory = Persistence.createEntityManagerFactory("com.mycompany_NoSQL_JPA_war_1.0-SNAPSHOTPU");
                entityManager = entityManagerFactory.createEntityManager();
                return entityManager;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return entityManager;
    }
}
