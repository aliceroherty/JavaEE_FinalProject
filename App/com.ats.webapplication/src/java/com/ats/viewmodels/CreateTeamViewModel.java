/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ats.viewmodels;

import com.ats.models.IEmployee;
import com.ats.models.ITeam;
import java.util.List;

/**
 *
 * @author soake
 */
public class CreateTeamViewModel {

    private ITeam team;
    private List<IEmployee> employee;
    
    public ITeam getTeam() {
        return team;
    }

    public void setTeam(ITeam team) {
        this.team = team;
    }

    public List<IEmployee> getEmployees() {
        return employee;
    }

    public void setEmployee(List<IEmployee> employee) {
        this.employee = employee;
    }
    
    
}
