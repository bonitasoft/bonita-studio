/**
 * Copyright (C) 2010-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.simulation.wizards;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.common.jface.ElementForIdLabelProvider;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.widgets.DurationComposite;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.simulation.LoadProfile;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.simulation.i18n.Messages;
import org.bonitasoft.studio.simulation.repository.SimulationLoadProfileRepositoryStore;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Baptiste Mesta
 */
public class RunSimulationWizardPage extends WizardPage {

    private Composite pageComposite;
    private Text path;
    private AbstractProcess selectedProcess;
    private Combo combo;
    private ComboViewer processCombo;
    private DurationComposite duration;
    private long timespan;
    private final SimulationLoadProfileRepositoryStore loadProfileStore;

    /**
     * @param selectedProcess
     * @param pageName
     */
    protected RunSimulationWizardPage(final AbstractProcess selectedProcess) {
        super("run simulation");//$NON-NLS-1$
        this.selectedProcess = selectedProcess;
        setTitle(Messages.RunSimulationWizard_Title);
        setMessage(Messages.RunSimulationWizard_Msg);
        setImageDescriptor(Pics.getWizban());
        loadProfileStore = RepositoryManager.getInstance().getRepositoryStore(SimulationLoadProfileRepositoryStore.class);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {

        pageComposite = new Composite(parent, SWT.NONE);
        pageComposite.setLayout(new GridLayout(2, false));
        final Label processToSimulateLabel = new Label(pageComposite, SWT.NONE);
        processToSimulateLabel.setText(Messages.process);
        processCombo = new ComboViewer(new Combo(pageComposite, SWT.BORDER | SWT.READ_ONLY));
        processCombo.setLabelProvider(new ElementForIdLabelProvider());
        MainProcess mainProcess = null;
        final List<AbstractProcess> processes = new ArrayList<AbstractProcess>();
        if (selectedProcess != null) {
            mainProcess = ModelHelper.getMainProcess(selectedProcess);
            for (final Element el : mainProcess.getElements()) {
                if (el instanceof AbstractProcess) {
                    processes.add((AbstractProcess) el);
                }
            }
        }
        processCombo.setContentProvider(ArrayContentProvider.getInstance());
        processCombo.setInput(processes);
        /* Try to select the same process as the last simulation runned */
        if (!processes.isEmpty()) {
            final String lastProcess = getDialogSettings().get(RunSimulationWizard.lastSelectedProcessKey);
            if (lastProcess != null) {
                for (final AbstractProcess process : processes) {
                    if (process.getName().equals(lastProcess)) {
                        processCombo.setSelection(new StructuredSelection(process));
                        break;
                    }
                }
            }
            if (processCombo.getSelection().isEmpty()) {
                processCombo.setSelection(new StructuredSelection(processes.get(0)));
            }
        }

        processCombo.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                selectedProcess = (AbstractProcess) ((StructuredSelection) processCombo.getSelection()).getFirstElement();
            }
        });

        selectedProcess = (AbstractProcess) ((StructuredSelection) processCombo.getSelection()).getFirstElement();
        final Label pathLabel = new Label(pageComposite, SWT.NONE);
        pathLabel.setText(Messages.RunSimulationWizard_path);
        final Composite pathComposite = new Composite(pageComposite, SWT.NONE);
        pathComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        pathComposite.setLayout(new GridLayout(2, false));

        path = new Text(pathComposite, SWT.BORDER);
        path.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).create());

        String property = getDialogSettings().get(RunSimulationWizard.lastPathKey);
        if (property == null) {
            property = System.getProperty("user.home");
        }
        path.setText(new File(property).getAbsolutePath());

        final Button browse = new Button(pathComposite, SWT.FLAT);
        browse.setText(Messages.browse);
        browse.addSelectionListener(new SelectionAdapter() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(final SelectionEvent e) {
                final DirectoryDialog dialog = new DirectoryDialog(Display.getCurrent().getActiveShell(), SWT.OPEN);
                final String newPath = dialog.open();
                if (newPath != null) {
                    path.setText(newPath);
                }
            }
        });

        final Label loadProfileLabel = new Label(pageComposite, SWT.None);
        loadProfileLabel.setText(Messages.loadProfile);

        final Composite loadProfileComposite = new Composite(pageComposite, SWT.NONE);
        loadProfileComposite.setLayout(new GridLayout(3, false));

        combo = new Combo(loadProfileComposite, SWT.BORDER | SWT.READ_ONLY);
        if (selectedProcess.getLoadProfileID() != null) {
            combo.setText(selectedProcess.getLoadProfileID());
        }
        for (final IRepositoryFileStore artifact : loadProfileStore.getChildren()) {
            combo.add(artifact.getDisplayName());
        }

        final Button editLoadProfile = new Button(loadProfileComposite, SWT.FLAT);
        editLoadProfile.setEnabled(false);
        editLoadProfile.setText(Messages.edit);
        editLoadProfile.addSelectionListener(new SelectionAdapter() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(final SelectionEvent e) {

                final IRepositoryFileStore artifact = loadProfileStore.getChild(combo.getText() + "."
                        + SimulationLoadProfileRepositoryStore.SIMULATION_LOADPROFILE_EXT);
                if (artifact != null) {
                    final String oldName = artifact.getName();
                    final EditSimulationLoadProfileWizard wizard = new EditSimulationLoadProfileWizard(artifact);
                    final int code = new CustomWizardDialog(Display.getCurrent().getActiveShell(), wizard).open();
                    long total = 0;
                    try {
                        total = getTotalDuration((LoadProfile) artifact.getContent());
                    } catch (final ReadFileStoreException e1) {
                        BonitaStudioLog.error("Failed to read load profile content", e1);
                    }
                    if (!oldName.equals(artifact.getName())) {
                        combo.remove(oldName);
                        combo.add(artifact.getName());
                        combo.setText(artifact.getName());
                    }

                    if (code == Dialog.OK) {
                        duration.setDuration(total / 12);
                    }

                }

            }
        });
        final Button createLoadProfile = new Button(loadProfileComposite, SWT.FLAT);
        createLoadProfile.setText(Messages.create);
        createLoadProfile.addSelectionListener(new SelectionAdapter() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(final SelectionEvent e) {
                final EditSimulationLoadProfileWizard wizard = new EditSimulationLoadProfileWizard();
                if (new CustomWizardDialog(Display.getCurrent().getActiveShell(), wizard).open() == IDialogConstants.OK_ID) {
                    final String id = wizard.getArtifact().getDisplayName();
                    combo.add(id);
                    combo.setText(id);
                    combo.getParent().pack(true);

                    final IRepositoryFileStore artifact = loadProfileStore.getChild(combo.getText() + "."
                            + SimulationLoadProfileRepositoryStore.SIMULATION_LOADPROFILE_EXT);
                    if (artifact != null) {
                        long total = 0;
                        try {
                            total = getTotalDuration((LoadProfile) artifact.getContent());
                        } catch (final ReadFileStoreException e1) {
                            BonitaStudioLog.error("Failed to read load profile content", e1);
                        }
                        duration.setDuration(total / 12);
                    }

                }

            }
        });

        final Label timespanLabel = new Label(pageComposite, SWT.NONE);
        timespanLabel.setText(Messages.timespan);

        duration = new DurationComposite(pageComposite, false, true, true, true, true, false, null);
        final ControlDecoration controlDecoration = new ControlDecoration(duration, SWT.TOP | SWT.LEFT);
        controlDecoration.setImage(Pics.getImage(PicsConstants.hint));
        controlDecoration.setDescriptionText(Messages.timespanHint);

        combo.addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(final ModifyEvent e) {
                if (combo.getText() != null && combo.getText().trim().length() > 0) {
                    editLoadProfile.setEnabled(true);
                    final IRepositoryFileStore artifact = loadProfileStore.getChild(combo.getText() + "."
                            + SimulationLoadProfileRepositoryStore.SIMULATION_LOADPROFILE_EXT);
                    if (artifact != null) {
                        long total = 0;
                        try {
                            total = getTotalDuration((LoadProfile) artifact.getContent());
                        } catch (final ReadFileStoreException e1) {
                            BonitaStudioLog.error("Failed to read load profile content", e1);
                        }
                        duration.setDuration(total / 12);
                    }
                }

            }
        });
        duration.addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(final ModifyEvent e) {
                timespan = duration.getDuration();
                if (getContainer().getCurrentPage() != null) {
                    getContainer().updateButtons();
                }
            }
        });

        if (combo.getItemCount() > 0) {
            combo.setText(combo.getItem(0));
        }

        setControl(pageComposite);
    }

    private long getTotalDuration(final LoadProfile lp) {
        long start = Long.MAX_VALUE;
        long end = 0;
        for (final org.bonitasoft.studio.model.simulation.InjectionPeriod p : lp.getInjectionPeriods()) {
            if (p.getBegin() < start) {
                start = p.getBegin();
            }
            if (p.getEnd() > end) {
                end = p.getEnd();
            }

        }
        return end - start;
    }

    @Override
    public boolean isPageComplete() {
        setErrorMessage(null);
        if (combo.getText() != null && combo.getText().trim().length() > 0) {
            final IRepositoryFileStore artifact = loadProfileStore.getChild(combo.getText() + "."
                    + SimulationLoadProfileRepositoryStore.SIMULATION_LOADPROFILE_EXT);
            if (artifact != null) {
                long total = 0;
                try {
                    total = getTotalDuration((LoadProfile) artifact.getContent());
                } catch (final ReadFileStoreException e) {
                    BonitaStudioLog.error("Failed to read load profile content", e);
                }
                if (duration.getDuration() > total / 2) {
                    setErrorMessage(Messages.RunSimulationWizardPage_Error_TimespanTooLong);
                    return false;
                } else if (duration.getDuration() < total / 100) {
                    setMessage(Messages.RunSimulationWizardPage_Error_TimespanTooShort, WizardPage.WARNING);
                } else {
                    setMessage(Messages.RunSimulationWizard_Msg);
                }
            }
        }

        return super.isPageComplete();
    };

    /**
     * @return the path
     */
    public String getPath() {
        return path.getText();
    }

    /**
     * @return the path
     */
    public AbstractProcess getProcess() {
        return selectedProcess;
    }

    public String getLoadProfileId() {
        return combo.getText();
    }

    public long getTimespan() {
        return timespan;
    }
}
