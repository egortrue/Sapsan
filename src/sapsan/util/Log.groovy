package sapsan.util

import com.cloudbees.groovy.cps.NonCPS
import sapsan.core.Context

import static groovy.json.JsonOutput.prettyPrint
import static groovy.json.JsonOutput.toJson

/**
 * Статический класс для вывода информации в консоль.
 */
final class Log extends Context {

    final class LogException extends Exception {
        LogException(String message) {
            super(message);
        }
    }

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
        // TODO: Установить статус UNSTABLE для класса Stage
        // TODO: Помечать вызывающий шаг желтым (UNSTABLE)
        // TODO: Сохранять все предупреждения в отдельный файл/переменную
        Context.pipeline.echo(Color.yellow("[Warning] $text"))
    }

    @NonCPS
    static void error(String text) {
        Context.pipeline.echo(Color.red("[Error] $text"))
        Context.pipeline.error(text)
    }
}
