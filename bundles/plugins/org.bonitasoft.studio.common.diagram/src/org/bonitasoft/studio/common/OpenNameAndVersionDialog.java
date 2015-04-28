/**
 * Copyright (C) 2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.common;

import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Sets.newHashSet;
import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.updateValueStrategy;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.forbiddenCharactersValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.mandatoryValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.maxLengthValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.multiValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.utf8InputValidator;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.databinding.DialogSupport;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.google.common.base.Function;

/**
 * @author Romain Bioteau
 */
public class OpenNameAndVersionDialog extends Dialog {

    private boolean forceNameUpdate = false;
    private final Set<String> existingFileNames;
    private final Set<Identifier> processIdentifiers;
    private final AbstractProcess abstractProcess;
    private Identifier identifier;
    private final IRepositoryStore<?> diagramStore;

    public OpenNameAndVersionDialog(final Shell parentShell, final AbstractProcess abstractProcess,
            final IRepositoryStore<?> diagramStore) {
        super(parentShell);
        this.abstractProcess = abstractProcess;
        this.diagramStore = diagramStore;
        identifier = newIdentifier(abstractProcess);
        existingFileNames = listExistingFileNames(diagramStore);
        processIdentifiers = computeProcessIdentifiers(diagramStore);
    }

    private Identifier newIdentifier(final AbstractProcess process) {
        return new Identifier(process.getName(), process.getVersion());
    }

    public void forceNameUpdate() {
        forceNameUpdate = true;
    }

    public boolean isForceNameUpdate() {
        return forceNameUpdate;
    }

    protected Set<String> listExistingFileNames(final IRepositoryStore<?> diagramStore) {
        final Set<String> result = new HashSet<String>();
        final String[] files = diagramStore.getResource().getLocation().toFile().list(new FilenameFilter() {

            @Override
            public boolean accept(final File arg0, final String arg1) {
                return arg1.endsWith(".proc");
            }
        });
        for (final String f : files) {
            result.add(f.toLowerCase());
        }
        return result;
    }

    protected Set<Identifier> computeProcessIdentifiers(final IRepositoryStore<? extends IRepositoryFileStore> diagramStore) {
        final Set<Identifier> result = new HashSet<Identifier>();
        for (final IRepositoryFileStore irepStore : diagramStore.getChildren()) {
            try {
                result.addAll(newHashSet(transform(ModelHelper.getAllProcesses((Element) irepStore.getContent()), toIdentifier())));
            } catch (final ReadFileStoreException e) {
                BonitaStudioLog.error("Failed read diagram content", e);
            }
        }
        return result;
    }

    private Function<AbstractProcess, Identifier> toIdentifier() {
        return new Function<AbstractProcess, Identifier>() {

            @Override
            public Identifier apply(final AbstractProcess input) {
                return new Identifier(input.getName(), input.getVersion());
            }

        };
    }

    protected Set<Identifier> existingProcessIdentifiers() {
        return processIdentifiers;
    }

    @Override
    protected void configureShell(final Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(Messages.openNameAndVersionDialogTitle);
    }

    @Override
    protected Control createDialogArea(final Composite parent) {
        final DataBindingContext dbc = new DataBindingContext();

        final Composite res = new Composite(parent, SWT.FILL);
        res.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        res.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(15, 15).create());

        createNameAndVersion(res, dbc);

        DialogSupport.create(this, dbc);
        return res;
    }

    protected void createNameAndVersion(final Composite res, final DataBindingContext dbc) {
        final Label nameLabel = new Label(res, SWT.NONE);
        nameLabel.setText(Messages.name);
        nameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());

        final Text nameText = new Text(res, SWT.BORDER);
        nameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(200, SWT.DEFAULT).indent(10, 0).create());
        final ISWTObservableValue observeNameText = SWTObservables.observeText(nameText, SWT.Modify);
        ControlDecorationSupport.create(dbc.bindValue(observeNameText, PojoProperties.value("name").observe(identifier)
                , nameUpdateStrategy(), null), SWT.LEFT);

        final Label versionLabel = new Label(res, SWT.NONE);
        versionLabel.setText(Messages.version);
        final Text versionText = new Text(res, SWT.BORDER);
        versionText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(10, 0).create());
        final ISWTObservableValue observeVersionText = SWTObservables.observeText(versionText, SWT.Modify);
        ControlDecorationSupport.create(dbc.bindValue(observeVersionText,
                PojoProperties.value("version").observe(identifier),
                versionUpdateStrategy()
                , null), SWT.LEFT);

        if (isForceNameUpdate()) {
            final MustUpdateValidator mustUpdateValidator = new MustUpdateValidator(abstractProcess, observeNameText, observeVersionText);
            dbc.addValidationStatusProvider(mustUpdateValidator);
            ControlDecorationSupport.create(mustUpdateValidator, SWT.LEFT);
        }

        final MultiValidator multiValidator = unicityValidator(observeNameText, observeVersionText);
        dbc.addValidationStatusProvider(multiValidator);
        ControlDecorationSupport.create(multiValidator, SWT.LEFT);

    }

    protected MultiValidator unicityValidator(final ISWTObservableValue observeNameText, final ISWTObservableValue observeVersionText) {
        return abstractProcess instanceof MainProcess ? new DiagramUnicityValidator((MainProcess) abstractProcess, observeNameText, observeVersionText,
                existingFileNames,
                diagramStore) : new ProcessUnicityValidator(abstractProcess, observeNameText, observeVersionText,
                existingProcessIdentifiers());
    }

    private UpdateValueStrategy nameUpdateStrategy() {
        return abstractProcess instanceof MainProcess ? diagramNameUpdateStrategy() : poolNameUpdateStrategy();
    }

    private UpdateValueStrategy versionUpdateStrategy() {
        return abstractProcess instanceof MainProcess ? diagramVersionUpdateStrategy() : poolVersionUpdateStrategy();
    }

    protected UpdateValueStrategy diagramVersionUpdateStrategy() {
        return updateValueStrategy().withValidator(multiValidator()
                .addValidator(mandatoryValidator(Messages.version))
                .addValidator(maxLengthValidator(Messages.version, 50))
                .addValidator(forbiddenCharactersValidator(Messages.version, '#', '%', '$', '\\', '/', '"', '*', '?', ':', '<', '>', '|'))
                .addValidator(utf8InputValidator(Messages.version))).create();
    }

    protected UpdateValueStrategy diagramNameUpdateStrategy() {
        return updateValueStrategy().withValidator(multiValidator()
                .addValidator(mandatoryValidator(Messages.name))
                .addValidator(maxLengthValidator(Messages.name, 50))
                .addValidator(forbiddenCharactersValidator(Messages.name, '#', '%', '$', '\\', '/', '"', '*', '?', ':', '<', '>', '|'))
                .addValidator(utf8InputValidator(Messages.name))).create();
    }

    protected UpdateValueStrategy poolNameUpdateStrategy() {
        return updateValueStrategy().withValidator(multiValidator()
                .addValidator(mandatoryValidator(Messages.name))
                .addValidator(maxLengthValidator(Messages.name, 50))
                .addValidator(forbiddenCharactersValidator(Messages.name, '#', '%', '$'))
                .addValidator(utf8InputValidator(Messages.name))).create();
    }

    protected UpdateValueStrategy poolVersionUpdateStrategy() {
        return updateValueStrategy().withValidator(multiValidator()
                .addValidator(mandatoryValidator(Messages.version))
                .addValidator(maxLengthValidator(Messages.version, 50))
                .addValidator(forbiddenCharactersValidator(Messages.version, '#', '%', '$'))
                .addValidator(utf8InputValidator(Messages.version))).create();
    }

    protected String typeLabel() {
        return abstractProcess instanceof MainProcess ? Messages.diagram.toLowerCase() : Messages.Pool_title.toLowerCase();
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(final Identifier identifier) {
        this.identifier = identifier;
    }

}
