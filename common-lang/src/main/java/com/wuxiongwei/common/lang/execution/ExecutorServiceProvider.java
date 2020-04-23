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

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.wuxiongwei.common.lang.concurrent.LoggingUncaughtExceptionHandler;
import org.slf4j.Logger;

import javax.inject.Provider;
import javax.inject.Singleton;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Configurable {@link Provider} of {@link ExecutorService}.
 *
 * <p>Allow to configure corePoolSize, maximumPoolSize, queueCapacity. It uses two different
 * implementation of Queue. if queueCapacity > 0 then this is capacity of {@link
 * LinkedBlockingQueue} if <=0 then {@link SynchronousQueue} is used.
 *
 * <p>Implementation add {@link java.util.concurrent.RejectedExecutionHandler} that is printing
 * rejected message to the LOG.error. This can happen in case if there is no available Thread in
 * ThreadPool and there is no capacity in the queue.
 *
 * @author Sergii Kabashniuk
 */
@Singleton
public class ExecutorServiceProvider implements Provider<ExecutorService> {

  private static final Logger LOG = getLogger(ExecutorServiceProvider.class);

  private final ThreadPoolExecutor executor;
  /**
   * @param corePoolSize - corePoolSize of ThreadPoolExecutor
   * @param maxPoolSize - maximumPoolSize of ThreadPoolExecutor
   * @param queueCapacity - queue capacity. if > 0 then this is capacity of {@link
   *     LinkedBlockingQueue} if <=0 then {@link SynchronousQueue} are used.
   * @param rejectedExecutionHandler - {@link RejectedExecutionHandler} of ThreadPoolExecutor
   */
  public ExecutorServiceProvider(
      int corePoolSize,
      int maxPoolSize,
      int queueCapacity,
      RejectedExecutionHandler rejectedExecutionHandler) {
    ThreadFactory factory =
        new ThreadFactoryBuilder()
            .setUncaughtExceptionHandler(LoggingUncaughtExceptionHandler.getInstance())
            .setNameFormat(this.getClass().getSimpleName() + "-%d")
            .setDaemon(true)
            .build();

    executor =
        new ThreadPoolExecutor(
            corePoolSize,
            maxPoolSize,
            60L,
            SECONDS,
            queueCapacity > 0 ? new LinkedBlockingQueue<>(queueCapacity) : new SynchronousQueue<>(),
            factory);
    executor.setRejectedExecutionHandler(rejectedExecutionHandler);
    executor.prestartCoreThread();
  }

  /**
   * @param corePoolSize - corePoolSize of ThreadPoolExecutor
   * @param maxPoolSize - maximumPoolSize of ThreadPoolExecutor
   * @param queueCapacity - queue capacity. if > 0 then this is capacity of {@link
   *     LinkedBlockingQueue} if <=0 then {@link SynchronousQueue} are used.
   */
  public ExecutorServiceProvider(int corePoolSize, int maxPoolSize, int queueCapacity) {
    this(
        corePoolSize,
        maxPoolSize,
        queueCapacity,
        (r, e) -> LOG.warn("Executor rejected to handle the payload {}", r));
  }

  @Override
  public ExecutorService get() {
    return executor;
  }
}
