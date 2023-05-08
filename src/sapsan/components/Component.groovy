package sapsan.components

import com.cloudbees.groovy.cps.NonCPS
import groovy.json.JsonOutput
import sapsan.Context
import sapsan.jenkins.Pipeline

abstract class Component extends Context {

  Component() {
    Map parameters = Pipeline.staticParameters[this.class.simpleName]
    log "Create component \"${this.class.simpleName}\"\nwith parameters: ${JsonOutput.prettyPrint(JsonOutput.toJson(parameters))}"
    initParameters(parameters)
  }

  @NonCPS
  protected abstract void initParameters(Map parameters)

}