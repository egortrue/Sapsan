package sapsan.module

import com.cloudbees.groovy.cps.NonCPS
import sapsan.core.Context

interface Module {
    
    void checkProperties(def properties)

}
