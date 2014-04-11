package org.bonitasoft.studio.properties.form.sections.grid.contributions;

import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.form.Column;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Group;
import org.bonitasoft.studio.model.form.Line;
import org.bonitasoft.studio.model.form.Widget;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public class LineAndColumnSectionContribution implements IExtensibleGridPropertySectionContribution {

	private TransactionalEditingDomain editingDomain;
	private Widget widget;
	private ModifyListener modifyListener = new ModifyListener() {

		public void modifyText(ModifyEvent e) {
			updateWidget();
		}
	};
	private Text textColumn;
	private Text textLine;

	public void createControl(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory, ExtensibleGridPropertySection extensibleGridPropertySection) {
		GridData gd = new GridData(SWT.BEGINNING,SWT.CENTER,false,false);
		gd.widthHint = 300;
		composite.setLayoutData(gd);
		composite.setLayout(new GridLayout(4, false));

		Label label = widgetFactory.createLabel(composite, Messages.AppearanceSection_ColumnWidth);
		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));

		textColumn = widgetFactory.createText(composite, "", SWT.SINGLE | SWT.BORDER); //$NON-NLS-1$
		textColumn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		textColumn.setToolTipText(Messages.AppearanceSection_ColumnWidth_tooltip);
		
		label = widgetFactory.createLabel(composite, Messages.AppearanceSection_LineHeight);
		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));

		textLine = widgetFactory.createText(composite, "", SWT.SINGLE | SWT.BORDER); //$NON-NLS-1$
		textLine.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		textLine.setToolTipText(Messages.AppearanceSection_LineHeight_tooltip);

	}

	public void dispose() {

	}

	public String getLabel() {
		return Messages.GridSize;
	}

	public boolean isRelevantFor(EObject eObject) {
		return eObject instanceof Widget;
	}

	public void refresh() {
		removeListeners();
		updateFields();
		addListeners();

	}

	private void addListeners() {
		if (textColumn.getListeners(SWT.Modify).length == 0) {
			textColumn.addModifyListener(modifyListener);
		}
		if (textLine.getListeners(SWT.Modify).length == 0) {
			textLine.addModifyListener(modifyListener);
		}
	}

	private void removeListeners() {
		textColumn.removeModifyListener(modifyListener);
		textLine.removeModifyListener(modifyListener);
	}

	private void updateWidget() {
		EList<Column> lColumns;
		EList<Line> lLines;
		EStructuralFeature columns;
		EStructuralFeature lines;
		
		if(widget.eContainer() instanceof Form){
			lColumns = ((Form) widget.eContainer()).getColumns();
			lLines = ((Form) widget.eContainer()).getLines();
			columns = FormPackage.Literals.FORM__COLUMNS;
			lines = FormPackage.Literals.FORM__LINES;
		}else{
			columns = FormPackage.Literals.GROUP__COLUMNS;
			lines = FormPackage.Literals.GROUP__LINES;
			lColumns = ((Group) widget.eContainer()).getColumns();
			lLines = ((Group) widget.eContainer()).getLines();
		}
		
		// updateColumns
		Column column = null;
		int columnNumber = -1;
		// set the column height
		boolean ok = false;
		if (widget.getWidgetLayoutInfo() != null) {
			columnNumber = widget.getWidgetLayoutInfo().getColumn();
			for (Column columnT :lColumns) {
				if (columnT.getNumber() == columnNumber) {
					ok = true;
					column = columnT;
				}

			}

		}

		if (textColumn.getText() != null && !textColumn.getText().isEmpty()) {
			if (!ok) {
				// create the column
				column = FormFactory.eINSTANCE.createColumn();
				editingDomain.getCommandStack().execute(new SetCommand(editingDomain, column, FormPackage.Literals.COLUMN__NUMBER, columnNumber));
				editingDomain.getCommandStack().execute(new AddCommand(editingDomain, widget.eContainer(), columns, column));
			}
			editingDomain.getCommandStack().execute(new SetCommand(editingDomain, column, FormPackage.Literals.COLUMN__WIDTH, textColumn.getText()));
		} else {
			// remove the line
			if (ok) {
				editingDomain.getCommandStack().execute(new RemoveCommand(editingDomain, widget.eContainer(), columns, column));
			}
		}

		// updateLinesLine
		Line line = null;
		int lineNumber = -1;
		// set the line height
		ok = false;
		if (widget.getWidgetLayoutInfo() != null) {
			lineNumber = widget.getWidgetLayoutInfo().getLine();
			for (Line lineT : lLines) {
				if (lineT.getNumber() == lineNumber) {
					ok = true;
					line = lineT;
				}

			}

		}

		if (textLine.getText() != null && !textLine.getText().isEmpty()) {
			if (!ok) {
				// create the line
				line = FormFactory.eINSTANCE.createLine();
				// line.setNumber(value)
				editingDomain.getCommandStack().execute(new SetCommand(editingDomain, line, FormPackage.Literals.LINE__NUMBER, lineNumber));
				editingDomain.getCommandStack().execute(new AddCommand(editingDomain, widget.eContainer(), lines, line));
			}
			// set line height
			editingDomain.getCommandStack().execute(new SetCommand(editingDomain, line, FormPackage.Literals.LINE__HEIGHT, textLine.getText()));
		} else {
			// remove the line
			if (ok) {
				editingDomain.getCommandStack().execute(new RemoveCommand(editingDomain, widget.eContainer(), lines, line));
			}
		}

	}

	private void updateFields() {
		// column
		
		EList<Column> columns;
		EList<Line> lines;
		final EObject widgetContainer = widget.eContainer();
		if(widgetContainer instanceof Form){
			columns = ((Form) widgetContainer).getColumns();
			lines = ((Form) widgetContainer).getLines();
		}else{
			columns = ((Group) widgetContainer).getColumns();
			lines = ((Group) widgetContainer).getLines();
		}
//		textColumn.setText("0");
//		textLine.setText("0");
		if (widget.getWidgetLayoutInfo() != null) {

			for (Column c : columns) {
				 if(c.getNumber() == widget.getWidgetLayoutInfo().getColumn()){
					 textColumn.setText(c.getWidth());
					 break;
				 }
								
			}

			for (Line l : lines) {
				 if(l.getNumber() == widget.getWidgetLayoutInfo().getLine()){
					 textLine.setText(l.getHeight());
					 break;
				 }
								
			}
		}
	}

	public void setEObject(EObject object) {
		widget = (Widget) object;

	}

	public void setEditingDomain(TransactionalEditingDomain editingDomain) {
		this.editingDomain = editingDomain;
	}

	public void setSelection(ISelection selection) {
	}

}
