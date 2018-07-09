package com.te.micoservice.service;

import com.te.micoservice.dao.MicoserviceregistryMapper;
import com.te.micoservice.model.Micoserviceregistry;
import com.te.micoservice.model.SearchModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cxj4842 on 2018/7/9.
 */

@Service
public class MicoSerRegistryImpl implements  IMicoSerRigistryService {

//    @Autowired
  private  MicoserviceregistryMapper micoserviceregistryMapper;


    @Override
    public SearchModel<Micoserviceregistry> getMicoSerList(SearchModel<Micoserviceregistry> searchModel) {
        return null;
    }

    @Override
    public boolean addMicoSer(Micoserviceregistry model) {

        return micoserviceregistryMapper.insert(model)>0;
    }

    @Override
    public boolean updateMicoSerById(Micoserviceregistry model) {
        return false;
    }

    @Override
    public Micoserviceregistry getEntityById(long id) {
        return null;
    }
}
