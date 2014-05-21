/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.figures;

import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.swt.SWT;

/**
 * Use for resize feedback only
 * @author Romain Bioteau
 */
public class EventSubprocessFigureWrapper extends CollapsableEventSubprocessFigure {


	private WrappingLabel fFigureEventSubProcessNameFigure;


	public EventSubprocessFigureWrapper() {

		GridLayout layoutThis = new GridLayout();
		layoutThis.numColumns = 1;
		layoutThis.makeColumnsEqualWidth = true;
		layoutThis.marginWidth = 2;
		layoutThis.marginHeight = 10;
		this.setLayoutManager(layoutThis);

		this.setLineCap(SWT.CAP_ROUND);

		this.setLineWidth(2);

		this.setLineStyle(SWT.LINE_DOT);

		this.setLineDash(new float[] { 1.0f, 5.0f });

		this.setAntialias(SWT.ON);

		this.setCornerDimensions(new Dimension(15, 15));

		this.setPreferredSize(new Dimension(100,50));
		this.setMinimumSize(new Dimension(100,50));
		createContents();
	}


	private void createContents() {

		fFigureEventSubProcessNameFigure = new WrappingLabel();
		fFigureEventSubProcessNameFigure.setText("");

		GridData constraintFFigureEventSubProcessNameFigure = new GridData();
		constraintFFigureEventSubProcessNameFigure.verticalAlignment = GridData.CENTER;
		constraintFFigureEventSubProcessNameFigure.horizontalAlignment = GridData.CENTER;
		constraintFFigureEventSubProcessNameFigure.horizontalIndent = 0;
		constraintFFigureEventSubProcessNameFigure.horizontalSpan = 1;
		constraintFFigureEventSubProcessNameFigure.verticalSpan = 1;
		constraintFFigureEventSubProcessNameFigure.grabExcessHorizontalSpace = true;
		constraintFFigureEventSubProcessNameFigure.grabExcessVerticalSpace = false;
		this.add(fFigureEventSubProcessNameFigure,
				constraintFFigureEventSubProcessNameFigure);

	}


	public WrappingLabel getFigureEventSubProcessNameFigure() {
		return fFigureEventSubProcessNameFigure;
	}


}
