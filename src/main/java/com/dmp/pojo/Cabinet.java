package com.dmp.pojo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cabinet")
public class Cabinet {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 45)
    @Column(name = "cabinetcol", length = 45)
    private String cabinetcol;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contract_id", nullable = false)
    private RentalContract contract;

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

    public RentalContract getContract() {
        return contract;
    }

    public void setContract(RentalContract contract) {
        this.contract = contract;
    }

}