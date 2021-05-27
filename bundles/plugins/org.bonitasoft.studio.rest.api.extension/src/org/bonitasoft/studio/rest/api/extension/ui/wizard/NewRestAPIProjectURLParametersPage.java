/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.ui.wizard;

import static com.google.common.collect.Sets.newHashSet;
import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.updateValueStrategy;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.model.RestAPIExtensionArchetypeConfiguration;
import org.bonitasoft.studio.maven.ui.WidgetFactory;
import org.bonitasoft.studio.maven.ui.wizard.control.StringListViewer;
import org.bonitasoft.studio.maven.ui.wizard.validator.AllJavaIdentifierValidator;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class NewRestAPIProjectURLParametersPage extends WizardPage {

    private static final int WIDTH_HINT = 400;
    private final RestAPIExtensionArchetypeConfiguration configuration;
    private final WidgetFactory widgetFactory;

    public NewRestAPIProjectURLParametersPage(final WidgetFactory widgetFactory, final RestAPIExtensionArchetypeConfiguration configuration) {
        super(NewRestAPIProjectURLParametersPage.class.getName());
        this.configuration = configuration;
        this.widgetFactory = widgetFactory;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

        final DataBindingContext context = new DataBindingContext();

        final Label description = widgetFactory.newLabel(mainComposite, Messages.bind(Messages.urlParametersDesciption,org.bonitasoft.studio.common.Messages.bonitaName), SWT.WRAP);
        description.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(15, 5).hint(WIDTH_HINT, SWT.DEFAULT).span(2, 1).create());

        final Composite buttonComposite = new Composite(mainComposite, SWT.NONE);
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).spacing(3, 3).create());
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults().align(SWT.RIGHT, SWT.FILL).grab(false, true).create());

        final Button addButton = widgetFactory.newButton(buttonComposite, Messages.add);
        final Button removeButton = widgetFactory.newButton(buttonComposite, Messages.remove);

        final IObservableList input = PojoProperties.list("urlParameters").observe(configuration);

        final StringListViewer urlParametersViewer = new StringListViewer(mainComposite, Messages.urlParameters, "urlParameters", widgetFactory);
        urlParametersViewer.setInput(input);

        context.addValidationStatusProvider(new AllJavaIdentifierValidator(input));

        addButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                final String[] existingPermissions = (String[]) input.toArray(new String[input.size()]);
                final String newName = NamingUtils.generateNewName(newHashSet(existingPermissions), "newParameter", 1);
                input.add(newName);
                urlParametersViewer.editElement(newName);
            }
        });

        removeButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                input.removeAll(urlParametersViewer.getSelection());
            }
        });

        final TableViewer viewer = urlParametersViewer.getViewer();
        context.bindValue(WidgetProperties.enabled().observe(removeButton), 
                ViewersObservables.observeSingleSelection(viewer), 
                null,
                updateValueStrategy().withConverter(convertToEnablement()).create());

        WizardPageSupport.create(this, context);
        setControl(mainComposite);
    }

    private IConverter convertToEnablement() {
        return new Converter(Object.class, Boolean.class) {

            @Override
            public Object convert(Object fromObject) {
                return fromObject != null;
            }
        };
    }

}
