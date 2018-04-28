package com.dlb.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupsOnMethods {

    @Test(groups = "server")
    public void test1(){
        System.out.println("server 1");
    }

    @Test(groups ="server")
    public void test2(){
        System.out.println("server 2");
    }

    @Test(groups ="client")
    public void test3(){
        System.out.println("client 1");
    }

    @Test(groups = "client")
    public void test4(){
        System.out.println("client 2");
    }

    @BeforeGroups("server")
    public void beforeServer(){
        System.out.println("before server");
    }

    @AfterGroups("server")
    public void afterServer(){
        System.out.println("after server");
    }

    @BeforeGroups("client")
    public void beforeClient(){
        System.out.println("before client");
    }

    @AfterGroups("client")
    public void afterClinet(){
        System.out.println("after client");
    }
}
