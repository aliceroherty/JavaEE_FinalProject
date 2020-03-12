/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ats.models;

/**
 *
 * @author soake
 */
public class TeamMember extends Base implements ITeamMember {

    private int TeamID;
    private int EmployeeID;

    public TeamMember() {
    }

    public TeamMember(int TeamID, int EmployeeID) {
        this.TeamID = TeamID;
        this.EmployeeID = EmployeeID;
    }

    public int getTeamID() {
        return TeamID;
    }

    public void setTeamID(int TeamID) {
        this.TeamID = TeamID;
    }

    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int EmployeeID) {
        this.EmployeeID = EmployeeID;
    }
}
