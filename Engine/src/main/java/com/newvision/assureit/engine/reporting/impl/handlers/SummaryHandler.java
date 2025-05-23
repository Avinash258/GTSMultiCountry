/*
 * Copyright 2014 - 2017 newvision Software Pvt Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.newvision.assureit.engine.reporting.impl.handlers;

import com.newvision.assureit.engine.core.RunContext;
import com.newvision.assureit.engine.reporting.SummaryReport;
import com.newvision.assureit.engine.reporting.TestCaseReport;
import com.newvision.assureit.engine.reporting.intf.OverviewReport;
import com.newvision.assureit.engine.reporting.performance.har.Har;
import com.newvision.assureit.engine.support.Status;

/**
 *
 * 
 */
public class SummaryHandler implements OverviewReport {

    public SummaryReport report;

    public SummaryHandler(SummaryReport report) {
        this.report = report;
    }

    @Override
    public void createReport(String runTime,int size) {
   
    }

    @Override
    public void updateTestCaseResults(String testScenario, String testCase,
            String Iteration, String testDescription, String executionTime, 
            String fileName, Status state, String Browser) {
    }

    @Override
    public void updateTestCaseResults(RunContext runContext, TestCaseReport report,
            Status state, String executionTime) {
    }

    @Override
    public void finalizeReport() {

    }

    @SuppressWarnings("rawtypes")
	public void addHar(Har<String, Har.Log> h, TestCaseReport report, String pageName) {

    }

}
