package com.te.micoservice.controller;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.te.micoservice.common.utils.JsonUtils;
import com.te.micoservice.model.Micoserviceregistry;
import com.te.micoservice.service.IMicoSerRigistryService;
import com.te.micoservice.serviceagent.MicoSerInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by cxj4842 on 2018/7/9.
 */
@Controller
@RequestMapping("MicoSer")
public class MicoServerInitController {

    @Autowired
    private IMicoSerRigistryService micoSerRegistry;

    @RequestMapping("/rigistry")
    @ResponseBody
    public String rigistryMicoSer(HttpServletRequest request, Model model ,@RequestBody Micoserviceregistry  micoserviceRegistry)
    {
//        String json = TestController.getModelJson(MicoSerInit.serviceName);
//        List<Micoserviceregistry> micoserviceRegistry = JsonUtils.jsonToModelList(json, Micoserviceregistry.class);
            boolean result = false;
      //  for (Micoserviceregistry item : micoserviceRegistry) {
            result = micoSerRegistry.addMicoSer(micoserviceRegistry);
      //  }
//        String res = result == true ? "success" : "fail";

        return "rigistry";
    }
}

