package com.chapa.service;

import org.springframework.aop.framework.AopContext;

import java.util.Date;

/**
 * Created by chapa on 17-6-6.
 */
public class TestServiceImpl implements  ITestService{
    private Long timeMilis;

    public TestServiceImpl(){
        timeMilis = (new Date()).getTime();
    }

    public void hprintTime() {
        System.out.println(timeMilis+"");
    }
    @Override
    public void hhh() {
        System.out.println("TestServiceImpl with interface");
    }

    public void hcur(){
        System.out.println("TestServiceImpl with interface");
        ((ITestService) AopContext.currentProxy()).hprintTime();
    }
}
