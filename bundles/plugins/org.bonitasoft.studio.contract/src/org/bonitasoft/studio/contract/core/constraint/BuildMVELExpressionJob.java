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
package org.bonitasoft.studio.contract.core.constraint;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.contract.ContractPlugin;
import org.bonitasoft.studio.model.process.ContractInput;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.mvel2.CompileException;
import org.mvel2.ErrorDetail;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;


/**
 * @author Romain Bioteau
 *
 */
public class BuildMVELExpressionJob extends Job {

    private String expression;
    private List<ContractInput> inputs = new ArrayList<ContractInput>();

    public BuildMVELExpressionJob(final List<ContractInput> inputs) {
        super("Build MVEL expression");
        setPriority(Job.BUILD);
        setSystem(true);
        setUser(false);
        this.inputs = inputs;
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    protected IStatus run(final IProgressMonitor monitor) {
        monitor.beginTask("Building MVEL expression...", IProgressMonitor.UNKNOWN);
        final ParserContext parserContext = ParserContext.create();
        for (final ContractInput input : inputs) {
            parserContext.addInput(input.getName(), getClassForType(input));
        }

        final MultiStatus status = new MultiStatus(ContractPlugin.PLUGIN_ID, IStatus.OK, "", null);
        try {
            MVEL.compileExpression(getExpression(), parserContext);
        } catch (final CompileException e) {
            status.add(createStatus(e));
            return status;
        } catch (final Throwable e) {
            return Status.OK_STATUS;
        }
        if (!parserContext.getErrorList().isEmpty()) {
            for (final ErrorDetail error : parserContext.getErrorList()) {
                status.add(createStatus(error));
            }
            return status;
        }
        return Status.OK_STATUS;
    }

    private IStatus createStatus(final CompileException e) {
        String message = "";
        try{
            message = e.getMessage();
        } catch (final RuntimeException ex) {

        }
        int cursor = e.getCursor();
        if (cursor < 0) {
            cursor = 0;
        }
        return new Status(IStatus.OK, ContractPlugin.PLUGIN_ID, cursor, message, e);

    }

    public Class<?> getClassForType(final ContractInput input) {
        try {
            return BuildMVELExpressionJob.class.getClassLoader().loadClass(ExpressionHelper.getContractInputReturnType(input));
        } catch (final ClassNotFoundException e) {
            return null;
        }
    }

    private IStatus createStatus(final ErrorDetail error) {
        return new Status(IStatus.OK, ContractPlugin.PLUGIN_ID, error.getCursor(), error.getMessage(), null);
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(final String expression) {
        this.expression = expression;
    }

}
