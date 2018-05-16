package com.hizliyol.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Table(name = "school", catalog = "randevu", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "School.findAll", query = "SELECT s FROM School s")
    , @NamedQuery(name = "School.findById", query = "SELECT s FROM School s WHERE s.id = :id")
    , @NamedQuery(name = "School.findByName", query = "SELECT s FROM School s WHERE s.name = :name")
    , @NamedQuery(name = "School.findByYear", query = "SELECT s FROM School s WHERE s.year = :year")
    , @NamedQuery(name = "School.findByCreatedUser", query = "SELECT s FROM School s WHERE s.createdUser = :createdUser")
    , @NamedQuery(name = "School.findByUpdatedUser", query = "SELECT s FROM School s WHERE s.updatedUser = :updatedUser")})
public class School implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "year")
    private Integer year;
    @Column(name = "created_user")
    @CreatedBy
    private String createdUser;
    @Column(name = "created_date")
    @CreatedDate
    private Date createdDate;
    @Column(name = "updated_user")
    @LastModifiedBy
    private String updatedUser;
    @Column(name = "updated_date")
    @LastModifiedDate
    private Date updatedDate;
    @Column(name = "area")
    private Integer area;
    @Column(name = "students")
    private Integer students;
    @OneToMany(mappedBy = "schoolId", fetch = FetchType.EAGER)
    private Set<SchoolResponsible> schoolResponsibleSet;
    
    @OneToMany(mappedBy = "schoolId", fetch = FetchType.LAZY)
    private Set<EnergyConsumption> energyConsumptionSet;
    
    
    public School() {
    }

    public School(Integer id) {
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public String getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(String updatedUser) {
        this.updatedUser = updatedUser;
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
        if (!(object instanceof School)) {
            return false;
        }
        School other = (School) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.School[ id=" + id + " ]";
    }

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Set<SchoolResponsible> getSchoolResponsibleSet() {
		return schoolResponsibleSet;
	}

	public void setSchoolResponsibleSet(Set<SchoolResponsible> schoolResponsibleSet) {
		this.schoolResponsibleSet = schoolResponsibleSet;
	}

	public Set<EnergyConsumption> getEnergyConsumptionSet() {
		return energyConsumptionSet;
	}

	public void setEnergyConsumptionSet(Set<EnergyConsumption> energyConsumptionSet) {
		this.energyConsumptionSet = energyConsumptionSet;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public Integer getStudents() {
		return students;
	}

	public void setStudents(Integer students) {
		this.students = students;
	}
    
}