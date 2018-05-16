package com.hizliyol.core.entity;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author grkn
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "constant_item", catalog = "randevu", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConstantItem.findAll", query = "SELECT c FROM ConstantItem c")
    , @NamedQuery(name = "ConstantItem.findById", query = "SELECT c FROM ConstantItem c WHERE c.id = :id")
    , @NamedQuery(name = "ConstantItem.findByName", query = "SELECT c FROM ConstantItem c WHERE c.name = :name")
    , @NamedQuery(name = "ConstantItem.findByCreatedDate", query = "SELECT c FROM ConstantItem c WHERE c.createdDate = :createdDate")
    , @NamedQuery(name = "ConstantItem.findByCreatedUser", query = "SELECT c FROM ConstantItem c WHERE c.createdUser = :createdUser")
    , @NamedQuery(name = "ConstantItem.findByUpdatedDate", query = "SELECT c FROM ConstantItem c WHERE c.updatedDate = :updatedDate")
    , @NamedQuery(name = "ConstantItem.findByUpdatedUser", query = "SELECT c FROM ConstantItem c WHERE c.updatedUser = :updatedUser")
    , @NamedQuery(name = "ConstantItem.findByType", query = "SELECT c FROM ConstantItem c WHERE c.type = :type")
    , @NamedQuery(name = "ConstantItem.findByValue", query = "SELECT c FROM ConstantItem c WHERE c.value = :value")})
public class ConstantItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @CreatedDate
    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @CreatedBy
    @Column(name = "createdUser")
    private String createdUser;
    @LastModifiedDate
    @Column(name = "updatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @LastModifiedBy
    @Column(name = "updatedUser")
    private String updatedUser;
    @Column(name = "type")
    private String type;
    @Column(name = "value")
    private String value;

    public ConstantItem() {
    }

    public ConstantItem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(String updatedUser) {
        this.updatedUser = updatedUser;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
        if (!(object instanceof ConstantItem)) {
            return false;
        }
        ConstantItem other = (ConstantItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ConstantItem[ id=" + id + " ]";
    }
    
}
