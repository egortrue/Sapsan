package sapsan.module

import sapsan.core.Configuration
import sapsan.core.Context

abstract class Module extends Context {

    /**
     * Определяем, откуда модуль берет свои свойства
     */
    protected Map getProperties() {
        assert Configuration.properties[this.class.simpleName] != null
        return Configuration.properties[this.class.simpleName]
    }

    /**
     * Инициализация свойств модуля (во время выполнения шага)
     * @param properties
     */
    protected abstract void initProperties()

    /**
     * Список свойств для проверки перед запуском
     * @param properties
     */
    protected abstract void checkProperties()

    /**
     * Выполнение модуля. Содержит инициализацию параметров и шага пайплайна
     */
    abstract def execute()

}
