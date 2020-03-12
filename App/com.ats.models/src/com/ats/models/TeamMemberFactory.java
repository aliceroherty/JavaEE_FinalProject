/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ats.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author soake
 */
public class TeamMemberFactory {

    public static ITeamMember createInstance() {
        return new TeamMember();
    }

    public static ITeamMember createInstance(int teamID, int empID) {
        return new TeamMember(teamID, empID);
    }

    public static List<ITeamMember> createListInstance() {
        return new ArrayList<>();
    }
}
