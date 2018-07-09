package com.te.micoservice.dao;

import com.te.micoservice.model.Micoserviceregistry;
import org.springframework.stereotype.Repository;


public interface MicoserviceregistryMapper {
    int deleteByPrimaryKey(Integer msrid);

    int insert(Micoserviceregistry record);

    int insertSelective(Micoserviceregistry record);

    Micoserviceregistry selectByPrimaryKey(Integer msrid);

    int updateByPrimaryKeySelective(Micoserviceregistry record);

    int updateByPrimaryKey(Micoserviceregistry record);
}