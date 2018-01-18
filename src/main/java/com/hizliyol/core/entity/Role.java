package com.hizliyol.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 *
 * @author grkn
 */
@Entity
@Table(name = "role", catalog = "randevu", schema = "")
@NamedQueries({
    @NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")
    , @NamedQuery(name = "Role.findById", query = "SELECT r FROM Role r WHERE r.id = :id")
    , @NamedQuery(name = "Role.findByRoleName", query = "SELECT r FROM Role r WHERE r.roleName = :roleName")})
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "role_name")
    private String roleName;
    @JoinTable(name = "user_jt_role", joinColumns = {
        @JoinColumn(name = "role_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "id")})
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<RandevuUser> randevuUserSet = new HashSet<>();
    @OneToMany(mappedBy = "roleId", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,CascadeType.REMOVE} )
    private Set<Auhorization> auhorizationSet;

    public Role() {
    }

    public Role(Integer id) {
        this.id = id;
    }

    public Role(Integer id2, String roleName2) {
		this.id = id2;
		this.roleName = roleName2;
	}

	public void setId(Integer id) {
		this.id = id;
	}
    
    public Integer getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<RandevuUser> getRandevuUserSet() {
        return randevuUserSet;
    }

    public void setRandevuUserSet(Set<RandevuUser> randevuUserSet) {
        this.randevuUserSet = randevuUserSet;
    }

    public Set<Auhorization> getAuhorizationSet() {
        return auhorizationSet;
    }

    public void setAuhorizationSet(Set<Auhorization> auhorizationSet) {
        this.auhorizationSet = auhorizationSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleName != null ? roleName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if(other != null && other.getRoleName() != null && other.getRoleName().equals(this.roleName))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return new StringBuilder(roleName).toString();
    }
    
}
