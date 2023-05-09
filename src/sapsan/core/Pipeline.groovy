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
     * @param node метка агента Jenkins
     * @param closure выполняемые действия
     */
    static void run(Script script, String node = 'linux', Closure closure) {
        Context.script = script
        configure()

        script.node(node) {
            script.ansiColor('xterm') {
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
        JOB_NAME=${env.JOB_NAME}
        JOB_BASE_NAME=${env.JOB_BASE_NAME}
        JOB_URL=${env.JOB_URL}
        BUILD_URL=${env.BUILD_URL}
        NODE_NAME=${env.NODE_NAME}
        NODE_LABELS=${env.NODE_LABELS}"""

        type = env.JOB_NAME.contains('/') ? Type.MULTIBRANCH : Type.CLASSIC
        
        Log.info "Pipeline.Type = $type"
    }

}
