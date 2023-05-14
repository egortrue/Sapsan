package sapsan.util

import com.cloudbees.groovy.cps.NonCPS
import sapsan.core.Context

import static groovy.json.JsonOutput.*

/**
 * Статический класс для вывода информации в консоль.
 */
final class Log extends Context {

    @NonCPS
    static void var(String name, Map object) {
        script.echo(Color.green(name + " \n" + prettyPrint(toJson(object))))
    }

    @NonCPS
    static void info(String text) {
        script.echo(Color.green(text))
    }

    @NonCPS
    static void warning(String text) {
        // TODO: Добавить метку Warning для Stage
        // TODO: Сохранять все предупреждения в отдельный файл/переменную
        script.echo(Color.yellow("[Warning] $text"))
    }

    @NonCPS
    static void error(String text) {
        // TODO: Добавить метку Error для Stage и останавливать Pipeline
        script.echo(Color.red("[Error] $text"))
        script.error(text)
    }
}
