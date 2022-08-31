/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.core.builder.resolution;

import java.util.Optional;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.maven.builder.validator.PomFileValidator;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.m2e.editor.pom.MavenPomEditor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.wst.sse.ui.StructuredTextEditor;

public class RestAPIDependencyToUpdateMarkerResolution implements IMarkerResolution {

    private String version;

    public RestAPIDependencyToUpdateMarkerResolution(String version) {
        this.version = version;
    }

    @Override
    public String getLabel() {
        return String.format(Messages.updateDependency, version);
    }

    @Override
    public void run(IMarker marker) {
        retrieveEditor((IFile) marker.getResource()).ifPresent(activeEditor -> {
            try {
                activeEditor.setFocus();
                Integer charStart = (Integer) marker.getAttribute(IMarker.CHAR_START);
                Integer charEnd = (Integer) marker.getAttribute(IMarker.CHAR_END);
                String tag = (String) marker.getAttribute(PomFileValidator.TAG_PROPERTY);
                String version = (String) marker.getAttribute(PomFileValidator.VERSION_MIN_PROPERTY);
                ITextEditor structuredTextEditor = activeEditor.getSourcePage();
                IDocument document = structuredTextEditor.getDocumentProvider().getDocument(activeEditor.getEditorInput());
                document.replace(charStart, charEnd - charStart, String.format("<%s>%s</%s>", tag, version, tag));
                structuredTextEditor.doSave(new NullProgressMonitor());
            } catch (CoreException | BadLocationException e) {
                throw new RuntimeException("Ann error occured while attempting to apply quick fix", e);
            }
        });
    }

    private Optional<MavenPomEditor> retrieveEditor(IFile pomFile) {
        IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        try {
            IEditorPart openEditor = IDE.openEditor(activePage, pomFile);
            return Optional.ofNullable(openEditor)
                    .filter(MavenPomEditor.class::isInstance)
                    .map(MavenPomEditor.class::cast);
        } catch (PartInitException e) {
            BonitaStudioLog.error(e);
            return Optional.empty();
        }
    }

}
