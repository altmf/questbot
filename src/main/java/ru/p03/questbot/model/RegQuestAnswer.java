/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.p03.questbot.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author altmf
 */
@Entity
@Table(name = "REG_QUEST_ANSWER", catalog = "QUEB", schema = "QUE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegQuestAnswer.findAll", query = "SELECT r FROM RegQuestAnswer r")})
public class RegQuestAnswer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "IS_DELETED")
    private Integer isDeleted;
    @Column(name = "DATE_ANSWER")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAnswer;
    @JoinColumn(name = "ID_ANSWER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ClsAnswer idAnswer;
    @JoinColumn(name = "ID_QUEST", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ClsQuest idQuest;

    public RegQuestAnswer() {
    }

    public RegQuestAnswer(Long id) {
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

    public Date getDateAnswer() {
        return dateAnswer;
    }

    public void setDateAnswer(Date dateAnswer) {
        this.dateAnswer = dateAnswer;
    }

    public ClsAnswer getIdAnswer() {
        return idAnswer;
    }

    public void setIdAnswer(ClsAnswer idAnswer) {
        this.idAnswer = idAnswer;
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
        if (!(object instanceof RegQuestAnswer)) {
            return false;
        }
        RegQuestAnswer other = (RegQuestAnswer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.p03.questbot.model.RegQuestAnswer[ id=" + id + " ]";
    }
    
}
