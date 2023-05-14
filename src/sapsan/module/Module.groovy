package sapsan.module

import com.cloudbees.groovy.cps.NonCPS
import sapsan.core.Context
import sapsan.util.Log

abstract class Module extends Context {

    protected Module() {
        Log.info "Initializing module '${this.class.name}'"
        checkProperties()
    }

    /**
     * Определяем, откуда модуль берет свои свойства
     */
    protected abstract Map getProperties()

    /**
     * Инициализация свойств модуля (во время выполнения шага)
     * @param properties
     */
    protected abstract void initProperties()

    /**
     * Список свойств для проверки перед запуском
     * @param properties
     */
    @NonCPS
    protected abstract void checkProperties()

    /**
     * Выполнение модуля. Содержит инициализацию параметров и шага пайплайна
     */
    abstract def execute()

}
