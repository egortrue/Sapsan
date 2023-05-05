package sapsan.utils


/**
 * Статический класс для определения сети.
 */
final class Net {

  enum Type {
    UNKNOWN,
    DELTA,
    SIGMA,
    ALPHA
  }

  /**
   *
   * @return
   */
  static Type getType() {
    return Type.UNKNOWN
  }

}