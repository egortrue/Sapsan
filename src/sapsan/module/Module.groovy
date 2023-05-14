package sapsan.module

import com.cloudbees.groovy.cps.NonCPS
import sapsan.core.Context

abstract class Module extends Context {

    /**
     * Инициализация свойств модуля (во время выполнения шага)
     * @param properties
     */
    static abstract void initProperties(Map properties)

    /**
     * Список свойств для проверки перед запуском
     * @param properties
     */
    static abstract checkProperties(Map properties)

}
