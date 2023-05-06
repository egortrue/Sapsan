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
    static void print(def object) {
      // TODO: Добавить метку Warning для Stage
      Logging.print(Colors.yellow("[Warning] ${object.toString()}"))
    }
  }

  final class Error {
    @NonCPS
    static void print(def object) {
      // TODO: Добавить метку Error для Stage и останавливать Pipeline
      Logging.print(Colors.red("[Error] ${object.toString()}"))
    }
  }

  @NonCPS
  static void print(def object) {
    Context.script.echo(object.toString())
  }
}
