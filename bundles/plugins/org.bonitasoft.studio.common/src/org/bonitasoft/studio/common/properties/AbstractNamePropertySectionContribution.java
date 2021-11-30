/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
import org.bonitasoft.studio.common.widgets.GTKStyleHandler;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.MessageFlow;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.TextAnnotation;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyComposite;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyTitle;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Aurelien Pupier
 */
@SuppressWarnings("restriction")
public abstract class AbstractNamePropertySectionContribution implements IExtensibleGridPropertySectionContribution {

    protected Text text;
    protected Element element;
    protected TransactionalEditingDomain editingDomain;
    protected ISelection selection;
    protected TabbedPropertySheetPage tabbedPropertySheetPage;
    protected EMFDataBindingContext context;

    public AbstractNamePropertySectionContribution(final TabbedPropertySheetPage tabbedPropertySheetPage) {
        this.tabbedPropertySheetPage = tabbedPropertySheetPage;
    }

    /** Create the Data binding */
    protected abstract void createBinding(EMFDataBindingContext context);

    @Override
    public boolean isRelevantFor(final EObject eObject) {
        return eObject instanceof Element && !(eObject instanceof MessageFlow) && !(eObject instanceof TextAnnotation);
    }

    @Override
    public void refresh() {

    }


    @Override
    public void setEditingDomain(final TransactionalEditingDomain editingDomain) {
        this.editingDomain = editingDomain;

    }

    /**
     * Update the title of the details view.
     */
    protected void updatePropertyTabTitle() {
        if (text != null && !text.isDisposed()) {
            final TabbedPropertyComposite tabbedPropertyComposite = (TabbedPropertyComposite) tabbedPropertySheetPage.getControl();
            final TabbedPropertyTitle title = tabbedPropertyComposite.getTitle();
            if (title != null) {
                final Image image = tabbedPropertySheetPage.getTitleImage(selection);
                title.setTitle(text.getText(), image);
            }
        }
    }

    @Override
    public void createControl(final Composite composite, final TabbedPropertySheetWidgetFactory widgetFactory,
            final ExtensibleGridPropertySection page) {

        context = new EMFDataBindingContext();

        Composite container = widgetFactory.createPlainComposite(composite,SWT.NONE);
        container.setLayout(GridLayoutFactory.fillDefaults().extendedMargins(1, 1, 3, 3).numColumns(useEditButton() ? 2 : 1).create());
        container.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.TOP).grab(true, true).create());
       

        text = new Text(container, GTKStyleHandler.removeBorderFlag(SWT.BORDER));
        if (!GTKStyleHandler.isGTK3()) {
            widgetFactory.adapt(text, true, true);
        }
        text.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).hint(250, SWT.DEFAULT).create());
        text.setText(element.getName());
        if (useEditButton()) {
            text.setEnabled(false);
        }
        if (!(element instanceof SequenceFlow)) {

            if (useEditButton()) {
                final Button editDiagramNameButton = widgetFactory.createButton(container, Messages.edit, SWT.FLAT);
                editDiagramNameButton.addSelectionListener(new SelectionAdapter() {

                    @Override
                    public void widgetSelected(final SelectionEvent e) {
                        editProcessNameAndVersion();
                    }
                });
            }

        }
        createBinding(context);
        updatePropertyTabTitle();
    }

    private boolean useEditButton() {
        return element instanceof MainProcess || element instanceof Pool;
    }

    protected void editProcessNameAndVersion() {
        //TO IMPLEMENT IN SUBCLASS
    }

    @Override
    public void dispose() {
        selection = null;
        if (context != null) {
            context.dispose();
        }
    }

}
