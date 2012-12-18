/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.form.sections.appearance;

import org.bonitasoft.studio.common.exporter.ExporterTools;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.form.ListFormField;
import org.bonitasoft.studio.model.form.PasswordFormField;
import org.bonitasoft.studio.model.form.TextAreaFormField;
import org.bonitasoft.studio.model.form.TextFormField;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * @author Baptiste Mesta
 * 
 */
public class FieldAppearanceSection extends AppearancePropertySection {

    private Composite lengthComposite;
    private Composite heightComposite;
    private Group attGroup;
    private Label label;

    /*
     * (non-Javadoc)
     * 
     * @see org.bonitasoft.studio.properties.form.sections.appearance.
     * AppearancePropertySection
     * #createControls(org.eclipse.swt.widgets.Composite,
     * org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
     */
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
        super.createControls(parent, aTabbedPropertySheetPage);
        doCreateControls(parent);
    }

    /**
     * @param parent
     */
    protected void doCreateControls(Composite parent) {
        tabInputContents = getWidgetFactory().createComposite(parent, SWT.NONE);

        tabInputContents.setLayout(new GridLayout(2, true));

        addSizeGroup(tabInputContents, ExporterTools.PREFIX_INPUT, 1, 1);
        addCustomStyleGroup(tabInputContents, ExporterTools.PREFIX_INPUT, 1, 2);
        addFontGroup(tabInputContents, ExporterTools.PREFIX_INPUT, 1, 1);
        addTextGroup(tabInputContents, ExporterTools.PREFIX_INPUT, 1, 1);

        addInputAttrGroup(tabInputContents, ExporterTools.PREFIX_INPUT, 1, 1);
    }

    protected void addInputAttrGroup(Composite contents, String prefix, int hs, int vs) {

        boolean length = haveLengthAttr();

        boolean height = haveHeightAttr();

        attGroup = getWidgetFactory().createGroup(contents, "");

        GridData gd = new GridData(SWT.FILL, SWT.FILL, false, false);
        gd.horizontalSpan = hs;
        gd.verticalSpan = vs;
        gd.widthHint = LEFT_WIDTH;
        attGroup.setLayoutData(gd);
        attGroup.setLayout(new GridLayout(1, false));

        lengthComposite = getWidgetFactory().createComposite(attGroup);
        lengthComposite.setLayout(new GridLayout(2, false));
        lengthComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        heightComposite = getWidgetFactory().createComposite(attGroup);
        heightComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        heightComposite.setLayout(new GridLayout(2, false));


        String textLabel = Messages.AppearancePropertySection_maxLength ;
        if(getEObject() instanceof TextAreaFormField){
            textLabel = Messages.AppearancePropertySection_maxLengthForTextArea;
        }
        label = getWidgetFactory().createLabel(lengthComposite, textLabel);
        label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));

        Text text = getWidgetFactory().createText(lengthComposite, "", SWT.SINGLE | SWT.BORDER); //$NON-NLS-1$
        text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        text.setToolTipText(Messages.AppearancePropertySection_maxLength_tooltip);
        widgets_others.put(prefix + ExporterTools.ATTR_MAXLENGTH, text);
        final Label heightLabel = getWidgetFactory().createLabel(heightComposite, Messages.AppearancePropertySection_maxHeight);
        heightLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));

        text = getWidgetFactory().createText(heightComposite, "", SWT.SINGLE | SWT.BORDER); //$NON-NLS-1$
        text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        text.setToolTipText(Messages.AppearancePropertySection_maxHeight_tooltip);
        widgets_others.put(prefix + ExporterTools.ATTR_MAXHEIGHT, text);

        lengthComposite.setVisible(length);
        heightComposite.setVisible(height);
        attGroup.setVisible(length || height);

    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.properties.form.sections.appearance.AppearancePropertySection#setInput(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setInput(IWorkbenchPart part, ISelection selection) {
        super.setInput(part, selection);

        if(label != null){
            boolean length = haveLengthAttr();
            boolean height = haveHeightAttr();


            String textLabel = Messages.AppearancePropertySection_maxLength ;
            if(getEObject() instanceof TextAreaFormField){
                textLabel = Messages.AppearancePropertySection_maxLengthForTextArea;
            }
            label.setText(textLabel);

            if(lengthComposite != null) {
                lengthComposite.setVisible(length);
                lengthComposite.layout();
            }
            if(heightComposite != null) {
                heightComposite.setVisible(height);
                heightComposite.layout();
            }
            if(attGroup != null) {
                attGroup.setVisible(length || height);
                attGroup.layout();
            }
        }
    }

    protected boolean haveHeightAttr() {
        return getWidget() instanceof ListFormField || getWidget() instanceof TextAreaFormField;
    }

    protected boolean haveLengthAttr() {
        return (getWidget() instanceof PasswordFormField || getWidget() instanceof TextAreaFormField || getWidget() instanceof TextFormField);
    }




}
