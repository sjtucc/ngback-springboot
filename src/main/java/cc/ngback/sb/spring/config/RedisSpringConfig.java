package cc.ngback.sb.spring.config;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

@Configuration
@PropertySource(value = "classpath:redis.properties")
public class RedisSpringConfig {

    @Value("${redis.maxTotal}")
    private Integer redisMaxTotal;
    
    @Value("${redis.maxIdle}")
    private Integer maxIdle;
    
    @Value("${redis.minIdle}")
    private Integer minIdle;
    
    @Value("${redis.maxWaitMillis}")
    private Integer maxWaitMillis;
    
    @Value("${redis.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${redis.node1.host}")
    private String redisNode1Host;

    @Value("${redis.node1.port}")
    private Integer redisNode1Port;
    

    private JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //设置最大实例总数 
        jedisPoolConfig.setMaxTotal(redisMaxTotal);
        //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例
        jedisPoolConfig.setMaxIdle(maxIdle); 
        jedisPoolConfig.setMinIdle(minIdle); 
        //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis); 
        // 在borrow一个jedis实例时，是否提前进行alidate操作；如果为true，则得到的jedis实例均是可用的
        jedisPoolConfig.setTestOnBorrow(testOnBorrow); 
        return jedisPoolConfig;
    }
    
    @Bean
    public JedisPool jedisPool() {  
        return new JedisPool(new JedisPoolConfig() , redisNode1Host, redisNode1Port);  
    } 

    @Bean
    public ShardedJedisPool shardedJedisPool() {
        List<JedisShardInfo> jedisShardInfos = new ArrayList<JedisShardInfo>();
        jedisShardInfos.add(new JedisShardInfo(redisNode1Host, redisNode1Port));
        return new ShardedJedisPool(jedisPoolConfig(), jedisShardInfos);
    }
}
