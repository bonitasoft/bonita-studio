/**
 * Copyright (C) 2012-2014 BonitaSoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.identity.actors.ui.section;

import javax.inject.Inject;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.BonitaStudioFontRegistry;
import org.bonitasoft.studio.common.jface.databinding.converter.BooleanInverserConverter;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.connector.model.definition.migration.ConnectorConfigurationMigratorFactory;
import org.bonitasoft.studio.connector.model.definition.migration.ConnectorConfigurationToConnectorDefinitionConverter;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.model.process.Actor;
import org.bonitasoft.studio.model.process.Assignable;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Romain Bioteau
 */
public class AssignableActorsPropertySection extends AbstractActorsPropertySection {

    @Inject
    public AssignableActorsPropertySection(ConnectorConfigurationMigratorFactory migrationFactory,
            ConnectorConfigurationToConnectorDefinitionConverter configurationToDefinitionConverter,
            RepositoryAccessor repositoryAccessor) {
        super(migrationFactory, configurationToDefinitionConverter, repositoryAccessor);
    }

    private Button useLaneActorButton;
    private Button taskActorButton;
    private Composite radioComposite;
    private Label actorDefinedInLaneLabel;

    @Override
    protected void updateDatabinding() {
        super.updateDatabinding();
        actorDefinedInLaneLabel.setText("");
        final Assignable assignable = (Assignable) getEObject();
        if (assignable != null) {

            EObject current = assignable;
            while (current != null && !(current instanceof Lane)) {
                current = current.eContainer();
            }

            useLaneActorButton.setEnabled(current instanceof Lane);
            final IObservableValue value = EMFEditObservables.observeValue(getEditingDomain(), assignable,
                    ProcessPackage.Literals.TASK__OVERRIDE_ACTORS_OF_THE_LANE);
            emfDatabindingContext.bindValue(SWTObservables.observeSelection(taskActorButton), value);
            emfDatabindingContext.bindValue(SWTObservables.observeSelection(useLaneActorButton), value,
                    new UpdateValueStrategy(),
                    new UpdateValueStrategy().setConverter(new BooleanInverserConverter()));
            final UpdateValueStrategy updateValueStrategy = new UpdateValueStrategy()
                    .setConverter(new BooleanInverserConverter());
            emfDatabindingContext.bindValue(SWTObservables.observeEnabled(actorComboViewer.getControl()),
                    SWTObservables.observeSelection(useLaneActorButton), new UpdateValueStrategy(), updateValueStrategy);

            databindactorDefinedInLaneLabel(assignable);

        }
    }

    private void databindactorDefinedInLaneLabel(final Assignable assignable) {
        final Lane parentLane = ModelHelper.getParentLane(assignable);
        if (parentLane != null) {

            final IObservableValue observeActorDefinedInLane = EMFEditObservables.observeValue(getEditingDomain(),
                    parentLane,
                    ProcessPackage.Literals.ASSIGNABLE__ACTOR);
            emfDatabindingContext.bindValue(
                    SWTObservables.observeText(actorDefinedInLaneLabel),
                    observeActorDefinedInLane,
                    new UpdateValueStrategy(),
                    new UpdateValueStrategy().setConverter(new Converter(Object.class, String.class) {

                        @Override
                        public String convert(final Object fromObject) {
                            if (fromObject != null) {
                                return "(" + ((Actor) fromObject).getName() + ")";
                            } else {
                                return Messages.noActorDefinedAtLaneLevel;
                            }
                        }
                    }));
            actorDefinedInLaneLabel.pack(true);
        }
    }

    @Override
    protected void createRadioComposite(final TabbedPropertySheetWidgetFactory widgetFactory,
            final Composite mainComposite) {
        radioComposite = widgetFactory.createComposite(mainComposite, SWT.NONE);
        radioComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        radioComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(0, 0).create());

        taskActorButton = widgetFactory.createButton(radioComposite, Messages.useTaskActors, SWT.RADIO);
        useLaneActorButton = widgetFactory.createButton(radioComposite, Messages.useActorsDefinedInLane, SWT.RADIO);
        actorDefinedInLaneLabel = widgetFactory.createLabel(radioComposite, "");
        actorDefinedInLaneLabel.setFont(BonitaStudioFontRegistry.getCommentsFont());
    }

}
