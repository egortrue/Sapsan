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
        precheck()
        Pipeline.stage(stageName) {
            init()
            execute()
        }
    }

    /**
     * Определяем откуда модуль берет свои свойства
     */
    protected Map getProperties() {
        if (Config.properties[this.class.simpleName] == null) {
            Log.error("Property '${this.class.simpleName}' not found in '${Config.propertiesFile}'")
        }
        return Config.properties[this.class.simpleName]
    }

    /**
     * Название шага для отображения в Jenkins
     */
    protected String getStageName() {
        return this.class.simpleName
    }

    /**
     * Валидация свойств перед запуском на шаге инициализации всего пайплайна
     * @param properties
     */
    protected abstract void precheck()

    /**
     * Инициализация свойств модуля (во время выполнения шага)
     * @param properties
     */
    protected abstract void init()

    /**
     * Тело шага. Выполняемые действия
     */
    protected abstract void execute()
}
