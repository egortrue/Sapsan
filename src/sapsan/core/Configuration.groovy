package sapsan.core

class Configuration extends Context {

    static String root = "configurations"
    @Lazy static String propertiesFile = "$root/${Job.name}/${Pipeline.task.toString().toLowerCase()}.properties"
    @Lazy static String parametersFile = "$root/${Job.name}/${Pipeline.task.toString().toLowerCase()}.yml"

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
