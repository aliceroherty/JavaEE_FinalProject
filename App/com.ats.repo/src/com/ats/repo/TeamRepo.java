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
 * @author Samuel Oakes
 * @date 03-04-2020
 */
public class TeamRepo extends BaseRepo implements ITeamRepo {

    private IDAL db = DALFactory.createInstance();

    /**
     * Creates a team.
     *
     * @param team The Team to be created.
     * @return The number of rows affected.
     */
    @Override
    public int insertTeam(ITeam team) {
        int id = 0;
        List<Object> returnParams;
        List<IParameter> params = ParameterFactory.createListInstance();

        IParameter idParam = ParameterFactory.createInstance(id, IParameter.Direction.OUT, Types.INTEGER);
        params.add(idParam);

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

        returnParams = db.executeNonQuery("CALL Team_Insert(?,?,?,?,?,?,?);", params);

        try {
            if (returnParams != null && returnParams.size() != 0) {
                id = Integer.parseInt(returnParams.get(0).toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return id;
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
