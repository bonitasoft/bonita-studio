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

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.bonitasoft.studio.groovy.contentassist.ExtendedJavaCompletionProcessor;
import org.codehaus.groovy.eclipse.editor.GroovyColorManager;
import org.codehaus.groovy.eclipse.editor.GroovyConfiguration;
import org.eclipse.jdt.groovy.core.util.ReflectionUtils;
import org.eclipse.jdt.internal.ui.text.ContentAssistPreference;
import org.eclipse.jdt.internal.ui.text.java.CompletionProposalCategory;
import org.eclipse.jdt.internal.ui.text.java.ContentAssistProcessor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.texteditor.ITextEditor;

public class ConstraintExpressionSourceViewerConfiguration extends GroovyConfiguration {

    private static final String CONSTRAINT_CONTENT_ASSIST_CATEGORY_ID = "org.codehaus.groovy.contract.input.category";

    public ConstraintExpressionSourceViewerConfiguration(final GroovyColorManager colorManager,
            final IPreferenceStore preferenceSource,
            final ITextEditor editor) {
        super(colorManager, preferenceSource, editor);
    }

    @Override
    public IContentAssistant getContentAssistant(final ISourceViewer sourceViewer) {
        // returns only Groovy-approved completion proposal categories
        ContentAssistant assistant = (ContentAssistant) super.getContentAssistant(sourceViewer);
        assistant.enableAutoActivation(true);
        assistant.setStatusLineVisible(false);

        // retain only contract input categories
        final ExtendedJavaCompletionProcessor processor = new ExtendedJavaCompletionProcessor(getEditor(), assistant,
                IDocument.DEFAULT_CONTENT_TYPE);
        assistant.setContentAssistProcessor(processor, IDocument.DEFAULT_CONTENT_TYPE);
        List<CompletionProposalCategory> categories = (List<CompletionProposalCategory>) ReflectionUtils
                .getPrivateField(ContentAssistProcessor.class, "fCategories", processor);

        ReflectionUtils.setPrivateField(ContentAssistProcessor.class, "fCategories", processor, categories.stream()
                .filter(category -> Objects.equals(category.getId(), CONSTRAINT_CONTENT_ASSIST_CATEGORY_ID))
                .collect(Collectors.toList()));

        ContentAssistPreference.configure(assistant, fPreferenceStore);

        return assistant;
    }

}
