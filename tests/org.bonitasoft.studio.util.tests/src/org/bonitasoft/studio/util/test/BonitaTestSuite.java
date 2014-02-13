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
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.BoolResult;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.ui.PlatformUI;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

/**
 * @author Baptiste Mesta
 *
 */
public class BonitaTestSuite extends Suite {

	private final SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");
	private RunListener runListener;
	
	private void closeAllShells(SWTWorkbenchBot bot, Description description) {
		SWTBotShell[] shells = bot.shells();
		for (SWTBotShell shell : shells) {
			if (shell.isOpen() && !isEclipseShell(shell)) {
				bot.captureScreenshot("screenshots/ShellOpenedAfter"+description.getMethodName()+".jpg");
				shell.close();
				BonitaStudioLog.log("/!\\ Shell "+shell+" has been closed automatically, please fix the corresponding test to close it in @After (see screenshots)");
			}
		}
	}

	@SuppressWarnings("boxing")
	public static boolean isEclipseShell(final SWTBotShell shell) {
		return UIThreadRunnable.syncExec(new BoolResult() {
			public Boolean run() {
				return PlatformUI.getWorkbench().getActiveWorkbenchWindow()
						.getShell() == shell.widget;
			}
		});
	}

	/**
	 * @param klass
	 * @param suiteClasses
	 * @throws InitializationError
	 */
	public BonitaTestSuite(Class<?> klass, Class<?>[] suiteClasses) throws InitializationError {
		super(klass, suiteClasses);
	}

	/**
	 * @param klass
	 * @param runners
	 * @throws InitializationError
	 */
	public BonitaTestSuite(Class<?> klass, List<Runner> runners) throws InitializationError {
		super(klass, runners);
	}

	/**
	 * @param klass
	 * @param builder
	 * @throws InitializationError
	 */
	public BonitaTestSuite(Class<?> klass, RunnerBuilder builder) throws InitializationError {
		super(klass, builder);
	}

	/**
	 * @param builder
	 * @param klass
	 * @param suiteClasses
	 * @throws InitializationError
	 */
	public BonitaTestSuite(RunnerBuilder builder, Class<?> klass, Class<?>[] suiteClasses) throws InitializationError {
		super(builder, klass, suiteClasses);
	}

	/**
	 * @param builder
	 * @param classes
	 * @throws InitializationError
	 */
	public BonitaTestSuite(RunnerBuilder builder, Class<?>[] classes) throws InitializationError {
		super(builder, classes);
	}

	/* (non-Javadoc)
	 * @see org.junit.runners.Suite#runChild(org.junit.runner.Runner, org.junit.runner.notification.RunNotifier)
	 */
	@Override
	protected void runChild(Runner runner, RunNotifier notifier) {
		printBeforeTestClass(runner);
		super.runChild(runner, notifier);
		printAfterTestClass(runner);
	}
	/* (non-Javadoc)
	 * @see org.junit.runners.ParentRunner#run(org.junit.runner.notification.RunNotifier)
	 */
	@Override
	public void run(RunNotifier runNotifier) {
		addRunListener(runNotifier);
		super.run(runNotifier);
	}

	protected void addRunListener(RunNotifier runNotifier){
		RunListener listener = getRunListener();
		runNotifier.removeListener(listener);
		runNotifier.addListener(listener);
	}
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
					SWTWorkbenchBot bot = new SWTWorkbenchBot();

					BonitaStudioLog.log("|====================================================");
					BonitaStudioLog.log("| Try to clean shells after test : "+description.getMethodName());
					try{
						closeAllShells(bot,description);
						bot.saveAllEditors();
						bot.closeAllEditors();
					}catch (Exception e) {
						BonitaStudioLog.log("| Fails to clean shells after test : "+description.getMethodName());
						BonitaStudioLog.log("|====================================================");
						return;
					}

					BonitaStudioLog.log("| Shells cleaned after test : "+description.getMethodName());
					BonitaStudioLog.log("|====================================================");
				}
			};
		}
		return runListener;
	}

	/**
	 * @param runner
	 */
	protected void printAfterTestClass(Runner runner) {
		BonitaStudioLog.log("| finish test class: "+runner.getDescription());
		BonitaStudioLog.log("|====================================================");
		BonitaStudioLog.log("|%%%%%%%%%%%%%");
	}

	/**
	 * @param runner
	 */
	protected void printBeforeTestClass(Runner runner) {
		BonitaStudioLog.log("|====================================================");
		BonitaStudioLog.log("| Start test class: "+runner.getDescription());
	}


	/**
	 * @param description
	 */
	protected void printAfterTest(Description description) {
		BonitaStudioLog.log("|"+format.format(new Date())+"=> Finish: "+description.getMethodName());
	}

	/**
	 * @param description
	 */
	protected void printBeforeTest(Description description) {
		BonitaStudioLog.log("|"+format.format(new Date())+"=> Start: "+description.getMethodName());
	}


}
