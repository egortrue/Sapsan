package sapsan.module

import com.cloudbees.groovy.cps.NonCPS
import sapsan.core.Context

abstract class Module extends Context {

    @NonCPS
    protected abstract void initParameters(Map parameters)

    @NonCPS
    String getInfo() {
        String info = "[${this.class.simpleName.capitalize()} Information]\n"

        def properties = this.metaClass.getProperties().findAll { property ->
            !property.synthetic && !Modifier.isStatic(property.getModifiers())
        }

        properties.each { property ->
            def value = this.getProperty(property.name)
            info += "${property.name}=$value\n"
        }
    }
}