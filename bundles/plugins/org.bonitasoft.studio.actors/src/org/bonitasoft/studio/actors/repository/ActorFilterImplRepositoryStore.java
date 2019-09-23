/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.actors.repository;

import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.actors.ActorsPlugin;
import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.connector.model.implementation.AbstractConnectorImplRepositoryStore;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 * 
 */
public class ActorFilterImplRepositoryStore extends AbstractConnectorImplRepositoryStore<ActorFilterImplFileStore> {

    private static final String STORE_NAME = "filters-impl";

    private static final Set<String> extensions = new HashSet<String>();

    public static final String IMPL_EXT = "impl";
    static {
        extensions.add(IMPL_EXT);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#createRepositoryFileStore(java.lang.String)
     */
    @Override
    public ActorFilterImplFileStore createRepositoryFileStore(String fileName) {
        return new ActorFilterImplFileStore(fileName, this);
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
        return Messages.filterImplRepositoryName;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getIcon()
     */
    @Override
    public Image getIcon() {
        return Pics.getImage("actor_filter-implem-new.png", ActorsPlugin.getDefault());
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getCompatibleExtensions()
     */
    @Override
    public Set<String> getCompatibleExtensions() {
        return extensions;
    }

    @Override
    public ActorFilterImplFileStore getChild(String fileName) {
        ActorFilterImplFileStore file = super.getChild(fileName);
        if (file == null) {
            URL url = ActorsPlugin.getDefault().getBundle().getResource(STORE_NAME + "/" + fileName);
            if (url != null) {
                return new URLActorFilterImplFileStore(url, this);
            } else {
                return null;
            }
        } else {
            return file;
        }

    }

    @Override
    public List<ActorFilterImplFileStore> getChildren() {
        List<ActorFilterImplFileStore> result = super.getChildren();
        Enumeration<URL> connectorImplementations = ActorsPlugin.getDefault().getBundle().findEntries(STORE_NAME, "*.impl", false);
        if (connectorImplementations != null) {
            while (connectorImplementations.hasMoreElements()) {
                URL url = connectorImplementations.nextElement();
                String[] segments = url.getFile().split("/");
                String fileName = segments[segments.length - 1];
                if (fileName.lastIndexOf(".") != -1) {
                    String extension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
                    if (extensions.contains(extension)) {
                        result.add(new URLActorFilterImplFileStore(url, this));
                    }
                }
            }
        }
        return result;
    }

    @Override
    protected IRepositoryStore<? extends IRepositoryFileStore> getSourceRepositoryStore() {
        return RepositoryManager.getInstance().getRepositoryStore(ActorFilterSourceRepositoryStore.class);
    }

}
