package com.hizliyol.core.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author grkn
 */
@Entity
@Table(name = "user_management", schema = "")
@NamedQueries({
    @NamedQuery(name = "UserManagement.findAll", query = "SELECT r FROM UserManagement r")
    , @NamedQuery(name = "UserManagement.findById", query = "SELECT r FROM UserManagement r WHERE r.id = :id")
    , @NamedQuery(name = "UserManagement.findByUsername", query = "SELECT r FROM UserManagement r WHERE r.username = :username")
    , @NamedQuery(name = "UserManagement.findByPassword", query = "SELECT r FROM UserManagement r WHERE r.password = :password")
    , @NamedQuery(name = "UserManagement.findByFirstName", query = "SELECT r FROM UserManagement r WHERE r.firstName = :firstName")
    , @NamedQuery(name = "UserManagement.findByLastName", query = "SELECT r FROM UserManagement r WHERE r.lastName = :lastName")
    , @NamedQuery(name = "UserManagement.findByEmail", query = "SELECT r FROM UserManagement r WHERE r.email = :email")})
public class UserManagement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @ManyToMany(mappedBy = "userSet",fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.REMOVE})
    private Set<Role> roleSet = new HashSet<>();

    public UserManagement() {
    }
    
    public UserManagement(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
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
        if (!(object instanceof UserManagement)) {
            return false;
        }
        UserManagement other = (UserManagement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UserManagement[ id=" + id + " ]";
    }

}
