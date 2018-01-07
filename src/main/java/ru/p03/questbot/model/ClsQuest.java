/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.p03.questbot.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import ru.p03.classifier.model.Classifier;

/**
 *
 * @author altmf
 */
@Entity
@Table(name = "CLS_QUEST", catalog = "QUEB", schema = "QUE")
@XmlRootElement
public class ClsQuest extends Classifier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "IS_DELETED")
    private Integer isDeleted;
    @Lob
    @Column(name = "QUEST_TEXT")
    private String questText;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idQuest")
    private Collection<RegQuestAnswer> regQuestAnswerCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idQuest")
    private Collection<ClsAnswer> clsAnswerCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idQuest")
    private Collection<ClsQuestPhoto> clsQuestPhotoCollection;

    public ClsQuest() {
    }

    public ClsQuest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getQuestText() {
        return questText;
    }

    public void setQuestText(String questText) {
        this.questText = questText;
    }

    @XmlTransient
    public Collection<RegQuestAnswer> getRegQuestAnswerCollection() {
        return regQuestAnswerCollection;
    }

    public void setRegQuestAnswerCollection(Collection<RegQuestAnswer> regQuestAnswerCollection) {
        this.regQuestAnswerCollection = regQuestAnswerCollection;
    }

    @XmlTransient
    public Collection<ClsAnswer> getClsAnswerCollection() {
        return clsAnswerCollection;
    }

    public void setClsAnswerCollection(Collection<ClsAnswer> clsAnswerCollection) {
        this.clsAnswerCollection = clsAnswerCollection;
    }

    @XmlTransient
    public Collection<ClsQuestPhoto> getClsQuestPhotoCollection() {
        return clsQuestPhotoCollection;
    }

    public void setClsQuestPhotoCollection(Collection<ClsQuestPhoto> clsQuestPhotoCollection) {
        this.clsQuestPhotoCollection = clsQuestPhotoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClsQuest)) {
            return false;
        }
        ClsQuest other = (ClsQuest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.p03.questbot.model.ClsQuest[ id=" + id + " ]";
    }
    
}
