package sapsan.module

import sapsan.core.Configuration
import sapsan.core.Context
import sapsan.core.Pipeline
import sapsan.util.Log

abstract class Module extends Context {

    /**
     * Выполнение модуля. Содержит инициализацию параметров и шага пайплайна
     */
    def execute(def override = false) {

        try {
            assert this.class.simpleName == 'Module'
        } catch (AssertionError e) {
            Log.error(e.message)
        }

        //initCustomScript()
        checkProperties(properties)
        Pipeline.stage(buildScript.name) {
            initProperties(properties)
            buildScript.execute(properties)
        }
    }

    /**
     * Определяем, откуда модуль берет свои свойства
     */
    protected Map getProperties() {
        try {
            assert Configuration.properties[this.class.simpleName] != null
        } catch (AssertionError e) {
            Log.error("Property '${this.class.name}' not found in ${Configuration.propertiesFile}")
        }

        return Configuration.properties[this.class.simpleName]
    }

    /**
     * Валидация свойств перед запуском на шаге инициализации всего пайплайна
     * @param properties
     */
    protected abstract void checkProperties(Map properties)

    /**
     * Инициализация свойств модуля (во время выполнения шага)
     * @param properties
     */
    protected abstract void initProperties(Map properties)
}
