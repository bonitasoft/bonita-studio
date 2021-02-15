package org.bonitasoft.studio.groovy.ui.viewer.proposal.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.expression.editor.ExpressionProviderService;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.groovy.ScriptVariable;
import org.bonitasoft.studio.groovy.library.FunctionsRepositoryFactory;
import org.bonitasoft.studio.groovy.ui.Activator;
import org.bonitasoft.studio.groovy.ui.Messages;
import org.bonitasoft.studio.groovy.ui.viewer.proposal.CodeTemplatesProvider;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;

public class ScriptExpressionContext {

    private List<Category> categories = new ArrayList<>();

    public static ScriptExpressionContext computeProposals(RepositoryAccessor repositoryAccessor,
            List<ScriptVariable> input, EObject processContext) {
        ScriptExpressionContext context = new ScriptExpressionContext();

        IExpressionProvider parameterExressionProvider = getExpressionProvider(ExpressionConstants.PARAMETER_TYPE);
        Category parameterCategory = context.addCategory(new Category(ExpressionConstants.PARAMETER_TYPE,
                parameterExressionProvider.getTypeLabel(), parameterExressionProvider.getTypeIcon()));
        parameterCategory.setDescription(Messages.parameterCategoryDescription);
        addProposals(input, parameterCategory, repositoryAccessor, processContext);

        IExpressionProvider contractInputExressionProvider = getExpressionProvider(
                ExpressionConstants.CONTRACT_INPUT_TYPE);
        Category contractInputCategory = context.addCategory(new Category(ExpressionConstants.CONTRACT_INPUT_TYPE,
                contractInputExressionProvider.getTypeLabel(), contractInputExressionProvider.getTypeIcon()));
        contractInputCategory.setDescription(Messages.contractInputCategoryDescription);
        addProposals(input, contractInputCategory, repositoryAccessor, processContext);

        IExpressionProvider connectorOuputExressionProvider = getExpressionProvider(
                ExpressionConstants.CONNECTOR_OUTPUT_TYPE);
        Category connectorOutputCategory = context.addCategory(new Category(ExpressionConstants.CONNECTOR_OUTPUT_TYPE,
                connectorOuputExressionProvider.getTypeLabel(), connectorOuputExressionProvider.getTypeIcon()));
        contractInputCategory.setDescription(Messages.connectorOutputCategoryDescription);
        addProposals(input, connectorOutputCategory, repositoryAccessor, processContext);

        IExpressionProvider variableExressionProvider = getExpressionProvider(ExpressionConstants.VARIABLE_TYPE);
        Category stepVariablesCategory = context.addCategory(new Category("step" + ExpressionConstants.VARIABLE_TYPE,
                Messages.stepVariables, variableExressionProvider.getTypeIcon()));
        stepVariablesCategory.setDescription(Messages.stepVariablesCategoryDescription);
        addProposals(input, stepVariablesCategory, repositoryAccessor, processContext);

        Category processVariablesCategory = context
                .addCategory(new Category("process" + ExpressionConstants.VARIABLE_TYPE, Messages.processVariables,
                        variableExressionProvider.getTypeIcon()));
        processVariablesCategory.setDescription(Messages.processVariablesCategoryDescription);
        addProposals(input, processVariablesCategory, repositoryAccessor, processContext);

        Category businessVariablesCategory = context
                .addCategory(
                        new Category("process" + ExpressionConstants.BUSINESS_DATA_TYPE, Messages.businessVariables,
                                Pics.getImage("bdm.png", BusinessObjectPlugin.getDefault())));
        businessVariablesCategory.setDescription(Messages.businessVariablesCategoryDescription);
        addProposals(input, businessVariablesCategory, repositoryAccessor, processContext);

        IExpressionProvider queryExressionProvider = getExpressionProvider(ExpressionConstants.QUERY_TYPE);
        Category daoCategory = context.addCategory(new Category(ExpressionConstants.DAO_TYPE, Messages.businessQueries,
                queryExressionProvider.getTypeIcon()));
        daoCategory.setDescription(Messages.businessQueriesCategoryDescription);
        addDAOProposals(input, daoCategory, getCurrentBDM(repositoryAccessor));

        IExpressionProvider documentExressionProvider = getExpressionProvider(ExpressionConstants.DOCUMENT_TYPE);
        Category docCategory = context.addCategory(
                new Category(ExpressionConstants.DOCUMENT_TYPE, Messages.documents,
                        documentExressionProvider.getTypeIcon()));
        docCategory.setDescription(Messages.documentsCategoryDescription);
        addProposals(input, docCategory, repositoryAccessor, processContext);

        IExpressionProvider documentRefExressionProvider = getExpressionProvider(ExpressionConstants.DOCUMENT_REF_TYPE);
        Category docRefCategory = context
                .addCategory(new Category(ExpressionConstants.DOCUMENT_REF_TYPE, Messages.documents,
                        documentRefExressionProvider.getTypeIcon()));
        docRefCategory.setDescription(Messages.documentsCategoryDescription);
        addProposals(input, docRefCategory, repositoryAccessor, processContext);

        Category executionContextCategory = context.addCategory(new Category(ExpressionConstants.ENGINE_CONSTANT_TYPE,
                Messages.executionContext, Pics.getImage(PicsConstants.engineConstant)));
        executionContextCategory.setDescription(Messages.executionContextCategoryDescription);
        addProposals(input, executionContextCategory, repositoryAccessor, processContext);

        Category codeTemplatesCategory = context.addCategory(new Category("code-templates", Messages.codeTemplates,
                Pics.getImage("template_obj.png", Activator.getDefault())));
        codeTemplatesCategory.setDescription(Messages.codeTemplatesCategoryDescription);
        CodeTemplatesProvider codeTemplatesProvider = new CodeTemplatesProvider();

        Category usersCodeTemplatesCategory = new Category("users-templates", Messages.usersTemplates,
                Pics.getImage("users.png", Activator.getDefault()));
        usersCodeTemplatesCategory.setDescription(Messages.usersTemplatesCategoryDescription);
        addUserTemplatesProposals(usersCodeTemplatesCategory, codeTemplatesProvider);
        codeTemplatesCategory.addSubcategory(usersCodeTemplatesCategory);

        Category userDefinedFunctionsCategory = new Category("user-defined-functions", Messages.userDefined,
                Pics.getImage("groovy_file.gif", Activator.getDefault()));
        userDefinedFunctionsCategory.setDescription(Messages.userDefinedCategoryDescription);
        addUserDefinedProposals(userDefinedFunctionsCategory);
        codeTemplatesCategory.addSubcategory(userDefinedFunctionsCategory);

        return context;
    }

    private static BusinessObjectModel getCurrentBDM(RepositoryAccessor repositoryAccessor) {
        BusinessObjectModelFileStore bdmFileStore = (BusinessObjectModelFileStore) repositoryAccessor
                .getRepositoryStore(BusinessObjectModelRepositoryStore.class)
                .getChild(BusinessObjectModelFileStore.BOM_FILENAME, false);
        if (bdmFileStore != null) {
            try {
                return bdmFileStore.getContent();
            } catch (ReadFileStoreException e) {
                BonitaStudioLog.error(e);
            }
        }
        return null;
    }

    private static void addUserDefinedProposals(Category category) {
        FunctionsRepositoryFactory.getUserFunctionCatgory()
                .getFunctions()
                .stream()
                .map(FunctionScriptProposalSupplier::new)
                .forEach(category::addScriptProposalSupplier);
    }

   

    

    private static void addUserTemplatesProposals(Category category, CodeTemplatesProvider codeTemplatesProvider) {
        codeTemplatesProvider.getBonitaUsersTemplates()
                .forEach(template -> category.addScriptProposalSupplier(new TemplateScriptProposalSupplier(template)));
    }

    private static void addProposals(List<ScriptVariable> input, Category category,
            RepositoryAccessor repositoryAccessor, EObject processContext) {
        input.stream()
                .filter(v -> Objects.equals(v.getCategory(), category.getId()))
                .map(scriptVariable -> new VariableScriptProposalSupplier(scriptVariable, repositoryAccessor, processContext))
                .forEach(category::addScriptProposalSupplier);
    }


    private static void addDAOProposals(List<ScriptVariable> input, Category category, BusinessObjectModel bdm) {
        input.stream()
                .filter(v -> Objects.equals(v.getCategory(), category.getId()))
                .map(v -> createBusinessObjectDaoCategory(v, bdm))
                .forEach(category::addSubcategory);
    }

    private static Category createBusinessObjectDaoCategory(ScriptVariable variable, BusinessObjectModel bdm) {
        String catName = NamingUtils.getSimpleName(variable.getType());
        if (catName.endsWith("DAO")) {
            catName = catName.substring(0, catName.length() - 3);
        }
        Category cat = new Category(variable.getType(), catName,
                Pics.getImage("BusinessObjectData.gif", Activator.getDefault()));
        findBusinessObjectFromDAO(variable.getType(), bdm)
                .ifPresent(bo -> cat.setDescription(bo.getDescription()));
        IJavaProject javaProject = RepositoryManager.getInstance().getCurrentRepository().getJavaProject();
        try {
            IType type = javaProject.findType(variable.getType());
            Stream.of(type.getMethods())
                    .filter(method -> {
                        try {
                            return Flags.isPublic(method.getFlags());
                        } catch (JavaModelException e) {
                            BonitaStudioLog.error(e);
                            return false;
                        }
                    })
                    .map(method -> new DAOScriptProposalSupplier(method, variable.getName(), bdm))
                    .forEach(cat::addScriptProposalSupplier);
        } catch (JavaModelException e) {
            BonitaStudioLog.error(e);
        }
        return cat;
    }
    
    static Optional<BusinessObject> findBusinessObjectFromDAO(String daoType, BusinessObjectModel bdm) {
        String businessObjectQualifiedName = daoType.substring(0, daoType.length() - 3);
        return bdm.getBusinessObjects().stream()
                .filter(bo -> bo.getQualifiedName().equals(businessObjectQualifiedName))
                .findFirst();
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

    public static Optional<ScriptProposal> findProposalById(String proposalId, List<Category> categories) {
        for (Category c : categories) {
            if (c.getSubcategories().isEmpty()) {
                Optional<ScriptProposal> result = findMatchingProposal(c.getProposals(), proposalId);
                if (result.isPresent()) {
                    return result;
                }
            } else {
                Optional<ScriptProposal> result = findProposalById(proposalId, c.getSubcategories());
                if (result.isPresent()) {
                    return result;
                }
            }
        }
        return Optional.empty();
    }

    private static Optional<ScriptProposal> findMatchingProposal(List<ScriptProposal> proposals, String proposalId) {
        for (ScriptProposal proposal : proposals) {
            if (Objects.equals(proposalId, proposal.getId())) {
                return Optional.of(proposal);
            }
            Optional<ScriptProposal> matchingChild = findMatchingProposal(proposal.getChildren(), proposalId);
            if (matchingChild.isPresent()) {
                return matchingChild;
            }
        }
        return Optional.empty();
    }
}
