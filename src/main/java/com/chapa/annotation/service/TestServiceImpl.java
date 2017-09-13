package com.chapa.annotation.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by chapa on 17-6-6.
 */
@Component
public class TestServiceImpl implements ITestService {
    @Override
    public void hhh() {
        System.out.println("TestServiceImpl with interface");
    }

    @Override
    public void say(String var, String word) {
        System.out.println("I`m "+var+"! "+word);
    }

    @Override
    public void callSlf() {
        hhh();
    }
}
