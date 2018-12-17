package com.telezone.socket;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.Serializable;

@Configuration
@ConfigurationProperties(prefix="com.telezone.socket.target")
@PropertySource(value = {"file:${config.path}"}, encoding="utf-8")
public class SocketResource implements Serializable {

    private Integer port;
    private String ip;

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
