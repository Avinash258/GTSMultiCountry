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
package com.newvision.assureit.engine.reporting.performance.har;

/**
 * MIME helper class for har
 *
 * 
 * 
 */
public enum MIME {

    HTML("text/html", ".html"),
    PNG("image/png", ".png"),
    JS("application/javascript", ".js"),
    CSS("text/css", ".css");
    /**
     * need to be added
     */
    public String val, ext;

    private MIME(String mime, String ext) {
        this.val = mime;
        this.ext = ext;
    }

    public String val() {
        return val;
    }

}
