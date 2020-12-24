/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation
 * version 2.1 of the License.
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License along with this
 * program; if not, write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth
 * Floor, Boston, MA 02110-1301, USA.
 **/
package org.bonitasoft.studio.identity.actors.ui.wizard.connector;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.ActiveOrganizationProvider;
import org.bonitasoft.studio.connector.model.definition.Checkbox;
import org.bonitasoft.studio.connector.model.definition.Page;
import org.bonitasoft.studio.connector.model.definition.Text;
import org.bonitasoft.studio.connector.model.definition.wizard.AbstractConnectorConfigurationWizardPage;
import org.bonitasoft.studio.connector.model.definition.wizard.PageComponentSwitchBuilder;
import org.bonitasoft.studio.expression.editor.viewer.CheckBoxExpressionViewer;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.repository.OrganizationRepositoryStore;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

/**
 * @author Elias Ricken de Medeiros
 */
public class CustomUserInfoConnectorConfigurationWizardPage extends AbstractConnectorConfigurationWizardPage {

    private static final int LABEL_WIDTH = 250;
    private final ActiveOrganizationProvider activeOrganizationProvider;

    public CustomUserInfoConnectorConfigurationWizardPage() {
        super(CustomUserInfoConnectorConfigurationWizardPage.class.getName());
        activeOrganizationProvider = new ActiveOrganizationProvider();
    }

    @Override
    protected Control doCreateControl(final Composite parent, final EMFDataBindingContext context) {
        // get inputs
        final Page page = getPage();
        final Text cuiNameInput = (Text) page.getWidget().get(0);
        final Text cuiValueInput = (Text) page.getWidget().get(1);
        final Checkbox cuiPartialMatchInput = (Checkbox) page.getWidget().get(2);
        final Checkbox automaticAssignInput = (Checkbox) page.getWidget().get(3);

        return buildUserFilterComposite(parent, context, cuiNameInput, cuiValueInput, cuiPartialMatchInput, automaticAssignInput);
    }

    private Control buildUserFilterComposite(final Composite parent, final EMFDataBindingContext context, final Text cuiNameInput, final Text cuiValueInput,
            final Checkbox cuiPartialMatchInput, final Checkbox automaticAssignInput) {
        final Composite mainComposite = createMainComposite(parent);
        addMessages(mainComposite);

        final Composite pageComposite = createInputsComposite(mainComposite);
        final Composite cuiComposite = createComposite(pageComposite, 2, 20);

        final PageComponentSwitchBuilder componentSwitchBuilder = new PageComponentSwitchBuilder(getElementContainer(), getDefinition(),
                getConfiguration(), context, getMessageProvider(), getExpressionTypeFilter(), LABEL_WIDTH);
        createCustomExpressionViewerWithCustomInfo(componentSwitchBuilder, cuiComposite, cuiNameInput);

        final Composite valueComposite = createComposite(cuiComposite, 2, 0);
        final ExpressionViewer valueViewer = componentSwitchBuilder.createTextControl(valueComposite, cuiValueInput);
        valueViewer.setMessage(Messages.typeValueToFilter);
        componentSwitchBuilder.createCheckboxControl(valueComposite, cuiPartialMatchInput);

        final CheckBoxExpressionViewer viewer = componentSwitchBuilder.createCheckboxControl(pageComposite, automaticAssignInput);
        viewer.setMessage(Messages.assignOnlyIfOneUser);

        return mainComposite;
    }

    private Composite createInputsComposite(final Composite mainComposite) {
        final Composite pageComposite = new Composite(mainComposite, SWT.NONE);
        pageComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).spacing(3, 60).create());
        pageComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        return pageComposite;
    }

    private Composite createMainComposite(final Composite parent) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 20).create());
        return mainComposite;
    }

    private void addMessages(final Composite mainComposite) {
        addLabel(mainComposite, Messages.cuiManagementMsg);
        addLabel(mainComposite, Messages.cuiFilterActorRelatedMsg);
    }

    private void createCustomExpressionViewerWithCustomInfo(final PageComponentSwitchBuilder componentSwitchBuilder, final Composite composite,
            final Text object) {
        final OrganizationRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(OrganizationRepositoryStore.class);
        final String fileName = activeOrganizationProvider.getActiveOrganization() + "." + OrganizationRepositoryStore.ORGANIZATION_EXT;
        componentSwitchBuilder.createTextControl(composite, object, new CustomUserInfoNameExpressionProvider(store, fileName),
                new CustomUserInfoLabelProvider());
    }

    private void addLabel(final Composite composite, final String label) {
        final Label fieldLabel = new Label(composite, SWT.WRAP);
        fieldLabel.setText(label);
        fieldLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
    }

    private Composite createComposite(final Composite parent, final int nbColumns, final int vSpacing) {
        final Composite composite = new Composite(parent, SWT.INHERIT_DEFAULT);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(nbColumns).margins(0, 0).spacing(3, vSpacing).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(false, false).span(2, 1).create());
        return composite;
    }

}
