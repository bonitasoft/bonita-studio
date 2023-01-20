package org.bonitasoft.studio.properties.sections.index;

import org.bonitasoft.studio.model.process.SearchIndex;
import org.bonitasoft.studio.refactoring.core.RefactorPair;

public class SearchIndexRefactorPair extends RefactorPair<SearchIndex, SearchIndex> {

    public SearchIndexRefactorPair(final SearchIndex newValue, final SearchIndex oldValue) {
        super(newValue, oldValue);
    }

    @Override
    public String getOldValueName() {
        return getOldValue().getName().getName();
    }

    @Override
    public String getNewValueName() {
        if (getNewValue() != null) {
            return getNewValue().getName().getName();
        } else {
            return super.getNewValueName();
        }
    }
}
