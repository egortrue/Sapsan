package sapsan.utils

import com.cloudbees.groovy.cps.NonCPS
import sapsan.core.Context
import sapsan.jenkins.Pipeline

/**
 * Статический класс для вывода информации в консоль.
 */
final class Logging {

  @NonCPS
  static void log(String text) {
    Context.script.echo(text)
  }

  @NonCPS
  static void warning(String text) {
    // TODO: Добавить метку Warning для Stage
    log(Colors.yellow("[Warning] $text"))
  }

  @NonCPS
  static void error(String text) {
    // TODO: Добавить метку Error для Stage и останавливать Pipeline
    log(Colors.red("[Error] $text"))
  }
}
