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

function Events() {
}

Events.getClickables = function () {
    return ["button", "input", "a", "textarea"];
};

Events.getInputables = function () {
    return ["color", "date", "datetime", "datetime-local", "email", "file", "month", "number", "password", "range", "search", "tel", "text", "time", "url", "week"];
};

function Recorder() {

}

Recorder.register = function () {
    registerClickEvents();
    registerChangeEvents();
    registerContextEvent();
};

Recorder.deregister = function () {
    deRegisterClickEvents();
    deRegisterChangeEvents();
    deRegisterContextEvent();
    assureitITS.deHighlightPreviousElement();
    Util.deghlightElement(context_element);
};

Recorder.recordContextEvent = function (data) {
    checkAndRecordContextEvent(data);
};

function registerClickEvents() {
    window.addEventListener("click", recordClickEvent, true);
}

function registerChangeEvents() {
    window.addEventListener("focus", recordFocusEvent, true);
    window.addEventListener("blur", recordBlurEvent, true);
    window.addEventListener("change", recordChangeEvent, true);
}

function registerContextEvent() {
    window.addEventListener("contextmenu", recordContextMenu, true);
}

function deRegisterClickEvents() {
    window.removeEventListener("click", recordClickEvent, true);
}

function deRegisterChangeEvents() {
    window.removeEventListener("focus", recordFocusEvent, true);
    window.removeEventListener("blur", recordBlurEvent, true);
    window.removeEventListener("change", recordChangeEvent, true);
}

function deRegisterContextEvent() {
    window.removeEventListener("contextmenu", recordContextMenu, true);
}

function recordClickEvent(event) {
    if (event.which === 1) {
        var clickElement = event.target;
        if (findClickableElement(clickElement)) {
            assureitITS.sendRecordedObject(clickElement, "Click");
        }
        else if (clickElement.tagName.toLowerCase() === "li") {
            unorderedListClickListener(clickElement);
        }
        else if (clickElement.tagName.toLowerCase() !== "select") {
            checkForMutatation(clickElement);
        }
    }
}

function findClickableElement(element) {
    if (!element.tagName)
        return null;
    if (Events.getClickables().indexOf(element.tagName.toLowerCase()) !== -1
            || element.hasAttribute("onclick")) {
        return element;
    }
    else {
        if (element.parentNode !== null) {
            return findClickableElement(element.parentNode);
        } else {
            return null;
        }
    }
}

function unorderedListClickListener(clickElement) {
    var value = Util.removeWhiteSpace(clickElement.textContent);
    if (Util.isNotNullOrEmpty(value))
        assureitITS.sendRecordedObject(clickElement.parentElement, "selectValueFromUnorderedList", "@" + value);
    else
        assureitITS.sendRecordedObject(clickElement.parentElement, "selectIndexFromUnorderedList", "@" + getElementCount(clickElement));
}

function recordFocusEvent(event) {
    var element = event.target;
    if (isInputTypeText(element))
        element.previousValue = element.value;
}

function recordBlurEvent(event) {
    var element = event.target;
    if (isInputTypeText(element))
    {
        var pValue = element.previousValue;
        var value = element.value;
        if (Util.isNotNullOrEmpty(value) && pValue !== value)
            element.dispatchEvent(new Event('change'));
    }
}

function isInputTypeText(element) {
    return element.tagName && element.tagName.toLowerCase() === "input" && element.type && (element.type.toLowerCase() === "text" || element.type.toLowerCase() === "password");
}

function recordChangeEvent(event) {
    var element = event.target;
    if (element.tagName.toLowerCase() === "select") {
        recordSelectEvent(element);
    }
    else if (element.tagName.toLowerCase() === "textarea" || (element.type && Events.getInputables().indexOf(element.type.toLowerCase()) !== -1)) {
        var input = Util.removeWhiteSpace(element.value);
        if (Util.isNotNullOrEmpty(input)) {
            if (element.type.toLowerCase() === "password")
                assureitITS.sendRecordedObject(element, "setEncrypted", "@" + input);
            else
                assureitITS.sendRecordedObject(element, "Set", "@" + input);
        }
        else
            assureitITS.sendRecordedObject(element, "clear");
    }
}

function recordSelectEvent(element) {
    var input = "@" + Util.removeWhiteSpace(element.selectedOptions[0].text);
    assureitITS.sendRecordedObject(element, "selectByVisibleText", input);
}

var context_element;
function recordContextMenu(event) {
    context_element = event.target;
}

function isBrowserAction(data) {
    return !isElementAction(data);
}

function isBrowserActionWithInput(data) {
    return isBrowserAction(data) && isElementWithInput(data);
}

function isElementAction(data) {
    return data.getElement ? true : false;
}

function isElementActionWithInput(data) {
    return isElementAction(data) && isElementWithInput(data);
}

function isElementWithInput(data) {
    return data.getInput ? true : data.input ? true : false;
}

function checkAndRecordContextEvent(data) {
    if (isElementActionWithInput(data)) {
        if (context_element)
            assureitITS.sendRecordedObject(context_element, data.command, getInput(data));
    } else if (isElementAction(data)) {
        if (context_element)
            assureitITS.sendRecordedObject(context_element, data.command);
    } else if (isBrowserActionWithInput(data)) {
        assureitITS.sendRecordedObject(null, data.command, getInput(data));
    } else if (isBrowserAction(data)) {
        assureitITS.sendRecordedObject(null, data.command);
    }
    context_element = null;
}

function getInput(data) {
    if (data.input)
        return data.input;
    return "@" + Object.getTextORValue(context_element);
}

var checkForMutatation = function (target) {
    var observer = new MutationObserver(function (mutations) {
        console.log(mutations);
        assureitITS.sendRecordedObject(mutations[0].target, "Click");
        observer.disconnect();
    });

    // configuration of the observer:
    var config = {attributes: true, childList: true, characterData: true};
    // pass in the target node, as well as the observer options
    observer.observe(target, config);
    console.log("Clicke");
};
