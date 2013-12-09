package org.bonitasoft.studio.common.refactoring;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.compare.IContentChangeListener;
import org.eclipse.compare.IContentChangeNotifier;
import org.eclipse.compare.IEditableContent;
import org.eclipse.compare.IStreamContentAccessor;
import org.eclipse.compare.ITypedElement;
import org.eclipse.compare.internal.ContentChangeNotifier;
import org.eclipse.compare.structuremergeviewer.IStructureComparator;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.internal.core.util.Util.Comparer;
import org.eclipse.jdt.internal.ui.javaeditor.ClassFileDocumentProvider.InputChangeListener;
import org.eclipse.jdt.internal.ui.javaeditor.IClassFileEditorInput;
import org.eclipse.swt.graphics.Image;

public class CompareScript implements IStreamContentAccessor,IStructureComparator,IEditableContent, ITypedElement, IAdaptable, IContentChangeNotifier {

	private String name;
	private EObject content;
	private List<Object> children;
	private ContentChangeNotifier changeNotifier;
	private Image image;

	CompareScript(String name,EObject content){
		this.name = name;
		this.content = content;
		children = new ArrayList<Object>();
	}
	
	@Override
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image){
		this.image = image;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getType() {
		return ITypedElement.TEXT_TYPE;
	}

	@Override
	public InputStream getContents() throws CoreException {
		if (content instanceof Expression){
			return new ByteArrayInputStream(((Expression)content).getContent().getBytes());
		}
		return null;
	}
	
	public Expression getContentExpression(){
		if (content instanceof Expression){
			return (Expression)content;
		} 
		return null;
	}

	@Override
	public Object[] getChildren() {
		return children.toArray();
	}
	
	public void addChild(Object child){
		children.add(child);
	}

	@Override
	public boolean isEditable() {
		return true;
	}

	@Override
	public ITypedElement replace(ITypedElement arg0, ITypedElement arg1) {
		return null;
	}


	@Override
	public void setContent(byte[] arg0) {
		if (content instanceof Expression){
			((Expression)content).setContent(new String(arg0));
		}
		if (changeNotifier !=null){
			changeNotifier.fireContentChanged();
		}
	}

	@Override
	public Object getAdapter(Class adapter) {
		return Platform.getAdapterManager().getAdapter(this, adapter);
	}

	@Override
	public void addContentChangeListener(IContentChangeListener listener) {
		if (changeNotifier == null) {
					changeNotifier = new ContentChangeNotifier(this);
		}
					changeNotifier.addContentChangeListener(listener);
		
	}

	@Override
	public void removeContentChangeListener(IContentChangeListener listener) {
		if (changeNotifier != null)
				changeNotifier.removeContentChangeListener(listener);
		
	}
	public ContentChangeNotifier getContentChangeNotifier(){
		return changeNotifier;
	}

	

}
