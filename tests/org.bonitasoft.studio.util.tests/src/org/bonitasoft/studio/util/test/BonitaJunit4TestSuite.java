/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.util.test;

import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunListener;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

/**
 * @author Baptiste Mesta
 *
 */
public final class BonitaJunit4TestSuite extends BonitaTestSuite {

    private RunListener runListener;

	public BonitaJunit4TestSuite(Class<?> klass, Class<?>[] suiteClasses) throws InitializationError {
        super(klass, suiteClasses);
    }

    /**
     * @param klass
     * @param runners
     * @throws InitializationError
     */
    public BonitaJunit4TestSuite(Class<?> klass, List<Runner> runners) throws InitializationError {
        super(klass, runners);
    }

    /**
     * @param klass
     * @param builder
     * @throws InitializationError
     */
    public BonitaJunit4TestSuite(Class<?> klass, RunnerBuilder builder) throws InitializationError {
        super(klass, builder);
    }

    /**
     * @param builder
     * @param klass
     * @param suiteClasses
     * @throws InitializationError
     */
    public BonitaJunit4TestSuite(RunnerBuilder builder, Class<?> klass, Class<?>[] suiteClasses) throws InitializationError {
        super(builder, klass, suiteClasses);
    }

    /**
     * @param builder
     * @param classes
     * @throws InitializationError
     */
    public BonitaJunit4TestSuite(RunnerBuilder builder, Class<?>[] classes) throws InitializationError {
        super(builder, classes);
    }

    @Override
    protected RunListener getRunListener() {
    	if(runListener == null){
    		runListener = new RunListener(){
                /* (non-Javadoc)
                 * @see org.junit.runner.notification.RunListener#testStarted(org.junit.runner.Description)
                 */
                @Override
                public void testStarted(Description description) throws Exception {
                    printBeforeTest(description);
                }
                /* (non-Javadoc)
                 * @see org.junit.runner.notification.RunListener#testFinished(org.junit.runner.Description)
                 */
                @Override
                public void testFinished(Description description) throws Exception {
                    printAfterTest(description);
                }
            };
		}
		return runListener;
		
    }
}