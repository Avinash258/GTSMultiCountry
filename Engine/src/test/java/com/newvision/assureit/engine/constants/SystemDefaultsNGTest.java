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
package com.newvision.assureit.engine.constants;

import java.util.regex.Pattern;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class SystemDefaultsNGTest {

    /**
     * Test of getBuildVersion method, of class SystemDefaults.
     */
    @Test
    public void testGetBuildVersion() {
        System.out.println("getBuildVersion");
        String result = SystemDefaults.getBuildVersion();
        Pattern pattern = Pattern.compile("^(?:(\\d+)\\.)?(?:(\\d+)\\.)?(\\*|\\d+)$");
        assertEquals(true, pattern.matcher(result).matches());
    }

    /**
     * Test of printSystemInfo method, of class SystemDefaults.
     */
    @Test
    public void testPrintSystemInfo() {
        System.out.println("printSystemInfo");
        SystemDefaults.printSystemInfo();
    }

}
