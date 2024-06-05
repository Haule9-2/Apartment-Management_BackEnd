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
    @NamedQuery(name = "Cabinet.findByCabinetcol", query = "SELECT c FROM Cabinet c WHERE c.cabinetcol = :cabinetcol")})
public class Cabinet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "cabinetcol")
    private String cabinetcol;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCabinetcol() {
        return cabinetcol;
    }

    public void setCabinetcol(String cabinetcol) {
        this.cabinetcol = cabinetcol;
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
