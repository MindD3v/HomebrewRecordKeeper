package com.homebrewrecordkeeper.entity;

import javax.persistence.*;

@Entity
@Table(name="maltrecord")
public class MaltRecordEntity {

    @Id
    @SequenceGenerator(name = "ID_seq", sequenceName = "maltrecord_id_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ID_seq")
    @Column(name = "id", unique = true, insertable = false, updatable = false)
    private int Id;
    @Column(name="name", nullable = false)
    private String Name;
    @Column(name="amount", nullable = false)
    private double Amount;
    @Column(name="unit", nullable = false)
    private String Unit;
    @Column(name="type", nullable = false)
    private String Type;

    public MaltRecordEntity(String name, double amount, String unit, String type)
    {
        setName(name);
        setAmount(amount);
        setUnit(unit);
        setType(type);
    }

    public MaltRecordEntity() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
