/**
 * Copyright (C) 2010-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.jface;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.Activator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Baptiste Mesta
 *
 */
public class BonitaErrorDialog extends ErrorDialog {
	private IStatus status;
	private boolean listCreated;
	private List list;
	private Button detailsButton;
	private Clipboard clipboard;

	/**
	 * @param parentShell
	 * @param dialogTitle
	 * @param message
	 * @param status
	 * @param displayMask
	 */
	public BonitaErrorDialog(final Shell parentShell, final String dialogTitle, final String message, final IStatus status, final int displayMask) {
		super(parentShell, dialogTitle, message, status, displayMask);
		this.message = message;
		if(status.getException() == null){
			this.status = new Status(status.getSeverity(), status.getPlugin(), status.getMessage(), new Exception(status.getMessage()));
		}else{
			this.status = status;
		}
		setStatus(this.status);
	}

	public BonitaErrorDialog(final Shell parentShell, final String dialogTitle, final String message, final Throwable t) {
		super(parentShell, dialogTitle, message, new Status(IStatus.ERROR, Activator.PLUGIN_ID, message, t), IStatus.ERROR);
		this.message = message;
		status = new Status(IStatus.ERROR, Activator.PLUGIN_ID, message, t);
		setStatus(status);
	}
	/*
	 * (non-Javadoc) Method declared on Dialog. Handles the pressing of the Ok
	 * or Details button in this dialog. If the Ok button was pressed then close
	 * this dialog. If the Details button was pressed then toggle the displaying
	 * of the error details area. Note that the Details button will only be
	 * visible if the error being displayed specifies child details.
	 */
	@Override
    protected void buttonPressed(final int id) {
		if (id == IDialogConstants.DETAILS_ID) {
			// was the details button pressed?
			toggleDetailsArea();
		} else {
			super.buttonPressed(id);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.ErrorDialog#createDropDownList(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected List createDropDownList(final Composite parent) {
		list = new List(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL
				| SWT.MULTI);
		// fill the list
		populateStackDetails(list);
		final GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL
				| GridData.GRAB_HORIZONTAL | GridData.VERTICAL_ALIGN_FILL
				| GridData.GRAB_VERTICAL);
		data.heightHint = list.getItemHeight() * 7;
		data.horizontalSpan = 2;
		list.setLayoutData(data);
		list.setFont(parent.getFont());
		final Menu copyMenu = new Menu(list);
		final MenuItem copyItem = new MenuItem(copyMenu, SWT.NONE);
		copyItem.addSelectionListener(new SelectionListener() {
			/*
			 * @see SelectionListener.widgetSelected (SelectionEvent)
			 */
			@Override
            public void widgetSelected(final SelectionEvent e) {
				copyToClipboard();
			}

			/*
			 * @see SelectionListener.widgetDefaultSelected(SelectionEvent)
			 */
			@Override
            public void widgetDefaultSelected(final SelectionEvent e) {
				copyToClipboard();
			}
		});
		copyItem.setText(JFaceResources.getString("copy")); //$NON-NLS-1$
		list.setMenu(copyMenu);
		listCreated = true;
		return list;
	}

    protected void populateStackDetails(final List list) {
		final Throwable exception = getStatusException();
		if(exception != null){
            addLocalizedMesssageToList(list, exception);
		}
        if (exception instanceof InvocationTargetException) {
            final Throwable targetException = ((InvocationTargetException) exception).getTargetException();
            if (targetException != null) {
                addLocalizedMesssageToList(list, targetException);
                addStackTraceToList(list, targetException);
            }
        }
		addStackTraceToList(list, exception);
	}

    protected Throwable getStatusException() {
        return status.getException();
    }

    private void addStackTraceToList(final List list, final Throwable targetException) {
        for (final StackTraceElement stack : targetException.getStackTrace()) {
            list.add(stack.toString());
        }
    }

    private void addLocalizedMesssageToList(final List list, final Throwable exception) {
        final String message = exception.getLocalizedMessage();
        if (message != null) {
            for (final String line : message.split(System.getProperty("line.separator"))) {
                list.add(line);
            }
        }
    }

	/**
	 * Toggles the unfolding of the details area. This is triggered by the user
	 * pressing the details button.
	 */
	private void toggleDetailsArea() {
		final Point windowSize = getShell().getSize();
		final Point oldSize = getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT);
		if (listCreated) {
			list.dispose();
			listCreated = false;
			detailsButton.setText(IDialogConstants.SHOW_DETAILS_LABEL);
		} else {
			list = createDropDownList((Composite) getContents());
			detailsButton.setText(IDialogConstants.HIDE_DETAILS_LABEL);
			getContents().getShell().layout();
		}
		final Point newSize = getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT);
		getShell()
		.setSize(
				new Point(windowSize.x, windowSize.y
						+ (newSize.y - oldSize.y)));
	}


	@Override
    protected void createDetailsButton(final Composite parent) {
		if (shouldShowDetailsButton()) {
			detailsButton = createButton(parent, IDialogConstants.DETAILS_ID,
					IDialogConstants.SHOW_DETAILS_LABEL, false);
		}
	}

	/**
	 * Copy the contents of the statuses to the clipboard.
	 */
	private void copyToClipboard() {
		if (clipboard != null) {
			clipboard.dispose();
		}
		final StringBuffer statusBuffer = new StringBuffer();
		populateCopyBuffer(status, statusBuffer, 0);
		clipboard = new Clipboard(list.getDisplay());
		clipboard.setContents(new Object[] { statusBuffer.toString() },
				new Transfer[] { TextTransfer.getInstance() });
	}
	/**
	 * Put the details of the status of the error onto the stream.
	 *
	 * @param buildingStatus
	 * @param buffer
	 * @param nesting
	 */
	private void populateCopyBuffer(final IStatus buildingStatus,
			final StringBuffer buffer, final int nesting) {
		buffer.append(buildingStatus.getMessage());
		buffer.append("\n"); //$NON-NLS-1$
		for (final StackTraceElement trace : buildingStatus.getException().getStackTrace()) {

			buffer.append(trace.toString());
			buffer.append("\n"); //$NON-NLS-1$
		}
	}
	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.jface.window.Window#close()
	 */
	@Override
    public boolean close() {
		if (clipboard != null) {
			clipboard.dispose();
		}
		return super.close();
	}
}