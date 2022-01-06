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
import static com.google.common.base.Predicates.containsPattern;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.regex.Pattern.compile;
import static org.bonitasoft.studio.common.emf.tools.ModelHelper.getAllItemsOfType;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
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
import org.bonitasoft.studio.designer.core.bar.CustomPageBarResourceBuilder;
import org.bonitasoft.studio.designer.core.bar.CustomPageBarResourceBuilderFactory;
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

    private final CustomPageBarResourceBuilder customPageBarResourceBuilder;

    @Inject
    public WebFormBOSArchiveFileStoreProvider(final RepositoryAccessor repositoryAccessor,
            final CustomPageBarResourceBuilderFactory customPageBarResourceFactory) {
        this.repositoryAccessor = repositoryAccessor;
        this.customPageBarResourceBuilder = customPageBarResourceFactory.create();
    }

    @Override
    public boolean distinctByConfiguration() {
        return false;
    }

    @Override
    public Set<IRepositoryFileStore<?>> getFileStoreForConfiguration(final AbstractProcess process,
            final Configuration configuration) {
        final Set<IRepositoryFileStore<?>> result = new HashSet<>();
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
        final BarResource barResource = customPageBarResourceBuilder.newBarResource(fStore.getName(), fStore.getId());
        final byte[] zipContent = barResource.getContent();
        return zipEntries(zipContent);
    }

    private Set<String> zipEntries(final byte[] zipContent) throws IOException {
        final Set<String> entries = new HashSet<>();
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
        return fstore -> fstore != null && fstore.canBeExported();
    }

    private Function<String, WebWidgetFileStore> toWidgetFileStore(final Pattern widgetPattern) {
        return matchingEntry -> {
            final Matcher matcher = widgetPattern.matcher(matchingEntry);
            if (matcher.matches()) {
                return repositoryAccessor.getRepositoryStore(WebWidgetRepositoryStore.class).getChild(matcher.group(1),
                        true);
            }
            return null;
        };
    }

    private Set<WebFragmentFileStore> relatedFragments(final Set<String> zipEntries) {
        return zipEntries.stream()
                .filter(entry -> containsPattern(FRAGMENT_ENTRY_REGEXP).apply(entry))
                .map(toFragmentFileStore(compile(FRAGMENT_ENTRY_REGEXP)))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    private java.util.function.Function<String, WebFragmentFileStore> toFragmentFileStore(
            final Pattern fragmentPattern) {
        return matchingEntry -> {
            final Matcher matcher = fragmentPattern.matcher(matchingEntry);
            if (!matcher.matches()) {
                BonitaStudioLog.warning(
                        String.format("Invalid fragment file found: %s. Please remove this file from your project.",
                                matchingEntry),
                        UIDesignerPlugin.PLUGIN_ID);
                return null;
            }
            return repositoryAccessor.getRepositoryStore(WebFragmentRepositoryStore.class)
                    .getChild(matcher.group(1), true);
        };
    }

    private Function<FormMapping, WebPageFileStore> formMappingToFileStore() {
        return mapping -> {
            final String formUUID = mapping.getTargetForm().getContent();
            final WebPageFileStore store = fileStoreFromFormUUID(formUUID);
            if (store == null) {
                BonitaStudioLog.warning(String.format("Page with id %s doesn't exist.", formUUID),
                        UIDesignerPlugin.PLUGIN_ID);
            }
            return store;
        };
    }

    private WebPageFileStore fileStoreFromFormUUID(final String formUUID) {
        checkArgument(!isNullOrEmpty(formUUID));
        return repositoryAccessor.getRepositoryStore(WebPageRepositoryStore.class).getChild(formUUID, true);
    }

    private Predicate<FormMapping> withInternalType() {
        return mapping -> mapping.getType() == FormMappingType.INTERNAL && mapping.getTargetForm().hasContent();
    }

    public Set<IRepositoryFileStore<?>> getRelatedFileStore(final WebPageFileStore webPageFileStore)
            throws BarResourceCreationException, IOException {
        final Set<String> zipEntries = findFormRelatedEntries(webPageFileStore);
        final Set<IRepositoryFileStore<?>> result = new HashSet<>();
        result.addAll(relatedFragments(zipEntries));
        result.addAll(relatedWidgets(zipEntries));
        return result;
    }

}
