/**
 * 
 */
package org.bonitasoft.studio.groovy.ui;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IAnnotation;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaModel;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.ILocalVariable;
import org.eclipse.jdt.core.IMemberValuePair;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IOpenable;
import org.eclipse.jdt.core.ISourceRange;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeParameter;
import org.eclipse.jdt.core.ITypeRoot;
import org.eclipse.jdt.core.JavaModelException;

public class GroovyFieldAccessorMethod implements IMethod {
	
	private String name;
	private String returnType;
	private String[] parameterNames;
	private String[] parameterTypes;
	private IType declaringType;

	public GroovyFieldAccessorMethod(IType declaringType, String name, String returnType, String[] parameterNames, String[] parameterTypes) {
		this.declaringType = declaringType;
		this.name = name;
		this.returnType = returnType;
		this.parameterNames = parameterNames;
		this.parameterTypes = parameterTypes;
	}

	@Override
	public String[] getCategories() throws JavaModelException {
		return null;
	}

	@Override
	public IClassFile getClassFile() {
		return null;
	}

	@Override
	public ICompilationUnit getCompilationUnit() {
		return null;
	}

	@Override
	public IType getDeclaringType() {
		return declaringType;
	}

	@Override
	public int getFlags() throws JavaModelException {
		return Flags.AccPublic;
	}

	@Override
	public ISourceRange getJavadocRange() throws JavaModelException {
		return null;
	}

	@Override
	public int getOccurrenceCount() {
		return 0;
	}

	@Override
	public IType getType(String arg0, int arg1) {
		return null;
	}

	@Override
	public ITypeRoot getTypeRoot() {
		return null;
	}

	@Override
	public boolean isBinary() {
		return false;
	}

	@Override
	public boolean exists() {
		return false;
	}

	@Override
	public IJavaElement getAncestor(int ancestorType) {
		return null;
	}

	@Override
	public String getAttachedJavadoc(IProgressMonitor monitor) throws JavaModelException {
		return null;
	}

	@Override
	public IResource getCorrespondingResource() throws JavaModelException {
		return null;
	}

	@Override
	public int getElementType() {
		return IJavaElement.METHOD ;
	}

	@Override
	public String getHandleIdentifier() {
		return null;
	}

	@Override
	public IJavaModel getJavaModel() {
		return null;
	}

	@Override
	public IJavaProject getJavaProject() {
		return null;
	}

	@Override
	public IOpenable getOpenable() {
		return null;
	}

	@Override
	public IJavaElement getParent() {
		return null;
	}

	@Override
	public IPath getPath() {
		return null;
	}

	@Override
	public IJavaElement getPrimaryElement() {
		return null;
	}

	@Override
	public IResource getResource() {
		return null;
	}

	@Override
	public ISchedulingRule getSchedulingRule() {
		return null;
	}

	@Override
	public IResource getUnderlyingResource() throws JavaModelException {
		return null;
	}

	@Override
	public boolean isReadOnly() {
		return false;
	}

	@Override
	public boolean isStructureKnown() throws JavaModelException {
		return false;
	}

	@Override
	public <T> T getAdapter(Class<T> adapter) {
		return null;
	}

	@Override
	public ISourceRange getNameRange() throws JavaModelException {
		return null;
	}

	@Override
	public String getSource() throws JavaModelException {
		return null;
	}

	@Override
	public ISourceRange getSourceRange() throws JavaModelException {
		return null;
	}

	@Override
	public void copy(IJavaElement arg0, IJavaElement arg1, String arg2, boolean arg3, IProgressMonitor arg4)
			throws JavaModelException {

	}

	@Override
	public void delete(boolean arg0, IProgressMonitor arg1) throws JavaModelException {

	}

	@Override
	public void move(IJavaElement arg0, IJavaElement arg1, String arg2, boolean arg3, IProgressMonitor arg4)
			throws JavaModelException {

	}

	@Override
	public void rename(String arg0, boolean arg1, IProgressMonitor arg2) throws JavaModelException {

	}

	@Override
	public IJavaElement[] getChildren() throws JavaModelException {
		return null;
	}

	@Override
	public boolean hasChildren() throws JavaModelException {
		return false;
	}

	@Override
	public IAnnotation getAnnotation(String name) {
		return null;
	}

	@Override
	public IAnnotation[] getAnnotations() throws JavaModelException {
		return null;
	}

	@Override
	public IMemberValuePair getDefaultValue() throws JavaModelException {
		return null;
	}

	@Override
	public String getElementName() {
		return name;
	}

	@Override
	public String[] getExceptionTypes() throws JavaModelException {
		return null;
	}

	@Override
	public String getKey() {
		return null;
	}

	@Override
	public int getNumberOfParameters() {
		return 0;
	}

	@Override
	public String[] getParameterNames() throws JavaModelException {
		return parameterNames;
	}

	@Override
	public String[] getParameterTypes() {
		return parameterTypes;
	}

	@Override
	public ILocalVariable[] getParameters() throws JavaModelException {
		return null;
	}

	@Override
	public String[] getRawParameterNames() throws JavaModelException {
		return null;
	}

	@Override
	public String getReturnType() throws JavaModelException {
		return returnType;
	}

	@Override
	public String getSignature() throws JavaModelException {
		return null;
	}

	@Override
	public ITypeParameter getTypeParameter(String name) {
		return null;
	}

	@Override
	public String[] getTypeParameterSignatures() throws JavaModelException {
		return null;
	}

	@Override
	public ITypeParameter[] getTypeParameters() throws JavaModelException {
		return null;
	}

	@Override
	public boolean isConstructor() throws JavaModelException {
		return false;
	}

	@Override
	public boolean isLambdaMethod() {
		return false;
	}

	@Override
	public boolean isMainMethod() throws JavaModelException {
		return false;
	}

	@Override
	public boolean isResolved() {
		return false;
	}

	@Override
	public boolean isSimilar(IMethod method) {
		return false;
	}

}
