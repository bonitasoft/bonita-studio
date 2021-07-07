/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.xml.repository;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.PostMigrationOperationCollector;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.repository.store.AbstractEMFRepositoryStore;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.xml.Messages;
import org.bonitasoft.studio.xml.XMLPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.xml.core.internal.XMLCorePlugin;
import org.eclipse.wst.xml.core.internal.catalog.provisional.ICatalog;
import org.eclipse.wst.xml.core.internal.catalog.provisional.ICatalogEntry;
import org.eclipse.wst.xml.core.internal.catalog.provisional.INextCatalog;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.util.XSDAdapterFactory;
import org.eclipse.xsd.util.XSDResourceFactoryImpl;

/**
 * @author Romain Bioteau
 */
public class XSDRepositoryStore extends AbstractEMFRepositoryStore<XSDFileStore> {

    public static final String PREFIX_IN_BAR = "xsd/";
    private static final String STORE_NAME = "xsd";
    private static final Set<String> extensions = new HashSet<>();
    private static final Set<String> XSD_KEYS = new HashSet<>();
    static {
        extensions.add("xsd");
        XSD_KEYS.add("http://www.w3.org/2001/12/soap-envelope");
        XSD_KEYS.add("http://www.w3.org/2001/12/soap-encoding");
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#createRepositoryFileStore(java.lang.String)
     */
    @Override
    public XSDFileStore createRepositoryFileStore(final String fileName) {
        return new XSDFileStore(fileName, this);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getName()
     */
    @Override
    public String getName() {
        return STORE_NAME;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getDisplayName()
     */
    @Override
    public String getDisplayName() {
        return Messages.xsdRepositoryName;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getIcon()
     */
    @Override
    public Image getIcon() {
        return Pics.getImage(PicsConstants.xml);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getCompatibleExtensions()
     */
    @Override
    public Set<String> getCompatibleExtensions() {
        return extensions;
    }

    public IStatus isXSDFileValid(final File file) {
        Resource resource = null;
        final String filePath = file.getAbsolutePath();
        try {
            final XSDResourceFactoryImpl factory = new XSDResourceFactoryImpl();
            resource = factory.createResource(URI.createFileURI(filePath));
            resource.load(Collections.EMPTY_MAP);
            final XSDSchema schema = (XSDSchema) resource.getContents().get(0);
            if (schema.getTargetNamespace() == null) {
                return new Status(IStatus.ERROR, XMLPlugin.PLUGIN_ID, Messages.missingATargetNamespace);
            }
        } catch (final Exception ex) {
            return new Status(IStatus.ERROR, XMLPlugin.PLUGIN_ID,
                    "An xsd file seems corrupted in your workspace (" + filePath + ").\n" + ex.getMessage(), ex);
        } finally {
            if (resource != null) {
                resource.unload();
            }
        }
        return Status.OK_STATUS;
    }

    public XSDElementDeclaration findElementDeclaration(final String namespace, final String elementName) {
        for (final IRepositoryFileStore artifact : getChildren()) {
            final XSDFileStore file = (XSDFileStore) artifact;
            final XSDElementDeclaration decl = file.findElementDeclaration(namespace, elementName);
            if (decl != null) {
                return decl;
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.store.AbstractRepositoryStore#getChildren()
     */
    @Override
    public List<XSDFileStore> getChildren() {
        List<XSDFileStore> children = super.getChildren();
        ICatalog catalog = XMLCorePlugin.getDefault().getDefaultXMLCatalog();
        INextCatalog[] nextCatalogs = catalog.getNextCatalogs();
        for (INextCatalog nextCatalog : nextCatalogs) {
            for (ICatalogEntry entry : nextCatalog.getReferencedCatalog().getCatalogEntries()) {
                String uri = entry.getURI();
                String key = entry.getKey();
                if (XSD_KEYS.contains(key) && uri != null) {
                    children.add(new URIXSDFileStore(uri, this));
                }
            }
        }
        return children;
    }

    public XSDFileStore findArtifactWithNamespace(final String xmlNamespace) {
        for (final IRepositoryFileStore artifact : getChildren()) {
            try {
                final XSDSchema schema = (XSDSchema) artifact.getContent();
                if (schema.getTargetNamespace() != null && schema.getTargetNamespace().equals(xmlNamespace)) {
                    return (XSDFileStore) artifact;
                }
            } catch (final ReadFileStoreException e) {
                BonitaStudioLog.error("Failed to retrieve xsd content", e);
            }
        }
        return null;
    }

    @Override
    protected void addAdapterFactory(final ComposedAdapterFactory adapterFactory) {
        adapterFactory.addAdapterFactory(new XSDAdapterFactory());
    }


    @Override
    public MigrationReport migrate(PostMigrationOperationCollector postMigrationOperationCollector, IProgressMonitor monitor) throws CoreException, MigrationException {
        XSDFileStore legacyXSD = getChild("soap-encoding.xsd", true);
        if (legacyXSD != null) {
            legacyXSD.delete();
        }
        legacyXSD = getChild("soap-envelope.xsd", true);
        if (legacyXSD != null) {
            legacyXSD.delete();
        }
        return MigrationReport.emptyReport();
    }

}
