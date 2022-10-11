/******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * IBM Corporation - initial API and implementation
 ****************************************************************************/

package org.bonitasoft.studio.properties.sections.appearance;

import java.text.NumberFormat;
import java.text.ParseException;

import org.eclipse.gmf.runtime.common.core.util.StringStatics;
import org.eclipse.gmf.runtime.common.ui.util.DisplayUtils;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.WorkspaceViewerProperties;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.gmf.runtime.diagram.ui.properties.internal.l10n.DiagramUIPropertiesImages;
import org.eclipse.gmf.runtime.diagram.ui.properties.internal.l10n.DiagramUIPropertiesMessages;
import org.eclipse.gmf.runtime.diagram.ui.properties.sections.appearance.ColorPalettePopup;
import org.eclipse.gmf.runtime.diagram.ui.properties.views.TextChangeHelper;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.CompositeImageDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

public class BonitaRulerGridPropertySection
        extends AbstractPropertySection {

    // Groups
    private Group displayGroup;
    private Group measurementGroup;
    private Group gridlineGroup;

    // Buttons
    private Button gridVisibilityButton;
    private Button snapToGridButton;
    private Button snapToGeometryButton;
    private Button restoreButton;

    private Button rulerVisibilityButton;

    /**
     * @since 1.2
     */
    protected Button lineColorButton;

    // Labels
    private static final String GRID_ON_LABEL = DiagramUIPropertiesMessages.Grid_On_Label_Text;
    private static final String GRID_LEVEL_LABEL = DiagramUIPropertiesMessages.Grid_Level_Label_Text;
    private static final String SNAP_TO_GRID_LABEL = DiagramUIPropertiesMessages.Snap_To_Grid_Label_Text;
    private static final String SNAP_TO_GEOMETRY_LABEL = DiagramUIPropertiesMessages.Snap_To_Geometry_Label_Text;
    private static final String RULER_ON_LABEL = DiagramUIPropertiesMessages.Ruler_On_Label_Text;
    private static final String RULER_UNITS_LABEL = DiagramUIPropertiesMessages.Ruler_Units_Label_Text;
    private static final String GRID_SPACING_LABEL = DiagramUIPropertiesMessages.Grid_Spacing_Label_Text;
    private static final String VISIBILITY_LABEL = DiagramUIPropertiesMessages.Display_Group_Label_Text;
    private static final String MEASUREMENT_LABEL = DiagramUIPropertiesMessages.Measurement_Group_Label_Text;
    private static final String GRIDLINE_LABEL = DiagramUIPropertiesMessages.Gridline_Group_Label_Text;
    private static final String LINE_COLOR_LABEL = DiagramUIPropertiesMessages.Line_Color_Label_Text;
    private static final String LINE_STYLE_LABEL = DiagramUIPropertiesMessages.Line_Style_Label_Text;
    private static final String RESTORE_LABEL = DiagramUIPropertiesMessages.Restore_Defaults_Label_Text;

    // Unit labels
    private static final String INCHES_LABEL = DiagramUIPropertiesMessages.Inches_Label_Text;
    private static final String CENTIMETERS_LABEL = DiagramUIPropertiesMessages.Centimeters_Label_Text;
    private static final String PIXEL_LABEL = DiagramUIPropertiesMessages.Pixel_Label_Text;

    // Line Style labels
    private static final String SOLID_LABEL = DiagramUIPropertiesMessages.Solid_Label_Text;
    private static final String DASH_LABEL = DiagramUIPropertiesMessages.Dash_Label_Text;
    private static final String DOT_LABEL = DiagramUIPropertiesMessages.Dot_Label_Text;
    private static final String DASH_DOT_LABEL = DiagramUIPropertiesMessages.Dash_Dot_Label_Text;
    private static final String DASH_DOT_DOT_LABEL = DiagramUIPropertiesMessages.Dash_Dot_Dot_Label_Text;
    private static final String SPACED_DOT_LABEL = DiagramUIPropertiesMessages.Spaced_Dot_Label_Text;

    // Default color for the grid.
    /**
     * @since 1.2
     */
    protected static final int LIGHT_GRAY_RGB = 0;

    // Ruler unit drop down
    private CCombo rulerUnitCombo;

    // Line style drop down
    private CCombo lineStyleCombo;

    // Text widget to display and set value of the property
    private Text textWidget;

    private RGB lineColor = null;

    // For changing ruler units
    private static final int INCHES = 0;
    private static final int CENTIMETERS = 1;
    private static final int PIXELS = 2;

    // Conversion from inch to centimeter
    private static final double INCH2CM = 2.54;

    // Valid grid spacing range
    private double minValidValue = 00.009;
    private double maxValidValue = 99.999;

    // Listener for workspace property changes
    private PropertyStoreListener propertyListener = new PropertyStoreListener();

    private IPreferenceStore workspaceViewerProperties = null;

    private static class ColorOverlayImageDescriptor
            extends CompositeImageDescriptor {

        /** default color icon width */
        private static final Point ICON_SIZE = new Point(16, 16);

        /** the basic icon */
        private ImageData basicImgData;

        /** the color of the thin color bar */
        private RGB rgb;

        /**
         * Creates a new color menu image descriptor
         * 
         * @param basicIcon
         *        The basic Image data
         * @param rgb
         *        The color bar RGB value
         */
        public ColorOverlayImageDescriptor(ImageData basicImgData, RGB rgb) {
            this.basicImgData = basicImgData;
            this.rgb = rgb;
        }

        /**
         * @see org.eclipse.jface.resource.CompositeImageDescriptor#drawCompositeImage(int,
         *      int)
         */
        @Override
        protected void drawCompositeImage(int width, int height) {

            // draw the thin color bar underneath
            if (rgb != null) {
                ImageData colorBar = new ImageData(width, height / 5, 1,

                        new PaletteData(new RGB[] { rgb }));
                drawImage(colorBar, 0, height - height / 5);

            }
            // draw the base image
            drawImage(basicImgData, 0, 0);
        }

        /**
         * @see org.eclipse.jface.resource.CompositeImageDescriptor#getSize()
         */
        @Override
        protected Point getSize() {
            return ICON_SIZE;
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.views.properties.tabbed.ISection#createControls(org.eclipse.swt.widgets.Composite,
     * org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
     */
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
        super.createControls(parent, aTabbedPropertySheetPage);
        initializeControls(parent);
    }

    /**
     * Sets up controls with proper layouts and groups
     * 
     * @param parent
     */
    private void initializeControls(Composite parent) {
        parent.setLayout(new GridLayout(1, false));

        // Top row composite
        Composite topComposite = getWidgetFactory().createComposite(parent);
        topComposite.setLayout(new GridLayout(2, false));

        // Create the groups for this section
        createDisplayGroup(topComposite);
        createMeasurementGroup(topComposite);

        // Bottom row composite
        Composite bottomComposite = getWidgetFactory().createComposite(parent);
        bottomComposite.setLayout(new GridLayout(2, false));

        // Create grid line settings
        createGridlineGroup(bottomComposite);

        Composite extraComposite = getWidgetFactory().createComposite(bottomComposite);
        extraComposite.setLayout(new GridLayout(1, false));

        // Create snap to grid checkbox
        snapToGridButton = new Button(extraComposite, SWT.CHECK);
        snapToGridButton.setText(SNAP_TO_GRID_LABEL);
        snapToGridButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent event) {
                // Set the snap to grid workspace property
                setWorkspaceProperty(WorkspaceViewerProperties.SNAPTOGRID, snapToGridButton.getSelection());
            }
        });

        // Create snap to geometry checkbox
        snapToGeometryButton = new Button(extraComposite, SWT.CHECK);
        snapToGeometryButton.setText(SNAP_TO_GEOMETRY_LABEL);
        snapToGeometryButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent event) {
                // Set the snap to geometry workspace property
                setWorkspaceProperty(WorkspaceViewerProperties.SNAPTOGEOMETRY, snapToGeometryButton.getSelection());
            }
        });

        // Create restore to preferences defaults
        restoreButton = getWidgetFactory().createButton(
                extraComposite, RESTORE_LABEL, SWT.PUSH);
        restoreButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent event) {
                restorePreferenceValues();
            }

            private void restorePreferenceValues() {
                IPreferenceStore preferenceStore = getPreferenceStore();

                // The workspace properties will always exist because it is set
                // 
                IPreferenceStore wsPrefStore = getWorkspaceViewerProperties();

                if (wsPrefStore.getBoolean(WorkspaceViewerProperties.GRIDORDER) == false) {
                    wsPrefStore.setValue(WorkspaceViewerProperties.GRIDORDER, true);
                }
                if (wsPrefStore.getInt(WorkspaceViewerProperties.GRIDLINECOLOR) != LIGHT_GRAY_RGB) {
                    wsPrefStore.setValue(WorkspaceViewerProperties.GRIDLINECOLOR, LIGHT_GRAY_RGB);
                }
                if (wsPrefStore.getInt(WorkspaceViewerProperties.GRIDLINESTYLE) != SWT.LINE_SOLID) {
                    wsPrefStore.setValue(WorkspaceViewerProperties.GRIDLINESTYLE, SWT.LINE_SOLID);
                }
                if (wsPrefStore.getBoolean(WorkspaceViewerProperties.VIEWRULERS) != preferenceStore
                        .getBoolean(IPreferenceConstants.PREF_SHOW_RULERS)) {
                    wsPrefStore.setValue(WorkspaceViewerProperties.VIEWRULERS,
                            preferenceStore.getBoolean(IPreferenceConstants.PREF_SHOW_RULERS));
                }
                if (wsPrefStore.getBoolean(WorkspaceViewerProperties.VIEWGRID) != preferenceStore
                        .getBoolean(IPreferenceConstants.PREF_SHOW_GRID)) {
                    wsPrefStore.setValue(WorkspaceViewerProperties.VIEWGRID,
                            preferenceStore.getBoolean(IPreferenceConstants.PREF_SHOW_GRID));
                }
                if (wsPrefStore.getBoolean(WorkspaceViewerProperties.SNAPTOGRID) != preferenceStore
                        .getBoolean(IPreferenceConstants.PREF_SNAP_TO_GRID)) {
                    wsPrefStore.setValue(WorkspaceViewerProperties.SNAPTOGRID,
                            preferenceStore.getBoolean(IPreferenceConstants.PREF_SNAP_TO_GRID));
                }
                if (wsPrefStore.getBoolean(WorkspaceViewerProperties.SNAPTOGEOMETRY) != preferenceStore
                        .getBoolean(IPreferenceConstants.PREF_SNAP_TO_GEOMETRY)) {
                    wsPrefStore.setValue(WorkspaceViewerProperties.SNAPTOGEOMETRY,
                            preferenceStore.getBoolean(IPreferenceConstants.PREF_SNAP_TO_GEOMETRY));
                }
                if ((wsPrefStore.getInt(WorkspaceViewerProperties.RULERUNIT) != preferenceStore
                        .getInt(IPreferenceConstants.PREF_RULER_UNITS)) ||
                        (wsPrefStore.getDouble(WorkspaceViewerProperties.GRIDSPACING) != preferenceStore
                                .getDouble(IPreferenceConstants.PREF_GRID_SPACING))) {
                    wsPrefStore.setValue(WorkspaceViewerProperties.RULERUNIT,
                            preferenceStore.getInt(IPreferenceConstants.PREF_RULER_UNITS));
                    wsPrefStore.setValue(WorkspaceViewerProperties.GRIDSPACING,
                            preferenceStore.getDouble(IPreferenceConstants.PREF_GRID_SPACING));
                }

                // reset the input values
                setInput(getPart(), null);
            }
        });
    }

    private IPreferenceStore getPreferenceStore() {
        IPreferenceStore preferenceStore = (IPreferenceStore) ((IDiagramWorkbenchPart) getPart()).getDiagramEditPart()
                .getDiagramPreferencesHint().getPreferenceStore();
        return preferenceStore;
    }

    /**
     * @since 1.2
     */
    protected void createLineColorControl(Composite composite) {
        getWidgetFactory().createLabel(composite, LINE_COLOR_LABEL);

        lineColorButton = new Button(composite, SWT.PUSH);
        lineColorButton.setImage(DiagramUIPropertiesImages.get(DiagramUIPropertiesImages.IMG_LINE_COLOR));

        lineColorButton.getAccessible().addAccessibleListener(new AccessibleAdapter() {

            @Override
            public void getName(AccessibleEvent e) {
                e.result = DiagramUIMessages.PropertyDescriptorFactory_LineColor;
            }
        });

        lineColorButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent event) {
                changeLineColor(event);
            }

            /**
             * Change line color property value
             */
            private void changeLineColor(SelectionEvent event) {
                lineColor = changeColor(
                        event,
                        lineColorButton,
                        DiagramUIPropertiesImages.DESC_LINE_COLOR,
                        getWorkspacePropertyInt(WorkspaceViewerProperties.GRIDLINECOLOR));
                if (lineColor != null)
                    setWorkspaceProperty(WorkspaceViewerProperties.GRIDLINECOLOR,
                            FigureUtilities.RGBToInteger(lineColor).intValue());
            }
        });
        lineColorButton.setEnabled(true);
    }

    private void createLineStyleControl(Composite composite) {
        getWidgetFactory().createLabel(composite, LINE_STYLE_LABEL);

        lineStyleCombo = getWidgetFactory().createCCombo(composite,
                SWT.DROP_DOWN | SWT.READ_ONLY | SWT.BORDER);
        lineStyleCombo.setItems(getStyles());
        lineStyleCombo.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent event) {
                updateLineStyle();
            }

            private void updateLineStyle() {
                int style = lineStyleCombo.getSelectionIndex();
                setWorkspaceProperty(WorkspaceViewerProperties.GRIDLINESTYLE, style + SWT.LINE_SOLID);
            }
        });

    }

    /**
     * @param event -
     *        selection event
     * @param button -
     *        event source
     * @param imageDescriptor -
     *        the image to draw overlay on the button after the new
     *        color is set
     * @return - new RGB color, or null if none selected
     */
    private RGB changeColor(SelectionEvent event, Button button,
            ImageDescriptor imageDescriptor, int previousColor) {

        ColorPalettePopup popup = new ColorPalettePopup(button.getParent()
                .getShell(), IDialogConstants.BUTTON_BAR_HEIGHT);

        popup.setPreviousColor(previousColor);
        Rectangle r = button.getBounds();
        Point location = button.getParent().toDisplay(r.x, r.y);
        popup.open(new Point(location.x, location.y + r.height));

        if (popup.useDefaultColor()) {
            Image overlyedImage = new ColorOverlayImageDescriptor(
                    imageDescriptor.getImageData(), FigureUtilities.integerToRGB(new Integer(LIGHT_GRAY_RGB)))
                            .createImage();
            disposeImage(button.getImage());
            button.setImage(overlyedImage);
            return FigureUtilities.integerToRGB(new Integer(LIGHT_GRAY_RGB));
        }

        if (popup.getSelectedColor() != null) {
            Image overlyedImage = new ColorOverlayImageDescriptor(
                    imageDescriptor.getImageData(), popup.getSelectedColor())
                            .createImage();
            disposeImage(button.getImage());
            button.setImage(overlyedImage);
        }

        return popup.getSelectedColor();

    }

    private void disposeImage(Image image) {
        if (image == null) {
            return;
        }

        if (image.equals(DiagramUIPropertiesImages
                .get(DiagramUIPropertiesImages.IMG_LINE_COLOR))) {
            return;
        }

        if (!image.isDisposed()) {
            image.dispose();
        }
    }

    private Double convertStringToDouble(String strValue) {
        NumberFormat numberFormatter = NumberFormat.getInstance();
        Double value;
        try {
            value = forceDouble(numberFormatter.parse(strValue));
        } catch (ParseException e) {
            // default value
            value = new Double(getWorkspacePropertyDouble(WorkspaceViewerProperties.GRIDSPACING));
            setGridSpacing(value.doubleValue());
        }
        return value;
    }

    private void setGridSpacing(double value) {
        // Set grid spacing back to the input value
        NumberFormat numberFormater = NumberFormat.getInstance();
        textWidget.setText(numberFormater.format(value));
        textWidget.selectAll();
    }

    /**
     * Creates group with ruler units and grid spacing controls
     * 
     * @param composite
     */
    private void createMeasurementGroup(Composite composite) {

        measurementGroup = getWidgetFactory().createGroup(composite, MEASUREMENT_LABEL);
        measurementGroup.setLayout(new GridLayout(2, false));

        // Create ruler unit combo
        getWidgetFactory().createLabel(measurementGroup, RULER_UNITS_LABEL);

        rulerUnitCombo = getWidgetFactory().createCCombo(measurementGroup,
                SWT.DROP_DOWN | SWT.READ_ONLY | SWT.BORDER);
        rulerUnitCombo.setItems(getUnits());
        rulerUnitCombo.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent event) {
                int oldUnits = getWorkspacePropertyInt(WorkspaceViewerProperties.RULERUNIT);
                int newUnits = rulerUnitCombo.getSelectionIndex();

                // Order of the changes is important so that there is no
                // interim point with a 1 pixel grid spacing
                if (oldUnits < newUnits) {
                    updateSpacing(oldUnits, newUnits);
                    updateRulerUnits();
                } else {
                    updateRulerUnits();
                    updateSpacing(oldUnits, newUnits);
                }
            }

            private void updateSpacing(int fromUnits, int toUnits) {
                String currentUnits = convertUnits(fromUnits, toUnits);
                setWorkspaceProperty(WorkspaceViewerProperties.GRIDSPACING,
                        convertStringToDouble(currentUnits).doubleValue());
            }

            private void updateRulerUnits() {
                int units = getCurrentRulerUnit();
                setWorkspaceProperty(WorkspaceViewerProperties.RULERUNIT, units);
            }
        });

        // Create grid spacing text field
        getWidgetFactory().createLabel(measurementGroup, GRID_SPACING_LABEL);
        textWidget = getWidgetFactory().createText(measurementGroup, StringStatics.BLANK, SWT.BORDER);
        GridData data = new GridData(SWT.FILL, SWT.FILL, true, false);
        textWidget.setLayoutData(data);
        startTextWidgetEventListener();

    }

    /**
     * converts fromUnits to toUnits (e.g. inches to pixels)
     * 
     * @param fromUnits
     * @param toUnits
     * @return equivalent number of toUnits for the given fromUnits
     */
    private String convertUnits(int fromUnits, int toUnits) {
        String valueStr = textWidget.getText();
        if (fromUnits == toUnits) {
            return valueStr;
        }
        Double value = convertStringToDouble(valueStr);
        double pixelValue = 0;
        Display display = DisplayUtils.getDisplay();
        switch (fromUnits) {
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

        switch (toUnits) {
            case INCHES:
                returnValue = pixelValue / display.getDPI().x;
                break;
            case CENTIMETERS:
                returnValue = pixelValue * INCH2CM / display.getDPI().x;
                break;
            case PIXELS:
                returnValue = Math.round(pixelValue);
        }
        NumberFormat numberFormatter = NumberFormat.getInstance();
        return numberFormatter.format(returnValue);

    }

    /**
     * A helper to listen for events that indicate that a text field has been
     * changed.
     */
    private TextChangeHelper textListener = new TextChangeHelper() {

        boolean textModified = false;

        /**
         * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
         */
        @Override
        public void handleEvent(Event event) {
            switch (event.type) {
                case SWT.KeyDown:
                    textModified = true;
                    if (event.character == SWT.CR)
                        textChanged((Control) event.widget);
                    break;
                case SWT.FocusOut:
                    textChanged((Control) event.widget);
                    break;
            }
        }

        @Override
        public void textChanged(Control control) {
            if (textModified) {
                String currentText = ((Text) control).getText();
                try {

                    double value = convertStringToDouble(currentText).doubleValue();
                    double pixels = convertToBase(value);
                    if (pixels >= minValidValue && pixels <= maxValidValue) {
                        setWorkspaceProperty(WorkspaceViewerProperties.GRIDSPACING, value);
                        setGridSpacing(value);
                    } else {
                        resetGridSpacing();
                    }

                } catch (NumberFormatException e) {
                    resetGridSpacing();
                }
                textModified = false;
            }
        }

        private void resetGridSpacing() {
            // Set grid spacing back to original value
            double value = getWorkspacePropertyDouble(WorkspaceViewerProperties.GRIDSPACING);
            setGridSpacing(value);
        }

    };

    /**
     * converts the current units used to a base unit value to be used (e.g. in validation)
     * 
     * @param number Units to be converted to the base unit
     * @return
     */
    private double convertToBase(double number) {

        double returnValue = 0;
        switch (getCurrentRulerUnit()) {
            case INCHES:
                returnValue = number;
                break;
            case CENTIMETERS:
                returnValue = number / INCH2CM;
                break;
            case PIXELS:
                returnValue = number / DisplayUtils.getDisplay().getDPI().x;
        }
        return returnValue;
    }

    private int getCurrentRulerUnit() {
        return rulerUnitCombo.getSelectionIndex();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.views.properties.tabbed.ISection#setInput(org.eclipse.ui.IWorkbenchPart,
     * org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setInput(IWorkbenchPart part, ISelection selection) {
        super.setInput(part, selection);

        // Set up workspace property listener
        initWorkspacePropertyListener();
        double value = getWorkspacePropertyDouble(WorkspaceViewerProperties.GRIDSPACING);
        NumberFormat numberFormatter = NumberFormat.getNumberInstance();
        textWidget.setText(numberFormatter.format(value));
        rulerUnitCombo.setText(CENTIMETERS_LABEL);
        gridVisibilityButton.setSelection(getBooleanWorkspaceProperty(WorkspaceViewerProperties.VIEWGRID));
        snapToGridButton.setSelection(getBooleanWorkspaceProperty(WorkspaceViewerProperties.SNAPTOGRID));
        snapToGeometryButton.setSelection(getBooleanWorkspaceProperty(WorkspaceViewerProperties.SNAPTOGEOMETRY));

        int styleValue = getValue(WorkspaceViewerProperties.GRIDLINESTYLE) - 1;

        lineStyleCombo.setText(getStyles()[styleValue]);
        setLineColorButtonImage();
    }

    /**
     * @since 1.2
     */
    protected void setLineColorButtonImage() {
        Image overlyedImage = new ColorOverlayImageDescriptor(
                (DiagramUIPropertiesImages.DESC_LINE_COLOR).getImageData(),
                FigureUtilities
                        .integerToRGB(getWorkspacePropertyInt(WorkspaceViewerProperties.GRIDLINECOLOR)))
                                .createImage();
        disposeImage(lineColorButton.getImage());
        lineColorButton.setImage(overlyedImage);
    }

    /**
     * @param property
     * @return the integer value of the string property
     */
    private int getValue(String property) {
        int value;
        String valueString = getWorkspaceProperty(property);

        if (valueString.equals(StringStatics.BLANK)) {
            value = 0;
        } else {
            value = new Integer(getWorkspaceProperty(property)).intValue();
        }
        return value;
    }

    private String[] getUnits() {
        return new String[] { INCHES_LABEL, CENTIMETERS_LABEL, PIXEL_LABEL };
    }

    private String[] getStyles() {
        return new String[] { SOLID_LABEL, DASH_LABEL, DOT_LABEL, DASH_DOT_LABEL, DASH_DOT_DOT_LABEL, SPACED_DOT_LABEL };
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.views.properties.tabbed.ISection#dispose()
     */
    @Override
    public void dispose() {
        stopTextWidgetEventListener();
        removeWorkspacePropertyListener();
        super.dispose();
    }

    /**
     * Start listening to the text widget events
     */
    private void startTextWidgetEventListener() {
        getListener().startListeningTo(getTextWidget());
        getListener().startListeningForEnter(getTextWidget());
    }

    /**
     * Stop listening to text widget events
     */
    private void stopTextWidgetEventListener() {
        getListener().stopListeningTo(getTextWidget());
    }

    /**
     * @return Returns the textWidget.
     */
    private Text getTextWidget() {
        return textWidget;
    }

    /**
     * @return Returns the listener.
     */
    private TextChangeHelper getListener() {
        return textListener;
    }

    /**
     * Creates group with ruler and grid visibility and grid order controls
     * 
     * @param composite
     */
    private void createDisplayGroup(Composite composite) {
        displayGroup = getWidgetFactory().createGroup(composite, VISIBILITY_LABEL);
        displayGroup.setLayout(new GridLayout(1, true));

        gridVisibilityButton = new Button(displayGroup, SWT.CHECK);
        gridVisibilityButton.setText(GRID_ON_LABEL);
        gridVisibilityButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent event) {
                // Set grid visibility workspace property
                setWorkspaceProperty(WorkspaceViewerProperties.VIEWGRID, gridVisibilityButton.getSelection());
            }
        });

    }

    /**
     * Creates group with line color and style controls
     * 
     * @param composite
     */
    private void createGridlineGroup(Composite composite) {

        gridlineGroup = getWidgetFactory().createGroup(composite, GRIDLINE_LABEL);
        GridLayout gridLayout = new GridLayout(2, false);
        gridlineGroup.setLayout(gridLayout);
        createLineColorControl(gridlineGroup);
        createLineStyleControl(gridlineGroup);

    }

    private void setWorkspaceProperty(String property, boolean setting) {
        getWorkspaceViewerProperties().setValue(property, setting);
    }

    /**
     * @since 1.2
     */
    protected void setWorkspaceProperty(String property, int setting) {
        getWorkspaceViewerProperties().setValue(property, setting);
    }

    private void setWorkspaceProperty(String property, double setting) {
        getWorkspaceViewerProperties().setValue(property, setting);
    }

    private String getWorkspaceProperty(String property) {
        return getWorkspaceViewerProperties().getString(property);
    }

    /**
     * @since 1.2
     */
    protected int getWorkspacePropertyInt(String property) {
        return getWorkspaceViewerProperties().getInt(property);
    }

    private double getWorkspacePropertyDouble(String property) {
        return getWorkspaceViewerProperties().getDouble(property);
    }

    private boolean getBooleanWorkspaceProperty(String property) {
        return getWorkspaceViewerProperties().getBoolean(property);
    }

    private IPreferenceStore getWorkspaceViewerProperties() {
        return workspaceViewerProperties;
    }

    /**
     * Listener for the workspace preference store.
     */
    private class PropertyStoreListener implements IPropertyChangeListener {

        /*
         * (non-Javadoc)
         * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
         */
        @Override
        public void propertyChange(org.eclipse.jface.util.PropertyChangeEvent event) {
            handleWorkspacePropertyChanged(event);
        }
    }

    /**
     * Handles workspace property changes
     * 
     * @param event
     */
    private void handleWorkspacePropertyChanged(PropertyChangeEvent event) {
        if (WorkspaceViewerProperties.VIEWGRID.equals(event.getProperty())) {
            if (!gridVisibilityButton.isDisposed()) {
                gridVisibilityButton.setSelection(getEventBoolean(event));
            }
        } else if (WorkspaceViewerProperties.VIEWRULERS.equals(event.getProperty())) {
            if (!rulerVisibilityButton.isDisposed()) {
                rulerVisibilityButton.setSelection(getEventBoolean(event));
            }
        } else if (WorkspaceViewerProperties.SNAPTOGRID.equals(event.getProperty())) {
            if (!snapToGridButton.isDisposed()) {
                snapToGridButton.setSelection(getEventBoolean(event));
            }
        } else if (WorkspaceViewerProperties.SNAPTOGEOMETRY.equals(event.getProperty())) {
            if (!snapToGeometryButton.isDisposed()) {
                snapToGeometryButton.setSelection(getEventBoolean(event));
            }
        } else if (WorkspaceViewerProperties.GRIDSPACING.equals(event.getProperty())) {
            if (!textWidget.isDisposed()) {
                Double value = new Double(getEventString(event));
                textWidget.setText(NumberFormat.getInstance().format(value));
            }
        } else if (WorkspaceViewerProperties.RULERUNIT.equals(event.getProperty())) {
            if (!rulerUnitCombo.isDisposed()) {
                rulerUnitCombo.select(Integer.parseInt(getEventString(event)));
            }
        } else if (WorkspaceViewerProperties.GRIDLINESTYLE.equals(event.getProperty())) {
            if (!lineStyleCombo.isDisposed()) {
                lineStyleCombo.select(Integer.parseInt(getEventString(event)) - 1);
            }
        }
    }

    private boolean getEventBoolean(PropertyChangeEvent event) {
        Boolean newValue = (Boolean) event.getNewValue();
        return newValue.booleanValue();
    }

    private String getEventString(PropertyChangeEvent event) {
        return event.getNewValue().toString();
    }

    /**
     * Initializes the preferenceStore property change
     * listener.
     */
    private void initWorkspacePropertyListener() {
        IDiagramWorkbenchPart editor = (IDiagramWorkbenchPart) getPart();
        if (editor == null)
            return;
        DiagramGraphicalViewer viewer = (DiagramGraphicalViewer) editor.getDiagramGraphicalViewer();
        workspaceViewerProperties = viewer.getWorkspaceViewerPreferenceStore();
        workspaceViewerProperties.addPropertyChangeListener(propertyListener);
    }

    /**
     * This method removes all listeners to the notational world (views, figures, editpart...etc)
     * Override this method to remove notational listeners down the hierarchy
     */
    private void removeWorkspacePropertyListener() {
        if (getWorkspaceViewerProperties() != null) {
            getWorkspaceViewerProperties().removePropertyChangeListener(propertyListener);
            workspaceViewerProperties = null;
        }
        propertyListener = null;
    }

    /**
     * The NumberFormatter.parse() could return a Long or Double
     * We are storing all values related to the page setup as doubles
     * so we call this function when ever we are getting values from
     * the dialog.
     * 
     * @param number
     * @return
     */
    private Double forceDouble(Number number) {
        if (!(number instanceof Double))
            return new Double(number.doubleValue());
        return (Double) number;
    }
}
