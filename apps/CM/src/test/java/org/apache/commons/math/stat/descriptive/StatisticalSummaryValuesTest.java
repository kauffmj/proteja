/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.math.stat.descriptive;


import junit.framework.TestCase;

import org.apache.commons.math.TestUtils;
/**
 * Test cases for the {@link StatisticalSummaryValues} class.
 *
 * @version $Revision: 902201 $ $Date: 2010-01-22 13:18:16 -0500 (Fri, 22 Jan 2010) $
 */

public final class StatisticalSummaryValuesTest extends TestCase {


    public StatisticalSummaryValuesTest(String name) {
        super(name);
    }

    public void testSerialization() {
        StatisticalSummaryValues u = new StatisticalSummaryValues(1, 2, 3, 4, 5, 6);
        TestUtils.checkSerializedEquality(u);
        StatisticalSummaryValues t = (StatisticalSummaryValues) TestUtils.serializeAndRecover(u);
        verifyEquality(u, t);
    }

    public void testEqualsAndHashCode() {
        StatisticalSummaryValues u  = new StatisticalSummaryValues(1, 2, 3, 4, 5, 6);
        StatisticalSummaryValues t = null;
        assertTrue("reflexive", u.equals(u));
        assertFalse("non-null compared to null", u.equals(t));
        assertFalse("wrong type", u.equals(Double.valueOf(0)));
        t = new StatisticalSummaryValues(1, 2, 3, 4, 5, 6);
        assertTrue("instances with same data should be equal", t.equals(u));
        assertEquals("hash code", u.hashCode(), t.hashCode());

        u = new StatisticalSummaryValues(Double.NaN, 2, 3, 4, 5, 6);
        t = new StatisticalSummaryValues(1, Double.NaN, 3, 4, 5, 6);
        assertFalse("instances based on different data should be different",
                (u.equals(t) ||t.equals(u)));
    }

    private void verifyEquality(StatisticalSummaryValues s, StatisticalSummaryValues u) {
        assertEquals("N",s.getN(),u.getN());
        TestUtils.assertEquals("sum",s.getSum(),u.getSum(), 0);
        TestUtils.assertEquals("var",s.getVariance(),u.getVariance(), 0);
        TestUtils.assertEquals("std",s.getStandardDeviation(),u.getStandardDeviation(), 0);
        TestUtils.assertEquals("mean",s.getMean(),u.getMean(), 0);
        TestUtils.assertEquals("min",s.getMin(),u.getMin(), 0);
        TestUtils.assertEquals("max",s.getMax(),u.getMax(), 0);
    }
}
