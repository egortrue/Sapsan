package sapsan.core

import sapsan.util.Email
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

                // Конфигурация пайплайна
                pipeline.stage("Init") {
                    pipeline.cleanWs()
                    configure()

                    // Общая информация
                    Log.info Job.info
                    Log.info Pipeline.info
                    Log.info Config.info
                    Log.var("properties", Config.properties)
                    Log.var("parameters", Config.parameters)

                    // Инициализация шагов
                    closure()
                }

                try {
                    // Выполнение действий, указанных в каждом шаге
                    for (int i = 0; i < stages.size(); ++i) {
                        stages[i].execute()
                    }
                } finally {
                    // Создание отчета о выполнении
                    pipeline.stage("Report") {
                        Log.info("Sending email...")
                        Email.send()
                    }
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
                task = Task.BUILD
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
    static void stage(String name, Closure steps) {
        stages << new Stage(name, steps)
    }

    static String sh(String command) {
        String outputFilename = "${Job.tag}.output"
        String executable = "$command > $outputFilename 2>&1"
        Integer statusCode = Context.pipeline.sh(script: executable, returnStatus: true)
        String output = Context.pipeline.sh(script: "cat $outputFilename", returnStdout: true)
        Context.pipeline.sh(script: "rm -f $outputFilename")
        if (statusCode != 0) {
            Log.error("Shell returned status code ${statusCode}\nCommand: $command\nOutput: \"$output\"")
        } else {
            Log.info(output)
        }
        return output
    }

    static String getInfo() {
        """
        [Pipeline Information]
        type=$type
        task=$task
        """.stripIndent()
    }
}
