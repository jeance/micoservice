package com.te.micoservice.common.other;

/**
 * Created by cxj4842 on 2018/4/13.
 */
public interface IObserver<M> {
    public void updateMsg(M msg);
}
