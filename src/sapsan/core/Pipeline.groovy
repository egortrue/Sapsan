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
        DELIVERY,
        TESTING,
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
        Context.script = script
        script.node(node) {
            script.ansiColor('xterm') {
                configure()
                closure.call()
                //stages.each { stage -> stage.call()}
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

    private static void configure() {
        Log.info """\
JOB_NAME=${script.env.JOB_NAME}
JOB_BASE_NAME=${script.env.JOB_BASE_NAME}
JOB_URL=${script.env.JOB_URL}
BUILD_URL=${script.env.BUILD_URL}
NODE_NAME=${script.env.NODE_NAME}
NODE_LABELS=${script.env.NODE_LABELS}"""

        type = script.env.JOB_NAME.contains('/') ? Type.MULTIBRANCH : Type.CLASSIC

        Log.info "Pipeline.Type = $type"
        Log.info Job.baseUrl
        Log.info Job.path
    }

}
