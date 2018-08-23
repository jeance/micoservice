package com.te.micoservice.serviceagent;


//@MicoService("Test")

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cxj4842 on 2018/6/28.
 */
@Controller
@RequestMapping("Test")
public  class Test   {
   @RequestMapping("print")
    public void print() {
    }

   // @PostConstruct
    private void init() {
        System.out.println("@PostConstruct将在依赖注入完成后被自动调用: b = " );
    }

}
