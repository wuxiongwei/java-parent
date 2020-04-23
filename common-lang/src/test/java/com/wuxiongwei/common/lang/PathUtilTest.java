/*
 * Copyright (c) 2012-2018 Red Hat, Inc.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Red Hat, Inc. - initial API and implementation
 */
package com.wuxiongwei.common.lang;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/** Tests for {@link PathUtil} */
public class PathUtilTest {

  @Test
  public void testCanonicalPath() throws Exception {
    String path = PathUtil.toCanonicalPath("/foo/../bar", false);
    assertNotNull(path);
    assertFalse(path.isEmpty());
    assertEquals(path, "/bar");
  }

  @Test
  public void testRemoveLastSlash() throws Exception {
    String path = PathUtil.toCanonicalPath("/foo/bar/", true);
    assertNotNull(path);
    assertFalse(path.isEmpty());
    assertEquals(path, "/foo/bar");
  }

  @Test
  public void testEliminationDot() throws Exception {
    String path = PathUtil.toCanonicalPath("./bar", false);
    assertNotNull(path);
    assertFalse(path.isEmpty());
    assertEquals(path, "bar");
  }

  @Test
  public void testCanonicalPathWithFile() throws Exception {
    String path = PathUtil.toCanonicalPath("/foo/../bar/pom.xml", false);
    assertNotNull(path);
    assertFalse(path.isEmpty());
    assertEquals(path, "/bar/pom.xml");
  }
}
