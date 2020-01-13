package cn.ocoop.framwork.miniprogram;

import cn.ocoop.framework.common.spring.SimplifiedAnnotationBeanNameGenerator;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableConfigurationProperties({MiniprogramProperties.class})
@ComponentScan(basePackageClasses = MiniprogramAutoConfiguration.class, nameGenerator = SimplifiedAnnotationBeanNameGenerator.class)
public class MiniprogramAutoConfiguration implements ApplicationContextAware {
    public static ApplicationContext ctx;
    @Value("${spring.redis.url}")
    private String redisUrl;

    @Bean
    @ConditionalOnMissingBean(RedissonClient.class)
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress(redisUrl);
        return Redisson.create(config);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MiniprogramAutoConfiguration.ctx = applicationContext;
    }
}
