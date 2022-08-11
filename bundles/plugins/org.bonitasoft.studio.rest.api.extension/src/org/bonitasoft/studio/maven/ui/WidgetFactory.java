/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.ui;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

@Creatable
public class WidgetFactory {

    public Text newText(final Composite parent, final int style) {
        final Text text = new Text(parent, style);
        text.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).indent(10, 0).create());
        return text;
    }

    public Text newText(final Composite parent) {
        return newText(parent, SWT.BORDER);
    }

    public Label newLabel(final Composite parent, final String text) {
        return newLabel(parent, text, SWT.NONE);
    }

    public Label newLabel(final Composite parent, final String text, final int style) {
        final Label label = new Label(parent, style);
        label.setText(text);
        label.setLayoutData(GridDataFactory.fillDefaults().align(SWT.RIGHT, SWT.CENTER).create());
        return label;
    }

    public TableViewer newTableViewer(final Composite parent, final String id) {
        final TableViewer viewer = new TableViewer(parent, SWT.BORDER | SWT.V_SCROLL | SWT.FULL_SELECTION);
        viewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        viewer.getControl().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, id);
        return viewer;
    }

    public Button newButton(final Composite parent, final String text) {
        final Button button = new Button(parent, SWT.FLAT);
        button.setLayoutData(
                GridDataFactory.fillDefaults().align(SWT.RIGHT, SWT.FILL).minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).grab(true, false).create());
        button.setText(text);
        return button;
    }

    public Button newCheckbox(final Composite parent, final String text) {
        final Button button = new Button(parent, SWT.CHECK);
        button.setLayoutData(GridDataFactory.swtDefaults().align(SWT.LEFT, SWT.FILL).grab(false, false).create());
        button.setText(text);
        return button;
    }

    public ControlDecoration createHintDecorator(final Text nameText, final int position, final String text) {
        final ControlDecoration hint = new ControlDecoration(nameText, position);
        hint.setImage(Pics.getImage(PicsConstants.hint));
        hint.setDescriptionText(text);
        hint.show();
        return hint;
    }

}
