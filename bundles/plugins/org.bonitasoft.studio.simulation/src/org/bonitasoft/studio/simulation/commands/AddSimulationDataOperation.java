/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.simulation.commands;

import java.util.List;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.simulation.SimulationBoolean;
import org.bonitasoft.studio.model.simulation.SimulationData;
import org.bonitasoft.studio.model.simulation.SimulationDataContainer;
import org.bonitasoft.studio.model.simulation.SimulationFactory;
import org.bonitasoft.studio.model.simulation.SimulationLiteral;
import org.bonitasoft.studio.model.simulation.SimulationLiteralData;
import org.bonitasoft.studio.model.simulation.SimulationNumberData;
import org.bonitasoft.studio.model.simulation.SimulationNumberRange;
import org.bonitasoft.studio.model.simulation.SimulationPackage;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

/**
 * @author Baptiste Mesta
 *
 */
public class AddSimulationDataOperation extends AbstractTransactionalCommand {


    private final SimulationDataContainer element;
    private final Expression dataExpression;
    private List<SimulationLiteral> literals;
    private final String dataDescription;
    private final String dataName;
    private List<SimulationNumberRange> ranges;
    private double probabilityOfTrue;
    private EClass dataClass;
    private SimulationData data;
    private final boolean expressionBased;

    /**
     * @param editingDomain
     * @param element
     * @param dataExpression
     * @param dataExpression2
     * @param dataDescription
     */
    public AddSimulationDataOperation(TransactionalEditingDomain editingDomain, SimulationDataContainer element, String dataName, String dataDescription, Expression dataExpression, boolean expressionBased) {
        super(editingDomain, "edit data operation",getWorkspaceFiles(element));
        this.element = element;
        this.dataName = dataName;
        this.dataExpression = dataExpression;
        this.dataDescription = dataDescription;
        this.expressionBased = expressionBased;

    }

    /**
     * @param editingDomain
     * @param element
     * @param dataName
     * @param dataDescription
     * @param probabilityOfTrue
     * @param dataExpression
     */
    public AddSimulationDataOperation(TransactionalEditingDomain editingDomain, SimulationDataContainer element, String dataName, String dataDescription,
            double probabilityOfTrue, Expression dataExpression, boolean expressionBased) {
        this(editingDomain, element, dataName, dataDescription, dataExpression, expressionBased);
        this.probabilityOfTrue = probabilityOfTrue;
        dataClass = SimulationPackage.eINSTANCE.getSimulationBoolean();
    }

    /**
     * @param editingDomain
     * @param element
     * @param dataName
     * @param dataDescription
     * @param ranges
     * @param dataExpression
     */
    public AddSimulationDataOperation(TransactionalEditingDomain editingDomain, SimulationDataContainer element, String dataName, String dataDescription,
            List<SimulationNumberRange> ranges, Expression dataExpression, boolean expressionBased) {
        this(editingDomain, element, dataName, dataDescription, dataExpression, expressionBased);
        this.ranges = ranges;
        dataClass = SimulationPackage.eINSTANCE.getSimulationNumberData();
    }

    /**
     * @param editingDomain
     * @param element
     * @param dataName
     * @param dataDescription
     * @param literals
     * @param dataExpression
     */
    public AddSimulationDataOperation(TransactionalEditingDomain editingDomain, SimulationDataContainer element, String dataName, String dataDescription,
            Expression dataExpression,List<SimulationLiteral> literals, boolean expressionBased) {
        this(editingDomain,element,dataName,dataDescription,dataExpression, expressionBased);
        this.literals = literals;
        dataClass = SimulationPackage.eINSTANCE.getSimulationLiteralData();
    }



    /**
     * @return the dataClass
     */
    public SimulationData getDataClass() {
        return data;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand#doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
     */
    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {

        if(dataClass.equals(SimulationPackage.eINSTANCE.getSimulationLiteralData())){
            data = SimulationFactory.eINSTANCE.createSimulationLiteralData();
            ((SimulationLiteralData) data).getLiterals().addAll(literals);
        } else if (dataClass.equals(SimulationPackage.eINSTANCE.getSimulationBoolean())){
            data = SimulationFactory.eINSTANCE.createSimulationBoolean();
            ((SimulationBoolean) data).setProbabilityOfTrue(probabilityOfTrue);
        } else if (dataClass.equals(SimulationPackage.eINSTANCE.getSimulationNumberData())){
            data = SimulationFactory.eINSTANCE.createSimulationNumberData();
            ((SimulationNumberData) data).getRanges().addAll(ranges);
        }
        data.setDescription(dataDescription);
        data.setExpression(dataExpression);
        data.setName(dataName);
        element.getSimulationData().add(data);
        data.setExpressionBased(expressionBased);
        return CommandResult.newOKCommandResult(data);
    }

}
