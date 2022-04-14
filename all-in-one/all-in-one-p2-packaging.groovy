import groovy.xml.XmlSlurper
import groovy.xml.XmlUtil

println "Add platform specific plugins to bundles.info"

println properties['root.dir']

def rootFolder =  new File(properties['root.dir'])
def bundlesInfo = rootFolder.toPath().resolve('configuration')
                                     .resolve('org.eclipse.equinox.simpleconfigurator')
                                     .resolve('bundles.info')
                                     .toFile()
def pluginsFolder = rootFolder.toPath().resolve('plugins').toFile()

def bundles = []  

def platformSpecificFilter = new FilenameFilter() {
    boolean accept(File path, String filename) {
        return filename.contains("win32")
            || filename.contains("win")
    }
}

def plugins = pluginsFolder
                .listFiles(platformSpecificFilter)
                .each { file ->
                    def name = file.getName();
                    def splitted = name.split("_")
                    if(splitted.length >= 2){
                        def symbolicName = splitted[0];
                        def version = '';
                        if(name.indexOf("x86_64") != -1 || name.indexOf("nl_") != -1){
                            for(int i = 1 ; i< splitted.length-1 ;i++){
                                symbolicName = symbolicName + "_" + splitted[i]
                            }
                        }
                        if(name.indexOf(".jar") != -1){
                            version = name.substring(symbolicName.length()+1, name.indexOf(".jar"))
                        }else{
                            version = name.substring(symbolicName.length()+1)
                        }
                        bundles << [id: symbolicName, version: version, location: pluginsFolder.getParentFile().toURI().relativize(file.toURI()), startLevel: 4 ]
                    }
                }
                         

bundlesInfo.withWriterAppend { out ->
     bundles.each { bundle ->
        println "Append $bundle to bundles.info"
        out.println "${bundle.id},${bundle.version},${bundle.location},${bundle.startLevel},false"
     }
}
