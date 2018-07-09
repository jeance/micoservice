package com.te.micoservice.controller;


import com.te.micoservice.model.Micoserviceregistry;
import com.te.micoservice.service.IMicoSerRigistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by cxj4842 on 2018/7/9.
 */
@Controller
@RequestMapping("MicoSer")
public class MicoServerInitController {

    @Autowired
    private IMicoSerRigistryService micoSerRegistry;
    @RequestMapping("rigistry")
    @ResponseBody
    public String rigistryMicoSer(HttpServletRequest request, Model model,@RequestBody Micoserviceregistry  micoserviceRegistry)
    {
        boolean result=micoSerRegistry.addMicoSer(micoserviceRegistry);
        String res=result==true?"success":"fail";
        return res;
    }
}

