package sapsan.core

class Configuration extends Context {

    static String root = "configurations"
    static String parametersFile = "parameters.yaml"
    static String properitesFile = "properties.yaml"

    @Lazy static String project = "$root/${Job.name}"
    @Lazy static Map parameters = parse(parametersPath)
    @Lazy static Map properties = parse(propertiesPath)

    static String getGlobalParametersPath() {
        "$root/$parametersFile"
    }

    static String getParametersPath() {
        "$root/${Job.name}/$parametersFile"
    }

    static String getPropertiesPath() {
        "$root/${Job.name}/$properitesFile"
    }

    static Map parse(String path) {
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
