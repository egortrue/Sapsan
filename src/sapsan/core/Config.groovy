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
    static Map getParameters() {
        return parse("$root/custom/${Job.name}/$parametersFilename")
    }

    @Memoized
    static Map getProperties() {
        return parse("$root/custom/${Job.name}/$propertiesFilename")
    }

    static String getInfo() {
        """
        [Config Information]
        globalParametersPath="$root/$parametersFilename"
        parametersPath="$root/${Job.name}/$parametersFilename"
        propertiesPath="$root/${Job.name}/$propertiesFilename"
        """.stripIndent()
    }

    private static Map parse(String path) {
        Log.info("First time reading config file: $path")
        Context.pipeline.readYaml(text: Context.pipeline.libraryResource(path))
    }
}
