package sapsan.module.storage

import com.cloudbees.groovy.cps.NonCPS
import sapsan.core.Pipeline
import sapsan.module.Module

class Nexus extends Module {

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
