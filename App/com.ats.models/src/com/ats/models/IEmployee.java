package com.ats.models;

import java.util.Date;
import java.util.List;

/**
 * Employee Interface
 * @author Alice Roherty-Carrier
 * @date 03-03-2020
 */
public interface IEmployee extends IBase {
    public int getId();

    public void setId(int id);

    public String getFirstName();

    public void setFirstName(String firstName);

    public String getLastName();

    public void setLastName(String lastName);

    public int getSIN();

    public void setSIN(int sin);

    public double getHourlyRate();

    public void setHourlyRate(double hourlyRate);

    public boolean isDeleted();

    public void setDeleted(boolean isDeleted);

    public Date getCreatedAt();

    public void setCreatedAt(Date createdAt);

    public Date getUpdatedAt();

    public void setUpdatedAt(Date updatedAt);

    public Date getDeletedAt();

    public void setDeletedAt(Date deletedAt);
    
    public List<ITask> getSkills();
    
    public void setSkills(List<ITask> skills);
}
