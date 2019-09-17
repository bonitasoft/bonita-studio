/**
 * Copyright (C) 2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.diagram.custom.preferences;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.diagram.custom.i18n.Messages;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditorPlugin;
import org.bonitasoft.studio.preferences.extension.IPreferenceFieldEditorContribution;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 *
 */
public class ShowDiagramGridField implements IPreferenceFieldEditorContribution {


private class DoubleFieldEditor extends StringFieldEditor {
		
		private double minValidValue = 00.009;
		private double maxValidValue = 99.999;
		
		public DoubleFieldEditor(String pref, String label, Composite parent ) {
			super(pref,label,parent);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.preference.StringFieldEditor#doCheckState()
		 */
		protected boolean doCheckState() {
			Text text = getTextControl();

			if (text == null)
				return false;
			
			try {
				NumberFormat numberFormatter = NumberFormat.getInstance();
				ParsePosition parsePosition = new ParsePosition(0);
				Number parsedNumber = numberFormatter.parse(text.getText(), parsePosition);
				
				if (parsedNumber == null) {
					showErrorMessage();
					return false;
				}
				
				Double pageHeight = forceDouble(parsedNumber);
				double number = pageHeight.doubleValue();
				number = convertToBase(number);
				if (number >= minValidValue && number <= maxValidValue 
						&& parsePosition.getIndex() == text.getText().length()) {
					clearErrorMessage();
					return true;
				} else {
					showErrorMessage();
					return true;
				}
			} catch (NumberFormatException e1) {
				showErrorMessage();
			}

			return false;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.preference.StringFieldEditor#doLoadDefault()
		 */
		protected void doLoadDefault() {
			Text text = getTextControl();
			if (text != null) {
				double value = getPreferenceStore().getDefaultDouble(getPreferenceName());
				NumberFormat numberFormatter = NumberFormat.getNumberInstance();
				text.setText(numberFormatter.format(value));
			}
			valueChanged();
		}
		
		/* (non-Javadoc)
		 * Method declared on FieldEditor.
		 */
		protected void doLoad() {
			Text text = getTextControl();			
			if (text != null) {
				double value = getPreferenceStore().getDouble(getPreferenceName());
				NumberFormat numberFormatter = NumberFormat.getNumberInstance();
				text.setText(numberFormatter.format(value));				
			}
		}		
		
		protected void doStore() {
			NumberFormat numberFormatter = NumberFormat.getInstance();				
			Double gridWidth;
			try {
				gridWidth = forceDouble(numberFormatter.parse(getTextControl().getText()));
				getPreferenceStore().setValue(getPreferenceName(), gridWidth.doubleValue());				
			} catch (ParseException e) {
				showErrorMessage();
			}
			
		}		
	}

	private int oldUnits = -1;

	private static final int INCHES = 0;
	private static final int CENTIMETERS = 1;
	private static final int PIXELS = 2;

	// Conversion from inch to centimeter
	private static final double INCH2CM = 2.54;
	
	private String GRID_GROUP_LABEL = Messages.GridRulerPreferencePage_gridGroup_label;
	private String SHOW_GRID_LABEL = Messages.GridRulerPreferencePage_showGrid_label;
	private String SNAP_TO_GRID_LABEL = Messages.GridRulerPreferencePage_snapToGrid_label;
	private String SNAP_TO_GEOMETRY_LABEL = Messages.GridRulerPreferencePage_snapToGeometry_label;
	private String GRID_SPACING_LABEL_INCHES = DiagramUIMessages.GridRulerPreferencePage_gridSpacing_label_inches;
    private String GRID_SPACING_LABEL_CM = Messages.GridRulerPreferencePage_gridSpacing_label_cm;
    private String GRID_SPACING_LABEL_PIXELS = DiagramUIMessages.GridRulerPreferencePage_gridSpacing_label_pixels;
    
    // Grid Field Editors
    private BooleanFieldEditor showGrid = null;
	private BooleanFieldEditor snapToGrid = null;
	private BooleanFieldEditor snapToGeometry = null;
	private DoubleFieldEditor gridSpacing = null;
    private Composite dblGroup = null;

	private ArrayList<FieldEditor> editors;

	private String convertUnits(int fromUnits, int toUnits ) {
		String valueStr = gridSpacing.getStringValue();
		if( fromUnits == toUnits ) {
			return valueStr;
		}
		
		//Double value = Double.valueOf( valueStr );
		NumberFormat numberFormatter = NumberFormat.getInstance();		
		Double value = new Double(0.125);
		try {
			value = forceDouble(numberFormatter.parse(valueStr));
		} catch (ParseException e) {
			// Use the default
		}
		double pixelValue = 0;
		
		Display display = Display.getDefault();

		switch( fromUnits ) {
			case INCHES:
				pixelValue = value.doubleValue() * display.getDPI().x;
				break;
			case CENTIMETERS:
				pixelValue = value.doubleValue() * display.getDPI().x / INCH2CM;
				break;
			case PIXELS:
				pixelValue = value.intValue();
		}
		
		double returnValue = 0;
		
		switch( toUnits ) {
			case INCHES:
				returnValue = pixelValue / display.getDPI().x;
				break;
			case CENTIMETERS:
				returnValue = pixelValue * INCH2CM / display.getDPI().x;
				break;
			case PIXELS:
				returnValue = pixelValue;
		}
		
		return numberFormatter.format(returnValue);		
	}

	
	/**
	 * 
	 * converts the current units used to a base unit value to be used (e.g. in validation)
	 * 
	 * @param number Units to be converted to the base unit
	 * @return
	 */
	private double convertToBase(double number) {
		
		double returnValue = 0;
		switch( getUnits() ) {
			case INCHES:
				returnValue = number;
				break;
			case CENTIMETERS:
				returnValue = number / INCH2CM;
				break;
			case PIXELS:
				returnValue = number / Display.getDefault().getDPI().x;
		}
		return returnValue;
	}

	private void updateUnits() {
		
		int units = getUnits();

		switch( units )
		{
			case INCHES:
                gridSpacing.setLabelText(GRID_SPACING_LABEL_INCHES);
				break;
				
			case CENTIMETERS:
                gridSpacing.setLabelText(GRID_SPACING_LABEL_CM);
				break;

			case PIXELS:
                gridSpacing.setLabelText(GRID_SPACING_LABEL_PIXELS);
				break;
		}

		gridSpacing.setStringValue( convertUnits( oldUnits, units ) );
		oldUnits = units;
        
        dblGroup.layout();
		
	}

	private int getUnits() {
		int units = -1 ;//rulerUnits.getComboControl().getSelectionIndex();
		
		// IF no selection has been made
		if( units == -1 ) {
			// Read the preference store
			units = getPreferenceStore().getInt(IPreferenceConstants.PREF_RULER_UNITS);
			oldUnits = units;
		}
		return units;
	}

	private IPreferenceStore getPreferenceStore() {
		return ProcessDiagramEditorPlugin.getInstance().getPreferenceStore();
	}

	
	private void addGridFields( Composite parent ) {
		
		// Create a Group to hold the grid fields
    	Group group = new Group(parent, SWT.NONE);
		group.setText(GRID_GROUP_LABEL);

		GridLayout gridLayout = new GridLayout(2, false);

		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalSpan = 2;

		showGrid = new BooleanFieldEditor(
			IPreferenceConstants.PREF_SHOW_GRID,
			SHOW_GRID_LABEL, group);
		showGrid.setPreferenceStore(getPreferenceStore()) ;
		editors.add(showGrid);

		snapToGrid = new BooleanFieldEditor(
			IPreferenceConstants.PREF_SNAP_TO_GRID,
			SNAP_TO_GRID_LABEL, group);
		snapToGrid.setPreferenceStore(getPreferenceStore()) ;
		editors.add(snapToGrid);
		
		snapToGeometry = new BooleanFieldEditor(
				IPreferenceConstants.PREF_SNAP_TO_GEOMETRY,
				SNAP_TO_GEOMETRY_LABEL, group);
		snapToGeometry.setPreferenceStore(getPreferenceStore()) ;
		editors.add(snapToGeometry);			
		
		addGridSpacing( group );

		group.setLayoutData(gridData);
		group.setLayout(gridLayout);
	}

	private void addGridSpacing( Composite parent ) {
	
		dblGroup = new Composite(parent, SWT.NONE);
		
		GridLayout gridLayout = new GridLayout(2, false);

		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalSpan = 2;

		gridSpacing = new DoubleFieldEditor(
			IPreferenceConstants.PREF_GRID_SPACING,
			GRID_SPACING_LABEL_INCHES, dblGroup);
		gridSpacing.setTextLimit(10);
		gridSpacing.setPreferenceStore(getPreferenceStore()) ;
		editors.add(gridSpacing);
		
		updateUnits();
		
        dblGroup.setLayoutData(gridData);
        dblGroup.setLayout(gridLayout);
	}
	
	/**
	 * The NumberFormatter.parse() could return a Long or Double
	 * We are storing all values related to the page setup as doubles
	 * so we call this function when ever we are getting values from
	 * the dialog.
	 * @param number
	 * @return
	 */
	private Double forceDouble(Number number) {
		if (!(number instanceof Double))
			return new Double(number.doubleValue());			
		return (Double) number;
	}	
	
	public boolean performOk() {
		return true;
	}

	public List<FieldEditor> createFieldEditors(Composite parent) {
		editors = new ArrayList<FieldEditor>();
		
		//addRulerFields( parent );
		addGridFields( parent );

		return editors;
	}

	public boolean appliesTo(String categoryName) {
		return categoryName.equals("Appearance");
	}
}
