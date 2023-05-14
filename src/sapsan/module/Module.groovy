package sapsan.module

import com.cloudbees.groovy.cps.NonCPS
import sapsan.core.Context

interface Module {

    /**
     * Инициализация свойств модуля (во время выполнения шага)
     * @param properties
     */
    void initProperties(def properties)

    /**
     * Список свойств для проверки перед запуском
     * @param properties
     */
    void checkProperties(def properties)

}
