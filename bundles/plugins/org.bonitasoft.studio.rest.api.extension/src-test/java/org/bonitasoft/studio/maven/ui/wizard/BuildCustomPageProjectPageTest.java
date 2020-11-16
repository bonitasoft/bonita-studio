/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.ui.wizard;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.ui.WidgetFactory;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionRepositoryStore;
import org.bonitasoft.studio.swt.WidgetFinder;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.set.WritableSet;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.swt.widgets.Text;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BuildCustomPageProjectPageTest {

    @Rule
    public RealmWithDisplay displayRule = new RealmWithDisplay();

    private final WidgetFinder widgetFinder = new WidgetFinder();

    @Mock
    private RestAPIExtensionRepositoryStore repositoryStore;

    @Test
    public void should_bind_target_location_to_observable() throws Exception {
        final IObservableValue locationObservable = new WritableValue();
        locationObservable.setValue(System.getProperty("user.home"));
        final BuildCustomPageProjectPage page = new BuildCustomPageProjectPage(repositoryStore, new WidgetFactory(),
                locationObservable, new WritableSet());
        doReturn(newArrayList()).when(repositoryStore).getChildren();
        page.setWizard(displayRule.wizardWithContainer());
        page.createControl(displayRule.createComposite());

        final Text locationText = widgetFinder.<Text> withLabel(displayRule.getShell(), Messages.targetLocation);
        assertThat(locationText).isNotNull();
        assertThat(locationText.getText()).isEqualTo(System.getProperty("user.home"));
    }
}
