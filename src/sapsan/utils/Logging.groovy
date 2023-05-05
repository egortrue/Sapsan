package sapsan.utils

import com.cloudbees.groovy.cps.NonCPS
import sapsan.core.Context
import sapsan.jenkins.Pipeline

/**
 * Статический класс для вывода информации в консоль.
 */
final class Logging {

  final class Warning {
    @NonCPS
    static void call(String text) {
      // TODO: Добавить метку Warning для Stage
      Logging.call(Colors.red("[Warning] $text"))
    }
  }


  final class Error {
    @NonCPS
    static void call(String text) {
      // TODO: Добавить метку Error для Stage и останавливать Pipeline
      Logging.call(Colors.red("[Error] $text"))
    }
  }

  @NonCPS
  static void call(String text) {
    Context.script.echo(text)
  }
}
