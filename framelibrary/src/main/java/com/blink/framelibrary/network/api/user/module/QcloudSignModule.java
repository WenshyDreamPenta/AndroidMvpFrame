package com.blink.framelibrary.network.api.user.module;

/**
 * <pre>
 *     author : wangmingxing
 *     time   : 2018/1/17
 *     desc   : 腾讯云签名
 * </pre>
 */
public class QcloudSignModule {
    private String buctetName;
    private String authorization;

    public String getBuctetName() {
        return buctetName;
    }

    public void setBuctetName(String buctetName) {
        this.buctetName = buctetName;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}
