/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.sections.general.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.databinding.validator.WrappingValidator;
import org.bonitasoft.studio.common.properties.AbstractPropertySectionContribution;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.ConnectableElement;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.EndErrorEvent;
import org.bonitasoft.studio.model.process.ErrorEvent;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Baptiste Mesta
 * 
 */
public class ErrorEventSectionContribution extends AbstractPropertySectionContribution {

    private DataBindingContext context;
    // private CCombo nameCombo;
    private Combo codeCombo;
    private ControlDecoration controlDecoration;
    private ControlDecoration hint;
    private final DiagramRepositoryStore diagramStore;

    public ErrorEventSectionContribution() {
	diagramStore = (DiagramRepositoryStore) RepositoryManager.getInstance().getCurrentRepository()
		.getRepositoryStore(DiagramRepositoryStore.class);
    }

    public boolean isRelevantFor(EObject eObject) {
	return eObject instanceof ErrorEvent;
    }

    public void refresh() {
    }

    public String getLabel() {
	if (eObject instanceof EndErrorEvent) {
	    return Messages.endErrorEvent_error;
	} else {
	    return Messages.errorEvent_error;
	}
    }

    public void createControl(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory,
	    ExtensibleGridPropertySection extensibleGridPropertySection) {
	codeCombo = new Combo(composite, SWT.NONE);
	codeCombo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

	controlDecoration = new ControlDecoration(codeCombo, SWT.RIGHT | SWT.TOP);
	FieldDecoration fieldDecoration = FieldDecorationRegistry.getDefault()
		.getFieldDecoration(FieldDecorationRegistry.DEC_ERROR);
	controlDecoration.setImage(fieldDecoration.getImage());
	controlDecoration.setDescriptionText(Messages.mustBeSet);
	controlDecoration.hide();
	
	hint = new ControlDecoration(composite.getChildren()[0], SWT.RIGHT);
	hint.setImage(Pics.getImage(PicsConstants.hint));
    }

    public void dispose() {

    }

    @Override
    public void setEObject(EObject object) {
	super.setEObject(object);
    }

    @Override
    public void setSelection(ISelection selection) {
	super.setSelection(selection);
	bindEObject();
    }

    private void bindEObject() {
	if (codeCombo != null && !codeCombo.isDisposed()) {
	    if (context != null) {
		context.dispose();
	    }
	    codeCombo.removeAll();

	    populateCombo();
	    context = new EMFDataBindingContext();
	    if (eObject instanceof EndErrorEvent) {

		hint.setDescriptionText(Messages.errorEvent_errorCodeHint);
		context.bindValue(SWTObservables.observeText(codeCombo),
			EMFEditObservables.observeValue(editingDomain, eObject,
				ProcessPackage.Literals.ERROR_EVENT__ERROR_CODE),
			new UpdateValueStrategy()
				.setAfterGetValidator(new WrappingValidator(controlDecoration, new IValidator() {

				    public IStatus validate(Object value) {
					if (value instanceof String && ((String) value).length() > 0) {
					    return Status.OK_STATUS;
					} else {
					    return Status.CANCEL_STATUS;
					}
				    }
				}, true)),
			null);
	    } else {
		hint.setDescriptionText(Messages.errorEvent_errorCodeCatchHint);
		controlDecoration.hide();
		context.bindValue(SWTObservables.observeText(codeCombo), EMFEditObservables.observeValue(editingDomain,
			eObject, ProcessPackage.Literals.ERROR_EVENT__ERROR_CODE));
	    }

	}
    }

    /**
     * Populate the combo with existing named error.
     */
    protected void populateCombo() {
	EObject parent = eObject.eContainer();
	Set<String> namedErrorsAvailable = new TreeSet<String>();
	if (parent instanceof ConnectableElement) {
	    /* Search in connectors */
	    ConnectableElement activity = (ConnectableElement) parent;
	    for (Connector connector : activity.getConnectors()) {
		String namedError = connector.getNamedError();
		if (namedError != null && namedError.length() != 0) {
		    namedErrorsAvailable.add(namedError);
		}
	    }
	}
	/* on subprocess search also end error event in subprocess */
	if (parent instanceof CallActivity) {

	    CallActivity subProcess = (CallActivity) parent;
	    final Expression calledProcessName = subProcess.getCalledActivityName();
	    String subprocessName = null;
	    if (calledProcessName != null && calledProcessName.getContent() != null
		    && calledProcessName.getType().equals(ExpressionConstants.CONSTANT_TYPE)) {
		subprocessName = calledProcessName.getContent();
	    }
	    final Expression calledProcessVersion = subProcess.getCalledActivityVersion();
	    String subprocessVersion = null;
	    if (calledProcessVersion != null && calledProcessVersion.getContent() != null
		    && calledProcessVersion.getType().equals(ExpressionConstants.CONSTANT_TYPE)) {
		subprocessVersion = calledProcessVersion.getContent();
	    }
	    if (subprocessName != null) {
		AbstractProcess relatedProcess = ModelHelper.findProcess(subprocessName, subprocessVersion,
			diagramStore.getAllProcesses());
		if (relatedProcess != null) {
		    List<EClass> types = new ArrayList<EClass>();
		    types.add(ProcessPackage.Literals.END_ERROR_EVENT);
		    List<Element> elements = new ArrayList<Element>();
		    ModelHelper.findAllElements(relatedProcess, elements, types);
		    for (Element element : elements) {
			EndErrorEvent endErrorEvent = (EndErrorEvent) element;
			String errorCode = endErrorEvent.getErrorCode();
			if (errorCode != null) {
			    namedErrorsAvailable.add(errorCode);
			}
		    }
		}
	    }
	}

	if (parent instanceof SubProcessEvent) {
	    SubProcessEvent subProcess = (SubProcessEvent) parent;
	    AbstractProcess relatedProcess = ModelHelper.getParentProcess(subProcess);
	    if (relatedProcess != null) {
		List<EClass> types = new ArrayList<EClass>();
		types.add(ProcessPackage.Literals.END_ERROR_EVENT);
		List<Element> elements = new ArrayList<Element>();
		ModelHelper.findAllElements(relatedProcess, elements, types);
		for (Element element : elements) {
		    EndErrorEvent endErrorEvent = (EndErrorEvent) element;
		    String errorCode = endErrorEvent.getErrorCode();
		    if (errorCode != null) {
			namedErrorsAvailable.add(errorCode);
		    }
		}

		/* Search in connectors */
		types.clear();
		types.add(ProcessPackage.Literals.CONNECTABLE_ELEMENT);
		elements.clear();
		ModelHelper.findAllElements(relatedProcess, elements, types);
		for (Element element : elements) {
		    ConnectableElement activity = (ConnectableElement) element;
		    for (Connector connector : activity.getConnectors()) {
			String namedError = connector.getNamedError();
			if (namedError != null && namedError.length() != 0) {
			    namedErrorsAvailable.add(namedError);
			}
		    }
		}

	    }
	}

	for (String string : namedErrorsAvailable) {
	    codeCombo.add(string);
	}
    }

}
