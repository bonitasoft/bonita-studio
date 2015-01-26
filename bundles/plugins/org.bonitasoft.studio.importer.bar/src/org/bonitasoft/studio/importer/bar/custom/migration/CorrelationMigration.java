/**
 * 
 */
package org.bonitasoft.studio.importer.bar.custom.migration;

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
 * @author aurelie zara
 *
 */
public class CorrelationMigration extends ReportCustomMigration {

	private Map<String, Instance> correlationTableAssociations = new HashMap<String,Instance>();
	private Map<String, Instance> correlationTableCatchMessage = new HashMap<String,Instance>();

	@Override
	public void migrateBefore(Model model, Metamodel metamodel)throws MigrationException{
		for (Instance correlationInstance : model.getAllInstances("process.Correlation")){
			final List<Instance> correlationAssociations = correlationInstance.get("correlationAssociation");
			final Instance tableExpression = model.newInstance("expression.TableExpression");
			for(Instance association : correlationAssociations){
				final String correlationKey = association.get("correlationKey");
				final String correlationExpression = association.get("correlationExpression");
				if(correlationKey != null || correlationExpression != null){
					final Instance rowInstance = model.newInstance("expression.ListExpression");
					final StringToExpressionConverter converter = getConverter(model,getScope(correlationInstance));
					Instance keyExpression = null ; 
					if(correlationKey != null && !correlationKey.isEmpty()){
						keyExpression = converter.parse(correlationKey, String.class.getName(), true);
					}else{
						keyExpression =	StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE,true);
					}
					Instance valueExpression = null ; 
					if(correlationExpression != null && !correlationExpression.isEmpty()){
						valueExpression = converter.parse(correlationExpression, String.class.getName(), false);
						if(ExpressionConstants.SCRIPT_TYPE.equals(valueExpression.get("type"))){
							valueExpression.set("name", "correlationValueScript");
						}else if(ExpressionConstants.VARIABLE_TYPE.equals(valueExpression.get("type"))){
							valueExpression.set("returnType", StringToExpressionConverter.getDataReturnType(((List<Instance>)valueExpression.get("referencedElements")).get(0)));
						}
					}else{
						valueExpression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE,false);
					}
					rowInstance.add("expressions", keyExpression);
					rowInstance.add("expressions", valueExpression);
					
					tableExpression.add("expressions", rowInstance);
				}
				model.delete(association);
			}
			if(correlationInstance.getContainer().instanceOf("process.AbstractCatchMessageEvent")){
				correlationTableCatchMessage.put(correlationInstance.getContainer().getUuid(),tableExpression);
				addReportChange((String) correlationInstance.getContainer().get("name"), correlationInstance.getContainer().getType().getEClass().getName(), correlationInstance.getContainer().getUuid(), Messages.correlationMigrationDescription, Messages.correlationProperty, IStatus.WARNING);
				model.delete(correlationInstance);
			}else{
				addReportChange((String) correlationInstance.getContainer().get("name"), correlationInstance.getContainer().getType().getEClass().getName(),  correlationInstance.getContainer().getUuid(), Messages.correlationMigrationDescription, Messages.messagesProperty, IStatus.WARNING);
				correlationTableAssociations.put(correlationInstance.getUuid(),tableExpression);
			}
		}
	}
	
	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {
		for (Instance correlationInstance : model.getAllInstances("process.Correlation")){
			correlationInstance.set("correlationAssociation", correlationTableAssociations.get(correlationInstance.getUuid()));
		}
		for (Instance correlationInstance : model.getAllInstances("process.AbstractCatchMessageEvent")){
			if(correlationTableCatchMessage.containsKey(correlationInstance.getUuid())){
				correlationInstance.set("correlation", correlationTableCatchMessage.get(correlationInstance.getUuid()));
			}
		}
	}

}
