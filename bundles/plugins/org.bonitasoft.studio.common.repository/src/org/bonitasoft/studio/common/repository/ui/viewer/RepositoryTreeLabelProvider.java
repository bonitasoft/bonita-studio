package org.bonitasoft.studio.common.repository.ui.viewer;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * @author Baptiste Mesta
 *
 */
public class RepositoryTreeLabelProvider extends LabelProvider {

	private final boolean showImages;

	public RepositoryTreeLabelProvider() {
		this(true);
	}

	public RepositoryTreeLabelProvider(final boolean showImages) {
		super();
		this.showImages = showImages;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(final Object element) {
		if (element instanceof IRepositoryStore) {
            return ((IRepositoryStore<?>) element).getDisplayName();
		} else if (element instanceof IRepositoryFileStore) {
			return ((IRepositoryFileStore) element).getDisplayName();
		}
		return super.getText(element);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
	 */
	@Override
	public Image getImage(final Object element) {
		if(showImages){
			if (element instanceof IRepositoryStore) {
                return ((IRepositoryStore<?>) element).getIcon();
			} else if (element instanceof IRepositoryFileStore) {
				return ((IRepositoryFileStore) element).getIcon();
			}
		}
		return super.getImage(element);
	}

}