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
package org.bonitasoft.studio.designer.ui.property.section.control;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.FormMappingBuilder.aFormMapping;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;
import static org.mockito.Mockito.doReturn;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.i18n.Messages;
import org.bonitasoft.studio.model.process.FormMappingType;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author aurelie
 */
@RunWith(MockitoJUnitRunner.class)
public class FormMappingRadioGroupTest {

    private TabbedPropertySheetWidgetFactory widgetFactory;
    @Mock
    private IEclipsePreferences preferenceStore;
    @Mock
    private RepositoryAccessor repositoryAccessor;
    @Mock
    private FormReferenceExpressionValidator formReferenceExpressionValidator;
    @Mock
    private CreateOrEditFormProposalListener createOrEditFormListener;
    @Mock
    private IObservableValue formMappingObservable;

    @Mock
    private IResourceChangeListener resourceChangeListener;

    @Mock
    private IWorkspace workspace;

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();
    private FormMappingRadioGroup radioGroup;

    @Before
    public void setup() {
        final Composite composite = realmWithDisplay.createComposite();
        widgetFactory = new TabbedPropertySheetWidgetFactory();
        doReturn(workspace).when(repositoryAccessor).getWorkspace();
        radioGroup = new FormMappingRadioGroup(composite, widgetFactory, preferenceStore, repositoryAccessor,
                formReferenceExpressionValidator, createOrEditFormListener);

    }

    @Test
    public void should_infoConverter_return_processUIDesignerMessage() {

        final Pool pool = aPool().havingFormMapping(aFormMapping()).build();

        doReturn(pool.getFormMapping()).when(formMappingObservable).getValue();

        final IConverter converter = radioGroup.createInfoConverter(formMappingObservable);
        assertThat(converter.convert(FormMappingType.INTERNAL)).isEqualTo(Messages.processUIDesignerInfo);
    }

    @Test
    public void should_infoConverter_return_stepUIDesignerMessage() {

        final Task task = aTask().havingFormMapping(aFormMapping()).build();

        doReturn(task.getFormMapping()).when(formMappingObservable).getValue();

        final IConverter converter = radioGroup.createInfoConverter(formMappingObservable);
        assertThat(converter.convert(FormMappingType.INTERNAL)).isEqualTo(Messages.stepUIDesignerInfo);
    }

    @Test
    public void should_infoConverter_return_overviewUIDesignerInfo() {

        final Pool pool = aPool().havingOverviewFormMapping(aFormMapping()).build();

        doReturn(pool.getOverviewFormMapping()).when(formMappingObservable).getValue();

        final IConverter converter = radioGroup.createInfoConverter(formMappingObservable);
        assertThat(converter.convert(FormMappingType.INTERNAL)).isEqualTo(Messages.overviewUIDesignerInfo);
    }

    @Test
    public void should_infoConverter_return_processURLMessage() {

        final Pool pool = aPool().havingFormMapping(aFormMapping()).build();

        doReturn(pool.getFormMapping()).when(formMappingObservable).getValue();

        final IConverter converter = radioGroup.createInfoConverter(formMappingObservable);
        assertThat(converter.convert(FormMappingType.URL)).isEqualTo(Messages.processURLInfo);
    }

    @Test
    public void should_infoConverter_return_stepURLMessage() {

        final Task task = aTask().havingFormMapping(aFormMapping()).build();

        doReturn(task.getFormMapping()).when(formMappingObservable).getValue();

        final IConverter converter = radioGroup.createInfoConverter(formMappingObservable);
        assertThat(converter.convert(FormMappingType.URL)).isEqualTo(Messages.stepURLInfo);
    }

    @Test
    public void should_infoConverter_return_overviewURLInfo() {

        final Pool pool = aPool().havingOverviewFormMapping(aFormMapping()).build();

        doReturn(pool.getOverviewFormMapping()).when(formMappingObservable).getValue();

        final IConverter converter = radioGroup.createInfoConverter(formMappingObservable);
        assertThat(converter.convert(FormMappingType.URL)).isEqualTo(Messages.overviewURLInfo);
    }

    @Test
    public void should_infoConverter_return_processLegacyMessage() {

        final Pool pool = aPool().havingFormMapping(aFormMapping()).build();

        doReturn(pool.getFormMapping()).when(formMappingObservable).getValue();

        final IConverter converter = radioGroup.createInfoConverter(formMappingObservable);
        assertThat(converter.convert(FormMappingType.LEGACY)).isEqualTo(Messages.processLegacyInfo);
    }

    @Test
    public void should_infoConverter_return_stepLegacyMessage() {

        final Task task = aTask().havingFormMapping(aFormMapping()).build();

        doReturn(task.getFormMapping()).when(formMappingObservable).getValue();

        final IConverter converter = radioGroup.createInfoConverter(formMappingObservable);
        assertThat(converter.convert(FormMappingType.LEGACY)).isEqualTo(Messages.stepLegacyInfo);
    }

    @Test
    public void should_infoConverter_return_overviewLegacyInfo() {

        final Pool pool = aPool().havingOverviewFormMapping(aFormMapping()).build();

        doReturn(pool.getOverviewFormMapping()).when(formMappingObservable).getValue();

        final IConverter converter = radioGroup.createInfoConverter(formMappingObservable);
        assertThat(converter.convert(FormMappingType.LEGACY)).isEqualTo(Messages.overviewLegacyInfo);
    }

}
