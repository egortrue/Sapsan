package sapsan.module

import com.cloudbees.groovy.cps.NonCPS
import sapsan.core.Context
import sapsan.core.Job
import sapsan.core.Pipeline
import sapsan.util.Log

abstract class Module extends Context {

    private static LinkedList<Module> modules = []

    /**
     * Название шага для отображения в Jenkins
     */
    protected abstract String getName()

    /**
     * Валидация свойств перед запуском модуля (на шаге инициализации всего пайплайна)
     */
    protected abstract void init()

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
    static Module execute(def module) {

        // Определение модуля
        Class moduleClass
        Module instance
        if (module instanceof GString || module instanceof String) {
            moduleClass = load(module)
        } else if (module instanceof Class) {
            moduleClass = module
        } else {
            Log.error("Module execution failed: Wrong paramerter 'module'=$module")
        }
        instance = moduleClass.getDeclaredConstructor().newInstance()
        modules << instance

        // Запуск модуля
        instance.init()
        Pipeline.stage(instance.name) {
            instance.execute()
        }

        return instance
    }

    @NonCPS
    static Module getModule(String name) {
        modules.find({ it.name == name })
    }

    /**
     * Динамическая загрузка и подключение пользовательских модулей
     */
    private static Class load(String path) {
        Class module
        try {
            String scriptText = Context.pipeline.libraryResource(path)
            Context.pipeline.prependToFile(file: "${Job.tag}/$path", content: scriptText)
            module = Context.pipeline.load("${Job.tag}/$path")
            Context.pipeline.sh("rm -f ${Job.tag}/$path")
        } catch (Exception e) {
            Log.error("Custom module loading threw exception: $e.message")
        }
        return module
    }

}
