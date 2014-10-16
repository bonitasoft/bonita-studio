/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.contract.ui.property.constraint.edit.editor;

import org.bonitasoft.studio.contract.core.constraint.ConstraintInputIndexer;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.swt.widgets.Display;


/**
 * @author Romain Bioteau
 *
 */
public class UpdateInputReferenceListener extends JobChangeAdapter {


    private final IObservableList dependenciesList;

    public UpdateInputReferenceListener(final IObservableList dependenciesList) {
        this.dependenciesList = dependenciesList;
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.jobs.IJobChangeListener#done(org.eclipse.core.runtime.jobs.IJobChangeEvent)
     */
    @Override
    public void done(final IJobChangeEvent event) {
        final ConstraintInputIndexer inputIndexer = (ConstraintInputIndexer) event.getJob();
        Display.getDefault().syncExec(new Runnable() {

            @Override
            public void run() {
                dependenciesList.clear();
                dependenciesList.addAll(inputIndexer.getReferencedInputs());
            }
        });

    }



}
