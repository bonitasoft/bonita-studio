package org.bonitasoft.studio.groovy.ui.viewer.proposal.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bonitasoft.studio.groovy.ui.Activator;
import org.bonitasoft.studio.pics.Pics;
import org.codehaus.groovy.eclipse.editor.GroovyEditor;
import org.codehaus.groovy.eclipse.quickfix.GroovyQuickFixPlugin;
import org.codehaus.groovy.eclipse.quickfix.templates.GroovyContext;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.jface.text.templates.TemplateProposal;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Image;

public class ScriptProposal implements DescriptionProvider {

    private static final TemplateContextType CONTEXT_TYPE;
    private String name;
    private String type;
    private String description;
    private Category category;
    private Template template;
    private List<ScriptProposal> children = new ArrayList<>();
    private Optional<ScriptProposal> parentProposal = Optional.empty();
    private Image icon = Pics.getImage("proposal.png", Activator.getDefault());

    static {
        CONTEXT_TYPE = GroovyQuickFixPlugin.getDefault() != null
                ? GroovyQuickFixPlugin.getDefault().getTemplateContextRegistry()
                        .getContextType(GroovyQuickFixPlugin.GROOVY_CONTEXT_TYPE)
                : null; // for test purpose
    }

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

    @Override
    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }

    public void apply(GroovyEditor editor) {
        ISourceViewer viewer = editor.getViewer();
        StyledText textWidget = viewer.getTextWidget();
        if (template != null) {
            GroovyContext templateContext = new GroovyContext(CONTEXT_TYPE, viewer.getDocument(),
                    editor.getCaretOffset(), 0, editor.getGroovyCompilationUnit());
            templateContext.setForceEvaluation(true);
            templateContext.setVariable("selection", "");
            Region region = new Region(templateContext.getCompletionOffset(), templateContext.getCompletionLength());
            new TemplateProposal(template, templateContext, region, null).apply(viewer, '.', 0,
                    viewer.getSelectedRange().x);
        } else {
            textWidget.insert(toGroovyExpression());
        }
        textWidget.setFocus();
    }

    protected String toGroovyExpression() {
        String expression = getName();
        if (parentProposal.isPresent()) {
            expression = String.format("%s.%s", parentProposal.get().toGroovyExpression(), expression);
        }
        return expression;
    }

    public void addChild(ScriptProposal child) {
        children.add(child);
        child.setParentProposal(this);
    }

    public List<ScriptProposal> getChildren() {
        return children;
    }

    public void setParentProposal(ScriptProposal parent) {
        this.parentProposal = Optional.ofNullable(parent);
    }

    public Optional<ScriptProposal> getParentProposal() {
        return parentProposal;
    }

    public Image getIcon() {
        return icon;
    }

    public void setType(String type) {
        this.type = type;
    }

}
