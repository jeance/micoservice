package com.te.micoservice.common.other;

/**
 * Created by cxj4842 on 2018/4/13.
 */
public interface  IPublisher<M> {
    public void addObservers(String key, IObserver observers);
    public void deleteObservers(String key, IObserver observers);
    public void notifyAllObservers(M message);

}
