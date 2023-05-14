package sapsan.module


import sapsan.core.Context

abstract class Module extends Context {

    protected Map properties

    protected Module() {

    }

    /**
     * Инициализация свойств модуля (во время выполнения шага)
     * @param properties
     */
    abstract void initProperties()

    /**
     * Список свойств для проверки перед запуском
     * @param properties
     */
    abstract void checkProperties()

    /**
     * Выполнение модуля. Содержит инициализацию параметров и шага пайплайна
     */
    abstract def execute()

}
