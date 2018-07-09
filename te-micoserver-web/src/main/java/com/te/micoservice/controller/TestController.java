package com.te.micoservice.controller;


//import com.te.micoservice.serviceagent.MicoSerFactoryBean;

import com.te.micoservice.serviceagent.MicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cxj4842 on 2018/6/29.
 */
@Controller
@MicoService("/Test")
@RequestMapping("TestController")
public class TestController {
    @Autowired
    private ApplicationContext context;

//    @Autowired
//     private Test  test;

    @RequestMapping("/printTest/test")
    public void printTest() {

//        String[] beanNames=context.getBeanDefinitionNames();
//        for(String name : beanNames)
//        {
//            System.out.println(name);
//        }

    }

    @RequestMapping("/printTest2")
    public void printTest2() {
    }


}
