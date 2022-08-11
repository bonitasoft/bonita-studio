/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.connectors.ui.property.section;

import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.ui.widget.ComboWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class SelectDefinitionVersionDialog extends MessageDialog {

    private Collection<ConnectorDefinition> definitions;
    private String version;

    public SelectDefinitionVersionDialog(Shell parentShell, List<ConnectorDefinition> definitions) {
        super(parentShell, Messages.selectTargetDefinitionVersionTitle,
                null,
                Messages.selectTargetDefinitionVersionMessage,
                MessageDialog.NONE,
                1,
                IDialogConstants.CANCEL_LABEL, IDialogConstants.NEXT_LABEL);
        this.definitions = definitions;
        this.version = definitions.get(0).getVersion();
    }

    @Override
    protected Button createButton(Composite parent, int id, String label, boolean defaultButton) {
        if (IDialogConstants.NEXT_LABEL.equals(label)) {
            return super.createButton(parent, IDialogConstants.NEXT_ID, label, defaultButton);
        }
        if (IDialogConstants.CANCEL_LABEL.equals(label)) {
            return super.createButton(parent, IDialogConstants.CANCEL_ID, label, defaultButton);
        }
        return super.createButton(parent, id, label, defaultButton);
    }
    
    @Override
    protected Control createCustomArea(Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);
        container.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        container.setLayout(GridLayoutFactory.fillDefaults().create());

        var dbc = new DataBindingContext();
        
        new ComboWidget.Builder()
                .withLabel(Messages.version)
                .withItems(definitions.stream().map(ConnectorDefinition::getVersion).toArray(String[]::new))
                .grabHorizontalSpace()
                .readOnly()
                .widthHint(200)
                .bindTo(PojoProperties.value("version", String.class).observe(this))
                .inContext(dbc)
                .createIn(container);

        return container;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
