package sapsan.module

import com.cloudbees.groovy.cps.NonCPS
import groovy.json.JsonOutput
import sapsan.core.Context
import sapsan.core.Pipeline

abstract class Module extends Context {

    Module() {
        Map parameters = Pipeline.staticParameters[this.class.simpleName]
        log "Create component \"${this.class.simpleName}\"\nwith parameters: ${JsonOutput.prettyPrint(JsonOutput.toJson(parameters))}"
        initParameters(parameters)
    }

    @NonCPS
    protected abstract void initParameters(Map parameters)

}