package org.bonitasoft.studio.importer.ui.wizard;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class BpmnSourceSelectionDialog extends Dialog {

    private static final String SELECT_SOURCE_BPMN_VENDOR = "Select source BPMN Vendor:";
	private static final String EXPORTER_VERSION = "Exporter Version:";
	private static final String[] vendors = { "Camunda", "Activiti", "Flowable", "Other" };
    private String selectedVendor;
    private Combo vendorCombo;
    private Text otherVendorText;
    private ImportFileData importFileData;
	private Text exporterVersionText;

    public BpmnSourceSelectionDialog(Shell parentShell, ImportFileWizard importFileWizard) {
    	this(parentShell);
    	this.importFileData = importFileWizard.getImportFileData();
    }
    
    protected BpmnSourceSelectionDialog(Shell parentShell) {
		super(parentShell);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        final DataBindingContext dbc = new DataBindingContext();
        Composite container = (Composite) super.createDialogArea(parent);
        container.setLayout(new GridLayout(2, false));

        Label comboLabel = new Label(container, SWT.NONE);
        comboLabel.setText(SELECT_SOURCE_BPMN_VENDOR);

        vendorCombo = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
        vendorCombo.setItems(vendors);
        vendorCombo.select(0);
        selectedVendor = vendors[0];
        vendorCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        Label otherLabel = new Label(container, SWT.NONE);
        otherLabel.setText("Other source Vendor:");
        otherLabel.setVisible(false);

        otherVendorText = new Text(container, SWT.BORDER);
        otherVendorText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        otherVendorText.setVisible(false);

        Label exporterLabel = new Label(container, SWT.NONE);
        exporterLabel.setText(EXPORTER_VERSION);

        exporterVersionText = new Text(container, SWT.BORDER); 
        exporterVersionText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        IObservableValue exporterVersionObservable = PojoProperties.value(ImportFileData.BPMN_SOURCE_VERSION_FIELD).observe(importFileData);
        dbc.bindValue(WidgetProperties.text(SWT.Modify).observe(exporterVersionText), exporterVersionObservable);

        
        vendorCombo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String selected = vendorCombo.getText();
                final IObservableValue filePathObservable = PojoProperties.value(ImportFileData.BPMN_SOURCE_FIELD).observe(importFileData);
                if ("Other".equals(selected)) {
                    otherLabel.setVisible(true);
                    otherVendorText.setVisible(true);
                    dbc.bindValue(WidgetProperties.text(SWT.Modify).observe(otherVendorText), filePathObservable);
                } else {
                    otherLabel.setVisible(false);
                    otherVendorText.setVisible(false);
                    selectedVendor = selected;
                    dbc.bindValue(WidgetProperties.text(SWT.Modify).observe(vendorCombo), filePathObservable);
                }
                container.layout();
            }
        });

        otherVendorText.addModifyListener(new ModifyListener() {
            @Override
            public void modifyText(ModifyEvent e) {
                selectedVendor = otherVendorText.getText().trim();
            }
        });

        return container;
    }


    @Override
    protected void okPressed() {
    	importFileData.setBpmnSource(otherVendorText.getText() != null ? otherVendorText.getText() : vendorCombo.getText());
    	importFileData.setBpmnSourceVersion(exporterVersionText.getText());
        super.okPressed();
    }

    public String getSelectedVendor() {
        return selectedVendor;
    }
}
