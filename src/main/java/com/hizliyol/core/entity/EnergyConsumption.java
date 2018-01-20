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
 * @author turkana
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "energy_consumption", catalog = "randevu", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnergyConsumption.findAll", query = "SELECT e FROM EnergyConsumption e"),
    @NamedQuery(name = "EnergyConsumption.findById", query = "SELECT e FROM EnergyConsumption e WHERE e.id = :id"),
    @NamedQuery(name = "EnergyConsumption.findByType", query = "SELECT e FROM EnergyConsumption e WHERE e.type = :type"),
    @NamedQuery(name = "EnergyConsumption.findByValue", query = "SELECT e FROM EnergyConsumption e WHERE e.value = :value"),
    @NamedQuery(name = "EnergyConsumption.findByCreatedUser", query = "SELECT e FROM EnergyConsumption e WHERE e.createdUser = :createdUser"),
    @NamedQuery(name = "EnergyConsumption.findByCreatedDate", query = "SELECT e FROM EnergyConsumption e WHERE e.createdDate = :createdDate"),
    @NamedQuery(name = "EnergyConsumption.findByUpdatedUser", query = "SELECT e FROM EnergyConsumption e WHERE e.updatedUser = :updatedUser"),
    @NamedQuery(name = "EnergyConsumption.findByUpdatedDate", query = "SELECT e FROM EnergyConsumption e WHERE e.updatedDate = :updatedDate"),
    @NamedQuery(name = "EnergyConsumption.findByDeleted", query = "SELECT e FROM EnergyConsumption e WHERE e.deleted = :deleted")})
public class EnergyConsumption implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "type")
    private String type;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "value")
    private Double value;
    @ManyToOne
    @JoinColumn(name = "school_id", referencedColumnName = "id")
    private School schoolId;
    @CreatedBy
    @Column(name = "createdUser")
    private String createdUser;
    @CreatedDate
    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @LastModifiedBy
    @Column(name = "updatedUser")
    private String updatedUser;
    @LastModifiedDate
    @Column(name = "updatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Column(name = "deleted")
    private Boolean deleted;
    @Column(name = "selectedDate")
    private Date selectedDate;
    
    public EnergyConsumption() {
    }

    public EnergyConsumption(Integer id) {
        this.id = id;
    }

    public EnergyConsumption(Integer id, School schoolId) {
        this.id = id;
        this.schoolId = schoolId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EnergyConsumption)) {
            return false;
        }
        EnergyConsumption other = (EnergyConsumption) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EnergyConsumption[ id=" + id + " ]";
    }

	public void setSchoolId(School schoolId) {
		this.schoolId = schoolId;
	}
    
	public School getSchoolId() {
		return schoolId;
	}

	public Date getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

}
