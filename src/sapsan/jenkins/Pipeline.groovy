package sapsan.jenkins

import groovy.transform.stc.ClosureParams
import groovy.transform.stc.SimpleType
import sapsan.core.Context

class Pipeline extends Context {

  enum Type {
    CLASSIC,
    MULTIBRANCH
  }

  static List<Stage> stages = new ArrayList<Stage>()
  static Type type = Type.CLASSIC

  static void init(Map staticParameters) {
    Pipeline.staticParameters = staticParameters
  }

  /**
   * Запуск трубы
   * @param node метка агента Jenkins
   * @param closure выполняемые действия
   */
  static void run(String node = 'linux', Closure closure) {
    Context.script.node(node) {
      closure.call()
      stages.each { stage ->
        stage.call()
      }
    }
  }


  /**
   * Обертка создания шага пайплайна
   * @see Stage
   */
  static void stage(String name, @ClosureParams(value = SimpleType, options = "Stage") Closure steps) {
    stages << new Stage(name, steps)
  }

}
