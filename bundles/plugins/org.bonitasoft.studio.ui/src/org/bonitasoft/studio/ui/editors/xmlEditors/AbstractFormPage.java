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
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.preferences.PreferenceUtil;
import org.bonitasoft.studio.ui.ColorConstants;
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
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.wst.sse.ui.StructuredTextEditor;

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

    private Color darkModeBg;

    public AbstractFormPage(String id, String title, IEclipseContext context) {
        super(id, title);
        this.repositoryAccessor = repositoryAccessor();
        eCommandService = context.get(ECommandService.class);
        eHandlerService = context.get(EHandlerService.class);
    }

    protected RepositoryAccessor repositoryAccessor() {
        return RepositoryManager.getInstance().getAccessor();
    }

    public ToolBarManager getToolbarManager() {
        return toolBarManager;
    }

    @Override
    protected void createFormContent(IManagedForm managedForm) {
        toolkit = managedForm.getToolkit();
        darkModeBg = new Color(Display.getDefault(), ColorConstants.DARK_MODE_EDITORS_BACKGROUND);
        if (PreferenceUtil.isDarkTheme()) {
            toolkit.setBackground(darkModeBg);
        }
        scrolledForm = managedForm.getForm();
        scrolledForm.getForm().setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME,
                BonitaThemeConstants.EDITOR_FORM_BAKGROUND_CLASS);
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
        final ToolBar toolBar = new ToolBar(head, SWT.HORIZONTAL | SWT.RIGHT | SWT.NO_FOCUS);
        toolBar.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).create());
        toolBar.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.EDITOR_TOOLBAR_TEXT_COLOR);
        toolBarManager = new ToolBarManager(toolBar);
        createHeaderContent(toolBar);
        return toolBar;
    }

    protected abstract void createHeaderContent(ToolBar toolBar);

    protected abstract void createForm();

    public void reflow() {
        if (scrolledForm != null) {
            scrolledForm.reflow(true);
        }
    }

    public void update() {
        retrieveModelFromDocument().ifPresent(model -> {
            updateWorkingCopy(model);
            setErrorState(false);
            recreateForm();
        });
    }

    protected Optional<T> retrieveModelFromDocument() {
        IDocument document = getSourceEditor() != null
                ? getSourceEditor().getDocumentProvider().getDocument(getEditorInput())
                : null;
        return document != null
                ? xmlToModel(document.get().getBytes())
                : Optional.empty();
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
                    getEditor().setActiveEditor(getSourceEditor());
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
        Stream.of(getManagedForm().getParts()).forEach(IFormPart::dispose);
        Stream.of(getManagedForm().getParts()).forEach(getManagedForm()::removePart);
    }

    public ECommandService getECommandService() {
        return eCommandService;
    }

    public EHandlerService getEHandlerService() {
        return eHandlerService;
    }

    public StructuredTextEditor getSourceEditor() {
        if (getEditor() instanceof AbstractEditor) {
            return ((AbstractEditor) getEditor()).getSourceEditor();
        }
        return null; // TODO: essayer de le recup via le context s'il existe! 
    }

    protected Optional<T> xmlToModel(byte[] xml) {
        if (getEditor() instanceof AbstractEditor) {
            return ((AbstractEditor) getEditor()).xmlToModel(xml);
        }
        return Optional.empty();
    }

    protected void updateWorkingCopy(T model) {
        if (getEditor() instanceof AbstractEditor) {
            ((AbstractEditor) getEditor()).updateWorkingCopy(model);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        if (darkModeBg != null) {
            darkModeBg.dispose();
        }
    }

}
