package com.te.micoservice.service;

import com.te.micoservice.model.Micoserviceregistry;
import com.te.micoservice.model.SearchModel;

import java.util.List;

/**
 * Created by cxj4842 on 2018/7/9.
 */
public interface IMicoSerRigistryService {

    SearchModel<Micoserviceregistry> getMicoSerList(SearchModel<Micoserviceregistry> searchModel);

    /**
     * 添加记录
     * @param model
     * @return
     */
    boolean addMicoSer(Micoserviceregistry model);
    /**
     * 更新记录
     * @param model
     * @return
     */
    boolean updateMicoSerById(Micoserviceregistry model);

    /**
     * 根据ID获取记录
     * @param id
     * @return
     */
    Micoserviceregistry getEntityById(long id);

    /**
     * 根据对应字段获取记录
     * @param record
     * @return
     */
    List<Micoserviceregistry> selectByPrimaryKey(Micoserviceregistry record);
}
