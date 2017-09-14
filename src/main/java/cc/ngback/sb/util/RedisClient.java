package cc.ngback.sb.util;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisClient {  
  
    @Autowired  
    private ShardedJedisPool shardedjedisPool;  
      
    public void set(String key, String value) throws Exception {  
    	ShardedJedis jedis = null;  
        try {  
            jedis = shardedjedisPool.getResource();  
            jedis.set(key, value);  
        } finally {  
            //返还到连接池  
            jedis.close();  
        }  
    }  
      
    public String get(String key) throws Exception  {  
    	ShardedJedis jedis = null;  
        try {  
            jedis = shardedjedisPool.getResource();  
            return jedis.get(key);  
        } finally {  
            //返还到连接池  
            jedis.close();  
        }  
    }  
      
    public void hset(String hkey, String key, String value) throws Exception {  
    	ShardedJedis jedis = null;  
        try {  
            jedis = shardedjedisPool.getResource();  
            jedis.hset(hkey, key, value);  
        } finally {  
            //返还到连接池  
            jedis.close();  
        }  
    }  
    
    public void hget(String hkey, String key) throws Exception {  
    	ShardedJedis jedis = null;  
        try {  
            jedis = shardedjedisPool.getResource();  
            jedis.hget(hkey, key);  
        } finally {  
            //返还到连接池  
            jedis.close();  
        }  
    } 
    
    public void expire(String key, int second) throws Exception {  
    	ShardedJedis jedis = null;  
        try {  
            jedis = shardedjedisPool.getResource();  
            jedis.expire(key, second);
        } finally {  
            //返还到连接池  
            jedis.close();  
        }  
    }
} 