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
package org.bonitasoft.studio.contract.ui.property.edit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
public class InputMappingProposalProvider implements IContentProposalProvider {

    private final ContractInput contractInput;
    private final Map<String, IContentProposal> proposals = new HashMap<String, IContentProposal>();

    public InputMappingProposalProvider(final ContractInput contractInput) {
        this.contractInput = contractInput;
        initializeVariableScope();
    }

    private void initializeVariableScope() {
        final List<Data> accessibleData = ModelHelper.getAccessibleData(contractInput);
        for(final Data d : accessibleData){
            proposals.put(d.getName(), new InputMappingProposal(d));
            if (d instanceof JavaObjectData) {
                final String className = ((JavaObjectData) d).getClassName();
                final IType javaType = getType(className);
                final Object[] setters = new JavaSetterContentProvider().getElements(javaType);
                if (setters != null) {
                    for (final Object m : setters) {
                        if (m instanceof IMethod && ((IMethod) m).getElementName().startsWith("set")) {
                            final InputMappingProposal inputMappingProposal = new InputMappingProposal(d, ((IMethod) m).getElementName(), toJavaType((IMethod) m));
                            proposals.put(inputMappingProposal.getContent(), inputMappingProposal);
                        }
                    }
                }
            }

        }
    }

    @SuppressWarnings("restriction")
    private String toJavaType(final IMethod method) {
        String qualifiedType = Object.class.getName();
        try {
            qualifiedType = JavaModelUtil.getResolvedTypeName(Signature.getTypeErasure(method.getParameterTypes()[0]), method.getDeclaringType());
        } catch (final JavaModelException e) {
            BonitaStudioLog.error(e);
        } catch (final IllegalArgumentException e) {
            BonitaStudioLog.error(e);
        }
        if("int".equals(qualifiedType)){
            qualifiedType = Integer.class.getName();
        }else if("boolean".equals(qualifiedType)){
            qualifiedType = Boolean.class.getName();
        }else if("long".equals(qualifiedType)){
            qualifiedType = Long.class.getName();
        }else if("float".equals(qualifiedType)){
            qualifiedType = Float.class.getName();
        }else if("double".equals(qualifiedType)){
            qualifiedType = Double.class.getName();
        }else if("short".equals(qualifiedType)){
            qualifiedType = Short.class.getName();
        }else if("byte".equals(qualifiedType)){
            qualifiedType = Byte.class.getName();
        }else if("E".equals(qualifiedType)){
            qualifiedType = Object.class.getName();
        }else if("V".equals(qualifiedType)){
            qualifiedType = Object.class.getName();
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
        final List<IContentProposal> result = new ArrayList<IContentProposal>();
        for (final Entry<String, IContentProposal> entry : proposals.entrySet()) {
            final String text = entry.getKey();
            if (text != null && text.length() >= contents.length() && text.substring(0, contents.length()).equalsIgnoreCase(contents)) {
                result.add(entry.getValue());
            }
        }
        return result.toArray(new IContentProposal[result.size()]);
    }

}
