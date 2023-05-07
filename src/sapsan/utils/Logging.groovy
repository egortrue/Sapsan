package sapsan.utils

import groovy.transform.CompileStatic

/**
 * Статический класс для вывода информации в консоль.
 */
abstract class Logging {

  static volatile Script script

  static void log(String text) {
    script.echo(Colors.green(text))
  }

  static void warning(String text) {
    // TODO: Добавить метку Warning для Stage
    // TODO: Сохранять все предупреждения в отдельный файл/переменную
    script.echo(Colors.yellow("[Warning] $text"))
  }

  static void error(String text) {
    // TODO: Добавить метку Error для Stage и останавливать Pipeline
    script.echo(Colors.red("[Error] $text"))
  }
}
