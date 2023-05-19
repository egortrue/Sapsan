package sapsan.core

final class Config extends Context {

    private static final String parametersFilename = "parameters.yaml"
    private static final String propertiesFilename = "properties.yaml"

    private static Map properties
    private static Map parameters

    ////////////////////////////////////////////////////////////////

    static String getGlobalFile(String filename) {
        "configurations/${filename}"
    }

    static String getProjectFile(String filename) {
        "configurations/project/${Job.name}/${filename}"
    }

    static Map getProperties() {
        if (properties == null) {
            properties = parse(getProjectFile(propertiesFilename))
        }
        return properties
    }


    static Map getParameters() {
        if (parameters == null) {
            parameters = parse(getProjectFile(parametersFilename))
        }

        return parameters
    }

    ////////////////////////////////////////////////////////////////

    static String getInfo() {
        """
        [Config Information]
        propertiesFile="${getProjectFile(propertiesFilename)}"
        parametersFile="${getGlobalFile(parametersFilename)}"
        """.stripIndent()
    }

    private static Map parse(String path) {
        Context.pipeline.readYaml(text: Context.pipeline.libraryResource(path))
    }
}
