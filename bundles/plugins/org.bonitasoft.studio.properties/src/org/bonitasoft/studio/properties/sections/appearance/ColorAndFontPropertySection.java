/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.sections.appearance;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.common.core.util.Log;
import org.eclipse.gmf.runtime.common.core.util.Trace;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.l10n.DiagramUIActionsMessages;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GroupEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIDebugOptions;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIPlugin;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIStatusCodes;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.Properties;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.properties.internal.l10n.DiagramUIPropertiesImages;
import org.eclipse.gmf.runtime.diagram.ui.requests.ChangePropertyValueRequest;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.emf.core.util.PackageUtil;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 *
 */
public class ColorAndFontPropertySection extends ForkedColorsAndFontsPropertySection {

	private ComboViewer styleCombo;
	private Button fontDialogButton;
	private Composite styleComposite;


	@Override
	protected Composite createFontsGroup(Composite contents) {
		fontDialogButton = getWidgetFactory().createButton(contents,Messages.chooseFont, SWT.FLAT) ;
		final Composite composite = super.createFontsGroup(contents);

		fontDialogButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {


				String name = (String) getOperationSetPropertyValue(Properties.ID_FONTNAME);
				Integer height = (Integer) getOperationSetPropertyValue(Properties.ID_FONTSIZE);
				Boolean bold = (Boolean) getOperationSetPropertyValue(Properties.ID_FONTBOLD);
				Boolean italic = (Boolean) getOperationSetPropertyValue(Properties.ID_FONTITALIC);
				int style = (bold.booleanValue()? SWT.BOLD : SWT.NORMAL) | (italic.booleanValue()? SWT.ITALIC : SWT.NORMAL);
				FontData initFontData = new FontData(name, height.intValue(), style);

				Integer color = (Integer) getOperationSetPropertyValue(Properties.ID_FONTCOLOR);
				RGB initFontColor = FigureUtilities.integerToRGB(color);

				Shell shell = getDiagramGraphicalViewer().getControl().getShell();
				FontDialog fontDialog = new FontDialog(shell);
				fontDialog.setFontList(new FontData[] {initFontData});
				fontDialog.setRGB(initFontColor);


				FontData fData = fontDialog.open();
				RGB fColor = fontDialog.getRGB();

				if (fData != null && fColor != null) {
					CompoundCommand cc = new CompoundCommand(DiagramUIActionsMessages.PropertyDescriptorFactory_Font);
					cc.add(getCommand(new ChangePropertyValueRequest(Properties.ID_FONTNAME, Properties.ID_FONTNAME, fData.getName())));
					cc.add(getCommand(new ChangePropertyValueRequest(Properties.ID_FONTSIZE, Properties.ID_FONTSIZE, new Integer(fData.getHeight()))));
					cc.add(getCommand(new ChangePropertyValueRequest(Properties.ID_FONTBOLD, Properties.ID_FONTBOLD, Boolean.valueOf((fData.getStyle() & SWT.BOLD) != 0))));
					cc.add(getCommand(new ChangePropertyValueRequest(Properties.ID_FONTITALIC, Properties.ID_FONTITALIC, Boolean.valueOf((fData.getStyle() & SWT.ITALIC) != 0))));
					cc.add(getCommand(new ChangePropertyValueRequest(Properties.ID_FONTCOLOR, Properties.ID_FONTCOLOR, FigureUtilities.RGBToInteger(fColor))));
					execute(cc, new NullProgressMonitor());
				}

			}
		});

		if(isReadOnly()){
			fontDialogButton.setEnabled(false);
		}else{
			fontDialogButton.setEnabled(true);
		}

		styleComposite = getWidgetFactory().createComposite(contents.getParent().getParent().getParent(),SWT.NONE) ;
		//styleComposite.setBackground(contents.getParent().getBackground());
		styleComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false)) ;
		styleComposite.setLayout(new GridLayout(2, false)) ;
		Label sameStyleLabel = getWidgetFactory().createLabel(styleComposite,Messages.applyStyle) ;
		//sameStyleLabel.setBackground(contents.getParent().getBackground());

		styleCombo = new ComboViewer(styleComposite,SWT.READ_ONLY | SWT.BORDER){
			public Object getElementAt(int index) {
				if(index >= 0 && index - 1 >= 0){
					return super.getElementAt(index-1 ) ;
				}else{
					return super.getElementAt(index) ;
				}
			};
		};
		//styleCombo.getCombo().setBackground(contents.getParent().getBackground()) ;
		styleCombo.setLabelProvider(new LabelProvider(){
			@Override
			public String getText(Object element) {
				return((Element)((IGraphicalEditPart)element).resolveSemanticElement()).getName();
			}
		});
		GridData gd = new GridData(SWT.FILL, SWT.FILL, false, false) ;
		gd.widthHint = 200 ;
		styleCombo.getControl().setLayoutData(gd) ;
		styleCombo.getCombo().setText("Choose an existing figure...");
		styleCombo.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				if(((StructuredSelection) event.getSelection()).getFirstElement() instanceof IGraphicalEditPart){
					IGraphicalEditPart ep = (IGraphicalEditPart) ((StructuredSelection) event.getSelection()).getFirstElement() ;

					fontColor = FigureUtilities.integerToRGB((Integer) ep
							.getStructuralFeatureValue(NotationPackage.eINSTANCE.getFontStyle_FontColor()));
					lineColor = FigureUtilities.integerToRGB((Integer) ep
							.getStructuralFeatureValue(NotationPackage.eINSTANCE.getLineStyle_LineColor()));
					fillColor = FigureUtilities.integerToRGB((Integer) ep
							.getStructuralFeatureValue(NotationPackage.eINSTANCE.getFillStyle_FillColor()));
					boolean isBold = (Boolean) ep.getStructuralFeatureValue(NotationPackage.eINSTANCE.getFontStyle_Bold()) ;
					boolean isItalic = (Boolean) ep.getStructuralFeatureValue(NotationPackage.eINSTANCE.getFontStyle_Italic()) ;
					int fontSize = (Integer) ep.getStructuralFeatureValue(NotationPackage.eINSTANCE.getFontStyle_FontHeight());
					String fontFamily = (String) ep.getStructuralFeatureValue(NotationPackage.eINSTANCE.getFontStyle_FontName());


					fontFamilyCombo.setText(fontFamily);
					fontSizeCombo.setText(((Integer)fontSize).toString());
					fontBoldButton.setSelection(isBold);
					fontItalicButton.setSelection(isItalic);

					applyFillColor();
					applyFontColor();
					applyLineColor();

					updateColorCache() ;

					updateFontBold();
					updateFontFamily();
					updateFontItalic();
					updateFontSize();
				}
			}


		});

		return composite ;
	}

	private void applyFontColor() {
		List commands = new ArrayList();
		Iterator it = getInputIterator();

		RGB colorToReturn = fontColor;
		RGB color = fontColor;
		while (it.hasNext()) {
			final IGraphicalEditPart editPart = (IGraphicalEditPart) it.next();

			color = fontColor;
			// If we are using default colors, we want to return the color of the first selected element to be consistent
			if (colorToReturn == null) {
				colorToReturn = color;
			}

			if (color != null) {
				final RGB finalColor = color; // need a final variable
				commands.add(createCommand("Font Color", ((View) editPart.getModel())
						.eResource(), new Runnable() {

					public void run() {
						ENamedElement element = PackageUtil
						.getElement(Properties.ID_FONTCOLOR);
						if (element instanceof EStructuralFeature)
							editPart.setStructuralFeatureValue(NotationPackage.eINSTANCE.getFontStyle_FontColor(),
									FigureUtilities.RGBToInteger(finalColor));
					}
				}));
			}
		}
		if (!commands.isEmpty()){
			executeAsCompositeCommand("Font Color", commands);
			Image overlyedImage = new ColorOverlayImageDescriptor(DiagramUIPropertiesImages.DESC_FONT_COLOR.getImageData(), color).createImage();
			disposeImage(fontColorButton.getImage());
			fontColorButton.setImage(overlyedImage);
		}
	}

	private void applyLineColor() {
		List commands = new ArrayList();
		Iterator it = getInputIterator();

		RGB colorToReturn = lineColor;
		RGB color = lineColor;
		while (it.hasNext()) {
			final IGraphicalEditPart editPart = (IGraphicalEditPart) it.next();

			color = lineColor;
			// If we are using default colors, we want to return the color of the first selected element to be consistent
			if (colorToReturn == null) {
				colorToReturn = color;
			}

			if (color != null) {
				final RGB finalColor = color; // need a final variable
				commands.add(createCommand("Line Color", ((View) editPart.getModel())
						.eResource(), new Runnable() {

					public void run() {
						ENamedElement element = PackageUtil
						.getElement(Properties.ID_LINECOLOR);
						if (element instanceof EStructuralFeature)
							editPart.setStructuralFeatureValue(NotationPackage.eINSTANCE.getLineStyle_LineColor(),
									FigureUtilities.RGBToInteger(finalColor));
					}
				}));
			}
		}
		if (!commands.isEmpty()){
			executeAsCompositeCommand("Line Color", commands);
			Image overlyedImage = new ColorOverlayImageDescriptor(DiagramUIPropertiesImages.DESC_LINE_COLOR.getImageData(), color).createImage();
			disposeImage(lineColorButton.getImage());
			lineColorButton.setImage(overlyedImage);
		}
	}

	private void applyFillColor() {
		List commands = new ArrayList();
		Iterator it = getInputIterator();

		RGB colorToReturn = fillColor;
		RGB color = fillColor;
		while (it.hasNext()) {
			final IGraphicalEditPart editPart = (IGraphicalEditPart) it.next();

			color = fillColor;
			// If we are using default colors, we want to return the color of the first selected element to be consistent
			if (colorToReturn == null) {
				colorToReturn = color;
			}

			if (color != null) {
				final RGB finalColor = color; // need a final variable
				commands.add(createCommand("Fill Color", ((View) editPart.getModel())
						.eResource(), new Runnable() {

					public void run() {
						ENamedElement element = PackageUtil
						.getElement(Properties.ID_FILLCOLOR);
						if (element instanceof EStructuralFeature)
							editPart.setStructuralFeatureValue(NotationPackage.eINSTANCE.getFillStyle_FillColor(),
									FigureUtilities.RGBToInteger(finalColor));
					}
				}));
			}
		}
		if (!commands.isEmpty()){
			executeAsCompositeCommand("Fill Color", commands);
			Image overlyedImage = new ColorOverlayImageDescriptor(DiagramUIPropertiesImages.DESC_FILL_COLOR.getImageData(), color).createImage();
			disposeImage(fillColorButton.getImage());
			fillColorButton.setImage(overlyedImage);
		}
	}

	private DiagramEditPart getCurrentDiagramEditPart() {
		IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() ;
		if(editor instanceof DiagramEditor){
			return ((DiagramEditor)editor).getDiagramEditPart();
		}
		return null;
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection); 
		if(!isReadOnly()){
			if(fontDialogButton != null){
				fontDialogButton.setEnabled(true) ;
			}
			if(styleCombo != null && getSingleInput()!= null && getCurrentDiagramEditPart() != null){
				if(getSingleInput() instanceof ConnectionEditPart){
					styleComposite.setVisible(false);
				}else{
					styleComposite.setVisible(true);
					styleCombo.getCombo().setEnabled(true) ;
					styleCombo.setContentProvider(new StyledObjectContentProvider(getCurrentDiagramEditPart())) ;
					styleCombo.setInput(getSingleInput().resolveSemanticElement()) ;
					styleCombo.getCombo().add(Messages.ChooseFigure,0);
					styleCombo.getCombo().setText(Messages.ChooseFigure);
				}
			}
		}else{
			if(fontDialogButton != null){
				fontDialogButton.setEnabled(false) ;
			}
			if(styleCombo != null){
				styleCombo.getCombo().setEnabled(false) ;
			}
		}
	}


	@Override
	public void refresh() {
		if(!isDisposed()){


			styleCombo.getCombo().setText(Messages.ChooseFigure);

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

						fontDialogButton.setEnabled(enableFontWidgets) ;
						fontFamilyCombo.setEnabled(enableFontWidgets);
						fontSizeCombo.setEnabled(enableFontWidgets);
						fontBoldButton.setEnabled(enableFontWidgets);
						fontItalicButton.setEnabled(enableFontWidgets);
						fontColorButton.setEnabled(enableFontWidgets);
						styleComposite.setEnabled(enableFontWidgets) ;

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

						Image overlyedImage = new ColorOverlayImageDescriptor(
								DiagramUIPropertiesImages.DESC_FILL_COLOR
								.getImageData(), fillColor).createImage();
						disposeImage(fillColorButton.getImage());
						fillColorButton.setImage(overlyedImage);
					}
				}
			});
		}
	}

	protected EditPartViewer getDiagramGraphicalViewer() {
		return ((IGraphicalEditPart)getInput().iterator().next()).getViewer() ;
	}

	protected Object getOperationSetPropertyValue(String id) {
		List set = getInput() ;
		if (!set.isEmpty()) {
			IGraphicalEditPart primaryEditPart =
				(IGraphicalEditPart) set.get(set.size() - 1);
			return getPropertyValue(primaryEditPart, id);
		}
		return null;
	}

	protected Object getPropertyValue(
			final IGraphicalEditPart editPart,
			final String thePropertyId) {

		try {
			return editPart.getEditingDomain().runExclusive(
					new RunnableWithResult.Impl() {

						public void run() {
							setResult(getStructuralFeatureValue(editPart, thePropertyId));
						}
					});
		} catch (InterruptedException e) {
			Trace.catching(DiagramUIPlugin.getInstance(),
					DiagramUIDebugOptions.EXCEPTIONS_CATCHING, getClass(),
					"getPropertyValue", e); //$NON-NLS-1$
			Log.error(DiagramUIPlugin.getInstance(),
					DiagramUIStatusCodes.IGNORED_EXCEPTION_WARNING,
					"getPropertyValue", e); //$NON-NLS-1$
		}
		return null;
	}

	private Object getStructuralFeatureValue(IGraphicalEditPart editpart,
			final String thePropertyId) {
		ENamedElement element = PackageUtil.getElement(thePropertyId);
		if (element instanceof EStructuralFeature) {
			if (digIntoGroups() && editpart instanceof GroupEditPart) {
				editpart = (IGraphicalEditPart) editpart.getChildren().get(0);
			}
			return editpart
			.getStructuralFeatureValue((EStructuralFeature) element);
		}
		return null;
	}

	protected Command getCommand(Request request) {
		List operationSet = getInput();
		Iterator editParts = operationSet.iterator();
		CompoundCommand command = new CompoundCommand("Change Font");
		while (editParts.hasNext()) {
			EditPart editPart = (EditPart) editParts.next();
			Command curCommand = editPart.getCommand(request);
			if (curCommand != null) {
				command.add(curCommand);
			}
		}
		return command.isEmpty() || command.size() != operationSet.size() ? UnexecutableCommand.INSTANCE
				: (Command) command;
	}

	protected final void execute(Command command,
			IProgressMonitor progressMonitor) {
		if (command == null || !command.canExecute())
			return;
		if (getDiagramCommandStack() != null)
			getDiagramCommandStack().execute(command, progressMonitor);
	}

	private DiagramCommandStack getDiagramCommandStack() {
		return ((IGraphicalEditPart)getInput().iterator().next()).getDiagramEditDomain().getDiagramCommandStack() ;
	}

}
