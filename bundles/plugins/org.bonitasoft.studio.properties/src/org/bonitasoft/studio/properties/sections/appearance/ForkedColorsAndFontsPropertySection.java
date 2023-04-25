/******************************************************************************
 * Copyright (c) 2005, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.bonitasoft.studio.properties.sections.appearance;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.common.core.util.StringStatics;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.Properties;
import org.eclipse.gmf.runtime.diagram.ui.internal.util.FontHelper;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.gmf.runtime.diagram.ui.properties.internal.l10n.DiagramUIPropertiesImages;
import org.eclipse.gmf.runtime.diagram.ui.properties.internal.l10n.DiagramUIPropertiesMessages;
import org.eclipse.gmf.runtime.diagram.ui.properties.sections.AbstractNotationPropertiesSection;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.emf.core.util.PackageUtil;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.CompositeImageDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleEvent;
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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Colors section to represent line and font properties of a shape or connection
 * 
 * @author nbalaba
 * 
 */
public class ForkedColorsAndFontsPropertySection
	extends AbstractNotationPropertiesSection {

	// properties
	protected static final String FONT_COLOR_COMMAND_NAME = DiagramUIPropertiesMessages.
		FontColor_commandText + StringStatics.SPACE 
		+ VALUE_CHANGED_STRING;

	protected static final String LINE_COLOR_COMMAND_NAME = DiagramUIPropertiesMessages.
		LineColor_commandText + StringStatics.SPACE 
		+ VALUE_CHANGED_STRING;

	// properties
	protected static final String FONT_COMMAND_NAME = DiagramUIPropertiesMessages.
		Font_commandText + StringStatics.SPACE 
		+ VALUE_CHANGED_STRING;


	static protected final String FILL_COLOR_COMMAND_NAME = DiagramUIPropertiesMessages.
		FillColor_commandText + StringStatics.SPACE 
		+ VALUE_CHANGED_STRING;
	
	protected static final String FONTS_AND_COLORS_LABEL = DiagramUIPropertiesMessages.
		FontAndColor_nameLabel;

	protected Button fillColorButton;

	protected RGB fillColor = null;

	// font family drop down
	protected Combo fontFamilyCombo;

	// font size drop down
	protected Combo fontSizeCombo;

	protected Button fontBoldButton;

	protected Button fontItalicButton;
	
	/** the default preference color */
	protected static final RGB DEFAULT_PREF_COLOR = new RGB(0, 0, 0);

	protected Button fontColorButton;

    protected Button lineColorButton;

	protected RGB fontColor;

	protected RGB lineColor;

	protected Group colorsAndFontsGroup;

	protected int previousColor;

	protected static class ColorOverlayImageDescriptor
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
		 *            The basic Image data
		 * @param rgb
		 *            The color bar RGB value
		 */
		public ColorOverlayImageDescriptor(ImageData basicImgData, RGB rgb) {
			this.basicImgData = basicImgData;
			this.rgb = rgb;
		}

		/**
		 * @see org.eclipse.jface.resource.CompositeImageDescriptor#drawCompositeImage(int,
		 *      int)
		 */
		protected void drawCompositeImage(int width, int height) {

			// draw the thin color bar underneath
			if (rgb != null) {
				ImageData colorBar = new ImageData(width, height / 5, 1,
				
					new PaletteData(new RGB[] {rgb}));
				drawImage(colorBar, 0, height - height / 5);
				
			}
			// draw the base image
			drawImage(basicImgData, 0, 0);
		}

		/**
		 * @see org.eclipse.jface.resource.CompositeImageDescriptor#getSize()
		 */
		protected Point getSize() {
			return ICON_SIZE;
		}
	}   	
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.properties.sections.AbstractNotationPropertiesSection#initializeControls(org.eclipse.swt.widgets.Composite)
	 */
	protected void initializeControls(Composite parent) {
		super.initializeControls(parent);
		createFontsAndColorsGroups(composite);		
	}

	/**
	 * Create  fonts and colors group
	 * 
	 * @param parent - parent composite
	 */
	protected Group createFontsAndColorsGroups(Composite parent) {
		colorsAndFontsGroup = getWidgetFactory().createGroup(parent,"");
		GridLayout layout = new GridLayout(1, false);
		colorsAndFontsGroup.setLayout(layout);

		createFontsGroup(colorsAndFontsGroup);

		return colorsAndFontsGroup;

	}
	
	/**
	 * Create  font tool bar group
	 * 
	 * @param parent - parent composite
	 * @return - font tool bar
	 */
	protected Composite createFontsGroup(Composite parent) {
		Composite familySize = getWidgetFactory().createComposite(parent,SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		familySize.setLayout(layout);

		fontFamilyCombo = new Combo(familySize,
			SWT.DROP_DOWN | SWT.READ_ONLY | SWT.BORDER);
		
		fontFamilyCombo.setItems(getFontNames(FontHelper.getFontNames()));
		fontFamilyCombo.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent event) {
				updateFontFamily();
			}
		});

		fontSizeCombo = new Combo(familySize,
			SWT.DROP_DOWN | SWT.READ_ONLY | SWT.BORDER);
		fontSizeCombo.setItems(FontHelper.getFontSizes());
		fontSizeCombo.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent event) {
				updateFontSize();
			}
		});

		Composite toolBar = getWidgetFactory().createComposite(parent, SWT.SHADOW_NONE);
		toolBar.setLayout(new GridLayout(7, false));
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true)) ;
		//toolBar.setBackground(parent.getBackground());

		fontBoldButton = getWidgetFactory().createButton(toolBar, "", SWT.TOGGLE | SWT.FLAT);
		fontBoldButton.setImage(DiagramUIPropertiesImages.get(DiagramUIPropertiesImages.IMG_BOLD));
        fontBoldButton.getAccessible().addAccessibleListener(new AccessibleAdapter() {
            public void getName(AccessibleEvent e) {
                e.result = DiagramUIMessages.PropertyDescriptorFactory_FontStyle_Bold;
            }
        });
	
		
		fontItalicButton = getWidgetFactory().createButton(toolBar, "", SWT.TOGGLE | SWT.FLAT );
		fontItalicButton.setImage(DiagramUIPropertiesImages.get(DiagramUIPropertiesImages.IMG_ITALIC));
        fontItalicButton.getAccessible().addAccessibleListener(new AccessibleAdapter() {
            public void getName(AccessibleEvent e) {
                e.result = DiagramUIMessages.PropertyDescriptorFactory_FontStyle_Italic;
            }
        });

		fontBoldButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent event) {
				updateFontBold();
			}
		});

		fontItalicButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent event) {
				updateFontItalic();
			}
		});

		getWidgetFactory().createLabel(toolBar, "", SWT.LEFT);

		fontColorButton = getWidgetFactory().createButton(toolBar, "", SWT.FLAT);
		fontColorButton.setImage(DiagramUIPropertiesImages.get(DiagramUIPropertiesImages.IMG_FONT_COLOR));
        fontColorButton.getAccessible().addAccessibleListener(new AccessibleAdapter() {
            public void getName(AccessibleEvent e) {
                e.result = DiagramUIMessages.PropertyDescriptorFactory_FontColor;
            }
        });
		fontColorButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent event) {
				changeFontColor(event);
			}
		});

		getWidgetFactory().createLabel(toolBar, "", SWT.LEFT);

		lineColorButton = getWidgetFactory().createButton(toolBar, "", SWT.FLAT);
		lineColorButton.setImage(DiagramUIPropertiesImages.get(DiagramUIPropertiesImages.IMG_LINE_COLOR));
        lineColorButton.getAccessible().addAccessibleListener(new AccessibleAdapter() {
            public void getName(AccessibleEvent e) {
                e.result = DiagramUIMessages.PropertyDescriptorFactory_LineColor;
            }
        });
		lineColorButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent event) {				
				changeLineColor(event);
			}
		});

		fillColorButton = getWidgetFactory().createButton(toolBar, "", SWT.FLAT);
		fillColorButton.setImage(DiagramUIPropertiesImages.get(DiagramUIPropertiesImages.IMG_FILL_COLOR));
		fillColorButton.getAccessible().addAccessibleListener(new AccessibleAdapter() {
            public void getName(AccessibleEvent e) {
                e.result = DiagramUIMessages.PropertyDescriptorFactory_FillColor;
            }
        });
		fillColorButton.setEnabled(false);

		fillColorButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent event) {
				changeFillColor(event);
			}
		});

		if (isReadOnly())
			fillColorButton.setEnabled(false);
		else
			fillColorButton.setEnabled(true);
		
		return toolBar;
	}

	protected void changeFillColor(SelectionEvent event) {
        // calling the deprectaed method in case a client overrides the deprecated method
		if (fillColor != null){
			previousColor = FigureUtilities.RGBToInteger(fillColor);
		}
		fillColor = changeColor(event, fillColorButton,
			IPreferenceConstants.PREF_FILL_COLOR, Properties.ID_FILLCOLOR,
			FILL_COLOR_COMMAND_NAME, DiagramUIPropertiesImages.DESC_FILL_COLOR);
	}
	
	/**
	 * @return - an itertor object to iterate over the selected/input edit parts
	 */
	protected Iterator getInputIterator() {
		return getInput().iterator();

	}

	/**
	 * Apply line color change
	 * 
	 * @param event -
	 *            line color button selection event
	 */
	protected void changeLineColor(SelectionEvent event) {
		if (lineColor != null){
			previousColor = FigureUtilities.RGBToInteger(lineColor);	
		}	
		lineColor = changeColor(event, lineColorButton,
			IPreferenceConstants.PREF_LINE_COLOR, Properties.ID_LINECOLOR,
			LINE_COLOR_COMMAND_NAME, DiagramUIPropertiesImages.DESC_LINE_COLOR);
	}

	/**
	 * Apply font color change
	 * 
	 * @param event -
	 *            font button selection event
	 */
	protected void changeFontColor(SelectionEvent event) {
		if (fontColor != null){
			previousColor = FigureUtilities.RGBToInteger(fontColor);
		}
		fontColor = changeColor(event, fontColorButton,
			IPreferenceConstants.PREF_FONT_COLOR, Properties.ID_FONTCOLOR,
			FONT_COLOR_COMMAND_NAME, DiagramUIPropertiesImages.DESC_FONT_COLOR);
	}

	/**
     * @param event -
     *            selection event
     * @param button -
     *            event source
     * @param preferenceId -
     *            id of the preference of the default color value for that
     *            property
     * @param propertyId -
     *            id of the property
     * @param commandName -
     *            name of the command
     * @param imageDescriptor -
     *            the image to draw overlay on the button after the new color is
     *            set
     * @return - new RGB color, or null if none selected
     * @deprecated The preference is not being retrieved from the correct
     *             preference store so it is not needed, use the other
     *             <code>changeColor</code> method.
     */
    protected RGB changeColor(SelectionEvent event, Button button,
            String preferenceId, final String propertyId, String commandName,
            ImageDescriptor imageDescriptor) {

        return changeColor(event, button, propertyId, commandName,
            imageDescriptor);
    }
    
    /**
     * @param event -
     *            selection event
     * @param button -
     *            event source
     * @param propertyId -
     *            id of the property
     * @param commandName -
     *            name of the command
     * @param imageDescriptor -
     *            the image to draw overlay on the button after the new color is
     *            set
     * @return - new RGB color, or null if none selected
     */
    protected RGB changeColor(SelectionEvent event, Button button,
            final String propertyId, String commandName,
            ImageDescriptor imageDescriptor) {

    	CustomColorPalettePopup popup = new CustomColorPalettePopup(button.getParent()
            .getShell(), IDialogConstants.BUTTON_BAR_HEIGHT);        
        popup.setPreviousColor(previousColor);
        Rectangle r = button.getBounds();
        Point location = button.getParent().toDisplay(r.x, r.y);
        popup.open(new Point(location.x, location.y + r.height));        
        if (popup.getSelectedColor() == null && !popup.useDefaultColor()) {
            return null;
        }        
        // selectedColor should be null if we are to use the default color
        final RGB selectedColor = popup.getSelectedColor();

        final EStructuralFeature feature = (EStructuralFeature) PackageUtil
            .getElement(propertyId);

        // Update model in response to user

        List commands = new ArrayList();
        Iterator it = getInputIterator();

        RGB colorToReturn = selectedColor;
        RGB color = selectedColor;
        while (it.hasNext()) {
            final IGraphicalEditPart ep = (IGraphicalEditPart) it.next();

            color = selectedColor;
            if (popup.useDefaultColor()) {
                Object preferredValue = ep.getPreferredValue(feature);
                if (preferredValue instanceof Integer) {
                    color = FigureUtilities
                        .integerToRGB((Integer) preferredValue);
                }
            }
                
            // If we are using default colors, we want to return the color of the first selected element to be consistent
            if (colorToReturn == null) {
                colorToReturn = color;
            }

            if (color != null) {
                final RGB finalColor = color; // need a final variable
               commands.add(createCommand(commandName, ((View) ep.getModel())
                    .eResource(), new Runnable() {

                    public void run() {
                        ENamedElement element = PackageUtil
                            .getElement(propertyId);
                        if (element instanceof EStructuralFeature)
                            ep.setStructuralFeatureValue(feature,
                                FigureUtilities.RGBToInteger(finalColor));
                    }
                }));
            }
        }
        if (!commands.isEmpty()){
	        executeAsCompositeCommand(commandName, commands);
    	    Image overlyedImage = new ColorOverlayImageDescriptor(
            imageDescriptor.getImageData(), color).createImage();
        	disposeImage(button.getImage());
	        button.setImage(overlyedImage);
	    }
        return colorToReturn;
    }
    
	/**
     * Update font property
     */
	protected void updateFontBold() {

		// Update model in response to user

		List commands = new ArrayList();
		Iterator it = getInputIterator();

		while (it.hasNext()) {
			final IGraphicalEditPart ep = (IGraphicalEditPart) it.next();
			commands.add(createCommand(FONT_COMMAND_NAME,
				((View) ep.getModel()).eResource(), new Runnable() {

					public void run() {
						ep.setStructuralFeatureValue(NotationPackage.eINSTANCE.getFontStyle_Bold(),
							Boolean.valueOf(fontBoldButton.getSelection()));
					}
				}));
		}

		executeAsCompositeCommand(FONT_COMMAND_NAME, commands);

	}

	/**
	 * Update font property
	 */
	protected void updateFontItalic() {

		// Update model in response to user
		List commands = new ArrayList();
		Iterator it = getInputIterator();

		while (it.hasNext()) {
			final IGraphicalEditPart ep = (IGraphicalEditPart) it.next();
			commands.add(createCommand(FONT_COMMAND_NAME,
				((View) ep.getModel()).eResource(), new Runnable() {

					public void run() {
						ep.setStructuralFeatureValue(NotationPackage.eINSTANCE.getFontStyle_Italic(),
							new Boolean(fontItalicButton.getSelection()));
					}
				}));
		}

		executeAsCompositeCommand(FONT_COMMAND_NAME, commands);
	}

	/**
	 * Update font family property
	 */
	protected void updateFontFamily() {

		// Update model in response to user
		if (fontFamilyCombo.getText() != null
			|| !fontFamilyCombo.getText().equals("")) { //$NON-NLS-1$
			List commands = new ArrayList();
			Iterator it = getInputIterator();

			while (it.hasNext()) {
				final IGraphicalEditPart ep = (IGraphicalEditPart) it.next();
				commands.add(createCommand(FONT_COMMAND_NAME, ((View) ep
					.getModel()).eResource(), new Runnable() {

					public void run() {
						ep.setStructuralFeatureValue(NotationPackage.eINSTANCE.getFontStyle_FontName(),
							fontFamilyCombo.getText());
					}
				}));
			}

			executeAsCompositeCommand(FONT_COMMAND_NAME, commands);
		}
	}

	/**
	 * Update font size property
	 */
	protected void updateFontSize() {

		// Update model in response to user
		if (fontSizeCombo.getText() != null
			|| !fontSizeCombo.getText().equals("")) { //$NON-NLS-1$
			List commands = new ArrayList();
			Iterator it = getInputIterator();

			while (it.hasNext()) {
				final IGraphicalEditPart ep = (IGraphicalEditPart) it.next();
				commands.add(createCommand(FONT_COMMAND_NAME, ((View) ep
					.getModel()).eResource(), new Runnable() {

					public void run() {
						ep.setStructuralFeatureValue(NotationPackage.eINSTANCE.getFontStyle_FontHeight(),
							new Integer(fontSizeCombo.getText()));
					}
				}));
			}

			executeAsCompositeCommand(FONT_COMMAND_NAME, commands);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#refresh()
	 */
	public void refresh() {
		if(!isDisposed()){
            Image overlyedImage = new ColorOverlayImageDescriptor(
				DiagramUIPropertiesImages.DESC_FONT_COLOR.getImageData(),
				fontColor).createImage();
            disposeImage(fontColorButton.getImage());
            fontColorButton.setImage(overlyedImage);

            overlyedImage = new ColorOverlayImageDescriptor(
				DiagramUIPropertiesImages.DESC_LINE_COLOR.getImageData(),
				lineColor).createImage();
            disposeImage(lineColorButton.getImage());
            lineColorButton.setImage(overlyedImage);

		executeAsReadAction(new Runnable() {

			public void run() {

				IGraphicalEditPart ep = getSingleInput();
				if (ep != null) {

                    boolean isReadOnly = isReadOnly();
                    lineColorButton.setEnabled((ep.getNotationView().getStyle(NotationPackage.eINSTANCE.getLineStyle()) != null) && !isReadOnly);
                    fillColorButton.setEnabled((ep.getNotationView().getStyle(NotationPackage.eINSTANCE.getFillStyle()) != null) && !isReadOnly);

					Style style = ep.getNotationView().getStyle(NotationPackage.eINSTANCE.getFontStyle());
					boolean enableFontWidgets = (style != null) && !isReadOnly;
                    
                    fontFamilyCombo.setEnabled(enableFontWidgets);
					fontSizeCombo.setEnabled(enableFontWidgets);
					fontBoldButton.setEnabled(enableFontWidgets);
					fontItalicButton.setEnabled(enableFontWidgets);
					fontColorButton.setEnabled(enableFontWidgets);
					
					fontFamilyCombo.setText((String) getSingleInput()
						.getStructuralFeatureValue(NotationPackage.eINSTANCE.getFontStyle_FontName()));
					fontSizeCombo.setText(Integer
						.toString(((Integer) getSingleInput().getStructuralFeatureValue(
							NotationPackage.eINSTANCE.getFontStyle_FontHeight())).intValue()));
					fontBoldButton.setSelection(((Boolean) getSingleInput()
						.getStructuralFeatureValue(NotationPackage.eINSTANCE.getFontStyle_Bold()))
						.booleanValue());
					fontItalicButton.setSelection(((Boolean) getSingleInput()
						.getStructuralFeatureValue(NotationPackage.eINSTANCE.getFontStyle_Italic()))
						.booleanValue());
				}
			}
		});
		}
		if(!isDisposed()){
			Image overlyedImage = new ColorOverlayImageDescriptor(
						DiagramUIPropertiesImages.DESC_FILL_COLOR
						.getImageData(), fillColor).createImage();
	        disposeImage(fillColorButton.getImage());
			fillColorButton.setImage(overlyedImage);
			}
	}

	/**
	 * Update if nessesary, upon receiving the model event. This event will only
	 * be processed when the reciever is visible (the default behavior is not to
	 * listen to the model events while not showing). Therefore it is safe to
	 * refresh the UI. Sublclasses, which will choose to override event
	 * listening behavior should take into account that the model events are
	 * sent all the time - regardless of the section visibility. Thus special
	 * care should be taken by the section that will choose to listen to such
	 * events all the time. Also, the default implementation of this method
	 * synchronizes on the GUI thread. The subclasses that overwrite it should
	 * do the same if they perform any GUI work (because events may be sent from
	 * a non-GUI thread).
	 * 
	 * @see #aboutToBeShown()
	 * @see #aboutToBeHidden()
	 * 
	 * @param notification
	 *            notification object
	 * @param element
	 *            element that has changed
	 */
	public void update(final Notification notification, final EObject element) {
		if (!isDisposed() && isCurrentSelection(notification, element)) {
			postUpdateRequest(new Runnable() {

				public void run() {
					if (!isDisposed()
						&& isCurrentSelection(notification, element)
						&& !isNotifierDeleted(notification))
						updateColorCache();
					refresh();

				}
			});
		}
	}

	/**
	 * react to selection or model change - updatye local cache
	 */
	protected void updateColorCache() {

		executeAsReadAction(new Runnable() {

			public void run() {

				IGraphicalEditPart ep = getSingleInput();
				if (ep != null) {
					fontColor = FigureUtilities.integerToRGB((Integer) ep
						.getStructuralFeatureValue(NotationPackage.eINSTANCE.getFontStyle_FontColor()));
					lineColor = FigureUtilities.integerToRGB((Integer) ep
						.getStructuralFeatureValue(NotationPackage.eINSTANCE.getLineStyle_LineColor()));
				}
			}
		});
		
		executeAsReadAction(new Runnable() {

			public void run() {

				if (getSingleInput() instanceof GraphicalEditPart) {
					GraphicalEditPart ep = (GraphicalEditPart) getSingleInput();
					fillColor = FigureUtilities.integerToRGB((Integer) ep
						.getStructuralFeatureValue(NotationPackage.eINSTANCE.getFillStyle_FillColor()));
				} else
					fillColor = DEFAULT_PREF_COLOR;
			}
		});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#setInput(org.eclipse.ui.IWorkbenchPart,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		updateColorCache();
	}

    /**
     * Dispose the image if it was created locally to avoid a leak. Do not
     * dispose the images in the registry.
     * 
     * @param image
     */
    protected void disposeImage(Image image) {
        if (image == null) {
            return;
        }
        
        if (image.equals(DiagramUIPropertiesImages
            .get(DiagramUIPropertiesImages.IMG_FILL_COLOR))
            || image.equals(DiagramUIPropertiesImages
                .get(DiagramUIPropertiesImages.IMG_LINE_COLOR))
            || image.equals(DiagramUIPropertiesImages
                .get(DiagramUIPropertiesImages.IMG_FONT_COLOR))) {
            return;
        }

        if (! image.isDisposed()) {
            image.dispose();
        }
    }

    public void dispose() {
        if (fontColorButton != null && ! fontColorButton.isDisposed()) {
            disposeImage(fontColorButton.getImage());
        }
        if (lineColorButton != null && ! lineColorButton.isDisposed()) {
            disposeImage(lineColorButton.getImage());
        }
        if (fillColorButton != null && ! fillColorButton.isDisposed()) {
            disposeImage(fillColorButton.getImage());
        }
        super.dispose();
    }
    
    protected boolean digIntoGroups() {
        return true;
    }
    
    private String[] getFontNames(String[] items){
    	ArrayList<String> fontNames= new ArrayList<String>();
    	for (String fontName:items){
    		if (!fontName.startsWith("@")){
    			fontNames.add(fontName);
    		}
    	}
    	return (String[])fontNames.toArray(new String[fontNames.size()]);
    }
}
