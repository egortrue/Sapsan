package sapsan.utils

import com.cloudbees.groovy.cps.NonCPS
import sapsan.core.Context
import sapsan.jenkins.Pipeline

/**
 * Статический класс для вывода информации в консоль.
 */
final class Logging {

  final class Log {
    @NonCPS
    static void call(Object object) {
      Logging.call("${object.toString()}")
    }
  }

  final class Warning {
    @NonCPS
    static void call(Object object) {
      // TODO: Добавить метку Warning для Stage
      Logging.call("[Warning] ${object.toString()}")
    }
  }


  final class Error {
    @NonCPS
    static void call(Object object) {
      // TODO: Добавить метку Error для Stage и останавливать Pipeline
      // Logging.call("[Error] ${object.toString()}")
      Pipeline.script.error "${object.toString()}"
    }
  }

  @NonCPS
  private static void call(String text) {
    // TODO: Добавить цвета (плагин ansiColor)
    Context.script.echo(text)
  }
}
