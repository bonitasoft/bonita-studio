/**
 * Copyright (C) 2015 BonitaSoft S.A.
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
package org.bonitasoft.studio.contract.ui.property.constraint.edit.editor;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.groovy.eclipse.editor.GroovyColorManager;
import org.codehaus.groovy.eclipse.editor.GroovyConfiguration;
import org.eclipse.jdt.groovy.core.util.ReflectionUtils;
import org.eclipse.jdt.internal.ui.text.java.CompletionProposalCategory;
import org.eclipse.jdt.internal.ui.text.java.ContentAssistProcessor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.texteditor.ITextEditor;

public class ConstraintExpressionSourceViewerConfiguration extends GroovyConfiguration {

    public ConstraintExpressionSourceViewerConfiguration(final GroovyColorManager colorManager, final IPreferenceStore preferenceSource,
            final ITextEditor editor) {
        super(colorManager, preferenceSource, editor);
    }

    @Override
    public IContentAssistant getContentAssistant(final ISourceViewer sourceViewer) {
        final ContentAssistant contentAssistant = (ContentAssistant) super.getContentAssistant(sourceViewer);
        contentAssistant.enableAutoActivation(true);
        contentAssistant.setStatusLineVisible(false);
        final IContentAssistProcessor processor = contentAssistant.getContentAssistProcessor(IDocument.DEFAULT_CONTENT_TYPE);
        final List<CompletionProposalCategory> categories = (List<CompletionProposalCategory>) ReflectionUtils.getPrivateField(ContentAssistProcessor.class,
                "fCategories", processor);
        final List<CompletionProposalCategory> newCategories = new ArrayList<CompletionProposalCategory>();
        for (final CompletionProposalCategory category : categories) {
            if ("org.bonitasoft.studio.contract.input.category".equals(category.getId())) {
                newCategories.add(category);
            }
        }

        ReflectionUtils.setPrivateField(ContentAssistProcessor.class, "fCategories", processor, newCategories);
        return contentAssistant;
    }

}
