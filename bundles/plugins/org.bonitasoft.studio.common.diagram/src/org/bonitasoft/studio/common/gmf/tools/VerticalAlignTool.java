package org.bonitasoft.studio.common.gmf.tools;

import org.eclipse.gef.Request;
import org.eclipse.gef.tools.MarqueeSelectionTool;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.ArrangeAction;
import org.eclipse.gmf.runtime.diagram.ui.requests.ArrangeRequest;
import org.eclipse.ui.PlatformUI;


public class VerticalAlignTool extends MarqueeSelectionTool {

	public VerticalAlignTool() {
		super();
	}

	@Override
	protected void performMarqueeSelect() {
		super.performMarqueeSelect();
		ArrangeAction action = new ArrangeAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), true){

			@Override
			public void init() {
				setId("arrangeSelectionAction");
				super.init();
			}

			@Override
			protected Request createTargetRequest() {
				return new ArrangeRequest(getId(),"TopDown");
			}
		};
		action.init();
		action.refresh();
		if(action.isRunnable()){
			action.run();
		}
		action.dispose();

	}
}
