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
package org.apache.commons.math.fraction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.commons.math.TestUtils;
import org.junit.Test;
import junit.framework.TestCase;

public class FractionFieldTest extends TestCase{

    @Test
    public void testZero() {
        assertEquals(Fraction.ZERO, FractionField.getInstance().getZero());
    }

    @Test
    public void testOne() {
        assertEquals(Fraction.ONE, FractionField.getInstance().getOne());
    }

    @Test
    public void testSerial() {
        // deserializing the singleton should give the singleton itself back
        FractionField field = FractionField.getInstance();
        assertTrue(field == TestUtils.serializeAndRecover(field));
    }

}