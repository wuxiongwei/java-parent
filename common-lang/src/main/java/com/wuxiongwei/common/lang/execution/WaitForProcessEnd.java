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
package com.wuxiongwei.common.lang.execution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.function.Consumer;

/**
 * Wait until the {@code Process} has terminated.
 *
 * @author Evgen Vidolob
 */
public class WaitForProcessEnd {

  private static final Logger LOG = LoggerFactory.getLogger(WaitForProcessEnd.class);

  private final Future<?> waitFor;
  private final BlockingQueue<Consumer<Integer>> endCallback =
      new ArrayBlockingQueue<Consumer<Integer>>(1);

  public WaitForProcessEnd(Process process, Executor executor) {
    waitFor =
        executor.execute(
            () -> {
              int exitCode = 0;
              try {
                while (true) {
                  try {
                    exitCode = process.waitFor();
                    break;
                  } catch (InterruptedException e) {
                    LOG.error(e.getMessage(), e);
                  }
                }

              } finally {
                try {
                  endCallback.take().accept(exitCode);
                } catch (InterruptedException e) {
                  LOG.error(e.getMessage(), e);
                }
              }
            });
  }

  public void setEndCallback(Consumer<Integer> callback) {
    endCallback.offer(callback);
  }

  public void stop() {
    waitFor.cancel(true);
  }
}
