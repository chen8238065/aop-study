package com.chapa.service;

/**
 * Created by chapa on 17-6-16.
 */
public class SingletonBean {
    private ITestService testService;

    public ITestService getTestService() {
        return testService;
    }

    public void setTestService(ITestService testService) {
        this.testService = testService;
    }
}
