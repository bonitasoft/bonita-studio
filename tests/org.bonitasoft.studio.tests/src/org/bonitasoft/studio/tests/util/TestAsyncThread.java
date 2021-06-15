/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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

import org.eclipse.swt.widgets.Display;

/**
 * @author Mickael Istria
 */
public abstract class TestAsyncThread {

    /**
     * @author Mickael Istria
     */
    private final class EvaluationRunnable implements Runnable {

        public void run() {
            do {
                try {
                    res = isTestGreen();
                    Thread.sleep(pause);
                } catch (Exception ex) {
                    if (tries == 0) {
                        lastException = ex;
                    }
                }
                tries--;
            } while (!res && tries >= 0);
        }
    }

    /**
     * @author Mickael Istria
     */
    private final class EvaluationThread extends Thread {

        /**
         * @param target
         */
        private EvaluationThread() {
            super(new EvaluationRunnable());
        }
    }

    private int tries;
    private int pause;

    public boolean res;
    public Exception lastException = null;

    /**
     * @param tries
     *        number of tries
     * @param pause
     *        pause time between each tries in ms
     */
    public TestAsyncThread(int tries, int pause) {
        reset(tries, pause);
    }

    public void reset(int tries, int pause) {
        this.tries = tries;
        this.pause = pause;
    }

    /**
     * @return
     */
    public abstract boolean isTestGreen() throws Exception;

    public boolean evaluate() throws Exception {

        Thread evaluationThread = new EvaluationThread();
        evaluationThread.start();
        do {
            Display.getDefault().readAndDispatch();
        } while (evaluationThread.isAlive());
        if (res == true) {
            return true;
        } else if (lastException != null) {
            throw lastException;
        } else {
            return false;
        }
    }

}
