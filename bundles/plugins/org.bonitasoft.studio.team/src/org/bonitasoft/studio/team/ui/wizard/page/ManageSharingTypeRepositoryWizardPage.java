/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.ui.wizard.page;

import org.bonitasoft.studio.common.jface.AbstractCheckboxLabelProvider;
import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.team.TeamRepositoryUtil;
import org.bonitasoft.studio.team.i18n.Messages;
import org.bonitasoft.studio.team.preferences.TeamPreferencesConstants;
import org.bonitasoft.studio.team.repository.Repository;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

/**
 * @author Aurelien Pupier
 */
public class ManageSharingTypeRepositoryWizardPage extends WizardPage {

    class CustomLabelProvider extends CellLabelProvider implements ILabelProvider {

        @Override
        public void update(final ViewerCell cell) {
            final IRepository repository = (IRepository) cell.getElement();
            cell.setText(repository.getName());
        }

        @Override
        public Image getImage(final Object element) {
            return null;
        }

        @Override
        public String getText(final Object element) {
            final IRepository repository = (IRepository) element;
            return repository.getName();
        }

    }

    private static final String CHECKED_KEY = "checkedKey";//NON-NLS-1
    private static final String UNCHECK_KEY = "uncheckKey";//NON-NLS-1
    private DataBindingContext context;

    public ManageSharingTypeRepositoryWizardPage() {
        super(Messages.manageSharingTypeRepositoryWIzardpageTitle);
        setImageDescriptor(Pics.getWizban());
        setTitle(Messages.manageSharingTypeRepositoryWIzardpageTitle);
        setDescription(Messages.manageSharingTypeRepositoryWizardPageDescription);
    }

    @Override
    public void createControl(final Composite parent) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(new GridLayout());

        final Button autoCheckbox = createAutoRadioButton(mainComposite);
        final Button dontSynchroAllCheck = createManualRadioButton(mainComposite);
        final Button customSynchro = createCustomRadioButton(mainComposite);
        initButtonState(autoCheckbox, dontSynchroAllCheck, customSynchro);

        final TableViewer viewer = createListOfRepo(mainComposite);

        context = new DataBindingContext();
        context.bindValue(SWTObservables.observeSelection(customSynchro), SWTObservables.observeVisible(viewer.getTable()), null, new UpdateValueStrategy(
                UpdateValueStrategy.POLICY_NEVER));

        setControl(mainComposite);
    }

    protected void initButtonState(final Button autoCheckbox,
            final Button dontSynchroAllCheck, final Button customSynchro) {
        if (TeamRepositoryUtil.isCustomSynchronization()) {
            customSynchro.setSelection(true);
        } else if (TeamRepositoryUtil.isManualAll()) {
            dontSynchroAllCheck.setSelection(true);
        } else if (TeamRepositoryUtil.isSynchroAll()) {
            autoCheckbox.setSelection(true);
        }
    }

    protected Button createCustomRadioButton(final Composite mainComposite) {
        final Button customSynchro = new Button(mainComposite, SWT.RADIO);
        customSynchro.setText(Messages.mixedModeSynchronization);
        customSynchro.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                super.widgetSelected(e);
                if (customSynchro.getSelection()) {
                    TeamRepositoryUtil.setApplyAllState(TeamPreferencesConstants.CUSTOM);
                }
            }
        });
        return customSynchro;
    }

    protected Button createManualRadioButton(final Composite mainComposite) {
        final Button dontSynchroAllCheck = new Button(mainComposite, SWT.RADIO);
        dontSynchroAllCheck.setText(Messages.synchronizeAllManually);
        dontSynchroAllCheck.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                super.widgetSelected(e);
                if (dontSynchroAllCheck.getSelection()) {
                    TeamRepositoryUtil.setApplyAllState(TeamPreferencesConstants.MANUAL);
                }
            }
        });
        return dontSynchroAllCheck;
    }

    protected Button createAutoRadioButton(final Composite mainComposite) {
        final Button autoCheckbox = new Button(mainComposite, SWT.RADIO);
        autoCheckbox.setText(Messages.synchronizeAllAutomatically);
        autoCheckbox.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                super.widgetSelected(e);
                if (autoCheckbox.getSelection()) {
                    TeamRepositoryUtil.setApplyAllState(TeamPreferencesConstants.AUTO);
                }
            }
        });
        return autoCheckbox;
    }

    private TableViewer createListOfRepo(final Composite mainComposite) {
        // TODO create the list with all repos and a checkbox auto/manual
        final TableViewer viewer = new TableViewer(mainComposite);
        final Table table = viewer.getTable();
        table.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        final TableLayout tl = new TableLayout();
        tl.addColumnData(new ColumnWeightData(20));
        tl.addColumnData(new ColumnWeightData(80));
        table.setLayout(tl);
        viewer.setContentProvider(new ArrayContentProvider());

        createColumns(viewer);

        viewer.setInput(TeamRepositoryUtil.getSharedRepositories());
        table.setVisible(TeamRepositoryUtil.isCustomSynchronization());

        return viewer;
    }

    private void createColumns(final TableViewer viewer) {
        createCheckboxColumn(viewer);
        createNameColumn(viewer);

        if (JFaceResources.getImageRegistry().getDescriptor(CHECKED_KEY) == null) {
            JFaceResources.getImageRegistry().put(UNCHECK_KEY,
                    makeShot(viewer.getControl(), false));
            JFaceResources.getImageRegistry().put(CHECKED_KEY,
                    makeShot(viewer.getControl(), true));
        }
    }

    protected void createNameColumn(final TableViewer viewer) {
        final TableViewerColumn nameColumn = new TableViewerColumn(viewer, SWT.NONE);
        nameColumn.getColumn().setText(Messages.repository);
        nameColumn.setLabelProvider(new CustomLabelProvider());

        final TableColumnSorter sorter = new TableColumnSorter(viewer);
        sorter.setColumn(nameColumn.getColumn());
    }

    protected void createCheckboxColumn(final TableViewer viewer) {
        final TableViewerColumn automaticColumn = new TableViewerColumn(viewer, SWT.CENTER);
        automaticColumn.getColumn().setText(Messages.automatic);
        automaticColumn.setLabelProvider(new AbstractCheckboxLabelProvider(automaticColumn.getViewer()) {

            @Override
            protected boolean isSelected(final Object element) {
                final IRepository repository = (IRepository) element;
                if (repository instanceof Repository) {
                    return ((Repository) repository).isAutoShare();
                }
                return false;
            }
        });

        automaticColumn.setEditingSupport(new EditingSupport(viewer) {

            @Override
            protected void setValue(final Object element, final Object value) {
                TeamRepositoryUtil.setAutoShare(((IRepository) element).getName(), (Boolean) value);
                getViewer().update(element, null);
            }

            @Override
            protected Object getValue(final Object element) {
                if (element instanceof Repository) {
                    return Boolean.valueOf(((Repository) element).isAutoShare());
                }
                return Boolean.FALSE;
            }

            @Override
            protected CellEditor getCellEditor(final Object element) {
                return new CheckboxCellEditor(viewer.getTable(), SWT.CHECK);
            }

            @Override
            protected boolean canEdit(final Object element) {
                return true;
            }
        });
    }

    private Image makeShot(final Control control, final boolean type) {
        // Hopefully no platform uses exactly this color because we'll make
        // it transparent in the image.
        final Color greenScreen = new Color(control.getDisplay(), 222, 223, 224);

        final Shell shell = new Shell(control.getShell(), SWT.NO_TRIM);

        // otherwise we have a default gray color
        shell.setBackground(greenScreen);

        final Button button = new Button(shell, SWT.CHECK);
        button.setBackground(greenScreen);
        button.setSelection(type);

        // otherwise an image is located in a corner
        button.setLocation(1, 1);
        final Point bsize = button.computeSize(SWT.DEFAULT, SWT.DEFAULT);

        // otherwise an image is stretched by width
        bsize.x = Math.max(bsize.x - 1, bsize.y - 1);
        bsize.y = Math.max(bsize.x - 1, bsize.y - 1);
        button.setSize(bsize);
        shell.setSize(bsize);

        shell.open();
        final GC gc = new GC(shell);
        final Image image = new Image(control.getDisplay(), bsize.x, bsize.y);
        gc.copyArea(image, 0, 0);
        gc.dispose();
        shell.close();

        final ImageData imageData = image.getImageData();
        imageData.transparentPixel = imageData.palette.getPixel(greenScreen
                .getRGB());

        return new Image(control.getDisplay(), imageData);
    }

    @Override
    public void dispose() {
        super.dispose();
        if (context != null) {
            context.dispose();
        }
    }

}
