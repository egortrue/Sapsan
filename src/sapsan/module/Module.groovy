package sapsan.module

import sapsan.core.Context
import sapsan.core.Job
import sapsan.core.Pipeline
import sapsan.util.Log

abstract class Module extends Context {

    /**
     * Валидация свойств перед запуском модуля (на шаге инициализации всего пайплайна)
     */
    protected abstract void precheck()

    /**
     * Запуск модуля (после инициализации всего пайплайна)
     */
    protected abstract void run()

    /**
     * Шаблонный метод (Поведенческий шаблон проектирования)
     * Содержит инициализацию параметров и шага пайплайна.
     * @param path путь к файлу для выполнения
     * @return экземпляр модуля
     */
    static def execute(def module) {
        def instance
        if (module instanceof GString || module instanceof String) {
            instance = load(module)
        } else if (module instanceof Class) {
            instance = module.getDeclaredConstructor().newInstance()
        } else {
            Log.error("Module execution failed: Wrong paramerter 'module'=$module")
        }

        instance.precheck([:])
        Pipeline.stage(instance.name) {
            instance.run([:])
        }

        return instance
    }

    /**
     * Название шага для отображения в Jenkins
     */
    protected String getName() {
        return this.class.simpleName
    }

    /**
     * Динамическая загрузка и подключение пользовательских скриптов
     */
    private static Script load(String path) {
        Script script
        try {
            String scriptText = Context.pipeline.libraryResource(path)
            Context.pipeline.prependToFile(file: "${Job.tag}/$path", content: scriptText)
            script = Context.pipeline.load "${Job.tag}/$path"
            Context.pipeline.sh "rm -f ${Job.tag}/$path"
        } catch (Exception e) {
            Log.error("Custom script loading threw exception: $e.message")
        }
        return script
    }

}
