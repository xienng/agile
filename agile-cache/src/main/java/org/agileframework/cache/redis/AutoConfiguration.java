package org.agileframework.cache.redis;

import org.springframework.context.annotation.Configuration;

/**
 * 因为集成的是spring-boot-starter-data-redis，Spring boot 会自动装配，注入RedisConnectionFactory对象
 * 参考springboot-autoconfigure jar中的org.springframework.boot.autoconfigure.data.redis代码
 *
 * @author xienng
 * @version 1.0
 * @date 2024年01月25日 14:35
 */
@Configuration
public class AutoConfiguration {


}
