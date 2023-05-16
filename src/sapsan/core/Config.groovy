package sapsan.core

final class Config extends Context {

    static final String root = "configurations"
    static final String parametersFilename = "parameters.yaml"
    static final String propertiesFilename = "properties.yaml"

    private static Map globalParameters
    private static Map parameters
    private static Map properties

    static Map getGlobalParameters() {
        if (globalParameters == null)
            globalParameters = parse("$root/global/$parametersFilename")
        return globalParameters
    }

    static Map getParameters() {
        if (parameters == null)
            parameters = parse("$root/custom/${Job.name}/$parametersFilename")
        return parameters
    }

    static Map getProperties() {
        if (properties == null)
            properties = parse("$root/custom/${Job.name}/$propertiesFilename")
        return properties
    }

    static String getInfo() {
        """
        [Config Information]
        globalParametersPath="$root/global/$parametersFilename"
        parametersPath="$root/custom/${Job.name}/$parametersFilename"
        propertiesPath="$root/custom/${Job.name}/$propertiesFilename"
        """.stripIndent()
    }

    private static Map parse(String path) {
        Context.pipeline.readYaml(text: Context.pipeline.libraryResource(path))
    }
}
