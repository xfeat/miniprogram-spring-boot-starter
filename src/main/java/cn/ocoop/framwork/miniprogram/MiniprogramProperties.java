package cn.ocoop.framwork.miniprogram;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@Data
@RefreshScope
@ConfigurationProperties(prefix = MiniprogramProperties.PREFIX)
public class MiniprogramProperties {
    public static final String PREFIX = "miniprogram";
    private String appid;
    private String secret;
}
