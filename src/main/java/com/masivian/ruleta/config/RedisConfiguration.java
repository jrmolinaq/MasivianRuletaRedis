package com.masivian.ruleta.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;

@Configuration
public class RedisConfiguration {

    @Autowired ClusterConfigurationProperties clusterConfigProp;

    @Bean
    JedisConnectionFactory jedisConnectionFactory(){

        RedisClusterConfiguration configCluster =
                new RedisClusterConfiguration(Arrays.asList(clusterConfigProp.getNodes().split(",")));

        configCluster.setMaxRedirects(clusterConfigProp.getMaxRedirects());
        configCluster.setPassword(clusterConfigProp.getPassword());

        return new JedisConnectionFactory(configCluster);
    }

    @Bean
    RedisTemplate<Integer, Object> redisTemplate(){
        final RedisTemplate<Integer, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }

}
