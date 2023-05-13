package sapsan.core

/**
 * Базовый класс, содержащий поля, для внутреннего использования.
 * Доступен везде
 */
abstract class Context {

    /**
     * Корневой восходящий скрипт (Jenkinsfile).
     * Позволяет использовать команды Jenkins, прописанные в /src/pipeline.gdsl
     */
    protected static Script script
}
