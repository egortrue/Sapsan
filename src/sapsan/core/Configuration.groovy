package sapsan.core

class Configuration extends Context {

    private static String root = "configurations"
    private static String parametersFile = "parameters.yaml"
    private static String properitesFile = "properties.yaml"

    static Map getGlobalParameters() {
        parse("$root/$parametersFile")
    }

    static Map getParameters() {
        parse("$root/${Job.name}/$parametersFile")
    }

    static Map getProperties() {
        parse("$root/${Job.name}/$properitesFile")
    }

    private static Map parse(String path) {
        script.readYaml(text: script.libraryResource(path))
    }

    static String getInfo() {
        """
        [Configuration Information]
        Configuration.globalParametersPath="$root/$parametersFile"
        Configuration.parametersPath="$root/${Job.name}/$parametersFile"
        Configuration.propertiesPath="$root/${Job.name}/$properitesFile"
        """.stripIndent()
    }

}
