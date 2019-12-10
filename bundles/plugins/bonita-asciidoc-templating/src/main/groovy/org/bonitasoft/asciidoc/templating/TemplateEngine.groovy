/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.asciidoc.templating

import org.bonitasoft.asciidoc.templating.model.Project
import org.codehaus.groovy.control.customizers.ImportCustomizer

import groovy.text.markup.MarkupTemplateEngine
import groovy.text.markup.TemplateConfiguration
import groovy.transform.CompileStatic

@CompileStatic
class TemplateEngine {

    Locale locale
    File templateDir

    def TemplateEngine(File templateDir) {
        this.templateDir = templateDir
    }

    def TemplateEngine(File templateDir, Locale locale) {
        this.templateDir = templateDir
        this.locale = locale
    }

    def run(String templatePath, File outputFile, Map context) {
        def config = new TemplateConfiguration()
        config.locale = locale ?: Locale.getDefault()
        ClassLoader templateClassloader = new URLClassLoader([templateDir.toURI().toURL()] as URL[])
        context << [messages:ResourceBundle.getBundle('messages', config.locale, templateClassloader)]
        config.setBaseTemplateClass(AsciiDocTemplate)
        def templateEngine = new MarkupTemplateEngine(TemplateEngine.getClassLoader(), templateDir, config)
        def compilerConfig = templateEngine.getCompilerConfiguration()
        compilerConfig
                .addCompilationCustomizers(new ImportCustomizer()
                .addStarImports('org.bonitasoft.asciidoc.templating.model',
                    'org.bonitasoft.asciidoc.templating.model.bdm',
                    'org.bonitasoft.asciidoc.templating.model.process',
                    'org.bonitasoft.asciidoc.templating')
                .addImports('groovy.transform.Field'))

        def template = templateEngine.createTemplateByPath(templatePath)

        outputFile.withWriter('UTF-8'){ out ->
            template.make(context).writeTo(out)
        }
    }
    
}
