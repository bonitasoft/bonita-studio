/**
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.sqlbuilder.ex.wizard;

import java.io.File;
import java.io.IOException;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.sqlbuilder.ex.Messages;
import org.bonitasoft.studio.sqlbuilder.ex.SQLBuilderExPlugin;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 *
 */
public class ConnectionInfoDialog extends Dialog {

	private String className;
	private String jdbcUrl;
	private String username;
	private String password;
	private DataBindingContext context;
	private final DialogSettings dialogSettings;
	private final String filename;
	private final String connectorDefId;

	protected ConnectionInfoDialog(final Shell parentShell, final int sheet,final String connectorDefId, final String className,final String jdbcUrl,final String username,final String password) {
		super(parentShell);
		final IPath path = SQLBuilderExPlugin.getDefault().getStateLocation();
		this.connectorDefId = connectorDefId;
		filename = path.append(this.connectorDefId+".txt").toOSString();
		dialogSettings = new DialogSettings(this.connectorDefId);
		try {
			if(new File(filename).exists()){
				dialogSettings.load(filename);
			}
		} catch (final IOException e) {
			BonitaStudioLog.error(e, SQLBuilderExPlugin.PLUGIN_ID);
		}

		final String storeClassName =  dialogSettings.get("driver");
		final String storURLName =  dialogSettings.get("url");
		final String storeUserName =  dialogSettings.get("username");
		final String storePasswordName =  dialogSettings.get("password");

		this.className = storeClassName == null || storeClassName.isEmpty() ?className:storeClassName;
		this.jdbcUrl = storURLName == null || storURLName.isEmpty() ? jdbcUrl:storURLName;
		this.username = storeUserName == null || storeUserName.isEmpty() ? username:storeUserName;
		this.password = storePasswordName == null || storePasswordName.isEmpty() ? password:storePasswordName;
	}

    @Override
    protected void configureShell(final Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(Messages.connectionInfoDialogTitle);
    }

	@Override
	protected Control createDialogArea(final Composite parent) {
		context = new DataBindingContext();
		final Composite dialogArea = (Composite) super.createDialogArea(parent);
		dialogArea.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).extendedMargins(10, 10, 10, 10).create());

		final IObservableValue classObservable = PojoProperties.value("className").observe(this);
		createField(dialogArea,Messages.className,classObservable,SWT.BORDER);

		final IObservableValue jdbcObservable = PojoProperties.value("jdbcUrl").observe(this);
		createField(dialogArea,Messages.JDBCUrl,jdbcObservable,SWT.BORDER);

		final IObservableValue userObservable = PojoProperties.value("username").observe(this);
		createField(dialogArea,Messages.username,userObservable,SWT.BORDER);

		final IObservableValue passwordObservable = PojoProperties.value("password").observe(this);
		createField(dialogArea,Messages.password,passwordObservable,SWT.BORDER | SWT.PASSWORD);
		return dialogArea;
	}

	private void createField(final Composite dialogArea, final String label,final IObservableValue modelObservable,final int style) {
		final Label labelControl = new Label(dialogArea, SWT.NONE);
		labelControl.setLayoutData(GridDataFactory.fillDefaults().align(SWT.RIGHT, SWT.CENTER).create());
		labelControl.setText(label);
		final Text textControl = new Text(dialogArea, style);
		textControl.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(250, SWT.DEFAULT).create());
		context.bindValue(SWTObservables.observeText(textControl, SWT.Modify), modelObservable);
	}

	@Override
	public boolean close() {
		context.dispose();
		return super.close();
	}

	@Override
	protected void okPressed() {
		dialogSettings.put("driver", getClassName());
		dialogSettings.put("url", getJdbcUrl());
		dialogSettings.put("username", getUsername());
		dialogSettings.put("password", getPassword());
		try {
			dialogSettings.save(filename);
		} catch (final IOException e) {
			BonitaStudioLog.error(e,SQLBuilderExPlugin.PLUGIN_ID);
		}
		super.okPressed();
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(final String className) {
		this.className = className;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(final String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

}
