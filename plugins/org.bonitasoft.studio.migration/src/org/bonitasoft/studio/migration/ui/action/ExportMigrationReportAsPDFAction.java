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
package org.bonitasoft.studio.migration.ui.action;

import java.io.File;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.migration.MigrationPlugin;
import org.bonitasoft.studio.migration.i18n.Messages;
import org.bonitasoft.studio.migration.model.report.Report;
import org.bonitasoft.studio.migration.utils.PDFMigrationReportWriter;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;

/**
 * @author Romain Bioteau
 *
 */
public class ExportMigrationReportAsPDFAction extends Action {

	private Report report;
	private TableViewer viewer;

	public ExportMigrationReportAsPDFAction(){
		super();
		setText(Messages.exportAsPdf);
		setToolTipText(Messages.exportAsPdf);
		setImageDescriptor(Pics.getImageDescriptor("pdf.png",MigrationPlugin.getDefault()));
		setHoverImageDescriptor(Pics.getImageDescriptor("pdf.png",MigrationPlugin.getDefault()));
	}
	
	@Override
	public void run() {
		if(report != null){
			final PDFMigrationReportWriter writer = new PDFMigrationReportWriter(report,viewer);
			final FileDialog dialog = new FileDialog(Display.getDefault().getActiveShell(), SWT.SAVE);
			dialog.setFileName(report.getName()+".pdf");
			dialog.setFilterExtensions(new String[]{"*.pdf"});
			final String filePath = dialog.open();
			if(filePath != null){
				final File targetFile = new File(filePath);
				if(targetFile.exists()){
					if(FileActionDialog.overwriteQuestion(targetFile.getName())){
						targetFile.delete();
					}else{
						return;
					}
				}
				writer.execute(filePath);
			}
		}
	}
	
	public void setReport(Report report) {
		this.report = report ;
	}
	
	public void setViewer(TableViewer viewer) {
		this.viewer = viewer ;
	}
}
