package sapsan.core

import groovy.transform.stc.ClosureParams
import groovy.transform.stc.SimpleType
import sapsan.util.Log

final class Pipeline extends Context {

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
    static ArrayList<Stage> stages = []

    /**
     * Запуск трубы
     * @param pipeline вызывающий объект (Jenkinsfile)
     * @param node метка агента Jenkins
     * @param closure выполняемые действия
     */
    static void run(Script pipeline, String node = 'linux', Closure closure) {
        Context.pipeline = pipeline
        pipeline.node(node) {
            pipeline.ansiColor('xterm') {

                pipeline.stage("Initialize Pipeline") {
                    pipeline.cleanWs()
                    configure()

                    // Общая информация
                    Log.info Job.info
                    Log.info Job.info
                    Log.info Pipeline.info
                    Log.info Config.info
                    Log.var("properties", Config.properties)
                    Log.var("parameters", Config.parameters)

                    // Инициализация шагов
                    closure()
                }

                // Выполнение действий, указанных в каждом шаге
                for (int i = 0; i < stages.size(); ++i) {
                    stages[i].execute()
                }

            }
        }
    }

    private static void configure() {
        // Определение типа
        type = Context.pipeline.env.BRANCH_NAME ? Type.MULTIBRANCH : Type.CLASSIC

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
    }

    /**
     * Регистрация шага в пайплайне.
     * Тело шага выполнится после полной инициализации всего пайплайна
     * @see Stage
     */
    static void stage(String name, @ClosureParams(value = SimpleType, options = "Stage") Closure steps) {
        stages << new Stage(name, steps)
    }

    static String getInfo() {
        """
        [Pipeline Information]
        type=$type
        task=$task
        """.stripIndent()
    }
}
