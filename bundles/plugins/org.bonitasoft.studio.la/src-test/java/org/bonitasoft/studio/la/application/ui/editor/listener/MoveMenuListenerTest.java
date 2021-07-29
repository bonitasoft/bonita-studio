package org.bonitasoft.studio.la.application.ui.editor.listener;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.la.application.ui.editor.ApplicationNavigation;
import org.bonitasoft.studio.la.application.ui.editor.NavigationPageNode;
import org.bonitasoft.studio.la.application.ui.editor.listener.MoveMenuListener;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Event;
import org.junit.Rule;
import org.junit.Test;

public class MoveMenuListenerTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    @Test
    public void should_up_direct_menu() {
        final IViewerObservableValue menuSelectionObservable = mock(IViewerObservableValue.class);
        final ApplicationNavigation applicationNavigation = mock(ApplicationNavigation.class);
        final TreeViewer viewer = mock(TreeViewer.class);
        when(applicationNavigation.getMenuTreeViewer()).thenReturn(viewer);
        final Event event = mock(Event.class);

        final List<NavigationPageNode> navigationPageNodeList = new ArrayList<>();
        final NavigationPageNode node1 = new NavigationPageNode("menu1", "", "");
        final NavigationPageNode node2 = new NavigationPageNode("menu2", "", "");
        navigationPageNodeList.add(node1);
        navigationPageNodeList.add(node2);
        when(menuSelectionObservable.getValue()).thenReturn(node2);
        final WritableList menuInputList = new WritableList(navigationPageNodeList, NavigationPageNode.class);
        menuInputList.addChangeListener(applicationNavigation::valueChangeListener);
        final MoveMenuListener listener = spy(new MoveMenuListener(-1, menuSelectionObservable, menuInputList,
                applicationNavigation));
        doNothing().when(listener).updateFocus();

        assertThat(navigationPageNodeList.get(0)).isEqualTo(node1);
        listener.handleEvent(event);
        assertThat(navigationPageNodeList.get(0)).isEqualTo(node2);
        verify(applicationNavigation).valueChangeListener(any());
    }

    @Test
    public void should_down_direct_menu() {
        final IViewerObservableValue menuSelectionObservable = mock(IViewerObservableValue.class);
        final ApplicationNavigation applicationNavigation = mock(ApplicationNavigation.class);
        final TreeViewer viewer = mock(TreeViewer.class);
        when(applicationNavigation.getMenuTreeViewer()).thenReturn(viewer);
        final Event event = mock(Event.class);

        final List<NavigationPageNode> navigationPageNodeList = new ArrayList<>();
        final NavigationPageNode node1 = new NavigationPageNode("menu1", "", "");
        final NavigationPageNode node2 = new NavigationPageNode("menu2", "", "");
        navigationPageNodeList.add(node1);
        navigationPageNodeList.add(node2);
        when(menuSelectionObservable.getValue()).thenReturn(node1);
        final WritableList menuInputList = new WritableList(navigationPageNodeList, NavigationPageNode.class);
        menuInputList.addChangeListener(applicationNavigation::valueChangeListener);
        final MoveMenuListener listener = spy(new MoveMenuListener(1, menuSelectionObservable, menuInputList,
                applicationNavigation));
        doNothing().when(listener).updateFocus();
        assertThat(navigationPageNodeList.get(1)).isEqualTo(node2);
        listener.handleEvent(event);
        assertThat(navigationPageNodeList.get(1)).isEqualTo(node1);
        verify(applicationNavigation).valueChangeListener(any());
    }

    @Test
    public void should_up_sub_menu() {
        final IViewerObservableValue menuSelectionObservable = mock(IViewerObservableValue.class);
        final TreeViewer viewer = mock(TreeViewer.class);
        final ApplicationNavigation applicationNavigation = mock(ApplicationNavigation.class);
        when(applicationNavigation.getMenuTreeViewer()).thenReturn(viewer);
        when(applicationNavigation.getDisplay()).thenReturn(realmWithDisplay.getShell().getDisplay());
        final Event event = mock(Event.class);

        final List<NavigationPageNode> navigationPageNodeList = new ArrayList<>();
        final NavigationPageNode node1 = new NavigationPageNode("menu1", "", "");
        final NavigationPageNode node2 = new NavigationPageNode("menu2", "", "");
        final NavigationPageNode node3 = new NavigationPageNode("menu3", "", "");
        final NavigationPageNode node4 = new NavigationPageNode("menu4", "", "");
        node2.addChild(node3);
        node2.addChild(node4);
        navigationPageNodeList.add(node1);
        navigationPageNodeList.add(node2);
        when(menuSelectionObservable.getValue()).thenReturn(node4);
        final WritableList menuInputList = new WritableList(navigationPageNodeList, NavigationPageNode.class);
        menuInputList.addChangeListener(applicationNavigation::valueChangeListener);
        final MoveMenuListener listener = spy(new MoveMenuListener(-1, menuSelectionObservable, menuInputList,
                applicationNavigation));
        doNothing().when(listener).updateFocus();
        assertThat(navigationPageNodeList.get(1)).isEqualTo(node2);
        assertThat(node2.getChildren().get(0)).isEqualTo(node3);
        listener.handleEvent(event);
        assertThat(navigationPageNodeList.get(1)).isEqualTo(node2);
        assertThat(node2.getChildren().get(0)).isEqualTo(node4);
        verify(applicationNavigation).valueChangeListener(any());
    }

    @Test
    public void should_down_sub_menu() {
        final IViewerObservableValue menuSelectionObservable = mock(IViewerObservableValue.class);
        final TreeViewer viewer = mock(TreeViewer.class);
        final ApplicationNavigation applicationNavigation = mock(ApplicationNavigation.class);
        when(applicationNavigation.getMenuTreeViewer()).thenReturn(viewer);
        when(applicationNavigation.getDisplay()).thenReturn(realmWithDisplay.getShell().getDisplay());
        final Event event = mock(Event.class);

        final List<NavigationPageNode> navigationPageNodeList = new ArrayList<>();
        final NavigationPageNode node1 = new NavigationPageNode("menu1", "", "");
        final NavigationPageNode node2 = new NavigationPageNode("menu2", "", "");
        final NavigationPageNode node3 = new NavigationPageNode("menu3", "", "");
        final NavigationPageNode node4 = new NavigationPageNode("menu4", "", "");
        node2.addChild(node3);
        node2.addChild(node4);
        navigationPageNodeList.add(node1);
        navigationPageNodeList.add(node2);
        when(menuSelectionObservable.getValue()).thenReturn(node3);
        final WritableList menuInputList = new WritableList(navigationPageNodeList, NavigationPageNode.class);
        menuInputList.addChangeListener(applicationNavigation::valueChangeListener);
        final MoveMenuListener listener = spy(new MoveMenuListener(1, menuSelectionObservable, menuInputList,
                applicationNavigation));
        doNothing().when(listener).updateFocus();

        assertThat(navigationPageNodeList.get(1)).isEqualTo(node2);
        assertThat(node2.getChildren().get(1)).isEqualTo(node4);
        listener.handleEvent(event);
        assertThat(navigationPageNodeList.get(1)).isEqualTo(node2);
        assertThat(node2.getChildren().get(1)).isEqualTo(node3);
        verify(applicationNavigation).valueChangeListener(any());
    }

}
