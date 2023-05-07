package sapsan.utils

/**
 * Статический класс для вывода информации в консоль.
 */
@Singleton
abstract class Logging {

  static volatile Script script

  static def log(String text) {
    script.echo(Colors.green(text))
  }

  static def warning(String text) {
    // TODO: Добавить метку Warning для Stage
    // TODO: Сохранять все предупреждения в отдельный файл/переменную
    script.echo(Colors.yellow("[Warning] $text"))
  }

  static def error(String text) {
    // TODO: Добавить метку Error для Stage и останавливать Pipeline
    script.echo(Colors.red("[Error] $text"))
  }
}
