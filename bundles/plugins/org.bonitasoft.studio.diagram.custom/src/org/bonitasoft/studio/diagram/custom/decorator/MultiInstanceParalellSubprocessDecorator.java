package org.bonitasoft.studio.diagram.custom.decorator;

import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.common.figures.DecoratorSVGFigure;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.MultiInstanceType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;

/**
 * @author Aurelien Pupier
 *
 */
public class MultiInstanceParalellSubprocessDecorator extends MultiInstanceSubprocessDecorator {

    public MultiInstanceParalellSubprocessDecorator(final IDecoratorTarget decoratorTarget, final ActivityDecoratorProvider activityDecoratorProvider) {
        super(decoratorTarget, activityDecoratorProvider);
    }

    @Override
    protected DecoratorSVGFigure getImageDecorator() {
        final DecoratorSVGFigure figure = FiguresHelper.getDecoratorFigure(FiguresHelper.MULTI_SUB_DECORATOR) ;
        figure.setSize(32, 16) ;
        return figure ;
    }

    @Override
    protected boolean isAppearing(final EObject activity) {
        return super.isAppearing(activity) && ((Activity) activity).getType() == MultiInstanceType.PARALLEL;
    }

}
