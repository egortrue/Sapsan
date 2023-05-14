package sapsan.core

class Configuration extends Context {

    @Lazy static def parameters = { return parse(parametersPath) }
    @Lazy static def properties = { return parse(propertiesPath) }

    private static String root = "configurations"
    private static String parametersFile = "parameters.yaml"
    private static String properitesFile = "properties.yaml"

    private static String getGlobalParametersPath() {
        "$root/$parametersFile"
    }

    private static String getParametersPath() {
        "$root/${Job.name}/$parametersFile"
    }

    private static String getPropertiesPath() {
        "$root/${Job.name}/$properitesFile"
    }

    private static Map parse(String path) {
        script.readYaml(text: script.libraryResource(path))
    }

    static String getInfo() {
        """
        [Configuration Information]
        Configuration.globalParametersPath=$globalParametersPath
        Configuration.parametersPath=$parametersPath
        Configuration.propertiesPath=$propertiesPath
        """.stripIndent()
    }

}
