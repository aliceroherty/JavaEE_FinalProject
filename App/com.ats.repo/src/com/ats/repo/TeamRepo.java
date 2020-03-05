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
import com.ats.models.ITeam;
import java.sql.Types;
import java.util.List;
/**
 * Team Repository Class
 *
 * @author Sam Oakes
 * @date 03-04-2020
 */
public class TeamRepo extends BaseRepo implements ITeamRepo {

    private IDAL db = DALFactory.createInstance();

    @Override 
    public int insertTeam(ITeam team) {
        List<IParameter> params = ParameterFactory.createListInstance();

        IParameter name = ParameterFactory.createInstance(team.getName(), IParameter.Direction.IN, Types.VARCHAR);
        params.add(name);

        IParameter onCall = ParameterFactory.createInstance(team.isIsOnCall(), IParameter.Direction.IN, Types.BOOLEAN);
        params.add(onCall);

        IParameter createdAt = ParameterFactory.createInstance(team.getCreatedAt(), IParameter.Direction.IN, Types.DATE);
        params.add(createdAt);

        IParameter updatedAt = ParameterFactory.createInstance(team.getUpdatedAt(), IParameter.Direction.IN, Types.DATE);
        params.add(updatedAt);

        IParameter isDeleted = ParameterFactory.createInstance(team.isIsDeleted(), IParameter.Direction.IN, Types.BOOLEAN);
        params.add(isDeleted);

        IParameter deletedAt = ParameterFactory.createInstance(team.getDeletedAt(), IParameter.Direction.IN, Types.DATE);
        params.add(deletedAt);

        return (int) db.executeNonQuery("Employee_Insert", params).get(0);
    }
    
    @Override
    public int updateTeam(ITeam team) {
        return 0;
    }

    @Override
    public int deleteTeam(int id) {
        return 0;
    }

}
