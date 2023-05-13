package sapsan.module

import com.cloudbees.groovy.cps.NonCPS
import groovy.json.JsonOutput
import sapsan.core.Context
import sapsan.util.Log

abstract class Module extends Context {

    Module() {
        //Map parameters = Pipeline.staticParameters[this.class.simpleName]
        //Log.info "Create component \"${this.class.simpleName}\"\n" +
        //"with parameters: ${JsonOutput.prettyPrint(JsonOutput.toJson(parameters))}"
        initParameters(parameters)
    }

    @NonCPS
    protected abstract void initParameters(Map parameters)

}