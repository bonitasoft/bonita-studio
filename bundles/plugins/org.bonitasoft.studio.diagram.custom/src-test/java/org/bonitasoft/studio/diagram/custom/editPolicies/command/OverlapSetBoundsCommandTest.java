/**
 * Copyright (C) 2015 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.diagram.custom.editPolicies.command;

import static org.bonitasoft.studio.assertions.StatusAssert.assertThat;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.eclipse.core.internal.registry.RegistryProviderFactory;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.spi.IRegistryProvider;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.internal.CommonCorePlugin;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

@RunWith(MockitoJUnitRunner.class)
public class OverlapSetBoundsCommandTest {

    @Mock
    private GraphicalEditPart editPart;
    @Mock
    private EditPart host;
    @Mock
    private IAdaptable adapter;
    @Mock
    private BundleContext bundleContext;
    @Mock
    private Bundle bundle;
    @Mock
    private IRegistryProvider provider;
    @Mock
    private IExtensionRegistry extensionRegistry;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        if (RegistryProviderFactory.getDefault() == null) {
            RegistryProviderFactory.setDefault(provider);
        }
        when(provider.getRegistry()).thenReturn(extensionRegistry);
        when(extensionRegistry.getConfigurationElementsFor(anyString(), anyString())).thenReturn(new IConfigurationElement[0]);
        doReturn(bundle).when(bundleContext).getBundle();
        doReturn("org.eclipse.gmf.runtime.common.core").when(bundle).getSymbolicName();
        final CommonCorePlugin commonCorePlugin = new CommonCorePlugin();
        commonCorePlugin.start(bundleContext);
    }

    @Test
    public void should_cancel_command_if_new_bound_overlap_an_existing_editpart() throws Exception {
        //Given
        final Rectangle bounds = new Rectangle(10, 10, 100, 50);
        final OverlapSetBoundsCommand overlapSetBoundsCommand = spy(new OverlapSetBoundsCommand(null, editPart, host, adapter, bounds));
        doReturn(new Point(10, 15)).when(overlapSetBoundsCommand).findValidLocation(bounds.getLocation());

        //When
        final CommandResult commandResult = overlapSetBoundsCommand.doExecuteWithResult(null, null);

        //Then
        assertThat(commandResult.getStatus()).hasSeverity(IStatus.CANCEL);
    }

    @Test
    public void should_execute_command_if_new_bound__does_not_overlap_an_existing_editpart() throws Exception {
        //Given
        final Rectangle bounds = new Rectangle(10, 10, 100, 50);
        final OverlapSetBoundsCommand overlapSetBoundsCommand = spy(new OverlapSetBoundsCommand(null, editPart, host, adapter, bounds));
        doReturn(new Point(10, 10)).when(overlapSetBoundsCommand).handleMargins(bounds.getLocation());

        //When
        final CommandResult commandResult = overlapSetBoundsCommand.doExecuteWithResult(null, null);

        //Then
        assertThat(commandResult.getStatus()).isOK();
    }
}
