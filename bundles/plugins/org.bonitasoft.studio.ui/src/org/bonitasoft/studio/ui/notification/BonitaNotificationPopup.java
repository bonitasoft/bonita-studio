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

import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.preferences.PreferenceUtil;
import org.bonitasoft.studio.ui.UIPlugin;
import org.bonitasoft.studio.ui.notification.job.CloseJob;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.window.Window;
import org.eclipse.mylyn.commons.ui.CommonImages;
import org.eclipse.mylyn.commons.ui.compatibility.CommonFonts;
import org.eclipse.mylyn.internal.commons.ui.AnimationUtil;
import org.eclipse.mylyn.internal.commons.ui.AnimationUtil.FadeJob;
import org.eclipse.mylyn.internal.commons.ui.AnimationUtil.IFadeListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class BonitaNotificationPopup extends Window {

    private static final int MAX_WIDTH = 400;
    private static final int MIN_HEIGHT = 120;
    private static final int PADDING_EDGE = 10;

    private static final long DEFAULT_DELAY_CLOSE = 8 * 1000;
    private long delayClose = DEFAULT_DELAY_CLOSE;

    private static List<Shell> existingNotifications = new ArrayList<>();

    private String title;
    private String content;
    private Optional<Listener> selectionListener;
    private boolean fadingEnabled;
    private FadeJob fadeJob;
    private Display display;
    private CloseJob closeJob;

    public BonitaNotificationPopup(Display display, String title, String content,
            Optional<Listener> selectionListener) {
        super(new Shell(display));
        setShellStyle(SWT.NO_TRIM | SWT.ON_TOP | SWT.NO_FOCUS | SWT.TOOL);
        this.display = display;
        this.title = title;
        this.content = content;
        this.selectionListener = selectionListener;
        this.closeJob = new CloseJob(this);
    }

    private void createTitleArea(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).create());
        composite.addListener(SWT.MouseUp, this::bringStudioToFront);

        Label iconLabel = new Label(composite, SWT.NONE);
        iconLabel.setLayoutData(GridDataFactory.fillDefaults().span(1, 2).create());
        Image image = PreferenceUtil.isDarkTheme()
                ? UIPlugin.getImage("icons/notification_dark.png")
                : UIPlugin.getImage("icons/notification_light.png");
        iconLabel.setImage(image);
        iconLabel.addListener(SWT.MouseUp, this::bringStudioToFront);

        Label titleLabel = new Label(composite, SWT.NONE);
        titleLabel.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, true).span(1, 2).align(SWT.FILL, SWT.CENTER).create());
        titleLabel.setText(title);
        titleLabel.setFont(CommonFonts.BOLD);
        titleLabel.addListener(SWT.MouseUp, this::bringStudioToFront);

        Label closeButton = new Label(composite, SWT.NONE);
        closeButton.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
        closeButton.setImage(CommonImages.getImage(CommonImages.NOTIFICATION_CLOSE));
        closeButton.addMouseTrackListener(new MouseTrackAdapter() {

            @Override
            public void mouseEnter(MouseEvent e) {
                closeButton.setImage(CommonImages.getImage(CommonImages.NOTIFICATION_CLOSE_HOVER));
            }

            @Override
            public void mouseExit(MouseEvent e) {
                closeButton.setImage(CommonImages.getImage(CommonImages.NOTIFICATION_CLOSE));
            }
        });
        closeButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseUp(MouseEvent e) {
                close();
                setReturnCode(CANCEL);
            }

        });
    }

    private void createContentArea(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        composite.setLayout(GridLayoutFactory.fillDefaults().create());
        composite.addListener(SWT.MouseUp, this::bringStudioToFront);

        Link link = new Link(composite, SWT.WRAP);
        link.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        link.setText(content);
        link.addListener(SWT.MouseUp, this::bringStudioToFront);
        selectionListener.ifPresent(listener -> link.addListener(SWT.Selection, listener));
    }

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
                    closeJob.scheduleAutoClose();
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

    @Override
    protected Control createContents(Composite parent) {
        ((GridLayout) parent.getLayout()).marginWidth = 1;
        ((GridLayout) parent.getLayout()).marginHeight = 1;

        Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().margins(5, 5).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.NOTIFICATION_COMPOSITE);

        Composite titleComposite = new Composite(mainComposite, SWT.NONE);
        titleComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        titleComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        createTitleArea(titleComposite);

        Label label = new Label(mainComposite, SWT.HORIZONTAL | SWT.SEPARATOR);
        label.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        Composite contentComposite = new Composite(mainComposite, SWT.NONE);
        contentComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        contentComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        createContentArea(contentComposite);

        return mainComposite;
    }

    @Override
    public boolean close() {
        existingNotifications.remove(getShell());
        return super.close();
    }

    public void closeFade() {
        existingNotifications.remove(getShell());
        if (fadeJob != null) {
            fadeJob.cancelAndWait(false);
        }
        fadeJob = AnimationUtil.fadeOut(getShell(), new IFadeListener() {

            @Override
            public void faded(Shell shell, int alpha) {
                if (!shell.isDisposed()) {
                    if (alpha == 0) {
                        shell.close();
                    } else if (isMouseOver(shell)) {
                        if (fadeJob != null) {
                            fadeJob.cancelAndWait(false);
                        }
                        fadeJob = AnimationUtil.fastFadeIn(shell, new IFadeListener() {

                            @Override
                            public void faded(Shell shell, int alpha) {
                                if (shell.isDisposed()) {
                                    return;
                                }

                                if (alpha == 255) {
                                    closeJob.scheduleAutoClose();
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    public boolean isMouseOver(Shell shell) {
        if (display.isDisposed()) {
            return false;
        }
        return shell.getBounds().contains(display.getCursorLocation());
    }

    public boolean isFadingEnabled() {
        return fadingEnabled;
    }

    public void setFadingEnabled(boolean fadingEnabled) {
        this.fadingEnabled = fadingEnabled;
    }

    public long getDelayClose() {
        return delayClose;
    }

    public void setDelayClose(long delayClose) {
        this.delayClose = delayClose;
    }

    public Display getDisplay() {
        return display;
    }

    private void bringStudioToFront(Event e) {
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().forceActive();
    }
}
