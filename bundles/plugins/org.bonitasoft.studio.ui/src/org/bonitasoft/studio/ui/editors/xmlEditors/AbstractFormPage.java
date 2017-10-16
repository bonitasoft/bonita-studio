/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.ui.editors.xmlEditors;

import java.util.Optional;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.jface.BonitaStudioFontRegistry;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.ui.UIPlugin;
import org.bonitasoft.studio.ui.i18n.Messages;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.ui.forms.widgets.ScrolledForm;

public abstract class AbstractFormPage<T> extends FormPage {

    protected RepositoryAccessor repositoryAccessor;

    protected ScrolledForm scrolledForm;
    protected FormToolkit toolkit;
    protected T workingCopy;
    protected IDocument document;
    protected ToolBarManager toolBarManager;

    private boolean errorState = false;

    private ECommandService eCommandService;

    private EHandlerService eHandlerService;

    public AbstractFormPage(String id, String title, IEclipseContext context) {
        super(id, title);
        this.repositoryAccessor = repositoryAccessor();
        eCommandService = context.get(ECommandService.class);
        eHandlerService = context.get(EHandlerService.class);
    }

    protected RepositoryAccessor repositoryAccessor() {
        final RepositoryAccessor repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
        return repositoryAccessor;
    }

    public ToolBarManager getToolbarManager() {
        return toolBarManager;
    }

    @Override
    protected void createFormContent(IManagedForm managedForm) {
        toolkit = managedForm.getToolkit();
        scrolledForm = managedForm.getForm();
        toolkit.decorateFormHeading(scrolledForm.getForm());
        scrolledForm.setHeadClient(createHeader(scrolledForm.getForm()));
        scrolledForm.getBody().setLayout(GridLayoutFactory.swtDefaults().create());
        scrolledForm.getBody().setLayoutData(GridDataFactory.fillDefaults().create());
        createForm();
    }

    public void init(T workingCopy, IDocument document) {
        this.workingCopy = workingCopy;
        this.document = document;
    }

    private Control createHeader(Form form) {
        final Composite head = form.getHead();
        final ToolBar toolBar = new ToolBar(head, SWT.HORIZONTAL | SWT.RIGHT);
        toolBarManager = new ToolBarManager(toolBar);
        createHeaderContent(toolBar);
        return toolBar;
    }

    protected abstract void createHeaderContent(ToolBar toolBar);

    protected abstract void createForm();

    public void reflow() {
        scrolledForm.reflow(true);
    }

    @Override
    public void setActive(boolean active) {
        super.setActive(active);
    }

    public void update() {
        IDocument document = getEditor().getSourceEditor() != null
                ? getEditor().getSourceEditor().getDocumentProvider().getDocument(getEditorInput())
                : null;
        if (document != null) {
            final Optional<T> newModel = getEditor().xmlToModel(document.get().getBytes());
            newModel.ifPresent(model -> {
                getEditor().updateWorkingCopy(model);
                setErrorState(false);
                recreateForm();
            });
        }
    }

    public void recreateForm() {
        if (scrolledForm != null) {
            Stream.of(toolBarManager.getItems()).forEach(IContributionItem::update);
            disposePageContent();
            createForm();
        }
    }

    public T getWorkingCopy() {
        return workingCopy;
    }

    public void setWorkingCopy(T workingCopy) {
        this.workingCopy = workingCopy;
    }

    public IDocument getDocument() {
        return document;
    }

    public FormToolkit getToolkit() {
        return toolkit;
    }

    public RepositoryAccessor getRepositoryAccessor() {
        return repositoryAccessor;
    }

    public boolean isErrorState() {
        return errorState;
    }

    public void setErrorState(boolean errorState) {
        this.errorState = errorState;
    }

    public void loadErrorPage() {
        if (scrolledForm != null) {
            Stream.of(toolBarManager.getItems()).forEach(IContributionItem::update);
            disposePageContent();

            final Composite composite = toolkit.createComposite(scrolledForm.getBody());
            composite.setLayout(GridLayoutFactory.fillDefaults().create());
            composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).align(SWT.CENTER, SWT.CENTER).create());

            final ImageHyperlink imageHyperlink = toolkit.createImageHyperlink(composite, SWT.NONE);
            imageHyperlink.setLayoutData(GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.CENTER).create());
            imageHyperlink.setImage(UIPlugin.getImage("icons/error.png"));
            imageHyperlink.addHyperlinkListener(new HyperlinkAdapter() {

                @Override
                public void linkActivated(HyperlinkEvent e) {
                    getEditor().setActiveEditor(getEditor().getSourceEditor());
                }
            });
            final Label label = toolkit.createLabel(composite, Messages.parseError);
            label.setLayoutData(GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.CENTER).create());
            label.setFont(BonitaStudioFontRegistry.getPreferenceTitleFont());
            scrolledForm.getParent().layout(true, true);
        }
    }

    protected void disposePageContent() {
        Stream.of(scrolledForm.getBody().getChildren()).forEach(Control::dispose);
    }

    @Override
    public AbstractEditor<T> getEditor() {
        return (AbstractEditor<T>) super.getEditor();
    }

    public ECommandService getECommandService() {
        return eCommandService;
    }

    public EHandlerService getEHandlerService() {
        return eHandlerService;
    }

}
