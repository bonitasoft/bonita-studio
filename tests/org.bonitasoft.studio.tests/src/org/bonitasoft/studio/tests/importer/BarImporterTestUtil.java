/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.importer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.importer.bar.factory.BarImporterFactory;
import org.bonitasoft.studio.importer.processors.ToProcProcessor;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.ui.PartInitException;

/**
 * @author Romain Bioteau
 */
public class BarImporterTestUtil {

    /**
     * @param resourceURL
     * @return the temporary migrated proc file
     * @throws Exception
     */
    public static File migrateBar(final URL resourceURL) throws Exception {
        final File archive = new File(FileLocator.toFileURL(resourceURL).getFile());
        final ToProcProcessor processor = new BarImporterFactory().createProcessor(archive.getName());
        assertNotNull("Failed to create processor", processor);
        return processor.createDiagram(archive.toURI().toURL(), Repository.NULL_PROGRESS_MONITOR);
    }

    /**
     * @param migratedProc
     * @return the loaded EMF resource
     */
    public static Resource assertIsLoadable(final File migratedProc) {
        final ResourceSet resourceSet = new ResourceSetImpl();
        final Resource resource = resourceSet.createResource(URI.createFileURI(migratedProc.getAbsolutePath()));
        if (resource == null) {
            fail("Can't create an EMF resource for " + migratedProc.getAbsolutePath());
        }
        try {
            resource.load(Collections.emptyMap());
        } catch (final Exception e) {
            fail("Resource is not loadable :\n" + e.getMessage());
        }
        return resource;
    }

    public static MainProcess getMainProcess(final Resource resource) {
        if (resource.getContents().size() > 1) {
            final EObject mainProcess = resource.getContents().get(0);
            if (mainProcess instanceof MainProcess) {
                return (MainProcess) mainProcess;
            }
            fail("MainProcess is not at the correct in index in the resource contents");
        }
        boolean mainProcFound = false;
        for (final EObject c : resource.getContents()) {
            if (c instanceof MainProcess) {
                mainProcFound = true;
                break;
            }
        }
        assertTrue("MainProcess not found in resource :" + resource.getURI(), mainProcFound);
        return null;
    }

    /**
     * Validate that all views of the gmf diagrams are bound to a semantic element
     * 
     * @param resource
     * @throws PartInitException
     */
    public static void assertViewsAreConsistent(final Resource resource) throws PartInitException {
        final MainProcess mainproc = getMainProcess(resource);
        final Diagram diagram = ModelHelper.getDiagramFor(mainproc);
        assertNotNull("Diagram view not found", diagram);
        final List<Shape> shapes = ModelHelper.getAllItemsOfType(diagram, NotationPackage.Literals.SHAPE);
        for (final Shape shape : shapes) {
            assertNotNull(
                    "A view (id=" + EMFCoreUtil.getProxyID(shape) + ", type=" + shape.getType()
                            + ") is not bound to a semantic element",
                    shape.eGet(NotationPackage.Literals.VIEW__ELEMENT, false));
        }

        for (final Form f : ModelHelper.getAllFormsContainedIn(mainproc)) {
            final Diagram formDiagram = ModelHelper.getDiagramFor(f);
            assertNotNull("Form Diagram view not found", formDiagram);
            final List<Shape> formViews = ModelHelper.getAllItemsOfType(diagram, NotationPackage.Literals.SHAPE);
            for (final Shape shape : formViews) {
                assertNotNull(
                        "A view (id=" + EMFCoreUtil.getProxyID(shape) + ", type=" + shape.getType()
                                + ") is not bound to a semantic element",
                        shape.eGet(NotationPackage.Literals.VIEW__ELEMENT, false));
            }
        }
    }

}
