/**
 * Copyright (C) 2011-2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.updateValueStrategy;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.fileNameValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.forbiddenCharactersValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.mandatoryValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.maxLengthValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.multiValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.reservedRESTAPIKeywordsValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.utf8InputValidator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.diagram.Identifier;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
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

    private static final int MAX_LENGTH = 75;
    private boolean forceNameUpdate = false;
    private final Set<String> existingFileNames;
    private final Set<Identifier> processIdentifiers;
    private final AbstractProcess abstractProcess;
    private Identifier identifier;
    private DataBindingContext dbc;

    public OpenNameAndVersionDialog(final Shell parentShell, final AbstractProcess abstractProcess,
            final IRepositoryStore<?> diagramStore) {
        super(parentShell);
        this.abstractProcess = abstractProcess;
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
        final Set<String> result = new HashSet<>();
        if (diagramStore.getResource() != null) {
            final String[] files = diagramStore.getResource().getLocation().toFile()
                    .list((arg0, arg1) -> arg1.endsWith(".proc"));
            for (final String f : files) {
                result.add(f);
            }
        }
        return result;
    }

    protected Set<Identifier> computeProcessIdentifiers(
            final IRepositoryStore<? extends IRepositoryFileStore> diagramStore) {
        final Set<Identifier> result = new HashSet<>();
        for (final IRepositoryFileStore irepStore : diagramStore.getChildren()) {
            try {
                result.addAll(newHashSet(
                        transform(ModelHelper.getAllProcesses((Element) irepStore.getContent()), toIdentifier())));
            } catch (final ReadFileStoreException e) {
                BonitaStudioLog.error("Failed read diagram content", e);
            }
        }
        return result;
    }

    private Function<AbstractProcess, Identifier> toIdentifier() {
        return input -> new Identifier(input.getName(), input.getVersion());
    }

    protected List<Identifier> existingProcessIdentifiers() {
        return newArrayList(processIdentifiers);
    }

    @Override
    protected void configureShell(final Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(Messages.openNameAndVersionDialogTitle);
    }

    @Override
    protected Control createDialogArea(final Composite parent) {
        final Composite res = new Composite(parent, SWT.FILL);
        res.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        res.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(15, 15).create());

        createNameAndVersion(res, dbc);
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

    protected void createNameAndVersion(final Composite res, final DataBindingContext dbc) {
        final Label nameLabel = new Label(res, SWT.NONE);
        nameLabel.setText(Messages.name);
        nameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());

        final Text nameText = new Text(res, SWT.BORDER);
        nameText.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, "org.bonitasoft.studio.common.diagram.dialog.name.text");
        nameText.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).hint(200, SWT.DEFAULT).indent(10, 0).create());
        final ISWTObservableValue observeNameText = SWTObservables.observeText(nameText, SWT.Modify);
        ControlDecorationSupport.create(
                dbc.bindValue(observeNameText, PojoProperties.value("name").observe(identifier), nameUpdateStrategy(), null),
                SWT.LEFT);

        final Label versionLabel = new Label(res, SWT.NONE);
        versionLabel.setText(Messages.version);
        final Text versionText = new Text(res, SWT.BORDER);
        versionText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(10, 0).create());
        final ISWTObservableValue observeVersionText = SWTObservables.observeText(versionText, SWT.Modify);
        ControlDecorationSupport.create(dbc.bindValue(observeVersionText,
                PojoProperties.value("version").observe(identifier),
                versionUpdateStrategy(), null), SWT.LEFT);

        if (isForceNameUpdate()) {
            final MustUpdateValidator mustUpdateValidator = new MustUpdateValidator(abstractProcess, observeNameText,
                    observeVersionText);
            dbc.addValidationStatusProvider(mustUpdateValidator);
            ControlDecorationSupport.create(mustUpdateValidator, SWT.LEFT);
        }

        final MultiValidator multiValidator = unicityValidator(observeNameText, observeVersionText);
        dbc.addValidationStatusProvider(multiValidator);
        ControlDecorationSupport.create(multiValidator, SWT.LEFT);

    }

    protected MultiValidator unicityValidator(final ISWTObservableValue observeNameText,
            final ISWTObservableValue observeVersionText) {
        return abstractProcess instanceof MainProcess
                ? new DiagramUnicityValidator((MainProcess) abstractProcess, observeNameText, observeVersionText,
                        existingFileNames)
                : new ProcessUnicityValidator(abstractProcess, observeNameText, observeVersionText,
                        existingProcessIdentifiers());
    }

    private UpdateValueStrategy nameUpdateStrategy() {
        return abstractProcess instanceof MainProcess ? diagramUpdateStrategy(Messages.name)
                : poolUpdateStrategy(Messages.name);
    }

    private UpdateValueStrategy versionUpdateStrategy() {
        return abstractProcess instanceof MainProcess ? diagramUpdateStrategy(Messages.version)
                : poolUpdateStrategy(Messages.version);
    }

    protected UpdateValueStrategy diagramUpdateStrategy(final String fieldName) {
        return updateValueStrategy().withValidator(multiValidator()
                .addValidator(mandatoryValidator(Messages.name))
                .addValidator(maxLengthValidator(Messages.name, MAX_LENGTH))
                .addValidator(fileNameValidator(Messages.name))
                .addValidator(utf8InputValidator(Messages.name))).create();
    }

    protected UpdateValueStrategy poolUpdateStrategy(final String fieldName) {
        return updateValueStrategy().withValidator(multiValidator()
                .addValidator(mandatoryValidator(Messages.name))
                .addValidator(maxLengthValidator(Messages.name, MAX_LENGTH))
                .addValidator(forbiddenCharactersValidator(Messages.name, '#', '%', '$'))
                .addValidator(utf8InputValidator(Messages.name))
                .addValidator(reservedRESTAPIKeywordsValidator())).create();
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(final Identifier identifier) {
        this.identifier = identifier;
    }

}
