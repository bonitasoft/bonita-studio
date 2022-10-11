def branch = properties['crowdin.branch']
def locale = properties['crowdin.locale']
def baseDir = properties['project.basedir']
def url = "https://crowdin.com/backend/download/project/bonita/${locale}.zip"
def output = File.createTempFile("${locale}_locale", '.zip')

println "Downloading localization archive for $locale..."
redirectFollowingDownload(url, output)
println "Archived downloaded to $output"

def zipFile = new java.util.zip.ZipFile(output)

zipFile.entries().findAll{ !it.directory && it.name.startsWith("${branch}/studio/community/") }.each {
      def filePath = it.name.replace("${branch}/studio/community/", '')
      def target = new File(baseDir, filePath)
      if(target.parentFile.exists()) {
          if(!target.exists()) {
              target.createNewFile()
          }
          target.withDataOutputStream { os->  
              println "Writing $target"
              os <<  zipFile.getInputStream(it)
          }
      }
}

def redirectFollowingDownload(String url, File outputFile) {
    while(url) {
      new URL(url).openConnection().with { conn ->
        conn.instanceFollowRedirects = false
        url = conn.getHeaderField( "Location" )
        if( !url ) {
          outputFile.withOutputStream { out ->
            conn.inputStream.with { inp ->
              out << inp
              inp.close()
            }
          }
        }
      }
    }
  }