import java.util.zip.ZipFile
import java.util.zip.ZipException

def productsDir =  new File(properties['products.dir'])
def allProducts = [  
                    productsDir.toPath().resolve('linux').resolve('gtk').resolve('x86_64').toFile(),
                    productsDir.toPath().resolve('win32').resolve('win32').resolve('x86_64').toFile(),
                    productsDir.toPath().resolve('macosx').resolve('cocoa').resolve('x86_64')
                                .resolve(properties['macosx.appName'])
                                .resolve('Contents')
                                .resolve('Eclipse').toFile()
                  ]
                   
allProducts.each { rootFolder -> verifyProduct(rootFolder, productsDir) }


def verifyProduct(rootFolder, productsDir) {
           
    println "⚙️  Verifying patched plugins packaging in ${rootFolder}"
        
    def pluginsFolder = rootFolder.toPath().resolve('plugins').toFile()
    def patchedPluginsFilter = new FilenameFilter() {
        boolean accept(File path, String filename) {
            return (filename.startsWith('org.eclipse.ui.views.properties.tabbed_')
                    || filename.startsWith('org.eclipse.datatools.modelbase.sql.query_')
                    || filename.startsWith('org.eclipse.gmf.runtime.lite.svg_') )
                && !filename.contains('nl_')
        }
    }
    def plugins = pluginsFolder.listFiles(patchedPluginsFilter)
    
    assert plugins.size() == 3 : 'Invalid number of patched plugins collected'
                        
    plugins.each { file ->
        try{
            def providerName = readProviderName(file)
            
            if(!providerName.contains('bonitasoft')) {
                warning("${file.name} is not the patched version !")
                def patchPluginFolder = file.name.substring(0, file.name.indexOf('_'))
                def targetFolder = productsDir.parentFile.parentFile.parentFile.parentFile.toPath()
                        .resolve('patched-plugins')
                        .resolve(patchPluginFolder)
                        .resolve('target')
                        .toFile()
                println "Looking for patched jar in $targetFolder"
                def patchedPluginFile = targetFolder
                        .listFiles( new FilenameFilter() {
                            boolean accept(File path, String filename) {
                                println "File found: $filename"
                                return filename.startsWith(patchPluginFolder) && filename.endsWith('.jar') && !filename.contains('SNAPSHOT')
                            }
                        })
                        .find{ it }
                if(patchedPluginFile) {
                    fix "Replacing ${file} with its pacthed version $patchedPluginFile"
                    file.withDataOutputStream { os->
                        patchedPluginFile.withDataInputStream { is->
                           os << is
                        }
                     }
                     providerName = readProviderName(file)
                } else {
                    warning("No valid patched version of ${file.name} found.")
                }
            }
            if(!providerName.contains('bonitasoft')) {
                throw new org.apache.maven.BuildFailureException( "Provider name should contains Bonitasoft in ${file.name} but found $providerName")
            }
          } catch (ZipException ex) {
             println "Unable to open file ${file.name}"
          }
    }

}

success('All packages have valid patched plugins')


def static warning(message) {
   println  '⚠️' + "  $message"
}

def static fix(message) {
    println  '💊' + "  $message"
}

def String readProviderName(file) {
    try(def zip = new ZipFile(file)){
        def pluginPropertiesEntry = zip.entries().find { it.name.contains('plugin.properties') }
        assert pluginPropertiesEntry != null : "No plugins.properties file found in ${file.name}"
        def p = new Properties();
        p.load(new StringReader(zip.getInputStream(pluginPropertiesEntry).text));
        def providerName = ''
        if(p['providerName'] != null) {
            providerName = p['providerName'].toLowerCase()
        }else if(p['Plugin.providerName'] != null) {
            providerName = p['Plugin.providerName'].toLowerCase()
        }
        return providerName
    } 
}

def static success(message) {
    println "✨ $message"
}
                      