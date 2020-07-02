/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.properties;

import org.bonitasoft.studio.common.Messages;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.google.common.base.Objects;
import com.google.common.base.Strings;

public class Well extends Composite {

    private static final int ARC_SIZE = 15;
    static Color infoSeprator = new Color(Display.getDefault(), 51, 102, 153);
    static Color infoBackground = new Color(Display.getDefault(), 229, 245, 255);

    static Color warningSeprator = new Color(Display.getDefault(), 120, 110, 30);
    static Color warningBackground = new Color(Display.getDefault(), 255, 255, 204);

    static Color errorSeprator = new Color(Display.getDefault(), 204, 0, 0);
    static Color errorBackground = new Color(Display.getDefault(), 255, 229, 229);

    private final Link label;
    private Link moreInformationLink;
    private IObservableValue<String> labelTextObservable;
    private WritableValue<Integer> severityObservable;

    /**
     * Display the given text in colored frame starting with a bold separator
     * The displayed color depends on the severity of the message (info, warning or error)
     *
     * @param parent
     * @param text
     * @param toolkit
     * @param severity
     * @param help
     */
    public Well(final Composite parent, final String text, final FormToolkit toolkit, final int severity) {
        this(parent, text, null, toolkit, severity);
    }

    public Well(final Composite parent, final String text, final String moreDetails, final FormToolkit toolkit,
            final int severity) {
        super(parent, SWT.NONE);
        severityObservable = new WritableValue<>(severity, Integer.class);
        setLayout(GridLayoutFactory.fillDefaults().extendedMargins(10, 10, 5, 8).spacing(0, 3).create());
        setLayoutData(GridDataFactory.fillDefaults().create());
        addPaintListener(new PaintListener() {

            @Override
            public void paintControl(final PaintEvent e) {
                final Control source = (Control) e.getSource();
                final Rectangle bounds = source.getBounds();
                final Rectangle borderBounds = new Rectangle(0, 0, bounds.width - 2, bounds.height - 2);
                e.gc.setAntialias(SWT.ON);
                e.gc.setBackground(backgroundColor(severityObservable.getValue()));
                e.gc.fillRoundRectangle(0, 0, bounds.width - 1, bounds.height - 1, ARC_SIZE, ARC_SIZE);
                e.gc.setForeground(separatorColor(severityObservable.getValue()));
                e.gc.setLineAttributes(new LineAttributes(1, SWT.CAP_ROUND, SWT.JOIN_ROUND));
                e.gc.setLineWidth(1);
                e.gc.drawRoundRectangle(0, 0, borderBounds.width, borderBounds.height, ARC_SIZE, ARC_SIZE);
            }

        });

        label = new Link(this, SWT.WRAP);
        if (text != null) {
            label.setText(text);
        }

        label.addPaintListener(e -> {
            Control source = (Control) e.getSource();
            Rectangle bounds = source.getBounds();
            e.gc.setBackground(backgroundColor(severityObservable.getValue()));
            e.gc.fillRectangle(0, 0, bounds.width, bounds.height);
            e.gc.setForeground(separatorColor(severityObservable.getValue()));
            e.gc.drawText(labelTextObservable.getValue(), 0, 2);
        });
        label.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        labelTextObservable = WidgetProperties.text().observe(label);
        labelTextObservable.addValueChangeListener(e -> {
            getParent().getParent().layout(true, true);
        });

        severityObservable.addValueChangeListener(e -> {
            getParent().getParent().layout(true, true);
        });

        if (!Strings.isNullOrEmpty(moreDetails)) {
            moreInformationLink = new Link(this, SWT.WRAP);
            moreInformationLink.setText("<a>" + Messages.moreDetails + "</a>");
            moreInformationLink.setBackground(backgroundColor(severityObservable.getValue()));
            moreInformationLink.setForeground(separatorColor(severityObservable.getValue()));
            moreInformationLink.setLayoutData(
                    GridDataFactory.fillDefaults().align(SWT.RIGHT, SWT.BOTTOM).grab(true, false).create());
            final Shell tootltipShell = new Shell(getDisplay());
            final org.eclipse.swt.widgets.ToolTip defaultToolTip = new org.eclipse.swt.widgets.ToolTip(tootltipShell,
                    SWT.BALLOON | SWT.ICON_INFORMATION);
            defaultToolTip.setMessage(moreDetails);
            defaultToolTip.setAutoHide(false);
            final Listener mouseUpFilter = mouseUpFilter(defaultToolTip);
            moreInformationLink.addSelectionListener(new SelectionAdapter() {

                /*
                 * (non-Javadoc)
                 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
                 */
                @Override
                public void widgetSelected(final SelectionEvent e) {
                    if (!defaultToolTip.isVisible()) {
                        final Point point = moreInformationLink.toDisplay(moreInformationLink.getLocation());
                        final Rectangle location = moreInformationLink.getBounds();
                        defaultToolTip.setLocation(point.x - location.x + location.width,
                                point.y - location.y + location.height);
                        defaultToolTip.setVisible(true);
                        getDisplay().addFilter(SWT.MouseUp, mouseUpFilter);
                    }
                }

            });

            parent.addControlListener(new ControlAdapter() {

                @Override
                public void controlResized(final ControlEvent e) {
                    e.display.asyncExec(new Runnable() {

                        @Override
                        public void run() {
                            if (!Well.this.isDisposed()) {
                                Well.this.redraw();
                            }
                            defaultToolTip.setVisible(false);
                            getDisplay().removeFilter(SWT.MouseUp, mouseUpFilter);
                        }
                    });
                    defaultToolTip.setVisible(false);
                    getDisplay().removeFilter(SWT.MouseUp, mouseUpFilter);
                }

                @Override
                public void controlMoved(final ControlEvent e) {
                    e.display.asyncExec(new Runnable() {

                        @Override
                        public void run() {
                            if (!Well.this.isDisposed()) {
                                Well.this.redraw();
                            }
                            defaultToolTip.setVisible(false);
                            getDisplay().removeFilter(SWT.MouseUp, mouseUpFilter);
                        }
                    });

                }

            });
        }
    }

    private Listener mouseUpFilter(final org.eclipse.swt.widgets.ToolTip defaultToolTip) {
        return new Listener() {

            @Override
            public void handleEvent(final Event event) {
                if (!Objects.equal(event.widget, moreInformationLink)) {
                    defaultToolTip.setVisible(false);
                    getDisplay().removeFilter(SWT.MouseUp, this);
                }
            }

        };
    }

    private Color separatorColor(final int style) {
        switch (style) {
            case IStatus.WARNING:
                return warningSeprator;
            case IStatus.ERROR:
                return errorSeprator;
            case IStatus.INFO:
            default:
                return infoSeprator;
        }
    }

    private Color backgroundColor(final int style) {
        switch (style) {
            case IStatus.WARNING:
                return warningBackground;
            case IStatus.ERROR:
                return errorBackground;
            case IStatus.INFO:
            default:
                return infoBackground;
        }
    }

    public void setText(final String text) {
        label.setText(text);
    }

    public String getText() {
        return label.getText();
    }

    public void addSelectionListener(final SelectionListener listener) {
        label.addSelectionListener(listener);
    }

    public IObservableValue<String> labelObservable() {
        return labelTextObservable;
    }

    public WritableValue<Integer> severityObservable() {
        return severityObservable;
    }

}
