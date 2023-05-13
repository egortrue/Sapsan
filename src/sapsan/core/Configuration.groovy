package sapsan.core

class Configuration extends Context {

    static String root = "configurations"
    @Lazy static String properties = "$root/${Job.project}/properties/${Pipeline.task.toString().toLowerCase()}.properties"
    @Lazy static String parameters = "$root/${Job.project}/parameters/${Pipeline.task.toString().toLowerCase()}.yml"

}
