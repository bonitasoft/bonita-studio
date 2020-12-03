/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.ui.wizard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.maven.MavenProjectConfiguration;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.model.RestAPIExtensionArchetypeConfiguration;
import org.bonitasoft.studio.maven.ui.WidgetFactory;
import org.bonitasoft.studio.swt.WidgetFinder;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.swt.widgets.Text;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NewCustomPageArtifactConfigurationPageTest {

    @Rule
    public RealmWithDisplay displayRule = new RealmWithDisplay();
    @Mock
    private IWorkspace workspace;
    @Mock
    private MavenProjectConfiguration projectImportConfiguration;

    private final WidgetFinder widgetFinder = new WidgetFinder();

    @Before
    public void setUp() throws Exception {
        when(workspace.validateName(anyString(), anyInt())).thenReturn(ValidationStatus.ok());
    }

    @Test
    public void should_bind_groupId_control_with_configuration() throws Exception {
        final RestAPIExtensionArchetypeConfiguration configuration = RestAPIExtensionArchetypeConfiguration
                .defaultArchetypeConfiguration();

        final NewCustomPageArtifactConfigurationPage page = new NewCustomPageArtifactConfigurationPage(
                new WidgetFactory(), configuration,
                projectImportConfiguration, workspace);
        page.setWizard(displayRule.wizardWithContainer());

        page.createControl(displayRule.createComposite());

        final Text groupIdText = widgetFinder.<Text> withLabel(displayRule.getShell(), Messages.groupId);
        assertThat(groupIdText).isNotNull();
        assertThat(groupIdText.getText()).isEqualTo(configuration.getGroupId());
    }

    @Test
    public void should_bind_artifactId_control_with_configuration() throws Exception {
        final RestAPIExtensionArchetypeConfiguration configuration = RestAPIExtensionArchetypeConfiguration
                .defaultArchetypeConfiguration();

        final NewCustomPageArtifactConfigurationPage page = new NewCustomPageArtifactConfigurationPage(
                new WidgetFactory(), configuration,
                projectImportConfiguration, workspace);
        page.setWizard(displayRule.wizardWithContainer());

        page.createControl(displayRule.createComposite());

        final Text artifactIdText = widgetFinder.<Text> withLabel(displayRule.getShell(), Messages.projectName);
        assertThat(artifactIdText).isNotNull();
        assertThat(artifactIdText.getText()).isEqualTo(configuration.getPageName());
        artifactIdText.setText("newArtifactId");
        assertThat(configuration.getPageName()).isEqualTo("newArtifactId");
    }

    @Test
    public void should_bind_version_control_with_configuration() throws Exception {
        final RestAPIExtensionArchetypeConfiguration configuration = RestAPIExtensionArchetypeConfiguration
                .defaultArchetypeConfiguration();

        final NewCustomPageArtifactConfigurationPage page = new NewCustomPageArtifactConfigurationPage(
                new WidgetFactory(), configuration,
                projectImportConfiguration, workspace);
        page.setWizard(displayRule.wizardWithContainer());

        page.createControl(displayRule.createComposite());

        final Text versionText = widgetFinder.<Text> withLabel(displayRule.getShell(), Messages.version);
        assertThat(versionText).isNotNull();
        assertThat(versionText.getText()).isEqualTo(configuration.getVersion());
        versionText.setText("0.0.1");
        assertThat(configuration.getVersion()).isEqualTo("0.0.1");
    }

    @Test
    public void should_bind_name_control_with_configuration() throws Exception {
        final RestAPIExtensionArchetypeConfiguration configuration = RestAPIExtensionArchetypeConfiguration
                .defaultArchetypeConfiguration();

        final NewCustomPageArtifactConfigurationPage page = new NewCustomPageArtifactConfigurationPage(
                new WidgetFactory(), configuration,
                projectImportConfiguration, workspace);
        page.setWizard(displayRule.wizardWithContainer());

        page.createControl(displayRule.createComposite());

        final Text nameText = widgetFinder.<Text> withLabel(displayRule.getShell(), Messages.name);
        assertThat(nameText).isNotNull();
        assertThat(nameText.getText()).isEqualTo(configuration.getPageDisplayName());
        nameText.setText("My Api");
        assertThat(configuration.getPageDisplayName()).isEqualTo("My Api");
    }

    @Test
    public void should_bind_description_control_with_configuration() throws Exception {
        final RestAPIExtensionArchetypeConfiguration configuration = RestAPIExtensionArchetypeConfiguration
                .defaultArchetypeConfiguration();

        final NewCustomPageArtifactConfigurationPage page = new NewCustomPageArtifactConfigurationPage(
                new WidgetFactory(), configuration,
                projectImportConfiguration, workspace);
        page.setWizard(displayRule.wizardWithContainer());

        page.createControl(displayRule.createComposite());

        final Text descriptionText = widgetFinder.<Text> withLabel(displayRule.getShell(), Messages.description);
        assertThat(descriptionText).isNotNull();
        assertThat(descriptionText.getText()).isEqualTo(configuration.getPageDescription());
        descriptionText.setText("My Api Description");
        assertThat(configuration.getPageDescription()).isEqualTo("My Api Description");
    }

}
