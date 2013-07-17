/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

/**
 * @author Romain Bioteau
 *
 */
public class SelectDatabaseOutputTypeWizardPage extends AbstractConnectorConfigurationWizardPage implements DatabaseConnectorConstants, IValueChangeListener{



	private Expression outputTypeExpression;
	private IPreferenceStore preferenceStore;
	private Expression scriptExpression;
	private Button gModeRadio;
	private Button alwaysUseScriptCheckbox;
	private SelectObservableValue radioGroupObservable;
	private ISWTObservableValue graphicalModeSelectionValue;
	private boolean editing;
	private ISWTObservableValue singleModeRadioObserveEnabled;
	private ISWTObservableValue nRowsOneColModeRadioObserveEnabled;
	private ISWTObservableValue oneRowNColModeRadioObserveEnabled;
	private IWizardPage previousPageBackup;

	public SelectDatabaseOutputTypeWizardPage(boolean editing) {
		super(SelectDatabaseOutputTypeWizardPage.class.getName());
		setTitle(Messages.outputOperationsDefinitionTitle);
		setDescription(Messages.outputOperationsDefinitionDesc);
		this.editing = editing;
		preferenceStore = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore();
	}


	@Override
	protected Control doCreateControl(Composite parent,	EMFDataBindingContext context) {
		final Composite mainComposite = new Composite(parent, SWT.NONE);
		mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).extendedMargins(10, 10, 5, -50).create());

		final Label selectLabel = new Label(mainComposite, SWT.NONE);
		selectLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		selectLabel.setText(Messages.selectConnectorOutputMode);
		selectLabel.setFont(BonitaStudioFontRegistry.getHighlightedFont());

		scriptExpression = (Expression) getConnectorParameter(getInput(SCRIPT_KEY)).getExpression();
		IObservableValue scriptContentValue = EMFObservables.observeValue(scriptExpression, ExpressionPackage.Literals.EXPRESSION__CONTENT);
		scriptContentValue.addValueChangeListener(this);
		outputTypeExpression = (Expression) getConnectorParameter(getInput(OUTPUT_TYPE_KEY)).getExpression();
		outputTypeExpression.setName(OUTPUT_TYPE_KEY);

		final Composite choicesComposite = createGraphicalModeControl(mainComposite,context);
		final Button singleModeRadio = createSingleChoice(choicesComposite,context);
		final Button oneRowModeRadio = createOneRowNColsChoice(choicesComposite,context);
		final Button nRowModeRadio = createNRowsOneColChoice(choicesComposite,context);
		final Button tableModeRadio = createTableChoice(choicesComposite,context);
		final Button scriptModeRadio = createScriptModeControl(mainComposite);

		final IObservableValue singleValue = SWTObservables.observeSelection(singleModeRadio);
		final IObservableValue oneRowValue = SWTObservables.observeSelection(oneRowModeRadio);
		final IObservableValue oneColValue = SWTObservables.observeSelection(nRowModeRadio);
		final IObservableValue tableValue = SWTObservables.observeSelection(tableModeRadio);
		final IObservableValue scriptValue = SWTObservables.observeSelection(scriptModeRadio);
		graphicalModeSelectionValue = SWTObservables.observeSelection(gModeRadio);

		radioGroupObservable = new SelectObservableValue(String.class);
		radioGroupObservable.addOption(SINGLE, singleValue);
		radioGroupObservable.addOption(ONE_ROW, oneRowValue);
		radioGroupObservable.addOption(N_ROW, oneColValue);
		radioGroupObservable.addOption(TABLE, tableValue);
		radioGroupObservable.addOption(null, scriptValue);
		radioGroupObservable.addValueChangeListener(new IValueChangeListener() {
			
			@Override
			public void handleValueChange(ValueChangeEvent event) {
				IWizardPage p = getNextPage();
				if(p instanceof DatabaseConnectorOutputWizardPage){
					((DatabaseConnectorOutputWizardPage)p).updateOutputs((String) event.getObservableValue().getValue());
				}
			}
		});

		context.bindValue(radioGroupObservable, EMFObservables.observeValue(outputTypeExpression, ExpressionPackage.Literals.EXPRESSION__CONTENT));
		context.bindValue(SWTObservables.observeEnabled(alwaysUseScriptCheckbox), SWTObservables.observeSelection(scriptModeRadio));
		final UpdateValueStrategy deselectStrategy = new UpdateValueStrategy();
		deselectStrategy.setConverter(new Converter(Boolean.class,Boolean.class) {

			@Override
			public Object convert(Object fromObject) {
				return false;
			}
		});

		context.bindValue(graphicalModeSelectionValue,SWTObservables.observeSelection(alwaysUseScriptCheckbox),deselectStrategy, new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER));
		context.bindValue(graphicalModeSelectionValue,SWTObservables.observeEnabled(choicesComposite));
		singleModeRadioObserveEnabled = SWTObservables.observeEnabled(singleModeRadio);

		final UpdateValueStrategy disabledStrategy = new UpdateValueStrategy();
		disabledStrategy.setConverter(new Converter(Boolean.class,Boolean.class) {

			@Override
			public Object convert(Object fromObject) {
				if((outputTypeExpression.getContent() == null && !(Boolean)fromObject) || SQLQueryUtil.getSelectedColumns(scriptExpression).size() != 1 && !SQLQueryUtil.useWildcard(scriptExpression)){
					return false;
				}
				return fromObject;
			}
		});
		
		final UpdateValueStrategy disabledStrategy2 = new UpdateValueStrategy();
		disabledStrategy2.setConverter(new Converter(Boolean.class,Boolean.class) {

			@Override
			public Object convert(Object fromObject) {
				if((outputTypeExpression.getContent() == null && !(Boolean)fromObject) || SQLQueryUtil.useWildcard(scriptExpression)){
					return false;
				}
				return fromObject;
			}
		});
		
		context.bindValue(graphicalModeSelectionValue,SWTObservables.observeEnabled(singleModeRadio),disabledStrategy,null);

		oneRowNColModeRadioObserveEnabled = SWTObservables.observeEnabled(oneRowModeRadio);
		context.bindValue(graphicalModeSelectionValue,SWTObservables.observeEnabled(oneRowModeRadio),disabledStrategy2,null);
		nRowsOneColModeRadioObserveEnabled = SWTObservables.observeEnabled(nRowModeRadio);
		context.bindValue(graphicalModeSelectionValue,SWTObservables.observeEnabled(nRowModeRadio),disabledStrategy,null);
		context.bindValue(graphicalModeSelectionValue,SWTObservables.observeEnabled(tableModeRadio));

		graphicalModeSelectionValue.addValueChangeListener(new IValueChangeListener() {

			@Override
			public void handleValueChange(ValueChangeEvent event) {
				if((Boolean)event.getObservableValue().getValue()){
					if(singleModeRadio.getSelection()){
						radioGroupObservable.setValue(SINGLE);
					}else if(oneRowModeRadio.getSelection()){
						radioGroupObservable.setValue(ONE_ROW);
					}else if(nRowModeRadio.getSelection()){
						radioGroupObservable.setValue(N_ROW);
					}else{
						radioGroupObservable.setValue(TABLE);
					}
				}

			}
		});

		parseQuery();

		return mainComposite;
	}

	protected void parseQuery() {
		if(graphicalModeSelectionValue != null){
			IObservableValue enableGraphicalMode = SWTObservables.observeEnabled(gModeRadio);
			if(SQLQueryUtil.isGraphicalModeSupportedFor(scriptExpression)){
				enableGraphicalMode.setValue(true);
				if(!editing){
					graphicalModeSelectionValue.setValue(true);
					if(outputTypeExpression.getContent() != null){
						radioGroupObservable.setValue(outputTypeExpression.getContent());
					}else{
						radioGroupObservable.setValue(TABLE);
					}
				}else if(outputTypeExpression.getContent() == null){
					graphicalModeSelectionValue.setValue(false);
				}
				updateEnabledChoices();
			}else{
				enableGraphicalMode.setValue(false);
				graphicalModeSelectionValue.setValue(false);
				radioGroupObservable.setValue(null);
			}
		}
	}


	protected void updateEnabledChoices() {
		if(SQLQueryUtil.getSelectedColumns(scriptExpression).size() != 1 && !SQLQueryUtil.useWildcard(scriptExpression)){
			singleModeRadioObserveEnabled.setValue(false);
			nRowsOneColModeRadioObserveEnabled.setValue(false);
			if(SINGLE.equals(outputTypeExpression.getContent()) 
					|| N_ROW.equals(outputTypeExpression.getContent())
					|| ONE_ROW.equals(outputTypeExpression.getContent())){
				radioGroupObservable.setValue(TABLE);
			}
		}else if(SQLQueryUtil.useWildcard(scriptExpression)){
			singleModeRadioObserveEnabled.setValue(false);
			nRowsOneColModeRadioObserveEnabled.setValue(false);
			oneRowNColModeRadioObserveEnabled.setValue(false);
			if(SINGLE.equals(outputTypeExpression.getContent()) 
					|| N_ROW.equals(outputTypeExpression.getContent())
					|| ONE_ROW.equals(outputTypeExpression.getContent())){
				radioGroupObservable.setValue(TABLE);
			}
		}else if(outputTypeExpression.getContent() != null){
			oneRowNColModeRadioObserveEnabled.setValue(true);
			singleModeRadioObserveEnabled.setValue(true);
			nRowsOneColModeRadioObserveEnabled.setValue(true);
		}
	}

	protected void updateQuery() {
		if(graphicalModeSelectionValue != null){
			IObservableValue enableGraphicalMode = SWTObservables.observeEnabled(gModeRadio);
			if(SQLQueryUtil.isGraphicalModeSupportedFor(scriptExpression)){
				enableGraphicalMode.setValue(true);
				updateEnabledChoices();
			}else{
				enableGraphicalMode.setValue(false);
				graphicalModeSelectionValue.setValue(false);
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
				preferenceStore.setValue(BonitaPreferenceConstants.ALWAYS_USE_SCRIPTING_MODE, alwaysUseScriptCheckbox.getSelection());
			}
		});

		return scriptModeRadio;
	}

	protected Composite createGraphicalModeControl(Composite parent,
			EMFDataBindingContext context) {
		gModeRadio = new Button(parent, SWT.RADIO);
		gModeRadio.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(15, 0).create());
		gModeRadio.setText(Messages.graphicalMode);
		gModeRadio.setFont(BonitaStudioFontRegistry.getActiveFont());

		final Composite choicesComposite = new Composite(parent, SWT.NONE);
		choicesComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(45, -5).create());
		choicesComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());

		final Label choiceDescriptionLabel = new Label(choicesComposite, SWT.WRAP);
		choiceDescriptionLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(160, SWT.DEFAULT).span(2, 1).create());
		choiceDescriptionLabel.setText(Messages.graphicalModeDescription);

		return choicesComposite;
	}

	protected Button createSingleChoice(final Composite choicesComposite, EMFDataBindingContext context) {
		final Button singleRadio = new Button(choicesComposite, SWT.RADIO);
		singleRadio.setLayoutData(GridDataFactory.fillDefaults().grab(false, false).create());
		singleRadio.setText(Messages.singleValue);

		final Label singleIcon = new Label(choicesComposite, SWT.NONE);
		singleIcon.setLayoutData(GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.CENTER).indent(15, 0).grab(true, false).create());
		singleIcon.setImage(Pics.getImage("single_placeholder.png",ConnectorPlugin.getDefault()));

		return singleRadio;
	}

	protected Button createOneRowNColsChoice(final Composite choicesComposite, EMFDataBindingContext context) {
		final Button oneRowRadio = new Button(choicesComposite, SWT.RADIO);
		oneRowRadio.setLayoutData(GridDataFactory.fillDefaults().grab(false, false).create());
		oneRowRadio.setText(Messages.oneRowNCol);

		final ControlDecoration oneRowDeco = new ControlDecoration(oneRowRadio, SWT.RIGHT);
		oneRowDeco.setImage(Pics.getImage(PicsConstants.hint));
		oneRowDeco.setDescriptionText(Messages.oneRowHint);

		final Label oneRowIcon = new Label(choicesComposite, SWT.NONE);
		oneRowIcon.setLayoutData(GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.CENTER).indent(15, 0).grab(true, false).create());
		oneRowIcon.setImage(Pics.getImage("row_placeholder.png",ConnectorPlugin.getDefault()));

		return oneRowRadio;
	}

	protected Button createNRowsOneColChoice(final Composite choicesComposite, EMFDataBindingContext context) {
		final Button oneColRadio = new Button(choicesComposite, SWT.RADIO);
		oneColRadio.setLayoutData(GridDataFactory.fillDefaults().grab(false, false).create());
		oneColRadio.setText(Messages.nRowOneCol);

		final ControlDecoration oneColDeco = new ControlDecoration(oneColRadio, SWT.RIGHT);
		oneColDeco.setImage(Pics.getImage(PicsConstants.hint));
		oneColDeco.setDescriptionText(Messages.oneColHint);

		final Label oneColIcon = new Label(choicesComposite, SWT.NONE);
		oneColIcon.setLayoutData(GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.CENTER).indent(15, 0).grab(true, false).create());
		oneColIcon.setImage(Pics.getImage("column_placeholder.png",ConnectorPlugin.getDefault()));

		return oneColRadio;

	}

	protected Button createTableChoice(final Composite choicesComposite, EMFDataBindingContext context) {
		final Button tableRadio = new Button(choicesComposite, SWT.RADIO);
		tableRadio.setLayoutData(GridDataFactory.fillDefaults().grab(false, false).create());
		tableRadio.setText(Messages.nRowsNcolumns);

		final ControlDecoration tableDeco = new ControlDecoration(tableRadio, SWT.RIGHT);
		tableDeco.setImage(Pics.getImage(PicsConstants.hint));
		tableDeco.setDescriptionText(Messages.tableHint);

		final Label tableIcon = new Label(choicesComposite, SWT.NONE);
		tableIcon.setLayoutData(GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.CENTER).indent(15, 0).grab(true, false).create());
		tableIcon.setImage(Pics.getImage("table_placeholder.png",ConnectorPlugin.getDefault()));

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
		if(previousPageBackup != null){
			return previousPageBackup;
		}

		IWizard wizard = getWizard();
		if(wizard != null){
			return wizard.getPreviousPage(this);
		}
		return super.getPreviousPage();
	}


}
