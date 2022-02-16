package org.bonitasoft.studio.groovy.ui.viewer.proposal.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.swt.graphics.Image;

public class Category implements DescriptionProvider{

    private List<Supplier<ScriptProposal>> proposals = new ArrayList<>();
    private List<Category> subcategories = new ArrayList<>();
    private String name;
    private Image icon;
    private String id;
    private Category parentCategory;
    private String description;

    public Category(String id, String name, Image icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public void addScriptProposalSupplier(Supplier<ScriptProposal> proposal) {
        proposals.add(proposal);
    }
    
    public boolean hasProposals() {
        return !proposals.isEmpty();
    }

    public List<ScriptProposal> getProposals() {
        return proposals.stream()
                .map(Supplier::get)
                .map(p -> {p.setCategory(Category.this); return p;})
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Image getIcon() {
        return icon;
    }
    
    public Category getParentCategory() {
        return parentCategory;
    }

    public void addSubcategory(Category subcategory) {
        subcategory.setParentCategory(this);
        subcategories.add(subcategory);
    }
    
    public List<Category> getSubcategories() {
        return subcategories;
    }

    public void setParentCategory(Category parentCategory) {
       this.parentCategory = parentCategory;
    }

    public void setDescription(String description) {
       this.description = description;
    }
    
    @Override
    public String getDescription() {
        return description;
    }

}
