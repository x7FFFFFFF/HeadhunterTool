package ru.alex.vic.vk;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("vk")
public class VkConfig {

    private String id;
    private String client_secret;

    public VkConfig() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }
}
