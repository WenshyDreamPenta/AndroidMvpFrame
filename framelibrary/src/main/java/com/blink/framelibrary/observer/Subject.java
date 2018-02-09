package com.blink.framelibrary.observer;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/2/9
 *     desc   : Observer Subject
 * </pre>
 */
public interface Subject {
    void registerObserver();
    void removeObserver();
    void notifyObserver();
}
