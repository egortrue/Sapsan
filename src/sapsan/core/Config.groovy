package sapsan.core

final class Config extends Context {

    static final String root = "configurations"
    static final String parametersFilename = "parameters.yaml"
    static final String propertiesFilename = "properties.yaml"

    private static Map globalParameters
    private static Map parameters
    private static Map properties

    static String getGlobalParametersFile() {
        "$root/global/$parametersFilename"
    }

    static String getParametersFile() {
        "$root/custom/${Job.name}/$parametersFilename"
    }

    static String getPropertiesFile() {
        "$root/custom/${Job.name}/$propertiesFilename"
    }

    static Map getGlobalParameters() {
        if (globalParameters == null)
            globalParameters = parse(globalParametersFile)
        return globalParameters
    }

    static Map getParameters() {
        if (parameters == null)
            parameters = parse(parametersFile)
        return parameters
    }

    static Map getProperties() {
        if (properties == null)
            properties = parse(propertiesFile)
        return properties
    }

    static String getInfo() {
        """
        [Config Information]
        globalParametersFile="$globalParametersFile"
        parametersFile="$parametersFile"
        propertiesFile="$propertiesFile"
        """.stripIndent()
    }

    private static Map parse(String path) {
        Context.pipeline.readYaml(text: Context.pipeline.libraryResource(path))
    }
}
