/**
 * Copyright (C) 2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.form.sections.actions.contributions;

import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.SuggestBox;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Romain Bioteau
 *
 */
public class AsynchronousSuggestBoxContribution implements IExtensibleGridPropertySectionContribution  {

	private SuggestBox suggestBox;
	private TransactionalEditingDomain editingDomain;
	private Button isAsynchronousCheckbox;
	private Text delayText;
	private EMFDataBindingContext dataBindingContext;
	private ExtensibleGridPropertySection section;

	public boolean isRelevantFor(final EObject eObject) {
		return eObject instanceof SuggestBox;
	}

	public void refresh() {}

	public String getLabel() {
		return Messages.asynchronousLabel;
	}

	public void createControl(final Composite composite,
			final TabbedPropertySheetWidgetFactory widgetFactory,
			final ExtensibleGridPropertySection extensibleGridPropertySection) {
		section = extensibleGridPropertySection ;
		composite.setLayout(new GridLayout(4, false)) ;
		isAsynchronousCheckbox = widgetFactory.createButton(composite,"", SWT.CHECK) ;
		widgetFactory.createLabel(composite, Messages.refreshDelay) ;
		delayText = widgetFactory.createText(composite, String.valueOf(suggestBox.getDelay()), SWT.BORDER | SWT.RIGHT) ;
		delayText.setLayoutData(GridDataFactory.fillDefaults().hint(50, SWT.DEFAULT).create()) ;
		widgetFactory.createLabel(composite, Messages.ms) ;

		final ControlDecoration asyncHint = new ControlDecoration(isAsynchronousCheckbox, SWT.LEFT | SWT.TOP) ;
		asyncHint.setImage(Pics.getImage(PicsConstants.hint)) ;
		asyncHint.setDescriptionText(Messages.asynchronousHint) ;
		
		bindWidgets() ;
		delayText.setEnabled(suggestBox.isAsynchronous()) ;
	}

	protected void bindWidgets() {
		if(dataBindingContext != null){
			dataBindingContext.dispose();
		}
		dataBindingContext = new EMFDataBindingContext();
		dataBindingContext.bindValue(SWTObservables.observeSelection(isAsynchronousCheckbox),EMFEditObservables.observeValue(editingDomain, suggestBox, FormPackage.Literals.SUGGEST_BOX__ASYNCHRONOUS) );
		dataBindingContext.bindValue(EMFEditObservables.observeValue(editingDomain, suggestBox, FormPackage.Literals.SUGGEST_BOX__ASYNCHRONOUS), SWTObservables.observeEnabled(delayText),null,new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER));
		dataBindingContext.bindValue(SWTObservables.observeText(delayText, SWT.Modify), EMFEditObservables.observeValue(editingDomain, suggestBox, FormPackage.Literals.SUGGEST_BOX__DELAY));

	}

	public void setEObject(final EObject object) {
		suggestBox = (SuggestBox) object ;
	}

	public void setSelection(final ISelection selection) {

	}

	public void setEditingDomain(final TransactionalEditingDomain editingDomain) {
		this.editingDomain = editingDomain ;

	}

	public void dispose() {}

}
