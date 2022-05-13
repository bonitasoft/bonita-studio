import java.util.zip.ZipFile
import java.util.zip.ZipException

print "Verifying patched plugins packaging in ${properties['root.dir']}"

def rootFolder =  new File(properties['root.dir'])
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
        assert providerName.contains('bonitasoft') : "Provider name should contains Bonitasoft in ${file.name} but found $providerName"
      } catch (ZipException ex) {
         println "Unable to open file ${file.name}"
      }
}

println success()

def static String color(String text, String ansiValue) {
        ansiValue + text + '\u001B[0m'
}

def static String success() {
    '....' + color('✓','\u001B[32m')
}
                      