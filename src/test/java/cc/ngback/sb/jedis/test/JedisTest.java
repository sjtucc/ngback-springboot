package cc.ngback.sb.jedis.test;

import org.junit.Test;
import org.junit.runner.RunWith;

import redis.clients.jedis.Jedis;
import cc.ngback.sb.util.JedisClientSigle;
import cc.ngback.sb.util.RedisClient;

public class JedisTest {

	@Test
	public void testJedis() {
		//创建一个jedis的对象。
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		//调用jedis对象的方法，方法名称和redis的命令一致。
		jedis.set("key1", "jedis test");
		String string = jedis.get("key1");
		System.out.println(string);
		//关闭jedis。
		jedis.close();
	}
	
	/**
	 * 使用连接池
	 * @throws Exception 
	 */
	@Test
	public void testJedisPoolSingle() throws Exception {
		new JedisClientSigle().set("id", "111");
		System.out.println(new JedisClientSigle().get("id"));
	}
	
	/**
	 * 测试springboot配置jedis
	 */
	@Test
	public void testShardedJedisPool() throws Exception {
		new RedisClient().set("id", "111");
		System.out.println(new RedisClient().get("id"));
	}
}
