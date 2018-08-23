package com.te.micoservice.serviceagent;

import com.te.micoservice.common.utils.HttpUtils;
import com.te.micoservice.common.utils.IpHelper;
import com.te.micoservice.common.utils.JsonUtils;
import com.te.micoservice.model.Micoserviceregistry;
import com.te.micoservice.serviceagent.jobcore.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cxj4842 on 2018/7/6.
 */
@Component
public final class MicoSerInit implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private JobService jobService;
    public static Map<String, Map<String, Object>> serviceName = new HashMap<String, Map<String, Object>>();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {//root application context 没有parent，他就是老大.
            //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
            try {
               registerBean(MicoService.class.getName());//注册bean
            //    getMicoSerInfo();//获取服务方服务
            //    rgisterMicoSer();//注册服务
            //    jobService.LoadJobs();//初始化job，发送心跳
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //@PostConstruct
    public void getMicoSerInfo() throws Exception {
        Map<String, Object> beanClassNames = context.getBeansWithAnnotation(MicoService.class);
        for (Map.Entry<String, Object> entry : beanClassNames.entrySet()) {
            Class<?> bean = entry.getValue().getClass();
            RequestMapping annotation = bean.getAnnotation(RequestMapping.class);
            if (annotation != null) {
                String serName = annotation.value()[0];
                Method[] methods = bean.getDeclaredMethods();
                Map<String, Object> methodMappingNames = new HashMap<>();
                for (Method method : methods) {
                    RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                    if (methodAnnotation == null) {
                        continue;
                    } else {
                        String serMethod = methodAnnotation.value()[0];
                        Micoserviceregistry micoserviceregistry = getMicserModel(serName, serMethod);
                        methodMappingNames.put(methodAnnotation.value()[0], micoserviceregistry);
                    }
                }
                serviceName.put(annotation.value()[0], methodMappingNames);
            } else {
                System.out.println("Error,Class need a requestmapping!");
            }
        }
    }

    public void  rgisterMicoSer()
    {
        String json= getModelJson();
        sendSerInfo(json);
    }

    private Micoserviceregistry getMicserModel(String serName, String serMethod) throws Exception {
        Micoserviceregistry micoserviceregistry = new Micoserviceregistry();
        micoserviceregistry.setMsrservicename(serName);
        micoserviceregistry.setMsrservicemethod(serMethod);
        micoserviceregistry.setMsrserviceversion("1.0.0");
        micoserviceregistry.setCreatedtime(new Date());
        micoserviceregistry.setMsrregistrytime(new Date());
        micoserviceregistry.setMsrserviceinfo("");
        micoserviceregistry.setMsrserviceip(IpHelper.getLocalHostLANAddress().getHostAddress());
        micoserviceregistry.setRemark("测试");
        micoserviceregistry.setUpdatedtime(new Date());
        return micoserviceregistry;
    }

    private void registerBean(String beanName) {
        MicoSerBeanUtil.init(context);
        MicoSerBeanUtil.addBean(beanName);
    }

    private void printService() {
        for (String key : serviceName.keySet()) {
            System.out.println("服务名：" + key);
            for (Map.Entry<String, Object> temp : serviceName.get(key).entrySet()) {
                System.out.println("服务方法名：" + temp.getKey());
            }
        }
    }

    private void sendSerInfo(String json) {

        HttpUtils httpUtils=  new HttpUtils("localhost://MicoSer/rigistry");
        httpUtils.setMethod("post");
        httpUtils.setContentType("Content-Type:application/json");
        httpUtils.setSentData(json);
        httpUtils.sendRequest();
    }

    public static String getModelJson() {
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
}
