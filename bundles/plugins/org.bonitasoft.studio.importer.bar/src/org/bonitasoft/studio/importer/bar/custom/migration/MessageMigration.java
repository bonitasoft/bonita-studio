/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.importer.bar.custom.migration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.importer.bar.i18n.Messages;
import org.bonitasoft.studio.migration.migrator.ReportCustomMigration;
import org.bonitasoft.studio.migration.utils.StringToExpressionConverter;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author Romain Bioteau
 *
 */
public class MessageMigration extends ReportCustomMigration {

    private final Map<String, String> targetProcessNameConditions = new HashMap<String, String>();
    private final Map<String, String> targetElementNameConditions = new HashMap<String, String>();
    private final Map<String, List<Instance>> messageContents = new HashMap<String, List<Instance>>();

    @Override
    public void migrateBefore(final Model model, final Metamodel metamodel)
            throws MigrationException {
        for(final Instance message : model.getAllInstances("process.Message")){
            setTargetProcessName(message, model);
            setTargetElementName(message, model);
            final List<Instance> data = message.get("data");
            final List<Instance> content = new ArrayList<Instance>();
            for(final Instance d : data){
                final Instance expInstance = (Instance) d.get("defaultValue");

                final Instance expression = expInstance.copy();

                List<Instance> dependencies = expression.get("referencedElements");
                for (final Instance dep : dependencies) {
                    model.delete(dep);
                }
                dependencies = expInstance.get("referencedElements");
                for (final Instance dep : dependencies) {
                    expression.add("referencedElements", dep.copy());
                }

                expression.set("returnTypeFixed", false);
                content.add(expression);
                model.delete(d);
            }
            if(!content.isEmpty()){
                messageContents.put(message.getUuid(), content);

            }
        }
    }

    private void setTargetElementName(final Instance message, final Model model) {

        final List<String> elementNameList = new ArrayList<String>();
        for (final Instance elem : model.getAllInstances("process.CatchMessageEvent")) {
            elementNameList.add((String) elem.get("name"));
        }

        String targetElement = message.get("targetElementName");
        message.set("targetElementName", null);

        for (final String elemName : elementNameList) {
            if (elemName.replaceAll(" ", "_").equals(targetElement)) {
                targetElement = elemName;
            }
        }

        if(targetElement != null && !targetElement.trim().isEmpty()){
            targetElementNameConditions.put(message.getUuid(), targetElement);
        }
    }

    private void setTargetProcessName(final Instance message, final Model model) {
        final List<String> poolNameList = new ArrayList<String>();
        for (final Instance pool : model.getAllInstances("process.Pool")) {
            poolNameList.add((String) pool.get("name"));
        }

        String targetProcess = message.get("targetProcessName");
        message.set("targetProcessName", null);

        for (final String procName : poolNameList) {
            if (procName.replaceAll(" ", "_").equals(targetProcess)) {
                targetProcess = procName;
            }
        }

        if (targetProcess != null && !targetProcess.trim().isEmpty()) {
            targetProcessNameConditions.put(message.getUuid(), targetProcess);
        }
    }


    @Override
    public void migrateAfter(final Model model, final Metamodel metamodel)
            throws MigrationException {
        for(final Instance message : model.getAllInstances("process.Message")){
            setMessageContent(message, model);
            setTargetProcessExpression(message, model);
            setTargetElementExpression(message, model);
        }
    }

    private void setMessageContent(final Instance message,final Model model) {
        final Instance tableExpression = model.newInstance("expression.TableExpression");
        if (messageContents.containsKey(message.getUuid())) {
            final List<Instance> content = messageContents.get(message.getUuid());
            for(final Instance expression : content){
                final Instance rowExpression = model.newInstance("expression.ListExpression");
                final Instance keyExpression = StringToExpressionConverter.createExpressionInstance(model, expression.get("name") + "Key",
                        expression.get("name") + "Key", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
                rowExpression.add("expressions", keyExpression);
                rowExpression.add("expressions", expression);
                tableExpression.add("expressions", rowExpression);
            }
            message.set("messageContent", tableExpression);
            addReportChange((String) message.get("name"), message.getType().getEClass().getName(), message.getUuid(),
                    Messages.messageContentMigrationDescription, Messages.messagesProperty, IStatus.WARNING);
        } else {
            message.set("messageContent", tableExpression);
        }
    }

    private void setTargetProcessExpression(final Instance message, final Model model) {
        Instance expression = null;
        if (targetProcessNameConditions.containsKey(message.getUuid())) {
            final StringToExpressionConverter converter = getConverter(model, getScope(message));
            final String script = targetProcessNameConditions.get(message.getUuid());
            expression = converter.parse(script, String.class.getName(), true);
            if (ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))) {
                expression.set("name", "targetProcessScript");
            }
            addReportChange((String) message.get("name"), message.getType().getEClass().getName(), message.getUuid(),
                    Messages.targetProcessNameMigrationDescription, Messages.messagesProperty, ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))
                    ? IStatus.WARNING : IStatus.OK);
        } else {
            expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
        }
        message.set("targetProcessExpression", expression);
    }



    private void setTargetElementExpression(final Instance message, final Model model) {
        Instance expression = null;
        if (targetElementNameConditions.containsKey(message.getUuid())) {
            final StringToExpressionConverter converter = getConverter(model, getScope(message));
            final String url = targetElementNameConditions.get(message.getUuid());
            expression = converter.parse(url, String.class.getName(), true);
            if (ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))) {
                expression.set("name", "targetElementScriptScript");
            }
            addReportChange((String) message.get("name"), message.getType().getEClass().getName(), message.getUuid(),
                    Messages.targetElementNameMigrationDescription, Messages.messagesProperty, ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))
                    ? IStatus.WARNING : IStatus.OK);
        } else {
            expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
        }
        message.set("targetElementExpression", expression);
    }
}
