import com.bjpowernode.redis.util.RedisUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

public class TestJedisPool {

    @Test
    public void testPool(){

        String host = "192.168.247.128";
        int port = 6379;
        String password = "123456";

        //创建Jedis的pool对象，从JedisPool中获取Jedis
        JedisPool pool = null;
        Jedis jedis = null;

        try{
            pool = RedisUtil.open(host,port,password);
            //从pool中获取Jedis
            jedis = pool.getResource();
            //调用jedis对象的方法，操作redis数据
            jedis.mset("k1","v1111","k2","v2222");
            List<String> keyList = jedis.mget("k1", "k2");
            for (String key : keyList){
                System.out.println(key);
            }

        }finally {
            //关闭Jedis对象，把从Pool中获取的Jedis对象放回到Pool，供其他请求使用
            if (jedis != null){
                jedis.close();
            }
        }
    }
}
