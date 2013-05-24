package org.bonitasoft.studio.form.preview.view;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.ListContentProvider;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.provider.FileStoreLabelProvider;
import org.bonitasoft.studio.form.preview.FormPreviewOperation;
import org.bonitasoft.studio.form.preview.i18n.Messages;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditor;
import org.bonitasoft.studio.repository.themes.ApplicationLookNFeelFileStore;
import org.bonitasoft.studio.repository.themes.LookNFeelRepositoryStore;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
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
	private DataBindingContext context;
	private TabbedPropertySheetWidgetFactory widgetFactory;

	public FormPreviewPropertiesView(){
		super();
		context = new DataBindingContext();
		repositoryStore = (LookNFeelRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(LookNFeelRepositoryStore.class);	
	}

	@Override
	public void createPartControl(Composite parent) {
		widgetFactory = new TabbedPropertySheetWidgetFactory();
		Composite mainComposite = widgetFactory.createComposite(parent, SWT.NONE);
		mainComposite.setLayout(GridLayoutFactory.fillDefaults().extendedMargins(5, 0, 3, 1).create());
		
		mainComposite.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
		createButtonsLine(mainComposite);
		//		createLookNFeelSelection(mainComposite);
		createBrowserSelection(mainComposite);

	}

	private void createButtonsLine(Composite mainComposite) {
		Composite buttonsComposite = widgetFactory.createComposite(mainComposite, SWT.NONE);
		buttonsComposite.setLayout(new RowLayout());

		createPreviewButton(buttonsComposite);
		//	createAdvancedPreviewButton(buttonsComposite);
	}

	private void createPreviewButton(Composite buttonsComposite) {
		Button previewButton =widgetFactory.createButton(buttonsComposite,Messages.previewButton, SWT.PUSH);
		previewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				Form form = (Form) ((FormDiagramEditor)getSite().getPage().getActiveEditor()).getDiagramEditPart().resolveSemanticElement();
				//	StructuredSelection lookNFeelSelection = (StructuredSelection) lnfCombo.getSelection();
				//	ApplicationLookNFeelFileStore lookNFeel = (ApplicationLookNFeelFileStore)lookNFeelSelection.getFirstElement();
				ApplicationLookNFeelFileStore lookNFeel = getCurrentLookNFeel();
				StructuredSelection webBrowserSelection =(StructuredSelection) webBrowserCombo.getSelection();
				IBrowserDescriptor browser = (IBrowserDescriptor)webBrowserSelection.getFirstElement();
				final FormPreviewOperation operation = new FormPreviewOperation(form,lookNFeel,browser);
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
		lnfCombo = new ComboViewer(lnfComposite, SWT.BORDER | SWT.READ_ONLY);
		lnfCombo.setContentProvider(new ListContentProvider());
		lnfCombo.setLabelProvider(new FileStoreLabelProvider());
		repositoryStore = (LookNFeelRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(LookNFeelRepositoryStore.class);		
		lnfCombo.setInput(repositoryStore.getApplicationLookNFeels());
		lnfCombo.setSelection(new StructuredSelection(getCurrentLookNFeel()));
	}

	private void createBrowserSelection(Composite mainComposite) {
		Composite browserComposite = widgetFactory.createComposite(mainComposite, SWT.NONE);
		browserComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

		new Label(browserComposite, SWT.NONE).setText(Messages.browserForPreview);
		webBrowserCombo = new ComboViewer(browserComposite, SWT.BORDER | SWT.READ_ONLY);
		webBrowserCombo.setLabelProvider(new BrowserTableLabelProvider());
		webBrowserCombo.setContentProvider(new ListContentProvider());
		webBrowserCombo.setInput(BrowserManager.getInstance().getWebBrowsers());
		webBrowserCombo.setSelection(new StructuredSelection(BrowserManager.getInstance().getCurrentWebBrowser()));
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
		//repositoryStore = (LookNFeelRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(LookNFeelRepositoryStore.class);		
		//		lnfCombo.setInput(repositoryStore.getApplicationLookNFeels());
		//		lnfCombo.setSelection(new StructuredSelection(getCurrentLookNFeel()));
		//webBrowserCombo.setSelection(new StructuredSelection(BrowserManager.getInstance().getCurrentWebBrowser()));
	}

	private ApplicationLookNFeelFileStore getCurrentLookNFeel(){
		if ((getSite().getPage().getActiveEditor() instanceof FormDiagramEditor)){
			Form form = (Form) ((FormDiagramEditor)getSite().getPage().getActiveEditor()).getDiagramEditPart().resolveSemanticElement();
			AbstractProcess process = ModelHelper.getParentProcess(form);
			return (ApplicationLookNFeelFileStore) repositoryStore.getChild(process.getBasedOnLookAndFeel());
		}
		return null;
	}

}