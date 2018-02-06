package com.blink.framelibrary.eventbus;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/2/5
 *     desc   : 事件总线类
 * </pre>
 */
public class EventMap {
    //事件总线基类
    public static class BaseEvent {
        public String code;        //错误码
        public String message;     //错误信息
    }

    //错误异常事件
    public static class HExceptionEvent extends BaseEvent {

        public HExceptionEvent(int code, String message) {
            this.code = String.valueOf(code);
            this.message = message;
        }
    }
}
