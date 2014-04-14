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
import java.util.Arrays;
import java.util.List;

import org.eclipse.nebula.widgets.gallery.Gallery;
import org.eclipse.nebula.widgets.gallery.GalleryItem;
import org.eclipse.swt.SWT;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.results.IntResult;
import org.eclipse.swtbot.swt.finder.results.ListResult;
import org.eclipse.swtbot.swt.finder.results.StringResult;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.results.WidgetResult;
import org.eclipse.swtbot.swt.finder.utils.internal.Assert;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBot;
import org.eclipse.swtbot.swt.finder.widgets.TimeoutException;

/**
 * @author Aurelien Pupier
 */
public class SWTBotGalleryItem extends AbstractSWTBot<GalleryItem> {
	
	private Gallery gallery;
	
	
	public SWTBotGalleryItem(final GalleryItem w) throws WidgetNotFoundException {
		super(w);
		this.gallery = syncExec(new WidgetResult<Gallery>() {
			public Gallery run() {
				return w.getParent();
			}
		});
	}
	
	/**
	 * @return The text of the gallery item.
	 */
	public String getText(){
		//TODO: check that we really want to override 
		return syncExec(new StringResult() {	
			public String run() {
				return widget.getText();
			}
		});
	}
	
	/**
	 * @return The text at the specified index of the gallery item.
	 */
	public String getText(final int index){
		return syncExec(new StringResult() {		
			public String run() {
				return widget.getText(index);
			}
		});
	}
	
	/**
	 * @return The number of items contained in the receiver that are direct
	 * item children of the receiver.
	 */
	public int getItemCount(){
		return syncExec(new IntResult() {
			public Integer run() {
				return widget.getItemCount();
			}
		});
	}
	
	/**
	 * @param the index of the gallery item.
	 * @return the gallery item with the specified index.
	 */
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
	 * Gets the table item matching the given name.
	 *
	 * @param itemText the text on the node.
	 * @return the gallery item with the specified text.
	 * @throws WidgetNotFoundException if the node was not found.
	 */
	public SWTBotGalleryItem getGalleryItem(final String itemText) throws WidgetNotFoundException {
		try {
			new SWTBot().waitUntil(new DefaultCondition() {
				public String getFailureMessage() {
					return "Could not find node with text " + itemText; //$NON-NLS-1$
				}

				public boolean test() throws Exception {
					return getItem(itemText) != null;
				}
			});
		} catch (TimeoutException e) {
			throw new WidgetNotFoundException("Timed out waiting for gallery item " + itemText, e); //$NON-NLS-1$
		}
		return new SWTBotGalleryItem(getItem(itemText));
	}

	/**
	 * Gets the item matching the given name.
	 *
	 * @param itemText the text on the node.
	 * @return the gallery item with the specified text.
	 */
	private GalleryItem getItem(final String itemText) {
		return syncExec(new WidgetResult<GalleryItem>() {
			public GalleryItem run() {
				GalleryItem[] items = widget.getItems();
				for (int i = 0; i < items.length; i++) {
					GalleryItem item = items[i];
					if (item.getText().equals(itemText))
						return item;
				}
				return null;
			}
		});
	}
	
	/**
	 * Gets the table item matching the given name.
	 *
	 * @param itemText the text on the node.
	 * @param textIndex the index of the text on the node.
	 * @return the gallery item with the specified text.
	 * @throws WidgetNotFoundException if the node was not found.
	 */
	public SWTBotGalleryItem getGalleryItem(final String itemText, final int textIndex) throws WidgetNotFoundException {
		try {
			new SWTBot().waitUntil(new DefaultCondition() {
				public String getFailureMessage() {
					return "Could not find node with text " + itemText; //$NON-NLS-1$
				}

				public boolean test() throws Exception {
					return getItem(itemText, textIndex) != null;
				}
			});
		} catch (TimeoutException e) {
			throw new WidgetNotFoundException("Timed out waiting for gallery item " + itemText, e); //$NON-NLS-1$
		}
		return new SWTBotGalleryItem(getItem(itemText, textIndex));
	}

	/**
	 * Gets the item matching the given name at the specified index.
	 *
	 * @param itemText the text on the node.
	 * @param textIndex the index of the text to search.
	 * @return the gallery item with the specified text.
	 */
	private GalleryItem getItem(final String itemText, final int textIndex) {
		return syncExec(new WidgetResult<GalleryItem>() {
			public GalleryItem run() {
				GalleryItem[] items = widget.getItems();
				for (int i = 0; i < items.length; i++) {
					GalleryItem item = items[i];
					if (item.getText(textIndex).equals(itemText))
						return item;
				}
				return null;
			}
		});
	}
	
	/**
	 * Selects the current gallery item.
	 * 
	 * @return the current node.
	 * @since 1.0
	 */
	public SWTBotGalleryItem select() {
		assertEnabled();
		syncExec(new VoidResult() {
			public void run() {
				gallery.setFocus();
				gallery.setSelection(new GalleryItem[]{widget});
			}
		});
		notifySelect();
		return this;
	}
	
	/**
	 * Selects the items matching the array provided.
	 * 
	 * @param items the items to select.
	 * @return the current node.
	 * @since 1.0
	 */
	public SWTBotGalleryItem select(final String... items) {
		//assertEnabled();
		//waitForEnabled();
		final List<String> nodes = Arrays.asList(items);
		Assert.isTrue(getNodes().containsAll(nodes));

		syncExec(new VoidResult() {
			public void run() {
				ArrayList<GalleryItem> selection = new ArrayList<GalleryItem>();

				for (String item : items) {
					SWTBotGalleryItem si = getGalleryItem(item);
					selection.add(si.widget);
				}
				gallery.setFocus();
				gallery.setSelection(selection.toArray(new GalleryItem[selection.size()]));
			}
		});

		notifySelect();
		return this;
	}

	/**
	 * Selects the item matching the given name.
	 * 
	 * @param item the items to select.
	 * @return the current node.
	 * @since 1.0
	 */
	public SWTBotGalleryItem select(final String item) {
		return select(new String[] { item });
	}
	
	
	/**
	 * notifies the gallery widget about selection changes.
	 * 
	 * @since 1.0
	 */
	private void notifySelect() {
		notifyGallery(SWT.MouseEnter);
		notifyGallery(SWT.MouseMove);
		notifyGallery(SWT.Activate);
		notifyGallery(SWT.FocusIn);
		notifyGallery(SWT.MouseDown);
		notifyGallery(SWT.Selection);
		notifyGallery(SWT.MouseUp);
		notifyGallery(SWT.MouseHover);
		notifyGallery(SWT.MouseMove);
		notifyGallery(SWT.MouseExit);
		notifyGallery(SWT.Deactivate);
		notifyGallery(SWT.FocusOut);
	}
	
	private void notifyGallery(int eventType) {
		notify(eventType, createEvent(), gallery);
	}
	
	/**
	 * Gets the nodes of the gallery item.
	 * 
	 * @return the list of nodes in the galleryitem.
	 */
	private List<String> getNodes() {
		return syncExec(new ListResult<String>() {
			public List<String> run() {
				GalleryItem[] items = widget.getItems();
				List<String> result = new ArrayList<String>(items.length);
				for (GalleryItem item : items)
					result.add(item.getText());
				return result;
			}
		});

	}
	
}
