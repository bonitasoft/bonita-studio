package org.bonitasoft.studio.swtbot.framework.rule;

import org.bonitasoft.studio.application.views.overview.ProjectOverviewEditorInput;
import org.eclipse.swtbot.swt.finder.matchers.AbstractMatcher;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;
import org.hamcrest.Description;

public class EditorMatcherExceptOverview extends AbstractMatcher<IEditorReference> {

    @Override
    public void describeTo(Description description) {

    }

    @Override
    protected boolean doMatch(Object item) {
        try {
            return !(((IEditorReference) item).getEditorInput() instanceof ProjectOverviewEditorInput);
        } catch (PartInitException e) {
            throw new RuntimeException(e);
        }
    }

}
