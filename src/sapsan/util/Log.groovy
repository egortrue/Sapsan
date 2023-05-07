package sapsan.util

import sapsan.core.Context

/**
 * Статический класс для вывода информации в консоль.
 */
final class Log extends Context {

    static void info(String text) {
        pipelineScript.echo(Color.green(text))
    }

    static void warning(String text) {
        // TODO: Добавить метку Warning для Stage
        // TODO: Сохранять все предупреждения в отдельный файл/переменную
        pipelineScript.echo(Color.yellow("[Warning] $text"))
    }

    static void error(String text) {
        // TODO: Добавить метку Error для Stage и останавливать Pipeline
        pipelineScript.echo(Color.red("[Error] $text"))
    }
}
