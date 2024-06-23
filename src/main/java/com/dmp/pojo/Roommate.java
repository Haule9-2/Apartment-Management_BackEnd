/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.pojo;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Phuc
 */
@Entity
@Table(name = "roommate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roommate.findAll", query = "SELECT r FROM Roommate r"),
    @NamedQuery(name = "Roommate.findById", query = "SELECT r FROM Roommate r WHERE r.id = :id")})
public class Roommate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "contract_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private RentalContract contractId;
    @JoinColumn(name = "resident_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Resident residentId;

    public Roommate() {
    }

    public Roommate(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RentalContract getContractId() {
        return contractId;
    }

    public void setContractId(RentalContract contractId) {
        this.contractId = contractId;
    }

    public Resident getResidentId() {
        return residentId;
    }

    public void setResidentId(Resident residentId) {
        this.residentId = residentId;
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
        if (!(object instanceof Roommate)) {
            return false;
        }
        Roommate other = (Roommate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dmp.pojo.Roommate[ id=" + id + " ]";
    }
    
}
