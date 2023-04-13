/*******************************************************************************
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.ui.notification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.ui.notification.BonitaNotificator.NOTIFICATION_LEVEL;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.notifications.AbstractNotificationPopup;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
 * This is a notification popup.
 * Quite similar to org.eclipse.jface.notifications.NotificationPopup, except:
 * <ul><li>it is displayed on same screen</li>
 * <li>a few cosmetic changes (color / cursor / layout)</li>
 * <li>it does not stack but displays vertically</li>
 * <li>height adapts to big text width-wrapping</li></ul>
 */
public class BonitaNotificationPopup extends AbstractNotificationPopup {

    private static final int MAX_WIDTH = 400;
    private static final int MIN_HEIGHT = 120;
    private static final int PADDING_EDGE = 10;

    private static List<Shell> existingNotifications = new ArrayList<>();

    private String title;
    private String content;
    private NOTIFICATION_LEVEL level;
    private Optional<Listener> selectionListener;

    public BonitaNotificationPopup(Display display, String title, String content, NOTIFICATION_LEVEL level,
            Optional<Listener> selectionListener) {
        super(display, SWT.NO_TRIM | SWT.ON_TOP | SWT.NO_FOCUS | SWT.TOOL);
        this.title = title;
        this.content = content;
        this.level = level;
        this.selectionListener = selectionListener;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.notifications.AbstractNotificationPopup#getTitleForeground()
     */
    @Override
    protected Color getTitleForeground() {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.notifications.AbstractNotificationPopup#getPopupShellImage(int)
     */
    @Override
    protected Image getPopupShellImage(int maximumHeight) {
        switch (level) {
            case ERROR:
                return Pics.getImage(PicsConstants.notificationError);
            case WARNING:
                return Pics.getImage(PicsConstants.notificationWarning);
            default:
                return Pics.getImage(PicsConstants.notificationInfo);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.notifications.AbstractNotificationPopup#createContentArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected void createContentArea(org.eclipse.swt.widgets.Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        composite.setLayout(GridLayoutFactory.fillDefaults().create());

        Link link = new Link(composite, SWT.WRAP);
        link.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        link.setText(content);
        selectionListener.ifPresent(listener -> link.addListener(SWT.Selection, listener));
    }

    protected String getPopupShellTitle() {
        return title;
    }

    @Override
    protected void initializeBounds() {
        Rectangle clArea = getClientArea();
        // we must compute height with max width as hint, so that the content/text is considered wrapped
        int height = Math.max(getShell().computeSize(MAX_WIDTH, SWT.DEFAULT).y, MIN_HEIGHT);
        // width, on the other hand, is computed with default sizes to be able to shrink smaller
        int width = Math.min(getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT).x, MAX_WIDTH);

        Point size = new Point(width, height);
        getShell().setLocation(clArea.width + clArea.x - size.x - PADDING_EDGE, getInitialY(clArea, height) - size.y
                - PADDING_EDGE);
        getShell().setSize(size);
        existingNotifications.add(getShell());
    }

    private int getInitialY(Rectangle clArea, int height) {
        int min = existingNotifications.stream()
                .filter(shell -> !shell.isDisposed())
                .map(Shell::getLocation)
                .mapToInt(p -> p.y)
                .min().orElse(0);
        int initialY = min > height ? min : clArea.height + clArea.y;
        return initialY;
    }

    /**
     * When an active shell can be found, returns the client area of the active monitor.
     * If there is no active shell (i.e user is on an other window), the client area from the default display is used.
     */
    private Rectangle getClientArea() {
        Shell activeShell = Display.getDefault().getActiveShell();
        return activeShell != null
                ? activeShell.getMonitor().getClientArea()
                : Display.getDefault().getClientArea();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.window.Window#constrainShellSize()
     */
    @Override
    protected void constrainShellSize() {
        super.constrainShellSize();
        getShell().setLocation(fixupDisplayBounds(getShell().getSize(), getShell().getLocation()));
    }

    private Point fixupDisplayBounds(Point tipSize, Point location) {
        Rectangle bounds;
        Point rightBounds = new Point(tipSize.x + location.x, tipSize.y + location.y);
        bounds = getClientArea();
        if (!(bounds.contains(location) && bounds.contains(rightBounds))) {
            if (rightBounds.x > bounds.x + bounds.width) {
                location.x -= rightBounds.x - (bounds.x + bounds.width);
            }

            if (rightBounds.y > bounds.y + bounds.height) {
                location.y -= rightBounds.y - (bounds.y + bounds.height);
            }

            if (location.x < bounds.x) {
                location.x = bounds.x;
            }

            if (location.y < bounds.y) {
                location.y = bounds.y;
            }
        }
        return location;
    }

    @Override
    protected Control createContents(Composite parent) {
        ((GridLayout) parent.getLayout()).marginWidth = 1;
        ((GridLayout) parent.getLayout()).marginHeight = 1;

        Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().margins(5, 5).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME,
                BonitaThemeConstants.NOTIFICATION_COMPOSITE);

        Composite titleComposite = new Composite(mainComposite, SWT.NO_FOCUS);
        titleComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(4).create());
        titleComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        createTitleArea(titleComposite);

        Label label = new Label(mainComposite, SWT.HORIZONTAL | SWT.SEPARATOR);
        label.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        Composite contentComposite = new Composite(mainComposite, SWT.NONE);
        contentComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        contentComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        createContentArea(contentComposite);
        addWindowActivationHelper(contentComposite);

        return mainComposite;
    }

    @Override
    public boolean close() {
        existingNotifications.remove(getShell());
        return super.close();
    }

    public void closeFade() {
        existingNotifications.remove(getShell());
        super.closeFade();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.notifications.AbstractNotificationPopup#addWindowActivationHelper(org.eclipse.swt.widgets.Control)
     */
    @Override
    protected void addWindowActivationHelper(Control control) {
        // reset cursor in case it was modified (for title)
        control.setCursor(getShell().getDisplay().getSystemCursor(SWT.DEFAULT));
        super.addWindowActivationHelper(control);
    }

}
