/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.sections.resources;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.repository.themes.ApplicationLookNFeelFileStore;
import org.bonitasoft.studio.repository.themes.LookNFeelRepositoryStore;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.nebula.jface.galleryviewer.GalleryTreeViewer;
import org.eclipse.nebula.widgets.gallery.AbstractGalleryItemRenderer;
import org.eclipse.nebula.widgets.gallery.DefaultGalleryGroupRenderer;
import org.eclipse.nebula.widgets.gallery.DefaultGalleryItemRenderer;
import org.eclipse.nebula.widgets.gallery.Gallery;
import org.eclipse.nebula.widgets.gallery.GalleryItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;

/**
 * @author Mickael Istria
 *
 */
public class SelectLocalTemplateWizardPage extends WizardPage {

    private ApplicationLookNFeelFileStore selectedTheme;
    private GalleryTreeViewer viewer;
    private final List<Image> images;
    private Button export;
    private Button remove;
    private final LookNFeelRepositoryStore looknfeelStore;

    private static final Color GALLERY_HEADER_BACKGROUND_COLOR = new Color(null,225,225,225) ;
    private static final Color GALLERY_HEADER_FOREGROUND_COLOR = Display.getDefault().getSystemColor(SWT.COLOR_BLACK) ;
    private static final Color GALLERY_BACKGROUND_COLOR = new Color(null,248,248,248) ;

    /**
     * @param pageName
     */
    protected SelectLocalTemplateWizardPage() {
        super("SelectLocalTemplateWizardPage"); //$NON-NLS-1$
        images = new ArrayList<Image>();
        setTitle(Messages.selectLocalTemplateWizardPageTitle);
        setDescription(Messages.selectLocalTemplateWizardPageDescription);
        setImageDescriptor(Pics.getWizban());
        looknfeelStore = (LookNFeelRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(LookNFeelRepositoryStore.class) ;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout(1, false));
        createGallery(composite);
        setPageComplete(selectedTheme != null);
        updateButtons(null);
        setControl(composite);

    }

    private void createGallery(Composite composite) {
        final Gallery gallery = new Gallery(composite, SWT.SINGLE | SWT.V_SCROLL | SWT.BORDER);

        viewer = new GalleryTreeViewer(gallery);

        viewer.setContentProvider(new ITreeContentProvider() {

            @Override
            public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

            }

            @Override
            public void dispose() {

            }

            @Override
            public Object[] getElements(Object inputElement) {
                return new String[]{Messages.webTemplates};
            }

            @Override
            public boolean hasChildren(Object element) {
                return element instanceof String;
            }

            @Override
            public Object getParent(Object element) {
                return null;
            }

            @Override
            public Object[] getChildren(Object parentElement) {
                if(parentElement instanceof String
                        && parentElement.equals(Messages.webTemplates)){
                    List<ApplicationLookNFeelFileStore> applicationThemes = looknfeelStore.getApplicationLookNFeels();
                    return applicationThemes.toArray();
                } else {
                    return new Object[]{};
                }

            }
        });

        viewer.setLabelProvider(new LabelProvider(){


            @Override
            public Image getImage(Object element) {
                if(element instanceof ApplicationLookNFeelFileStore){
                    Image res = null;
                    try {
                        res = ((ApplicationLookNFeelFileStore) element).getPreviewImage();
                    } catch (IOException e) {
                        BonitaStudioLog.error(e);
                    }
                    /*Store image to dispose them at the close*/
                    if(res != null ) {
                        images.add(res);
                    }
                    if(res == null){
                        return Pics.getImage(PicsConstants.noPreview) ;
                    }
                    return res;
                }
                return super.getImage(element);
            }

            @Override
            public String getText(Object element) {
                if(element instanceof ApplicationLookNFeelFileStore){
                    return ((ApplicationLookNFeelFileStore) element).getDisplayName();
                }
                return super.getText(element);
            }

        });

        GridDataFactory.fillDefaults().grab(true, true).applyTo(viewer.getGallery());

        AbstractGalleryItemRenderer ir = new DefaultGalleryItemRenderer();

        ir.setGallery(gallery);
        gallery.setItemRenderer(ir);
        DefaultGalleryGroupRenderer groupRenderer = new DefaultGalleryGroupRenderer();
        groupRenderer.setItemSize(200, 100);
        groupRenderer.setAnimation(true);
        groupRenderer.setTitleForeground(GALLERY_HEADER_FOREGROUND_COLOR) ;
        groupRenderer.setTitleBackground(GALLERY_HEADER_BACKGROUND_COLOR) ;
        gallery.setBackground(GALLERY_BACKGROUND_COLOR);
        gallery.setGroupRenderer(groupRenderer);

        viewer.setInput(new Object());
        /*Add a tool tip to display the image of the form in better size :)*/
        new ToolTip(gallery) {
            @Override
            protected boolean shouldCreateToolTip(Event event) {
                GalleryItem gi = gallery.getItem(new Point(event.x, event.y));
                if(gi == null || gi.getParentItem() == null){//avoid tooltip on Group
                    return false;
                }
                return super.shouldCreateToolTip(event);
            }

            @Override
            protected Composite createToolTipContentArea(Event event, Composite parent) {
                //TODO : manage size of the tooltip for gallery
                //TODO : create a better UI for the tooltip of the gallery
                GalleryItem gi = gallery.getItem(new Point(event.x, event.y));
                if(gi != null){ //check that the item already exist
                    Label testLabel = new Label(parent, SWT.BORDER);
                    testLabel.setImage(gi.getImage());
                    return parent;
                } else {
                    return null;
                }
            }
        };

        viewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                if(((StructuredSelection) event.getSelection()).getFirstElement()  instanceof ApplicationLookNFeelFileStore){
                    selectedTheme = (ApplicationLookNFeelFileStore) ((StructuredSelection) event.getSelection()).getFirstElement() ;
                    setPageComplete(selectedTheme != null);
                    updateButtons(selectedTheme);
                }
            }
        });
        Composite buttonComposite = new Composite(composite, SWT.NONE);
        buttonComposite.setLayout(new RowLayout(SWT.HORIZONTAL));
        remove = new Button(buttonComposite, SWT.FLAT);
        remove.setText(Messages.Remove);
        remove.addSelectionListener(new SelectionAdapter() {
            /* (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(SelectionEvent e) {
                IStructuredSelection selection = (IStructuredSelection)viewer.getSelection();
                Iterator<?> it = selection.iterator();
                while (it.hasNext()) {
                    ApplicationLookNFeelFileStore artifact = (ApplicationLookNFeelFileStore) it.next();
                    artifact.delete() ;
                    if(!looknfeelStore.getChildren().contains(artifact)){
                        viewer.remove(artifact);
                    }
                }
            }
        });
        export = new Button(buttonComposite, SWT.FLAT);
        export.setText(Messages.ResourceSection_export);
        export.addSelectionListener(new SelectionAdapter() {
            /* (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(SelectionEvent e) {
                if(!viewer.getSelection().isEmpty()){
                    final DirectoryDialog dialog = new DirectoryDialog(Display.getDefault().getActiveShell());
                    final String path = dialog.open();
                    if(path != null){
                        try {
                            getContainer().run(false, false,new IRunnableWithProgress(){
                                @Override
                                public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                                    monitor.beginTask(Messages.exporting, IProgressMonitor.UNKNOWN);
                                    IStructuredSelection selection = (IStructuredSelection)viewer.getSelection();
                                    Iterator<?> it = selection.iterator();
                                    while (it.hasNext()) {
                                        final ApplicationLookNFeelFileStore artifact = (ApplicationLookNFeelFileStore) it.next();
                                        artifact.export(path);
                                    }
                                    Display.getDefault().syncExec(new Runnable() {
                                        @Override
                                        public void run() {
                                            MessageDialog.openInformation(Display.getDefault().getActiveShell(), Messages.exportSuccessfullTitle, Messages.exportSuccessfullMsg);
                                        }
                                    });
                                }
                            });
                        } catch (InvocationTargetException e1) {
                            BonitaStudioLog.error(e1);
                        } catch (InterruptedException e1) {
                            BonitaStudioLog.error(e1);
                        }
                    }

                }
            }
        });
        Button importAction = new Button(buttonComposite, SWT.FLAT);
        importAction.setText(Messages.ResourceSection_importTemplate);
        importAction.addSelectionListener(new SelectionAdapter() {
            /* (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(SelectionEvent e) {
                FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
                dialog.setFilterPath(System.getProperty("user.home"));
                dialog.setFilterExtensions(new String[]{"*."+LookNFeelRepositoryStore.LF_EXTENSION});
                String path = dialog.open();
                if(path != null){
                    FileInputStream fis = null;
                    try {
                        File file = new File(path);
                        fis = new FileInputStream(file);
                        IRepositoryFileStore artifact = looknfeelStore.importInputStream(file.getName(), fis);
                        if(artifact != null){
                            MessageDialog.openInformation(Display.getDefault().getActiveShell(), Messages.importResultTitle, Messages.importSuccessMsg);
                        }
                        if (artifact instanceof ApplicationLookNFeelFileStore) {
                            viewer.add(Messages.webTemplates, artifact);
                        }
                    } catch (Exception ee) {
                        new BonitaErrorDialog(getShell(), Messages.Error, Messages.saveAsTemplate_import_error, ee).open();
                        BonitaStudioLog.error(ee);
                    } finally {
                        try {
                            fis.close();
                        } catch (IOException e1) {
                            BonitaStudioLog.error(e1);
                        }
                    }
                }
            }
        });
    }

    /**
     * @param template
     */
    protected void updateButtons(ApplicationLookNFeelFileStore template) {
        remove.setEnabled(template!= null && !template.isProvided());
        export.setEnabled(template!= null);
    }

    /**
     * @return
     */
    public ApplicationLookNFeelFileStore getSelectedTemplate() {
        return selectedTheme;
    }

    @Override
    public void dispose() {
        super.dispose();
        if(images != null){
            for(Image im : images){
                im.dispose();
            }
        }
    }

}
