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

import java.util.HashMap;
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
public class FileAndImageWidgetMigration extends ReportCustomMigration {

	private final Map<String, String> fileDocumentNames = new HashMap<String,String>();
	private final Map<String, String> imgPaths = new HashMap<String,String>();
	private final Map<String, Boolean> isDocuments = new HashMap<String,Boolean>();
	private final Map<Instance,Instance> attachmentDatas=new HashMap<Instance,Instance>();

	@Override
	public void migrateBefore(final Model model, final Metamodel metamodel)
			throws MigrationException {
		for(final Instance widget : model.getAllInstances("form.FileWidget")){
			if(!widget.getContainer().instanceOf("expression.Expression")){
				storeFileData(widget);
			}
		}
		for(final Instance widget : model.getAllInstances("form.ImageWidget")){
			if(!widget.getContainer().instanceOf("expression.Expression")){
				storeImgPath(widget);
				storeIsDocument(widget);
			}
		}
		removeAttachmentData(model);
	}

	protected void removeAttachmentData(final Model model) {
		for (final Instance data : model.getAllInstances("process.AttachmentData")) {
			attachmentDatas.put(data.copy(),data.getContainer());
			model.delete(data);
		}
	}

	private void storeFileData(final Instance widget) {
		final Instance data = widget.get("fileData");
		if(data != null){
			final String dataName = data.get("name");
			widget.set("fileData", null);
			fileDocumentNames.put(widget.getUuid(), dataName);
		}
	}

	private void storeImgPath(final Instance widget) {
		final String imgPath = widget.get("imgPath");
		widget.set("imgPath", null);
		if(imgPath != null && !imgPath.trim().isEmpty()){
			imgPaths.put(widget.getUuid(), imgPath);
		}
	}

	private void storeIsDocument(final Instance widget) {
		final boolean isDocument = widget.get("isAnAttachment");
		isDocuments.put(widget.getUuid(), isDocument);
	}

	@Override
	public void migrateAfter(final Model model, final Metamodel metamodel)
			throws MigrationException {
		createDocuments(model);
		for(final Instance widget : model.getAllInstances("form.FileWidget")){
			if(!widget.getContainer().instanceOf("expression.Expression")){
				setFileDocument(widget,model,metamodel);
			}
		}
		for(final Instance widget : model.getAllInstances("form.ImageWidget")){
			if(!widget.getContainer().instanceOf("expression.Expression")){
				setIsADocument(widget);
				setImgPath(model, widget);
			}
		}
	}

	private void setIsADocument(final Instance widget) {
		widget.set("isADocument",isDocuments.get(widget.getUuid()));
	}

	private void setFileDocument(final Instance widget,final Model model,final Metamodel metamodel) {
		if(fileDocumentNames.containsKey(widget.getUuid())){
			final String name = fileDocumentNames.get(widget.getUuid());
			Instance documentToSet = null;
			for(final Instance document : model.getAllInstances("process.Document")){
				if(name.equals(document.get("name"))){
					documentToSet = document;
					break;
				}
			}
			if(documentToSet != null){
				widget.set("inputType", metamodel.getEEnumLiteral("form.FileWidgetInputType.Document"));
				widget.set("outputDocumentName", name);
			}
		}
	}



	private void setImgPath(final Model model, final Instance widget) {
		Instance expression = null;
		if(imgPaths.containsKey(widget.getUuid())){
			final String imgPath = imgPaths.get(widget.getUuid());
			if(widget.get("isADocument")){
				Instance documentToSet = null;
				for(final Instance document : model.getAllInstances("process.Document")){
					if(imgPath.equals(document.get("name"))){
						documentToSet = document;
						break;
					}
				}
                if (documentToSet != null) {
                    widget.set("document", documentToSet);
                }
				expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
			}else{
				expression = getConverter(model,getScope(widget)).parse(imgPath, String.class.getName(), true);
				if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
					expression.set("name", "initialValueScript");
				}
				addReportChange((String) widget.get("name"),widget.getEClass().getName(), widget.getUuid(), Messages.imgPathMigrationDescription, Messages.dataProperty, StringToExpressionConverter.getStatusForExpression(expression));
			}
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		widget.set("imgPath", expression);
	}

	private void createDocuments(final Model model){
		for (final Instance attachmentData:attachmentDatas.keySet()){
			final Instance process = attachmentDatas.get(attachmentData);
			if(process.instanceOf("process.Pool")){
				final Instance document = model.newInstance("process.Document");
				document.set("isInternal", true);
				final String name = attachmentData.get("name");
				document.set("name",name);
				String defaultValue = attachmentData.get("barPath");
				if(defaultValue != null && defaultValue.startsWith("attachments/")){
					defaultValue = defaultValue.substring("attachments/".length());
				}
				document.set("defaultValueIdOfDocumentStore", defaultValue);
				final String doc = attachmentData.get("documentation");
				document.set("documentation", doc);
                document.set("mimeType",
                        StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true));
                document.set("url",
                        StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true));

				process.add("documents", document);
				addReportChange(name,Messages.document, process.getUuid(),Messages.documentCreationDescription,Messages.documentProperty, IStatus.WARNING);
			}else{
				addReportChange((String) process.get("name"),process.getEClass().getName(), process.getUuid(),Messages.bind(Messages.attachementDataRemovedFromMessage,attachmentData.get("name")),Messages.messagesProperty, IStatus.ERROR);
			}
			model.delete(attachmentData);
		}
	}

}
