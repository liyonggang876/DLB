package com.dlb.testng.paramter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest {

    @Test(dataProvider = "data")
    public void testDataProvider(String name,int age){
        System.out.println("name=" + name + ":" + "age=" + age);
    }

    @DataProvider(name="data")
    public Object[][] prviderData(){
        Object[][] o = new Object[][]{
                {"zhangsan",10},
                {"lisi",5},
                {"wangwu",8}
        };
        return o;
    }





}
