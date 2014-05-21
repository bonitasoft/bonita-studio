package org.bonitasoft.studio.form.preview.view;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.extension.IWidgetContribtution;
import org.bonitasoft.studio.common.jface.ListContentProvider;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.provider.FileStoreLabelProvider;
import org.bonitasoft.studio.form.preview.AbstractFormPreviewInitialization;
import org.bonitasoft.studio.form.preview.FormPreviewInitialization;
import org.bonitasoft.studio.form.preview.FormPreviewOperation;
import org.bonitasoft.studio.form.preview.i18n.Messages;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditor;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.repository.themes.ApplicationLookNFeelFileStore;
import org.bonitasoft.studio.repository.themes.LookNFeelRepositoryStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.browser.BrowserManager;
import org.eclipse.ui.internal.browser.IBrowserDescriptor;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.progress.IProgressService;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Aurelien pupier
 *
 */
public class FormPreviewPropertiesView extends ViewPart{

	public static final String VIEW_ID = "org.bonitasoft.studio.views.properties.form.preview";
	private LookNFeelRepositoryStore repositoryStore;
	private ComboViewer webBrowserCombo;
	private ComboViewer lnfCombo; 
	private TabbedPropertySheetWidgetFactory widgetFactory;
	private IFormPreviewContribution advancedFormPreview;


	public FormPreviewPropertiesView(){
		super();
		repositoryStore = (LookNFeelRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(LookNFeelRepositoryStore.class);	
	}

	@Override
	public void createPartControl(Composite parent) {
		widgetFactory = new TabbedPropertySheetWidgetFactory();
		Composite mainComposite = widgetFactory.createComposite(parent, SWT.NONE);
		mainComposite.setLayout(GridLayoutFactory.fillDefaults().extendedMargins(10, 10, 3, 1).create());

		mainComposite.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
		createButtonsLine(mainComposite);
		createLookNFeelSelection(mainComposite);
		createBrowserSelection(mainComposite);

	}

	private void createButtonsLine(Composite mainComposite) {
		Composite buttonsComposite = widgetFactory.createComposite(mainComposite, SWT.NONE);
		buttonsComposite.setLayout(new RowLayout());
		createPreviewButton(buttonsComposite);
		createAdvancedPreviewButton(buttonsComposite);
	}

	private void createPreviewButton(Composite buttonsComposite) {
		Button previewButton =widgetFactory.createButton(buttonsComposite,Messages.previewButton, SWT.PUSH);
		previewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				Form form = (Form) ((FormDiagramEditor)getSite().getPage().getActiveEditor()).getDiagramEditPart().resolveSemanticElement();
				StructuredSelection lookNFeelSelection = (StructuredSelection) lnfCombo.getSelection();
				ApplicationLookNFeelFileStore lookNFeel = (ApplicationLookNFeelFileStore)lookNFeelSelection.getFirstElement();
				StructuredSelection webBrowserSelection =(StructuredSelection) webBrowserCombo.getSelection();
				IBrowserDescriptor browser = (IBrowserDescriptor)webBrowserSelection.getFirstElement();
				AbstractFormPreviewInitialization formPreviewInit = new FormPreviewInitialization(form, lookNFeel, browser);
				final FormPreviewOperation operation = new FormPreviewOperation(formPreviewInit);
				IProgressService service = PlatformUI.getWorkbench().getProgressService();
				try {
					service.run(true, false, new IRunnableWithProgress() {

						@Override
						public void run(IProgressMonitor monitor) throws InvocationTargetException,
						InterruptedException {
							operation.run(monitor);
						}
					});
				} catch (InvocationTargetException e1) {
					BonitaStudioLog.error(e1);
				} catch (InterruptedException e1) {
					BonitaStudioLog.error(e1);
				}

			}

		});
	}

	private void createAdvancedPreviewButton(Composite buttonsComposite) {
		IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements("org.bonitasoft.studio.form.preview.formPreviewContribution"); //$NON-NLS-1$

		for (IConfigurationElement elem : elements) {
			try {
				advancedFormPreview = (IFormPreviewContribution) elem.createExecutableExtension("class"); //$NON-NLS-1$
				advancedFormPreview.createControl(buttonsComposite);
				Form form = (Form) ((FormDiagramEditor)getSite().getPage().getActiveEditor()).getDiagramEditPart().resolveSemanticElement();
				advancedFormPreview.setForm(form);
				advancedFormPreview.setLookNfeel(getCurrentLookNFeel());
				break;
			} catch (CoreException e ){
				BonitaStudioLog.error(e);
			}
		}


	}

	private void createLookNFeelSelection(Composite mainComposite) {
		Composite lnfComposite = widgetFactory.createComposite(mainComposite, SWT.NONE);
		lnfComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
		widgetFactory.createLabel(lnfComposite, Messages.lnfForPreview, SWT.NONE);
		lnfCombo = new ComboViewer(lnfComposite, SWT.BORDER | SWT.READ_ONLY);
		lnfCombo.setContentProvider(new ListContentProvider());
		lnfCombo.setLabelProvider(new FileStoreLabelProvider());
		repositoryStore = (LookNFeelRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(LookNFeelRepositoryStore.class);		
		lnfCombo.setInput(repositoryStore.getApplicationLookNFeels());
		lnfCombo.setSelection(new StructuredSelection(getCurrentLookNFeel()));
		lnfCombo.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if (advancedFormPreview!=null){
					StructuredSelection selection = (StructuredSelection)event.getSelection();
					advancedFormPreview.setLookNfeel((ApplicationLookNFeelFileStore)selection.getFirstElement());
				}

			}
		});
		if (advancedFormPreview!=null){
			advancedFormPreview.setLookNfeel(getCurrentLookNFeel());
		}
	}

	private void createBrowserSelection(Composite mainComposite) {
		Composite browserComposite = widgetFactory.createComposite(mainComposite, SWT.NONE);
		browserComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
		widgetFactory.createLabel(browserComposite, Messages.browserForPreview, SWT.NONE);
		webBrowserCombo = new ComboViewer(browserComposite, SWT.BORDER | SWT.READ_ONLY);
		webBrowserCombo.setLabelProvider(new BrowserTableLabelProvider());
		webBrowserCombo.setContentProvider(new ListContentProvider());
		webBrowserCombo.setInput(BrowserManager.getInstance().getWebBrowsers());
		webBrowserCombo.setSelection(new StructuredSelection(BrowserManager.getInstance().getCurrentWebBrowser()));
		webBrowserCombo.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if (advancedFormPreview!=null){
					StructuredSelection selection = (StructuredSelection)event.getSelection();
					advancedFormPreview.setBrowser((IBrowserDescriptor)selection.getFirstElement());
				}

			}
		});
		if (advancedFormPreview!=null){
			advancedFormPreview.setBrowser(BrowserManager.getInstance().getCurrentWebBrowser());
		}
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
		repositoryStore = (LookNFeelRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(LookNFeelRepositoryStore.class);		
		lnfCombo.setInput(repositoryStore.getApplicationLookNFeels());
		lnfCombo.setSelection(new StructuredSelection(getCurrentLookNFeel()));
		Form form = (Form) ((FormDiagramEditor)getSite().getPage().getActiveEditor()).getDiagramEditPart().resolveSemanticElement();
		if (advancedFormPreview!=null){
			advancedFormPreview.setForm(form);
		}
		//webBrowserCombo.setSelection(new StructuredSelection(BrowserManager.getInstance().getCurrentWebBrowser()));
	}

	private ApplicationLookNFeelFileStore getCurrentLookNFeel(){
		if ((getSite().getPage().getActiveEditor() instanceof FormDiagramEditor)){
			Form form = (Form) ((FormDiagramEditor)getSite().getPage().getActiveEditor()).getDiagramEditPart().resolveSemanticElement();
			AbstractProcess process = ModelHelper.getParentProcess(form);
			ApplicationLookNFeelFileStore lnfStore = (ApplicationLookNFeelFileStore) repositoryStore.getChild(process.getBasedOnLookAndFeel());
			if(lnfStore == null){
				String themeId = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getString(BonitaPreferenceConstants.DEFAULT_APPLICATION_THEME) ;
				lnfStore = (ApplicationLookNFeelFileStore) repositoryStore.getChild(themeId);
			}
			return lnfStore; 
		}
		return null;
	}

}