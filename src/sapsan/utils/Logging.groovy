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
    static void call(Object object) {
      // TODO: Добавить метку Warning для Stage
      Logging.call(Colors.yellow("[Warning] ${object.toString()}"))
    }
  }

  final class Error {
    @NonCPS
    static void call(Object object) {
      // TODO: Добавить метку Error для Stage и останавливать Pipeline
      Logging.call(Colors.red("[Error] ${object.toString()}"))
    }
  }

  @NonCPS
  static void call(Object object) {
    Context.script.echo(object.toString())
  }
}
