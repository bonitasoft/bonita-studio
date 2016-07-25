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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import junit.framework.Test;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

/**
 * @author Baptiste Mesta
 *
 */
public final class BonitaJunit3TestSuite extends TestSuite {

    private final SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");
    /**
     * @param name
     */
    public BonitaJunit3TestSuite(String name) {
        super(name);
    }

    @Override
    public void runTest(Test test, TestResult result) {
        if (test instanceof TestSuite) {
            TestSuite testSuite = ((TestSuite) test);
            BonitaJunit3TestSuite bonitaJunit3TestSuite = new BonitaJunit3TestSuite(testSuite.getName());
            Enumeration<Test> tests = testSuite.tests();
            while (tests.hasMoreElements()) {
                Test testElement = tests.nextElement();
                bonitaJunit3TestSuite.addTest(testElement);
            }
            printBeforeTestClass(bonitaJunit3TestSuite.getName());
            super.runTest(bonitaJunit3TestSuite, result);
            printAfterTestClass(bonitaJunit3TestSuite.getName());
        } else {
            printBeforeTest(test.toString());
            super.runTest(test, result);
            printAfterTest(test.toString());
        }
    }


    /**
     * @param runner
     */
    private void printAfterTestClass(String description) {
        BonitaStudioLog.log("| finish test class: "+description);
        BonitaStudioLog.log("|====================================================");
        BonitaStudioLog.log("|%%%%%%%%%%%%%");
    }

    /**
     * @param runner
     */
    private void printBeforeTestClass(String description) {
        BonitaStudioLog.log("|====================================================");
        BonitaStudioLog.log("| Start test class: "+description);
    }


    /**
     * @param description
     */
    private void printAfterTest(String description) {
        final IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        if(activePage != null){
            for(IEditorPart part : activePage.getDirtyEditors()){
                BonitaStudioLog.log("| Editor with input : "+ part.getEditorInput().getName()+" is dirty after test "+ description);
            }
        }

        BonitaStudioLog.log("|"+format.format(new Date())+"=> Finish: "+description);
    }

    /**
     * @param description
     */
    private void printBeforeTest(String description) {
        BonitaStudioLog.log("|"+format.format(new Date())+"=> Start: "+description);
    }
}