import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

public class TestJedis {

    @Test
    public void testJedis(){

        /*
            通过网络，访问Redis服务器:
                1.修改redis.conf, 启动redis需要指定 redis.conf的位置
                2.关闭linux的防火墙，或者让redis的端口号通过防火墙
         */

        //1.创建Jedis对象，指定连接的redis服务器的ip，端口
        String host = "192.168.247.128";
        int port = 6379;
        Jedis jedis = new Jedis(host,port);

        //2.设置访问密码
        jedis.auth("123456");

        //3.调用jedis对象的方法，操作redis数据
        jedis.mset("k1","v1","k2","v2");
        List<String> keyList = jedis.mget("k1", "k2");
        for (String key : keyList){
            System.out.println(key);
        }
    }
}
