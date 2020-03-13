/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ats.models;

import java.util.Date;
import java.util.List;

/**
 * Team Class
 *
 * @author Sam Oakes
 * @date 03-04-2020
 */
public class Team extends Base implements ITeam {

    public Team(String name, boolean isOnCall, Date createdAt, Date updatedAt, boolean isDeleted, Date deletedAt, List<IEmployee> employees ) {
        this.name = name;
        this.isOnCall = isOnCall;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
        this.deletedAt = deletedAt;
        this.employees = employees;
        
    }

    private int id;
    private String name;
    private boolean isOnCall;
    private Date createdAt;
    private Date updatedAt;
    private boolean isDeleted;
    private Date deletedAt;
    private List<IEmployee> employees;

    public Team() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsOnCall() {
        return isOnCall;
    }

    public void setIsOnCall(boolean isOnCall) {
        this.isOnCall = isOnCall;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public List<IEmployee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<IEmployee> employee) {
        this.employees = employee;
    }
}
