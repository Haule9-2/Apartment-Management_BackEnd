/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.pojo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Phuc
 */
@Entity
@Table(name = "rental_contract")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RentalContract.findAll", query = "SELECT r FROM RentalContract r"),
    @NamedQuery(name = "RentalContract.findById", query = "SELECT r FROM RentalContract r WHERE r.id = :id"),
    @NamedQuery(name = "RentalContract.findByRentalFee", query = "SELECT r FROM RentalContract r WHERE r.rentalFee = :rentalFee"),
    @NamedQuery(name = "RentalContract.findByDeposit", query = "SELECT r FROM RentalContract r WHERE r.deposit = :deposit"),
    @NamedQuery(name = "RentalContract.findByCreatedDate", query = "SELECT r FROM RentalContract r WHERE r.createdDate = :createdDate"),
    @NamedQuery(name = "RentalContract.findByStartedDate", query = "SELECT r FROM RentalContract r WHERE r.startedDate = :startedDate")})
public class RentalContract implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rental_fee")
    private int rentalFee;
    @Basic(optional = false)
    @NotNull
    @Column(name = "deposit")
    private long deposit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "started_date")
    @Temporal(TemporalType.DATE)
    private Date startedDate;
    @JoinColumn(name = "resident_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Resident residentId;
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Room roomId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contractId")
    private Set<Roommate> roommateSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contractId")
    private Set<Receipt> receiptSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contractId")
    private Set<Cabinet> cabinetSet;

    public RentalContract() {
    }

    public RentalContract(Integer id) {
        this.id = id;
    }

    public RentalContract(Integer id, int rentalFee, long deposit, Date createdDate, Date startedDate) {
        this.id = id;
        this.rentalFee = rentalFee;
        this.deposit = deposit;
        this.createdDate = createdDate;
        this.startedDate = startedDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRentalFee() {
        return rentalFee;
    }

    public void setRentalFee(int rentalFee) {
        this.rentalFee = rentalFee;
    }

    public long getDeposit() {
        return deposit;
    }

    public void setDeposit(long deposit) {
        this.deposit = deposit;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(Date startedDate) {
        this.startedDate = startedDate;
    }

    public Resident getResidentId() {
        return residentId;
    }

    public void setResidentId(Resident residentId) {
        this.residentId = residentId;
    }

    public Room getRoomId() {
        return roomId;
    }

    public void setRoomId(Room roomId) {
        this.roomId = roomId;
    }

    @XmlTransient
    public Set<Roommate> getRoommateSet() {
        return roommateSet;
    }

    public void setRoommateSet(Set<Roommate> roommateSet) {
        this.roommateSet = roommateSet;
    }

    @XmlTransient
    public Set<Receipt> getReceiptSet() {
        return receiptSet;
    }

    public void setReceiptSet(Set<Receipt> receiptSet) {
        this.receiptSet = receiptSet;
    }

    @XmlTransient
    public Set<Cabinet> getCabinetSet() {
        return cabinetSet;
    }

    public void setCabinetSet(Set<Cabinet> cabinetSet) {
        this.cabinetSet = cabinetSet;
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
        if (!(object instanceof RentalContract)) {
            return false;
        }
        RentalContract other = (RentalContract) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dmp.pojo.RentalContract[ id=" + id + " ]";
    }
    
}
