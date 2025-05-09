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
package com.newvision.assureit.engine.execution.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * 
 */
public class DataIterator {

    private final Map<String, Integer> dataIter;
    private int maxIter = -1;

    public DataIterator() {
        dataIter = new HashMap<>();
    }

    public void setMaxIter(int n) {
        maxIter = Math.max(1, n);
    }

    public boolean isIterResolved(String sheet) {
        return dataIter.containsKey(sheet);
    }

    public synchronized void setIter(String sheet, Set<String> iter) {
        dataIter.put(sheet, iter.size());
        maxIter = (maxIter <= 1) ? iter.size() : Math.min(iter.size(), maxIter);
    }

    public Integer getMaxIter() {
        return Math.max(1, maxIter);
    }


    @Override
    public String toString() {
        return String.format("MaxIter:%s", maxIter);
    }
}
