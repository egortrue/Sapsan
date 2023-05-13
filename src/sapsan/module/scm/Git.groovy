package sapsan.module.scm

import com.cloudbees.groovy.cps.NonCPS
import groovy.transform.InheritConstructors
import sapsan.core.Pipeline
import sapsan.module.Module
import sapsan.util.Log

import java.beans.Introspector

@InheritConstructors
class Git extends Module {
    String url
    String branch

    @Override
    @NonCPS
    void initParameters(Map parameters) {
        if (Pipeline.type == Pipeline.Type.CLASSIC) {
            url = parameters["url"]
            branch = parameters["branch"]
            assert url != null
            assert branch != null
        } else {
//            url = script.scm.userRemoteConfigs[0].url
        }
    }

    String getInfo() {
        String info = "[${this.class.simpleName.capitalize()} Information]\n"

        def properties = Introspector.getBeanInfo(this.getClass()).propertyDescriptors.findAll { pd ->
            !pd.name.equals("class") && pd.readMethod != null && !pd.syntheticConstructor
        }

        properties.each { property ->
            def value = this."${property.name}"
            println "Property name: ${property.name}, value: $value"
        }
    }


    static void checkout(String url = null, String branch = null) {

        if (Pipeline.type == Pipeline.Type.MULTIBRANCH) {
            script.checkout script.scm
        } else if (Pipeline.type == Pipeline.Type.CLASSIC) {
            checkout([
                    $class           : 'GitSCM',
                    branches         : [[name: '*/master']],
                    userRemoteConfigs: [[credentialsId: '<gitCredentials>', url: '<gitRepoURL>']]
            ])
        }

    }

}
