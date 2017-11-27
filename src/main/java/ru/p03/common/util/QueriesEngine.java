/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.p03.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Parameter;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.commons.lang3.tuple.ImmutablePair;

/**
 *
 * @author timofeevan
 */
public class QueriesEngine /*extends AbstractQueriesEngine*/{
    
    private EntityManagerFactory emf;
    
    private QueriesEngine(String unitName, Map parameters){
        emf = createEntityManagerFactory(unitName, parameters);
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public EntityManagerFactory createEntityManagerFactory(String unitName, Map parameters) {
        EntityManagerFactory emf = null;
        if (parameters == null || parameters.isEmpty()){
            emf = Persistence.createEntityManagerFactory(unitName);
        }else{
            emf = Persistence.createEntityManagerFactory(unitName, parameters);
        }
        return emf;
    }
    
    public static QueriesEngine instance(String unitName, Map parameters){
        QueriesEngine qe = new QueriesEngine(unitName, parameters);
        return qe;
    }
    
    public static class QEParameter<ParamType> implements Parameter{
        private ParamType value;
        private String name;
        private Integer position;
        
        public QEParameter(ParamType value, String name, Integer position){
            this.value = value;
            this.name = name;
            this.position = position;
        }
        
        public ParamType getValue(){
            return value;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Integer getPosition() {
            return position;
        }

        @Override
        public Class getParameterType() {
            return value.getClass();
        }
        
    }
    
    public <T> T single(List<T> list){
        if (list != null){
            if (list.size() > 0){
                return list.get(0);
            }
        }
        return null;
    }
    
    public <T> List<T> getListTextQuery(Class clazz, String queryText, Map.Entry<String, Object> ... parameters){
         List<Map.Entry<String, Object>> asList = Arrays.asList(parameters);
         return getListTextQuery(clazz, queryText, asList);
    }
    
    public <T> List<T> getListNamedQuery(Class clazz, String queryText, Map.Entry<String, Object> ... parameters){
         List<Map.Entry<String, Object>> asList = Arrays.asList(parameters);
         return getListNamedQuery(clazz, queryText, asList);
    }
    
    public <T> List<T> getListTextQuery(Class clazz, String queryText, List<Map.Entry<String, Object>> parameters){
        List<T> list = new ArrayList<>();                       
        final EntityManager em = getEntityManager();
        TypedQuery<T> query = prepareTextQuery(em, clazz, queryText, parameters);
        list = query.getResultList();
        em.close();
        return list;
    }
    
    public <T> List<T> getListNamedQuery(Class clazz, String queryText, List<Map.Entry<String, Object>> parameters){
        List<T> list = new ArrayList<>();
        final EntityManager em = getEntityManager();
        TypedQuery<T> query = prepareNamedQuery(em, clazz, queryText, parameters);
        list = query.getResultList();
        em.close();
        return list;
    }
    
    public <T> List<T> getListNativeQuery(String queryText, List<Map.Entry<Integer, Object>> parameters) {
        List list = new ArrayList<>();
        final EntityManager em = getEntityManager();
        Query query = prepareNativeQuery(em, queryText, parameters);
        list = query.getResultList();
        em.close();
        return list;
    }

    public <T> List<T> getListNativeQueryQE(String queryText, List<QEParameter> parameters) {
        List list = new ArrayList<>();
        final EntityManager em = getEntityManager();
        Query query = qe_prepareNativeQuery(em, queryText, parameters);
        list = query.getResultList();
        em.close();
        return list;
    }
    
    public <T> List<T> getListNativeQueryQE(String queryText, QEParameter ... parameters){
         List<QEParameter> asList = Arrays.asList(parameters);
         return (List<T>)getListNativeQueryQE(queryText, asList);
    }
    
    public <T> List<T> getListNativeQuery(String queryText, Map.Entry<Integer, Object> ... parameters){
         List<Map.Entry<Integer, Object>> asList = Arrays.asList(parameters);
         return (List<T>)getListNativeQuery(queryText, asList);
    }
        
    public int executeNativeQuery(String queryText, List<Map.Entry<Integer, Object>> parameters) {
        int executeUpdate = 0;
        final EntityManager em = getEntityManager();
        final EntityTransaction transaction = em.getTransaction();
        Query query = prepareNativeQuery(em, queryText, parameters);
        transaction.begin();
        executeUpdate = query.executeUpdate();
        transaction.commit();
        em.close();
        return executeUpdate;
    }
    
    public int executeNativeQuery(String queryText, Map.Entry<Integer, Object> ... parameters){
         List<Map.Entry<Integer, Object>> asList = Arrays.asList(parameters);
         return executeNativeQuery(queryText, asList);
    }
    
    public <T> TypedQuery<T> prepareTextQuery(EntityManager em, Class clazz, String queryText, List<Map.Entry<String, Object>> parameters){
        TypedQuery<T> query = em.createQuery(queryText, clazz);
        if (parameters != null){
            parameters.forEach((entry) -> {
                query.setParameter(entry.getKey(), entry.getValue());
            });
        }
        return query;
    }
    
    public Query prepareNativeQuery(EntityManager em, String queryText, List<Map.Entry<Integer, Object>> parameters){
        Query query = em.createNativeQuery(queryText);
        if (parameters != null){
            parameters.forEach((entry) -> {
                query.setParameter(entry.getKey(), entry.getValue());
            });
        }
        return query;
    }
    
    public Query qe_prepareNativeQuery(EntityManager em, String queryText, List<QEParameter> parameters){
        Query query = em.createNativeQuery(queryText);
        if (parameters != null){
            parameters.forEach((p) -> {
                query.setParameter(p, p.getValue());
            });
        }
        return query;
    }
    
    public <T> Query prepareNativeQuery(EntityManager em, String queryText, ArrayList<Map.Entry<Parameter<T>, T>> parameters){
        Query query = em.createNativeQuery(queryText);
        if (parameters != null){
            parameters.forEach((entry) -> {
                query.setParameter(entry.getKey(), entry.getValue());
            });
        }
        return query;
    }
    
    public <T> TypedQuery<T> prepareNamedQuery(EntityManager em, Class clazz, String namedQuery, List<Map.Entry<String, Object>> parameters){
        TypedQuery<T> query = em.createNamedQuery(namedQuery, clazz);
        if (parameters != null){
            parameters.forEach((entry) -> {
                query.setParameter(entry.getKey(), entry.getValue());
            });
        }
        return query;
    }
    
    public Map.Entry<String, Object> pair(String s, Object o){
        ImmutablePair<String, Object> ip = new ImmutablePair<>(s, o);
        return ip;
    }
    
    public Map.Entry<Integer, Object> pair(Integer i, Object o){
        ImmutablePair<Integer, Object> ip = new ImmutablePair<>(i, o);
        return ip;
    }
    
    public <T> QEParameter<T> parameter(Integer i, String name, T o){
        return new QEParameter(o, name, i);
    }
    
}
