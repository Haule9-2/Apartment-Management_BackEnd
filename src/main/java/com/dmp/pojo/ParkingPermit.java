package com.dmp.pojo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "parking_permits")
public class ParkingPermit {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "family_member_id", nullable = false)
    private FamilyMember familyMember;

    @Size(max = 10)
    @NotNull
    @Column(name = "license_plate", nullable = false, length = 10)
    private String licensePlate;

    @NotNull
    @Column(name = "permit_start_date", nullable = false)
    private LocalDate permitStartDate;

    @Column(name = "permit_end_date")
    private LocalDate permitEndDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FamilyMember getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(FamilyMember familyMember) {
        this.familyMember = familyMember;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public LocalDate getPermitStartDate() {
        return permitStartDate;
    }

    public void setPermitStartDate(LocalDate permitStartDate) {
        this.permitStartDate = permitStartDate;
    }

    public LocalDate getPermitEndDate() {
        return permitEndDate;
    }

    public void setPermitEndDate(LocalDate permitEndDate) {
        this.permitEndDate = permitEndDate;
    }

}