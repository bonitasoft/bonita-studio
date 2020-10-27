package org.bonitasoft.studio.groovy.ui.viewer.proposal.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Image;

public class Category {

    private List<ScriptProposal> proposals = new ArrayList<>();
    private String name;
    private Image icon;
    private String id;

    public Category(String id, String name, Image icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public void addScriptProposal(ScriptProposal proposal) {
        proposal.setCategory(this);
        proposals.add(proposal);
    }

    public List<ScriptProposal> getProposals() {
        return proposals;
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

}
