/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.businessobject.ui.wizard.control;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.bonitasoft.engine.bdm.BDMQueryUtil;
import org.bonitasoft.engine.bdm.model.Query;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.model.businessObject.BusinessObjectBuilder;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.junit.Rule;
import org.junit.Test;

public class QueryStatusLabelProviderTest {

    @Rule
    public RealmWithDisplay realm = new RealmWithDisplay();
    
    @Test
    public void should_display_hint_image_for_multiple_query_without_count() throws Exception {
        WritableValue writableValue = new WritableValue();
        Query query = new Query("allEmp", "",List.class.getName());
        writableValue.setValue(BusinessObjectBuilder.aBO("Employee").withQuery(query).build());
        
        QueryStatusLabelProvider queryStatusLabelProvider = spy(new QueryStatusLabelProvider(writableValue));
        
        queryStatusLabelProvider.getImage(query);
        
        verify(queryStatusLabelProvider).getHintImage();
    }
    
    @Test
    public void should_not_display_hint_image_for_multiple_query_with_count() throws Exception {
        WritableValue writableValue = new WritableValue();
        Query query = new Query("allEmp", "",List.class.getName());
        writableValue.setValue(BusinessObjectBuilder.aBO("Employee").withQuery(query)
                .withQuery(new Query(BDMQueryUtil.getCountQueryName("allEmp"), "", Long.class.getName())).build());
        
        QueryStatusLabelProvider queryStatusLabelProvider = spy(new QueryStatusLabelProvider(writableValue));
        
        queryStatusLabelProvider.getImage(query);
        
        verify(queryStatusLabelProvider,never()).getHintImage();
    }
    
    @Test
    public void should_not_display_hint_image_for_single_query() throws Exception {
        WritableValue writableValue = new WritableValue();
        Query query = new Query("allEmp", "",Long.class.getName());
        writableValue.setValue(BusinessObjectBuilder.aBO("Employee").withQuery(query).build());
        
        QueryStatusLabelProvider queryStatusLabelProvider = spy(new QueryStatusLabelProvider(writableValue));
        
        queryStatusLabelProvider.getImage(query);
        
        verify(queryStatusLabelProvider,never()).getHintImage();
    }
    
    @Test
    public void should_display_tooltip_for_multiple_query_without_count() throws Exception {
        WritableValue writableValue = new WritableValue();
        Query query = new Query("allEmp", "",List.class.getName());
        writableValue.setValue(BusinessObjectBuilder.aBO("Employee").withQuery(query).build());
        
        QueryStatusLabelProvider queryStatusLabelProvider = spy(new QueryStatusLabelProvider(writableValue));
        
        String toolTipText = queryStatusLabelProvider.getToolTipText(query);
        
        assertThat(toolTipText).isEqualTo(Messages.bind(Messages.missingCountQuery,BDMQueryUtil.getCountQueryName("allEmp")));
    }
    
    @Test
    public void should_not_display_tooltip_for_multiple_query_with_count() throws Exception {
        WritableValue writableValue = new WritableValue();
        Query query = new Query("allEmp", "",List.class.getName());
        writableValue.setValue(BusinessObjectBuilder.aBO("Employee").withQuery(query)
                .withQuery(new Query(BDMQueryUtil.getCountQueryName("allEmp"), "", Long.class.getName())).build());
        
        QueryStatusLabelProvider queryStatusLabelProvider = spy(new QueryStatusLabelProvider(writableValue));
        
        String toolTipText = queryStatusLabelProvider.getToolTipText(query);
        
        assertThat(toolTipText).isNullOrEmpty();
    }
    
    @Test
    public void should_not_display_tooltip_for_single_query() throws Exception {
        WritableValue writableValue = new WritableValue();
        Query query = new Query("allEmp", "",Long.class.getName());
        writableValue.setValue(BusinessObjectBuilder.aBO("Employee").withQuery(query).build());
        
        QueryStatusLabelProvider queryStatusLabelProvider = spy(new QueryStatusLabelProvider(writableValue));
        
        String toolTipText = queryStatusLabelProvider.getToolTipText(query);
        
        assertThat(toolTipText).isNullOrEmpty();
    }
}
