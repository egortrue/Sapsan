package sapsan.core

class Configuration extends Context {

    private static String root = "configurations"
    private static String parametersFile = "parameters.yaml"
    private static String properitesFile = "properties.yaml"

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
            properties = parse("$root/${Job.name}/$properitesFile")
        return properties
    }

    static String getInfo() {
        """
        [Configuration Information]
        Configuration.globalParametersPath="$root/$parametersFile"
        Configuration.parametersPath="$root/${Job.name}/$parametersFile"
        Configuration.propertiesPath="$root/${Job.name}/$properitesFile"
        """.stripIndent()
    }

    private static Map parse(String path) {
        script.readYaml(text: script.libraryResource(path))
    }
}
