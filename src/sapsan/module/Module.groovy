package sapsan.module

import sapsan.core.Context
import sapsan.core.Job
import sapsan.core.Pipeline
import sapsan.util.Log

abstract class Module extends Context {

    static LinkedList<Object> modules = []

    /**
     * Название шага для отображения в Jenkins
     */
    protected abstract String getName()

    /**
     * Валидация свойств перед запуском модуля (на шаге инициализации всего пайплайна)
     */
    protected abstract void precheck()

    /**
     * Запуск модуля (после инициализации всего пайплайна)
     */
    protected abstract void execute()

    /**
     * Шаблонный метод (Поведенческий шаблон проектирования)
     * Содержит инициализацию параметров и шага пайплайна.
     * @param path путь к файлу для выполнения
     * @return экземпляр модуля
     */
    static def execute(def module) {

        // Определение модуля
        Module instance
        if (module instanceof GString || module instanceof String) {
            instance = load(module)
        } else if (module instanceof Class) {
            instance = module.getDeclaredConstructor().newInstance()
        } else {
            Log.error("Module execution failed: Wrong paramerter 'module'=$module")
        }

        // Запуск модуля
        instance.precheck()
        Pipeline.stage(instance.name) {
            instance.execute()
        }

        return instance
    }

    /**
     * Динамическая загрузка и подключение пользовательских скриптов
     */
    private static Module load(String path) {
        Module module
        try {
            String scriptText = Context.pipeline.libraryResource(path)
            Context.pipeline.prependToFile(file: "${Job.tag}/$path", content: scriptText)
            module = Context.pipeline.load("${Job.tag}/$path")
            Context.pipeline.sh("rm -f ${Job.tag}/$path")
        } catch (Exception e) {
            Log.error("Custom script loading threw exception: $e.message")
        }
        return module
    }

}
