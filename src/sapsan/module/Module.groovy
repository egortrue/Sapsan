package sapsan.module

import com.cloudbees.groovy.cps.NonCPS
import groovy.transform.InheritConstructors
import sapsan.core.Context

@Singleton
@InheritConstructors
abstract class Module extends Context {

    Module() {
        //Map parameters = Pipeline.staticParameters[this.class.simpleName]
        Map parameters = [:]
        //Log.info "Create component \"${this.class.simpleName}\"\n" +
        //"with parameters: ${JsonOutput.prettyPrint(JsonOutput.toJson(parameters))}"
        initParameters(parameters)
    }

    @NonCPS
    protected abstract void initParameters(Map parameters)
}