/**
 * Copyright (C) 2021 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.tests.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class Await {

    public static void waitUntil(Supplier<Boolean> condition, int timeout, int interval)
            throws TimeoutException, InterruptedException {
        var countDownLatch = new CountDownLatch(1);
        var shouldExitThread = new AtomicBoolean();
        shouldExitThread.set(false);
        new Thread(() -> {
            var result = false;
            while (!result && !shouldExitThread.get()) {
                result = condition.get();
                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if (result) {
                countDownLatch.countDown();
            }
        }, "Wait condition thread").start();

        if (!countDownLatch.await(timeout, TimeUnit.MILLISECONDS)) {
            shouldExitThread.set(true);
            throw new TimeoutException(String.format("Failed to evaluate condtion after %sms", timeout));
        }

    }

}
