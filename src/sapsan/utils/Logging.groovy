package sapsan.utils

import com.cloudbees.groovy.cps.NonCPS
import sapsan.core.Context
import sapsan.jenkins.Pipeline

/**
 * Статический класс для вывода информации в консоль.
 */
final class Logging {

  static void log(String text) {
    Context.script.echo(Colors.green(text))
  }

  static void warning(String text) {
    // TODO: Добавить метку Warning для Stage
    Context.script.echo(Colors.yellow("[Warning] $text"))
  }

  static void error(String text) {
    // TODO: Добавить метку Error для Stage и останавливать Pipeline
    Context.script.echo(Colors.red("[Error] $text"))
  }
}
