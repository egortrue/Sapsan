package sapsan.core

abstract class Configuration extends Context {

    // Файлы конфигураций
    public static String root = "configurations"
    public static String parametersFile = "parameters.yaml"
    public static String propertiesFile = "properties.yaml"

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
        Context.pipeline.readYaml(text: Context.pipeline.libraryResource(path))
    }

    private static void setParameters() {}

    private static void setProperties() {}
}
