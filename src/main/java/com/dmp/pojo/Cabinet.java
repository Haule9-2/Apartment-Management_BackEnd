/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.pojo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "cabinet")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Cabinet.findAll", query = "SELECT c FROM Cabinet c"),
        @NamedQuery(name = "Cabinet.findById", query = "SELECT c FROM Cabinet c WHERE c.id = :id"),
        @NamedQuery(name = "Cabinet.findByStatus", query = "SELECT c FROM Cabinet c WHERE c.status = :status"),
        @NamedQuery(name = "Cabinet.findByIsActive", query = "SELECT c FROM Cabinet c WHERE c.isActive = :isActive")})
public class Cabinet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_active")
    private short isActive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cabinetId")
    private Set<Item> itemSet;
    @JoinColumn(name = "contract_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private RentalContract contractId;

    public Cabinet() {
    }

    public Cabinet(Integer id) {
        this.id = id;
    }

    public Cabinet(Integer id, String status, short isActive) {
        this.id = id;
        this.status = status;
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public short getIsActive() {
        return isActive;
    }

    public void setIsActive(short isActive) {
        this.isActive = isActive;
    }

    @XmlTransient
    public Set<Item> getItemSet() {
        return itemSet;
    }

    public void setItemSet(Set<Item> itemSet) {
        this.itemSet = itemSet;
    }

    public RentalContract getContractId() {
        return contractId;
    }

    public void setContractId(RentalContract contractId) {
        this.contractId = contractId;
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
        if (!(object instanceof Cabinet)) {
            return false;
        }
        Cabinet other = (Cabinet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dmp.pojo.Cabinet[ id=" + id + " ]";
    }

}
