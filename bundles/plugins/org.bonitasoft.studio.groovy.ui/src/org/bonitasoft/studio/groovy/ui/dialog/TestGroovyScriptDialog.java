/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.groovy.ui.dialog;

import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.updateValueStrategy;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.mandatoryValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.multiValidator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.jface.databinding.DialogSupport;
import org.bonitasoft.studio.common.jface.databinding.UpdateValueStrategyFactory;
import org.bonitasoft.studio.common.jface.databinding.validator.MultiValidatorFactory;
import org.bonitasoft.studio.dependencies.ui.dialog.ManageConnectorJarDialog;
import org.bonitasoft.studio.groovy.ScriptVariable;
import org.bonitasoft.studio.groovy.ui.Messages;
import org.bonitasoft.studio.groovy.ui.dialog.control.BooleanRadioGroup;
import org.bonitasoft.studio.groovy.ui.dialog.control.DateTimeControl;
import org.bonitasoft.studio.groovy.ui.viewer.TestGroovyScriptUtil;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.conversion.StringToNumberConverter;
import org.eclipse.core.internal.databinding.validation.StringToDoubleValidator;
import org.eclipse.core.internal.databinding.validation.StringToFloatValidator;
import org.eclipse.core.internal.databinding.validation.StringToIntegerValidator;
import org.eclipse.core.internal.databinding.validation.StringToLongValidator;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 */
public class TestGroovyScriptDialog extends Dialog {

    private final List<ScriptVariable> nodes;
    private final GroovyCompilationUnit unit;
    private final List<PropertyValue<?>> propertyValues = new ArrayList<PropertyValue<?>>();
    private final String returnType;
    private final Map<String, Serializable> variables;
    private DataBindingContext dbc;

    public TestGroovyScriptDialog(final Shell parentShell, final List<ScriptVariable> nodes, final GroovyCompilationUnit unit, final String returnType,
            final Map<String, Serializable> variables) {
        super(parentShell);
        this.nodes = nodes;
        this.unit = unit;
        this.returnType = returnType;
        this.variables = variables;
    }

    @Override
    protected void createButtonsForButtonBar(final Composite parent) {
        super.createButtonsForButtonBar(parent);
        final Button okButton = getButton(IDialogConstants.OK_ID);
        okButton.setText(Messages.testButtonLabel);
        okButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                evaluateScript();
            }

        });
    }

    private void evaluateScript() {
        try {
            final ManageConnectorJarDialog mcjd = new ManageConnectorJarDialog(Display.getDefault().getActiveShell());
            final int retCode = mcjd.open();
            if (retCode == Window.OK) {
                TestGroovyScriptUtil.evaluateExpression(unit.getSource(), returnType, TestGroovyScriptUtil.getVariableValues(unit, nodes, propertyValues),
                        mcjd.getSelectedJars());
            }
        } catch (final JavaModelException e1) {
            new BonitaErrorDialog(getShell(), "Evaluation failed", "An error occured while evaluating script", e1);
        }
    }

    @Override
    protected Control createDialogArea(final Composite parent) {
        dbc = new DataBindingContext();

        final Composite mainComposite = (Composite) super.createDialogArea(parent);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().hint(400, SWT.DEFAULT).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().extendedMargins(15, 15, 10, 0).spacing(0, 15).create());
        final Label descriptionLabel = new Label(mainComposite, SWT.WRAP);
        descriptionLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        descriptionLabel.setText(Messages.testGroovyScriptDialogDescription);
        final Map<String, Serializable> procVariables = getProcessVariables(variables);
        final Map<String, Serializable> unknownVariables = getUnknownVariables(variables);
        if (!procVariables.isEmpty()) {
            createVariableGroup(mainComposite, Messages.processVariableLabel, procVariables, dbc);
        }
        if (!unknownVariables.isEmpty()) {
            createVariableGroup(mainComposite, Messages.unknownVariableLabel, unknownVariables, dbc);
        }

        return mainComposite;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.Dialog#create()
     */
    @Override
    public void create() {
        super.create();
        DialogSupport.create(this, dbc);
    }

    private void createVariableGroup(final Composite parent, final String groupTitle, final Map<String, Serializable> groupVariables,
            final DataBindingContext dbc) {
        final Group group = new Group(parent, SWT.NONE);
        group.setText(groupTitle);
        group.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create());
        group.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(true, false).create());

        final Iterator<Entry<String, Serializable>> it = groupVariables.entrySet().iterator();
        while (it.hasNext()) {
            final Entry<String, Serializable> var = it.next();
            final Label varLabel = new Label(group, SWT.NONE);
            varLabel.setText(var.getKey());
            final PropertyValue<?> propertyValue = new PropertyValue<Serializable>(var.getKey());
            if (var.getValue().equals(TestGroovyScriptUtil.LIST)) {
                final Combo varValueCombo = new Combo(group, SWT.READ_ONLY);
                varValueCombo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(10, 0).create());
                final ScriptVariable node = TestGroovyScriptUtil.getProcessVariable(var.getKey(), nodes);
                final List<String> values = org.bonitasoft.studio.groovy.GroovyUtil.getTypeValues(node.getType());
                for (final String f : values) {
                    varValueCombo.add(f);
                }
                ControlDecorationSupport.create(dbc.bindValue(SWTObservables.observeText(varValueCombo), PojoObservables.observeValue(propertyValue, "value"),
                        updateValueStrategy().withValidator(mandatoryValidator(var.getKey())).create(), null), SWT.LEFT);
            } else if (var.getValue().equals(TestGroovyScriptUtil.BOOLEAN)) {
                final BooleanRadioGroup booleanRadioGroup = new BooleanRadioGroup(group);
                booleanRadioGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(10, 0).create());
                booleanRadioGroup.bindControl(dbc, PojoObservables.observeValue(propertyValue, "value"));
            } else if (var.getValue().equals(TestGroovyScriptUtil.DATE)) {
                final DateTimeControl dateTimeControl = new DateTimeControl(group);
                dateTimeControl.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(10, 0).create());
                dateTimeControl.bindControl(dbc, PojoObservables.observeValue(propertyValue, "value"));
            } else {
                final Text varValueText = new Text(group, SWT.BORDER);
                varValueText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(10, 0).create());
                final ScriptVariable scriptVaraible = getScriptVariable(var.getKey());
                ControlDecorationSupport.create(
                        dbc.bindValue(SWTObservables.observeText(varValueText, SWT.Modify), PojoObservables.observeValue(propertyValue, "value"),
                                variableInputStrategy(var.getKey(), scriptVaraible), null), SWT.LEFT);
                if (scriptVaraible != null && scriptVaraible.getDefaultValue() != null) {
                    varValueText.setText(scriptVaraible.getDefaultValue());
                }
            }
            propertyValues.add(propertyValue);
        }

    }

    protected UpdateValueStrategy variableInputStrategy(final String inputName, final ScriptVariable variable) {
        final UpdateValueStrategyFactory strategyFactory = updateValueStrategy();
        final MultiValidatorFactory multiValidator = multiValidator();
        multiValidator.addValidator(mandatoryValidator(inputName));
        if (variable != null) {
            if (Long.class.getName().equals(variable.getType())) {
                final StringToNumberConverter converter = StringToNumberConverter.toLong(true);
                strategyFactory.withConverter(converter);
                multiValidator.addValidator(new StringToLongValidator(converter));
            }
            if (Integer.class.getName().equals(variable.getType())) {
                final StringToNumberConverter converter = StringToNumberConverter.toInteger(true);
                strategyFactory.withConverter(converter);
                multiValidator.addValidator(new StringToIntegerValidator(converter));
            }
            if (Float.class.getName().equals(variable.getType())) {
                final StringToNumberConverter converter = StringToNumberConverter.toFloat(true);
                strategyFactory.withConverter(converter);
                multiValidator.addValidator(new StringToFloatValidator(converter));
            }
            if (Double.class.getName().equals(variable.getType())) {
                final StringToNumberConverter converter = StringToNumberConverter.toDouble(true);
                strategyFactory.withConverter(converter);
                multiValidator.addValidator(new StringToDoubleValidator(converter));
            }
        }
        strategyFactory.withValidator(multiValidator);
        return strategyFactory.create();
    }

    private ScriptVariable getScriptVariable(final String name) {
        for (final ScriptVariable sv : nodes) {
            if (sv.getName().equals(name)) {
                return sv;
            }
        }
        return null;
    }

    private Map<String, Serializable> getProcessVariables(final Map<String, Serializable> initialMap) {
        final Map<String, Serializable> result = new HashMap<String, Serializable>();
        for (final String varName : initialMap.keySet()) {
            if (processContains(varName)) {
                result.put(varName, initialMap.get(varName));
            }
        }
        return result;
    }

    private Map<String, Serializable> getUnknownVariables(final Map<String, Serializable> initialMap) {
        final Map<String, Serializable> result = new HashMap<String, Serializable>();
        for (final String varName : initialMap.keySet()) {
            if (!processContains(varName)) {
                result.put(varName, initialMap.get(varName));
            }
        }
        return result;
    }

    private boolean processContains(final String varName) {
        for (final ScriptVariable v : nodes) {
            if (v.getName().equals(varName)) {
                return true;
            }
        }
        return false;
    }

}
