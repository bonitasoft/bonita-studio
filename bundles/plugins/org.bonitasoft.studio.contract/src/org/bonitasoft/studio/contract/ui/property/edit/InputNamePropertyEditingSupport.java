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
import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.common.DataUtil;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.contract.core.ContractDefinitionValidator;
import org.bonitasoft.studio.contract.ui.property.FieldDecoratorProvider;
import org.bonitasoft.studio.contract.ui.property.edit.proposal.InputMappingProposal;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.PropertyEditingSupport;

/**
 * @author Romain Bioteau
 *
 */
public class InputNamePropertyEditingSupport extends PropertyEditingSupport implements ICellEditorValidator, ICellEditorListener {

    private ContractInput currentElement;
    private final AdapterFactoryLabelProvider adapterFactoryLabelProvider;
    private final ContractDefinitionValidator contractDefinitionValidator;
    private boolean validate = false;
    private Object currentValue;
    private final FieldDecoratorProvider decoratorProvider;
    private static final Map<String, ContractInputType> JAVA_TO_INPUT_TYPE_MAP;
    static {
        JAVA_TO_INPUT_TYPE_MAP = new HashMap<String, ContractInputType>();
        JAVA_TO_INPUT_TYPE_MAP.put(String.class.getName(), ContractInputType.TEXT);
        JAVA_TO_INPUT_TYPE_MAP.put(Long.class.getName(), ContractInputType.INTEGER);
        JAVA_TO_INPUT_TYPE_MAP.put(Integer.class.getName(), ContractInputType.INTEGER);
        JAVA_TO_INPUT_TYPE_MAP.put(Short.class.getName(), ContractInputType.INTEGER);
        JAVA_TO_INPUT_TYPE_MAP.put(Boolean.class.getName(), ContractInputType.BOOLEAN);
        JAVA_TO_INPUT_TYPE_MAP.put(Date.class.getName(), ContractInputType.DATE);
        JAVA_TO_INPUT_TYPE_MAP.put(Double.class.getName(), ContractInputType.DECIMAL);
        JAVA_TO_INPUT_TYPE_MAP.put(Float.class.getName(), ContractInputType.DECIMAL);
    }

    public InputNamePropertyEditingSupport(final AdapterFactoryContentProvider propertySourceProvider,
            final TableViewer viewer,
            final AdapterFactoryLabelProvider adapterFactoryLabelProvider,
            final ContractDefinitionValidator contractDefinitionValidator,
            final FieldDecoratorProvider decoratorProvider) {
        super(viewer, propertySourceProvider, ProcessPackage.Literals.CONTRACT_INPUT__NAME.getName());
        this.adapterFactoryLabelProvider = adapterFactoryLabelProvider;
        this.contractDefinitionValidator = contractDefinitionValidator;
        this.decoratorProvider = decoratorProvider;
    }

    @Override
    protected void setValue(final Object element, final Object value) {
        super.setValue(element, value);
        if (element instanceof ContractInput) {
            contractDefinitionValidator.validateDuplicatedInputs((Contract) ((ContractInput) element).eContainer());
        }
        getViewer().refresh(true);
    }

    @Override
    protected void initializeCellEditorValue(final CellEditor cellEditor, final ViewerCell cell) {
        super.initializeCellEditorValue(cellEditor, cell);
        validate = false;
        setCurrentElement(cell.getElement());
        final Text textControl = (Text) cellEditor.getControl();
        textControl.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SWTBotConstants.SWTBOT_ID_INPUT_NAME_TEXTEDITOR);
        //    attachContentAssist(cellEditor);
        //        decoratorProvider.createControlDecorator(cellEditor.getControl(),
        //                Messages.automaticMappingTooltip,
        //                FieldDecorationRegistry.DEC_CONTENT_PROPOSAL,
        //                SWT.TOP | SWT.LEFT);
        cellEditor.setValidator(this);
        cellEditor.addListener(this);
        //Clear cell label
        cell.setText("");
        cell.setImage(null);
    }

    //    protected void attachContentAssist(final CellEditor cellEditor) {
    //        final Text textControl = (Text) cellEditor.getControl();
    //        KeyStroke keyStroke = null;
    //        try {
    //            keyStroke = KeyStroke.getInstance("Ctrl+Space");
    //        } catch (final ParseException e) {
    //            BonitaStudioLog.error("Failed to retrieve Ctrl+Space key stroke", e, ContractPlugin.PLUGIN_ID);
    //        }
    //        final ContentProposalAdapter contentProposalAdapter = new ContentProposalAdapter(textControl, new TextContentAdapter(),
    //                new InputMappingProposalProvider(getCurrentElement()), keyStroke,
    //                null);
    //        contentProposalAdapter.setLabelProvider(new InputMappingProposalLabelProvider(adapterFactoryLabelProvider));
    //        contentProposalAdapter.addContentProposalListener(new IContentProposalListener() {
    //
    //            @Override
    //            public void proposalAccepted(final IContentProposal acceptedProposal) {
    //                if (acceptedProposal instanceof InputMappingProposal) {
    //                    updateContractInputWithProposal(getCurrentElement(), textControl, acceptedProposal);
    //                }
    //            }
    //        });
    //    }

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

        final ContractInputType inputType = JAVA_TO_INPUT_TYPE_MAP.get(javaType);
        if (inputType == null) {
            throw new IllegalStateException("Invalid input type: " + javaType);
        }
        return inputType;
    }

    @Override
    public String isValid(final Object value) {
        if (validate || getValue(currentElement) != null) {
            contractDefinitionValidator.validateInputName(currentElement, (String) value);
        }
        currentValue = value;
        return null;
    }

    @Override
    public void applyEditorValue() {
        validate = true;
        isValid(currentValue);
    }

    @Override
    public void cancelEditor() {
        //Nothing to do
    }

    @Override
    public void editorValueChanged(final boolean oldValidState, final boolean newValidState) {
        validate = oldValidState || newValidState;
    }

    //    protected void updateContractInputWithProposal(final Object element, final Text textControl, final IContentProposal acceptedProposal) {
    //        final IPropertySource inputPropertySource = propertySourceProvider.getPropertySource(element);
    //        final String name = ((InputMappingProposal) acceptedProposal).getInputContent();
    //        setValue(element, name);
    //        textControl.setText(name);
    //        inputPropertySource.setPropertyValue(ProcessPackage.Literals.CONTRACT_INPUT__TYPE.getName(), getType((InputMappingProposal) acceptedProposal));
    //        inputPropertySource.setPropertyValue(ProcessPackage.Literals.CONTRACT_INPUT__MULTIPLE.getName(), ((InputMappingProposal) acceptedProposal).getData()
    //                .isMultiple());
    //
    //        final ContractInputMapping mapping = ((ContractInput) element).getMapping();
    //        Assert.isNotNull(mapping);
    //        final IPropertySource mappingPropertySource = propertySourceProvider.getPropertySource(mapping);
    //        mappingPropertySource.setPropertyValue(ProcessPackage.Literals.CONTRACT_INPUT_MAPPING__DATA.getName(),
    //                ((InputMappingProposal) acceptedProposal).getData());
    //        mappingPropertySource.setPropertyValue(ProcessPackage.Literals.CONTRACT_INPUT_MAPPING__SETTER_NAME.getName(),
    //                ((InputMappingProposal) acceptedProposal).getSetterName());
    //        mappingPropertySource.setPropertyValue(ProcessPackage.Literals.CONTRACT_INPUT_MAPPING__SETTER_PARAM_TYPE.getName(),
    //                ((InputMappingProposal) acceptedProposal).getSetterParamType());
    //        getViewer().update(element, null);
    //    }

    public ContractInput getCurrentElement() {
        return currentElement;
    }

    public void setCurrentElement(final Object currentElement) {
        Assert.isLegal(currentElement instanceof ContractInput);
        this.currentElement = (ContractInput) currentElement;
        currentValue = ((ContractInput) currentElement).getName();
    }



}
