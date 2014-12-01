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
package org.bonitasoft.studio.expression.editor.formfield;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormButton;
import org.bonitasoft.studio.model.form.FormField;
import org.bonitasoft.studio.model.form.Group;
import org.bonitasoft.studio.model.form.NextFormButton;
import org.bonitasoft.studio.model.form.SubmitFormButton;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.form.WidgetDependency;
import org.bonitasoft.studio.model.process.AbstractPageFlow;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.RecapFlow;
import org.bonitasoft.studio.model.process.ViewPageFlow;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 *
 */
public class FormFieldExpressionProvider implements IExpressionProvider {

    private final ComposedAdapterFactory adapterFactory;
    private final AdapterFactoryLabelProvider adapterLabelProvider;

    public FormFieldExpressionProvider() {
        adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        adapterLabelProvider = new AdapterFactoryLabelProvider(adapterFactory);
    }


    @Override
    public Set<Expression> getExpressions(final EObject context) {
        final Set<Expression> result = new HashSet<Expression>();
        final EObject relevantParent = getRelevantParent(context);
        if (relevantParent instanceof Widget) {
            result.add(ExpressionHelper.createWidgetExpression((Widget) relevantParent));
            for (final WidgetDependency dep : ((Widget) relevantParent).getDependOn()) {
                if (dep.getWidget() != null) {
                    result.add(ExpressionHelper.createWidgetExpression(dep.getWidget()));
                }
            }
            // for the Submit button only, add fields of other widgets
            if (relevantParent instanceof SubmitFormButton) {
                if (relevantParent.eContainer() != null &&
                        (relevantParent.eContainer() instanceof Form || relevantParent.eContainer() instanceof Group)) {
                    final Form f = ModelHelper.getParentForm(relevantParent);
                    for (final Widget w : ModelHelper.getAllAccessibleWidgetInsideForm(f)) {
                        if (w instanceof FormField || w instanceof Group) {
                            result.add(ExpressionHelper.createWidgetExpression(w));
                        }
                    }
                }
            }

            //Add all widgets from the pageflow not in the same form
            final AbstractPageFlow pageFlow = ModelHelper.getPageFlow((Widget) relevantParent);
            if (pageFlow != null) {
                final List<? extends Form> forms = getForms(pageFlow, ModelHelper.getForm((Widget) relevantParent));
                final Form parentForm = ModelHelper.getParentForm(relevantParent);
                for (final Form f : forms) {
                    if (!f.equals(parentForm)) {
                        for (final Widget w : ModelHelper.getAllAccessibleWidgetInsideForm(f)) {
                            if (w instanceof FormField || w instanceof NextFormButton || w instanceof Group) {
                                result.add(ExpressionHelper.createWidgetExpression(w));
                            }
                        }
                    }
                }
            }
        } else if (relevantParent instanceof Form && relevantParent.eContainer() != null) {
            if (relevantParent.eContainer() instanceof AbstractPageFlow) {
                // get all fields from pageflow
                final AbstractPageFlow pageFlow = (AbstractPageFlow) relevantParent.eContainer();
                if (pageFlow != null) {
                    final List<? extends Form> forms = getForms(pageFlow, (Form) relevantParent);
                    for (final Form f : forms) {
                        for (final Widget w : ModelHelper.getAllAccessibleWidgetInsideForm(f)) {
                            if (w instanceof FormField || w instanceof FormButton || w instanceof Group) {
                                result.add(ExpressionHelper.createWidgetExpression(w));
                            }
                        }
                    }
                }
            }
        } else if (relevantParent instanceof AbstractPageFlow) {
            // get all fields from pageflow
            if (relevantParent instanceof PageFlow) {
                if (relevantParent != null) {
                    for (final Form f : ((PageFlow) relevantParent).getForm()) {
                        for (final Widget w : ModelHelper.getAllAccessibleWidgetInsideForm(f)) {
                            if (w instanceof FormField || w instanceof NextFormButton || w instanceof Group) {
                                result.add(ExpressionHelper.createWidgetExpression(w));
                            }
                        }
                    }
                    if (relevantParent instanceof ViewPageFlow) {
                        for (final Form f : ((ViewPageFlow) relevantParent).getViewForm()) {
                            for (final Widget w : ModelHelper.getAllAccessibleWidgetInsideForm(f)) {
                                if (w instanceof FormField || w instanceof NextFormButton || w instanceof Group) {
                                    result.add(ExpressionHelper.createWidgetExpression(w));
                                }
                            }
                        }
                    }
                    if (relevantParent instanceof RecapFlow) {
                        for (final Form f : ((RecapFlow) relevantParent).getRecapForms()) {
                            for (final Widget w : ModelHelper.getAllAccessibleWidgetInsideForm(f)) {
                                if (w instanceof FormField || w instanceof NextFormButton || w instanceof Group) {
                                    result.add(ExpressionHelper.createWidgetExpression(w));
                                }
                            }
                        }
                    }
                }
            }
        }


        return result;
    }

    private List<? extends Form> getForms(final AbstractPageFlow pageFlow, final Form form) {
        final EStructuralFeature eContainingFeature = form.eContainingFeature();
        if (ProcessPackage.Literals.PAGE_FLOW__FORM.equals(eContainingFeature)) {
            return ((PageFlow) pageFlow).getForm();
        } else if (ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_FORM.equals(eContainingFeature)) {
            return ((ViewPageFlow) pageFlow).getViewForm();
        } else if (ProcessPackage.Literals.RECAP_FLOW__RECAP_FORMS.equals(eContainingFeature)) {
            return ((RecapFlow) pageFlow).getRecapForms();
        }
        return Collections.emptyList();
    }

    private EObject getRelevantParent(final EObject context) {
        EObject parent = context;
        while (parent != null && !(parent instanceof Form) && !(parent instanceof Widget) && !(parent instanceof PageFlow)) {
            parent = parent.eContainer();
        }
        return parent;
    }


    @Override
    public String getExpressionType() {
        return ExpressionConstants.FORM_FIELD_TYPE;
    }

    @Override
    public Image getIcon(final Expression expression) {
        if (expression.getReferencedElements().isEmpty()) {
            return null;
        }
        return adapterLabelProvider.getImage(expression.getReferencedElements().get(0));
    }

    @Override
    public String getProposalLabel(final Expression expression) {
        return expression.getName();
    }

    @Override
    public boolean isRelevantFor(final EObject context) {
        return context instanceof EObject;
    }

    @Override
    public Image getTypeIcon() {
        return Pics.getImage(PicsConstants.form);
    }

    @Override
    public String getTypeLabel() {
        return Messages.formFieldTypeLabel;
    }

    @Override
    public IExpressionEditor getExpressionEditor(final Expression expression,final EObject context) {
        return new FormFieldExpressionEditor();
    }



}
