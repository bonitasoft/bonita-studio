/**
 * Copyright (C) 2011-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.preferences.dialog;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.studio.common.jface.BonitaStudioFontRegistry;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.PreferenceUtil;
import org.bonitasoft.studio.preferences.i18n.Messages;
import org.eclipse.e4.ui.css.swt.resources.SWTResourcesRegistry;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.IPreferencePage;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.dialogs.WorkbenchPreferenceDialog;
import org.eclipse.ui.internal.misc.StringMatcher;
import org.eclipse.ui.internal.preferences.WorkbenchPreferenceExtensionNode;
import org.eclipse.ui.themes.ColorUtil;

public class BonitaPreferenceDialog extends Dialog {

    private static final int SHELL_HEIGHT = 530;
    private static final int SHELL_WIDTH = 800;
    private static final int MARGIN_RIGHT = 80;
    private static final int MARGIN_LEFT = 25;
    private static final int LABEL_WIDTH = 180;

    private static final int SECTION_TITLE_MARGIN = -20;
    public static final String DATABASE_PAGE_ID = "org.bonitasoft.studio.preferences.database"; //$NON-NLS-1$
    public static final String APPEARANCE_PAGE_ID = "org.bonitasoft.studio.preferences.appearance"; //$NON-NLS-1$
    public static final String LANGUAGE_PAGE_ID = "org.bonitasoft.studio.preferences.language"; //$NON-NLS-1$
    public static final String JAVA_PAGE_ID = "org.eclipse.jdt.debug.ui.preferences.VMPreferencePage"; //$NON-NLS-1$
    public static final String RUN_DEPLOY_MODE_PAGE_ID = "org.bonitasoft.studio.preferences.run"; //$NON-NLS-1$
    public static final String SERVER_SETTINGS_PAGE_ID = "org.bonitasoft.studio.preferences.server"; //$NON-NLS-1$
    public static final String DB_CONNECTORS_PAGE_ID = "org.bonitasoft.studio.preferences.dbconnectors";//$NON-NLS-1$
    public static final String REMOTE_ENGINE_PAGE_ID = "org.bonitasoft.studio.preferences.remoteEngine"; //$NON-NLS-1$
    public static final String WEB_BROWSER_PAGE_ID = "org.eclipse.ui.browser.preferencePage"; //$NON-NLS-1$
    public static final String PROXY_PAGE_ID = "org.eclipse.ui.net.custom.NetPreferences"; //$NON-NLS-1$
    public static final String ADVANCED_PAGE_ID = "org.bonitasoft.studio.preferences.advanced"; //$NON-NLS-1$
    public static final String ECLIPSE_PAGE_ID = "eclipse.page"; //$NON-NLS-1$;
    private static final String COLOR = "color";
    private static final String DARKER = "darker";
    private static final String LIGHTER = "lighter";

    private final Map keywordCache = new HashMap();
    private final Map<String, ToolItem> itemPerPreferenceNode = new HashMap<String, ToolItem>() ;
    private final Map<String, Label> labelPerPreferenceNode = new HashMap<String, Label>() ;
    private StackLayout stack;
    private Composite mainComposite;
    private Composite menuComposite;
    private Composite preferencePageComposite;
    private Button btnDisplay;
    private StringMatcher matcher;

    private final List<IPreferencePage> applyOnBack;

    private static String[] pageIds = new String[] { DATABASE_PAGE_ID,
        APPEARANCE_PAGE_ID,LANGUAGE_PAGE_ID,JAVA_PAGE_ID,RUN_DEPLOY_MODE_PAGE_ID,RUN_DEPLOY_MODE_PAGE_ID,
            SERVER_SETTINGS_PAGE_ID, DB_CONNECTORS_PAGE_ID, REMOTE_ENGINE_PAGE_ID, WEB_BROWSER_PAGE_ID, PROXY_PAGE_ID,
            ADVANCED_PAGE_ID,
            ECLIPSE_PAGE_ID };
    private SWTResourcesRegistry swtResourcesRegistry;

    /**
     * Create the dialog.
     * @param parentShell
     */
    public BonitaPreferenceDialog(final Shell parentShell) {
        super(parentShell);
        setShellStyle(SWT.CLOSE | SWT.BORDER | SWT.APPLICATION_MODAL);
        applyOnBack = new ArrayList<>();
        swtResourcesRegistry = new SWTResourcesRegistry(parentShell.getDisplay());
    }

    @Override
    public boolean close() {
        for(final IPreferencePage page : applyOnBack){
            page.performOk() ;
        }
        applyOnBack.clear() ;
        return super.close() ;
    }


    /**
     * Create contents of the dialog.
     * @param parent
     */
    @Override
    protected Control createDialogArea(final Composite parent) {

        final Composite container = (Composite) super.createDialogArea(parent);
        final GridLayout gl_container = new GridLayout(1, false);
        gl_container.horizontalSpacing = 0;
        gl_container.verticalSpacing = 0;
        gl_container.marginWidth = 0;
        gl_container.marginHeight = 0;
        gl_container.marginBottom = 0;
        container.setLayout(gl_container);

        final Composite composite = new Composite(container, SWT.NONE);
        final GridLayout gl_composite = new GridLayout(2, false);
        gl_composite.marginTop = 10;
        gl_composite.marginRight = 10;
        gl_composite.marginLeft = 10;
        gl_composite.marginHeight = 0;
        gl_composite.verticalSpacing = 0;
        gl_composite.marginWidth = 0;
        gl_composite.marginBottom = 10;
        gl_composite.horizontalSpacing = 0;
        composite.setLayout(gl_composite);
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
        Color background = composite.getBackground();

        Color color = (Color) swtResourcesRegistry.getResource(COLOR, DARKER);
        if (color == null) {
            RGB blend = ColorUtil.blend(background.getRGB(),
                    Display.getDefault().getSystemColor(SWT.COLOR_DARK_GRAY).getRGB());
            color = new Color(composite.getDisplay(), blend);
            swtResourcesRegistry.registerResource(COLOR, DARKER, color);
        }
        composite.setBackground(color);


        btnDisplay = new Button(composite, SWT.PUSH );
        final GridData gd_btnDisplay = GridDataFactory.fillDefaults().align(SWT.LEFT,SWT.CENTER).indent(10,5).create();
        gd_btnDisplay.horizontalIndent = 0;
        gd_btnDisplay.verticalIndent = 0;
        btnDisplay.setLayoutData(gd_btnDisplay) ;
        btnDisplay.setText(Messages.BonitaPreferenceDialog_back);
        btnDisplay.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                for(final IPreferencePage page : applyOnBack){
                    page.performOk() ;
                }
                applyOnBack.clear();
                stack.topControl = menuComposite ;
                mainComposite.layout() ;
                btnDisplay.setEnabled(false) ;
                updateShellSize(true);
            }
        }) ;
        btnDisplay.setEnabled(false) ;

        createSearchField(composite);

        final Label separator = new Label(container,SWT.SEPARATOR | SWT.HORIZONTAL) ;
        separator.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

        mainComposite = new Composite(container, SWT.NONE);
        stack = new StackLayout() ;
        mainComposite.setLayout(stack);
        mainComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));


        menuComposite = createMenuComposite(mainComposite);
        preferencePageComposite = createPreferencePageComposite(mainComposite);

        stack.topControl = menuComposite ;
        mainComposite.layout() ;
        menuComposite.setFocus();


        return container;
    }

    protected void createSearchField(final Composite composite) {
        final Text searchTxt = new Text(composite, SWT.SEARCH |SWT.ICON_SEARCH | SWT.CANCEL ) ;
        final GridData gd_searchTxt = GridDataFactory.fillDefaults().align(SWT.LEFT,SWT.CENTER).indent(10,5).create();
        gd_searchTxt.grabExcessHorizontalSpace = true;
        gd_searchTxt.verticalIndent = 0;
        gd_searchTxt.horizontalAlignment = SWT.RIGHT;
        gd_searchTxt.horizontalIndent = 0;
        gd_searchTxt.widthHint = 150;
        searchTxt.setLayoutData(gd_searchTxt);
        searchTxt.setText(Messages.BonitaPreferenceDialog_search) ;
        searchTxt.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY)) ;

        searchTxt.addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(final ModifyEvent e) {
                searchTxt.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_BLACK)) ;
                cleanHighlights() ;
                if(!searchTxt.getText().trim().isEmpty()){
                    filter(searchTxt.getText()) ;
                    updateShellSize(false) ;
                }
            }

        }) ;


        searchTxt.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseDown(final MouseEvent e) {
                if(searchTxt.getText().equals(Messages.BonitaPreferenceDialog_search)){
                    searchTxt.setText("") ;
                }
            }
        }) ;
    }

    protected void cleanHighlights() {
        for(final ToolItem item : itemPerPreferenceNode.values()){
            item.setEnabled(true) ;
        }
        for(final Label l : labelPerPreferenceNode.values()){
            l.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_BLACK)) ;
            l.setFont(BonitaStudioFontRegistry.getNormalFont()) ;
        }
    }

    protected void filter(final String text) {
        matcher = new StringMatcher(text, true, false) ;
        final Set<String> foundIds = new HashSet<String>();
        for(final String id : pageIds ){
            final IPreferenceNode node = PreferenceUtil.findNodeMatching(id);
            if(isLeafMatch(node)){
                foundIds.add(id) ;
            }
        }

        if(text.toLowerCase().contains("eclipse")){
            foundIds.add(ECLIPSE_PAGE_ID) ;
        }

        disableAllItems() ;
        if(!foundIds.isEmpty()){

            for(final String id : foundIds){
                highlight(id) ;
            }
        }

    }

    private void disableAllItems() {
        for(final ToolItem item : itemPerPreferenceNode.values()){
            item.setEnabled(false) ;
        }

        for(final Label l : labelPerPreferenceNode.values()){
            l.setForeground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY)) ;
            l.setFont(BonitaStudioFontRegistry.getNormalFont()) ;
        }
    }

    private void highlight(final String id) {
        itemPerPreferenceNode.get(id).setEnabled(true) ;
        labelPerPreferenceNode.get(id).setForeground(Display.getDefault().getSystemColor(SWT.COLOR_BLACK)) ;
        labelPerPreferenceNode.get(id).setFont(BonitaStudioFontRegistry.getHighlightedFont()) ;

    }

    protected boolean wordMatches(final String text) {
        if (text == null) {
            return false;
        }

        //If the whole text matches we are all set
        if(match(text)) {
            return true;
        }

        // Otherwise check if any of the words of the text matches
        final String[] words = getWords(text);
        for (int i = 0; i < words.length; i++) {
            final String word = words[i];
            if (match(word)) {
                return true;
            }
        }

        return false;
    }

    private boolean match(final String string) {
        if (matcher == null) {
            return true;
        }
        return matcher.match(string);
    }

    protected boolean isLeafMatch(final IPreferenceNode element) {
        if(element != null){
            final IPreferenceNode node = element;
            final String text = node.getLabelText();

            if (wordMatches(text)) {
                return true;
            }

            // Also need to check the keywords
            final String[] keywords = getKeywords(node);
            for (int i = 0; i < keywords.length; i++){
                if (wordMatches(keywords[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    private String[] getWords(final String text){
        final List words = new ArrayList();
        final BreakIterator iter = BreakIterator.getWordInstance();
        iter.setText(text);
        int i = iter.first();
        while (i != java.text.BreakIterator.DONE && i < text.length()) {
            int j = iter.following(i);
            if (j == java.text.BreakIterator.DONE) {
                j = text.length();
            }
            // match the word
            if (Character.isLetterOrDigit(text.charAt(i))) {
                final String word = text.substring(i, j);
                words.add(word);
            }
            i = j;
        }
        return (String[]) words.toArray(new String[words.size()]);
    }

    protected Composite createPreferencePageComposite(final Composite parent) {
        final Composite preferencePageComposite = new Composite(parent,SWT.NONE) ;
        final FillLayout fl = new FillLayout(SWT.HORIZONTAL);
        fl.marginWidth = 10 ;
        fl.marginHeight = 10 ;
        preferencePageComposite.setLayout(fl) ;
        preferencePageComposite.setLayoutData(GridDataFactory.fillDefaults().create()) ;
        return preferencePageComposite;
    }

    @Override
    protected void configureShell(final Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(Messages.BonitaPreferenceDialog_preferences) ;
    }

    protected Composite createMenuComposite(final Composite parent) {
        final Composite menuComposite = new Composite(parent, SWT.NONE);
        final GridLayout gl_menuComposite = new GridLayout(1, false);
        gl_menuComposite.marginWidth = 0;
        gl_menuComposite.marginHeight = 0;
        gl_menuComposite.horizontalSpacing = 0;
        gl_menuComposite.verticalSpacing = 0;
        gl_menuComposite.marginBottom = 15 ;
        menuComposite.setLayout(gl_menuComposite);

        createGeneralCategoryLine(menuComposite);
        createSeparator(menuComposite) ;
        createDeploymentCategoryLine(menuComposite);
        createSeparator(menuComposite) ;
        createWebCategoryLine(menuComposite);
        createSeparator(menuComposite) ;
        createOtherCategoryLine(menuComposite);

        return menuComposite;
    }

    protected void createGeneralCategoryLine(final Composite menuComposite) {
        Color color = (Color) swtResourcesRegistry.getResource(COLOR, LIGHTER);
        if (color == null) {
            RGB rgb = menuComposite.getBackground().getRGB();
            RGB blend = ColorUtil.blend(rgb, Display.getDefault().getSystemColor(SWT.COLOR_WHITE).getRGB(), 70);
            color = new Color(Display.getDefault(), blend);
            swtResourcesRegistry.registerResource(COLOR, DARKER, color);
        }

        Composite generalRow = createRow(menuComposite, color, Messages.BonitaPreferenceDialog_general, 4);

        final ToolItem tltmDatabase = createTool(generalRow, color, Pics.getImage(PicsConstants.preferenceDB),
                Pics.getImage(PicsConstants.preferenceDBdisabled), DATABASE_PAGE_ID);
        final ToolItem tltmAppearance = createTool(generalRow, color, Pics.getImage(PicsConstants.preferenceAppearance),
                Pics.getImage(PicsConstants.preferenceAppearancedisabled), APPEARANCE_PAGE_ID);
        final ToolItem tltmLanguage = createTool(generalRow, color, Pics.getImage(PicsConstants.preferenceLanguage),
                Pics.getImage(PicsConstants.preferenceLanguagedisabled), LANGUAGE_PAGE_ID);
        final ToolItem tltmJava = createTool(generalRow, color, Pics.getImage(PicsConstants.preferenceJava),
                Pics.getImage(PicsConstants.preferenceJavadisabled), JAVA_PAGE_ID);

        final Label lblDatabase = createItemLabel(generalRow, color, Messages.BonitaPreferenceDialog_database);

        itemPerPreferenceNode.put(DATABASE_PAGE_ID, tltmDatabase) ;
        labelPerPreferenceNode.put(DATABASE_PAGE_ID, lblDatabase) ;


        final Label lblAppearance = createItemLabel(generalRow, color, Messages.BonitaPreferenceDialog_appearance);


        itemPerPreferenceNode.put(APPEARANCE_PAGE_ID, tltmAppearance) ;
        labelPerPreferenceNode.put(APPEARANCE_PAGE_ID, lblAppearance) ;

        final Label lblLanguage = createItemLabel(generalRow, color, Messages.BonitaPreferenceDialog_language);


        itemPerPreferenceNode.put(LANGUAGE_PAGE_ID, tltmLanguage) ;
        labelPerPreferenceNode.put(LANGUAGE_PAGE_ID, lblLanguage) ;

        final Label lblJava = createItemLabel(generalRow, color, Messages.BonitaPreferenceDialog_Java);

        itemPerPreferenceNode.put(JAVA_PAGE_ID, tltmJava) ;
        labelPerPreferenceNode.put(JAVA_PAGE_ID,lblJava) ;
    }

    protected void createDeploymentCategoryLine(final Composite menuComposite) {
        final Composite deploymentRow = createRow(menuComposite, null, Messages.BonitaPreferenceDialog_Deployment, 4) ;

        final ToolItem tltmRunMode = createTool(deploymentRow, null, Pics.getImage(PicsConstants.preferenceDeploy), Pics.getImage(PicsConstants.preferenceDeploydisabled), RUN_DEPLOY_MODE_PAGE_ID) ;
        final ToolItem tltmUserxpSettings = createTool(deploymentRow, null, Pics.getImage(PicsConstants.preferenceLogin), Pics.getImage(PicsConstants.preferenceLogindisabled), SERVER_SETTINGS_PAGE_ID) ;
        final ToolItem tltmDBConnectors = createTool(deploymentRow, null, Pics.getImage(PicsConstants.preferenceAdvanced), Pics.getImage(PicsConstants.preferenceAdvanceddisabled), DB_CONNECTORS_PAGE_ID) ;


        if(PreferenceUtil.findNodeMatching(REMOTE_ENGINE_PAGE_ID) != null){
            final ToolItem tltmRemoteEngine = createTool(deploymentRow, null, Pics.getImage(PicsConstants.preferenceRemote), Pics.getImage(PicsConstants.preferenceRemotedisabled), REMOTE_ENGINE_PAGE_ID) ;
            itemPerPreferenceNode.put(REMOTE_ENGINE_PAGE_ID, tltmRemoteEngine) ;
        }else{
            final ToolBar emptyToolbar = new ToolBar(deploymentRow, SWT.FLAT | SWT.TRANSPARENT);
            final GridData gd_toolBar_8 = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
            gd_toolBar_8.verticalIndent = 5;
            emptyToolbar.setLayoutData(gd_toolBar_8);
            emptyToolbar.setVisible(false) ;
        }


        final Label lblRunMode = createItemLabel(deploymentRow, null, Messages.BonitaPreferenceDialog_RunMode) ;

        itemPerPreferenceNode.put(RUN_DEPLOY_MODE_PAGE_ID, tltmRunMode) ;
        labelPerPreferenceNode.put(RUN_DEPLOY_MODE_PAGE_ID, lblRunMode) ;

        final Label lblUserxpSettings = createItemLabel(deploymentRow, null, Messages.BonitaPreferenceDialog_UserXP_Settings) ;

        itemPerPreferenceNode.put(SERVER_SETTINGS_PAGE_ID, tltmUserxpSettings) ;
        labelPerPreferenceNode.put(SERVER_SETTINGS_PAGE_ID, lblUserxpSettings) ;

        final Label lblDbConnectors = createItemLabel(deploymentRow, null, Messages.BonitaPreferenceDialog_DBConnectors);
        itemPerPreferenceNode.put(DB_CONNECTORS_PAGE_ID, tltmDBConnectors);
        labelPerPreferenceNode.put(DB_CONNECTORS_PAGE_ID, lblDbConnectors);

        if(PreferenceUtil.findNodeMatching(REMOTE_ENGINE_PAGE_ID) != null){
            final Label lblRemoteEngine =  createItemLabel(deploymentRow, null, Messages.BonitaPreferenceDialog_Remote_Engine) ;
            labelPerPreferenceNode.put(REMOTE_ENGINE_PAGE_ID, lblRemoteEngine) ;
        }else{
            new Label(deploymentRow, SWT.WRAP | SWT.CENTER);
        }
    }

    protected void createWebCategoryLine(final Composite menuComposite) {
        Color color = (Color) swtResourcesRegistry.getResource(COLOR, LIGHTER);
        if (color == null) {
            RGB rgb = menuComposite.getBackground().getRGB();
            RGB blend = ColorUtil.blend(rgb, Display.getDefault().getSystemColor(SWT.COLOR_WHITE).getRGB(), 70);
            color = new Color(Display.getDefault(), blend);
            swtResourcesRegistry.registerResource(COLOR, DARKER, color);
        }

        final Composite webRowComposite = createRow(menuComposite, color, Messages.BonitaPreferenceDialog_Web, 2);

        final ToolItem tltmBrowser = createTool(webRowComposite, color, Pics.getImage(PicsConstants.preferenceWeb),
                Pics.getImage(PicsConstants.preferenceWebdisabled), WEB_BROWSER_PAGE_ID);
        final ToolItem tltmProxy = createTool(webRowComposite, color, Pics.getImage(PicsConstants.preferenceProxy),
                Pics.getImage(PicsConstants.preferenceProxydisabled), PROXY_PAGE_ID);

        final Label lblBrowser = createItemLabel(webRowComposite, color, Messages.BonitaPreferenceDialog_Browser);

        itemPerPreferenceNode.put(WEB_BROWSER_PAGE_ID, tltmBrowser) ;
        labelPerPreferenceNode.put(WEB_BROWSER_PAGE_ID,lblBrowser) ;

        final Label lblProxy = createItemLabel(webRowComposite, color, Messages.BonitaPreferenceDialog_Proxy);

        itemPerPreferenceNode.put(PROXY_PAGE_ID, tltmProxy) ;
        labelPerPreferenceNode.put(PROXY_PAGE_ID,lblProxy) ;
    }

    protected void createOtherCategoryLine(final Composite menuComposite) {
        final Composite otherRowComposite = createRow(menuComposite, null, Messages.BonitaPreferenceDialog_Other, 2);

        final ToolItem tltmAdvancedSettings = createTool(otherRowComposite, null, Pics.getImage(PicsConstants.preferenceAdvanced), Pics.getImage(PicsConstants.preferenceAdvanceddisabled), ADVANCED_PAGE_ID) ;

        final ToolItem eclipseItem = createTool(otherRowComposite, null,  Pics.getImage(PicsConstants.preferenceEclipse), Pics.getImage(PicsConstants.preferenceEclipseDisabled), null) ;
        eclipseItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {

                final Set<String> preferencesToShow = new HashSet<String>() ;
                for(final Object elem : PlatformUI.getWorkbench().getPreferenceManager().getElements(PreferenceManager.POST_ORDER)){
                    if(elem instanceof IPreferenceNode){
                        //REMOVE BONITA PREFS
                        if(!((IPreferenceNode)elem).getId().contains("org.bonitasoft")) {
                            preferencesToShow.add(((IPreferenceNode)elem).getId()) ;
                        }
                    }
                }

                final WorkbenchPreferenceDialog dialog = WorkbenchPreferenceDialog.createDialogOn(null, null) ;
                dialog.showOnly(preferencesToShow.toArray(new String[]{})) ;
                dialog.open();
            }
        });

        final Label lblAdvanced = createItemLabel(otherRowComposite, null, Messages.BonitaPreferenceDialog_Advanced) ;
        final Label eclipseLabel = createItemLabel(otherRowComposite, null, Messages.EclipsePreferences) ;

        putInItemPerPreferenceNode(ADVANCED_PAGE_ID, tltmAdvancedSettings);
        putInLabelPerPreferenceNode(ADVANCED_PAGE_ID, lblAdvanced);

        putInItemPerPreferenceNode(ECLIPSE_PAGE_ID, eclipseItem);
        putInLabelPerPreferenceNode(ECLIPSE_PAGE_ID, eclipseLabel);
    }

    protected void putInItemPerPreferenceNode(final String id, final ToolItem toolItem) {
        itemPerPreferenceNode.put(id, toolItem);
    }

    protected void putInLabelPerPreferenceNode(final String id, final Label label) {
        labelPerPreferenceNode.put(id, label);
    }

    protected void updateShellSize(final boolean restore) {
         getShell().setSize(getInitialSize()) ;
    }

    private void createSeparator(final Composite composite) {
        final Label label = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
        label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
    }

    protected Label createItemLabel(final Composite composite,final Color backgroundColor, final String text) {
        final Composite labelContainer = new Composite(composite, SWT.NONE);
        GridDataFactory.fillDefaults().hint(LABEL_WIDTH, SWT.DEFAULT).applyTo(labelContainer);
        final GridLayout layout = new GridLayout(1, true);
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        labelContainer.setLayout(layout);
        labelContainer.setBackground(backgroundColor);
        layout.marginBottom = 0;
        final Label label = new Label(labelContainer, SWT.CENTER);
        GridDataFactory.fillDefaults().grab(true, false).align(SWT.CENTER, SWT.CENTER).applyTo(label);
        label.setText(text);
        label.setBackground(backgroundColor) ;
        return label;
    }

    protected Composite createRow(final Composite menuComposite,final Color backgroundColor,final String rowTitle,final int nbItems) {
        final Composite composite =  new Composite(menuComposite, SWT.NONE);
        final GridLayout gl = new GridLayout(nbItems, true);
        gl.verticalSpacing = 0;
        gl.marginLeft = MARGIN_LEFT;
        gl.horizontalSpacing = 20;
        gl.marginRight = MARGIN_RIGHT;
        composite.setLayout(gl);
        composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

        composite.setBackground(backgroundColor) ;

        final Label sectionTitle = new Label(composite, SWT.NONE);
        final GridData gd_lblGeneral = new GridData(SWT.LEFT, SWT.CENTER, false, false, nbItems, 1);
        gd_lblGeneral.horizontalIndent = SECTION_TITLE_MARGIN;
        sectionTitle.setLayoutData(gd_lblGeneral);
        sectionTitle.setFont(BonitaStudioFontRegistry.getPreferenceTitleFont());
        sectionTitle.setText(rowTitle) ;
        sectionTitle.setBackground(backgroundColor) ;

        return composite;
    }

    protected ToolItem createTool(final Composite composite,final Color backgroundColor, final Image image,final Image disableImage,final String preferencePageId) {
        final ToolBar toolBar = new ToolBar(composite, SWT.FLAT);
        final GridData gd_toolBar = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
        gd_toolBar.verticalIndent = 5;
        toolBar.setLayoutData(gd_toolBar);
        toolBar.setBackground(backgroundColor) ;

        final ToolItem toolItem = new ToolItem(toolBar, SWT.PUSH | SWT.CENTER);
        toolItem.setImage(image) ;
        toolItem.setDisabledImage(disableImage) ;
        if(preferencePageId != null){
            toolItem.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(final SelectionEvent e) {
                    openPreferencePage(preferencePageId) ;
                }
            }) ;
        }
        return toolItem;
    }

    protected void openPreferencePage(final String pageId) {

        final IPreferenceNode node = PreferenceUtil.findNodeMatching(pageId) ;
        final IPreferencePage p = node.getPage();
        if(p != null){
            node.disposeResources();
        }
        node.createPage();


        for(final Control c : preferencePageComposite.getChildren()){
            c.dispose() ;
        }

        preferencePageComposite.pack(true) ;


        if(pageId.equals(JAVA_PAGE_ID)){
            final Composite parent = new Composite(preferencePageComposite, SWT.NONE) ;
            final GridLayout gl = new GridLayout(1,false) ;
            gl.verticalSpacing = 0 ;
            gl.marginHeight = 0 ;
            gl.marginTop = 0 ;
            parent.setLayout(gl) ;
            createTitleBar(parent, Messages.BonitaPreferenceDialog_Java, Pics.getImage(PicsConstants.preferenceJava)) ;
            node.getPage().createControl(parent) ;
            applyOnBack.add(node.getPage()) ;
        }else if(pageId.equals(WEB_BROWSER_PAGE_ID)){
            final Composite parent = new Composite(preferencePageComposite, SWT.NONE) ;

            final GridLayout gl = new GridLayout(1,false) ;
            gl.verticalSpacing = 0 ;
            gl.marginHeight = 0 ;
            gl.marginTop = 0 ;
            parent.setLayout(gl) ;


            createTitleBar(parent, Messages.BonitaPreferenceDialog_Browser, Pics.getImage(PicsConstants.preferenceWeb)) ;
            final Composite fillComposite =  new Composite(parent, SWT.NONE) ;
            fillComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
            fillComposite.setLayout(new FillLayout()) ;
            node.getPage().createControl(fillComposite) ;
        }else if(pageId.equals(PROXY_PAGE_ID)){
            final Composite parent = new Composite(preferencePageComposite, SWT.NONE) ;
            final GridLayout gl = new GridLayout(1,false) ;
            gl.verticalSpacing = 0 ;
            gl.marginHeight = 0 ;
            gl.marginTop = 0 ;
            parent.setLayout(gl) ;


            createTitleBar(parent, Messages.BonitaPreferenceDialog_Proxy, Pics.getImage(PicsConstants.preferenceProxy)) ;
            final Composite fillComposite =  new Composite(parent, SWT.NONE) ;
            fillComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
            fillComposite.setLayout(new FillLayout()) ;
            node.getPage().createControl(fillComposite) ;
        }else{
            node.getPage().createControl(preferencePageComposite) ;
        }



        stack.topControl = preferencePageComposite ;
        mainComposite.layout() ;
        btnDisplay.setEnabled(true) ;
        updateShellSize(false);
    }

    protected void createTitleBar(final Composite parent,final String titleLabel, final Image image) {

        final Composite composite = new Composite(parent, SWT.NONE) ;
        composite.setLayout(new GridLayout(2,false)) ;
        if(parent.getLayout() instanceof GridLayout){
            composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
        }
        final Label imageLabel = new Label(composite, SWT.NONE) ;
        imageLabel.setImage(image) ;

        final Label title = new Label(composite, SWT.NONE) ;
        title.setText(titleLabel) ;
        title.setFont(BonitaStudioFontRegistry.getPreferenceTitleFont()) ;

    }

    /**
     * Create contents of the button bar.
     * @param parent
     */
    @Override
    protected void createButtonsForButtonBar(final Composite parent) {

    }

    /**
     * Return the initial size of the dialog.
     */
    @Override
    protected Point getInitialSize() {
    	return new Point(SHELL_WIDTH, SHELL_HEIGHT);
    }


    /*
     * Return true if the given Object matches with any possible keywords that
     * have been provided. Currently this is only applicable for preference and
     * property pages.
     */
    private String[] getKeywords(final Object element) {
        final List keywordList = new ArrayList();
        if (element instanceof WorkbenchPreferenceExtensionNode) {
            final WorkbenchPreferenceExtensionNode workbenchNode = (WorkbenchPreferenceExtensionNode) element;

            Collection keywordCollection = (Collection) keywordCache
                    .get(element);
            if (keywordCollection == null) {
                keywordCollection = workbenchNode.getKeywordLabels();
                keywordCache.put(element, keywordCollection);
            }
            if (!keywordCollection.isEmpty()){
                final Iterator keywords = keywordCollection.iterator();
                while (keywords.hasNext()) {
                    keywordList.add(keywords.next());
                }
            }
        }
        return (String[]) keywordList.toArray(new String[keywordList.size()]);
    }

	public void setSelectedPreferencePage(final String pageId) {
		openPreferencePage(pageId);
	}

}
