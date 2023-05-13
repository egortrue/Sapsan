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
        DEPLOYMENT,
    }

    static Type type = Type.NOT_DEFINED
    static Task task = Task.NOT_DEFINED
    static Map properties
    static Map parameters
    static def stages = []

    /**
     * Запуск трубы
     * @param script объект jenkinsfile
     * @param node метка агента Jenkins
     * @param closure выполняемые действия
     */
    static void run(Script script, String node = 'linux', Closure closure) {
        Context.script = script
        script.node(node) {
            script.ansiColor('xterm') {

                Pipeline.stage("Init") {
                    script.cleanWs()
                    configure()

                    Log.info Job.info
                    Log.info Pipeline.info
                    Log.info Configuration.info

                    closure.call()
                }

                stages.each {
                    it.call()
                }
            }
        }
    }

    private static void configure() {
        type = Job.name.contains('/') ? Type.MULTIBRANCH : Type.CLASSIC

        // Определение задачи
        if (type == Type.CLASSIC) {
            if (Job.name.toLowerCase().contains("deploy"))
                task = Task.DEPLOYMENT
            else if (Job.name.toLowerCase().contains("test"))
                task = Task.TESTING
            else {
                Log.error "Pipeline.task not defined!"
            }
        } else if (type == Type.MULTIBRANCH) {
            task = Task.BUILD
        }

        properties = script.readProperties(Configuration.properties)
        parameters = script.readProperties(text: script.libraryResource(Configuration.parameters))
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
