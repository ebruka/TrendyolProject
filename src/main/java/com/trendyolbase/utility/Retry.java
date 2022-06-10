package com.trendyolbase.utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	// @Test(retryAnalyzer = Retry.class)
	private int count = 0;
	private static final int maxTry = 1;

	@Override
	public boolean retry(ITestResult iTestResult) {
		if (!iTestResult.isSuccess()) {
			if (count < maxTry) {
				count++;
				iTestResult.setStatus(ITestResult.SKIP);
				return true;
			} else {
				iTestResult.setStatus(ITestResult.FAILURE);
			}
		} else {
			iTestResult.setStatus(ITestResult.SUCCESS);
		}
		return false;
	}
}