package sapsan.core

import groovy.transform.stc.ClosureParams
import groovy.transform.stc.SimpleType
import sapsan.module.Module
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
    static ArrayList<Stage> stages = []

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

                script.stage("Configure") {
                    script.cleanWs()
                    configure()

                    Log.info Job.info
                    Log.info Pipeline.info
                    Log.info Configuration.info
                    Log.var("properties", Configuration.properties)
                    Log.var("parameters", Configuration.parameters)

                    closure.call()
                }

                for (int i = 0; i < stages.size(); ++i) {
                    stages[i].execute()
                }
            }
        }
    }

    private static void configure() {
        // Определение типа
        type = script.env.BRANCH_NAME ? Type.MULTIBRANCH : Type.CLASSIC

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
     * Обертка создания шага пайплайна
     * Регистрация шага в пайплайне
     * @see Stage
     */
    static void stage(String name, @ClosureParams(value = SimpleType, options = "Stage") Closure steps) {
        stages << new Stage(name, steps)
    }

    /**
     * Инициализация модуля сборки
     * @return
     */
    static Module getBuild() {
        return initModule("build", [
            // TODO: Генерировать список
            sapsan.module.build.Custom.name,
            sapsan.module.build.Docker.name,
            sapsan.module.build.Python.name,
        ])
    }

    static private Module initModule(String name, List available) {
        Log.var "Available $name type", available

        String className = Configuration.properties.find {
            available.contains(it.key)
        }?.key ?: "Custom"

        def classObject = Pipeline.class.classLoader.loadClass("sapsan.module.build.$className", true)
        Log.info("Initiated build class: $classObject.name")

        return classObject.getDeclaredConstructor().newInstance() as Module
    }

    static String getInfo() {
        """
        [Pipeline Information]
        Pipeline.type=$type
        Pipeline.task=$task
        """.stripIndent()
    }

}
