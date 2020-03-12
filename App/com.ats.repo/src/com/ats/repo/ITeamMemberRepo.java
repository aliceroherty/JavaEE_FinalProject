/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ats.repo;

import com.ats.models.ITeamMember;

/**
 *
 * @author soake
 */
public interface ITeamMemberRepo {
    int insertTeamMember(ITeamMember team);
}
