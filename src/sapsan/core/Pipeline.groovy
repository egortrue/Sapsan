package sapsan.core

import groovy.transform.stc.ClosureParams
import groovy.transform.stc.SimpleType
import sapsan.util.Log

class Pipeline extends Context {

    enum Type {
        NOT_DEFINED,
        CLASSIC,
        MULTIBRANCH
    }

    enum Task {
        NOT_DEFINED,
        BUILD,
        TESTING,
        DELIVERY,
        DEPLOYMENT,
    }

    static Type type = Type.NOT_DEFINED
    static Task task = Task.NOT_DEFINED
    static def stages = []

    /**
     * Запуск трубы
     * @param script объект jenkinsfile
     * @param node метка агента Jenkins
     * @param closure выполняемые действия
     */
    static void run(Script script, String node = 'linux', Closure closure) {

        // Настройка
        Context.script = script
        script.cleanWs()
        type = Job.name.contains('/') ? Type.MULTIBRANCH : Type.CLASSIC

        // Запуск
        script.node(node) {
            script.ansiColor('xterm') {

                Log.info Job.info
                Log.info Pipeline.info

                closure.call()
                stages.each { stage -> stage.call() }
            }
        }
    }

    /**
     * Обертка создания шага пайплайна
     * Регистрация шага в пайплайне
     * @see Stage
     */
    static void stage(String name, @ClosureParams(value = SimpleType, options = "Stage") Closure steps) {
        stages << new Stage(name, steps)
    }

    static String getInfo() {
        """
        [Pipeline Information]
        Pipeline.type=$type
        Pipeline.task=$task
        """.stripIndent()
    }

}
