/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.ui.wizard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.model.RestAPIExtensionArchetypeConfiguration;
import org.bonitasoft.studio.maven.ui.WidgetFactory;
import org.bonitasoft.studio.rest.api.extension.core.RestAPIAddressResolver;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionRepositoryStore;
import org.bonitasoft.studio.swt.WidgetFinder;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NewRestAPIProjectAdvancedConfigurationPageTest {

    @Rule
    public RealmWithDisplay displayRule = new RealmWithDisplay();

    private final WidgetFinder widgetFinder = new WidgetFinder();

    @Mock
    private RestAPIExtensionRepositoryStore repositoryStore;

    @Mock
    private RestAPIAddressResolver addressResolver;

    @Test
    public void should_bind_pathTemplate_control_with_configuration() throws Exception {
        final RestAPIExtensionArchetypeConfiguration configuration = RestAPIExtensionArchetypeConfiguration.defaultArchetypeConfiguration();

        final NewRestAPIProjectAdvancedConfigurationPage page = new NewRestAPIProjectAdvancedConfigurationPage(new WidgetFactory(), configuration,
                repositoryStore, addressResolver);
        page.setWizard(wizardWithContainer());

        page.createControl(displayRule.createComposite());

        final Text groupIdText = widgetFinder.<Text> withLabel(displayRule.getShell(), Messages.pathTemplate);
        assertThat(groupIdText).isNotNull();
        assertThat(groupIdText.getText()).isEqualTo(configuration.getPathTemplate());
        groupIdText.setText("my_path");
        assertThat(configuration.getPathTemplate()).isEqualTo("my_path");
    }


    @Test
    public void should_not_accept_empty_permission_list() throws Exception {
        final RestAPIExtensionArchetypeConfiguration configuration = RestAPIExtensionArchetypeConfiguration.defaultArchetypeConfiguration();

        final NewRestAPIProjectAdvancedConfigurationPage page = new NewRestAPIProjectAdvancedConfigurationPage(new WidgetFactory(), configuration,
                repositoryStore, addressResolver);

        final IObservableList input = new WritableList();
        final MultiValidator validator = page.notEmptyPermissionsValidator(input);
        StatusAssert.assertThat((IStatus) validator.getValidationStatus().getValue()).isNotOK();

        input.add("aPermission");
        StatusAssert.assertThat((IStatus) validator.getValidationStatus().getValue()).isOK();
    }
    
    @Test
    public void should_disable_add_bdm_checkbox_if_no_bdmPackage_in_configuration() throws Exception {
        final RestAPIExtensionArchetypeConfiguration configuration = RestAPIExtensionArchetypeConfiguration.defaultArchetypeConfiguration();

        final NewRestAPIProjectAdvancedConfigurationPage page = new NewRestAPIProjectAdvancedConfigurationPage(new WidgetFactory(), configuration,
                repositoryStore, addressResolver);
        page.setWizard(wizardWithContainer());

        page.createControl(displayRule.createComposite());

        final Button bdmCheckbox = widgetFinder.<Button> withMnemonic(displayRule.getShell(), Messages.addBDMDependencies);
        assertThat(bdmCheckbox).isNotNull();
        assertThat(bdmCheckbox.getSelection()).isFalse();
        assertThat(bdmCheckbox.isEnabled()).isFalse();
    }

    @Test
    public void should_select_add_bdm_checkbox_if_bdmPackage_in_configuration_exists() throws Exception {
        final RestAPIExtensionArchetypeConfiguration configuration = RestAPIExtensionArchetypeConfiguration.defaultArchetypeConfiguration("com.company.model", "1.0.0");

        final NewRestAPIProjectAdvancedConfigurationPage page = new NewRestAPIProjectAdvancedConfigurationPage(new WidgetFactory(), configuration,
                repositoryStore, addressResolver);
        page.setWizard(wizardWithContainer());

        page.createControl(displayRule.createComposite());

        final Button bdmCheckbox = widgetFinder.<Button> withMnemonic(displayRule.getShell(), Messages.addBDMDependencies);
        assertThat(bdmCheckbox).isNotNull();
        assertThat(bdmCheckbox.getSelection()).isTrue();
        assertThat(bdmCheckbox.isEnabled()).isTrue();
    }


    private IWizard wizardWithContainer() {
        final IWizard wizard = mock(IWizard.class);
        final IWizardContainer wizardContainer = mock(IWizardContainer.class);
        when(wizardContainer.getShell()).thenReturn(displayRule.getShell());
        when(wizard.getContainer()).thenReturn(wizardContainer);
        return wizard;
    }
}
