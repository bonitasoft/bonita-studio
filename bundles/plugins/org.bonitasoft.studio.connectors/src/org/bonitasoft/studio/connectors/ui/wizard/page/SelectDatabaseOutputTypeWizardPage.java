/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connectors.ui.wizard.page;

import org.bonitasoft.studio.common.jface.BonitaStudioFontRegistry;
import org.bonitasoft.studio.connector.model.definition.wizard.AbstractConnectorConfigurationWizardPage;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.ui.wizard.page.sqlutil.SQLQueryUtil;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

/**
 * @author Romain Bioteau
 */
public class SelectDatabaseOutputTypeWizardPage extends AbstractConnectorConfigurationWizardPage
        implements DatabaseConnectorConstants, IValueChangeListener {

    protected Expression outputTypeExpression;
    protected IPreferenceStore preferenceStore;
    protected Expression scriptExpression;
    protected Button gModeRadio;
    protected Button alwaysUseScriptCheckbox;
    protected SelectObservableValue radioGroupObservable;
    protected ISWTObservableValue graphicalModeSelectionValue;
    protected boolean editing;
    protected ISWTObservableValue singleModeRadioObserveEnabled;
    protected ISWTObservableValue nRowsOneColModeRadioObserveEnabled;
    protected ISWTObservableValue oneRowNColModeRadioObserveEnabled;
    protected IWizardPage previousPageBackup;
    private ISWTObservableValue scriptValue;
    private ISWTObservableValue tableObserveEnabled;

    public SelectDatabaseOutputTypeWizardPage(boolean editing) {
        super(SelectDatabaseOutputTypeWizardPage.class.getName());
        setTitle(Messages.outputOperationsDefinitionTitle);
        setDescription(Messages.outputOperationsDefinitionDesc);
        this.editing = editing;
        preferenceStore = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore();
    }

    @Override
    protected Control doCreateControl(Composite parent, EMFDataBindingContext context) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).extendedMargins(10, 10, 5, 0).create());

        createSelectModeLabelControl(mainComposite);

        scriptExpression = (Expression) getConnectorParameter(getInput(SCRIPT_KEY)).getExpression();
        final IObservableValue scriptContentValue = EMFObservables.observeValue(scriptExpression,
                ExpressionPackage.Literals.EXPRESSION__CONTENT);
        scriptContentValue.addValueChangeListener(this);
        outputTypeExpression = (Expression) getConnectorParameter(getInput(OUTPUT_TYPE_KEY)).getExpression();
        outputTypeExpression.setName(OUTPUT_TYPE_KEY);

        final Composite choicesComposite = createGraphicalModeControl(mainComposite, context);
        final Button singleModeRadio = createSingleChoice(choicesComposite, context);
        final Button oneRowModeRadio = createOneRowNColsChoice(choicesComposite, context);
        final Button nRowModeRadio = createNRowsOneColChoice(choicesComposite, context);
        final Button tableModeRadio = createTableChoice(choicesComposite, context);
        final Button scriptModeRadio = createScriptModeControl(mainComposite);

        final IObservableValue singleValue = SWTObservables.observeSelection(singleModeRadio);
        final IObservableValue oneRowValue = SWTObservables.observeSelection(oneRowModeRadio);
        final IObservableValue oneColValue = SWTObservables.observeSelection(nRowModeRadio);
        final IObservableValue tableValue = SWTObservables.observeSelection(tableModeRadio);
        scriptValue = SWTObservables.observeSelection(scriptModeRadio);
        graphicalModeSelectionValue = SWTObservables.observeSelection(gModeRadio);

        radioGroupObservable = new SelectObservableValue(String.class);
        radioGroupObservable.addOption(SINGLE, singleValue);
        radioGroupObservable.addOption(ONE_ROW, oneRowValue);
        radioGroupObservable.addOption(N_ROW, oneColValue);
        radioGroupObservable.addOption(TABLE, tableValue);
        radioGroupObservable.addOption(null, scriptValue);

        context.bindValue(SWTObservables.observeEnabled(alwaysUseScriptCheckbox), scriptValue);
        final UpdateValueStrategy deselectStrategy = new UpdateValueStrategy();
        deselectStrategy.setConverter(new Converter(Boolean.class, Boolean.class) {

            @Override
            public Object convert(Object fromObject) {
                return false;
            }
        });

        context.bindValue(graphicalModeSelectionValue, SWTObservables.observeSelection(alwaysUseScriptCheckbox),
                deselectStrategy, new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER));
        context.bindValue(graphicalModeSelectionValue, SWTObservables.observeEnabled(choicesComposite));

        final UpdateValueStrategy disabledStrategy = new UpdateValueStrategy();
        disabledStrategy.setConverter(new Converter(Boolean.class, Boolean.class) {

            @Override
            public Object convert(Object fromObject) {
                if ((outputTypeExpression.getContent() == null && fromObject != null && !(Boolean) fromObject)
                        || SQLQueryUtil.getSelectedColumns(scriptExpression).size() != 1
                                && !SQLQueryUtil.useWildcard(scriptExpression)) {
                    return false;
                }
                return fromObject;
            }
        });

        final UpdateValueStrategy disabledStrategy2 = new UpdateValueStrategy();
        disabledStrategy2.setConverter(new Converter(Boolean.class, Boolean.class) {

            @Override
            public Object convert(Object fromObject) {
                if ((outputTypeExpression.getContent() == null && fromObject != null && !(Boolean) fromObject)
                        || SQLQueryUtil.useWildcard(scriptExpression)) {
                    return false;
                }
                return fromObject;
            }
        });

        radioGroupObservable.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(ValueChangeEvent event) {
                final IWizardPage p = getNextPage();
                if (p instanceof DatabaseConnectorOutputWizardPage) {
                    ((DatabaseConnectorOutputWizardPage) p).updateOutputs((String) event.getObservableValue().getValue());
                }
            }
        });

        context.bindValue(radioGroupObservable,
                EMFObservables.observeValue(outputTypeExpression, ExpressionPackage.Literals.EXPRESSION__CONTENT));
        graphicalModeSelectionValue.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(ValueChangeEvent event) {
                if ((Boolean) event.getObservableValue().getValue()) {
                    if (singleModeRadio.getSelection()) {
                        radioGroupObservable.setValue(SINGLE);
                    } else if (oneRowModeRadio.getSelection()) {
                        radioGroupObservable.setValue(ONE_ROW);
                    } else if (nRowModeRadio.getSelection()) {
                        radioGroupObservable.setValue(N_ROW);
                    } else {
                        radioGroupObservable.setValue(TABLE);
                    }
                    updateEnabledChoices();
                } else {
                    singleModeRadioObserveEnabled.setValue(false);
                    nRowsOneColModeRadioObserveEnabled.setValue(false);
                    oneRowNColModeRadioObserveEnabled.setValue(false);
                    tableObserveEnabled.setValue(false);
                }

            }
        });

        parseQuery();

        return mainComposite;
    }

    protected void createSelectModeLabelControl(final Composite mainComposite) {
        final Label selectLabel = new Label(mainComposite, SWT.NONE);
        selectLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        selectLabel.setText(Messages.selectConnectorOutputMode);
        selectLabel.setFont(BonitaStudioFontRegistry.getHighlightedFont());
    }

    protected void parseQuery() {
        if (graphicalModeSelectionValue != null) {
            final IObservableValue enableGraphicalMode = SWTObservables.observeEnabled(gModeRadio);
            if (SQLQueryUtil.isGraphicalModeSupportedFor(scriptExpression)) {
                enableGraphicalMode.setValue(true);
                if (!editing) {
                    graphicalModeSelectionValue.setValue(true);
                    if (outputTypeExpression.getContent() != null) {
                        radioGroupObservable.setValue(outputTypeExpression.getContent());
                    } else {
                        radioGroupObservable.setValue(TABLE);
                    }
                } else if (outputTypeExpression.getContent() == null) {
                    graphicalModeSelectionValue.setValue(false);
                }
                updateEnabledChoices();
            } else {
                enableGraphicalMode.setValue(false);
                graphicalModeSelectionValue.setValue(false);
                scriptValue.setValue(true);
                radioGroupObservable.setValue(null);
            }
        }
    }

    protected void updateEnabledChoices() {
        final String content = outputTypeExpression.getContent();
        if (content != null) {
            if (SQLQueryUtil.getSelectedColumns(scriptExpression).size() != 1
                    && !SQLQueryUtil.useWildcard(scriptExpression)) {
                singleModeRadioObserveEnabled.setValue(false);
                nRowsOneColModeRadioObserveEnabled.setValue(false);
                tableObserveEnabled.setValue(true);
                oneRowNColModeRadioObserveEnabled.setValue(true);
                if (SINGLE.equals(content)
                        || N_ROW.equals(content)) {
                    radioGroupObservable.setValue(TABLE);
                }
            } else if (SQLQueryUtil.useWildcard(scriptExpression)) {
                singleModeRadioObserveEnabled.setValue(false);
                nRowsOneColModeRadioObserveEnabled.setValue(false);
                oneRowNColModeRadioObserveEnabled.setValue(false);
                tableObserveEnabled.setValue(true);
                if (SINGLE.equals(content)
                        || N_ROW.equals(content)
                        || ONE_ROW.equals(content)) {
                    radioGroupObservable.setValue(TABLE);
                }
            } else {
                oneRowNColModeRadioObserveEnabled.setValue(true);
                singleModeRadioObserveEnabled.setValue(true);
                nRowsOneColModeRadioObserveEnabled.setValue(true);
                tableObserveEnabled.setValue(true);
            }
        }
    }

    protected void updateQuery() {
        if (graphicalModeSelectionValue != null) {
            final IObservableValue enableGraphicalMode = SWTObservables.observeEnabled(gModeRadio);
            if (SQLQueryUtil.isGraphicalModeSupportedFor(scriptExpression)) {
                enableGraphicalMode.setValue(true);
                updateEnabledChoices();
            } else {
                enableGraphicalMode.setValue(false);
                graphicalModeSelectionValue.setValue(false);
                scriptValue.setValue(true);
                radioGroupObservable.setValue(null);
            }
        }
    }

    protected Button createScriptModeControl(Composite parent) {
        final Button scriptModeRadio = new Button(parent, SWT.RADIO);
        scriptModeRadio.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(15, 20).create());
        scriptModeRadio.setText(Messages.scriptMode);
        scriptModeRadio.setFont(BonitaStudioFontRegistry.getActiveFont());

        final Composite descriptionComposite = new Composite(parent, SWT.NONE);
        descriptionComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(45, -5).create());
        descriptionComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());

        final Label scriptingDescriptionLabel = new Label(descriptionComposite, SWT.WRAP);
        scriptingDescriptionLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        scriptingDescriptionLabel.setText(Messages.scriptModeDescription);

        alwaysUseScriptCheckbox = new Button(descriptionComposite, SWT.CHECK);
        alwaysUseScriptCheckbox.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        alwaysUseScriptCheckbox.setText(Messages.alwaysUseScriptingMode);
        alwaysUseScriptCheckbox.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                preferenceStore.setValue(BonitaPreferenceConstants.ALWAYS_USE_SCRIPTING_MODE,
                        alwaysUseScriptCheckbox.getSelection());
            }
        });

        return scriptModeRadio;
    }

    protected Composite createGraphicalModeControl(Composite parent,
            EMFDataBindingContext context) {
        gModeRadio = new Button(parent, SWT.RADIO);
        gModeRadio.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(15, 0).create());
        gModeRadio.setText(getAdvancedModeLabel());
        gModeRadio.setFont(BonitaStudioFontRegistry.getActiveFont());

        final Composite choicesComposite = new Composite(parent, SWT.NONE);
        choicesComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(45, -5).create());
        choicesComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());

        createAdvancedModeDescriptionControl(choicesComposite);

        return choicesComposite;
    }

    protected void createAdvancedModeDescriptionControl(
            final Composite choicesComposite) {
        final Label choiceDescriptionLabel = new Label(choicesComposite, SWT.WRAP);
        choiceDescriptionLabel
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(160, SWT.DEFAULT).span(2, 1).create());
        choiceDescriptionLabel.setText(Messages.graphicalModeDescription);
    }

    protected String getAdvancedModeLabel() {
        return Messages.graphicalMode;
    }

    protected Button createSingleChoice(final Composite choicesComposite, EMFDataBindingContext context) {
        final Button singleRadio = new Button(choicesComposite, SWT.RADIO);
        singleRadio.setLayoutData(GridDataFactory.fillDefaults().grab(false, false).create());
        singleRadio.setText(Messages.singleValue);

        final Label singleIcon = new Label(choicesComposite, SWT.NONE);
        singleIcon.setLayoutData(
                GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.CENTER).indent(15, 0).grab(true, false).create());

        final UpdateValueStrategy selectImageStrategy = new UpdateValueStrategy();
        selectImageStrategy.setConverter(new Converter(Boolean.class, Image.class) {

            @Override
            public Object convert(Object fromObject) {
                if (fromObject != null && (Boolean) fromObject) {
                    return Pics.getImage("single_placeholder.png", ConnectorPlugin.getDefault());
                } else {
                    return Pics.getImage("single_placeholder_disabled.png", ConnectorPlugin.getDefault());
                }

            }

        });
        singleModeRadioObserveEnabled = SWTObservables.observeEnabled(singleRadio);
        context.bindValue(SWTObservables.observeImage(singleIcon), singleModeRadioObserveEnabled, null, selectImageStrategy);

        return singleRadio;
    }

    protected Button createOneRowNColsChoice(final Composite choicesComposite, EMFDataBindingContext context) {
        final Button oneRowRadio = new Button(choicesComposite, SWT.RADIO);
        oneRowRadio.setLayoutData(GridDataFactory.fillDefaults().grab(false, false).create());
        oneRowRadio.setText(Messages.oneRowNCol);

        final ControlDecoration oneRowDeco = new ControlDecoration(oneRowRadio, SWT.RIGHT, choicesComposite);
        oneRowDeco.setImage(Pics.getImage(PicsConstants.hint));
        oneRowDeco.setDescriptionText(Messages.oneRowHint);

        final Label oneRowIcon = new Label(choicesComposite, SWT.NONE);
        oneRowIcon.setLayoutData(
                GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.CENTER).indent(15, 0).grab(true, false).create());

        final UpdateValueStrategy selectImageStrategy = new UpdateValueStrategy();
        selectImageStrategy.setConverter(new Converter(Boolean.class, Image.class) {

            @Override
            public Object convert(Object fromObject) {
                if (fromObject != null && (Boolean) fromObject) {
                    return Pics.getImage("row_placeholder.png", ConnectorPlugin.getDefault());
                } else {
                    return Pics.getImage("row_placeholder_disabled.png", ConnectorPlugin.getDefault());
                }

            }

        });
        oneRowNColModeRadioObserveEnabled = SWTObservables.observeEnabled(oneRowRadio);
        context.bindValue(SWTObservables.observeImage(oneRowIcon), oneRowNColModeRadioObserveEnabled, null,
                selectImageStrategy);

        return oneRowRadio;
    }

    protected Button createNRowsOneColChoice(final Composite choicesComposite, EMFDataBindingContext context) {
        final Button oneColRadio = new Button(choicesComposite, SWT.RADIO);
        oneColRadio.setLayoutData(GridDataFactory.fillDefaults().grab(false, false).create());
        oneColRadio.setText(Messages.nRowOneCol);

        final ControlDecoration oneColDeco = new ControlDecoration(oneColRadio, SWT.RIGHT, choicesComposite);
        oneColDeco.setImage(Pics.getImage(PicsConstants.hint));
        oneColDeco.setDescriptionText(Messages.oneColHint);

        final Label oneColIcon = new Label(choicesComposite, SWT.NONE);
        oneColIcon.setLayoutData(
                GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.CENTER).indent(15, 0).grab(true, false).create());

        final UpdateValueStrategy selectImageStrategy = new UpdateValueStrategy();
        selectImageStrategy.setConverter(new Converter(Boolean.class, Image.class) {

            @Override
            public Object convert(Object fromObject) {
                if (fromObject != null && (Boolean) fromObject) {
                    return Pics.getImage("column_placeholder.png", ConnectorPlugin.getDefault());
                } else {
                    return Pics.getImage("column_placeholder_disabled.png", ConnectorPlugin.getDefault());
                }

            }

        });

        nRowsOneColModeRadioObserveEnabled = SWTObservables.observeEnabled(oneColRadio);
        context.bindValue(SWTObservables.observeImage(oneColIcon), nRowsOneColModeRadioObserveEnabled, null,
                selectImageStrategy);

        return oneColRadio;

    }

    protected Button createTableChoice(final Composite choicesComposite, EMFDataBindingContext context) {
        final Button tableRadio = new Button(choicesComposite, SWT.RADIO);
        tableRadio.setLayoutData(GridDataFactory.fillDefaults().grab(false, false).create());
        tableRadio.setText(Messages.nRowsNcolumns);

        final ControlDecoration tableDeco = new ControlDecoration(tableRadio, SWT.RIGHT, choicesComposite);
        tableDeco.setImage(Pics.getImage(PicsConstants.hint));
        tableDeco.setDescriptionText(Messages.tableHint);

        final Label tableIcon = new Label(choicesComposite, SWT.NONE);
        tableIcon.setLayoutData(
                GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.CENTER).indent(15, 0).grab(true, false).create());

        final UpdateValueStrategy selectImageStrategy = new UpdateValueStrategy();
        selectImageStrategy.setConverter(new Converter(Boolean.class, Image.class) {

            @Override
            public Object convert(Object fromObject) {
                if (fromObject != null && (Boolean) fromObject) {
                    return Pics.getImage("table_placeholder.png", ConnectorPlugin.getDefault());
                } else {
                    return Pics.getImage("table_placeholder_disabled.png", ConnectorPlugin.getDefault());
                }

            }

        });
        tableObserveEnabled = SWTObservables.observeEnabled(tableRadio);
        context.bindValue(SWTObservables.observeImage(tableIcon), tableObserveEnabled, null, selectImageStrategy);

        return tableRadio;
    }

    @Override
    public void handleValueChange(ValueChangeEvent event) {
        updateQuery();
    }

    @Override
    public void setPreviousPage(IWizardPage page) {
        this.previousPageBackup = page;
        super.setPreviousPage(page);
    }

    @Override
    public IWizardPage getPreviousPage() {
        if (previousPageBackup != null) {
            return previousPageBackup;
        }

        final IWizard wizard = getWizard();
        if (wizard != null) {
            return wizard.getPreviousPage(this);
        }
        return super.getPreviousPage();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.IBonitaVariableContext#isOverViewContext()
     */
    @Override
    public boolean isOverViewContext() {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.IBonitaVariableContext#setIsOverviewContext(boolean)
     */
    @Override
    public void setIsOverviewContext(boolean isOverviewContext) {
    }

}
