package com.te.micoservice.controller;


//import com.te.micoservice.serviceagent.MicoSerFactoryBean;

import com.te.micoservice.common.definition.PublicConsts;
import com.te.micoservice.common.utils.JsonUtils;
import com.te.micoservice.service.test.Red_BlackTreeTest;
import com.te.micoservice.serviceagent.MicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.awt.image.InputStreamImageSource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

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
    @ResponseBody
    public String printTest() {
      //  String result = "no data";
      //  String result2 = "no data";
        String reqInfo="no data";
        try (ServerSocket serverSocket = new ServerSocket(8091)) {

            Socket socket = serverSocket.accept();
            InputStream inputStream=socket.getInputStream();
            byte []bytes=new byte[1000];
            int len;
            StringBuilder sb = new StringBuilder();
           while ((len=inputStream.read(bytes))!=-1)
           {
               sb.append(new String(bytes, 0, len,"UTF-8"));
           }
           reqInfo= sb.toString();
           socket.close();
           inputStream.close();
         //   result = socket.getPort() + "";
         //   result2 = socket.getLocalPort() + "";

        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        return reqInfo;
        // Red_BlackTreeTest.main(null);
//        String[] beanNames=context.getBeanDefinitionNames();
//        for(String name : beanNames)
//        {
//            System.out.println(name);
//        }

    }

    @RequestMapping("/printTest2")
    @ResponseBody
    public String printTest2() {

//        try (Socket socket = new Socket("127.0.0.1", 8092)) {
//            OutputStream os=socket.getOutputStream();
//            String message="hello";
//            os.write(message.getBytes());
//            os.flush();
//            os.close();
//        } catch (Exception ex) {
//            System.out.print(ex.getMessage());
//        }
        return "test";
    }


    public static String getServiceJson(Map<String, Map<String, Object>> serviceName) {
        String result = JsonUtils.modelToJson(serviceName);
        return result;
    }

    public static String getModelJson(Map<String, Map<String, Object>> serviceName) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (String key : serviceName.keySet()) {
            StringBuilder sbContent = new StringBuilder();
            for (Map.Entry<String, Object> temp : serviceName.get(key).entrySet()) {
                String tempJson = JsonUtils.modelToJson(temp.getValue());
                sbContent.append(tempJson);
                sbContent.append(',');
            }
            if (sbContent.length() > 0) {
                sbContent.delete(sbContent.length() - 1, sbContent.length());
            }
            sb.append(sbContent);
        }
        sb.append("]");
        return sb.toString();
    }

    private void printService(Map<String, Map<String, Object>> serviceName) {
        for (String key : serviceName.keySet()) {
            System.out.println("服务名：" + key);
            for (Map.Entry<String, Object> temp : serviceName.get(key).entrySet()) {
                System.out.println("服务方法名：" + temp.getKey());
            }
        }
    }


    public static String createModel(String serviceName, String serviceMethod, String serviceVersion) {

        StringBuilder defaultJobStr = new StringBuilder();
        // defaultJobStr.append("[");
        defaultJobStr.append("{");

        defaultJobStr.append("\"msrservicename\":\"".concat(serviceName).concat("\","));
        defaultJobStr.append("\"msrservicemethod\":\"".concat(serviceMethod).concat("\","));
        defaultJobStr.append("\"msrserviceversion\":\"".concat(serviceVersion).concat("\","));
        defaultJobStr.append("\"msrserviceip\":\"".concat(PublicConsts.JobConfig.JobDefaultJobTriger).concat("\","));
        defaultJobStr.append("\"msrserviceinfo\":\"".concat(PublicConsts.JobConfig.JobDefaultNickName).concat("\","));
        defaultJobStr.append("\"remark\":\"".concat("test").concat("\","));
        defaultJobStr.append("\"msrregistrytime\":\"2017-08-23 20:58:56.797\",");
        defaultJobStr.append("\"createdtime\":\"2017-08-23 20:58:56.797\",");
        defaultJobStr.append("\"updatedtime\":\"2017-08-23 20:58:56.797\",");
        defaultJobStr.append("}");
        //   defaultJobStr.append("]");
        return defaultJobStr.toString();

    }
}
