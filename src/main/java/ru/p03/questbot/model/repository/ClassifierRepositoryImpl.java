/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.p03.questbot.model.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import ru.p03.classifier.model.Classifier;
import ru.p03.common.util.QueriesEngine;
import ru.p03.questbot.model.repository.exceptions.NonexistentEntityException;

/**
 *
 * @author timofeevan
 */
public class ClassifierRepositoryImpl implements ClassifierRepository{

    /**
     * @return the DAO
     */
    public QueriesEngine getDAO() {
        return DAO;
    }

    /**
     * @param DAO the DAO to set
     */
    public void setDAO(QueriesEngine DAO) {
        this.DAO = DAO;
    }

    private QueriesEngine DAO;
    
    @Override
    public <T extends Classifier> void add(T classifier) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T extends Classifier> List<T> find(Class<T> clazz) {
        return getAll(clazz);
    }
    
    @Override
    public <T extends Classifier> List<T> find(Class<T> clazz, boolean isDeleted) {
        return getAll(clazz, isDeleted);
    }
    
    @Override
    public <T extends Classifier> T find(Class<T> clazz, Long id) {
        String text = " SELECT c FROM " + clazz.getSimpleName() 
                + " c  WHERE c.isDeleted = 0 AND c.id = :id";
        T obj = DAO.<T>single(DAO.<T>getListTextQuery(clazz, text, DAO.pair("id", id)));
        return (T)obj;
    }

    @Override
    public <T extends Classifier> List<T> getAll(Class<T> clazz) {
        return getAll(clazz, false);
    }
    
    @Override
    public <T extends Classifier> List<T> getAll(Class<T> clazz, boolean isDeleted) {
        String text = " SELECT c FROM " + clazz.getSimpleName() + " c  WHERE c.isDeleted = :isDeleted";
        List<T> list = DAO.<T>getListTextQuery(clazz, text, DAO.pair("isDeleted", isDeleted ? 1 : 0));
        return list;
    }

    @Override
    public <T> Classifier findDocumentType(Class<T> clazz, String code) {
        String text = " SELECT c FROM " + clazz.getSimpleName() 
                + " c  WHERE c.isDeleted = 0 AND c.code = :code";
        T obj = DAO.<T>single(DAO.<T>getListTextQuery(clazz, text, DAO.pair("code", code)));
        return (Classifier)obj;
    }
    
    @Override
    public<T extends Classifier> void delete(Class<T> clazz, Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getDAO().getEntityManager();
            em.getTransaction().begin();
            Classifier cls;
            try {
                cls = (Classifier)em.getReference(clazz, id);
                cls.getId();
                cls.setIsDeleted(1);
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clssifier" + clazz + " with id " + id + " no longer exists.", enfe);
            }
            em.merge(cls);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public <T extends Classifier> void create(T object) {
        EntityManager em = null;
        try {
            em = getDAO().getEntityManager();
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public <T extends Classifier> void edit(T object) throws NonexistentEntityException, Exception {
        if (object.getId() == null){
            create(object);
            return;
        }
        EntityManager em = null;
        try {
            em = getDAO().getEntityManager(); 
            em.getTransaction().begin();
            object = em.merge(object);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = object.getId();
                if (em.find(object.getClass(), id) == null) {
                    throw new NonexistentEntityException("The " + object.getClass() + " with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


}
