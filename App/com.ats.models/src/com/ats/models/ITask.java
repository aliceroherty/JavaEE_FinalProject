package com.ats.models;

/**
 * Task Interface
 * @author Alice Roherty-Carrier
 * @date 03-03-2020
 */
public interface ITask extends IBase {
    public int getId();

    public void setId(int id);

    public String getName();

    public void setName(String name);

    public String getDescription();

    public void setDescription(String description);

    public int getDuration();

    public void setDuration(int duration);

}
