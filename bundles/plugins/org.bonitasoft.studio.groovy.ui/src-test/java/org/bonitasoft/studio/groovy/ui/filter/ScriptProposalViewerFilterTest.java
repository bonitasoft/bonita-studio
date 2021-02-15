package org.bonitasoft.studio.groovy.ui.filter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.bonitasoft.studio.groovy.ui.viewer.proposal.model.Category;
import org.bonitasoft.studio.groovy.ui.viewer.proposal.model.ScriptProposal;
import org.junit.Test;

public class ScriptProposalViewerFilterTest {

    @Test
    public void should_filter_simple_proposal() {
        Category category = new Category("categoryId", "categoryName", null);
        ScriptProposal proposal1 = new ScriptProposal("proposal1", "");
        ScriptProposal proposal2 = new ScriptProposal("proposal2", "");
        category.addScriptProposalSupplier(()->proposal1);
        category.addScriptProposalSupplier(()->proposal2);

        List<ScriptProposal> proposalToFilter = Arrays.asList(proposal1);
        ScriptProposalViewerFilter filter = new ScriptProposalViewerFilter(proposalToFilter);

        assertThat(filter.select(null, null, proposal1)).isFalse();
        assertThat(filter.select(null, null, proposal2)).isTrue();
    }

    @Test
    public void should_filter_proposal_with_children() {
        Category category = new Category("categoryId", "categoryName", null);

        ScriptProposal parentProposal1 = new ScriptProposal("parentProposal1", "");
        ScriptProposal childProposal1 = new ScriptProposal("childProposal1", "");
        parentProposal1.addChild(childProposal1);

        ScriptProposal parentProposal2 = new ScriptProposal("parentProposal2", "");
        ScriptProposal childProposal2 = new ScriptProposal("childProposal2", "");
        ScriptProposal childProposal3 = new ScriptProposal("childProposal3", "");
        parentProposal2.addChild(childProposal2);
        parentProposal2.addChild(childProposal3);

        category.addScriptProposalSupplier(()-> parentProposal1);
        category.addScriptProposalSupplier(()-> parentProposal2);

        List<ScriptProposal> proposalToFilter = Arrays.asList(childProposal1, childProposal2);
        ScriptProposalViewerFilter filter = new ScriptProposalViewerFilter(proposalToFilter);

        assertThat(filter.select(null, null, parentProposal1)).isFalse();
        assertThat(filter.select(null, null, parentProposal2)).isTrue();
    }

    @Test
    public void should_filter_simple_category() {
        Category category1 = new Category("categoryId1", "categoryName1", null);
        ScriptProposal proposal1 = new ScriptProposal("proposal1", "");
        category1.addScriptProposalSupplier(()-> proposal1);

        Category category2 = new Category("categoryId2", "categoryName2", null);
        ScriptProposal proposal2 = new ScriptProposal("proposal2", "");
        category2.addScriptProposalSupplier(()-> proposal2);

        List<ScriptProposal> proposalToFilter = Arrays.asList(proposal1);
        ScriptProposalViewerFilter filter = new ScriptProposalViewerFilter(proposalToFilter);

        assertThat(filter.select(null, null, category1)).isFalse();
        assertThat(filter.select(null, null, category2)).isTrue();
    }

    @Test
    public void should_filter_category_with_subcategory() {
        Category category1 = new Category("categoryId1", "categoryName1", null);
        Category subCategory1 = new Category("subCategoryId1", "subCategoryName1", null);
        ScriptProposal parentProposal1 = new ScriptProposal("parentProposal1", "");
        ScriptProposal childProposal1 = new ScriptProposal("childProposal1", "");
        parentProposal1.addChild(childProposal1);
        subCategory1.addScriptProposalSupplier(()-> childProposal1);
        category1.addSubcategory(subCategory1);

        Category category2 = new Category("categoryId2", "categoryName2", null);
        Category subCategory2 = new Category("subCategoryId2", "subCategoryName2", null);
        Category subCategory3 = new Category("subCategoryId3", "subCategoryName3", null);
        ScriptProposal parentProposal2 = new ScriptProposal("parentProposal2", "");
        ScriptProposal childProposal2 = new ScriptProposal("childProposal2", "");
        ScriptProposal childProposal3 = new ScriptProposal("childProposal3", "");
        ScriptProposal parentProposal3 = new ScriptProposal("parentProposal3", "");

        parentProposal2.addChild(childProposal2);
        parentProposal2.addChild(childProposal3);
        subCategory2.addScriptProposalSupplier(()-> parentProposal2);
        subCategory3.addScriptProposalSupplier(()-> parentProposal3);
        category2.addSubcategory(subCategory2);
        category2.addSubcategory(subCategory3);

        List<ScriptProposal> proposalToFilter = Arrays.asList(childProposal1, childProposal2, parentProposal3);
        ScriptProposalViewerFilter filter = new ScriptProposalViewerFilter(proposalToFilter);

        assertThat(filter.select(null, null, subCategory1)).isFalse();
        assertThat(filter.select(null, null, subCategory2)).isTrue();
        assertThat(filter.select(null, null, subCategory3)).isFalse();

        assertThat(filter.select(null, null, category1)).isFalse();
        assertThat(filter.select(null, null, category2)).isTrue();
    }

}
