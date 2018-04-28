package com.dlb.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "teacher")
public class GroupsClass3 {
    public void teacher1(){
        System.out.println("class3 teacher1");
    }

    public void teacher2(){
        System.out.println("class3 teacher2");
    }
}
