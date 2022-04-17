package com.hepsiburadabase.utility;

import io.qameta.allure.Attachment;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListeners implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result){
        if (Driver.getDriver() != null) {
            Driver.takeScreenShot();
            Log.warning(Driver.getDriver().getCurrentUrl());
        }
        saveTextLog(result.getMethod().getConstructorOrMethod().getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result){
        Driver.takeScreenShot();
        saveTextLog(result.getMethod().getConstructorOrMethod().getName());

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result){
        Driver.takeScreenShot();
        saveTextLog(result.getMethod().getConstructorOrMethod().getName());

    }

    //Text attachments for Allure
    @Attachment(value = "Log", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    //HTML attachments for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        iTestContext.setAttribute("WebDriver", Driver.getDriver());
    }

}

