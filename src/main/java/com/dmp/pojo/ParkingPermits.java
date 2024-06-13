/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.pojo;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Phuc
 */
@Entity
@Table(name = "parking_permits")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParkingPermits.findAll", query = "SELECT p FROM ParkingPermits p"),
    @NamedQuery(name = "ParkingPermits.findById", query = "SELECT p FROM ParkingPermits p WHERE p.id = :id"),
    @NamedQuery(name = "ParkingPermits.findByLicensePlate", query = "SELECT p FROM ParkingPermits p WHERE p.licensePlate = :licensePlate"),
    @NamedQuery(name = "ParkingPermits.findByPermitStartDate", query = "SELECT p FROM ParkingPermits p WHERE p.permitStartDate = :permitStartDate"),
    @NamedQuery(name = "ParkingPermits.findByPermitEndDate", query = "SELECT p FROM ParkingPermits p WHERE p.permitEndDate = :permitEndDate")})
public class ParkingPermits implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "license_plate")
    private String licensePlate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "permit_start_date")
    @Temporal(TemporalType.DATE)
    private Date permitStartDate;
    @Column(name = "permit_end_date")
    @Temporal(TemporalType.DATE)
    private Date permitEndDate;
    @JoinColumn(name = "family_member_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private FamilyMember familyMemberId;

    public ParkingPermits() {
    }

    public ParkingPermits(Integer id) {
        this.id = id;
    }

    public ParkingPermits(Integer id, String licensePlate, Date permitStartDate) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.permitStartDate = permitStartDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Date getPermitStartDate() {
        return permitStartDate;
    }

    public void setPermitStartDate(Date permitStartDate) {
        this.permitStartDate = permitStartDate;
    }

    public Date getPermitEndDate() {
        return permitEndDate;
    }

    public void setPermitEndDate(Date permitEndDate) {
        this.permitEndDate = permitEndDate;
    }

    public FamilyMember getFamilyMemberId() {
        return familyMemberId;
    }

    public void setFamilyMemberId(FamilyMember familyMemberId) {
        this.familyMemberId = familyMemberId;
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
        if (!(object instanceof ParkingPermits)) {
            return false;
        }
        ParkingPermits other = (ParkingPermits) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dmp.pojo.ParkingPermits[ id=" + id + " ]";
    }
    
}
