/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.validation.constraints;

import static java.util.function.Predicate.not;

import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;

public class ProcessScriptsCompiler {

    private Map<String, Object> processes = new HashMap<>();

    public ProcessScriptsCompilationResult compileForErrors(Pool process) {
        try (var source = GroovySourceWriter.write(process)) {
            source.build();
            ProcessScriptsCompilationResult result = source.collectCompilationErrors();
            processes.put(ModelHelper.getEObjectID(process), result);
            return result;
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
            return null;
        }
    }

    public boolean contains(Pool process) {
        return processes.containsKey(ModelHelper.getEObjectID(process));
    }

    static class GroovySourceWriter implements Closeable {

        private static final String GROOVY_DEF_ID = "scripting-groovy";
        private static final Object SCRIPT_PARAMETER = "script";

        private Map<String, Path> sourceIndex = new HashMap<>();
        private IFolder srcFolder;

        public static GroovySourceWriter write(Pool process) {
            var source = new GroovySourceWriter();

            ModelHelper.getAllElementOfTypeIn(process, Expression.class).stream()
                    .filter(not(ModelHelper::isAnExpressionCopy))
                    .filter(expr -> ExpressionConstants.SCRIPT_TYPE.equals(expr.getType()))
                    .filter(expr -> ExpressionConstants.GROOVY.equals(expr.getInterpreter()))
                    .filter(Expression::hasContent)
                    .forEach(source::addExpression);

            ModelHelper.getAllElementOfTypeIn(process, Connector.class).stream()
                    .filter(c -> Objects.equals(c.getDefinitionId(), GROOVY_DEF_ID))
                    .flatMap(c -> c.getConfiguration().getParameters().stream())
                    .filter(p -> Objects.equals(p.getKey(), SCRIPT_PARAMETER))
                    .map(ConnectorParameter::getExpression)
                    .filter(Expression.class::isInstance)
                    .map(Expression.class::cast)
                    .filter(not(ModelHelper::isAnExpressionCopy))
                    .filter(Expression::hasContent)
                    .forEach(source::addExpression);

            return source;
        }

        public GroovySourceWriter() {
            IProject project = RepositoryManager.getInstance().getCurrentRepository().getProject();
            srcFolder = project.getFolder("src-providedGroovy");
        }

        public ProcessScriptsCompilationResult collectCompilationErrors() {
            var result = new ProcessScriptsCompilationResult();
            for (var entry : sourceIndex.entrySet()) {
                IFile file = srcFolder.getFile(entry.getValue().getFileName().toString());
                if (file.exists()) {
                    try {
                        IMarker[] markers = file.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_ONE);
                        if (markers != null) {
                            List<String> errorMessages = Stream.of(markers)
                                    .filter(m -> {
                                        try {
                                            return Objects.equals(m.getAttribute(IMarker.SEVERITY),
                                                    IMarker.SEVERITY_ERROR);
                                        } catch (CoreException e1) {
                                            return false;
                                        }
                                    })
                                    .map(m -> {
                                        try {
                                            return m.getAttribute(IMarker.MESSAGE);
                                        } catch (CoreException e) {
                                            return null;
                                        }
                                    })
                                    .map(String.class::cast)
                                    .filter(Objects::nonNull)
                                    .collect(Collectors.toList());
                            result.add(entry.getKey(), errorMessages);
                        }
                    } catch (CoreException e) {
                        e.printStackTrace();
                    }
                }
            }
            return result;
        }

        public void build() throws CoreException {
            srcFolder.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
            srcFolder.getProject().build(IncrementalProjectBuilder.INCREMENTAL_BUILD, new NullProgressMonitor());
        }

        public void addExpression(Expression expression) {
            try {
                String uuid = ModelHelper.getEObjectID(expression);
                Path sourceFilePath = srcFolder.getLocation().toFile().toPath().resolve(uuid + ".groovy");
                Files.writeString(sourceFilePath, expression.getContent(),
                        StandardCharsets.UTF_8);

                sourceIndex.put(ModelHelper.getEObjectID(expression), sourceFilePath);
            } catch (IOException e) {
                BonitaStudioLog.error(e);
            }
        }

        @Override
        public void close() {
            if (sourceIndex != null) {
                sourceIndex.values().stream().forEach(p -> PlatformUtil.delete(p.toFile(), new NullProgressMonitor()));
                try {
                    srcFolder.refreshLocal(IResource.DEPTH_ONE, new NullProgressMonitor());
                } catch (CoreException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }

    }

}
