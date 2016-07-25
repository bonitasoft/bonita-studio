/**
 * Copyright (C) 2010-2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Aurelien Pupier <aurelien.pupier@bonitasoft.com> - initial API and implementation
 */
package org.eclipse.swtbot.finder.nebula.widgets;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.nebula.widgets.gallery.Gallery;
import org.eclipse.nebula.widgets.gallery.GalleryItem;
import org.eclipse.swt.SWT;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.SWTBotWidget;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.results.ArrayResult;
import org.eclipse.swtbot.swt.finder.results.IntResult;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.results.WidgetResult;
import org.eclipse.swtbot.swt.finder.utils.MessageFormat;
import org.eclipse.swtbot.swt.finder.utils.StringUtils;
import org.eclipse.swtbot.swt.finder.utils.internal.Assert;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBotControl;
import org.eclipse.swtbot.swt.finder.widgets.TimeoutException;
import org.hamcrest.SelfDescribing;

/**
 * @author Aurelien Pupier
 */
@SWTBotWidget(clasz = Gallery.class, preferredName = "gallery")
public class SWTBotGallery extends AbstractSWTBotControl<Gallery> {
	
	public SWTBotGallery(Gallery w) throws WidgetNotFoundException {
		super(w);
	}

	public SWTBotGallery(Gallery widget, SelfDescribing description) {
		super(widget, description);
	}

	public int getItemCount(){
		return syncExec(new IntResult() {
			public Integer run() {
				return widget.getItemCount();
			}
		});
	}
	
	public int getSelectionCount(){
		return syncExec(new IntResult() {
			public Integer run() {
				return widget.getSelectionCount();
			}
		});
	}
	
	/**
	 * Gets the selected Gallery items.
	 *
	 * @return the selection in the gallery
	 */
	public GalleryItem[]  selection() {

		return syncExec(new Result<GalleryItem[]>() {
			public GalleryItem[] run() {
				return widget.getSelection();
			}
		});
	}
	
	public void select(int... indices){
		waitForEnabled();
		if (indices.length > 1)
			assertMultiSelect();
		setFocus();
		log.debug(MessageFormat.format("Selecting items {0} in gallery {1}", StringUtils.join(indices, ", "), this)); //$NON-NLS-1$ //$NON-NLS-2$
		unselect();
		for (int i = 0; i < indices.length; i++)
			additionalSelectAndNotify(indices[i]);
	}
	
	/**
	 * Selects the items matching the array list.
	 *
	 * @param items the items to select.
	 * @return this same instance.
	 */
	public SWTBotGallery select(final String... items) {
		waitForEnabled();
		setFocus();
		asyncExec(new VoidResult() {
			public void run() {
				List<GalleryItem> selection = new ArrayList<GalleryItem>();
				for (String item : items) {
					SWTBotGalleryItem si = getGalleryItem(item);
					selection.add(si.widget);
				}
				if (!hasStyle(widget, SWT.MULTI) && items.length > 1)
					log.warn("Gallery does not support SWT.MULTI, cannot make multiple selections"); //$NON-NLS-1$
				widget.setSelection(selection.toArray(new GalleryItem[selection.size()]));
			}
		});
		notifySelect();
		return this;
	}
	
	/**
	 * Selects the items in the array. Useful for cases where you're selecting items whose names are not unique, or
	 * items you've exposed one at a time while traversing the gallery.
	 * 
	 * @param items the items to select.
	 * @return this same instance.
	 */
	public SWTBotGallery select(final SWTBotGalleryItem... items) {
		assertEnabled();
		setFocus();
		asyncExec(new VoidResult() {
			public void run() {
				List<GalleryItem> selection = new ArrayList<GalleryItem>();
				for (SWTBotGalleryItem galleryItem : items) {
					selection.add(galleryItem.widget);
				}
				if (!hasStyle(widget, SWT.MULTI) && items.length > 1)
					log.warn("Gallery does not support SWT.MULTI, cannot make multiple selections"); //$NON-NLS-1$
				widget.setSelection(selection.toArray(new GalleryItem[] {}));
			}
		});
		notifySelect();
		return this;
	}

	private void assertMultiSelect() {
		Assert.isLegal(hasStyle(widget, SWT.MULTI), "Gallery does not support multi selection."); //$NON-NLS-1$
	}
	
	public SWTBotGalleryItem getGalleryItem(final int index){
		try {
			new SWTBot().waitUntil(new DefaultCondition() {
				public String getFailureMessage() {
					return "Could not find gallery item for index " + index; //$NON-NLS-1$
				}

				public boolean test() throws Exception {
					return getItem(index) != null;
				}
			});
		} catch (TimeoutException e) {
			throw new WidgetNotFoundException("Timed out waiting for gallery item in index " + index, e); //$NON-NLS-1$
		}
		return new SWTBotGalleryItem(getItem(index));
	}
	
	protected GalleryItem getItem(final int index){

		return syncExec(new WidgetResult<GalleryItem>() {
			public GalleryItem run() {
				return widget.getItem(index);
			}
		});
	}
	
	/**
	 * Gets the gallery item matching the given name.
	 *
	 * @param nodeText the text on the node.
	 * @return the gallery item with the specified text.
	 * @throws WidgetNotFoundException if the node was not found.
	 */
	public SWTBotGalleryItem getGalleryItem(final String nodeText) throws WidgetNotFoundException {
		try {
			new SWTBot().waitUntil(new DefaultCondition() {
				public String getFailureMessage() {
					return "Could not find node with text " + nodeText; //$NON-NLS-1$
				}

				public boolean test() throws Exception {
					return getItem(nodeText) != null;
				}
			});
		} catch (TimeoutException e) {
			throw new WidgetNotFoundException("Timed out waiting for gallery item " + nodeText, e); //$NON-NLS-1$
		}
		return new SWTBotGalleryItem(getItem(nodeText));
	}

	/**
	 * Gets the item matching the given name.
	 *
	 * @param nodeText the text on the node.
	 * @return the gallery item with the specified text.
	 */
	private GalleryItem getItem(final String nodeText) {
		return syncExec(new WidgetResult<GalleryItem>() {
			public GalleryItem run() {
				GalleryItem[] items = widget.getItems();
				for (GalleryItem item : items) {
					if (item.getText().equals(nodeText))
						return item;
				}
				return null;
			}
		});
	}

	/**
	 * Gets all the items in the gallery.
	 *
	 * @return the list of all gallery items in the gallery, or an empty list if there are none.
	 * @since 1.0
	 */
	public SWTBotGalleryItem[] getAllItems() {
		return syncExec(new ArrayResult<SWTBotGalleryItem>() {
			public SWTBotGalleryItem[] run() {
				GalleryItem[] items = widget.getItems();
				SWTBotGalleryItem[] result = new SWTBotGalleryItem[items.length];

				for (int i = 0; i < items.length; i++)
					try {
						result[i] = new SWTBotGalleryItem(items[i]);
					} catch (WidgetNotFoundException e) {
						return new SWTBotGalleryItem[0];
					}
				return result;
			}
		});
	}
	
	
	/**
	 * Notifies the selection.
	 */
	protected void notifySelect() {
		notify(SWT.MouseEnter);
		notify(SWT.MouseMove);
		notify(SWT.Activate);
		notify(SWT.FocusIn);
		notify(SWT.MouseDown);
		notify(SWT.Selection/*, selectionEvent()*/);
		notify(SWT.MouseUp);
		notify(SWT.MouseHover);
		notify(SWT.MouseMove);
		notify(SWT.MouseExit);
		notify(SWT.Deactivate);
		notify(SWT.FocusOut);
	}
	
	/**
	 * Unselect all selections.
	 */
	public void unselect() {
		waitForEnabled();
		setFocus();
		asyncExec(new VoidResult() {
			public void run() {
				log.debug(MessageFormat.format("Unselecting all in {0}", widget)); //$NON-NLS-1$
				widget.deselectAll();
			}
		});
		notifySelect();
	}
	
	/**
	 * Does not clear previous selections.
	 */
	private void additionalSelectAndNotify(final int j) {
		//assertIsLegalRowIndex(j);
		asyncExec(new VoidResult() {
			public void run() {
				List<GalleryItem> newSelection = new ArrayList<GalleryItem>();
				GalleryItem[] oldSelection = widget.getSelection();
				for (GalleryItem galleryItem : oldSelection) {
					newSelection.add(galleryItem);
				}
				newSelection.add(widget.getItem(j));
				widget.setSelection(newSelection.toArray(new GalleryItem[newSelection.size()]));
			}
		});
		notifySelect();
	}

}
