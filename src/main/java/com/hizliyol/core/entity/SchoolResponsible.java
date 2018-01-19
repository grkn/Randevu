package com.hizliyol.core.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
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
@Table(name = "school_responsible", catalog = "randevu", schema = "")
@EntityListeners(AuditingEntityListener.class)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SchoolResponsible.findAll", query = "SELECT s FROM SchoolResponsible s")
    , @NamedQuery(name = "SchoolResponsible.findById", query = "SELECT s FROM SchoolResponsible s WHERE s.id = :id")
    , @NamedQuery(name = "SchoolResponsible.findByName", query = "SELECT s FROM SchoolResponsible s WHERE s.name = :name")
    , @NamedQuery(name = "SchoolResponsible.findByCreatedUser", query = "SELECT s FROM SchoolResponsible s WHERE s.createdUser = :createdUser")
    , @NamedQuery(name = "SchoolResponsible.findByCreatedDate", query = "SELECT s FROM SchoolResponsible s WHERE s.createdDate = :createdDate")
    , @NamedQuery(name = "SchoolResponsible.findByUpdatedUser", query = "SELECT s FROM SchoolResponsible s WHERE s.updatedUser = :updatedUser")
    , @NamedQuery(name = "SchoolResponsible.findByUpdatedDate", query = "SELECT s FROM SchoolResponsible s WHERE s.updatedDate = :updatedDate")
    , @NamedQuery(name = "SchoolResponsible.findByTitle", query = "SELECT s FROM SchoolResponsible s WHERE s.title = :title")
    , @NamedQuery(name = "SchoolResponsible.findByLastName", query = "SELECT s FROM SchoolResponsible s WHERE s.lastName = :lastName")})
public class SchoolResponsible implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @CreatedBy
    @Column(name = "created_user")
    private String createdUser;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdDate;
    @LastModifiedBy
    @Column(name = "updated_user")
    private String updatedUser;
    @LastModifiedDate
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Column(name = "title")
    private String title;
    @Column(name = "last_name")
    private String lastName;
    @JoinColumn(name = "school_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private School schoolId;

    public SchoolResponsible() {
    }

    public SchoolResponsible(Integer id) {
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

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(String updatedUser) {
        this.updatedUser = updatedUser;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public School getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(School schoolId) {
        this.schoolId = schoolId;
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
        if (!(object instanceof SchoolResponsible)) {
            return false;
        }
        SchoolResponsible other = (SchoolResponsible) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SchoolResponsible[ id=" + id + " ]";
    }
    
}
