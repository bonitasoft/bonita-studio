/*******************************************************************************
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.ui.notification;

import java.util.Optional;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.window.Window;
import org.eclipse.mylyn.commons.ui.dialogs.AbstractNotificationPopup;
import org.eclipse.mylyn.internal.commons.ui.AnimationUtil;
import org.eclipse.mylyn.internal.commons.ui.AnimationUtil.IFadeListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class BonitaNotificationPopup extends AbstractNotificationPopup {

    private static final int MAX_WIDTH = 400;
    private static final int MIN_HEIGHT = 100;
    private static final int PADDING_EDGE = 10;

    private String title;
    private String content;
    private Optional<Listener> selectionListener;

    public BonitaNotificationPopup(Display display, String title, String content,
            Optional<Listener> selectionListener) {
        super(display);
        this.title = title;
        this.content = content;
        this.selectionListener = selectionListener;
    }

    @Override
    protected void createContentArea(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        composite.setLayout(GridLayoutFactory.fillDefaults().create());

        Link link = new Link(composite, SWT.WRAP);
        link.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        link.setText(content);
        selectionListener.ifPresent(listener -> link.addListener(SWT.Selection, listener));
    }

    @Override
    protected String getPopupShellTitle() {
        return title;
    }

    @Override
    protected void initializeBounds() {
        Rectangle clArea = getClientArea();
        Point initialSize = getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT);
        int height = Math.max(initialSize.y, MIN_HEIGHT);
        int width = Math.min(initialSize.x, MAX_WIDTH);

        Point size = new Point(width, height);
        getShell().setLocation(clArea.width + clArea.x - size.x - PADDING_EDGE, clArea.height + clArea.y - size.y
                - PADDING_EDGE);
        getShell().setSize(size);
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

    @Override
    public int open() {
        if (getShell() == null || getShell().isDisposed()) {
            create();
        }

        constrainShellSize();
        getShell().setLocation(fixupDisplayBounds(getShell().getSize(), getShell().getLocation()));

        if (isFadingEnabled()) {
            getShell().setAlpha(0);
        }
        getShell().setVisible(true);
        AnimationUtil.fadeIn(getShell(), new IFadeListener() {

            @Override
            public void faded(Shell shell, int alpha) {
                if (shell.isDisposed()) {
                    return;
                }

                if (alpha == 255) {
                    scheduleAutoClose();
                }
            }
        });

        return Window.OK;
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

}
