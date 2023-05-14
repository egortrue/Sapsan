package sapsan.core

class Configuration extends Context {

    static String root = "configurations"
    @Lazy static String properties = "$root/${Job.name}/properties/${Pipeline.task.toString().toLowerCase()}.properties"
    @Lazy static String parameters = "$root/${Job.name}/parameters/${Pipeline.task.toString().toLowerCase()}.yml"

    static String getInfo() {
        """
        [Configuration Information]
        Configuration.properties=$properties
        Configuration.parameters=$parameters
        """.stripIndent()
    }

}
