package com.dlb.testng.xml;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.DataProvider;


public class LoginDataProvider {
    private static Object[][] obj;
    private static List parList=new ArrayList();
    private static List sonList=new ArrayList();
    //@DataProvider(name="loginProvider")
    @DataProvider()
    public static Object[][] loginProvider(Method method) {
        System.out.println("@DataProvider(name='loginProvider')");
        System.out.println("dataProvider"+method.getName());
//读取xml内容
        String  fname = "D:\\Users\\Administrator\\IdeaProjects\\AutoTestng\\DLBTestng\\src\\main\\resources\\LoginTest.xml";
        parList = XmlUtil.getXmlComent(fname);
        for (int i=0;i< parList.size();i++) {
            Map map = (Map)parList.get(i);
            if (map.containsKey(method.getName())) {
                Map<String,String> submap = (Map<String,String>) map.get(method.getName());
                sonList.add(submap);
            }
        }
        if (sonList.size() > 0) {
            obj = new Object[sonList.size()][];
            for (int i = 0; i < sonList.size(); i++) {
                obj[i] = new Object[] { sonList.get(i) };
            }
            return obj;
        }else{
            Assert.assertTrue(sonList.size()!=0,parList+"为空，找不到属性值："+method.getName() );
            return null;
        }
    }
}
