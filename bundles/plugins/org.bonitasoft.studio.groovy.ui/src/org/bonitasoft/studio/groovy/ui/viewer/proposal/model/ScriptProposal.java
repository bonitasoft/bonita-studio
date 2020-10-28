package org.bonitasoft.studio.groovy.ui.viewer.proposal.model;

import org.codehaus.groovy.eclipse.editor.GroovyEditor;
import org.codehaus.groovy.eclipse.quickfix.GroovyQuickFixPlugin;
import org.codehaus.groovy.eclipse.quickfix.templates.GroovyContext;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.jface.text.templates.TemplateProposal;

public class ScriptProposal {

    private static final TemplateContextType CONTEXT_TYPE = GroovyQuickFixPlugin.getDefault()
            .getTemplateContextRegistry()
            .getContextType(GroovyQuickFixPlugin.GROOVY_CONTEXT_TYPE);
    private String name;
    private String type;
    private String description;
    private Category category;
    private Template template;

    public ScriptProposal(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public ScriptProposal(String name, Template template) {
        this.name = name;
        this.template = template;
    }
    
    public String getId() {
        StringBuilder sb = new StringBuilder();
        Category c = getCategory();
        while (c != null) {
            sb.append("[");
            sb.append(c.getId());
            sb.append("]");
            c = c.getParentCategory();
        }
        sb.append("[");
        sb.append(name);
        sb.append("]");
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void apply(GroovyEditor editor) {
        ISourceViewer viewer = editor.getViewer();
        if (template != null) {
            GroovyContext templateContext = new GroovyContext(CONTEXT_TYPE, viewer.getDocument(),
                    editor.getCaretOffset(), 0, editor.getGroovyCompilationUnit());
            templateContext.setForceEvaluation(true);
            templateContext.setVariable("selection", "");
            Region region = new Region(templateContext.getCompletionOffset(), templateContext.getCompletionLength());
            new TemplateProposal(template, templateContext, region, null).apply(viewer, '.', 0,
                    viewer.getSelectedRange().x);
        } else {
            viewer.getTextWidget().insert(" " + getName());
        }
    }

}
