/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.application.ui.control.model.dependency;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ArtifactDependencyLoader {

    private ObjectMapper objectMapper = new ObjectMapper();
    private TypeReference<List<BonitaArtifactDependency>> typeReference = new TypeReference<>() {
    };
    private MarketplaceIconLoader marketplaceIconLoader;

    public ArtifactDependencyLoader(MarketplaceIconLoader marketplaceIconLoader) {
        this.marketplaceIconLoader = marketplaceIconLoader;
    }

    public List<BonitaArtifactDependency> load(Path target) {
        if (target.toFile().exists()) {
            try {
                return objectMapper.readValue(target.toFile(), typeReference)
                        .stream()
                        .sorted()
                        .peek(dep -> dep.setIconImage(marketplaceIconLoader.load(dep)))
                        .collect(Collectors.toList());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return Collections.emptyList();
    }

}
