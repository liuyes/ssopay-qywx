package com.ssopay.qywx.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.ssopay.qywx.base.Constants;
import com.ssopay.qywx.mail.SimpleMailSender;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis操作工具类
 * @author ssopay
 * @date 创建时间：Mar 14, 2014 5:25:07 PM
 */
public class RedisUtils {
	public static final int DEFAULT_MAX_TOTAL = -1; //默认不限制jedis实例数
	public static final int DEFAULT_TIMEOUT = 60 * 60 * 2; //默认超时时间 单位：秒
	public static final int MAX_TIMEOUT = Integer.MAX_VALUE; //最大超时时间 单位：秒
	private static JedisPool pool = null;
	
	/**
	 * 重新设置key的过期时间
	 */
	public static void resetTimout(String key) {
		JedisPool pool  = null;
		Jedis     jedis = null;
		try {
			pool  = getPool();
			jedis = pool.getResource();
			if (StringUtils.isNotEmpty(Constants.REDIS_AUTH)) {
				jedis.auth(Constants.REDIS_AUTH);
			}
			if(jedis != null) {
				// 检查key是否存在
				if (jedis.exists(key)) {
					jedis.expire(key, DEFAULT_TIMEOUT);
				}
			}
		} catch (Exception e) {
			// 发送警告
			sendMail(e);
			// 释放 redis对象
			returnBrokenResource(pool, jedis);
			e.printStackTrace();
		} finally {
			returnResource(pool, jedis);
		}
	}
	/**
	 * 设置字符串值
	 */
	public static void setString(String key, String value) {
		setString(key, value, DEFAULT_TIMEOUT);
	}
	/**
	 * 设置字符串值
	 */
	public static void setString(String key, String value, int timeout) {
		JedisPool pool  = null;
		Jedis     jedis = null;
		try {
			pool  = getPool();
			jedis = pool.getResource();
			if (StringUtils.isNotEmpty(Constants.REDIS_AUTH)) {
				jedis.auth(Constants.REDIS_AUTH);
			}
			if(jedis != null) {
				// 检查key是否存在，如果存在先删除
//				if (jedis.exists(key)) {
//					jedis.del(key);
//				}
				jedis.setex(key, timeout, value);
			}
		} catch (Exception e) {
			// 发送警告
			sendMail(e);
			// 释放 redis对象
			returnBrokenResource(pool, jedis);
			e.printStackTrace();
		} finally {
			returnResource(pool, jedis);
		}
	}
	
	/**
	 * 设置对象，mybatis cache使用
	 */
	public static void setObject(byte[] key, byte[] value) {
		JedisPool pool  = null;
		Jedis     jedis = null;
		try {
			pool  = getPool();
			jedis = pool.getResource();
			if (StringUtils.isNotEmpty(Constants.REDIS_AUTH)) {
				jedis.auth(Constants.REDIS_AUTH);
			}
			if(jedis != null) {
				// 检查key是否存在，如果存在先删除
//				if (jedis.exists(key)) {
//					jedis.del(key);
//				}
				jedis.set(key, value);
			}
		} catch (Exception e) {
			// 发送警告
			sendMail(e);
			// 释放 redis对象
			returnBrokenResource(pool, jedis);
			e.printStackTrace();
		} finally {
			returnResource(pool, jedis);
		}
	}
	/**
	 * 取字符串值
	 */
	public static String getString(String key) {
		JedisPool pool  = null;
		Jedis     jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			if (StringUtils.isNotEmpty(Constants.REDIS_AUTH)) {
				jedis.auth(Constants.REDIS_AUTH);
			}
			if(jedis != null) {
				return jedis.get(key);
			}
		} catch (Exception e) {
			// 发送警告
			sendMail(e);
			// 释放 redis对象
			returnBrokenResource(pool, jedis);
			e.printStackTrace();
		} finally {
			// 返还到连接池
            returnResource(pool, jedis);
		}
		return null;
	}
	/**
	 * 取对象，mybatis cache使用
	 */
	public static byte[] getObject(byte[] key) {
		JedisPool pool  = null;
		Jedis     jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			if (StringUtils.isNotEmpty(Constants.REDIS_AUTH)) {
				jedis.auth(Constants.REDIS_AUTH);
			}
			if(jedis != null) {
				return jedis.get(key);
			}
		} catch (Exception e) {
			// 发送警告
			sendMail(e);
			// 释放 redis对象
			returnBrokenResource(pool, jedis);
			e.printStackTrace();
		} finally {
			// 返还到连接池
            returnResource(pool, jedis);
		}
		return null;
	}
	
	/**
	 * 返回当前db中key的数量
	 */
	public static Long getSize() {
		JedisPool pool  = null;
		Jedis     jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			if (StringUtils.isNotEmpty(Constants.REDIS_AUTH)) {
				jedis.auth(Constants.REDIS_AUTH);
			}
			if(jedis != null) {
				return jedis.dbSize();
			}
		} catch (Exception e) {
			// 发送警告
			sendMail(e);
			// 释放 redis对象
			returnBrokenResource(pool, jedis);
			e.printStackTrace();
		} finally {
			// 返还到连接池
            returnResource(pool, jedis);
		}
		return null;
	}
	
	/**
	 * 删除key
	 */
	public static Long delete(String key) {
		JedisPool pool  = null;
		Jedis     jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			if (StringUtils.isNotEmpty(Constants.REDIS_AUTH)) {
				jedis.auth(Constants.REDIS_AUTH);
			}
			if(jedis != null) {
				if (jedis.exists(key)) {
					return jedis.del(key);
				}
			}
		} catch (Exception e) {
			// 发送警告
			sendMail(e);
			// 释放 redis对象
			returnBrokenResource(pool, jedis);
			e.printStackTrace();
		} finally {
			// 返还到连接池
            returnResource(pool, jedis);
		}
		return null;
	}
	
	/**
	 * 删除多个key
	 */
	public static void deleteKeys(String value) {
		JedisPool pool  = null;
		Jedis     jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			if (StringUtils.isNotEmpty(Constants.REDIS_AUTH)) {
				jedis.auth(Constants.REDIS_AUTH);
			}
			if(jedis != null) {
				Set<String> keys = jedis.keys(value);
				for (String key : keys) {
					if (jedis.exists(key)) {
						jedis.del(key);
					}
				}
			}
		} catch (Exception e) {
			// 发送警告
			sendMail(e);
			// 释放 redis对象
			returnBrokenResource(pool, jedis);
			e.printStackTrace();
		} finally {
			// 返还到连接池
            returnResource(pool, jedis);
		}
	}
	
	/**
	 * 清空当前数据库的所有key，可以使用jedis.select(0-15)选择数据库
	 * 默认数据库数是16个。
	 */
	public static void flushDB() {
		JedisPool pool  = null;
		Jedis     jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			if (StringUtils.isNotEmpty(Constants.REDIS_AUTH)) {
				jedis.auth(Constants.REDIS_AUTH);
			}
			if(jedis != null) {
				jedis.flushDB();// 删除当前数据库中所有key,此方法不会失败。慎用
			}
		} catch (Exception e) {
			// 发送警告
			sendMail(e);
			// 释放 redis对象
			returnBrokenResource(pool, jedis);
			e.printStackTrace();
		} finally {
			// 返还到连接池
            returnResource(pool, jedis);
		}
	}
	
	/**
	 * 清空所有数据库的所有key，可以使用jedis.select(0-15)选择数据库
	 * 默认数据库数是16个。
	 */
	public static void flushAll() {
		JedisPool pool  = null;
		Jedis     jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			if (StringUtils.isNotEmpty(Constants.REDIS_AUTH)) {
				jedis.auth(Constants.REDIS_AUTH);
			}
			if(jedis != null) {
		        jedis.flushAll();// 删除所有数据库中的所有key，此方法不会失败。更加慎用
			}
		} catch (Exception e) {
			// 发送警告
			sendMail(e);
			// 释放 redis对象
			returnBrokenResource(pool, jedis);
			e.printStackTrace();
		} finally {
			// 返还到连接池
            returnResource(pool, jedis);
		}
	}
	
	/**
     * 构建redis连接池
     */
    public static JedisPool getPool() {
        if (pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
            //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
            config.setMaxTotal(DEFAULT_MAX_TOTAL);
            //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
            config.setMaxIdle(20);
            //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
            config.setMaxWaitMillis(60000L);
            //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
            //启用密码验证的时候不可设为true，否则抛出JedisConnectionException Could not get a resource from the pool
            //config.setTestOnBorrow(true);
            pool = new JedisPool(config, Constants.REDIS_SERVER, 6379);
        }
        return pool;
    }
    
    /**
     * get/set出现异常时
     * 返还redis到连接池
     */
    public static void returnBrokenResource(JedisPool pool, Jedis redis) {
        if (redis != null) {
            pool.returnBrokenResource(redis);
        }
    }
	
	/**
     * 返还redis到连接池
     */
    public static void returnResource(JedisPool pool, Jedis redis) {
        if (redis != null) {
            pool.returnResource(redis);
        }
    }
    
   /**
    * 发送邮件提醒
    */
    private static void sendMail(Exception e) {
    	if (Constants.NEED_EMAIL) {
    		StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            try {
            	e.printStackTrace(pw);
            	String content = SimpleMailSender.CONTENT + sw.toString();
            	SimpleMailSender.sendTextMail(SimpleMailSender.ADDRESS, SimpleMailSender.SUBJECT, content);
            	sw.close();
            	pw.close();
    		} catch (Exception ex) {
    			ex.printStackTrace();
    		} finally {
    			if (sw != null) {
    				try {
    					sw.close();
    				} catch (IOException e1) {
    					e1.printStackTrace();
    				}
    			}
    			if (pw != null) {
    				pw.close();
    			}
    		}
		}
    }

}
