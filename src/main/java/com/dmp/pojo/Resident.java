/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Phuc
 */
@Entity
@Table(name = "resident")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resident.findAll", query = "SELECT r FROM Resident r"),
    @NamedQuery(name = "Resident.findById", query = "SELECT r FROM Resident r WHERE r.id = :id"),
    @NamedQuery(name = "Resident.findByAvatar", query = "SELECT r FROM Resident r WHERE r.avatar = :avatar")})
public class Resident implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "avatar")
    private String avatar;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "residentId")
    private Set<RentalContract> rentalContractSet;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "residentId")
    private Set<Complaint> complaintSet;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "residentId")
    private Set<FamilyMember> familyMemberSet;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "residentId")
    private Set<Roommate> roommateSet;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user;

    public Resident() {
    }

    public Resident(Integer id) {
        this.id = id;
    }

    public Resident(Integer id, String avatar) {
        this.id = id;
        this.avatar = avatar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @XmlTransient
    public Set<RentalContract> getRentalContractSet() {
        return rentalContractSet;
    }

    public void setRentalContractSet(Set<RentalContract> rentalContractSet) {
        this.rentalContractSet = rentalContractSet;
    }

    @XmlTransient
    public Set<Complaint> getComplaintSet() {
        return complaintSet;
    }

    public void setComplaintSet(Set<Complaint> complaintSet) {
        this.complaintSet = complaintSet;
    }

    @XmlTransient
    public Set<FamilyMember> getFamilyMemberSet() {
        return familyMemberSet;
    }

    public void setFamilyMemberSet(Set<FamilyMember> familyMemberSet) {
        this.familyMemberSet = familyMemberSet;
    }

    @XmlTransient
    public Set<Roommate> getRoommateSet() {
        return roommateSet;
    }

    public void setRoommateSet(Set<Roommate> roommateSet) {
        this.roommateSet = roommateSet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        if (!(object instanceof Resident)) {
            return false;
        }
        Resident other = (Resident) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dmp.pojo.Resident[ id=" + id + " ]";
    }
    
}
