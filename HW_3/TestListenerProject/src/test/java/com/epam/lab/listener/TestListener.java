package com.epam.lab.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private Logger LOG = LogManager.getLogger("TestListener");

    @Override
    public void onTestStart(ITestResult iTestResult) {
        logMessage(iTestResult, " started");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        logMessage(iTestResult, " succeed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logMessage(iTestResult, "failed");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        logMessage(iTestResult, "skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        LOG.info("Test " + iTestContext.getName() + " started!.");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        LOG.info("Test " + iTestContext.getName() + " finished!.");
    }

    private void logMessage(ITestResult iTestResult, String testExecutionStep) {
        LOG.info("Test " + iTestResult.getName() + "\n\tusername: " + iTestResult.getParameters()[0]
                + "\n\tpassword: " + iTestResult.getParameters()[1] + "\n\tstatus: " + testExecutionStep);
    }
}