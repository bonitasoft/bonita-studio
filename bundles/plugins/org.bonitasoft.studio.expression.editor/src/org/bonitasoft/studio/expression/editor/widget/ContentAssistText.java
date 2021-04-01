/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.expression.editor.widget;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.expression.editor.ExpressionEditorPlugin;
import org.bonitasoft.studio.expression.editor.autocompletion.AutoCompletionField;
import org.bonitasoft.studio.expression.editor.autocompletion.IBonitaContentProposalListener2;
import org.bonitasoft.studio.expression.editor.autocompletion.IExpressionProposalLabelProvider;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.PreferenceUtil;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

/**
 * @author Romain Bioteau
 */
public class ContentAssistText extends Composite implements SWTBotConstants, ISelectionProvider {

    private Control textControl;
    private final AutoCompletionField autoCompletion;
    private boolean drawBorder = true;
    private final ToolBar tb;
    private final List<IBonitaContentProposalListener2> contentAssistListerners = new ArrayList<>();
    private final List<ISelectionChangedListener> listeners = new ArrayList<>();
    private ISelection selection;
    private ToolItem eraseItem;
    private ToolItem showProposalsItem;
    private boolean isReadOnly;
    private CLabel typeIconControl;
    private ToolItem editToolItem;

    public ContentAssistText(final Composite parent,
            final IExpressionProposalLabelProvider contentProposalLabelProvider,
            boolean withEdit,
            int style) {
        super(parent, SWT.NONE);
        Point margins = new Point(1, 1);
        if ((style & SWT.BORDER) == 0) {
            drawBorder = false;
            margins = new Point(0, 0);
        } else {
            style = style ^ SWT.BORDER;
        }
        if ((style & SWT.READ_ONLY) != 0) {
            style = style ^ SWT.READ_ONLY;
            isReadOnly = true;
        }
        setLayout(GridLayoutFactory.fillDefaults().numColumns(drawBorder ? 4 : 2)
                .margins(margins)
                .spacing(0, 0)
                .create());
        setBackground(PreferenceUtil.isDarkTheme()
                ? parent.getBackground()
                : Display.getDefault().getSystemColor(SWT.COLOR_WHITE));

        if (drawBorder) {
            typeIconControl = new CLabel(this, SWT.NONE | SWT.NO_FOCUS);
            typeIconControl.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
            typeIconControl.setAlignment(SWT.CENTER);

            new Label(this, SWT.SEPARATOR).setLayoutData(GridDataFactory.fillDefaults()
                    .grab(false, false)
                    .hint(1, 1)
                    .create());
        }
        if (!isReadOnly) {
            textControl = new Text(this, style | SWT.SINGLE);
            textControl.setBackground(getBackground());
            textControl.setLayoutData(GridDataFactory.fillDefaults()
                    .align(SWT.FILL, isGTK() ? SWT.FILL : SWT.CENTER)
                    .grab(true, false).create());
        } else {
            textControl = new Label(this, SWT.NONE);
            textControl.setBackground(getBackground());
            textControl.setLayoutData(GridDataFactory.fillDefaults()
                    .align(SWT.FILL, SWT.CENTER)
                    .indent(3, 0)
                    .grab(true, false).create());
        }
        textControl.addTraverseListener(e -> {
            if (e.detail == SWT.TRAVERSE_TAB_NEXT || e.detail == SWT.TRAVERSE_TAB_PREVIOUS) {
                e.doit = true;
            }
        });
        textControl.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(final FocusEvent e) {
                if (textControl.equals(e.widget)) {
                    textControl.getDisplay().asyncExec(() -> {
                        if (!ContentAssistText.this.isDisposed()) {
                            ContentAssistText.this.redraw();
                        }
                    });
                }
            }

            @Override
            public void focusGained(final FocusEvent e) {
                if (textControl.equals(e.widget)) {
                    textControl.getDisplay().asyncExec(() -> {
                        if (!ContentAssistText.this.isDisposed()) {
                            ContentAssistText.this.redraw();
                        }
                    });
                }
            }
        });
        /* Data for test purpose */
        textControl.setData(SWTBOT_WIDGET_ID_KEY, SWTBOT_ID_EXPRESSIONVIEWER_TEXT);
        tb = new ToolBar(this, SWT.FLAT | SWT.NO_FOCUS);
        tb.setBackground(PreferenceUtil.isDarkTheme()
                ? parent.getBackground()
                : Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
        // grab vertical true leads to a weird behavior on macos (icon are not visible). 
        tb.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(false, !isMACOSX()).create());
        eraseItem = createEraseToolItem(tb);
        eraseItem.setEnabled(false);
        showProposalsItem = new ToolItem(tb, SWT.FLAT | SWT.NO_FOCUS);
        showProposalsItem.setData(SWTBOT_WIDGET_ID_KEY, SWTBOT_ID_EXPRESSIONVIEWER_DROPDOWN);
        showProposalsItem.setImage(Pics.getImage("arrow_down.png"));
        showProposalsItem.setEnabled(true);
        showProposalsItem.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                setFocus();
                BusyIndicator.showWhile(getShell().getDisplay(), () -> {
                    fireOpenProposalEvent();
                    if (autoCompletion.getContentProposalAdapter().isProposalPopupOpen()) {
                        autoCompletion.getContentProposalAdapter().closeProposalPopup();
                    } else {
                        autoCompletion.getContentProposalAdapter().showProposalPopup();
                    }
                });
            }
        });
        if (withEdit) {
            new ToolItem(tb, SWT.SEPARATOR);
            editToolItem = createEditToolItem(tb);
        }
        addPaintListener(e -> {
            if (drawBorder) {
                paintControlBorder(e);
            }
        });
        autoCompletion = new AutoCompletionField(textControl,
                textControl instanceof Text ? new TextContentAdapter() : new LabelContentAdapter(),
                contentProposalLabelProvider);
    }

    private boolean isMACOSX() {
        return Platform.isRunning() && Objects.equals(Platform.getOS(), Platform.OS_MACOSX);
    }

    private boolean isGTK() {
        return Platform.isRunning() && Platform.getWS().equals("gtk");
    }

    public void addEraseSelectionListener(Listener listener) {
        eraseItem.addListener(SWT.Selection, listener);
    }

    public void setEraseEnabled(boolean enable) {
        eraseItem.setEnabled(enable);
    }

    protected ToolItem createEraseToolItem(final ToolBar tb) {
        ToolItem eraseControl = new ToolItem(tb, SWT.PUSH | SWT.NO_FOCUS);
        eraseControl.setHotImage(Pics.getImage("eraser.png", ExpressionEditorPlugin.getDefault()));
        eraseControl.setImage(Pics.getImage("eraser_muted.png", ExpressionEditorPlugin.getDefault()));
        eraseControl.setToolTipText(Messages.eraseExpression);

        /* For test purpose */
        eraseControl.setData(SWTBOT_WIDGET_ID_KEY, SWTBOT_ID_ERASEBUTTON);
        return eraseControl;
    }

    protected ToolItem createEditToolItem(final ToolBar tb) {
        final ToolItem item = new ToolItem(tb, SWT.PUSH | SWT.NO_FOCUS);
        item.setImage(Pics.getImage(PicsConstants.edit));
        item.setToolTipText(Messages.editAndContinue);
        item.setData(SWTBOT_WIDGET_ID_KEY, SWTBOT_ID_EDITBUTTON);
        return item;
    }

    public void addEditListener(Listener editListener) {
        if (editToolItem != null) {
            editToolItem.addListener(SWT.Selection, editListener);
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        setProposalEnabled(enabled);
        if (textControl != null) {
            textControl.setEnabled(enabled);
        }
        if (eraseItem != null) {
            eraseItem.setEnabled(enabled);
        }
        if (typeIconControl != null) {
            typeIconControl.setEnabled(enabled);
        }
    }

    public void setProposalEnabled(final boolean proposalEnabled) {
        showProposalsItem.setEnabled(proposalEnabled);
    }

    protected void fireOpenProposalEvent() {
        for (final IBonitaContentProposalListener2 listener : contentAssistListerners) {
            listener.proposalPopupOpened(autoCompletion.getContentProposalAdapter());
        }
    }

    protected void paintControlBorder(final PaintEvent e) {
        final GC gc = e.gc;
        final Display display = e.display;
        if (display != null && gc != null && !gc.isDisposed()) {
            final Control focused = display.getFocusControl();
            final GC parentGC = gc;
            parentGC.setAdvanced(true);
            final Rectangle r = ContentAssistText.this.getBounds();
            if (focused == null || focused.getParent() != null && !focused.getParent().equals(ContentAssistText.this)) {
                parentGC.setForeground(display.getSystemColor(SWT.COLOR_GRAY));
            } else {
                parentGC.setForeground(display.getSystemColor(SWT.COLOR_WIDGET_BORDER));
            }
            parentGC.setLineWidth(2);
            parentGC.drawRectangle(0, 0, r.width, r.height);
        }
    }

    public Control getTextControl() {
        return textControl;
    }

    public AutoCompletionField getAutocompletion() {
        return autoCompletion;
    }

    public ToolBar getToolbar() {
        return tb;
    }

    public void addContentAssistListener(final IBonitaContentProposalListener2 listener) {
        contentAssistListerners.add(listener);
    }

    @Override
    public void addSelectionChangedListener(final ISelectionChangedListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    @Override
    public void removeSelectionChangedListener(final ISelectionChangedListener listener) {
        if (listeners.contains(listener)) {
            listeners.remove(listener);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ISelectionProvider#getSelection()
     */
    @Override
    public ISelection getSelection() {
        return selection;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ISelectionProvider#setSelection(org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setSelection(ISelection selection) {
        this.selection = selection;
        getAutocompletion().setSelection(selection);
    }

    public ToolItem getEraseControl() {
        return eraseItem;
    }

    public void setImage(Image image) {
        if (typeIconControl != null) {
            typeIconControl.setImage(image);
            typeIconControl.getParent().layout();
        }
    }

    public void setImageToolTipText(String desc) {
        if (typeIconControl != null) {
            typeIconControl.setToolTipText(desc);
        }
    }

}
