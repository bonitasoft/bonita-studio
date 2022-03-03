/**
 * Copyright (C) 2021 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.groovy.ui.viewer.proposal.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.DataUtil;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.groovy.ScriptVariable;
import org.bonitasoft.studio.groovy.ui.JDTMethodHelper;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Document;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;


public class VariableScriptProposalSupplier extends ScriptProposalSupplier {
    
    private static final Set<String> PRIMITIVE_TYPES = new HashSet<>();
    static {
        PRIMITIVE_TYPES.add(String.class.getName());
        PRIMITIVE_TYPES.add(Boolean.class.getName());
        PRIMITIVE_TYPES.add(Date.class.getName());
        PRIMITIVE_TYPES.add(Integer.class.getName());
        PRIMITIVE_TYPES.add(Long.class.getName());
        PRIMITIVE_TYPES.add(Double.class.getName());
        PRIMITIVE_TYPES.add(Float.class.getName());
        PRIMITIVE_TYPES.add(Map.class.getName());
        PRIMITIVE_TYPES.add(List.class.getName());
        PRIMITIVE_TYPES.add(Set.class.getName());
        PRIMITIVE_TYPES.add(Collection.class.getName());
        PRIMITIVE_TYPES.add(Short.class.getName());
        PRIMITIVE_TYPES.add(Byte.class.getName());
        PRIMITIVE_TYPES.add(BigDecimal.class.getName());
    }
    
    private ScriptVariable scriptVariable;
    private RepositoryAccessor repositoryAccessor;
    private EObject processContext;

    public VariableScriptProposalSupplier(ScriptVariable scriptVariable, RepositoryAccessor repositoryAccessor, EObject processContext) {
        this.scriptVariable = scriptVariable;
        this.repositoryAccessor = repositoryAccessor;
        this.processContext = processContext;
    }

    @Override
    protected ScriptProposal toProposal() {
        ScriptProposal scriptProposal = new ScriptProposal(scriptVariable.getName(), scriptVariable.getType());
        scriptProposal.setDescription(scriptVariable.getDescription());
        String type = scriptVariable.getType();
        if (isBusinessObject(type)) {
            addBusinessVariableProposalChildren(scriptProposal);
        } else if (isJavaObject(type)) {
            addGetterProposalChildren(scriptProposal);
        }else if (List.class.getName().equals(type)) {
            findData(scriptVariable.getName())
                    .ifPresent(d -> scriptProposal.setType(DataUtil.getDisplayTypeName(d)));
            findDocument(scriptVariable.getName())
                    .ifPresent(d -> scriptProposal.setType("List<Document>"));
        }
        return scriptProposal;
    }
    
    private boolean isBusinessObject(String type) {
        return getBusinessObjectModelRepository()
                .getChildByQualifiedName(type)
                .isPresent();
    }
    

    private Optional<Data> findData(String name) {
        return ModelHelper.getAccessibleData(processContext, true).stream()
                .filter(d -> Objects.equals(d.getName(), name))
                .findFirst();
    }

    private Optional<Document> findDocument(String name) {
        return ModelHelper.getParentPool(processContext).getDocuments().stream()
                .filter(d -> Objects.equals(d.getName(), name))
                .findFirst();
    }
    
    @SuppressWarnings("unchecked")
    private BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> getBusinessObjectModelRepository() {
        return repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class);
    }
    
    private void addBusinessVariableProposalChildren(ScriptProposal scriptProposal) {
        getBusinessObjectModelRepository()
                .getBusinessObjectByQualifiedName(scriptProposal.getType())
                .ifPresent(bo -> bo.getFields().stream()
                        .map(VariableScriptProposalSupplier::toProposal)
                        .forEach(scriptProposal::addChild));
    }
    
    private static ScriptProposal toProposal(Field field) {
        String type = field instanceof SimpleField ? ((SimpleField) field).getType().getClazz().getName()
                : ((RelationField) field).getReference().getSimpleName();
        if (field.isCollection() != null && field.isCollection()) {
            type = "List<" + type + ">";
        }
        ScriptProposal scriptProposal = new ScriptProposal(field.getName(), type);
        scriptProposal.setDescription(field.getDescription());
        return scriptProposal;
    }
    
    private void addGetterProposalChildren(ScriptProposal scriptProposal) {
        final IJavaProject project = repositoryAccessor.getCurrentRepository().getJavaProject();
        try {
            IType type = project.findType(scriptProposal.getType());
            if(type != null) {
                JDTMethodHelper.allPublicNonStaticMethodWithoutParameterReturningNonVoid(type)
                .stream()
                .map(VariableScriptProposalSupplier::toProposal)
                .filter(Objects::nonNull)
                .forEach(scriptProposal::addChild);
            }
        } catch (JavaModelException e) {
            BonitaStudioLog.error(e);
        }
    }
    
    private static ScriptProposal toProposal(IMethod method) {
        try {
            String qualifiedType = JDTMethodHelper.retrieveQualifiedType(method.getReturnType(), method.getDeclaringType());
            ScriptProposal scriptProposal = new ScriptProposal(method.getElementName()+"()", qualifiedType);
            scriptProposal.setDescription(method.getAttachedJavadoc(AbstractRepository.NULL_PROGRESS_MONITOR));
            return scriptProposal;
        } catch (JavaModelException e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }
    
    private static boolean isJavaObject(String type) {
        return !PRIMITIVE_TYPES.contains(type);
    }

}
