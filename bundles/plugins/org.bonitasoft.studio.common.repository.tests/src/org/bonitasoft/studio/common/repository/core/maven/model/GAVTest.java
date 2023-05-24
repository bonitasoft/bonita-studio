/**
 * Copyright (C) 2022 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core.maven.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.maven.model.Dependency;
import org.junit.jupiter.api.Test;

class GAVTest {
    
    @Test
    void newGavFromDependency() throws Exception {
        var gav = new GAV(aDependency("g", "a", "1", null, null, null));
        
        assertThat(gav.getGroupId()).isEqualTo("g");
        assertThat(gav.getArtifactId()).isEqualTo("a");
        assertThat(gav.getVersion()).isEqualTo("1");
        assertThat(gav.getClassifier()).isNull();
        assertThat(gav.getType()).isEqualTo("jar");
        assertThat(gav.getScope()).isEqualTo("compile");
    }
    
    @Test
    void isSameGavIgnoreVersion() throws Exception {
        var gav = new GAV(aDependency("g", "a", "1", null, null, null));
        var gav1 = new GAV(aDependency("g", "a", "2", null, null, null));
        
        assertThat(gav.isSameAs(gav1)).isTrue();
    }
    
    @Test
    void isEqualGavDoesNotIgnoreVersion() throws Exception {
        var gav = new GAV(aDependency("g", "a", "1", null, null, null));
        var gav1 = new GAV(aDependency("g", "a", "2", null, null, null));
        
        assertThat(gav.equals(gav1)).isFalse();
    }
    
    @Test
    void isEqualGavWithDefaultType() throws Exception {
        var gav = new GAV(aDependency("g", "a", "1", null, "jar", null));
        var gav1 = new GAV(aDependency("g", "a", "1", null, null, null));
        
        assertThat(gav.equals(gav1)).isTrue();
    }
    
    @Test
    void isEqualGavWithDefaultScope() throws Exception {
        var gav = new GAV(aDependency("g", "a", "1", null, null, "compile"));
        var gav1 = new GAV(aDependency("g", "a", "1", null, null, null));
        
        assertThat(gav.equals(gav1)).isTrue();
    }
    
    @Test
    void toLocalRepositoryPath() throws Exception {
        var gav = new GAV(aDependency("com.company.group", "my-artifact", "1.3.7", null, null, "compile"));
        var gavWithClassifier = new GAV(aDependency("com.company.group", "my-artifact", "1.3.7", "archived", "zip", "compile"));
        
        assertThat(gav.toLocalRepositoryPath()).isEqualTo("com/company/group/my-artifact/1.3.7/my-artifact-1.3.7.jar");
        assertThat(gavWithClassifier.toLocalRepositoryPath()).isEqualTo("com/company/group/my-artifact/1.3.7/my-artifact-1.3.7-archived.zip");
    }

    
    private static Dependency aDependency(String groupId, String artifactId, String version, String classifier, String type, String scope) {
        var dep = new Dependency();
        dep.setGroupId(groupId);
        dep.setArtifactId(artifactId);
        dep.setVersion(version);
        dep.setType(type);
        dep.setScope(scope);
        dep.setClassifier(classifier);
        return dep;
    }
}
