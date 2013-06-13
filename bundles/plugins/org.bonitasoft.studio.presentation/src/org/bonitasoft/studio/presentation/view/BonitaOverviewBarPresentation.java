/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.bonitasoft.studio.presentation.view;


import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditor;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.presentation.PresentationImages;
import org.bonitasoft.studio.presentation.i18n.Messages;
import org.eclipse.gmf.runtime.diagram.ui.internal.l10n.DiagramUIPluginImages;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.util.Geometry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.presentations.IPartMenu;
import org.eclipse.ui.presentations.IPresentablePart;
import org.eclipse.ui.presentations.IStackPresentationSite;
import org.eclipse.ui.presentations.PresentationUtil;
import org.eclipse.ui.presentations.StackDropResult;
import org.eclipse.ui.presentations.StackPresentation;
import org.eclipse.ui.themes.ITheme;

public class  BonitaOverviewBarPresentation extends StackPresentation{


	private static final String PART_DATA = "part"; //$NON-NLS-1$

	private boolean activeFocus = false;

	/**
	 * Main widget for the presentation
	 */
	private Composite presentationControl;

	/**
	 * Currently selected part
	 */
	private IPresentablePart current;


	/**
	 * close button
	 */
	private ToolItem close;

	private ViewForm contentArea;

	private Label contentDescription;
	private Composite contentDescriptionWrapper;

	private Composite clientArea;




	/** 
	 * Drag listener for regions outside the toolbar
	 */
	Listener dragListener = new Listener() {
		public void handleEvent(Event event) {
			Point loc = new Point(event.x, event.y);
			Control ctrl = (Control)event.widget;

			getSite().dragStart(ctrl.toDisplay(loc), false);
		}
	};




	private MouseListener mouseListener = new MouseAdapter() {

		// If we single-click on an empty space on the toolbar, move focus to the
		// active control
		public void mouseDown(MouseEvent e) {

			// Ignore double-clicks if we're currently over a toolbar item
			if (isOverToolItem(e)) {
				return;
			}

			if (current != null) {
				current.setFocus();
			}
		}

		public boolean isOverToolItem(MouseEvent e) {
			Point p = new Point(e.x, e.y);
			Control control = (Control)e.widget;

			if (control instanceof ToolBar) {
				ToolItem item = ((ToolBar)control).getItem(p);

				if (item != null) {
					return true;
				}
			} 

			return false;

		}

		public void mouseDoubleClick(MouseEvent e) {

		}
	};


	private Composite titleArea;

	private ToolBar tb;



	private ToolItem showOverview;

	private ToolItem showOutline;
	private static final int SPACING_WIDTH = 2;

	protected static final int TOOLBAR_WIDTH = 5;


	/**
	 * Creates a new bare-bones part presentation, given the parent composite and 
	 * an IStackPresentationSite interface that will be used to communicate with 
	 * the workbench.
	 * 
	 * @param stackSite interface to the workbench
	 * @param showToolbar 
	 */
	public BonitaOverviewBarPresentation(Composite parent, 
			IStackPresentationSite stackSite) {
		super(stackSite);

		// Create a top-level control for the presentation.
		presentationControl = new Composite(parent, SWT.NONE);

		// Add a dispose listener. This will call the presentationDisposed()
		// method when the widget is destroyed.
		presentationControl.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				presentationDisposed();
			}
		});


		titleArea = new Composite(presentationControl, SWT.NONE);
		titleArea.addMouseListener(mouseListener);
		titleArea.setLayout(new GridLayout(1,true));


		titleArea.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				Rectangle titleRect = titleArea.getClientArea();
				GC gc = e.gc;

				if(current != null) {
					int textWidth = titleRect.width - 1;
					if (textWidth > 0) {

						gc.setFont(presentationControl.getFont());
						String text = current.getTitle();

						Point extent = gc.textExtent(text, SWT.DRAW_TRANSPARENT | SWT.DRAW_MNEMONIC);	
						int textY = titleRect.y + (titleRect.height - extent.y) / 2;

						gc.setBackground(e.display.getSystemColor(SWT.COLOR_WHITE)) ;
						gc.setForeground(e.display.getSystemColor(SWT.COLOR_BLACK));
						gc.setFont(JFaceResources.getDefaultFont());
						gc.drawImage(getCurrent().getTitleImage(),getCurrent().getTitleImage().getBounds().x+3, textY-2);
						gc.drawText(text,getCurrent().getTitleImage().getBounds().width+10, textY, SWT.DRAW_TRANSPARENT | SWT.DRAW_MNEMONIC);			

					}				
				}

			}
		});

		titleArea.setBackgroundImage(Pics.getImage(PresentationImages.COOLBAR_BACKGROUND)); //$NON-NLS-1$



		PresentationUtil.addDragListener(titleArea, dragListener);



		presentationControl.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				Rectangle clientArea = presentationControl.getClientArea();			
				e.gc.setLineWidth(getBorderWidth());
				e.gc.setForeground(getBorderColor());
				e.gc.drawRectangle(clientArea.x, clientArea.y, clientArea.width-1, clientArea.height-1);
				Rectangle contentAreaBounds = contentArea.getBounds();
				int ypos = contentAreaBounds.y - 1;
				e.gc.drawLine(clientArea.x, ypos, clientArea.x + clientArea.width - 1, ypos);
			}

		});
		initPresentationWidget(presentationControl);

		contentArea = new ViewForm(presentationControl, SWT.FLAT);

		initPresentationWidget(contentArea);
		contentDescriptionWrapper = new Composite(contentArea, SWT.NONE);

		GridLayout layout = new GridLayout();
		layout.marginWidth = 4;
		layout.marginHeight = 2;
		contentDescriptionWrapper.setLayout(layout);

		GridData data = new GridData(GridData.FILL_BOTH);
		data.verticalAlignment = GridData.VERTICAL_ALIGN_CENTER;

		contentDescription = new Label(contentDescriptionWrapper, SWT.NONE);
		initPresentationWidget(contentDescription);
		contentDescription.setLayoutData(data);

		clientArea = new Composite(contentArea, SWT.NONE);
		clientArea.setVisible(false);

		contentArea.setContent(clientArea);

		createButtonBar();


	}

	private void initPresentationWidget(Control toInitialize) {
		PresentationUtil.addDragListener(toInitialize, dragListener);
		toInitialize.addMouseListener(mouseListener);
	}





	private void createButtonBar() {

		tb = new ToolBar(titleArea,SWT.FLAT);
		tb.setBackgroundMode(SWT.INHERIT_FORCE); 
		tb.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		tb.setBackgroundImage(PresentationImages.getImage(PresentationImages.COOLBAR_BACKGROUND));
		GridData gd = new GridData();
		gd.horizontalAlignment = SWT.END ;
		gd.grabExcessHorizontalSpace = true ;
		gd.verticalIndent = -8 ;
		gd.heightHint = 20 ;
		tb.setLayoutData(gd);

		showOverview = new ToolItem(tb, SWT.PUSH | SWT.TRANSPARENT);
		showOverview.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() ;
				if (editor != null && editor instanceof ProcessDiagramEditor ) {
					((ProcessDiagramEditor)editor).getShowOverivewAction().run() ;
				}else if(editor != null && editor instanceof FormDiagramEditor){
					((FormDiagramEditor)editor).getShowOverivewAction().run() ;
				}
			}

		});

		showOverview.setImage(DiagramUIPluginImages.DESC_OVERVIEW.createImage()) ;
		showOverview.setToolTipText(Messages.overview);

		showOutline = new ToolItem(tb, SWT.PUSH | SWT.TRANSPARENT);
		showOutline.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() ;
				if (editor != null && editor instanceof ProcessDiagramEditor ) {
					((ProcessDiagramEditor)editor).getShowOutlineAction().run() ;
				}else if(editor != null && editor instanceof FormDiagramEditor){
					((FormDiagramEditor)editor).getShowOutlineAction().run() ;
				}
			}

		});

		showOutline.setImage(DiagramUIPluginImages.DESC_OUTLINE.createImage()) ;
		showOutline.setToolTipText(Messages.outline_find);
		
		close = new ToolItem(tb, SWT.PUSH | SWT.TRANSPARENT);
		close.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (current != null) {
					getSite().close(new IPresentablePart[] {current});
				}
			}

		});
		close.setImage(PresentationImages.getImage(PresentationImages.CLOSE_VIEW));

	}



	public void refreshButtonBarEnablement() {
		close.setEnabled(current != null && getSite().isCloseable(current));
	}

	public void dispose() {
		// Dispose the main presentation widget. This will cause 
		// presentationDisposed to be called, which will do the real cleanup.
		presentationControl.dispose();
	}

	/**
	 * Perform any cleanup. This method should remove any listeners that were
	 * attached to other objects. This gets called when the presentation
	 * widget is disposed. This is safer than cleaning up in the dispose() 
	 * method, since this code will run even if some unusual circumstance 
	 * destroys the Shell without first calling dispose().
	 */
	protected void presentationDisposed() {
		// Remove any listeners that were attached to any
		// global Eclipse resources. This is necessary in order to prevent
		// memory leaks.
	}

	@SuppressWarnings("deprecation")
	public void setBounds(Rectangle bounds) {
		Rectangle newBounds = Geometry.copy(bounds);

		if (newBounds.width == 0) {
			// Workaround a bug in the Eclipse 3.0 release: minimized presentations will be
			// given a width of 0.
			newBounds.width = presentationControl.getBounds().width;
		}

		if (getSite().getState() == IStackPresentationSite.STATE_MINIMIZED) {
			newBounds.height = computeMinimumSize().y;
		}

		// Set the bounds of the presentation widge
		presentationControl.setBounds(newBounds);

		// Update the bounds of the currently visible part
		layout();
	}




	/**
	 * Lay out the presentation's widgets
	 */
	private void layout() {

		// Determine the inner bounds of the presentation
		Rectangle presentationClientArea = presentationControl.getClientArea();
		presentationClientArea.x += getBorderWidth();
		presentationClientArea.width -= getBorderWidth() * 2;
		presentationClientArea.y += getBorderWidth();
		presentationClientArea.height -= getBorderWidth() * 2;

		titleArea.setBounds(presentationClientArea.x, presentationClientArea.y, presentationClientArea.width, presentationClientArea.y + 25);

		int verticalSpaceRequired = 20;

		int verticalOffset = presentationClientArea.y + verticalSpaceRequired + getBorderWidth() + 2 * SPACING_WIDTH;

		contentArea.setBounds(presentationClientArea.x, verticalOffset, 
				presentationClientArea.width, presentationClientArea.height - verticalOffset);


		//		contentArea.setTopLeft(null);
		//		contentArea.setTopCenter(null);


		contentArea.layout();
		tb.pack(false);

		// Position the view's widgets

		if (current != null) {
			Rectangle clientRectangle = contentArea.getBounds();
			Point clientAreaStart = presentationControl.getParent().toControl(
					presentationControl.toDisplay(clientRectangle.x, clientRectangle.y));

			current.setBounds(new Rectangle(clientAreaStart.x, 
					clientAreaStart.y,
					clientRectangle.width, 
					clientRectangle.height));
		}


	}

	private int getBorderWidth() {
		return PlatformUI.getWorkbench().getThemeManager().getCurrentTheme().getInt(BonitaThemeConstants.BORDER_SIZE);
	}



	public void setVisible(boolean isVisible) {

		// Make the presentation widget visible
		presentationControl.setVisible(isVisible);

		// Make the currently visible part visible
		if (current != null) {
			current.setVisible(isVisible);
			if (current.getToolBar() != null) {
				current.getToolBar().setVisible(false);
			}
		}

		if (isVisible) {
			// Restore the bounds of the currently visible part. 
			// IPartPresentations can be used by multiple StackPresentations,
			// although only one such presentation is ever visible at a time.
			// It is possible that some other presentation has changed the
			// bounds of the part since it was last visible, so we need to
			// update the part's bounds when the presentation becomes visible.
			layout();
		}
	}

	private void clearSelection() {
		// If there was an existing part selected, make it invisible
		if (current != null) {
			current.setVisible(false);
		}

		current = null;
	}


	public void selectPart(IPresentablePart toSelect) {
		// Ignore redundant selections
		if (toSelect == current) {
			return;
		}

		clearSelection();

		// Select the new part
		current = toSelect;

		// Ordering is important here. We need to make the part
		// visible before updating its bounds, or the call to setBounds
		// may be ignored.

		if (current != null) {
			// Make the newly selected part visible
			current.setVisible(true);
		}

		refreshButtonBarEnablement();


		// Update the bounds of the newly selected part
		layout();
	}

	public Control[] getTabList(IPresentablePart part) {
		return new Control[] {part.getControl()};
	}

	public Control getControl() {
		return presentationControl;
	}


	public void addPart(IPresentablePart newPart, Object cookie) {
		//	if (getTab(newPart) != null) {
		//		return;
		//	}
		//	
		//	int position = toolBar.getItemCount();
		//	
		//	// If this part is being added due to a drag/drop operation,
		//	// determine the correct insertion position
		//	if (cookie instanceof DropLocation) {
		//		DropLocation location = (DropLocation)cookie;
		//		
		//		position = indexOf(location.part);
		//		
		//		// If we can't find the tab, then fall back to the
		//		// insertionPosition field
		//		if (position == -1) {
		//			position = location.insertionPosition;
		//		} else {
		//			if (!location.before) {
		//				position++;
		//			}
		//		}
		//	}
		//	
		//	// Ignore the cookie for now, since we don't support drag-and-drop yet.
		//	ToolItem toolItem = new ToolItem(toolBar, SWT.RADIO, position);
		//	
		//	// Attach the newPart pointer to the ToolItem. This is used for getPartForTab
		//	// to determine which part is associated with the tool item
		//	toolItem.setData(PART_DATA, newPart);
		//	
		//	// Attach a property change listener to the part. This will update the ToolItem
		//	// to reflect changes in the part.
		//	newPart.addPropertyListener(childPropertyChangeListener);
		//	
		//	// Attach a dispose listener to the item. This removes the above property
		//	// change listener from the part when the item is destroyed. This prevents
		//	// memory leaks.
		//	toolItem.addDisposeListener(tabDisposeListener);
		//	
		//	// Listen to selection events in the new tool item
		//	toolItem.addSelectionListener(tabItemSelectionAdapter);
		//	
		//	// Initialize the tab for this part
		//	initTab(toolItem, newPart);
		//	
		//	layout();
		//	
		//	toolBar.layout(true);
		presentationControl.redraw();
	}



	/**
	 * Returns the decorated tab text for the given part. By default, we attach 
	 * a star to indicate dirty tabs.
	 * 
	 * @param part part whose text is being computed
	 * @return the decorated tab text for the given part
	 */
	protected String getTabText(IPresentablePart part) {
		String result = part.getName();

		if (part.isDirty()) {
			result = "*" + result; //$NON-NLS-1$
		}

		return result;
	}

	/**
	 * Removes the given part from the stack.
	 * 
	 * @param oldPart the part to remove (not null)
	 */
	public void removePart(IPresentablePart oldPart) {
		// If we're removing the currently selected part, clear the selection
		if (oldPart == current) {
			clearSelection();
			refreshButtonBarEnablement();
		}

		layout();

		presentationControl.redraw();
	}





	/**
	 * Returns the part associated with the given tab, or null if none.
	 * 
	 * @param item the tab to query
	 * @return the part associated with the given tab or null if none
	 */
	protected final IPresentablePart getPartForTab(ToolItem item) {
		return (IPresentablePart)item.getData(PART_DATA);
	}

	public StackDropResult dragOver(Control currentControl, Point location) {		
		return null;
	}

	public void setActive(int newState) {
		activeFocus = (newState == AS_ACTIVE_FOCUS);
		presentationControl.redraw();
	}



	public void showPaneMenu(Point location) {
		if (current == null) {
			return;
		}

		IPartMenu menu = current.getMenu();

		if (menu == null) {
			return;
		}

		menu.showMenu(location);
	}

	public void showPaneMenu() {}

	public void showSystemMenu() {}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.presentations.StackPresentation#showPartList()
	 */
	public void showPartList() {}




	public IPresentablePart getCurrent() {
		return current;
	}


	/**
	 * @param parts
	 */
	public void close(IPresentablePart[] parts) {
		getSite().close(parts);
	}

	private Color getBorderColor() {
		ITheme current = PlatformUI.getWorkbench().getThemeManager().getCurrentTheme();
		if(activeFocus) {
			return current.getColorRegistry().get(BonitaThemeConstants.BORDER_COLOR_FOCUS);
		} else {
			return current.getColorRegistry().get(BonitaThemeConstants.BORDER_COLOR_NOFOCUS);
		}
	}

	@Override
	public void setState(int state) {
	}


}
