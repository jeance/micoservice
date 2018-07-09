package com.te.micoservice.serviceagent;

import com.te.micoservice.serviceagent.jobcore.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
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
    public static Map<String, Map<String, Object>> serviceName=new HashMap<String, Map<String, Object>>();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {//root application context 没有parent，他就是老大.
            //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
            try {
                registerBean(MicoService.class.getName());
                init();
                jobService.LoadJobs();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //@PostConstruct
    public void init() throws Exception {
        Map<String, Object> beanClassNames = context.getBeansWithAnnotation(MicoService.class);
        for (Map.Entry<String, Object> entry : beanClassNames.entrySet()) {
            Class<?> bean = entry.getValue().getClass();
            RequestMapping annotation = bean.getAnnotation(RequestMapping.class);
            if (annotation != null) {
                Method[] methods = bean.getDeclaredMethods();
                Map<String, Object> methodMappingNames=new HashMap<>();
                for (Method method : methods) {
                    RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                    if(methodAnnotation==null){
                        continue;
                    }
                    else{
                        methodMappingNames.put(methodAnnotation.value()[0],null);
                    }
                }
                serviceName.put(annotation.value()[0],methodMappingNames);
            } else {
                System.out.println("Error,Class need a requestmapping!");
            }
        }
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
    }
