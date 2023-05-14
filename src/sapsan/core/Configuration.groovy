package sapsan.core

class Configuration extends Context {

    // Файлы конфигураций
    private static String root = "configurations"
    private static String parametersFile = "parameters.yaml"
    private static String propertiesFile = "properties.yaml"

    // Модули
    // TODO: Генерировать список
    private static def packageBuild = [
        sapsan.module.build.Custom.name,
        sapsan.module.build.Docker.name,
        sapsan.module.build.Python.name,
    ]

    private static Map globalParameters
    private static Map parameters
    private static Map properties

    static Map getGlobalParameters() {
        if (globalParameters == null)
            globalParameters = parse("$root/$parametersFile")
        return globalParameters
    }

    static Map getParameters() {
        if (parameters == null)
            parameters = parse("$root/${Job.name}/$parametersFile")
        return parameters
    }

    static Map getProperties() {
        if (properties == null)
            properties = parse("$root/${Job.name}/$propertiesFile")
        return properties
    }

    static Module getBuild() {
        String className = properties.find { packageBuild.contains(it.key) }?.key ?: "Custom"
        return this.class.classLoader.loadClass("sapsan.module.build.$className").getDeclaredConstructor().newInstance() as Module
    }

    static String getInfo() {
        """
        [Configuration Information]
        Configuration.globalParametersPath="$root/$parametersFile"
        Configuration.parametersPath="$root/${Job.name}/$parametersFile"
        Configuration.propertiesPath="$root/${Job.name}/$propertiesFile"
        """.stripIndent()
    }

    private static Map parse(String path) {
        script.readYaml(text: script.libraryResource(path))
    }
}
