/**
 * Copyright (c) 2014,2018 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.smarthome.ui.icon.internal;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.eclipse.smarthome.ui.icon.AbstractResourceIconProvider;
import org.eclipse.smarthome.ui.icon.IconProvider;
import org.eclipse.smarthome.ui.icon.IconSet;
import org.eclipse.smarthome.ui.icon.IconSet.Format;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link AbstractResourceIconProvider}.
 *
 * @author Kai Kreuzer - Initial contribution
 * @author Wouter Born - Migrate tests from Groovy to Java
 */
public class AbstractResourceIconProviderTest {

    private IconProvider provider;

    @Before
    public void setUp() {

        provider = new AbstractResourceIconProvider() {
            @Override
            protected InputStream getResource(String iconset, String resourceName) {
                return "x-30.png".equals(resourceName) ? new ByteArrayInputStream("".getBytes()) : null;
            }

            @Override
            protected boolean hasResource(String iconset, String resourceName) {
                String state = StringUtils.substringAfterLast(resourceName, "-");
                state = StringUtils.substringBeforeLast(state, ".");
                return Integer.valueOf(state) == 30;
            };

            @Override
            public Set<IconSet> getIconSets(Locale locale) {
                return Collections.emptySet();
            };

            @Override
            public Integer getPriority() {
                return 0;
            };
        };
    }

    @Test
    public void testScanningForState() throws IOException {
        try (InputStream is = provider.getIcon("x", "classic", "34", Format.PNG)) {
            assertNotNull(is);
        }

        try (InputStream is = provider.getIcon("x", "classic", "25", Format.PNG)) {
            assertNull(is);
        }
    }
}
