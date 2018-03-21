package com.hizliyol.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
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

/**
 *
 * @author grkn
 */
@Entity
@Table(name = "auhorization", schema = "")
@NamedQueries({
    @NamedQuery(name = "Auhorization.findAll", query = "SELECT a FROM Auhorization a")
    , @NamedQuery(name = "Auhorization.findById", query = "SELECT a FROM Auhorization a WHERE a.id = :id")
    , @NamedQuery(name = "Auhorization.findByAuthName", query = "SELECT a FROM Auhorization a WHERE a.authName = :authName")})
public class Auhorization implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "auth_name")
    private String authName;
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @ManyToOne
    @JsonIgnore
    private Role roleId;

    public Auhorization() {
    }

    public Auhorization(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (authName != null ? authName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Auhorization)) {
            return false;
        }
        Auhorization other = (Auhorization) object;
        if(other != null && other.authName != null && other.getAuthName().equals(this.authName)){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return authName;
    }
    
}
