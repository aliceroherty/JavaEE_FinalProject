/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ats.models;

import java.util.Date;

/**
 * Team Interface
 * @author Sam Oakes
 * @date 03-04-2020
 */
public interface ITeam extends IBase {
    
     public int getId();

    public void setId(int id);

    public String getName();
    
    public void setName(String name);

    public boolean isIsOnCall();

    public void setIsOnCall(boolean isOnCall);

    public Date getCreatedAt();

    public void setCreatedAt(Date createdAt);

    public Date getUpdatedAt();

    public void setUpdatedAt(Date updatedAt);

    public boolean isIsDeleted();

    public void setIsDeleted(boolean isDeleted);

    public Date getDeletedAt();

    public void setDeletedAt(Date deletedAt);
}
