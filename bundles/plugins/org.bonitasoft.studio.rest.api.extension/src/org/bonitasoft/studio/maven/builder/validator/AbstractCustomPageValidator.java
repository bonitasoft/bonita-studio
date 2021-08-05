/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.builder.validator;

import java.io.IOException;
import java.nio.charset.Charset;

import org.bonitasoft.studio.common.jface.databinding.StatusToMarkerSeverity;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.jface.text.Document;

import com.google.common.io.Files;

public abstract class AbstractCustomPageValidator {

    private static final Charset UTF_8 = Charset.forName("UTF-8");

    protected IProject project;
    protected RepositoryAccessor repositoryAccessor;

    public AbstractCustomPageValidator(IProject project) {
        this.project = project;
        this.repositoryAccessor = RepositoryManager.getInstance().getAccessor();
    }

    public void acceptAndValidate(IFile file) throws CoreException {
        if (accept(file)) {
            validate(file);
        }
    }

    public abstract void acceptAndValidate() throws CoreException;

    protected abstract boolean accept(IFile candidate);

    protected abstract void validate(IFile file) throws CoreException;

    protected void createMarker(IResource resource, IStatus status, String markerType) throws CoreException {
        final IMarker marker = resource.createMarker(markerType);
        if (status instanceof StatusWithLocation) {
            final Location location = ((StatusWithLocation) status).getLocation();
            marker.setAttribute(IMarker.CHAR_START, location.getOffset());
            marker.setAttribute(IMarker.CHAR_END, location.getOffset() + location.getLength());
            if (location.getLineNumber() > 0) {
                marker.setAttribute(IMarker.LINE_NUMBER, location.getLineNumber() + 1);
            }
        }
        marker.setAttribute(IMarker.MESSAGE, status.getMessage());
        marker.setAttribute(IMarker.SEVERITY, new StatusToMarkerSeverity(status).toMarkerSeverity());
    }

    protected void refreshMarkers(IFile pagePropertyFile, MultiStatus status, String markerType) throws CoreException {
        pagePropertyFile.deleteMarkers(markerType, false, IResource.DEPTH_ZERO);
        for (IStatus child : status.getChildren()) {
            if (!child.isOK()) {
                if (child.isMultiStatus()) {
                    for (final IStatus c : child.getChildren()) {
                        if (!c.isOK()) {
                            createMarker(pagePropertyFile, c, markerType);
                        }
                    }
                } else {
                    createMarker(pagePropertyFile, child, markerType);
                }
            }
        }
    }

    protected abstract String getMarkerType();

    protected abstract IFolder getParentFolder() ;

    protected Document toDocument(IFile file) {
        final Document document = new Document();
        try {
            document.set(Files.toString(file.getLocation().toFile(), UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return document;
    }

}
