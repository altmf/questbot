/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.p03.classifier.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author timofeevan
 */
public abstract class Classifier implements Serializable{

    private static final long serialVersionUID = 1L;
//    @XmlTransient
//	private Long id;

//    @XmlTransient
//	private Period lifetime;

//	@XmlTransient
//	private String dsc;

	public Classifier() {
		//this.lifetime = new Period();
	}
        
        public abstract Long getId();
        public abstract Integer getIsDeleted();
        public abstract void setIsDeleted(Integer isDeleted);

//    public Classifier(Period lifetime) {
//		this.lifetime = lifetime;
//	}
//
//	// @Embedded
//	public Period getLifetime() {
//		return lifetime;
//	}
//
//    public void setLifetime(Period lifetime) {
//        this.lifetime = lifetime;
//    }

    /**
	 * This method should not be used in business code.
	 *
	 * deprecated [schurukanov]: see
	 *             {@link com.estylesoft.pfr.spu.classifier.infrastructure.ClassifierRepositoryImpl#add(Classifier)}
	 */
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	// @Id
//	// @Column(name = "ID")
//    public Long getId() {
//        return id;
//    }
//
//    @Override
//	public int hashCode() {
//		return this.getId() != null ? this.getId().hashCode() : 0;
//	}
//
//	@Override
//	public boolean equals(Object object) {
//		if (this == object) {
//			return true;
//		}
//		if (object == null || getClass() != object.getClass()) {
//			return false;
//		}
//
//		Classifier classifier = (Classifier) object;
//        if(this.getId() != null){
//            return this.getId().equals(classifier.getId());
//        } else {
//            return (classifier.getId() == null);
//        }
//	}
//
//	//
//	// Infrastructure stuff
//	//
//
//	// @Transient
//	public Long getPersistenceId() {
//		return getId();
//	}
//
//
//	// @Transient
//	public String getDsc() {
//		return dsc;
//	}
//
//	public void setDsc(String dsc) {
//		this.dsc = dsc;
//	}
//
//    @Override
//    public String toString() {
//        return "Classifier{" +
//                "id=" + getId() +
//                ", dsc='" + getDsc() + '\'' +
//                ", persistenceId=" + getPersistenceId() +
//                '}';
//    }
}
