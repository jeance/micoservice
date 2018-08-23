package com.te.micoservice.dao;

import com.te.micoservice.model.Micoserviceregistry;

public interface MicoserviceregistryMapper {
    int deleteByPrimaryKey(Integer msrid);

    int insert(Micoserviceregistry record);

    int insertSelective(Micoserviceregistry record);

    Micoserviceregistry selectByPrimaryKey(Integer msrid);

    int updateByPrimaryKeySelective(Micoserviceregistry record);

    int updateByPrimaryKey(Micoserviceregistry record);
}