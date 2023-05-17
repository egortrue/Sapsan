package sapsan.module


import sapsan.core.Config
import sapsan.core.Context
import sapsan.core.Pipeline
import sapsan.util.Log

abstract class Module extends Context {

    /**
     * Выполнение модуля. Содержит инициализацию параметров и шага пайплайна
     */
    def call(def override = false) {
        //initCustomScript()
        checkProperties(properties)
        Pipeline.stage(this.class.simpleName) {
            initProperties(properties)
            execute()
        }
    }

    /**
     * Определяем, откуда модуль берет свои свойства
     */
    protected Map getProperties() {
        try {
            assert Config.properties[this.class.simpleName] != null
        } catch (AssertionError e) {
            Log.error("Property '${this.class.simpleName}' not found in '${Config.propertiesFile}'")
        }

        return Config.properties[this.class.simpleName]
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

    protected abstract void execute()
}
