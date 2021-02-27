/**
 * Copyright (C) 2019 BonitaSoft S.A.
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
package org.bonitasoft.studio.groovy;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.codehaus.jdt.groovy.model.ModuleNodeMapper.ModuleNodeInfo;
import org.eclipse.jdt.core.WorkingCopyOwner;
import org.eclipse.jdt.internal.core.PackageFragment;

public class BonitaScriptGroovyCompilationUnit extends GroovyCompilationUnit {

    private Map<String, ScriptVariable> context;
    private Map<String, ClassNode> resolvedTypes = new HashMap<>();

    public BonitaScriptGroovyCompilationUnit(PackageFragment parent, String name, WorkingCopyOwner owner) {
        super(parent, name, owner);
    }

    @Override
    public ModuleNodeInfo getModuleInfo(boolean force) {
        ModuleNodeInfo moduleInfo = super.getModuleInfo(force);
        if (context != null) {
            ClassNode scriptClassDummy = moduleInfo.module.getScriptClassDummy();
            context.values().forEach(var -> {
                String typeName = var.getType();
                ClassNode resolvedType = resolvedTypes.computeIfAbsent(typeName, t -> moduleInfo.resolver.resolve(t));
                scriptClassDummy.addField(var.getName(),
                        FieldNode.ACC_PUBLIC | FieldNode.ACC_FINAL,
                        resolvedType,
                        null);
            });

        }
        return moduleInfo;
    }

    public void setContext(Map<String, ScriptVariable> context) {
        this.context = context;

    }

    public Map<String, ScriptVariable> getContext() {
        return context;
    }

}
