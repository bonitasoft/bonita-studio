/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.diagram.custom.decorator.subprocessevent;

import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.common.figures.DecoratorSVGFigure;
import org.bonitasoft.studio.model.process.StartTimerEvent;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;

/**
 * @author Aurelien Pupier
 */
public class TimerSubProcessEventDecorator extends
		AbstractSubProcessEventStartTypeDecorator {
	
	public TimerSubProcessEventDecorator(IDecoratorTarget decoratorTarget) {
		super(decoratorTarget);
	}

	@Override
	protected DecoratorSVGFigure getImageDecorator() {
		return FiguresHelper.getDecoratorFigure(FiguresHelper.SUBPROCEVENT_TIMER_DECORATOR);
	}

	@Override
	protected Class<?> getClassOfStartType() {
		return StartTimerEvent.class;
	}

}
