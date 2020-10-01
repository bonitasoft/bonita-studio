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
package org.bonitasoft.studio.application.splash;

import org.eclipse.core.runtime.IProduct;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.ProgressIndicator;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.ProgressMonitorPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.branding.IProductConstants;
import org.eclipse.ui.internal.StartupThreading.StartupRunnable;
import org.eclipse.ui.internal.util.PrefUtil;
import org.eclipse.ui.splash.AbstractSplashHandler;

/**
 * @author Romain Bioteau
 *
 */
public class BOSSplashHandler extends AbstractSplashHandler {

    public static final String BONITA_TASK = "Bonita";

    private static BOSSplashHandler INSTANCE ;
    private CustomAbsolutePositionProgressMonitorPart monitor;
    private Rectangle progressRect;
    private Rectangle messageRect;
    private Color foreground = null;

    public BOSSplashHandler(){
        if(INSTANCE == null){
            INSTANCE = this;
        }
    }


    public static IProgressMonitor getMonitor(){
        if(INSTANCE != null){
            return INSTANCE.getBundleProgressMonitor();
        }else{
            return null ;
        }
    }

    public static Shell getShell() {
        return INSTANCE.getSplash();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.ui.splash.AbstractSplashHandler#dispose()
     */
    @Override
    public void dispose() {
        super.dispose();
        if (foreground != null) {
            foreground.dispose();
        }
    }

    /**
     * Set the foreground text color. This method has no effect after
     * {@link #getBundleProgressMonitor()} has been invoked.
     *
     * @param foregroundRGB
     *            the color
     */
    protected void setForeground(final RGB foregroundRGB) {
        if (monitor != null) {
            return;
        }
        if (foreground != null) {
            foreground.dispose();
        }
        foreground = new Color(getSplash().getShell().getDisplay(),
                foregroundRGB);
    }

    /**
     * Get the foreground text color. This color should not be disposed by
     * callers.
     *
     * @return the foreground color
     */
    protected Color getForeground() {
        return foreground;
    }

    /**
     * Set the location of the message text in the splash. This method has no
     * effect after {@link #getBundleProgressMonitor()} has been invoked.
     *
     * @param messageRect
     *            the location of the message text
     */
    protected void setMessageRect(final Rectangle messageRect) {
        this.messageRect = messageRect;
    }

    /**
     * Set the location of the progress bar in the splash. This method has no
     * effect after {@link #getBundleProgressMonitor()} has been invoked.
     *
     * @param progressRect
     *            the location of the progress bar
     */
    protected void setProgressRect(final Rectangle progressRect) {
        this.progressRect = progressRect;
    }



    @Override
    public void init(final Shell splash) {
        super.init(splash);
        String progressRectString = null;
        String messageRectString = null;
        String foregroundColorString = null;
        final IProduct product = Platform.getProduct();
        if (product != null) {
            progressRectString = product.getProperty(IProductConstants.STARTUP_PROGRESS_RECT);
            messageRectString = product.getProperty(IProductConstants.STARTUP_MESSAGE_RECT);
            foregroundColorString = product.getProperty(IProductConstants.STARTUP_FOREGROUND_COLOR);
        }

        final Rectangle progressRect = StringConverter.asRectangle(progressRectString);
        setProgressRect(progressRect);

        final Rectangle messageRect = StringConverter.asRectangle(messageRectString);
        setMessageRect(messageRect);

        int foregroundColorInteger;
        try {
            foregroundColorInteger = Integer.parseInt(foregroundColorString, 16);
        } catch (final Exception ex) {
            foregroundColorInteger = 0xD2D7FF; // off white
        }

        setForeground(new RGB((foregroundColorInteger & 0xFF0000) >> 16,
                (foregroundColorInteger & 0xFF00) >> 8,
                foregroundColorInteger & 0xFF));

        setForeground(new RGB((foregroundColorInteger & 0xFF0000) >> 16,
                (foregroundColorInteger & 0xFF00) >> 8,
                foregroundColorInteger & 0xFF));

        // the following code will be removed for release time
        if (PrefUtil.getInternalPreferenceStore().getBoolean(
                "SHOW_BUILDID_ON_STARTUP")) { //$NON-NLS-1$
            final String buildId = System.getProperty(
                    "eclipse.buildId", "Unknown Build"); //$NON-NLS-1$ //$NON-NLS-2$
            // find the specified location.  Not currently API
            // hardcoded to be sensible with our current splash Graphic
            final String buildIdLocString = product.getProperty("buildIdLocation"); //$NON-NLS-1$
            final Point buildIdPoint = StringConverter.asPoint(buildIdLocString,
                    new Point(322, 190));
            getContent().addPaintListener(new PaintListener() {

                @Override
                public void paintControl(final PaintEvent e) {
                    e.gc.setForeground(getForeground());
                    e.gc.setBackground(getForeground()) ;
                    e.gc
                    .drawText(buildId, buildIdPoint.x, buildIdPoint.y,
                            true);
                }
            });
        }
        else {
            getContent(); // ensure creation of the progress
        }
    }

    protected Composite getContent() {
        return (Composite) getBundleProgressMonitor();
    }

    @Override
    public IProgressMonitor getBundleProgressMonitor() {
        if (monitor == null) {
            if(getSplash() == null){
                return null ;
            }
            final Composite parent = new Composite(getSplash(), Window.getDefaultOrientation());
            final Point size = getSplash().getSize();
            parent.setBounds(new Rectangle(0,0,size.x,size.y));
            monitor = new CustomAbsolutePositionProgressMonitorPart(parent);
            monitor.setSize(size);
            if (progressRect != null) {
                monitor.getProgressIndicator().setBounds(progressRect);
            } else {
                monitor.getProgressIndicator().setVisible(false);
            }

            if (messageRect != null) {
                monitor.getProgressText().setBounds(messageRect);
            } else {
                monitor.getProgressText().setVisible(false);
            }


            monitor.getProgressText().setForeground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
            monitor.setBackgroundMode(SWT.INHERIT_FORCE);
            monitor.setBackgroundImage(getSplash().getShell()
                    .getBackgroundImage());
        }
        return monitor;
    }

    private void updateUI(final Runnable r) {
        final Shell splashShell = getSplash();
        if (splashShell == null || splashShell.isDisposed()) {
            return;
        }

        final Display display = splashShell.getDisplay();

        if (Thread.currentThread() == display.getThread()) {
            r.run(); // run immediatley if we're on the UI thread
        } else {
            // wrapper with a StartupRunnable to ensure that it will run before
            // the UI is fully initialized
            final StartupRunnable startupRunnable = new StartupRunnable() {

                @Override
                public void runWithException() throws Throwable {
                    r.run();
                }
            };
            display.asyncExec(startupRunnable);
        }
    }

    /**
     * Hacks the progress monitor to have absolute positioning for its controls.
     * In addition, all methods that access the controls will be wrapped in an
     * asynchExec().
     */
    class CustomAbsolutePositionProgressMonitorPart extends ProgressMonitorPart {

        private final boolean noMoreUpdate =false;

        public CustomAbsolutePositionProgressMonitorPart(final Composite parent) {
            super(parent, null);
            setLayout(null);
        }

        public ProgressIndicator getProgressIndicator() {
            return fProgressIndicator;
        }

        public Label getProgressText() {
            return fLabel;
        }


        /* (non-Javadoc)
         * @see org.eclipse.jface.wizard.ProgressMonitorPart#beginTask(java.lang.String, int)
         */
        @Override
        public void beginTask(final String name, final int totalWork) {

            if(name.equals(BONITA_TASK)){
                updateUI(new Runnable() {

                    @Override
                    public void run() {
                        if (isDisposed()) {
                            return;
                        }
                        CustomAbsolutePositionProgressMonitorPart.super.beginTask(name,
                                totalWork);
                    }
                });
            }

        }


        /*
         * (non-Javadoc)
         *
         * @see org.eclipse.jface.wizard.ProgressMonitorPart#done()
         */
        @Override
        public void done() {

            updateUI(new Runnable() {

                @Override
                public void run() {
                    if (isDisposed()) {
                        return;
                    }
                    CustomAbsolutePositionProgressMonitorPart.super.done();
                }
            });

        }

        @Override
        public void subTask(final String name) {
        	super.subTask("");
        }

        /*
         * (non-Javadoc)
         *
         * @see org.eclipse.jface.wizard.ProgressMonitorPart#internalWorked(double)
         */
        @Override
        public void internalWorked(final double work) {

            updateUI(new Runnable() {

                @Override
                public void run() {
                    if (isDisposed()) {
                        return;
                    }
                    CustomAbsolutePositionProgressMonitorPart.super.internalWorked(work);
                }
            });

        }

        /*
         * (non-Javadoc)
         *
         * @see org.eclipse.jface.wizard.ProgressMonitorPart#setFont(org.eclipse.swt.graphics.Font)
         */
        @Override
        public void setFont(final Font font) {

            updateUI(new Runnable() {

                @Override
                public void run() {
                    if (isDisposed()) {
                        return;
                    }
                    CustomAbsolutePositionProgressMonitorPart.super.setFont(font);
                }
            });

        }

        /*
         * (non-Javadoc)
         *
         * @see org.eclipse.jface.wizard.ProgressMonitorPart#updateLabel()
         */
        @Override
        protected void updateLabel() {

            updateUI(new Runnable() {



                @Override
                public void run() {
                    if (isDisposed()) {
                        return;
                    }

                    if(!noMoreUpdate){
                        if(fSubTaskName != null){
                            fLabel.setText(fSubTaskName);
                        }else{
                            fLabel.setText("");
                        }
                        //Force an update as we are in the UI Thread
                        fLabel.update();
                    }
                }
            });

        }
    }

}
