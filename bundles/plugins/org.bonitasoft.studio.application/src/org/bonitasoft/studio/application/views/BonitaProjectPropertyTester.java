package org.bonitasoft.studio.application.views;

import java.util.Objects;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.BonitaProjectNature;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;


public class BonitaProjectPropertyTester extends PropertyTester {

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (receiver instanceof IProject) {
            try {
                return receiver instanceof IProject && ((IProject) receiver).isOpen()
                        && ((IProject) receiver).hasNature(BonitaProjectNature.NATURE_ID);
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
                return false;
            }
        } else if (receiver instanceof IFolder && expectedValue != null) {
            return receiver instanceof IFolder && Objects.equals(((IResource) receiver).getName(), expectedValue);
        }
        return false;
    }

}
