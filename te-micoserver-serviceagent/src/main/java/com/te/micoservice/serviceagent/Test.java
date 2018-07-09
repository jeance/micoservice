package com.te.micoservice.serviceagent;


//@MicoService("Test")
/**
 * Created by cxj4842 on 2018/6/28.
 */
public  class Test   {
    @MicoServiceAction("print")
    public void print() {
        System.out.println("scanClass1");
    }

   // @PostConstruct
    private void init() {
        System.out.println("@PostConstruct将在依赖注入完成后被自动调用: b = " );
    }

}
