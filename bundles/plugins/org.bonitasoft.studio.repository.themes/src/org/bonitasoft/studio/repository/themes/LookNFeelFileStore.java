/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.repository.themes;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.repository.themes.helper.BindingWrapper;
import org.bonitasoft.studio.repository.themes.helper.CSSUtil;
import org.bonitasoft.theme.ThemeDescriptorManager;
import org.bonitasoft.theme.css.CSSProperties;
import org.bonitasoft.theme.exception.ThemeDescriptorNotFoundException;
import org.bonitasoft.theme.model.Binding;
import org.bonitasoft.theme.model.ThemeDescriptor;
import org.bonitasoft.theme.model.ThemeType;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.AssertionFailedException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.ide.undo.MoveResourcesOperation;
import org.eclipse.ui.internal.wizards.datatransfer.ArchiveFileExportOperation;

/**
 * @author Romain Bioteau
 * @author Baptiste Mesta
 * 
 */
public abstract class LookNFeelFileStore extends AbstractFileStore implements IEditorInput {


    private static final String DOTDOT = "../";
    private static final String THEME_SERVLET_NEGATIVE_LOOKAHEAD = "(?!themeResource\\?theme\\=)";

    private Map<String, BindingWrapper> bindings;
    private final Map<String, CSSProperties> cssPropertiesFileCache = new HashMap<String, CSSProperties>();
    private final String[] urlThatWontBeReplaced = new String[] { "scripts/bonita.js", "scripts/changeCSS.js", "console.nocache.js", "pictures/favicon2.ico", "javascript:''" };
    private final Map<String,Boolean> existingResource = new HashMap<String,Boolean>();
    private IEditorPart editor;
    private ThemeDescriptor themeDesctiptor;

    public LookNFeelFileStore(final String fileName,IRepositoryStore store) {
        super(fileName, store)  ;
    }



    @Override
    public IFolder getResource() {
        return getParentStore().getResource().getFolder(getName()) ;
    }


    protected ThemeDescriptor getThemeDescriptor()  {
        if(themeDesctiptor == null){
            try {
                themeDesctiptor = getThemeDescriptorManager().getThemeDescriptor(getThemeDescriptorFile().getLocation().toFile());
            } catch (ThemeDescriptorNotFoundException e) {
                BonitaStudioLog.error(e) ;
            }
        }
        return themeDesctiptor ;
    }



    /*
     * (non-Javadoc)
     * 
     * @see
     * org.bonitasoft.studio.common.repository.AbstractRepositoryArtifact#getContent
     * ()
     */
    @Override
    public FileInputStream getContent() {
        try {
            final File tempFile;
            if(getName().length()<=3){
                tempFile = File.createTempFile(getName()+"___", ".zip", ProjectUtil.getBonitaStudioWorkFolder());
            }else{
                tempFile = File.createTempFile(getName(), ".zip", ProjectUtil.getBonitaStudioWorkFolder());
            }
            final IFolder resource = getResource();
            final IFolder tempFolder = getParentStore().getResource().getProject().getFolder("tmp");
            if(tempFolder.exists()){
                tempFolder.delete(true, Repository.NULL_PROGRESS_MONITOR);
            }
            if(resource != null){
                resource.refreshLocal(IResource.DEPTH_INFINITE, Repository.NULL_PROGRESS_MONITOR) ;
                resource.copy(tempFolder.getFullPath(), false, Repository.NULL_PROGRESS_MONITOR);

                replaceUrl(tempFolder);

                //zip it
                final ArchiveFileExportOperation exportOperation = new ArchiveFileExportOperation(Arrays.asList(tempFolder.members()), tempFile.getAbsolutePath());
                exportOperation.setCreateLeadupStructure(false);
                exportOperation.run(Repository.NULL_PROGRESS_MONITOR);
                //delete temp files
                tempFolder.delete(true, Repository.NULL_PROGRESS_MONITOR);
                final FileInputStream fis = new FileInputStream(tempFile);
                return fis;
            }
        } catch (final Exception e){
            BonitaStudioLog.error(e) ;
        }
        return null;
    }

    @Override
    public void export(String targetAbsoluteFilePath) {
        try{
            final IFolder resource = getResource();
            //first make a copy of the folder
            final IFolder tempFolder = resource.getProject().getFolder("tmp");
            if(tempFolder.exists()){
                tempFolder.delete(true, Repository.NULL_PROGRESS_MONITOR);
            }

            resource.refreshLocal(IResource.DEPTH_INFINITE, Repository.NULL_PROGRESS_MONITOR) ;
            resource.copy(tempFolder.getFullPath(), true, Repository.NULL_PROGRESS_MONITOR);
            tempFolder.refreshLocal(IResource.DEPTH_INFINITE, Repository.NULL_PROGRESS_MONITOR) ;

            //replace urls
            if(ThemeType.userXP.equals(getType())){
                replaceUrl(tempFolder);
            }

            targetAbsoluteFilePath = targetAbsoluteFilePath + File.separatorChar + getName() +"."+LookNFeelRepositoryStore.LF_EXTENSION;
            File file = new File(targetAbsoluteFilePath);
            if (file.exists()){
            	if (FileActionDialog.overwriteQuestion(targetAbsoluteFilePath)){
            		PlatformUtil.delete(file, Repository.NULL_PROGRESS_MONITOR);
            	} else {
            		return;
            	}
            }
            final ArchiveFileExportOperation exportOperation = new ArchiveFileExportOperation(Arrays.asList(tempFolder.members()), targetAbsoluteFilePath);
            
            exportOperation.setCreateLeadupStructure(false);
            exportOperation.run(Repository.NULL_PROGRESS_MONITOR);

            if(tempFolder.exists()){
                tempFolder.delete(true, Repository.NULL_PROGRESS_MONITOR);
            }

        }catch(Exception e){
            BonitaStudioLog.error(e) ;
        }
    }


    protected void replaceUrl(final IResource resource) throws CoreException, IOException {
        existingResource.clear();
        replaceUrl( resource, "", 0, true, System.currentTimeMillis());
    }

    private void replaceUrl(final IResource resource, final String baseFolder, int depth, final boolean firstCall, long timestamp) throws CoreException, IOException {
        if (resource.getType() == IResource.FOLDER) {
            final IFolder folder = (IFolder) resource;
            String baseFolder2;
            if (firstCall) {
                baseFolder2 = baseFolder;
            } else {
                depth++;
                baseFolder2 = baseFolder + resource.getName() + "/";
            }

            for (final IResource f : folder.members()) {
                replaceUrl(f, baseFolder2, depth, false, timestamp);
            }
        } else if (resource.getType() == IResource.FILE
                && (resource.getName().endsWith(".html")
                        || resource.getName().endsWith(".htm")
                        || resource.getName().endsWith(".xhtml")
                        || resource.getName().endsWith(".css")
                        || resource.getName().endsWith(".jsp"))) {
            final IFile file = (IFile)resource;
            final InputStream fis = file.getContents();
            final String content = PlatformUtil.getFileContent(fis);
            fis.close();
            String newContent = new String(content);
            final StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("(src=\"");
            stringBuilder.append(THEME_SERVLET_NEGATIVE_LOOKAHEAD);
            stringBuilder.append(")([^\"]*)(\")|(href=\"");
            stringBuilder.append(THEME_SERVLET_NEGATIVE_LOOKAHEAD);
            stringBuilder.append(")([^\"]*)(\")|(url\\('");
            stringBuilder.append(THEME_SERVLET_NEGATIVE_LOOKAHEAD);
            stringBuilder.append(")([^'\"]*)(\'\\))|(url\\(");
            stringBuilder.append(THEME_SERVLET_NEGATIVE_LOOKAHEAD) ;
            stringBuilder.append(")\"{0,1}([^\\)\"]*)\"{0,1}(\\))|(background=\"");
            stringBuilder.append(THEME_SERVLET_NEGATIVE_LOOKAHEAD);
            stringBuilder.append(")([^\"]*)(\")");
            final String regex = stringBuilder.toString();
            final Pattern pattern = Pattern.compile(regex.toString());
            final Matcher matcher = pattern.matcher(newContent);
            final StringBuffer stringBuffer = new StringBuffer();
            while(matcher.find()) {
                int i;
                if(matcher.group(1)!= null){
                    i = 0;
                }else if(matcher.group(4)!= null){
                    i = 3;
                } else if(matcher.group(7)!= null){
                    i = 6;
                }else if(matcher.group(10)!= null){
                    i = 9;
                }else{
                    i = 12;
                }
                final String url = matcher.group(2+i);
                if (shouldReplace(url,resource.getLocation().toFile().toURI())) {
                    final String replacement = matcher.group(1+i) + toThemeResourceUrl(baseFolder,depth,url,timestamp) + matcher.group(3+i);
                    matcher.appendReplacement(stringBuffer, replacement);
                }
            }
            matcher.appendTail(stringBuffer);
            newContent = stringBuffer.toString();
            file.setContents(new ByteArrayInputStream(newContent.getBytes(LookNFeelRepositoryStore.UTF_8)), IResource.FORCE, Repository.NULL_PROGRESS_MONITOR);

        }
    }


    private boolean shouldReplace(final String url, URI baseURI) {
        boolean shouldReplace = !(url.startsWith("http://") || url.startsWith("https://") || url.startsWith("file://") || url.startsWith("ftp://"));
        if(shouldReplace){
            for (int i = 0; i < urlThatWontBeReplaced.length && shouldReplace; i++) {
                shouldReplace = !urlThatWontBeReplaced[i].equals(url);
            }
        }
        if(shouldReplace){
            if(!existingResource.containsKey(url)){//the resource exists (already checked it)
                try{
                    URI resource = baseURI.resolve(url);//resolve the uri
                    File file = new File(resource);
                    shouldReplace = file.exists();
                    existingResource.put(url,shouldReplace);
                } catch (IllegalArgumentException e ) {
                    existingResource.put(url,false);
                    return false;
                }
            }else{
                return existingResource.get(url);
            }
        }
        return shouldReplace;
    }

    public String toThemeResourceUrl(final String basefolder, int depth, String url, long timestamp) {

        int numberOfDotDotToRemove = 0;
        String urlWithoutDotDot = url;
        String shortenBaseFolder = basefolder;
        int length = DOTDOT.length();
        while(urlWithoutDotDot.startsWith(DOTDOT) && numberOfDotDotToRemove < depth ){
            urlWithoutDotDot = urlWithoutDotDot.substring(length);
            numberOfDotDotToRemove++;
        }
        if (numberOfDotDotToRemove > 0){
            //must remove to the basefolder this number of ../
            String[] split = shortenBaseFolder.split("/");
            int numberOfCharToRemove = 0;
            for (int i = 0; i < numberOfDotDotToRemove; i++) {
                numberOfCharToRemove += split[split.length-1-i].length()+1;
            }
            shortenBaseFolder = shortenBaseFolder.substring(0,Math.max(0, shortenBaseFolder.length()-numberOfCharToRemove));
        }
        String urlToEncode = shortenBaseFolder + urlWithoutDotDot;
        String newUrl = null;
        try {
            newUrl = LookNFeelRepositoryStore.THEME_SERVLET_URL + URLEncoder.encode(getName(),"UTF-8") + LookNFeelRepositoryStore.THEME_SERVLET_TIMESTAMP + timestamp + LookNFeelRepositoryStore.THEME_SERVLET_LOCATION + URLEncoder.encode(urlToEncode, LookNFeelRepositoryStore.UTF_8);
        } catch (final UnsupportedEncodingException e) {
            BonitaStudioLog.error(e);
        }
        if (newUrl == null) {
            newUrl = LookNFeelRepositoryStore.THEME_SERVLET_URL + getName() + LookNFeelRepositoryStore.THEME_SERVLET_TIMESTAMP + timestamp + LookNFeelRepositoryStore.THEME_SERVLET_LOCATION + urlToEncode;
        }
        return newUrl;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.bonitasoft.studio.common.repository.AbstractRepositoryArtifact#doSave
     * (java.lang.Object, boolean)
     */
    @Override
    protected void doSave(final Object content) {
        final IFolder themeRoot = getResource();
        final ThemeDescriptorManager themeManager = getThemeDescriptorManager() ;
        if(!themeRoot.exists()){
            try {
                themeRoot.create(true, true, Repository.NULL_PROGRESS_MONITOR);
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
        final IFile themeDescriptorFile = getThemeDescriptorFile() ;
        ThemeDescriptor themeDescriptor = null ;
        try {
            // Create ThemeDescriptor.xml
            themeDescriptorFile.refreshLocal(IResource.DEPTH_ONE, new NullProgressMonitor()) ;

            if (themeDescriptorFile.exists()) {
                themeDescriptor = themeManager.getThemeDescriptor(themeDescriptorFile.getLocation().toFile());
            } else {
                themeDescriptor = themeManager.createThemeDescriptor(getName(), themeDescriptorFile.getLocation().toFile());
                themeDescriptor.setCreationDate(System.currentTimeMillis()) ;
                themeDescriptor.setName(getDisplayName()) ;
                themeDescriptor.setProvided(false);
                themeManager.updateThemeDescriptor(themeDescriptor);
                themeDescriptor.setType((this instanceof ApplicationLookNFeelFileStore)?ThemeType.application:ThemeType.userXP);
                themeDescriptorFile.refreshLocal(IResource.DEPTH_ZERO, new NullProgressMonitor());
            }
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }

        themeDescriptor.setUpdateDate(System.currentTimeMillis());
        final String name = themeDescriptor.getName() ;

        final WorkspaceJob saveJob = new WorkspaceJob("Saving " +name+"...") {

            @Override
            public IStatus runInWorkspace(final IProgressMonitor monitor) throws CoreException {
                final IFolder folder = getResource();
                ThemeDescriptor themeDescriptor = getThemeDescriptor();
                if(!name.equals(folder.getName())){
                    IRepositoryFileStore file = getParentStore().getChild(name);
                    if(file != null){
                        setThemeName(getName());
                        throw new CoreException(new Status(IStatus.CANCEL, ThemeRepositoryPlugin.PLUGIN_ID, "Trying to rename a theme into an already existing name"));
                    }
                }
                try {
                    final Set<String> filesToUpdate = new HashSet<String>();
                    final Set<String> existingBinding = new HashSet<String>();
                    if(getBindings() != null){
                        for (final String key : getBindings().keySet()) {
                            final Binding binding = getBindings().get(key).getBinding();
                            existingBinding.add(binding.getName()) ;
                            themeDescriptor.updateBinding(binding);
                            String cssRule = binding.getCssRule();
                            String cssFile = binding.getCssFile();
                            filesToUpdate.add(cssFile) ;
                            if(!cssRule.isEmpty()){
                                final Map<String, Object> cssProperties = getBindings().get(key).getCssProperties();
                                final Map<String, Object> map = CSSUtil.formatPropertiesForOutput(cssProperties) ;
                                final CSSProperties cssPropertyFile = getCSSProperies(cssFile) ;
                                for (final String cssKey : map.keySet()) {
                                    Object value = map.get(cssKey);
                                    if (value == null || value.toString().isEmpty()) {
                                        value = null;
                                    }
                                    cssPropertyFile.put(cssRule, cssKey, value != null  && !value.toString().isEmpty() ? value.toString() : null) ;
                                }
                            }
                        }

                        Set<String> toDelete = new HashSet<String>();
                        for(String binding :themeDescriptor.getBindings().keySet()){
                            if(!existingBinding.contains(binding)){
                                toDelete.add(binding) ;
                            }
                        }
                        for(String binding : toDelete){
                            themeDescriptor.deleteBinding(binding) ;
                        }

                        for(final String file : filesToUpdate){
                            final File cssFile = new File(getRootFile(),file) ;
                            final CSSProperties cssPropertyFile = cssPropertiesFileCache.get(file) ;
                            final FileOutputStream fos = new FileOutputStream(cssFile) ;
                            try{
                                cssPropertyFile.save(fos) ;
                            }finally{
                                fos.close() ;
                            }
                        }
                    }
                    themeManager.updateThemeDescriptor(themeDescriptor);
                    getResource().refreshLocal(IResource.DEPTH_INFINITE, monitor);
                    //move folder if id changed
                    if(!name.equals(folder.getName())){
                        rename(name) ;
                    }
                } catch (final Exception e) {
                    throw new CoreException(new Status(IStatus.CANCEL, ThemeRepositoryPlugin.PLUGIN_ID, "error while saving theme", e));
                }
                return Status.OK_STATUS;
            }
        };
        try{
            saveJob.runInWorkspace(Repository.NULL_PROGRESS_MONITOR);
        } catch ( CoreException e ){
            BonitaStudioLog.error(e) ;
        }
    }


    protected ThemeDescriptorManager getThemeDescriptorManager(){
        return ((LookNFeelRepositoryStore) getParentStore()).getThemeDescriptorManager() ;
    }


    public void load() throws Exception {
        cssPropertiesFileCache.clear();
        ThemeDescriptor themeDescriptor = getThemeDescriptor() ;
        if(themeDescriptor != null){
            bindings = new HashMap<String, BindingWrapper>();
            for (final String key : themeDescriptor.getBindings().keySet()) {
                final Binding binding = themeDescriptor.getBindings().get(key);
                Map<String, Object> cssProperties = new HashMap<String, Object>();
                if(getCSSProperies(binding.getCssFile()) != null){
                    cssProperties = CSSUtil.getPropertyMap(binding.getCssRule(), getCSSProperies(binding.getCssFile())) ;
                }
                final BindingWrapper wrapper = new BindingWrapper(binding, cssProperties);
                bindings.put(key, wrapper);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.bonitasoft.studio.common.repository.AbstractRepositoryArtifact#doDelete
     * ()
     */
    @Override
    protected void doDelete() {
        if(isProvided() || isReadOnly()){
            throw new AssertionFailedException("Impossible to delete a provided look'n'feel");
        }
        try {
            getThemeDescriptorManager().deleteThemeDescriptor(getThemeDescriptor().getThemeDescriptor());
            getResource().delete(true, new NullProgressMonitor());
        } catch (Exception e) {
            BonitaStudioLog.error(e) ;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.bonitasoft.studio.common.repository.AbstractRepositoryArtifact#doClose
     * ()
     */
    @Override
    protected void doClose() {
        //		if(PlatformUI.isWorkbenchRunning()
        //				&& PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null
        //				&& PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null) {
        //			for (IEditorReference editor : PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences()) {
        //				IEditorPart simpleEditor = editor.getEditor(true);
        //				if (simpleEditor instanceof MultiPageEditor) {
        //					DiagramEditor diagramEditor = (DiagramEditor) simpleEditor;
        //					EObject input = diagramEditor.getDiagramEditPart().resolveSemanticElement();
        //					if (input instanceof MainProcess) {
        //						MainProcess oldProcess = ((MainProcess) input);
        //						if ((oldProcess.getName() + "_" + oldProcess.getVersion()).equals(getName())) { //$NON-NLS-1$
        //							PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeEditor(diagramEditor, !getRepository().isDisableUIPopup());
        //							break;
        //						}
        //					}
        //				}
        //			}
        //		}
    }




    public boolean isProvided() {
        return getThemeDescriptor().isProvided();
    }

    public IFile getThemeDescriptorFile() {
        return getResource().getFile(ThemeDescriptorManager.THEME_DESCRIPTOR_NAME);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Object getAdapter(final Class adapter) {
        return null;
    }

    @Override
    public boolean exists() {
        return false;
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        return Pics.getImageDescriptor(PicsConstants.looknfeel);
    }

    @Override
    public IPersistableElement getPersistable() {
        return null;
    }


    @Override
    public String getToolTipText() {
        return getThemeDescriptor().getName();
    }

    public Map<String, BindingWrapper> getBindings() {
        return bindings;
    }

    public String getDescription() {
        return getThemeDescriptor().getDescription();
    }

    public String getAuthor() {
        return getThemeDescriptor().getAuthor();
    }

    public String getPreviewFilePath() {
        return getThemeDescriptor().getImagePreview();
    }
    public Image getPreviewImage() throws IOException {
        String previewFilePath = getPreviewFilePath();
        if(previewFilePath != null && previewFilePath.length()>0){
            File img = new File(getRootFile(),previewFilePath);
            if(img.exists()) {
                return ImageDescriptor.createFromURL(img.toURI().toURL()).createImage();
            }
        }
        return null;
    }

    public void setThemeName(final String themeName) {
        getThemeDescriptor().setName(themeName);
    }

    public void setAuthor(final String themeAuthor) {
        getThemeDescriptor().setAuthor(themeAuthor);
    }

    public void setDescription(final String themeDescription) {
        getThemeDescriptor().setDescription(themeDescription);
    }

    public void setPreviewFilePath(final String themePreviewFilePath) {
        getThemeDescriptor().setImagePreview(themePreviewFilePath) ;
    }

    public File getRootFile() {
        return getResource().getLocation().toFile();
    }

    public ThemeType getType() {
        return getThemeDescriptor().getType();
    }

    public CSSProperties getCSSProperies(String path) {
        IFile file = getResource().getFile(new Path(path));
        if(file != null && !file.exists()){
            return null ;
        }
        if(cssPropertiesFileCache.get(path) == null){
            try {
                cssPropertiesFileCache.put(path,((LookNFeelRepositoryStore) getParentStore()).getCSSManager().createCSSPropertyFromFile(file.getLocation().toFile()));
            } catch (IOException e) {
                BonitaStudioLog.error(e) ;
            }
        }
        return cssPropertiesFileCache.get(path) ;

    }


    /**
     * @param fileName
     * @param inputStream
     * @throws CoreException
     * @throws IOException
     */
    public void copyPreviewFile(String fileName, InputStream inputStream) throws CoreException, IOException {
        IFile file = getResource().getFile(fileName);
        if(fileName.lastIndexOf(".") != -1){
            String ext = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()) ;
            BufferedImage source = ImageIO.read(inputStream) ;
            BufferedImage image = FileUtil.resizeImage(source,500) ;
            file.getLocation().toFile().delete();
            ImageIO.write(image, ext, file.getLocation().toFile());
            file.refreshLocal(IResource.DEPTH_ZERO,  Repository.NULL_PROGRESS_MONITOR) ;
        }else{
            throw new IOException(fileName + " doesn't have a valid extension") ;
        }
    }

    @Override
    protected IWorkbenchPart doOpen() {
        try {
            load() ;
        } catch (Exception e1) {
            BonitaStudioLog.error(e1) ;
        }

        Set<String> filesToLoad = new HashSet<String>();
        for(Binding b : getThemeDescriptor().getBindings().values()){
            filesToLoad.add(b.getCssFile()) ;
        }

        for(String file : filesToLoad){
            IFile cssFile = getResource().getFile(new Path(file));
            if(cssFile != null && cssFile.exists()){
                try {
                    cssPropertiesFileCache.put(file, ((LookNFeelRepositoryStore) getParentStore()).getCSSManager().createCSSPropertyFromFile(cssFile.getLocation().toFile()));
                } catch (IOException e) {
                    BonitaStudioLog.error(e) ;
                }
            }
        }
        try {
            getResource().refreshLocal(IResource.DEPTH_INFINITE, Repository.NULL_PROGRESS_MONITOR) ;
        } catch (CoreException e1) {
            BonitaStudioLog.error(e1);
        }
        Display.getDefault().asyncExec(new Runnable() {

            @Override
            public void run() {
                final IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                try {
                    editor = IDE.openEditor(page, LookNFeelFileStore.this, "org.bonitasoft.studio.themes.editors.themeMultiPageEditor",true);
                    editor.setFocus() ;
                } catch (final PartInitException e) {

                }
            }
        });

        return editor ;

    }

    @Override
    public Image getIcon() {
        return Pics.getImage(PicsConstants.looknfeel);
    }


    @Override
    public void rename(String newName) {
        final IFolder destination = getParentStore().getResource().getFolder(newName);
        if(destination.exists()){
            throw new RuntimeException("Trying to rename into an existing name");
        }
        try {
            IPath newPath = getResource().getFullPath().removeLastSegments(1).append(newName);
            getResource().refreshLocal(IResource.DEPTH_INFINITE, Repository.NULL_PROGRESS_MONITOR) ;
            MoveResourcesOperation op = new MoveResourcesOperation(
                    getResource(),
                    newPath,
                    "Renaming Theme artifact...");
            op.execute(Repository.NULL_PROGRESS_MONITOR, null);


            setName(newName);
            themeDesctiptor = null ;
            final ThemeDescriptor themeDescriptor = getThemeDescriptor() ;
            //refresh theme descriptor
            themeDescriptor.setThemeDescriptor(getThemeDescriptorFile().getLocation().toFile());
            getThemeDescriptorManager().updateThemeDescriptor(themeDescriptor);
            getThemeDescriptorFile().refreshLocal(IResource.DEPTH_INFINITE, Repository.NULL_PROGRESS_MONITOR);
        } catch (Exception e) {
            BonitaStudioLog.error(e) ;
        }
    }

}
