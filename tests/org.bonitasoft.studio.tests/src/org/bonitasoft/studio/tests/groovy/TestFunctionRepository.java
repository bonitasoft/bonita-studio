/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.groovy;

import static org.bonitasoft.studio.common.Messages.bonitaName;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.groovy.Messages;
import org.bonitasoft.studio.groovy.library.FunctionsRepositoryFactory;
import org.bonitasoft.studio.groovy.library.IFunctionCategory;
import org.eclipse.core.runtime.OperationCanceledException;
import org.junit.Test;


public class TestFunctionRepository {

    @Test
    public void testBonitaCategoryExistsAndNotEmpty() throws OperationCanceledException, InterruptedException {
        IFunctionCategory bonitaCategory = null;
        for (IFunctionCategory category : FunctionsRepositoryFactory
                .getFunctionCatgories(RepositoryManager.getInstance().getCurrentRepository()).getCategories()) {
            if (category.getName().equals(bonitaName)) {
                bonitaCategory = category;
            }
        }
        assertNotNull("Bonita functions category not found", bonitaCategory);
        assertNotSame("Bonita function category is empty", 0, bonitaCategory.getFunctions().size());
        String doc = bonitaCategory.getFunctions().get(0).getDocumentation();
        assertTrue("Doc for Bonita category methods not found", doc != null && doc.length() > 0);
    }

    @Test
    public void testCollectionCategoryHasDoc() throws OperationCanceledException, InterruptedException {
        IFunctionCategory collectionsCategory = null;
        for (IFunctionCategory category : FunctionsRepositoryFactory
                .getFunctionCatgories(RepositoryManager.getInstance().getCurrentRepository()).getCategories()) {
            if (category.getName().equals(Messages.collectionCatLabel)) {
                collectionsCategory = category;
            }
        }
        String doc = collectionsCategory.getFunctions().get(0).getDocumentation();
        assertTrue("Doc for Collections category methods not found", doc != null && doc.length() > 0);
    }
}
