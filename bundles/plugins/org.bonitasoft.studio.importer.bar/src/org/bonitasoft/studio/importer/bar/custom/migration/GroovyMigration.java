package org.bonitasoft.studio.importer.bar.custom.migration;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.importer.bar.i18n.Messages;
import org.bonitasoft.studio.migration.migrator.ReportCustomMigration;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

public class GroovyMigration extends ReportCustomMigration {
	
	private final String FOR_NAME_STRING = "Class.forName(";
	
	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {
		for (Instance expression : model.getAllInstances("expression.Expression")){
			String expressionType = expression.get("type");
			if (ExpressionConstants.SCRIPT_TYPE.equals(expressionType)){
				if (containsForNameString((String)expression.get("content"))){
					addReportChange((String)expression.get("name"), 
							expression.getType().getEClass().getName(), 
							expression.getContainer().getUuid(), 
							Messages.groovyForNameWarningDescription, 
							Messages.groovyForNameWarningPropertyName, 
							IStatus.WARNING);
				}
			}
		}
		
	}
	
	
	private boolean containsForNameString(String script){
		return script.contains(FOR_NAME_STRING);
	}

}
