/**
 * Copyright (C) 2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.diagram.dialog;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.jface.databinding.DialogSupport;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class OpenNameAndVersionForDiagramDialog extends OpenNameAndVersionDialog {

    private final List<ProcessesNameVersion> pools = new ArrayList<>();
    private DataBindingContext dbc;

    public OpenNameAndVersionForDiagramDialog(final Shell parentShell, final MainProcess diagram,
            final IRepositoryStore<?> diagramStore) {
        super(parentShell, diagram, diagramStore);
        for (final AbstractProcess pool : ModelHelper.getAllProcesses(diagram)) {
            pools.add(new ProcessesNameVersion(pool));
        }
    }

    @Override
    protected Control createDialogArea(final Composite parent) {
        final Composite res = new Composite(parent, SWT.FILL);
        res.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        res.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(15, 15).create());

        createDiagramComposite(res, dbc);
        createProcessesNameAndVersion(res, dbc);
        return res;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.Dialog#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents(final Composite parent) {
        dbc = new DataBindingContext();
        final Control contents = super.createContents(parent);
        DialogSupport.create(this, dbc);
        return contents;
    }

    private void createDiagramComposite(final Composite res, final DataBindingContext dbc) {
        final Group diagramGroup = new Group(res, SWT.NONE);
        diagramGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create());
        diagramGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        diagramGroup.setText(Messages.diagram);
        createNameAndVersion(diagramGroup, dbc);
    }

    void createPNVComposite(final Composite parent, final ProcessesNameVersion pnv, final DataBindingContext dbc) {
        final Composite pnvCompo = new Composite(parent, SWT.NONE);
        pnvCompo.setLayout(GridLayoutFactory.fillDefaults().equalWidth(false).numColumns(4).create());
        pnvCompo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        final Label poolNameLabel = new Label(pnvCompo, SWT.NONE);
        poolNameLabel.setText(Messages.name);
        final Text poolNameText = new Text(pnvCompo, SWT.BORDER);
        poolNameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(200, SWT.DEFAULT).create());
        poolNameText.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY,
                "org.bonitasoft.studio.common.diagram.dialog.poolName.text");

        final ISWTObservableValue observePoolNameText = SWTObservables.observeText(poolNameText, SWT.Modify);
        ControlDecorationSupport.create(dbc.bindValue(observePoolNameText,
                PojoProperties.value("newName").observe(pnv),
                poolUpdateStrategy(Messages.name),
                null), SWT.LEFT);

        final Label poolVersion = new Label(pnvCompo, SWT.NONE);
        poolVersion.setText(Messages.version);
        final Text poolVersionText = new Text(pnvCompo, SWT.BORDER);
        poolVersionText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(100, SWT.DEFAULT).create());

        final ISWTObservableValue observePoolVersionText = SWTObservables.observeText(poolVersionText, SWT.Modify);
        ControlDecorationSupport.create(dbc.bindValue(observePoolVersionText,
                PojoProperties.value("newVersion").observe(pnv),
                poolUpdateStrategy(Messages.version),
                null), SWT.LEFT);

        if (isForceNameUpdate()) {
            final MustUpdateValidator mustUpdateValidator = new MustUpdateValidator(pnv.getAbstractProcess(),
                    observePoolNameText, observePoolVersionText);
            dbc.addValidationStatusProvider(mustUpdateValidator);
            ControlDecorationSupport.create(mustUpdateValidator, SWT.LEFT);
        }
        final MultiValidator processesNameVersionUnicityValidator = new ProcessesNameVersionUnicityValidator(
                pnv.getAbstractProcess(), observePoolNameText,
                observePoolVersionText, existingProcessIdentifiers(), pools);
        dbc.addValidationStatusProvider(processesNameVersionUnicityValidator);
        ControlDecorationSupport.create(processesNameVersionUnicityValidator, SWT.LEFT);

    }

    protected void createProcessesNameAndVersion(final Composite res, final DataBindingContext dbc) {
        final Group poolGroup = new Group(res, SWT.NONE);
        poolGroup.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(true, false).create());
        poolGroup.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());
        poolGroup.setText(Messages.pools);

        for (final ProcessesNameVersion pnv : pools) {
            createPNVComposite(poolGroup, pnv, dbc);
        }
    }

    public List<ProcessesNameVersion> getPools() {
        return pools;
    }

}
