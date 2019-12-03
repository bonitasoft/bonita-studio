/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.connector.model.definition.wizard;

import org.bonitasoft.studio.common.repository.model.IDefinitionRepositoryStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 *
 */
public class SaveConnectorConfigurationWizardPage extends SelectConnectorConfigurationWizardPage {

    private String confName;

    public SaveConnectorConfigurationWizardPage(ConnectorConfiguration configuration, IRepositoryStore<? extends IRepositoryFileStore> configurationStore,
            IDefinitionRepositoryStore defStore) {
        super(configuration, configurationStore, defStore);
        setTitle(Messages.saveConfigurationWizardPageTitle);
        setDescription(Messages.bind(Messages.saveConfigurationWizardPageDesc,configuration.getDefinitionId()+ " ("+configuration.getVersion()+")"));
    }


    @Override
    protected void doCreateControl(Composite composite) {
        super.doCreateControl(composite);
        final Composite saveComposite = new Composite(composite, SWT.NONE) ;
        saveComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true,false).create()) ;
        saveComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 10).create()) ;

        final Label nameLabel = new Label(saveComposite, SWT.NONE) ;
        nameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
        nameLabel.setText(Messages.name +" *") ;

        final Text nameText = new Text(saveComposite, SWT.BORDER) ;
        nameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;

        final UpdateValueStrategy targetToModel = new UpdateValueStrategy() ;
        targetToModel.setBeforeSetValidator(new IValidator() {

            @Override
            public IStatus validate(Object newText) {
                if(newText == null || newText.toString().isEmpty()){
                    return ValidationStatus.error(Messages.nameIsEmpty);
                }

                if(configurationStore.getChild(newText+"."+configurationStore.getCompatibleExtensions().iterator().next(), true) != null){
                    return ValidationStatus.error(Messages.nameAlreadyExists) ;
                }
                return Status.OK_STATUS;
            }
        }) ;
        context.bindValue(SWTObservables.observeText(nameText, SWT.Modify), PojoProperties.value(SaveConnectorConfigurationWizardPage.class, "confName").observe(this), targetToModel, null) ;

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.connector.model.definition.wizard.SelectConnectorConfigurationWizardPage#targetUpdateValueStrategy()
     */
    @Override
    protected UpdateValueStrategy targetUpdateValueStrategy() {
        return null;
    }


    public String getConfName() {
        return confName;
    }


    public void setConfName(String confName) {
        this.confName = confName;
    }

}
