package org.bonitasoft.studio.application.ui.control.model;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bonitasoft.bpm.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.bpm.model.edit.ProcessEditPlugin;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.filestore.DefinitionConfigurationFileStore;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.filestore.PackageFileStore;
import org.bonitasoft.studio.common.repository.filestore.SourceFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.ui.IDisplayable;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.configuration.repository.DefaultConfigurationStyler;
import org.bonitasoft.studio.configuration.repository.EnvironmentFileStore;
import org.bonitasoft.studio.configuration.repository.LocalEnvironmentFileStore;
import org.bonitasoft.studio.connector.model.definition.AbstractDefFileStore;
import org.bonitasoft.studio.connector.model.definition.AbstractDefinitionRepositoryStore;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesFileStore;
import org.bonitasoft.studio.dependencies.DependenciesPlugin;
import org.bonitasoft.studio.dependencies.repository.MavenDependencyFileStore;
import org.bonitasoft.studio.designer.core.repository.NamedJSONFileStore;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.groovy.repository.GroovyFileStore;
import org.bonitasoft.studio.identity.organization.repository.OrganizationFileStore;
import org.bonitasoft.studio.identity.organization.styler.ActiveOrganizationStyler;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;

import com.google.common.base.Suppliers;

public class RepositoryFileStoreDisplayable implements IDisplayable {

    private IRepositoryFileStore store;
    public static final String DISPLAY_NAME_KEY = "displayName";
    private static final Pattern diagramNamePattern = Pattern.compile("(.*)-(.*)");

    public RepositoryFileStoreDisplayable(IRepositoryFileStore repositoryFileStore) {
        this.store = repositoryFileStore;
    }

    @Override
    public String getDisplayName() {
        if (store instanceof EMFFileStore) {
            if (store instanceof AbstractDefFileStore) {
                // ActorFilterDefFileStore or ConnectorDefFileStore
                AbstractDefinitionRepositoryStore parentStore = (AbstractDefinitionRepositoryStore) ((AbstractDefFileStore) store)
                        .getParentStore();
                ConnectorDefinition def;
                try {
                    def = ((AbstractDefFileStore) store).getContent();
                } catch (ReadFileStoreException e) {
                    return store.getName();
                }
                if (def != null) {
                    String defName = parentStore.getResourceProvider().getConnectorDefinitionLabel(def);
                    if (defName == null) {
                        defName = def.getId();
                    }
                    return defName + " (" + def.getVersion() + ")";
                }
                // else, let defaut behavior do the job
            } else if (store instanceof DiagramFileStore) {
                final String displayName = store.getResource().getLocation().removeFileExtension().lastSegment();
                final Matcher matcher = diagramNamePattern.matcher(displayName);
                if (matcher.matches()) {
                    return String.format("%s (%s)", matcher.group(1), matcher.group(2));
                }
                return displayName;
            } else if (store instanceof EnvironmentFileStore) {
                if (store instanceof LocalEnvironmentFileStore) {
                    return ConfigurationPreferenceConstants.LOCAL_CONFIGURATION;
                } else {
                    try {
                        return ((EnvironmentFileStore) store).getContent().getName();
                    } catch (ReadFileStoreException e) {
                        BonitaStudioLog.warning(e.getMessage(), getClass());
                        return store.getName();
                    }
                }
            } else if (store instanceof OrganizationFileStore) {
                   String name = ((OrganizationFileStore) store).getResource().getName();
                   if(name.endsWith(".xml")) {
                	   name = name.substring(0, name.length() -4);
                   }
				return name;
            } else
                try {
                    return ((EMFFileStore) store).getLabelProvider().getText(store.getContent());
                } catch (ReadFileStoreException e) {
                    return store.getName();
                }
        } else if (store instanceof BusinessObjectModelFileStore) {
            return Messages.businessDataModel;
        } else if (store instanceof NamedJSONFileStore) {
            if (store instanceof WebPageFileStore) {
                try {
                    return ((WebPageFileStore) store).getStringAttribute(DISPLAY_NAME_KEY);
                } catch (ReadFileStoreException e) {
                    return store.getName();
                }
            } else
                return ((NamedJSONFileStore) store).getCustomPageName();
        } else if (store instanceof DatabaseConnectorPropertiesFileStore) {
            // rely on default behavior
        }

        // default behavior based on name...
        if (store.getName().indexOf('.') != -1) {
            return store.getName().substring(0, store.getName().lastIndexOf('.'));
        }
        return store.getName();
    }

    @Override
    public Image getIcon() {
        if (store instanceof PackageFileStore) {
            return Pics.getImage("package.gif", CommonRepositoryPlugin.getDefault());
        } else if (store instanceof SourceFileStore) {
            return Pics.getImage("java.gif", CommonRepositoryPlugin.getDefault());
        } else if (store instanceof GroovyFileStore) {
            return Pics.getImage(PicsConstants.groovyScript);
        } else if (store instanceof EMFFileStore) {
            if (store instanceof AbstractDefFileStore) {
                // ActorFilterDefFileStore or ConnectorDefFileStore
                try {
                    var definition = ((AbstractDefFileStore) store).getContent();
                    ImageDescriptor descriptor = ((AbstractDefFileStore) store).getDefinitionImageResourceLoader()
                            .getIcon(definition);
                    if (descriptor != null) {
                        return descriptor.createImage();
                    }
                } catch (ReadFileStoreException e) {
                    BonitaStudioLog.debug(e.getMessage(), e, getClass());
                }
                return null;
            } else if (store instanceof DefinitionConfigurationFileStore) {
                return Pics.getImage("conf.png", CommonRepositoryPlugin.getDefault());
            } else if (store instanceof EnvironmentFileStore) {
                return Pics.getImage(PicsConstants.environment);
            }else if (store instanceof DiagramFileStore) {
            	return Pics.getImage("full/obj16/MainProcess.gif", ProcessEditPlugin.getPlugin());
            } else if (store instanceof OrganizationFileStore) {
                // rely on default behavior
            } else {
	            try {
	                return ((EMFFileStore) store).getLabelProvider().getImage(store.getContent());
	            } catch (ReadFileStoreException e) {
	                return null;
	            }
            }
        } else if (store instanceof BusinessObjectModelFileStore) {
            return Pics.getImage(PicsConstants.bdm);
        } else if (store instanceof MavenDependencyFileStore) {
            return Pics.getImage("jar.gif", DependenciesPlugin.getDefault());
        } else if (store instanceof DatabaseConnectorPropertiesFileStore) {
            return Pics.getImage("databases_driver.png", ConnectorPlugin.getDefault());
        }

        // default behavior : try and get icon from parent store
        return IDisplayable.adapt(store.getParentStore()).map(IDisplayable::getIcon).orElse(null);
    }

    /** styler used for default configuration for EnvironmentFileStore */
    private Supplier<DefaultConfigurationStyler> defaultConfigurationStylerSupplier = Suppliers
            .memoize(DefaultConfigurationStyler::new);

    /** styler used for active organization for OrganizationFileStore */
    private Supplier<ActiveOrganizationStyler> activeOrganizationStylerSupplier = Suppliers
            .memoize(ActiveOrganizationStyler::new);

    @Override
    public StyledString getStyledString() {
        if (store instanceof WebPageFileStore) {

            StyledString styledString = new StyledString();
            String displayName = getDisplayName();
            String name = displayName == null || displayName.isEmpty() ? store.getName() : displayName;
            styledString.append(name);
            if (!Objects.equals(((NamedJSONFileStore) store).getCustomPageName(), name)) {
                styledString.append(String.format(" (%s)", ((NamedJSONFileStore) store).getCustomPageName()),
                        StyledString.COUNTER_STYLER);
            }
            String type = ((WebPageFileStore) store).getType();
            if (type != null) {
                styledString.append(String.format(" %s", type.toUpperCase()), StyledString.QUALIFIER_STYLER);
            }
            return styledString;

        } else if (store instanceof EnvironmentFileStore) {
            StyledString styledString = new StyledString(store.getName());
            String defaultConfiguration = ConfigurationPlugin.getDefault().getPreferenceStore()
                    .getString(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION);
            boolean isDefault = Objects.equals(defaultConfiguration, getDisplayName());
            if (isDefault) {
                styledString.append(
                        String.format("  (%s)", org.bonitasoft.studio.configuration.i18n.Messages.activeConfiguration),
                        defaultConfigurationStylerSupplier.get());
            }
            return styledString;
        } else if (store instanceof OrganizationFileStore) {
            StyledString styledString = new StyledString(store.getName());
            if (((OrganizationFileStore) store).isActiveOrganization()) {
                styledString.append(String.format("  (%s)", org.bonitasoft.studio.identity.i18n.Messages.active),
                        activeOrganizationStylerSupplier.get());
            }
            return styledString;
        }
        return new StyledString(store.getName());
    }

}
