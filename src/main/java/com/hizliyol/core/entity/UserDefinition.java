package com.hizliyol.core.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author grkn
 */
@Entity
@Table(name = "user_definition", catalog = "randevu", schema = "")
@NamedQueries({
    @NamedQuery(name = "UserDefinition.findAll", query = "SELECT u FROM UserDefinition u")
    , @NamedQuery(name = "UserDefinition.findById", query = "SELECT u FROM UserDefinition u WHERE u.id = :id")
    , @NamedQuery(name = "UserDefinition.findByName", query = "SELECT u FROM UserDefinition u WHERE u.name = :name")
    , @NamedQuery(name = "UserDefinition.findByResponsibility", query = "SELECT u FROM UserDefinition u WHERE u.responsibility = :responsibility")
    , @NamedQuery(name = "UserDefinition.findByUserName", query = "SELECT u FROM UserDefinition u WHERE u.userName = :userName")
    , @NamedQuery(name = "UserDefinition.findByTimeInterval", query = "SELECT u FROM UserDefinition u WHERE u.timeInterval = :timeInterval")})
public class UserDefinition implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "responsibility")
    private String responsibility;
    @Column(name = "userName")
    private String userName;
    @Column(name = "time_interval")
    private Integer timeInterval;
    @OneToMany(mappedBy = "userDefinitionId")
    private Collection<UserDefinitionTime> userDefinitionTimeCollection;
    @Column(name = "free_day")
    private Date freeDay;
    @Column(name = "free_day_two")
    private Date freeDayTwo;
    
    public UserDefinition() {
    }

    public UserDefinition(Integer id) {
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

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(Integer timeInterval) {
        this.timeInterval = timeInterval;
    }

    public Collection<UserDefinitionTime> getUserDefinitionTimeCollection() {
        return userDefinitionTimeCollection;
    }

    public void setUserDefinitionTimeCollection(Collection<UserDefinitionTime> userDefinitionTimeCollection) {
        this.userDefinitionTimeCollection = userDefinitionTimeCollection;
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
        if (!(object instanceof UserDefinition)) {
            return false;
        }
        UserDefinition other = (UserDefinition) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UserDefinition[ id=" + id + " ]";
    }

	public Date getFreeDay() {
		return freeDay;
	}

	public void setFreeDay(Date freeDay) {
		this.freeDay = freeDay;
	}

	public Date getFreeDayTwo() {
		return freeDayTwo;
	}

	public void setFreeDayTwo(Date freeDayTwo) {
		this.freeDayTwo = freeDayTwo;
	}
    
}
