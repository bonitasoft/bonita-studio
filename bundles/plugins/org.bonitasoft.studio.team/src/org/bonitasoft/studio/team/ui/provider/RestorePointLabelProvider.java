/*******************************************************************************
 * Copyright (C) 2009, 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.ui.provider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

import org.bonitasoft.studio.common.jface.BonitaStudioFontRegistry;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.team.TeamRepositoryUtil;
import org.bonitasoft.studio.team.operations.CreateWorkspaceBackupOperation;
import org.eclipse.core.databinding.observable.map.IMapChangeListener;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.map.MapChangeEvent;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.team.svn.core.resource.IRepositoryContainer;

/**
 * @author Romain Bioteau
 *
 */
public class RestorePointLabelProvider extends StyledCellLabelProvider implements ILabelProvider {


	public RestorePointLabelProvider(final int style) {
		super(style);
		initStyle();
	}

	public RestorePointLabelProvider(final IObservableMap... attributeMaps) {
		for (int i = 0; i < attributeMaps.length; i++) {
			attributeMaps[i].addMapChangeListener(mapChangeListener);
		}
		initStyle();
	}


	public RestorePointLabelProvider() {
		super();
		initStyle();
	}

	@Override
    public String getText(final Object element) {
		if(element instanceof IRepositoryContainer){
			final IRepositoryContainer resource = (IRepositoryContainer) element;
			final String label = resource.getName() ;
			final int i = label.lastIndexOf('_') ;
			final String timestamp = label.substring(i+1, label.length()) ;
            return createDateInCorrectFormat(timestamp);
		}
		return null ;
	}


	@Override
    public Image getImage(final Object element) {
		return Pics.getImage(PicsConstants.restorePoint) ;
	}

	private final IMapChangeListener mapChangeListener = new IMapChangeListener() {
		@Override
        public void handleMapChange(final MapChangeEvent event) {
			final Set<?> affectedElements = event.diff.getChangedKeys();
			if (!affectedElements.isEmpty()) {
				final LabelProviderChangedEvent newEvent = new LabelProviderChangedEvent(RestorePointLabelProvider.this, affectedElements.toArray());
				fireLabelProviderChanged(newEvent);
			}
		}
	};
	private Styler bold;

	private void initStyle() {
		bold = new StyledString.Styler() {
			@Override
			public void applyStyles(final TextStyle textStyle) {
				textStyle.font = BonitaStudioFontRegistry.getDateDataFont();
			}
		};
	}

	@Override
	public void update(final ViewerCell cell) {
		final IRepositoryContainer resource = (IRepositoryContainer) cell.getElement() ;
		final String label = resource.getName() ;
		final String comment = TeamRepositoryUtil.getRestorePointComment(resource) ;
		final int i = label.lastIndexOf('_') ;
		final String timestamp = label.substring(i+1, label.length()) ;
		final String version = TeamRepositoryUtil.getRemoteRepositoryVersion(resource) ;
		final String styledDate = createDateInCorrectFormat(timestamp);
		final StyledString styledString = createStyledString(comment, version, styledDate);

		cell.setText(styledString.getString());
		cell.setImage(getImage(null)) ;
		cell.setStyleRanges(styledString.getStyleRanges());
	}

    protected StyledString createStyledString(final String comment, final String version, final String styledDate) {
        final StyledString styledString = new StyledString();

		styledString.append(styledDate, bold);
		styledString.append(" -- ", null);
		styledString.append(version, StyledString.COUNTER_STYLER);

		if(comment != null && comment.trim().length() > 0 ){
			styledString.append(" -- ", null);
			styledString.append(comment, StyledString.DECORATIONS_STYLER);
		}
        return styledString;
    }

    protected String createDateInCorrectFormat(final String timestamp) {
        SimpleDateFormat formater = new SimpleDateFormat(CreateWorkspaceBackupOperation.RESOTRE_POINT_TIMESTAMP_PATTERN) ;
		Date date = null ;
		try {
			date  = formater.parse(timestamp) ;
		} catch (final ParseException e) {
			BonitaStudioLog.error(e) ;
		}
		formater = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss",Locale.getDefault()) ;
		final String styledDate = formater.format(date) ;
        return styledDate;
    }
}
