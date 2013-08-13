/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.actors.ui.section;

import org.bonitasoft.studio.actors.i18n.Messages;
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
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Romain Bioteau
 *
 */
public class AssignableActorsPropertySection extends AbstractActorsPropertySection implements ISelectionChangedListener{

	private Button useLaneActorButton;
	private Button taskActorButton;
	private Composite radioComposite;


	@Override
	protected void updateDatabinding() {
		super.updateDatabinding();
		Assignable assignable = (Assignable) getEObject() ;
		if(assignable != null){
			
			EObject current = assignable ;
			while (current != null && !(current instanceof Lane)) {
				current = current.eContainer() ;
			}

			useLaneActorButton.setEnabled(current instanceof Lane) ;
			IObservableValue value = EMFEditObservables.observeValue(getEditingDomain(), assignable, ProcessPackage.Literals.TASK__OVERRIDE_ACTORS_OF_THE_LANE) ;
			emfDatabindingContext.bindValue(SWTObservables.observeSelection(taskActorButton), value) ;
			emfDatabindingContext.bindValue(SWTObservables.observeSelection(useLaneActorButton), value,new UpdateValueStrategy(),new UpdateValueStrategy().setConverter(new Converter(Boolean.class,Boolean.class){

				@Override
				public Object convert(Object from) {
					return !(Boolean) from;
				}

			})) ;
			UpdateValueStrategy updateValueStrategy = new UpdateValueStrategy().setConverter(new Converter(Boolean.class,Boolean.class){

				@Override
				public Object convert(Object from) {
					return !(Boolean) from;
				}

			});
			emfDatabindingContext.bindValue(SWTObservables.observeEnabled(actorComboViewer.getControl()), SWTObservables.observeSelection(useLaneActorButton),new UpdateValueStrategy(),updateValueStrategy);			
		}
	}

	@Override
	protected void createRadioComposite(TabbedPropertySheetWidgetFactory widgetFactory,Composite mainComposite) {
		radioComposite = widgetFactory.createComposite(mainComposite, SWT.NONE) ;
		radioComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true,false).span(3, 1).create()) ;
		radioComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create()) ;

		taskActorButton = widgetFactory.createButton(radioComposite, Messages.useTaskActors, SWT.RADIO) ;
		useLaneActorButton = widgetFactory.createButton(radioComposite, Messages.useActorsDefinedInLane, SWT.RADIO) ;

	}

	

}
