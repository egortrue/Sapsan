package sapsan.components

import com.cloudbees.groovy.cps.NonCPS
import sapsan.core.Component
import sapsan.jenkins.Pipeline

class Nexus extends Component {

  private static Nexus _instance

  static Nexus getInstance() {
    if (_instance == null)
      _instance = new Nexus()
    return _instance
  }

  // Параметры сервиса
  private final String baseUrlSigma = "http://nexus.sigma.sber.ru/repository"
  private final String baseUrlAlpha = "http://nexus.alpha.sber.ru/repository"

  String repository
  String groupId
  String artifactId
  String artifactVersion

  @Override
  @NonCPS
  protected void initParameters(Map parameters) {
    repository = parameters["repository"]
    groupId = parameters["groupId"]
    artifactId = parameters["artifactId"]
    artifactVersion = parameters["artifactVersion"]

    assert repository != null
    assert groupId != null
    assert artifactId != null
    assert artifactVersion != null
  }

  private String getNexusBaseUrl() {
    // if (Context.net == Net.SIGMA) return baseUrlSigma
    // if (Context.net == Net.ALPHA) return baseUrlAlpha
    // throw new Exception("Сеть не определена")
    return baseUrlSigma
  }

  static void publishArtifact() {
    Pipeline.stage("Publish") {
      log "publishing artifact to $instance.artifactUrl"
    }
  }

  private String getArtifactUrl() {
      def groupIdNonRest = groupId.replace('.', '/')
    "$nexusBaseUrl/$repository/$groupIdNonRest/$artifactId/$artifactVersion/$artifactId-$artifactVersion"
  }
}
