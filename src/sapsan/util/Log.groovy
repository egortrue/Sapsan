package sapsan.util

import com.cloudbees.groovy.cps.NonCPS
import sapsan.core.Context

import static groovy.json.JsonOutput.prettyPrint
import static groovy.json.JsonOutput.toJson

/**
 * Статический класс для вывода информации в консоль.
 */
final class Log extends Context {

    @NonCPS
    static void var(String prefix = null, def variable) {
        def message = prefix ?: variable.getClass().name

        switch (variable.getClass()) {
            case [ArrayList, LinkedHashMap, HashMap, Map]:
                message += " " + prettyPrint(toJson(variable)); break
            default:
                message += " = $variable"
        }

        Context.pipeline.echo(Color.cyan(message))
    }

    @NonCPS
    static void info(String text) {
        Context.pipeline.echo(Color.green(text))
    }

    @NonCPS
    static void warning(String text) {
        // TODO: Добавить метку Warning для Stage
        // TODO: Сохранять все предупреждения в отдельный файл/переменную
        Context.pipeline.echo(Color.yellow("[Warning] $text"))
    }

    @NonCPS
    static void error(String text) {
        // TODO: Добавить метку Error для Stage и останавливать Pipeline
        Context.pipeline.echo(Color.red("[Error] $text"))
        Context.pipeline.currentBuild.result = 'FAILURE'
    }
}
