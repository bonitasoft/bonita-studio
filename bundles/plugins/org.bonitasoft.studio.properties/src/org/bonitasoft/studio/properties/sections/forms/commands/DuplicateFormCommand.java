/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.sections.forms.commands;

import java.util.List;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.gmf.tools.CopyEObjectFeaturesCommand;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.MultipleValuatedFormField;
import org.bonitasoft.studio.model.form.ViewForm;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.properties.sections.forms.FormsUtils;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

/**
 *
 *  allow to duplicate an existing Form
 *
 * @author Baptiste Mesta
 * @author Aurelien Pupier - use AbstractTransactionalCommand to avoid memory leaks (instead of Command)
 */
public class DuplicateFormCommand extends AbstractTransactionalCommand {

    private final Element pageFlow;
    private final Form baseForm;
    private final String formName;
    private final String formDesc;
    private final EStructuralFeature feature;

    public DuplicateFormCommand(final Element pageFlow, final EStructuralFeature feature, final Form baseForm, final String id, final String formDesc, final TransactionalEditingDomain editingDomain) {
        super(editingDomain, "Duplicate form", getWorkspaceFiles(pageFlow));
        this.pageFlow = pageFlow;
        this.baseForm = baseForm;
        formName = NamingUtils.toJavaIdentifier(id, true);
        this.formDesc = formDesc;
        this.feature = feature;
    }


    @SuppressWarnings("unchecked")
    @Override
    protected CommandResult doExecuteWithResult(final IProgressMonitor monitor,
            final IAdaptable info) throws ExecutionException {

        Form form;
        if(baseForm instanceof ViewForm){
            if(feature.getEType().equals(FormPackage.Literals.VIEW_FORM)){
                //nothing to convert
                form = EcoreUtil.copy(baseForm);
            }else{
                form = FormFactory.eINSTANCE.createForm();
                CopyEObjectFeaturesCommand.copyFeatures(EcoreUtil.copy(baseForm), form);
            }
        }else{
            if(feature.getEType().equals(FormPackage.Literals.FORM)){
                //nothing to convert
                form = EcoreUtil.copy(baseForm);
            }else{
                form = FormFactory.eINSTANCE.createViewForm();
                CopyEObjectFeaturesCommand.copyFeatures(EcoreUtil.copy(baseForm), form);
                cleanWidgetsContingenciesPropertiesOfForm(form);
            }
        }

        form.setName(formName);
        form.setDocumentation(formDesc);
        ((List<Form>) pageFlow.eGet(feature)).add(form);
        //remove data out of the scope
        ModelHelper.removedReferencedEObjects(form,pageFlow);

        FormsUtils.createFormDiagram(form, getEditingDomain());
        FormsUtils.openDiagram(form,getEditingDomain());
        return CommandResult.newOKCommandResult(form);
    }


    protected void cleanWidgetsContingenciesPropertiesOfForm(final Form form){
        final List<Widget> widgets = form.getWidgets();
        for(final Widget widget : widgets){
            if( widget.getDependOn() != null ){
                widget.getDependOn().clear();
            }
            final Expression displayAfterEventDependsOnConditionScript = widget.getDisplayAfterEventDependsOnConditionScript();
            if(displayAfterEventDependsOnConditionScript != null){
                ExpressionHelper.clearExpression(displayAfterEventDependsOnConditionScript);
            }
            final Expression displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition = widget.getDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition();
            if(displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition != null){
                ExpressionHelper.clearExpression(displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition);
            }
            final Expression afterEventExpression = widget.getAfterEventExpression();
            if(afterEventExpression != null){
                ExpressionHelper.clearExpression(afterEventExpression);
            }
            if(widget instanceof MultipleValuatedFormField){
                final Expression defaultExpressionAfterEvent = ((MultipleValuatedFormField)widget).getDefaultExpressionAfterEvent();
                if(defaultExpressionAfterEvent != null){
                    ExpressionHelper.clearExpression(defaultExpressionAfterEvent);
                }
            }


        }
    }


}
