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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.groovy.ui.viewer;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.groovy.eclipse.editor.GroovyColorManager;
import org.codehaus.groovy.eclipse.editor.GroovyConfiguration;
import org.codehaus.groovy.eclipse.editor.GroovyPartitionScanner;
import org.eclipse.jdt.groovy.core.util.ReflectionUtils;
import org.eclipse.jdt.internal.ui.text.java.CompletionProposalCategory;
import org.eclipse.jdt.internal.ui.text.java.ContentAssistProcessor;
import org.eclipse.jdt.internal.ui.text.java.JavaCompletionProcessor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.texteditor.ITextEditor;

/**
 * @author Romain Bioteau
 *
 */
public class BonitaGroovyConfiguration extends GroovyConfiguration {

	public BonitaGroovyConfiguration(GroovyColorManager colorManager, IPreferenceStore preferenceSource, ITextEditor editor) {
	    super(colorManager, preferenceSource, editor);
    }

    
    @SuppressWarnings("unchecked")
    @Override
    public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
        ContentAssistant assistant = (ContentAssistant) super.getContentAssistant(sourceViewer);
        assistant.enableAutoActivation(true) ;
        assistant.setStatusLineVisible(false) ;
        
        ContentAssistProcessor stringProcessor= new JavaCompletionProcessor(getEditor(), assistant, GroovyPartitionScanner.GROOVY_MULTILINE_STRINGS);
        assistant.setContentAssistProcessor(stringProcessor, GroovyPartitionScanner.GROOVY_MULTILINE_STRINGS);

        // remove Java content assist processor category
        // do a list copy so as not to disturb globally shared list.
        IContentAssistProcessor processor = assistant.getContentAssistProcessor(IDocument.DEFAULT_CONTENT_TYPE);
        List<CompletionProposalCategory> categories = (List<CompletionProposalCategory>) ReflectionUtils.getPrivateField(ContentAssistProcessor.class, "fCategories", processor);
        List<CompletionProposalCategory> newCategories = new ArrayList<CompletionProposalCategory>();
        for (CompletionProposalCategory category : categories) {
            if (!category.getId().equals("org.eclipse.jdt.ui.javaTypeProposalCategory")
                    && !category.getId().equals("org.eclipse.jdt.ui.javaNoTypeProposalCategory")
                    && !category.getId().equals("org.eclipse.jdt.ui.javaAllProposalCategory")
                    && !category.getId().equals("org.eclipse.mylyn.java.ui.javaAllProposalCategory")
                    && !category.getId().equals("org.eclipse.jdt.ui.templateProposalCategory")
                    && !category.getId().equals("org.eclipse.jdt.ui.defaultProposalCategory")
                    && !category.getId().equals("org.eclipse.jdt.ui.textProposalCategory")
                    && !category.getId().equals("org.eclipse.jdt.ui.swtProposalCategory")
                    && !category.getId().equals("org.eclipse.jst.ws.jaxws.ui.jaxwsProposalComputer")
                    && !category.getId().equals("org.eclipse.pde.api.tools.ui.apitools_proposal_category")) {
                newCategories.add(category);
           }
        }
        
        ReflectionUtils.setPrivateField(ContentAssistProcessor.class, "fCategories", processor, newCategories);
        return assistant;
    }	
	
}
