package sapsan.core

import groovy.transform.Memoized
import sapsan.util.Log

final class Config extends Context {

    static final String root = "configurations"
    static final String parametersFilename = "parameters.yaml"
    static final String propertiesFilename = "properties.yaml"

    @Memoized
    static Map getGlobalParameters() {
        return parse("$root/global/$parametersFilename")
    }

    @Memoized
    static Map getCustomParameters() {

    }

    @Memoized
    static Map getParameters() {
        return parse("$root/${Job.name}/$parametersFilename")
    }

    @Memoized
    static Map getProperties() {
        return parse("$root/${Job.name}/$propertiesFilename")
    }

    static String getInfo() {
        """
        [Configuration Information]
        Configuration.globalParametersPath="$root/$parametersFilename"
        Configuration.parametersPath="$root/${Job.name}/$parametersFilename"
        Configuration.propertiesPath="$root/${Job.name}/$propertiesFilename"
        """.stripIndent()
    }

    private static Map parse(String path) {
        Log.info("First time reading config file: $path")
        Context.pipeline.readYaml(text: Context.pipeline.libraryResource(path))
    }
}
