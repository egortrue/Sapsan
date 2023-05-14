package sapsan.module


import sapsan.core.Context

abstract class Module extends Context {

    /**
     * Инициализация свойств модуля (во время выполнения шага)
     * @param properties
     */
    abstract void initProperties(Map properties)

    /**
     * Список свойств для проверки перед запуском
     * @param properties
     */
    abstract void checkProperties(Map properties)

    /**
     * Выполнение модуля. Содержит инициализацию параметров и шага пайплайна
     */
    abstract void call()

}
