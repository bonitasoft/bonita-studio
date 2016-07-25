package org.bonitasoft.studio.importer.builder;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class ProcBuilderTest {

    private ProcBuilder procBuilder;


    @Before
    public void setup(){
        procBuilder = spy(new ProcBuilder());
    }

    @Test
    public void shoud_set_label_on_sequence_flow() throws ProcBuilderException{
        final View currentView = mock(View.class);
        final EList list = mock(EList.class) ;
        final Node node = mock(Node.class);
        final Location constraint = mock(Location.class);
        when(currentView.getChildren()).thenReturn(list);
        when(list.isEmpty()).thenReturn(false);
        when(list.get(0)).thenReturn(node);
        when(node.getLayoutConstraint()).thenReturn(constraint);
        final Point point = new Point(400,500);
        doNothing().when(procBuilder).setLabelPosition(new Point(400,500),node);
        procBuilder.setCurrentView(currentView);
        procBuilder.setLabelPositionOnSequenceFlowOrEvent(new Point(400,500));
        verify(procBuilder).setLabelPosition(point, node);

    }
   }
