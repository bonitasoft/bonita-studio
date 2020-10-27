package org.bonitasoft.studio.groovy.ui.viewer.proposal.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.ExpressionProviderService;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.groovy.ScriptVariable;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;

public class ScriptExpressionContext {

    private List<Category> categories = new ArrayList<>();

    public static ScriptExpressionContext computeProposals(List<ScriptVariable> input) {
        ScriptExpressionContext context = new ScriptExpressionContext();

        IExpressionProvider parameterExressionProvider = getExpressionProvider(ExpressionConstants.PARAMETER_TYPE);
        Category parameterCategory = context.addCategory(new Category(ExpressionConstants.PARAMETER_TYPE,
                parameterExressionProvider.getTypeLabel(), parameterExressionProvider.getTypeIcon()));
        addProposals(input, parameterCategory);

        IExpressionProvider contractInputExressionProvider = getExpressionProvider(
                ExpressionConstants.CONTRACT_INPUT_TYPE);
        Category contractInputCategory = context.addCategory(new Category(ExpressionConstants.CONTRACT_INPUT_TYPE,
                contractInputExressionProvider.getTypeLabel(), contractInputExressionProvider.getTypeIcon()));
        addProposals(input, contractInputCategory);

        IExpressionProvider connectorOuputExressionProvider = getExpressionProvider(
                ExpressionConstants.CONNECTOR_OUTPUT_TYPE);
        Category connectorOutputCategory = context.addCategory(new Category(ExpressionConstants.CONNECTOR_OUTPUT_TYPE, 
                connectorOuputExressionProvider.getTypeLabel(), connectorOuputExressionProvider.getTypeIcon()));
        addProposals(input, connectorOutputCategory);
        
        IExpressionProvider variableExressionProvider = getExpressionProvider(ExpressionConstants.VARIABLE_TYPE);
        Category stepVariablesCategory = context.addCategory(new Category("step"+ExpressionConstants.VARIABLE_TYPE, "Step variables", variableExressionProvider.getTypeIcon()));
        addProposals(input, stepVariablesCategory);
        
        Category processVariablesCategory = context.addCategory( new Category("process"+ExpressionConstants.VARIABLE_TYPE, "Process variables", variableExressionProvider.getTypeIcon()));
        addProposals(input, processVariablesCategory);
        
        Category businessVariablesCategory = context.addCategory(new Category("process"+ExpressionConstants.BUSINESS_DATA_TYPE, "Business variables",
                Pics.getImage("bdm.png", BusinessObjectPlugin.getDefault())));
        addProposals(input, businessVariablesCategory);
        
        IExpressionProvider queryExressionProvider = getExpressionProvider(ExpressionConstants.QUERY_TYPE);
        Category daoCategory = context.addCategory(new Category(ExpressionConstants.DAO_TYPE, "Business queries", queryExressionProvider.getTypeIcon()));
        addProposals(input, daoCategory);
        
        IExpressionProvider documentExressionProvider = getExpressionProvider(ExpressionConstants.DOCUMENT_TYPE);
        Category docCategory = context.addCategory(new Category(ExpressionConstants.DOCUMENT_TYPE, "Documents", documentExressionProvider.getTypeIcon()));
        addProposals(input, docCategory);
        
        IExpressionProvider documentRefExressionProvider = getExpressionProvider(ExpressionConstants.DOCUMENT_REF_TYPE);
        Category decRefCategory = context.addCategory(new Category(ExpressionConstants.DOCUMENT_REF_TYPE, "Documents", documentRefExressionProvider.getTypeIcon()));
        addProposals(input, decRefCategory);
        
        Category engineCategory = context.addCategory(new Category(ExpressionConstants.ENGINE_CONSTANT_TYPE, "Engine context", Pics.getImage(PicsConstants.engineConstant)));
        addProposals(input, engineCategory);

        return context;
    }

    private static void addProposals(List<ScriptVariable> input, Category category) {
        input.stream()
                .filter(v -> Objects.equals(v.getCategory(), category.getId()))
                .map(ScriptExpressionContext::toProposal)
                .forEach(category::addScriptProposal);
    }

    private static ScriptProposal toProposal(ScriptVariable scriptVariable) {
        ScriptProposal scriptProposal = new ScriptProposal(scriptVariable.getName(), scriptVariable.getType());
        scriptProposal.setDescription(scriptVariable.getDescription());
        return scriptProposal;
    }

    private static IExpressionProvider getExpressionProvider(String expressionType) {
        return ExpressionProviderService.getInstance().getExpressionProvider(expressionType);
    }

    public Category addCategory(Category category) {
        this.categories.add(category);
        return category;
    }

    public List<Category> getCategories() {
        return categories;
    }
}
