package ru.alex.vic.vk;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "vk")
public class VkConfig {

    private int userId;
    private String accessToken;

    public VkConfig() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
