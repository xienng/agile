package org.agileframework.cache.redis;

import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * [简要描述类用途]
 * <p>
 * [详细描述类用途、功能等，可选]
 * <p>
 * [额外的细节描述，比如类的组成、原理等，可选]
 *
 * @author xienng
 * @version 1.0
 * @date 2024年01月25日 14:35
 */
public class RedisConfig {
    @Value("${redis.max-active:200}")
    private int maxActive;

    @Value("${redis.max-idle:50}")
    private int maxIdle;

    @Value("${redis.min-idle:5}")
    private int minIdle;

    @Value("${redis.max-wait:-1}")
    private int maxWait;

    @Value("${redis.redis-password:redis-pass}")
    private String password;

    @Value("${redis.max-redirects:3}")
    private int maxRedirects;

    @Value(("${redis.test-on-borrow:true}"))
    private boolean testOnBorrow;

    @Value("${redis.refresh-periods:60}")
    private int refreshPeriods;

    @Value("${redis.redis-cluster-nodes}")
    private String[] nodes;

    /**
     * 配置LettuceConnectionFactory，包含Redis集群配置和客户端配置
     *
     * @param redisClusterConfiguration
     * @param lettucePoolingClientConfiguration
     * @return
     */
    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory(
            @Autowired RedisClusterConfiguration redisClusterConfiguration,
            @Autowired LettucePoolingClientConfiguration lettucePoolingClientConfiguration) {
        return new LettuceConnectionFactory(redisClusterConfiguration, lettucePoolingClientConfiguration);
    }

    /**
     * Redis集群配置
     *
     * @return
     */
    @Bean
    public RedisClusterConfiguration redisClusterConfiguration() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        List<RedisNode> redisNodes = new ArrayList<>();
        for (String node : nodes) {
            String[] nodeInfo = node.split(":");
            RedisNode redisNode = new RedisNode(nodeInfo[0], Integer.valueOf(nodeInfo[1]));
            redisNodes.add(redisNode);
        }
        redisClusterConfiguration.setClusterNodes(redisNodes);
        redisClusterConfiguration.setMaxRedirects(maxRedirects);
        redisClusterConfiguration.setPassword(password);
        return redisClusterConfiguration;
    }

    /**
     * Redis客户端配置
     *
     * @return
     */
    @Bean
    public LettucePoolingClientConfiguration lettucePoolingClientConfiguration() {
        LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder configurationBuilder =
                LettucePoolingClientConfiguration.builder();
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxIdle(maxIdle);
        genericObjectPoolConfig.setMinIdle(minIdle);
        genericObjectPoolConfig.setMaxTotal(maxActive);
        genericObjectPoolConfig.setMaxWaitMillis(maxWait);
        genericObjectPoolConfig.setTestOnBorrow(testOnBorrow);
        configurationBuilder.poolConfig(genericObjectPoolConfig);
        // 拓扑更新设置
        ClusterTopologyRefreshOptions.Builder topologyBuilder = ClusterTopologyRefreshOptions.builder();
        topologyBuilder.enableAllAdaptiveRefreshTriggers();
        topologyBuilder.enablePeriodicRefresh(true);
        topologyBuilder.refreshPeriod(Duration.ofSeconds(refreshPeriods));
        topologyBuilder.dynamicRefreshSources(true);
        topologyBuilder.closeStaleConnections(true);
        // client选项设置
        ClusterClientOptions.Builder clientOptionsBuilder = ClusterClientOptions.builder();
        clientOptionsBuilder.autoReconnect(true);
        clientOptionsBuilder.topologyRefreshOptions(topologyBuilder.build());
        configurationBuilder.clientOptions(clientOptionsBuilder.build());
        return configurationBuilder.build();
    }

    @Bean
    public StringRedisTemplate stringTestRedisTemplate(@Autowired LettuceConnectionFactory lettuceConnectionFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(lettuceConnectionFactory);
        return stringRedisTemplate;
    }

    @Bean
    public RedisPipelineTemplate redisPipelineTemplate() {
        return new RedisPipelineTemplate();
    }


}
