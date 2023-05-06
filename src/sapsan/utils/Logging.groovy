package sapsan.utils

import com.cloudbees.groovy.cps.NonCPS
import sapsan.core.Context
import sapsan.jenkins.Pipeline

/**
 * Статический класс для вывода информации в консоль.
 */
final class Logging {

  @NonCPS
  static void log(def object) {
    Context.script.echo(object.toString())
  }

  @NonCPS
  static void warning(def object) {
    // TODO: Добавить метку Warning для Stage
    log(Colors.yellow("[Warning] ${object.toString()}"))
  }

  @NonCPS
  static void error(def object) {
    // TODO: Добавить метку Error для Stage и останавливать Pipeline
    log(Colors.red("[Error] ${object.toString()}"))
  }
}
