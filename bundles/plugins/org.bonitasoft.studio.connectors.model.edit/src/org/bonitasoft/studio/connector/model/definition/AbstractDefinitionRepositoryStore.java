package org.bonitasoft.studio.connector.model.definition;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.store.AbstractEMFRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.util.ConnectorDefinitionAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.osgi.framework.Bundle;

public abstract class AbstractDefinitionRepositoryStore<T extends EMFFileStore> extends AbstractEMFRepositoryStore<T> implements IDefinitionRepositoryStore {

    @Override
    public List<ConnectorDefinition> getDefinitions() {
        final List<ConnectorDefinition> result = new ArrayList<ConnectorDefinition>();
        for(IRepositoryFileStore fileStore : getChildren()){
            ConnectorDefinition def ;
            try{
                def = (ConnectorDefinition) fileStore.getContent();
            }catch(Exception e){
                def = ConnectorDefinitionFactory.eINSTANCE.createUnloadableConnectorDefinition();
                def.setId(fileStore.getName());
                def.setVersion("");
            }
            if(def == null){
            	  def = ConnectorDefinitionFactory.eINSTANCE.createUnloadableConnectorDefinition();
                  def.setId(fileStore.getName());
                  def.setVersion("");
            }
            result.add(def) ;
        }
        return result ;
    }

    @Override
    public ConnectorDefinition getDefinition(String id, String version) {
        for(ConnectorDefinition def : getDefinitions()){
            if(def.getId().equals(id) && def.getVersion().equals(version)){
                return def ;
            }
        }
        return null;
    }

    @Override
    public ConnectorDefinition getDefinition(String id, String version,Collection<ConnectorDefinition> existingDefinitions) {
        for(ConnectorDefinition def : existingDefinitions){
            if(def.getId().equals(id) && def.getVersion().equals(version)){
                return def ;
            }
        }
        return null;
    }

    @Override
    public List<T> getChildren() {
        final List<T> result = super.getChildren();
        Enumeration<URL> connectorDefs = getBundle().findEntries(getName(), "*.def", false);
        if( connectorDefs != null ){
            while (connectorDefs.hasMoreElements()) {
                URL url = connectorDefs.nextElement();
                String[] segments = url.getFile().split("/") ;
                String fileName = segments[segments.length-1] ;
                if(fileName.lastIndexOf(".") != -1){
                    String extension = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()) ;
                    if(getCompatibleExtensions().contains(extension)){
                        result.add(getDefFileStore(url)) ;
                    }
                }
            }
        }
        return result ;
    }

    @Override
    public T getChild(String fileName) {
        T file = super.getChild(fileName) ;
        if(file == null){
            URL url = getBundle().getResource(getName()+ "/" +fileName);
            if(url != null){
                return getDefFileStore(url) ;
            }else{
                return null ;
            }
        }else{
            return file ;
        }

    }

    @Override
    protected void addAdapterFactory(ComposedAdapterFactory adapterFactory) {
        adapterFactory.addAdapterFactory(new ConnectorDefinitionAdapterFactory());
    }

    protected abstract T getDefFileStore(URL url);

    protected abstract Bundle getBundle();

}
