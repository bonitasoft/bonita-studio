/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.diagram.custom.refactoring;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.gmf.tools.GMFTools;
import org.bonitasoft.studio.diagram.custom.clipboard.CustomPasteCommand;
import org.bonitasoft.studio.diagram.custom.clipboard.CustomPasteCommand.LocationStrategy;
import org.bonitasoft.studio.expression.editor.ExpressionProviderService;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.FormMappingType;
import org.bonitasoft.studio.model.process.InputMapping;
import org.bonitasoft.studio.model.process.InputMappingAssignationType;
import org.bonitasoft.studio.model.process.OutputMapping;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MainProcessEditPart;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest.ViewAndElementDescriptor;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Node;

/**
 * @author Mickael Istria
 */
public class MoveItemsAndCopyDataCommand extends AbstractTransactionalCommand implements IUndoableOperation {

    private final CreateViewAndElementRequest req;
    private final List<IGraphicalEditPart> parts;
    private final ViewAndElementDescriptor subprocessDescriptor;

    /**
     * @param req
     * @param subprocessDescriptor
     * @param parts
     */
    public MoveItemsAndCopyDataCommand(final TransactionalEditingDomain domain, final CreateViewAndElementRequest req,
            final ViewAndElementDescriptor subprocessDescriptor, final List<IGraphicalEditPart> parts) {
        super(domain, "Move items", getWorkspaceFiles(parts.get(0).resolveSemanticElement()));
        this.req = req;
        this.parts = parts;
        this.subprocessDescriptor = subprocessDescriptor;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.gmf.runtime.common.core.command.AbstractCommand#doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor,
     * org.eclipse.core.runtime.IAdaptable)
     */
    @Override
    protected CommandResult doExecuteWithResult(final IProgressMonitor progressMonitor, final IAdaptable info)
            throws ExecutionException {
        IGraphicalEditPart rootPart = GMFTools.getFirstNodeShapeEditPart(parts);
        while (!(rootPart instanceof MainProcessEditPart)) {
            rootPart = (IGraphicalEditPart) rootPart.getParent();
        }
        final Node targetNode = (Node) req.getViewAndElementDescriptor().getAdapter(Node.class);
        final Pool targetProcess = (Pool) targetNode.getElement();
        final IGraphicalEditPart targetPart = (IGraphicalEditPart) rootPart.findEditPart(rootPart, targetProcess);

        final CustomPasteCommand pasteCommand = new CustomPasteCommand("paste", targetPart);
        pasteCommand.setLocationStrategy(LocationStrategy.COPY);
        pasteCommand.performPaste(progressMonitor, GMFTools.addMissingConnectionsAndBoundaries(parts));
        final AbstractProcess sourceProcess = ModelHelper.getParentProcess(parts.get(0).resolveSemanticElement());
        final CallActivity subprocessStep = (CallActivity) subprocessDescriptor.getElementAdapter()
                .getAdapter(EObject.class);
        BusinessDataMapper businessDataMapper = new BusinessDataMapper(ExpressionProviderService.getInstance().getExpressionProvider(ExpressionConstants.QUERY_TYPE), 
                ExpressionProviderService.getInstance() .getExpressionProvider(ExpressionConstants.DAO_TYPE));
        for (final Data sourceData : sourceProcess.getData()) {
            if (sourceData instanceof BusinessObjectData) {
                businessDataMapper.map((BusinessObjectData) sourceData, targetProcess, subprocessStep);
            } else {
                mapProcessDataWithCallActivity(targetProcess, subprocessStep, sourceData);
            }
        }
        targetProcess.getFormMapping().setType(FormMappingType.NONE);
        return CommandResult.newOKCommandResult();
    }

    private void mapProcessDataWithCallActivity(final Pool targetProcess, final CallActivity subprocessStep,
            final Data sourceData) {
        final Data targetData = EcoreUtil.copy(sourceData);
        targetProcess.getData().add(targetData);
        final InputMapping inputMapping = ProcessFactory.eINSTANCE.createInputMapping();
        inputMapping.setProcessSource(ExpressionHelper.createVariableExpression(sourceData));
        inputMapping.setSubprocessTarget(sourceData.getName());
        subprocessStep.getInputMappings().add(inputMapping);
        final OutputMapping outputMapping = ProcessFactory.eINSTANCE.createOutputMapping();
        outputMapping.setProcessTarget(sourceData);
        outputMapping.setSubprocessSource(sourceData.getName());
    }
}
