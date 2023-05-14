package sapsan.core

class Configuration extends Context {

    static String root = "configurations"
    @Lazy static String properties = "$root/${Job.name}/${Pipeline.task.toString().toLowerCase()}.properties"
    @Lazy static String parameters = "$root/${Job.name}/${Pipeline.task.toString().toLowerCase()}.yml"

    static String readProperties() {
        script.libraryResource properties
    }
    
    static String readParameters() {
        script.libraryResource parameters
    }

    static String getInfo() {
        """
        [Configuration Information]
        Configuration.properties=$properties
        Configuration.parameters=$parameters
        """.stripIndent()
    }

}
