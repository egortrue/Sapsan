package sapsan.core

import sapsan.utils.Logging

import java.util.concurrent.Callable
import java.util.function.Function

/**
 * Базовый класс, содержащий поля, для внутреннего использования.
 * Доступен везде
 */
abstract class Context implements Serializable {

  /**
   * Корневой восходящий скрипт, содержащийся в /vars. Содержит шаги трубы.
   * Позволяет использовать команды Jenkins, прописанные в /src/pipeline.gdsl
   */
  static volatile Script script
  
  /**
   * Интерфейс для вывода сообщений в консоль.
   * @see Logging#call
   */
  public static final def log = Logging.&call

  /**
   * Интерфейс для вывода предупреждений в консоль.
   * @see Logging.Warning#call
   */
  public static final def warning = Logging.Warning.&call

  /**
   * Интерфейс для вывода ошибок в консоль.
   * @see Logging.Error#call
   */
  public static final def error = Logging.Error.&call

//  /**
//   * Инициализация всех свойств объекта.
//   * Этот метод должны задействовать все дочерние классы в конструкторе.
//   * Аналог @MapConstructor + @NullCheck (доступны только в groovy 3.0+)
//   * @param args все публичные свойства объекта
//   */
//  protected void init(Map args) {
//    if (args == null) throw new IllegalArgumentException("args cannot be null")
//    this.properties.findAll({ !['class'].contains(it.key) }).each { property, value ->
//      if (!args.containsKey(property) || args[property] == null)
//        throw new IllegalArgumentException("${property} cannot be null")
//      this.@"$property" = args[property]
//    }
//  }
}

