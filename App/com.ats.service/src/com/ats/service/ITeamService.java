/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ats.service;

import com.ats.models.ITeam;
import java.util.List;

/**
 *
 * @author soake
 */
public interface ITeamService {
    int insertTeam(ITeam employee);
    int updateTeam(ITeam employee);
    int deleteTeam(int id);
    boolean isValid(ITeam team);
}
