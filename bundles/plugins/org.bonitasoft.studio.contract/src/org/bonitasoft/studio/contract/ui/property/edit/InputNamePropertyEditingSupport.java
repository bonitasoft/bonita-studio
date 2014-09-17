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

import java.util.Date;

import org.bonitasoft.studio.common.DataUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputMapping;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.Data;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalListener;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyEditingSupport;

/**
 * @author Romain Bioteau
 *
 */
public class InputNamePropertyEditingSupport extends PropertyEditingSupport {


    public InputNamePropertyEditingSupport(final AdapterFactoryContentProvider propertySourceProvider, final TableViewer viewer) {
        super(viewer, propertySourceProvider, "name");
    }

    @Override
    protected void setValue(final Object element, final Object value) {
        super.setValue(element, value);
        getViewer().update(element, null);
    }

    @Override
    protected CellEditor getCellEditor(final Object element) {
        if (element instanceof ContractInput) {
            final TextCellEditor cellEditor = (TextCellEditor) super.getCellEditor(element);
            final Text textControl = (Text) cellEditor.getControl();
            textControl.setToolTipText(Messages.automaticMappingTooltip);
            KeyStroke keyStroke = null;
            try {
                keyStroke = KeyStroke.getInstance("Ctrl+Space");
            } catch (final ParseException e) {
                BonitaStudioLog.error(e);
            }
            final ContentProposalAdapter contentProposalAdapter = new ContentProposalAdapter(textControl, new TextContentAdapter(),
                    new InputMappingProposalProvider((ContractInput) element), keyStroke,
                    null);
            contentProposalAdapter.setLabelProvider(new AdapterFactoryLabelProvider(((AdapterFactoryContentProvider) propertySourceProvider)
                    .getAdapterFactory()) {

                @Override
                public String getText(final Object object) {
                    if (object instanceof InputMappingProposal) {
                        return ((InputMappingProposal) object).getLabel();
                    }
                    return super.getText(object);
                }

                @Override
                public Image getImage(final Object object) {
                    if (object instanceof InputMappingProposal) {
                        return super.getImage(((InputMappingProposal) object).getData());
                    }
                    return super.getImage(object);
                }
            });
            contentProposalAdapter.addContentProposalListener(new IContentProposalListener() {

                @Override
                public void proposalAccepted(final IContentProposal acceptedProposal) {
                    if (acceptedProposal instanceof InputMappingProposal) {
                        final IPropertySource inputPropertySource = propertySourceProvider.getPropertySource(element);
                        final String name = ((InputMappingProposal) acceptedProposal).getContent();
                        setValue(element, name);
                        textControl.setText(name);
                        inputPropertySource.setPropertyValue("type", getType((InputMappingProposal) acceptedProposal));
                        inputPropertySource.setPropertyValue("multiple", ((InputMappingProposal) acceptedProposal).getData().isMultiple());
                        final ContractInputMapping mapping = ((ContractInput) element).getMapping();
                        Assert.isNotNull(mapping);
                        final IPropertySource mappingPropertySource = propertySourceProvider.getPropertySource(mapping);
                        mappingPropertySource.setPropertyValue("data", ((InputMappingProposal) acceptedProposal).getData());
                        mappingPropertySource.setPropertyValue("setterName", ((InputMappingProposal) acceptedProposal).getSetterName());
                        mappingPropertySource.setPropertyValue("setterParamType", ((InputMappingProposal) acceptedProposal).getSetterParamType());
                        getViewer().update(element, null);
                    }
                }
            });
            return cellEditor;
        }
        return null;
    }

    protected ContractInputType getType(final InputMappingProposal acceptedProposal) {
        String javaType = null;
        if (acceptedProposal.getSetterParamType() != null) {
            javaType = acceptedProposal.getSetterParamType();
        } else {

            Data data = acceptedProposal.getData();
            if (data.isMultiple()) {
                data = EcoreUtil.copy(data);
                data.setMultiple(false);
            }
            javaType = DataUtil.getTechnicalTypeFor(data);
        }

        if (javaType.equals(String.class.getName())) {
            return ContractInputType.TEXT;
        } else if (javaType.equals(Integer.class.getName())
                || javaType.equals(Long.class.getName())) {
            return ContractInputType.INTEGER;
        } else if (javaType.equals(Double.class.getName())
                || javaType.equals(Float.class.getName())) {
            return ContractInputType.DECIMAL;
        } else if (javaType.equals(Boolean.class.getName())) {
            return ContractInputType.BOOLEAN;
        } else if (javaType.equals(Date.class.getName())) {
            return ContractInputType.DATE;
        } else {
            return ContractInputType.COMPLEX;
        }
    }

}
