package com.te.micoservice.common.other;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by cxj4842 on 2018/5/1.
 */
public class ProxyFactory implements InvocationHandler {

    private Object target;

    private Method method;

   public ProxyFactory() {
        super();
    }

  public  ProxyFactory(Object target,String method)   throws NoSuchMethodException, SecurityException {
        super();
        this.target = target;
        this.method=target.getClass().getMethod(method);
    }
    public Object invoke() throws Throwable {
       return invoke(target, method,null);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(proxy, args);
        return result;
    }
}
