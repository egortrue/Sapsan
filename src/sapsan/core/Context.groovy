package sapsan.core

/**
 * Базовый класс, содержащий поля, для внутреннего использования.
 * Доступен везде
 */
trait Context {

  /**
   * Корневой восходящий скрипт, содержащийся в /vars. Содержит шаги трубы.
   * Позволяет использовать команды Jenkins, прописанные в /src/pipeline.gdsl
   */
  public Script script

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

