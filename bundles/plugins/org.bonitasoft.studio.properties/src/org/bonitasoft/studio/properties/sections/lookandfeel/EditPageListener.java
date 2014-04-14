package org.bonitasoft.studio.properties.sections.lookandfeel;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceFileStore.ResourceType;
import org.bonitasoft.studio.diagram.custom.repository.WebTemplatesUtil;
import org.bonitasoft.studio.model.process.AssociatedFile;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

/**
 * @author Mickael Istria
 * 
 */
final class EditPageListener extends LookAndFeelPropertySectionListener implements SelectionListener {
	private ResourceType templateType;

	public EditPageListener(ResourceType templateType) {
		this.templateType = templateType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse
	 * .swt.events.SelectionEvent)
	 */
	public void widgetSelected(SelectionEvent e) {
		AssociatedFile template;
		switch (templateType) {
		case ERROR_TEMPLATE:
			template = getProcess().getErrorTemplate();
			break;
		case GLOBAL_CONSULTATION_TEMPLATE:
			template = getProcess().getConsultationTemplate();
			break;
		case GLOBAL_PAGE_TEMPLATE:
			template = getProcess().getPageTemplate();
			break;
		case HOST_PAGE:
			template = getProcess().getHostPage();
			break;
		case LOGIN_PAGE:
			template = getProcess().getLogInPage();
			break;
		case PROCESS_TEMPLATE:
			template = getProcess().getProcessTemplate();
			break;
		case WELCOME:
			template = getProcess().getWelcomePage();
			break;

		default:
			return;
		}
		if (template != null && template.getPath() != null) {
			File file = WebTemplatesUtil.getFile(template.getPath());
			if (file != null && file.exists() && !file.isDirectory()) {
				URI uri = file.toURI();
				try {
					URL url = uri.toURL();
					url.toString();
					url.toURI();
					try {
						IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), url.toURI(),
								IDE.getEditorDescriptor(file.getName()).getId(), true);
					} catch (PartInitException e1) {
						BonitaStudioLog.error(e1);
					}
				} catch (MalformedURLException e2) {
					BonitaStudioLog.error(e2);
				} catch (URISyntaxException e4) {
					BonitaStudioLog.error(e4);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse
	 * .swt.events.SelectionEvent)
	 */
	public void widgetDefaultSelected(SelectionEvent e) {
	}
}