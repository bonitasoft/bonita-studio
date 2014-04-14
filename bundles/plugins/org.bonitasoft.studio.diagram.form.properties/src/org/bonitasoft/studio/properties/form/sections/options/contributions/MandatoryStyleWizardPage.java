/**
 * Copyright (C) 2010-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.form.sections.options.contributions;

import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.MandatoryFieldsCustomization;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author Mickael Istria
 *
 */
public class MandatoryStyleWizardPage extends WizardPage {

    private final MandatoryFieldsCustomization mandatoryCustoWorkingCopy;

    /**
     * @param mandatoryCusto
     */
    public MandatoryStyleWizardPage(MandatoryFieldsCustomization mandatoryCusto) {
        super(MandatoryStyleWizardPage.class.getName());
        setImageDescriptor(Pics.getWizban());
        setTitle(Messages.customizeMandatory_WizardTitle);
        setDescription(Messages.customizeMandatory_WizardDesc);
        mandatoryCustoWorkingCopy = mandatoryCusto;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent) {
        Composite res = new Composite(parent, SWT.NONE);
        res.setLayout(new GridLayout(2, false));
        Label mandatorySymbolLabel = new Label(res, SWT.NONE);
        mandatorySymbolLabel.setText(Messages.mandatorySymbol);
        ExpressionViewer mandatorySymbolText = new ExpressionViewer(res, SWT.BORDER, FormPackage.Literals.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_SYMBOL);

        Expression mandatorySymbol = mandatoryCustoWorkingCopy.getMandatorySymbol();
        if(mandatorySymbol == null){
            mandatorySymbol = ExpressionFactory.eINSTANCE.createExpression();
            mandatoryCustoWorkingCopy.setMandatorySymbol(mandatorySymbol);
        }
        mandatorySymbolText.setSelection(new StructuredSelection(mandatorySymbol));
        mandatorySymbolText.setInput(mandatoryCustoWorkingCopy);
        mandatorySymbolText.getControl().setLayoutData(GridDataFactory.swtDefaults().grab(false, false).hint(120, SWT.DEFAULT).create());


        Label mandatoryLabelLabel = new Label(res, SWT.NONE);
        mandatoryLabelLabel.setText(Messages.mandatoryLabel);
        ExpressionViewer mandatoryLabelText = new ExpressionViewer(res, SWT.BORDER,  FormPackage.Literals.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_LABEL);
        Expression mandatoryLabel = mandatoryCustoWorkingCopy.getMandatoryLabel();
        if(mandatoryLabel == null){
            mandatoryLabel = ExpressionFactory.eINSTANCE.createExpression();
            mandatoryCustoWorkingCopy.setMandatoryLabel(mandatoryLabel);
        }
        mandatoryLabelText.setSelection(new StructuredSelection(mandatoryLabel));
        mandatoryLabelText.setInput(mandatoryCustoWorkingCopy);
        mandatoryLabelText.getControl().setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));


        setControl(res);
    }

    public Expression getMandatorySymbol() {
        return mandatoryCustoWorkingCopy.getMandatorySymbol();
    }

    public Expression getMandatoryLabel() {
        return mandatoryCustoWorkingCopy.getMandatoryLabel();
    }
}
