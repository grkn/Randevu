package com.hizliyol.core.entity;

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

/**
 *
 * @author grkn
 */
@Entity
@Table(name = "user_definition_time", catalog = "randevu", schema = "")
@NamedQueries({
    @NamedQuery(name = "UserDefinitionTime.findAll", query = "SELECT u FROM UserDefinitionTime u")
    , @NamedQuery(name = "UserDefinitionTime.findById", query = "SELECT u FROM UserDefinitionTime u WHERE u.id = :id")
    , @NamedQuery(name = "UserDefinitionTime.findByStartDate", query = "SELECT u FROM UserDefinitionTime u WHERE u.startDate = :startDate")
    , @NamedQuery(name = "UserDefinitionTime.findByEndDate", query = "SELECT u FROM UserDefinitionTime u WHERE u.endDate = :endDate")})
public class UserDefinitionTime implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @JoinColumn(name = "user_definition_id", referencedColumnName = "id")
    @ManyToOne
    private UserDefinition userDefinitionId;

    public UserDefinitionTime() {
    }

    public UserDefinitionTime(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public UserDefinition getUserDefinitionId() {
        return userDefinitionId;
    }

    public void setUserDefinitionId(UserDefinition userDefinitionId) {
        this.userDefinitionId = userDefinitionId;
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
        if (!(object instanceof UserDefinitionTime)) {
            return false;
        }
        UserDefinitionTime other = (UserDefinitionTime) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UserDefinitionTime[ id=" + id + " ]";
    }
    
}
