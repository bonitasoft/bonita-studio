package org.bonitasoft.studio.groovy.ui.viewer.proposal.model;


public class ScriptProposal {

    private String name;
    private String type;
    private String description;
    private Category category;

    public ScriptProposal(String name, String type) {
        this.name = name;
        this.type = type;
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
    
    

}
