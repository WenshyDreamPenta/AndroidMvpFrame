package com.blink.framelibrary.network.api.testapi.module;

import com.blink.framelibrary.network.base.BaseModule;

import java.util.List;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/2/8
 *     desc   :
 * </pre>
 */
public class TestModule extends BaseModule {

    private List<DataBean> data;

    public List<DataBean> getData() { return data;}

    public void setData(List<DataBean> data) { this.data = data;}

    public static class DataBean {

        private int id;
        private String name;
        private String url;
        private String title;

        public int getId() { return id;}

        public void setId(int id) { this.id = id;}

        public String getName() { return name;}

        public void setName(String name) { this.name = name;}

        public String getUrl() { return url;}

        public void setUrl(String url) { this.url = url;}

        public String getTitle() { return title;}

        public void setTitle(String title) { this.title = title;}
    }
}
