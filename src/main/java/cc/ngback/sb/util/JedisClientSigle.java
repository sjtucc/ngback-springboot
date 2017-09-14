package cc.ngback.sb.util;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisClientSigle {
	@Autowired  
    private JedisPool jedisPool;  
      
    public void set(String key, String value) throws Exception {  
        Jedis jedis = null;  
        try {  
            jedis = jedisPool.getResource();  
            jedis.set(key, value);  
        } finally {  
            //返还到连接池  
            jedis.close();  
        }  
    }  
      
    public String get(String key) throws Exception  {  
  
        Jedis jedis = null;  
        try {  
            jedis = jedisPool.getResource();  
            return jedis.get(key);  
        } finally {  
            //返还到连接池  
            jedis.close();  
        }  
    }
}
