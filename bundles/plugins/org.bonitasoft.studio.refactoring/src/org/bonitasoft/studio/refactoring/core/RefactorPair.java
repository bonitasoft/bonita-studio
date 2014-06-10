package org.bonitasoft.studio.refactoring.core;



public class RefactorPair<T,Y> {
	
    protected static final String EMPTY_VALUE = "     ";
	private T newValue;
	private Y oldValue;

	/**
	 * @param newValue, if null it means that the oldValue need to be removed
	 * @param oldValue
	 */
	public RefactorPair(T newValue, Y oldValue) {
		this.newValue = newValue;
		this.oldValue = oldValue;
	}

	public T getNewValue() {
		return newValue;
	}

	public Y getOldValue() {
		return oldValue;
	}
	
    /**
     * @return the old value name used in scripts
     */
    public String getOldValueName(){
    	return oldValue.toString();
    }

    /**
     * @return the new value name to use in scripts
     */
    public String getNewValueName(){
    	return newValue != null ? newValue.toString() : EMPTY_VALUE;
    }

}