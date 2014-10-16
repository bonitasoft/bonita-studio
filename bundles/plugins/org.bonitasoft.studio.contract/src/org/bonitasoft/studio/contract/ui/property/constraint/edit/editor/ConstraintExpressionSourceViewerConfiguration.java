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

    @SuppressWarnings({ "restriction", "unchecked" })
    @Override
    public IContentAssistant getContentAssistant(final ISourceViewer sourceViewer) {
        final ContentAssistant contentAssistant = (ContentAssistant) super.getContentAssistant(sourceViewer);
        final IContentAssistProcessor processor = contentAssistant.getContentAssistProcessor(IDocument.DEFAULT_CONTENT_TYPE);
        final List<CompletionProposalCategory> categories = (List<CompletionProposalCategory>) ReflectionUtils.getPrivateField(ContentAssistProcessor.class,
                "fCategories", processor);
        final List<CompletionProposalCategory> newCategories = new ArrayList<CompletionProposalCategory>();
        for (final CompletionProposalCategory category : categories) {
            if (category.getId().equals("org.bonitasoft.studio.contract.input.category")) {
                newCategories.add(category);
            }
        }

        ReflectionUtils.setPrivateField(ContentAssistProcessor.class, "fCategories", processor, newCategories);
        return contentAssistant;
    }

}
