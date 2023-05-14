package sapsan.core

class Configuration extends Context {

    // Файлы конфигураций
    private static String root = "configurations"
    private static String parametersFile = "parameters.yaml"
    private static String propertiesFile = "properties.yaml"

    // Модули
    // TODO: Генерировать список
    static def packageBuild = [
        sapsan.module.build.Custom.simpleName,
        sapsan.module.build.Docker.simpleName,
        sapsan.module.build.Python.simpleName,
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
