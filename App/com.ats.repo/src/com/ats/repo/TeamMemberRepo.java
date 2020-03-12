/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ats.repo;

import com.ats.dataaccess.DALFactory;
import com.ats.dataaccess.IDAL;
import com.ats.dataaccess.IParameter;
import com.ats.dataaccess.ParameterFactory;
import com.ats.models.ITeamMember;
import java.util.List;

/**
 *
 * @author soake
 */
public class TeamMemberRepo extends BaseRepo implements ITeamMemberRepo {

    private IDAL db = DALFactory.createInstance();

    @Override
    public int insertTeamMember(ITeamMember member) {
        int id = 0;
        List<Object> returnParams;
        List<IParameter> params = ParameterFactory.createListInstance();
        
        returnParams = db.executeNonQuery("CALL TeamMember_Insert(?,?);", params);

        try {
            if (returnParams != null && returnParams.size() != 0) {
                id = Integer.parseInt(returnParams.get(0).toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return id;

    }
}
