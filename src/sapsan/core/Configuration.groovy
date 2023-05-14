package sapsan.core

class Configuration extends Context {

    // Файлы конфигураций
    private static String root = "configurations"
    private static String parametersFile = "parameters.yaml"
    private static String propertiesFile = "properties.yaml"

    // Файлы модулей
    private static String packageBuild = "sapsan.module.build"

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

    static Module getBuild() {
        def classLoader = new GroovyClassLoader()
        def packageClasses = classLoader.getPackageClassLoader(packageBuild).getClasses()
        packageClasses.each { println it }
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
        script.readYaml(text: script.libraryResource(path))
    }
}
