/*
 * Copyright (C) 2009-2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.preferences.pages;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.connector.model.definition.provider.ConnectorEditPlugin;
import org.bonitasoft.studio.connector.model.definition.wizard.AbstractDefinitionWizard;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.preferences.extension.IPreferenceFieldEditorContribution;
import org.bonitasoft.studio.preferences.i18n.Messages;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * @author Romain Bioteau 
 *
 */

public class BonitaAdvancedPreferencePage extends AbstractBonitaPreferencePage implements IWorkbenchPreferencePage {


	private BooleanFieldEditor askSaveDiagramAfterFirstSave;
	private List<IPreferenceFieldEditorContribution> contributions;
	private BooleanFieldEditor showConnectorEditionConfirmation;

	public BonitaAdvancedPreferencePage() {
		super(GRID);
		setPreferenceStore(BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore());
		contributions = new ArrayList<IPreferenceFieldEditorContribution>();
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common
	 * GUI blocks needed to manipulate various types of preferences. Each field
	 * editor knows how to save and restore itself.
	 */
	public void createFieldEditors() {

		createTitleBar(Messages.BonitaPreferenceDialog_Advanced, Pics.getImage(PicsConstants.preferenceAdvanced),false) ;

		askSaveDiagramAfterFirstSave = new BooleanFieldEditor(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE, Messages.askRenameDiagram, getFieldEditorParent());
		addField(askSaveDiagramAfterFirstSave);
		
		showConnectorEditionConfirmation = new BooleanFieldEditor(AbstractDefinitionWizard.HIDE_CONNECTOR_DEFINITION_CHANGE_WARNING, Messages.doNotDisplayConnectorDefConfirmationMessage, getFieldEditorParent());
		addField(showConnectorEditionConfirmation);

		IConfigurationElement[] elems = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements("org.bonitasoft.studio.preferences.prefrenceFieldEditorContribution"); //$NON-NLS-1$
		IPreferenceFieldEditorContribution prefEditorContrib = null;
		for (IConfigurationElement elem : elems){
			try {
				prefEditorContrib = (IPreferenceFieldEditorContribution) elem.createExecutableExtension("class"); //$NON-NLS-1$
			} catch (CoreException e) {
				BonitaStudioLog.error(e);
			} 
			if(prefEditorContrib.appliesTo("Advanced")){
				new Label(getFieldEditorParent(),SWT.NONE) ;
				new Label(getFieldEditorParent(),SWT.NONE) ;

				Label separator = new Label(getFieldEditorParent(),SWT.SEPARATOR | SWT.HORIZONTAL) ;
				separator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create()) ;

				new Label(getFieldEditorParent(),SWT.NONE) ;
				new Label(getFieldEditorParent(),SWT.NONE) ;

				contributions.add(prefEditorContrib) ;
				for(FieldEditor fe : prefEditorContrib.createFieldEditors(getFieldEditorParent())){
					addField(fe);
				}
			}
		}

	}


	@Override
	public boolean performOk() {
		boolean ok = true ;
		for(IPreferenceFieldEditorContribution contrib : contributions){
			if(ok){
				ok = contrib.performOk();
			}
		}
		return super.performOk() && ok ;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
		for(IPreferenceFieldEditorContribution contrib : contributions){
			contrib.init(workbench) ;
		}
	}
	
	@Override
	protected void initialize() {
		super.initialize();
		if(showConnectorEditionConfirmation != null){
			showConnectorEditionConfirmation.setPreferenceStore(ConnectorEditPlugin.getPlugin().getPreferenceStore());
			showConnectorEditionConfirmation.load();
		}
	}


}