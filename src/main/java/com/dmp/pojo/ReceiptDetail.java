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
@Table(name = "receipt_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReceiptDetail.findAll", query = "SELECT r FROM ReceiptDetail r"),
    @NamedQuery(name = "ReceiptDetail.findById", query = "SELECT r FROM ReceiptDetail r WHERE r.id = :id"),
    @NamedQuery(name = "ReceiptDetail.findByAmount", query = "SELECT r FROM ReceiptDetail r WHERE r.amount = :amount")})
public class ReceiptDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "amount")
    private Integer amount;
    @JoinColumn(name = "receipt_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Receipt receiptId;
    @JoinColumn(name = "services_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Services servicesId;

    public ReceiptDetail() {
    }

    public ReceiptDetail(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Receipt getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Receipt receiptId) {
        this.receiptId = receiptId;
    }

    public Services getServicesId() {
        return servicesId;
    }

    public void setServicesId(Services servicesId) {
        this.servicesId = servicesId;
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
        if (!(object instanceof ReceiptDetail)) {
            return false;
        }
        ReceiptDetail other = (ReceiptDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dmp.pojo.ReceiptDetail[ id=" + id + " ]";
    }
    
}
