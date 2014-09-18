/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.contract.ui.property.edit.proposal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bonitasoft.studio.common.DataUtil;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.data.provider.JavaSetterContentProvider;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.internal.corext.util.JavaModelUtil;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;


/**
 * @author Romain Bioteau
 *
 */
@SuppressWarnings("restriction")
public class InputMappingProposalProvider implements IContentProposalProvider {

    private final ContractInput contractInput;
    private final Map<String, IContentProposal> proposals = new HashMap<String, IContentProposal>();
    private List<Data> accessibleData;
    private static Set<String> compatibleTypes;
    static{
        compatibleTypes = new HashSet<String>();
        compatibleTypes.add(String.class.getName());
        compatibleTypes.add(Integer.class.getName());
        compatibleTypes.add(Double.class.getName());
        compatibleTypes.add(Long.class.getName());
        compatibleTypes.add(Float.class.getName());
        compatibleTypes.add(Boolean.class.getName());
        compatibleTypes.add(Date.class.getName());
    }
    private static Map<String, String> primitiveToObjectTypes;
    static {
        primitiveToObjectTypes = new HashMap<String, String>();
        primitiveToObjectTypes.put("int", Integer.class.getName());
        primitiveToObjectTypes.put("boolean", Boolean.class.getName());
        primitiveToObjectTypes.put("long", Long.class.getName());
        primitiveToObjectTypes.put("double", Double.class.getName());
        primitiveToObjectTypes.put("float", Float.class.getName());
        primitiveToObjectTypes.put("short", Short.class.getName());
        primitiveToObjectTypes.put("byte", Byte.class.getName());
        primitiveToObjectTypes.put("E", Object.class.getName());
        primitiveToObjectTypes.put("V", Object.class.getName());
    }

    public InputMappingProposalProvider(final ContractInput contractInput) {
        this.contractInput = contractInput;
    }

    private void initializeVariableScope() {
        if (accessibleData == null) {
            accessibleData = getDataInScope();
            for(final Data d : accessibleData){
                if (isCompatibleType(DataUtil.getTechnicalTypeFor(d))) {
                    proposals.put(d.getName(), new InputMappingProposal(d));
                }
                if (d instanceof JavaObjectData) {
                    final String className = ((JavaObjectData) d).getClassName();
                    final IType javaType = getType(className);
                    final Object[] setters = getSetters(javaType);
                    if (setters != null) {
                        for (final Object m : setters) {
                            if (m instanceof IMethod && ((IMethod) m).getElementName().startsWith("set")) {
                                final String inputType = toJavaType((IMethod) m);
                                if (isCompatibleType(inputType)) {
                                    final InputMappingProposal inputMappingProposal = new InputMappingProposal(d, ((IMethod) m).getElementName(), inputType);
                                    proposals.put(inputMappingProposal.getContent(), inputMappingProposal);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    protected Object[] getSetters(final IType javaType) {
        return new JavaSetterContentProvider().getElements(javaType);
    }

    protected List<Data> getDataInScope() {
        return ModelHelper.getAccessibleData(contractInput);
    }

    private boolean isCompatibleType(final String inputType) {
        return compatibleTypes.contains(inputType);
    }

    @SuppressWarnings("restriction")
    protected String toJavaType(final IMethod method) {
        String qualifiedType = Object.class.getName();
        try {
            qualifiedType = JavaModelUtil.getResolvedTypeName(Signature.getTypeErasure(method.getParameterTypes()[0]), method.getDeclaringType());
        } catch (final JavaModelException e) {
            BonitaStudioLog.error(e);
        } catch (final IllegalArgumentException e) {
            BonitaStudioLog.error(e);
        }
        if (primitiveToObjectTypes.containsKey(qualifiedType)) {
            qualifiedType = primitiveToObjectTypes.get(qualifiedType);
        }
        return qualifiedType;
    }

    protected IType getType(final String className) {
        final IJavaProject javaProject = getJavaProject();
        try {
            return javaProject.findType(className);
        } catch (final JavaModelException e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    protected IJavaProject getJavaProject() {
        return RepositoryManager.getInstance().getCurrentRepository().getJavaProject();
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.fieldassist.IContentProposalProvider#getProposals(java.lang.String, int)
     */
    @Override
    public IContentProposal[] getProposals(final String contents, final int position) {
        initializeVariableScope();
        final List<IContentProposal> result = new ArrayList<IContentProposal>();
        for (final Entry<String, IContentProposal> entry : proposals.entrySet()) {
            final String text = entry.getKey();
            if (text.length() >= contents.length() && text.substring(0, contents.length()).equalsIgnoreCase(contents)) {
                result.add(entry.getValue());
            }
        }
        return result.toArray(new IContentProposal[result.size()]);
    }

}
