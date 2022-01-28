/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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

package org.bonitasoft.studio.properties.sections.timer;

import org.bonitasoft.studio.common.DateUtil;
import org.bonitasoft.studio.common.gmf.tools.GMFTools;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.common.widgets.GTKStyleHandler;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.AbstractTimerEvent;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.properties.sections.timer.wizard.EditTimerConditionWizard;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public class TimerEventConditionContribution implements IExtensibleGridPropertySectionContribution {

    private Text conditionViewer;

    private TabbedPropertySheetWidgetFactory widgetFactory;

    private TransactionalEditingDomain editingDomain;

    private AbstractTimerEvent eObject;

    public void createControl(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory,
            ExtensibleGridPropertySection extensibleGridPropertySection) {
        this.widgetFactory = widgetFactory;

        Composite container = widgetFactory.createPlainComposite(composite, SWT.NONE);
        container.setLayout(GridLayoutFactory.fillDefaults().extendedMargins(1, 1, 3, 3).numColumns(2).create());
        container.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.TOP).grab(true, true).create());

        conditionViewer = new Text(container,
                GTKStyleHandler.removeBorderFlag(SWT.BORDER | SWT.NO_FOCUS | SWT.READ_ONLY));
        if (!GTKStyleHandler.isGTK3()) {
            widgetFactory.adapt(conditionViewer, true, true);
        }
        conditionViewer
                .setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).create());
        createEditConditionButton(container);
    }

    private Button createEditConditionButton(final Composite parent) {
        final Button addData = widgetFactory.createButton(parent, Messages.editCondition, SWT.FLAT);
        addData.addListener(SWT.Selection, e -> new WizardDialog(Display.getDefault().getActiveShell(),
                new EditTimerConditionWizard(eObject, editingDomain, TimerEventConditionContribution.this)).open());
        return addData;
    }

    @Override
    public void dispose() {

    }

    public String getLabel() {
        return Messages.timerCondition;
    }

    public boolean isRelevantFor(EObject eObject) {
        return eObject instanceof AbstractTimerEvent;
    }

    public void refresh() {
        Expression condition = eObject.getCondition();
        if (condition != null) {
            String conditionLabel = groovyToLabel(condition);
            conditionViewer.setText(conditionLabel != null ? conditionLabel : "");

            IEditorPart editor =  PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                    .getActiveEditor();
            if (editor instanceof ProcessDiagramEditor) {
                EditPart ep = GMFTools.findEditPart(((ProcessDiagramEditor)editor).getDiagramEditPart(), eObject);
                if (ep != null && !ep.getChildren().isEmpty()) {
                    ((LabelEditPart) ep.getChildren().get(0)).refresh();
                }
            }
        }
    }

    private String groovyToLabel(Expression condition) {
        String conditionLabel;
        if (DateUtil.isDate(condition.getContent())) {
            conditionLabel = DateUtil.getDisplayDate(condition.getContent());
        } else if (DateUtil.isDuration(condition.getContent())) {
            conditionLabel = DateUtil.getDisplayDuration(condition.getContent());
        } else {
            conditionLabel = condition.getName();
        }
        return conditionLabel;
    }

    public void setEObject(EObject object) {
        eObject = (AbstractTimerEvent) object;
    }

    public void setEditingDomain(TransactionalEditingDomain editingDomain) {
        this.editingDomain = editingDomain;
    }

    public void setSelection(ISelection selection) {

    }
}
