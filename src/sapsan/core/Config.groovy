package sapsan.core

final class Config extends Context {

    private static final String parametersFilename = "parameters.yaml"
    private static final String propertiesFilename = "properties.yaml"

    private static Map groupParameters
    private static Map groupProperties
    private static Map projectParameters
    private static Map projectProperties

    static String getGroupFile(String filename) {
        "configurations/group/${filename}"
    }

    static String getProjectFile(String filename) {
        "configurations/project/${Job.name}/${filename}"
    }

    static Map getGroupParameters() {
        if (groupParameters == null)
            groupParameters = parse(getGroupFile(parametersFilename))
        return groupParameters
    }

    static Map getGroupProperties() {
        if (groupProperties == null)
            groupProperties = parse(getGroupFile(propertiesFilename))
        return groupProperties
    }

    static Map getProjectParameters() {
        if (projectParameters == null)
            projectParameters = parse(getProjectFile(parametersFilename))
        return projectParameters
    }

    static Map getProjectProperties() {
        if (projectProperties == null)
            projectProperties = parse(getProjectFile(propertiesFilename))
        return projectProperties
    }

    static String getInfo() {
        """
        [Config Information]
        globalParameters="${getGroupFile(parametersFilename)}"
        globalProperties="${getGroupFile(propertiesFilename)}"
        projectParameters="${getProjectFile(parametersFilename)}"
        projectProperties="${getProjectFile(propertiesFilename)}"
        """.stripIndent()
    }

    private static Map parse(String path) {
        Context.pipeline.readYaml(text: Context.pipeline.libraryResource(path))
    }
}
