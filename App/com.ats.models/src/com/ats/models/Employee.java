package com.ats.models;

import java.util.Date;
import java.util.List;

/**
 * Employee Class
 * @author Alice Roherty-Carrier
 * @date 03-03-2020
 */
public class Employee extends Base implements IEmployee {
    private int id;
    private String firstName;
    private String lastName;
    private int sin;
    private double hourlyRate;
    private boolean isDeleted;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
    private List<ITask> skills;
    
    public Employee() {}
    
    public Employee(String firstName, String lastName, int sin, double hourlyRate, Date createdAt, boolean isDeleted) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sin = sin;
        this.hourlyRate = hourlyRate;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public int getSIN() {
        return this.sin;
    }

    @Override
    public void setSIN(int sin) {
        this.sin = sin;
    }

    @Override
    public double getHourlyRate() {
        return this.hourlyRate;
    }

    @Override
    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public boolean isDeleted() {
        return this.isDeleted;
    }

    @Override
    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public Date getCreatedAt() {
        return this.createdAt;
    }

    @Override
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    @Override
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public Date getDeletedAt() {
        return this.deletedAt;
    }

    @Override
    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public List<ITask> getSkills() {
        return this.skills;
    }

    @Override
    public void setSkills(List<ITask> skills) {
        this.skills = skills;
    }
}
