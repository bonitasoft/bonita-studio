package org.bonitasoft.studio.tests.utils.wizards;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.TestClass;

public class WizardDialogTestRunner extends SWTBotJunit4ClassRunner {

    private IWizard newWizard = null;

    public WizardDialogTestRunner(Class<?> klass) throws Exception {
        super(klass);
    }

    @Override
    protected void runChild(final FrameworkMethod method, final RunNotifier notifier) {
        Shell parentShell = new Shell();
        try {
            newWizard = newWizard();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        final boolean[] finishPressed = new boolean[1];
        final WizardDialog dialog = new WizardDialog(parentShell, newWizard) {

            @Override
            protected void finishPressed() {
                super.finishPressed();
                finishPressed[0] = true;
            }
        };
        final Exception[] ex = new Exception[1];
        final boolean[] done = new boolean[1];
        Runnable r = new Runnable() {

            public void run() {
                try {
                    WizardDialogTestRunner.super.runChild(method, notifier);
                } catch (Exception e) {
                    ex[0] = e;
                } finally {
                    if (dialog.getShell() != null && dialog.getShell().isDisposed() == false) {
                        UIThreadRunnable.syncExec(new VoidResult() {

                            public void run() {
                                dialog.close();
                            }
                        });
                    }
                    done[0] = true;
                }
            }
        };
        Thread nonUIThread = new Thread(r);
        nonUIThread.setName("Runnning Test Thread");//$NON-NLS-1$
        nonUIThread.start();
        dialog.open();

        waitForNonUIThreadFinished(parentShell, nonUIThread);
        if (ex[0] != null) {
            throw new IllegalStateException(ex[0]);
        }
    }

    private void waitForNonUIThreadFinished(Shell parentShell, Thread nonUIThread) {
        Display display = parentShell.getDisplay();
        while (nonUIThread.isAlive()) {
            try {
                if (!display.readAndDispatch()) {
                    display.sleep();
                }
            } catch (Throwable e) {
            }
        }
    }

    @Override
    protected Object createTest() throws Exception {
        Object test = super.createTest();
        if (newWizard != null) {
            try {
                Field field = test.getClass().getField("wizard");
                field.set(test, newWizard);
            } catch (NoSuchFieldException e) {
            }
        }
        return test;
    }

    private IWizard newWizard() throws InstantiationException, IllegalAccessException {
        TestClass testClass = getTestClass();
        Annotation[] annotations = testClass.getAnnotations();
        IWizard newWizard = null;
        for (Annotation annotation : annotations) {
            if (annotation instanceof WithWizard) {
                WithWizard wizardAnnotation = (WithWizard) annotation;
                Class<? extends IWizard> value = wizardAnnotation.value();
                newWizard = value.newInstance();
            }
        }
        return newWizard;
    }

}
