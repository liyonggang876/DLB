package com.dlb.testng.xml;
import java.awt.Frame;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.testng.annotations.Test;

public class LoginTest {
    @Test(dataProvider="loginProvider",dataProviderClass=LoginDataProvider.class)
    public  void loginRight(Map<?, ?> param) throws InterruptedException{
        /*Iterator it = param.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            System.out.println("key=" + key + " value=" + value);
        }*/
        System.out.println((String)param.get("username") + "=" + (String)param.get("password"));

    }
}
