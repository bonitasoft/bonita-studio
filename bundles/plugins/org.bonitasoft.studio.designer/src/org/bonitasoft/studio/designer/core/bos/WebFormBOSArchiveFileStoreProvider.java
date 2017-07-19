/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.designer.core.bos;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Predicates.containsPattern;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.regex.Pattern.compile;
import static org.bonitasoft.studio.common.emf.tools.ModelHelper.getAllItemsOfType;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.inject.Inject;

import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.provider.IBOSArchiveFileStoreProvider;
import org.bonitasoft.studio.designer.UIDesignerPlugin;
import org.bonitasoft.studio.designer.core.bar.BarResourceCreationException;
import org.bonitasoft.studio.designer.core.bar.CustomPageBarResourceFactory;
import org.bonitasoft.studio.designer.core.repository.WebFragmentFileStore;
import org.bonitasoft.studio.designer.core.repository.WebFragmentRepositoryStore;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.designer.core.repository.WebWidgetFileStore;
import org.bonitasoft.studio.designer.core.repository.WebWidgetRepositoryStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.model.process.FormMappingType;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.e4.core.di.annotations.Creatable;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.io.ByteSource;

/**
 * @author Romain Bioteau
 */
@Creatable
public class WebFormBOSArchiveFileStoreProvider implements IBOSArchiveFileStoreProvider {

    private static final String WIDGET_ENTRY_REGEXP = "^resources/widgets/(.*)/.*\\.json";
    private static final String FRAGMENT_ENTRY_REGEXP = "^resources/fragments/(.*)/.*\\.json";

    private final RepositoryAccessor repositoryAccessor;

    private final CustomPageBarResourceFactory customPageBarResourceFactory;

    @Inject
    public WebFormBOSArchiveFileStoreProvider(final RepositoryAccessor repositoryAccessor,
            final CustomPageBarResourceFactory customPageBarResourceFactory) {
        this.repositoryAccessor = repositoryAccessor;
        this.customPageBarResourceFactory = customPageBarResourceFactory;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.provider.
     * IBOSArchiveFileStoreProvider#getFileStoreForConfiguration(org.bonitasoft.
     * studio.model.process. AbstractProcess,
     * org.bonitasoft.studio.model.configuration.Configuration)
     */
    @Override
    public Set<IRepositoryFileStore> getFileStoreForConfiguration(final AbstractProcess process,
            final Configuration configuration) {
        final Set<IRepositoryFileStore> result = new HashSet<IRepositoryFileStore>();
        final List<FormMapping> allFormMappings = getAllItemsOfType(process, ProcessPackage.Literals.FORM_MAPPING);
        for (final WebPageFileStore fStore : transform(filter(allFormMappings, withInternalType()),
                formMappingToFileStore())) {
            if (fStore != null) {
                result.add(fStore);
                try {
                    result.addAll(getRelatedFileStore(fStore));
                } catch (final BarResourceCreationException | IOException e) {
                    BonitaStudioLog.error("Failed to retrieve related form resources", e);
                }
            }
        }
        return result;
    }

    protected Set<String> findFormRelatedEntries(final WebPageFileStore fStore)
            throws BarResourceCreationException, IOException {
        final BarResource barResource = customPageBarResourceFactory.newBarResource(fStore.getName(), fStore.getId());
        final byte[] zipContent = barResource.getContent();
        final Set<String> zipEntries = zipEntries(zipContent);
        return zipEntries;
    }

    private Set<String> zipEntries(final byte[] zipContent) throws IOException {
        final Set<String> entries = new HashSet<String>();
        try (final ZipInputStream stream = new ZipInputStream(ByteSource.wrap(zipContent).openBufferedStream())) {
            ZipEntry entry;
            while ((entry = stream.getNextEntry()) != null) {
                entries.add(entry.getName());
            }
        }
        return entries;
    }

    private Set<WebWidgetFileStore> relatedWidgets(final Set<String> zipEntries) {
        return newHashSet(filter(transform(filter(zipEntries, containsPattern(WIDGET_ENTRY_REGEXP)),
                toWidgetFileStore(compile(WIDGET_ENTRY_REGEXP))), customWidgetOnly()));
    }

    private Predicate<WebWidgetFileStore> customWidgetOnly() {
        return new Predicate<WebWidgetFileStore>() {

            @Override
            public boolean apply(final WebWidgetFileStore input) {
                return input.canBeExported();
            }
        };
    }

    private Function<String, WebWidgetFileStore> toWidgetFileStore(final Pattern widgetPattern) {
        return new Function<String, WebWidgetFileStore>() {

            @Override
            public WebWidgetFileStore apply(final String matchingEntry) {
                final Matcher matcher = widgetPattern.matcher(matchingEntry);
                checkState(matcher.matches() == true);
                return repositoryAccessor.getRepositoryStore(WebWidgetRepositoryStore.class).getChild(matcher.group(1));
            }
        };
    }

    private Set<WebFragmentFileStore> relatedFragments(final Set<String> zipEntries) {
        return newHashSet(transform(filter(zipEntries, containsPattern(FRAGMENT_ENTRY_REGEXP)),
                toFragmentFileStore(compile(FRAGMENT_ENTRY_REGEXP))));
    }

    private Function<String, WebFragmentFileStore> toFragmentFileStore(final Pattern fragmentPattern) {
        return new Function<String, WebFragmentFileStore>() {

            @Override
            public WebFragmentFileStore apply(final String matchingEntry) {
                final Matcher matcher = fragmentPattern.matcher(matchingEntry);
                checkState(matcher.matches() == true);
                return repositoryAccessor.getRepositoryStore(WebFragmentRepositoryStore.class)
                        .getChild(matcher.group(1));
            }
        };
    }

    private Function<FormMapping, WebPageFileStore> formMappingToFileStore() {
        return new Function<FormMapping, WebPageFileStore>() {

            @Override
            public WebPageFileStore apply(final FormMapping mapping) {
                final String formUUID = mapping.getTargetForm().getContent();
                final WebPageFileStore store = fileStoreFromFormUUID(formUUID);
                if (store == null) {
                    BonitaStudioLog.warning(String.format("Page with id %s doesn't exist.", formUUID),
                            UIDesignerPlugin.PLUGIN_ID);
                }
                return store;
            }

        };
    }

    private WebPageFileStore fileStoreFromFormUUID(final String formUUID) {
        checkArgument(!isNullOrEmpty(formUUID));
        return repositoryAccessor.getRepositoryStore(WebPageRepositoryStore.class).getChild(formUUID);
    }

    private Predicate<FormMapping> withInternalType() {
        return new Predicate<FormMapping>() {

            @Override
            public boolean apply(final FormMapping mapping) {
                return mapping.getType() == FormMappingType.INTERNAL && mapping.getTargetForm().hasContent();
            }
        };
    }

    public Set<IRepositoryFileStore> getRelatedFileStore(final WebPageFileStore webPageFileStore)
            throws BarResourceCreationException, IOException {
        final Set<String> zipEntries = findFormRelatedEntries(webPageFileStore);
        final Set<IRepositoryFileStore> result = new HashSet<IRepositoryFileStore>();
        result.addAll(relatedFragments(zipEntries));
        result.addAll(relatedWidgets(zipEntries));
        return result;
    }

}
