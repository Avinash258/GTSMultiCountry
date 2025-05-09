/**
Copyright 2023 - 2030 newvision Technology Solutions

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
**/

function ContextMenuList() {

}

ContextMenuList.menus = ["Open", "waitForPageLoaded","capturePageTimings"];

ContextMenuList.subMenus = {
    "Element": [
        "assertElementSelected",
        "assertElementEnabled",
        "assertElementDisplayed",
        "assertElementPresent",
        "---",
        "verifyElementSelected",
        "verifyElementEnabled",
        "verifyElementDisplayed",
        "---",
        "waitForElementToBePresent",
        "waitForElementToBeVisible",
        "waitForElementToBeClickable"
    ],
    "Text": [
        "assertElementTextEquals",
        "assertElementTextContains",
        "assertElementTextStartsWith",
        "assertElementTextEndsWith",
        "assertElementTextMatches"
    ],
    "Title": [
        "assertTitleEquals",
        "assertTitleContains",
        "assertTitleStartsWith",
        "assertTitleEndsWith",
        "assertTitleMatches",
        "---",
        "waitForTitleToBe",
        "waitForTitleToContain",
        "---",
        "switchToWindowByTitle",
        "switchToWindowByTitleContains",
        "switchToWindowByTitleStartsWith"
    ],
    "URL": [
        "assertUrlEquals",
        "assertUrlContains",
        "assertUrlStartsWith",
        "assertUrlEndsWith",
        "assertUrlMatches"
    ],
    "Other": [
        "AddElement",
        "AddElementWithInput",
        "AddBrowserWithInput"
    ]
};