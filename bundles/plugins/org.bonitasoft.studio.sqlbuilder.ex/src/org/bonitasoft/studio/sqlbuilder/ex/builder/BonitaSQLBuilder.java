/**
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.sqlbuilder.ex.builder;

import java.util.List;

import org.bonitasoft.studio.expression.editor.pattern.FakeEditorPart;
import org.bonitasoft.studio.expression.editor.pattern.PatternExpressionViewer;
import org.bonitasoft.studio.expression.editor.pattern.contentAssist.PatternExpressionCompletionProcessor;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.sqlbuilder.ex.Messages;
import org.bonitasoft.studio.sqlbuilder.ex.action.BonitaAddTableAction;
import org.bonitasoft.studio.sqlbuilder.ex.builder.viewer.BonitaDesignViewer;
import org.bonitasoft.studio.sqlbuilder.ex.builder.viewer.BonitaSQLSourceViewer;
import org.bonitasoft.studio.sqlbuilder.ex.builder.viewer.BonitaSQLSourceViewerConfiguration;
import org.bonitasoft.studio.sqlbuilder.ex.builder.viewer.provider.QueryStatementContentProvider;
import org.bonitasoft.studio.sqlbuilder.ex.builder.viewer.provider.QueryStatementLabelProvider;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilder;
import org.eclipse.datatools.sqltools.sqlbuilder.model.IWindowStateInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.views.CustomSashForm;
import org.eclipse.datatools.sqltools.sqlbuilder.views.source.SQLEditorDocumentProvider;
import org.eclipse.datatools.sqltools.sqlbuilder.views.source.SQLPartitionScanner;
import org.eclipse.datatools.sqltools.sqlbuilder.views.source.SQLSourceViewerConfiguration;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

/**
 * @author Romain Bioteau
 */
public class BonitaSQLBuilder extends SQLBuilder {

    private ComboViewer selectTypeCombo;
    private Composite mainComposite;
    private TabFolder tabFolder;
    private TabItem designerTab;
    private TabItem sourceTab;
    private BonitaAddTableAction addTableAction;
    private Composite designerComposite;
    private final List<Expression> filteredExpressions;

    public BonitaSQLBuilder(List<Expression> filteredExpressions) {
        this.filteredExpressions = filteredExpressions;
        _sqlDomainModel = new BonitaSQLDomainModel();
        _sqlDomainModel.setEditingDomain(_editingDomain);
    }

    /**
     * Creates the Design Viewer
     */
    @Override
    protected void createDesignViewer(Composite client) {

        _designViewer = new BonitaDesignViewer(_sqlDomainModel, filteredExpressions, client);

        final GridData data = new GridData();
        data.verticalAlignment = GridData.FILL;
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        data.grabExcessVerticalSpace = true;
        _designViewer.setLayoutData(data);

    }

    /**
     * Creates the Source Viewer
     */
    @Override
    protected void createSourceViewer(Composite client) {
        _sourceViewer = new BonitaSQLSourceViewer(_sqlDomainModel, client, true);
        BonitaSQLSourceViewerConfiguration bonitaSQLSourceViewerConfiguration = new BonitaSQLSourceViewerConfiguration(filteredExpressions);
        ((BonitaSQLSourceViewer) _sourceViewer).setConfiguration(bonitaSQLSourceViewerConfiguration);
        ((BonitaSQLSourceViewer) _sourceViewer).getSourceViewer().configure(bonitaSQLSourceViewerConfiguration);
        
        _sourceViewer.setContentChangeListener(this);
        _sourceViewer.initDBContext();
        _sourceViewer.setContentProvider(_sqlDomainModel.createContentProvider());
        _sourceViewer.setSQLBuilder(this);

        final GridData data = new GridData();
        data.verticalAlignment = GridData.FILL;
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        data.grabExcessVerticalSpace = true;
        _sourceViewer.getControl().getParent().setLayoutData(data);
    }

    @Override
    protected void createGraphViewer(Composite client) {
        _graphControl = new BonitaGraphControl(_sqlDomainModel, false);
        _graphControl.createControl(client);

        final GridData data = new GridData();
        data.verticalAlignment = GridData.FILL;
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        data.grabExcessVerticalSpace = true;
        _graphControl.getControl().setLayoutData(data);

    }

    @Override
    public void createClient(Composite parent) {
        _inCreateClient = true;
        _parentControl = parent;

        mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(new GridLayout(2, false));
        mainComposite.setLayoutData(ViewUtility.createFill());

        new Label(mainComposite, SWT.NONE).setText("Query Type"); //$NON-NLS-1$
        selectTypeCombo = new ComboViewer(mainComposite, SWT.READ_ONLY);
        selectTypeCombo.getCombo().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        selectTypeCombo.setContentProvider(new QueryStatementContentProvider());
        selectTypeCombo.setLabelProvider(new QueryStatementLabelProvider());
        selectTypeCombo.setInput(new Object());
        selectTypeCombo.setSelection(new StructuredSelection(StatementHelper.getStatementType(getDomainModel().getSQLStatement())));
        selectTypeCombo.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                changeStatementType((Integer) ((StructuredSelection) selectTypeCombo.getSelection()).getFirstElement());
            }
        });

        tabFolder = new TabFolder(mainComposite, SWT.NONE);
        designerTab = new TabItem(tabFolder, SWT.NONE, 0);
        designerTab.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(Event arg0) {
                System.out.println(arg0.type);

            }
        });
        designerTab.setText(Messages.designerTabLabel);
        sourceTab = new TabItem(tabFolder, SWT.NONE, 1);
        sourceTab.setText(Messages.queryTabLabel);

        designerComposite = new Composite(tabFolder, SWT.NONE);
        designerComposite.setLayout(new GridLayout(1, false));
        final Button addTableButton = new Button(designerComposite, SWT.FLAT);
        addTableButton.setText(Messages.addTableLabel);
        addTableAction = new BonitaAddTableAction(_sqlDomainModel);
        addTableButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                if (getDomainModel().getSQLStatement() != null) {
                    addTableAction.setElement(getDomainModel().getSQLStatement());
                    addTableAction.run();
                }
            }
        });

        _sashMain = new CustomSashForm(designerComposite, SWT.VERTICAL, CustomSashForm.NO_HIDE_UP);
        _sashMain.setLayoutData(new GridData(GridData.FILL_BOTH));

        final GridData gd = new GridData(GridData.FILL_BOTH);
        gd.horizontalSpan = 2;
        tabFolder.setLayoutData(gd);

        int height = DEFAULT_SASHMAIN_HEIGHT;
        int width = DEFAULT_SASHMAIN_WIDTH;
        if (_sqlBuilderEditorInput != null) {
            final IWindowStateInfo winState = _sqlBuilderEditorInput.getWindowStateInfo();
            if (winState != null) {
                height = winState.getHeight() <= 0 ? height : winState.getHeight();
                width = winState.getWidth() <= 0 ? width : winState.getWidth();
            }
        }

        _sashMain.setSize(width, height);

        if (_inputLoaded) {
            doCreateClient();

        }
        designerTab.setControl(designerComposite);
        _inCreateClient = false;
    }

    /*
     * The part of CreateClient that depends on the input having been loaded
     */
    private void doCreateClient() {

        _sashSourceGraph = new CustomSashForm(_sashMain, SWT.VERTICAL);
        _sashSourceGraph.setLayoutData(ViewUtility.createFill());

        final Composite graphComposite = ViewUtility.createNestedComposite(
                _sashSourceGraph, SWT.BORDER);

        createGraphViewer(graphComposite);

        final Composite designComposite = ViewUtility.createNestedComposite(
                _sashMain, SWT.BORDER);
        createDesignViewer(designComposite);

        // composite for source & label to go on sash
        final Composite outsideSrcComp = ViewUtility.createNestedComposite(tabFolder,
                SWT.NONE);
        // composite for source viewer to add border
        final Composite srcComposite = ViewUtility.createNestedComposite(
                outsideSrcComp, SWT.BORDER);
        createSourceViewer(srcComposite);
        sourceTab.setControl(outsideSrcComp);

        // set _clientCreated before getting the ContentOutlinePage
        _clientCreated = true;

        if (_sashGraphOutline != null) {
            final Composite contentOutlineComposite = ViewUtility.createNestedComposite(
                    _sashGraphOutline, SWT.BORDER);
            getContentOutlinePage(contentOutlineComposite); // make sure everything is initialized
        } else {
            getContentOutlinePage(_sashGraphOutline); // make sure everything is initialized
        }

        //  set weights of _sashGraphOutline
        if (_sashGraphOutline != null) {
            _sashGraphOutline.setWeights(new int[] { DEFAULT_SASHGRAPHOUTLINE_WEIGHT1, DEFAULT_SASHGRAPHOUTLINE_WEIGHT2 });
        }
        if (_sashMain != null) {
            _sashMain.setWeights(new int[] { 1, 2 });
        }
        //		}

        ((IChangeNotifier) getDomainModel().getAdapterFactory())
                .addListener(new INotifyChangedListener() {

                    // public void notifyChanged(Object object, int eventType,
                    // Object
                    // feature, Object oldValue, Object newValue, int index)
                    @Override
                    public void notifyChanged(Notification msg) {
                        if (Display.getCurrent() != null) {
                            Display.getCurrent().asyncExec(new Runnable() {

                                @Override
                                public void run() {
                                    notifyContentChange();
                                }
                            });
                        }
                    }
                });

        final boolean isProper = _sqlDomainModel.isProper();
        updateProperStatement(isProper);
        _graphControl.refresh();
        _graphControl.setSQLBuilder(this);

        if (!_inputLoaded) {
            _sourceViewer.revertToDefaultSource();
        }
        _sourceViewer.setTextDirty(true);
    }

    /**
     * Changes the statement type in the SQLBuilder by using the statement template for the
     * specified type.
     * Statement type must be a constant from the StatementHelper class
     * 
     * @param statementType
     */
    @Override
    public void changeStatementType(int statementType) {
        // Check that statement type is changing
        if (statementType != StatementHelper.getStatementType(_sqlDomainModel.getSQLStatement())) {
            // Make sure graphControl is visible and enabled first
            if (!_graphControl.getControl().isVisible()) {
                _graphControl.getControl().setVisible(true);
                if (_sashGraphOutline != null)
                    _sashGraphOutline.layout(true);
            }
            if (!_graphControl.getControl().isEnabled()) {
                _graphControl.getControl().setEnabled(true);
                if (_sashGraphOutline != null)
                    _sashGraphOutline.layout(true);
            }

            // Reset the statement type in the domainModel
            _sqlDomainModel.initializeFromType(statementType);
            _sqlDomainModel.clearStatementToTemplate();

            // Reset the SQLTreeViewer/OutlineView input
            getSQLTreeViewer().resetInput(_sqlDomainModel.getSQLStatement());

            // Calling handleContentOutlineSelection resets the graph, design and source viewers
            handleContentOutlineSelection(
                    new StructuredSelection(_sqlDomainModel.getSQLStatement()), false);
        }
    }

}
