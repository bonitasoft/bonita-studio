package org.bonitasoft.studio.groovy.ui.viewer.proposal;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.codehaus.groovy.eclipse.quickfix.GroovyQuickFixPlugin;
import org.eclipse.jface.text.templates.Template;

public class CodeTemplatesProvider {
    
    private static final Set<String> BONITA_USERS_TEMPLATES = new HashSet<>();
    static {
        BONITA_USERS_TEMPLATES.add("userById");
        BONITA_USERS_TEMPLATES.add("userByUserName");
        BONITA_USERS_TEMPLATES.add("processInitiatorUser");
        BONITA_USERS_TEMPLATES.add("userPersonalContact");
        BONITA_USERS_TEMPLATES.add("userProfessionalContact");
    }
    private Template[] groovyTemplates;

    public CodeTemplatesProvider() {
        groovyTemplates = GroovyQuickFixPlugin.getDefault().getTemplateStore().getTemplates();
    }
    
    public List<Template> getBonitaUsersTemplates(){
        return Stream.of(groovyTemplates)
                    .filter(t -> BONITA_USERS_TEMPLATES.contains(t.getName()))
                    .collect(Collectors.toList());
    }
    
    

}
