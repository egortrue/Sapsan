package sapsan.core

class Configuration extends Context {

    static String root = "configurations"

    static void loadProperties() {
        script.libraryResource "$root/${Job.project}/properties/${Pipeline.type.toString().toLowerCase()}.properties"
    }

    static void loadParameters() {
        script.libraryResource "$root/${Job.project}/parameters/${Pipeline.type.toString().toLowerCase()}.yml"
    }

}
