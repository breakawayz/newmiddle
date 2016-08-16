package org.redis;

import lombok.Data;
import redis.clients.jedis.Jedis;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by break on 2016/8/14.
 */
@Data
public class RedisBillLockHandler {
    private static Jedis jedis;
    private static final String _KEY = "emailproxy_lock";
    private static final String _CLIENTCODE;
    private static final int _EXPIRE_TIME = 5;
    static {
        _CLIENTCODE = UUID.randomUUID().toString().replaceAll("-","");
    }
    public RedisBillLockHandler(Jedis jedis){
        this.jedis = jedis;
    }
    public static boolean tryLock(long timeout) {

        long nano = System.nanoTime();
        try {
            do {
                String setResult = jedis.set(_KEY, _CLIENTCODE, "nx", "ex", _EXPIRE_TIME);
                if (setResult != null && "OK".equalsIgnoreCase(setResult)) {
                    jedis.expire(_KEY, _EXPIRE_TIME);
                    System.out.println("get lock, key: " + _KEY + " , expire in " + _EXPIRE_TIME + " seconds,client code :"+_CLIENTCODE+ ".");
                    return Boolean.TRUE;
                } else { // 存在锁

                    String value = jedis.get(_KEY);
                    if(_CLIENTCODE.equals(value)){  //重入锁
                        System.out.println("reenconcuent lock");
                        jedis.expire(_KEY, _EXPIRE_TIME);
                        return Boolean.TRUE;
                    }
                    System.out.println("key: " + _KEY + " now business is : "+_CLIENTCODE+" locked by another business：" + value);

                }
                if (timeout == 0) {
                    break;
                }
                Thread.sleep(300);
            }while ((System.nanoTime() - nano) < TimeUnit.NANOSECONDS.toNanos(timeout));
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return Boolean.FALSE;
    }
}
