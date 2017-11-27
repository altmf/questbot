/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.p03.questbot.model.repository;

import java.util.List;
import ru.p03.classifier.model.Classifier;
import ru.p03.questbot.model.ClsDocType;
import ru.p03.questbot.model.repository.exceptions.NonexistentEntityException;

/**
 *
 * @author timofeevan
 */
public interface ClassifierRepository {
    /**
	 * Adds a classifier.
	 *
	 * @param classifier
	 *            {@link Classifier}
	 */
	<T extends Classifier> void add(T classifier);

	/**
	 * Finds all classifiers.
	 *
	 * @param clazz
	 *            the class of the classifier
	 * @return the list of {@link Classifier}
	 */
	<T extends Classifier> List<T> find(Class<T> clazz);
        
        <T extends Classifier> T find(Class<T> clazz, Long id);
        
        <T extends Classifier> List<T> find(Class<T> clazz, boolean isDeleted);
	
	<T extends Classifier> List<T> getAll(Class<T> clazz);
        
        <T extends Classifier> List<T> getAll(Class<T> clazz, boolean isDeleted);

	<T> Classifier findDocumentType(Class<T> clazz, String code);
        
        public default ClsDocType findDocumentType(String code)  {
            return (ClsDocType)findDocumentType(ClsDocType.class, code);
        }
        
        <T extends Classifier> void delete(Class<T> clazz, Long id) throws NonexistentEntityException;
        
        <T extends Classifier> void create(T object);

        <T extends Classifier> void edit(T object) throws NonexistentEntityException, Exception;

}
