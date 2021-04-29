/**
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.sqlbuilder.ex.builder.viewer;

import java.lang.reflect.Field;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.expression.editor.pattern.GroovyExpressionPartitioner;
import org.eclipse.jface.contentassist.IContentAssistSubjectControl;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;

public class CustomContentAssistant extends ContentAssistant {

    private GroovyExpressionPartitioner groovyExpressionPartitioner = new GroovyExpressionPartitioner();
    
    @Override
    public IContentAssistProcessor getContentAssistProcessor(String contentType) {
        try {
            IContentAssistSubjectControl contentAssistSubjectControlAdapter = getContentAssistSubjectControlAdapter();
            int offset = contentAssistSubjectControlAdapter.getSelectedRange().x;

            IDocument document = contentAssistSubjectControlAdapter.getDocument();
          
            String type =  IDocument.DEFAULT_CONTENT_TYPE;
            if (document != null){
                groovyExpressionPartitioner.connect(document);
                ITypedRegion partition = groovyExpressionPartitioner.getPartition(offset);
                groovyExpressionPartitioner.disconnect();
                type = partition.getType();
            } 
            return super.getContentAssistProcessor(type);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            BonitaStudioLog.error(e);
        } 
        return super.getContentAssistProcessor(contentType);
    }

    private IContentAssistSubjectControl getContentAssistSubjectControlAdapter() throws NoSuchFieldException, IllegalAccessException {
        Field declaredField = ContentAssistant.class.getDeclaredField("fContentAssistSubjectControlAdapter");
        declaredField.setAccessible(true);
        return (IContentAssistSubjectControl) declaredField.get(this);
    }

}
