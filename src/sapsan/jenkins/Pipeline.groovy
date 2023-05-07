package sapsan.jenkins

import groovy.transform.stc.ClosureParams
import groovy.transform.stc.SimpleType
import sapsan.core.Context

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

    static List<Stage> stages = new ArrayList<Stage>()
    static Type type = Type.NOT_DEFINED
    static Task task = Task.NOT_DEFINED

    /**
     * Запуск трубы
     * @param node метка агента Jenkins
     * @param closure выполняемые действия
     */
    static void run(String node = 'linux', Closure closure) {
        pipelineScript.node(node) {
            pipelineScript.ansiColor('xterm') {
                closure.call()
//        stages.each { stage -> stage.call()}
            }
        }
    }


    /**
     * Обертка создания шага пайплайна
     * @see Stage
     */
    static void stage(String name, @ClosureParams(value = SimpleType, options = "Stage") Closure steps) {
        stages << new Stage(name, steps)
    }

}
