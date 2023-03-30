/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.document.core.export;

import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder;
import org.bonitasoft.studio.engine.contribution.BuildProcessDefinitionException;
import org.bonitasoft.studio.engine.contribution.IEngineDefinitionBuilder;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.emf.ecore.EObject;

public class DocumentEngineDefinitionBuilder implements IEngineDefinitionBuilder<ProcessDefinitionBuilder> {

    private ProcessDefinitionBuilder processDefinitionBuilder;
    private final DocumentGroovyScriptExpressionFactory scriptExpressionFactory;

    public DocumentEngineDefinitionBuilder() {
        scriptExpressionFactory = new DocumentGroovyScriptExpressionFactory();
    }

    @Override
    public void build(final EObject element) throws BuildProcessDefinitionException {
        if(!(element instanceof Document)) {
            throw new IllegalArgumentException();
        }
        documentBuilderDelegate((Document) element).build();
    }

    private IDefinitionBuildable documentBuilderDelegate(final Document document) {
        return document.isMultiple() ? multipeDocumentDelegate(document) :
                singleDocumentDelegate(document);
    }

    protected SingleDocumentEngineDefinitionBuilder singleDocumentDelegate(final Document document) {
        return new SingleDocumentEngineDefinitionBuilder(document, processDefinitionBuilder, scriptExpressionFactory);
    }

    protected MultipleDocumentEngineDefinitionBuilder multipeDocumentDelegate(final Document document) {
        return new MultipleDocumentEngineDefinitionBuilder(document, processDefinitionBuilder, scriptExpressionFactory);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.engine.contribution.IEngineDefinitionBuilder#appliesTo(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
     */
    @Override
    public boolean appliesTo(final EObject context, final EObject element) {
        return context instanceof Pool && element instanceof Document;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.engine.contribution.IEngineDefinitionBuilder#setEngineBuilder(java.lang.Object)
     */
    @Override
    public void setEngineBuilder(final ProcessDefinitionBuilder engineBuilder) {
        processDefinitionBuilder = engineBuilder;
    }

}
