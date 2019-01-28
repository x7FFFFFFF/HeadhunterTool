package ru.alex.vic.vk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties(VkConfig.class)
public class Client {

    @Autowired
    private VkConfig vkConfig;

    public void execute() {



    }
}
