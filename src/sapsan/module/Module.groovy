package sapsan.module

import com.cloudbees.groovy.cps.NonCPS
import groovy.json.JsonOutput
import sapsan.core.Context
import sapsan.core.Pipeline
import sapsan.util.Log

abstract class Module extends Context {


    private static Module _instance

    static Module getInstance() {
        if (_instance == null)
            _instance = this.class.getDeclaredConstructor().newInstance()
        return _instance
    }

    Module() {
        //Map parameters = Pipeline.staticParameters[this.class.simpleName]
        Map parameters = [:]
        Log.info "Create component \"${this.class.simpleName}\"\n" +
                "with parameters: ${JsonOutput.prettyPrint(JsonOutput.toJson(parameters))}"
        initParameters(parameters)
    }

    @NonCPS
    protected abstract void initParameters(Map parameters)

}