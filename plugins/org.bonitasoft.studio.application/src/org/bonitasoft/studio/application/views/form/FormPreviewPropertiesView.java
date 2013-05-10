/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.application.views.form;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.jface.ListContentProvider;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.provider.FileStoreLabelProvider;
import org.bonitasoft.studio.repository.themes.LookNFeelRepositoryStore;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.internal.browser.BrowserManager;
import org.eclipse.ui.internal.browser.IBrowserDescriptor;
import org.eclipse.ui.part.ViewPart;

/**
 * @author Aurelien pupier
 *
 */
public class FormPreviewPropertiesView extends ViewPart{
		
	public static final String VIEW_ID = "org.bonitasoft.studio.views.properties.form.preview";
	
	public FormPreviewPropertiesView(){
		super();
	}

	@Override
	public void createPartControl(Composite parent) {
		Composite mainComposite = new Composite(parent, SWT.NONE);
		mainComposite.setLayout(GridLayoutFactory.fillDefaults().extendedMargins(5, 0, 3, 1).create());
		
		createButtonsLine(mainComposite);
		createLookNFeelSelection(mainComposite);
		createBrowserSelection(mainComposite);
		
	}

	private void createButtonsLine(Composite mainComposite) {
		Composite buttonsComposite = new Composite(mainComposite, SWT.NONE);
		buttonsComposite.setLayout(new RowLayout());
		
		createPreviewButton(buttonsComposite);
		createAdvancedPreviewButton(buttonsComposite);
	}
	
	private void createPreviewButton(Composite buttonsComposite) {
		Button previewButton = new Button(buttonsComposite, SWT.FLAT);
		previewButton.setText(Messages.previewButton);
		previewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				//TODO: call Form preview Operation!
			}
			
		});
	}

	private void createAdvancedPreviewButton(Composite buttonsComposite) {
		Button previewButton = new Button(buttonsComposite, SWT.FLAT);
		previewButton.setText(Messages.advancedPreviewButton);
		previewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				//TODO: call Advanced Form preview Operation!
			}
			
		});		
	}

	private void createLookNFeelSelection(Composite mainComposite) {
		Composite lnfComposite = new Composite(mainComposite, SWT.NONE);
		lnfComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
		
		new Label(lnfComposite, SWT.NONE).setText(Messages.lnfForPreview);
		ComboViewer lnfCombo = new ComboViewer(lnfComposite, SWT.BORDER | SWT.READ_ONLY);
		lnfCombo.setContentProvider(new ListContentProvider());
		lnfCombo.setLabelProvider(new FileStoreLabelProvider());
		final LookNFeelRepositoryStore repositoryStore = (LookNFeelRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(LookNFeelRepositoryStore.class);		
		lnfCombo.setInput(repositoryStore.getApplicationLookNFeels());
		
		//TODO: set a default value, register it in PrefernceStore? Calculate it from process?
		lnfCombo.setSelection(new StructuredSelection());
		
	}
	
	private void createBrowserSelection(Composite mainComposite) {
		Composite browserComposite = new Composite(mainComposite, SWT.NONE);
		browserComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
		
		new Label(browserComposite, SWT.NONE).setText(Messages.browserForPreview);
		ComboViewer webBrowserCombo = new ComboViewer(browserComposite, SWT.BORDER | SWT.READ_ONLY);
		webBrowserCombo.setLabelProvider(new BrowserTableLabelProvider());
		webBrowserCombo.setContentProvider(new ListContentProvider());
		webBrowserCombo.setInput(BrowserManager.getInstance().getWebBrowsers());
		//TODO: define the default value
		webBrowserCombo.setSelection(new StructuredSelection());
	}

	/**
	 * Copy pasted from internal Eclipse code
	 *
	 */
	class BrowserTableLabelProvider extends LabelProvider {
		
		@Override
		public String getText(Object element) {
			IBrowserDescriptor browser = (IBrowserDescriptor) element;
			return notNull(browser.getName());
		}

		protected String notNull(String s) {
			if (s == null){
				return ""; //$NON-NLS-1$
			}
			return s;
		}
	}
	
	@Override
	public void setFocus() {
		//TODO: should we recalculate available browser and looknfeel?
	}

}