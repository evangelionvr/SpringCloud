package com.liaofan.rest;

import com.liaofan.config.MyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // 注入对象
    @Autowired
    private Environment env;

    @GetMapping("/hello")
    public String hello() {
        String port = env.getProperty("server.port");
        return port;
    }

    /**
     * 注入值
     */
    @Value("${server.port}")
    private String injectionPort;
    @GetMapping("/injectionValue")
    public String injectionValue() {
        return "注入值:" + injectionPort;
    }


    /**
     * 获取自定义配置
     * @return
     */

    @Autowired
    private MyConfig myConfig;
    @GetMapping("getCustomConfiguration")
    public String getCustomConfiguration() {
        return "自定义配置名称:" + myConfig.getName();
    }

    @Async
    @GetMapping("saveLog")
    public String saveLog() {
        System.err.println(Thread.currentThread().getName());
        return "执行saveLog";
    }
}
