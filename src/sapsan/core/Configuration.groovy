package sapsan.core

class Configuration extends Context {

    static String root = "configurations"
    @Lazy static String properties = "$root/${Job.project}/properties/${Pipeline.type.toString().toLowerCase()}.properties"
    @Lazy static String parameters = "$root/${Job.project}/parameters/${Pipeline.type.toString().toLowerCase()}.yml"

}
