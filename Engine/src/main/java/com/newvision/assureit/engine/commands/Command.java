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
package com.newvision.assureit.engine.commands;

import com.newvision.assureit.datalib.or.common.ObjectGroup;
import com.newvision.assureit.datalib.or.image.ImageORObject;
import com.newvision.assureit.engine.core.CommandControl;
import com.newvision.assureit.engine.drivers.AutomationObject;
import com.newvision.assureit.engine.drivers.SeleniumDriver;
import com.newvision.assureit.engine.execution.data.UserDataAccess;
import com.newvision.assureit.engine.mail.Mailer;
import com.newvision.assureit.engine.reporting.TestCaseReport;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Command {

    public WebDriver Driver;
    public AutomationObject AObject;
    public String Data;
    public String ObjectName;
    public WebElement Element;
    public ObjectGroup<ImageORObject> imageObjectGroup;
    public String Description;
    public String Condition;
    public String Input;
    public String Action;
    public TestCaseReport Report;
    public String Reference;
    private final CommandControl Commander;
    public UserDataAccess userData;

    /********Webservice********/
     static public Map<String, String> endPoints = new HashMap<>();
     static public Map<String, ArrayList<String>> headers = new HashMap<>();
     static public Map<String, String> responsebodies = new HashMap<>();
     static public Map<String, String> responsecodes = new HashMap<>();
     static public Map<String, String> responsemessages = new HashMap<>();
     static public Map<String, HttpURLConnection> httpConnections = new HashMap<>();
     static public Map<String, String> httpagents = new HashMap<>();
     public String key;
     static public String basicAuthorization;
    /***************************/
    
    public Command(CommandControl cc) {
        Commander = cc;
        Driver = Commander.seDriver.driver;
        AObject = Commander.AObject;
        Data = Commander.Data;
        ObjectName = Commander.ObjectName;
        Element = Commander.Element;
        imageObjectGroup = Commander.imageObjectGroup;
        Description = Commander.Description;
        Condition = Commander.Condition;
        Input = Commander.Input;
        Report = Commander.Report;
        Reference = Commander.Reference;
        Action = Commander.Action;
        userData = Commander.userData;
        
        /********Webservice********/
        key = userData.getScenario()+userData.getTestCase();
        /**************************/
    }

    public void addVar(String key, String val) {
        Commander.addVar(key, val);
    }

    public String getVar(String key) {
        return Commander.getVar(key);
    }

    public void addGlobalVar(String key, String val) {
        if (key.matches("%.*%")) {
            key = key.substring(1, key.length() - 1);
        }
        Commander.putUserDefinedData(key, val);
    }

    public String getUserDefinedData(String key) {
        return Commander.getUserDefinedData(key);
    }

    public String getDataBaseData(String key) {
        String data = Commander.getDataBaseProperty(key);
        data = Mailer.decrypt(data);
        return data;
    }
    
 
    public Stack<WebElement> getRunTimeElement() {
        return Commander.getRunTimeElement();
    }

    public void executeMethod(String Action) {
        Commander.executeAction(Action);
    }

    public void executeMethod(WebElement element, String Action, String Input) {
        setElement(element);
        setInput(Input);
        executeMethod(Action);
    }

    public void executeMethod(String Action, String Input) {
        setInput(Input);
        executeMethod(Action);
    }

    public void executeMethod(WebElement element, String Action) {
        setElement(element);
        executeMethod(Action);
    }

    public SeleniumDriver getDriverControl() {
        return Commander.seDriver;
    }

    public Boolean isDriverAlive() {
          return getDriverControl().isAlive();
    }

    private void setElement(WebElement element) {
        Commander.Element = element;
    }

    private void setInput(String input) {
        Commander.Data = input;
    }

    public String getCurrentBrowserName() {
        return Commander.seDriver.getCurrentBrowser();
    }

    public CommandControl getCommander() {
        return Commander;
    }

    public void executeTestCase(String scenarioName, String testCaseName, int subIteration) {
        Commander.execute(scenarioName + ":" + testCaseName, subIteration);
    }

    public void executeTestCase(String scenarioName, String testCaseName) {
        executeTestCase(scenarioName, testCaseName, userData.getSubIterationAsNumber());
    }

    public boolean browserAction() {
        return "browser".equalsIgnoreCase(ObjectName);
    }
    /********Webservice***************/
    public String Endpoint(){
		return endPoints.get(key);
	}
    
    public String ResponseCode(){
		return responsecodes.get(key);
	}
    
    public String ResponseMessage(){
		return responsemessages.get(key);
	}
    
    public String ResponseBody(){
		return responsebodies.get(key);
	}
    
    public HttpURLConnection Connection(){
		return httpConnections.get(key);
	}
    
    public String HttpAgent(){
		return httpagents.get(key);
	}
    
    /*********************************/
}
