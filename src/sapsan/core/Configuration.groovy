package sapsan.core

class Configuration extends Context {

    static String root = "configurations"

    static String getPropertiesFile() {
        "$root/${Job.name}/properties.yaml"
    }

    static String getParametersFile() {
        "$root/${Job.name}/parameters.yaml"
    }

    static String readProperties() {
        script.libraryResource propertiesFile
    }

    static String readParameters() {
        script.libraryResource parametersFile
    }

    static String getInfo() {
        """
        [Configuration Information]
        Configuration.propertiesFile=$propertiesFile
        Configuration.parametersFile=$parametersFile
        """.stripIndent()
    }

}
