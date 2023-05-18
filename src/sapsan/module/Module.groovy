package sapsan.module


import sapsan.core.Context
import sapsan.core.Job
import sapsan.util.Log

abstract class Module extends Context {

    protected Map moduleProperties
    protected Map moduleParameters

    /**
     * Валидация свойств перед запуском (на шаге инициализации всего пайплайна)
     */
    protected abstract void precheck()

    /**
     * Тело шага. Выполняемые действия (после инициализации всего пайплайна)
     */
    protected abstract void run()

    /**
     * Выполнение модуля.
     * Содержит инициализацию параметров и шага пайплайна.
     * @param path путь к файлу для выполнения
     * @return экземпляр модуля
     */
    static void execute(String path = null) {
        Log.var(this.class.name)
//        instance.precheck(properties)
//        Pipeline.stage(instance.stageName) {
//            instance.run(properties)
//        }
    }

    /**
     * Название шага для отображения в Jenkins
     */
    protected String getStageName() {
        return this.class.simpleName
    }

    /**
     * Определяем откуда модуль берет свои свойства
     */
//    protected Map getProperties() {
//        if (Config.properties[this.class.simpleName] == null) {
//            Log.error("Property '${this.class.simpleName}' not found in '${Config.propertiesFile}'")
//        }
//        return Config.properties[this.class.simpleName]
//    }


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
