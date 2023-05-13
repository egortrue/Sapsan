package sapsan.module

import com.cloudbees.groovy.cps.NonCPS
import sapsan.core.Context

abstract class Module extends Context {

    @NonCPS
    protected abstract void initParameters(Map parameters)
    
}