/**
 * Copyright (C) 2010-2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.importer.ui.wizard;

import java.io.File;

import org.bonitasoft.studio.importer.ImporterFactory;
import org.bonitasoft.studio.importer.ImporterPlugin;
import org.bonitasoft.studio.importer.ImporterPriorityDisplayComparator;
import org.bonitasoft.studio.importer.ImporterRegistry;
import org.bonitasoft.studio.importer.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import static org.bonitasoft.studio.common.Messages.bonitaStudioModuleName;
/**
 * @author Mickael Istria
 *
 */
public class ImportFileWizardPage extends WizardPage {

    private static final String LAST_IMPORT_PATH = null;
    private TableViewer importList;
    protected String filePath;
    /*
     * Do not use this field. It is only a workaround for MAC
     */
    private ImporterFactory lastSelection;

    /**
     * @param pageName
     */
    protected ImportFileWizardPage() {
        super(ImportFileWizardPage.class.getName());
        setTitle(Messages.importFileTitle);
        setDescription(Messages.bind(Messages.importFileDescription, new Object[]{bonitaStudioModuleName}));
        setImageDescriptor(Pics.getWizban());
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite arg0) {
        Composite mainComposite = new Composite(arg0, SWT.NONE);
        GridLayout gridLayout = new GridLayout(3, false);
        mainComposite.setLayout(gridLayout);

        final Group transfoGroup = new Group(mainComposite, SWT.BORDER);
        transfoGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(3, 1).create());
        transfoGroup.setLayout(new GridLayout(2, true));
     
        Label selectImportLabel = new Label(transfoGroup, SWT.NONE);
        selectImportLabel.setText(Messages.selectImportLabel);
        Label importDescriptionLabel = new Label(transfoGroup, SWT.NONE);
        importDescriptionLabel.setText(Messages.importDescriptionLabel);
        importList = new TableViewer(transfoGroup, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
        importList.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        importList.setContentProvider(new ArrayContentProvider());
        importList.setComparator(new ImporterPriorityDisplayComparator());
        importList.setLabelProvider(new LabelProvider() {
            @Override
            public String getText(Object item) {
                return ((ImporterFactory)item).getName();
            }
        });


        importList.setInput(ImporterRegistry.getInstance().getAllAvailableImports());

        Composite descComposite = new Composite(transfoGroup, SWT.NONE) ;
        descComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).spacing(5, 3).create()) ;
        descComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create()) ;

        final Label descriptionImage = new Label(descComposite, SWT.NONE);
        descriptionImage.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING,SWT.FILL).create()) ;

        final Label separator = new Label(descComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;

        final Label descriptionLabel = new Label(descComposite, SWT.WRAP);
        descriptionLabel.setLayoutData(GridDataFactory.fillDefaults().hint(230, SWT.DEFAULT).grab(false, true).create());

   
        Label fileLabel = new Label(mainComposite, SWT.NONE);
        fileLabel.setText(Messages.selectFileToImport);
        final Text text = new Text(mainComposite, SWT.BORDER);
        text.addModifyListener(new ModifyListener() {
            @Override
            public void modifyText(ModifyEvent e) {
                filePath = text.getText();
                setPageComplete(isPageComplete());
            }
        });
        text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        final Button browseButton = new Button(mainComposite, SWT.PUSH);
        browseButton.setText(Messages.browseButton_label);
        browseButton.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String file = openFileDialog();
                if (file != null) {
                    text.setText(file);
                    if(new File(file).exists()){
                        savePath(new File(file).getParentFile().getAbsolutePath()) ;
                    }
                }
            }


            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });
        importList.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                if(getSelectedTransfo() != null){
                    descriptionLabel.setText(getSelectedTransfo().getDescription());
                    descriptionImage.setImage(getSelectedTransfo().getImageDescription()) ;
                    descriptionImage.getParent().getParent().layout(true,true) ;
                } else {
                    descriptionLabel.setText("");
                    descriptionImage.setImage(null) ;
                }
                descriptionLabel.redraw();
                setPageComplete(isPageComplete());
            }
        });
        importList.addDoubleClickListener(new IDoubleClickListener() {

            @Override
            public void doubleClick(DoubleClickEvent arg0) {
                if(getSelectedTransfo() != null){
                    descriptionLabel.setText(getSelectedTransfo().getDescription());
                } else {
                    descriptionLabel.setText("");
                }
                descriptionLabel.redraw();
                setPageComplete(isPageComplete());
                browseButton.notifyListeners(SWT.Selection,null) ;
            }
        }) ;

        importList.setSelection(new StructuredSelection(importList.getElementAt(0)));
        setControl(mainComposite);
    }

    /**
     * @return
     */
    protected String openFileDialog() {
        FileDialog fd = new FileDialog(getShell(), SWT.OPEN | SWT.SINGLE);
        fd.setText(Messages.importProcessTitle);

        fd.setFilterPath(getLastPath());
        
        final String filterExtensions = getSelectedTransfo().getFilterExtensions();
        
		String[] filterExt = filterExtensions.split(",");
        fd.setFilterExtensions(filterExt);
        return fd.open();
    }

    private String getLastPath() {
        String path = ImporterPlugin.getDefault().getDialogSettings().get(LAST_IMPORT_PATH) ;
        if(path == null || !new File(path).exists()){
            path = System.getProperty("user.home") ;
        }
        return path;
    }

    private void savePath(String path) {
    	ImporterPlugin.getDefault().getDialogSettings().put(LAST_IMPORT_PATH,path) ;
    }


    /**
     * @return
     */
    protected ImporterFactory getSelectedTransfo() {
        lastSelection = null;
        ImporterFactory currentTransfo = (ImporterFactory)((IStructuredSelection)importList.getSelection()).getFirstElement();
        if (currentTransfo != null) {
            lastSelection = currentTransfo;
        }
        return lastSelection;
    }

    /**
     * @return
     */
    public String getSelectedFilePath() {
        return filePath;
    }

    @Override
    public boolean isPageComplete() {
        return getSelectedFilePath() != null && new File(getSelectedFilePath()).exists() && getSelectedTransfo() != null;
    }
}
