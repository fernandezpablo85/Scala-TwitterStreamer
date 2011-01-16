require 'buildr/scala'

M2_REPO = 'http://repo1.maven.org/maven2/'
SCALATOOLS_REPO = 'http://scala-tools.org/repo-releases/'

repositories.remote << M2_REPO
repositories.remote << SCALATOOLS_REPO

COMMONS_HTTP = 'commons-httpclient:commons-httpclient:jar:3.1'
CONFIGGY = 'net.lag:configgy:jar:1.5.2'
COMMONS_CODEC = 'commons-codec:commons-codec:jar:1.4'

CONFIGGY_URL = 'https://github.com/downloads/ijuma/configgy/configgy_2.8.0.RC3-1.5.2-SNAPSHOT.jar'

download(artifact(CONFIGGY) => CONFIGGY_URL)

define 'scribe-twitter-stream-client' do
  project.version = "1.0"
  compile.with COMMONS_HTTP, CONFIGGY, COMMONS_CODEC
  
end