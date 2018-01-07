/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.p03.questbot.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import ru.p03.classifier.model.Classifier;

/**
 *
 * @author altmf
 */
@Entity
@Table(name = "CLS_QUEST_PHOTO", catalog = "QUEB", schema = "QUE")
@XmlRootElement
public class ClsQuestPhoto extends Classifier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "IS_DELETED")
    private Integer isDeleted;
    @Lob
    @Column(name = "REL_FILE_PATH")
    private String relFilePath;
    @Lob
    @Column(name = "PHOTO_TEXT")
    private String photoText;
    @JoinColumn(name = "ID_QUEST", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ClsQuest idQuest;

    public ClsQuestPhoto() {
    }

    public ClsQuestPhoto(Long id) {
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

    public String getRelFilePath() {
        return relFilePath;
    }

    public void setRelFilePath(String relFilePath) {
        this.relFilePath = relFilePath;
    }

    public String getPhotoText() {
        return photoText;
    }

    public void setPhotoText(String photoText) {
        this.photoText = photoText;
    }

    public ClsQuest getIdQuest() {
        return idQuest;
    }

    public void setIdQuest(ClsQuest idQuest) {
        this.idQuest = idQuest;
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
        if (!(object instanceof ClsQuestPhoto)) {
            return false;
        }
        ClsQuestPhoto other = (ClsQuestPhoto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.p03.questbot.model.ClsQuestPhoto[ id=" + id + " ]";
    }
    
}
