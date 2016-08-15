package org.lamda;

import com.google.common.collect.Maps;
import lombok.Data;

import java.util.Map;

/**
 * Created by zhangyx on 2016/8/15.
 */
public class ThreadLocalTest {
    private static ThreadLocal<Map<String,Object>> statisticsInfo= null;
    static{
        statisticsInfo = new ThreadLocal<>();
        statisticsInfo.set(Maps.newHashMap());
    }

    public static void main(String[] args) {
        TestMM testmm1 = new TestMM();
        Map<String,Object> map = statisticsInfo.get();
        map.put("m",testmm1);
        statisticsInfo.set(map);

        Map<String,Object> map2 =  statisticsInfo.get();

        TestMM testMM = (TestMM) map2.get("m");
        testmm1.setId("22");

        Map<String,Object> map3 =  statisticsInfo.get();

        TestMM testMM3 = (TestMM) map3.get("m");
        System.out.println(testMM3.getId());

        testmm1.setId("89");


        Map<String,Object> map4 =  statisticsInfo.get();

        TestMM testMM4 = (TestMM) map4.get("m");
        System.out.println(testMM4.getId());

    }
    @Data
   static class TestMM{
        private String id;
    }
}
