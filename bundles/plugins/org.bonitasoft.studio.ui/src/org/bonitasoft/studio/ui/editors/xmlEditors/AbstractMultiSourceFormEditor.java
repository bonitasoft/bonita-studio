/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.ui.editors.xmlEditors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.internal.WorkbenchWindow;
import org.eclipse.ui.texteditor.DocumentProviderRegistry;
import org.eclipse.ui.texteditor.IDocumentProvider;

public abstract class AbstractMultiSourceFormEditor extends FormEditor {

    public static final String EDITOR_CONTRIBUTION_EXTENSION_POINT_ID = "org.bonitasoft.studio.editor.contribution";

    private IEclipseContext eclipseContext;
    protected List<AbstractEditorContribution> editorContributions;
    protected List<AbstractEditorContribution> pendingContributions = new ArrayList<>();

    private Composite pageContainer;

    @Override
    public void init(IEditorSite site, IEditorInput input) throws PartInitException {
        super.init(site, input);
        eclipseContext = ((WorkbenchWindow) getEditorSite().getWorkbenchWindow()).getModel().getContext();
        editorContributions = createEditorContributions();
        IDocumentProvider documentProvider = DocumentProviderRegistry.getDefault().getDocumentProvider(input);
        editorContributions.stream().forEach(documentProvider::addElementStateListener);
    }

    @Override
    protected Composite createPageContainer(Composite parent) {
        this.pageContainer = super.createPageContainer(parent);
        return pageContainer;
    }

    @Override
    protected void addPages() {
        assertContributionIndexesUniqueness();
        pendingContributions = editorContributions.stream()
                .filter(contribution -> !contribution.canContribute())
                .collect(Collectors.toList());
        editorContributions.stream()
                .filter(AbstractEditorContribution::canContribute)
                .sorted()
                .forEach(contribution -> {
                    try {
                        contribution.addPages(this);
                    } catch (PartInitException e) {
                        throw new RuntimeException("An error occured while intializing pages...", e);
                    }
                });
        editorContributions.stream()
                .filter(AbstractEditorContribution::canContribute)
                .sorted()
                .forEach(contribution -> {
                    try {
                        contribution.addSource(this);
                        contribution.initFormPages();
                    } catch (PartInitException e) {
                        throw new RuntimeException("An error occured while intializing pages...", e);
                    }
                });
        customizeTabeItem();
    }

    private void customizeTabeItem() {
        Arrays.asList(pageContainer.getChildren()).stream()
                .filter(CTabFolder.class::isInstance)
                .map(CTabFolder.class::cast)
                .findFirst()
                .ifPresent(cTabFolder -> {
                    cTabFolder.setBorderVisible(true);
                    cTabFolder.setTabPosition(SWT.TOP);
                });
    }

    private Optional<AbstractEditorContribution> getPendingContribution(String contributionId) {
        return pendingContributions.stream()
                .filter(aCOntribution -> Objects.equals(aCOntribution.getId(), contributionId))
                .findFirst();
    }

    /**
     * @return true if the contribution has been set as the active one, false otherwise
     */
    public boolean setActiveContribution(String contributionId) {
        if (isContributionActive(contributionId)) {
            getEditorContribution(contributionId).ifPresent(contribution -> setActivePage(contribution.getMainPageIndex()));
            return true;
        }
        return false;
    }

    public boolean isContributionActive(String contributionId) {
        return !getPendingContribution(contributionId).isPresent();
    }

    private void assertContributionIndexesUniqueness() {
        if (editorContributions.stream().map(AbstractEditorContribution::getContributionIndex).distinct()
                .count() < editorContributions.size()) {
            throw new IllegalStateException("Contributions indexes must be unique in a given editor!");
        }
    }

    public void setPageTitle(int pageIndex, String title) {
        setPageText(pageIndex, title);
    }

    @Override
    public void doSave(IProgressMonitor monitor) {
        editorContributions.stream()
                .filter(contribution -> !pendingContributions.contains(contribution))
                .forEach(contribution -> contribution.doSave(monitor));
    }

    @Override
    protected void pageChange(int newPageIndex) {
        editorContributions.stream()
                .filter(contribution -> !pendingContributions.contains(contribution))
                .forEach(contribution -> contribution.pageChange(newPageIndex));
        super.pageChange(newPageIndex);
    }

    @Override
    public void doSaveAs() {
        // Not supported
    }

    @Override
    public boolean isDirty() {
        return editorContributions
                .stream()
                .filter(contribution -> !pendingContributions.contains(contribution))
                .anyMatch(AbstractEditorContribution::isDirty);
    }

    @Override
    public boolean isSaveAsAllowed() {
        // Not supported
        return false;
    }

    private List<AbstractEditorContribution> createEditorContributions() {
        return Arrays.asList(BonitaStudioExtensionRegistryManager.getInstance()
                .getConfigurationElements(EDITOR_CONTRIBUTION_EXTENSION_POINT_ID))
                .stream()
                .map(configuratioElement -> {
                    try {
                        return configuratioElement.createExecutableExtension("class");
                    } catch (CoreException e) {
                        BonitaStudioLog.error(String.format(
                                "An error occured while retrieving configuration element for editor %s", getEditorId()), e);
                        return null;
                    }
                })
                .filter(AbstractEditorContribution.class::isInstance)
                .map(AbstractEditorContribution.class::cast)
                .filter(contribution -> Objects.equals(contribution.getEditorId(), getEditorId()))
                .collect(Collectors.toList());
    }

    public IEclipseContext getEclipseContext() {
        return eclipseContext;
    }

    public Optional<AbstractEditorContribution> getEditorContribution(String contributionId) {
        return editorContributions.stream()
                .filter(editorContribution -> Objects.equals(contributionId, editorContribution.getId()))
                .findFirst();
    }

    public int getCurrentPageIndex() {
        return getCurrentPage();
    }

    public abstract String getEditorId();
    
    @Override
    public void dispose() {
        IDocumentProvider documentProvider = DocumentProviderRegistry.getDefault().getDocumentProvider(getEditorInput());
        editorContributions.stream().forEach(documentProvider::removeElementStateListener);
        super.dispose();
    }

}
