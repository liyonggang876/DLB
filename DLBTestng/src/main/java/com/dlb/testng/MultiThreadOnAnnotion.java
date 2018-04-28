package com.dlb.testng;

import org.testng.annotations.Test;

public class MultiThreadOnAnnotion {

    @Test(invocationCount = 10,threadPoolSize = 2)
    public void test(){
        System.out.println(1);
        System.out.println("id:%s%n"+Thread.currentThread().getId());
    }
}
