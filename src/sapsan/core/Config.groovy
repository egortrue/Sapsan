package sapsan.core

import sapsan.util.Log

final class Config extends Context {

    private static final String propertiesFilename = "properties.yml"
    private static final String parametersFilename = "parameters.yml"
    private static final String descriptionParametersFilename = "description.parameters.yml"

    private static Map properties
    private static Map parameters

    ////////////////////////////////////////////////////////////////

    static String getGlobalFile(String filename) {
        "configurations/${filename}"
    }

    static String getProjectFile(String filename) {
        "configurations/project/${Job.name}/${filename}"
    }

    static Map getProperties() {
        if (properties == null) {
            properties = parse(getProjectFile(propertiesFilename))
        }
        return properties
    }


    static Map getParameters() {
        if (parameters == null) {
            updateParameters(parse(getProjectFile(parametersFilename)))
            if (Job.buildNumber == 1) {
                Log.warning("Some parameters may be missed")
            }
            parameters = Context.pipeline.params
        }

        return parameters
    }

    ////////////////////////////////////////////////////////////////

    static String getInfo() {
        """
        [Config Information]
        propertiesFile="${getProjectFile(propertiesFilename)}"
        parametersFile="${getGlobalFile(parametersFilename)}"
        """.stripIndent()
    }

    private static Map parse(String path) {
        Context.pipeline.readYaml(text: Context.pipeline.libraryResource(path))
    }

    private static void updateParameters(Map parametersDescription) {
        def parametersList = []

        // Загрузка пользовательских параметров
        parametersDescription["custom"].each { Map it ->
            parametersList.add(createParameter(it.key, it.value))
        }

        // Загрузка стандартных параметров
        Map descriptionDefault = parse(getGlobalFile(descriptionParametersFilename))
        parametersDescription["default"].each { String key ->
            parametersList.add(createParameter(key, descriptionDefault[key]))
        }

        Log.var("parametersList", parametersList)
        Context.pipeline.properties([Context.pipeline.parameters(parametersList)])
    }

    static def createParameter(String key, Map value) {
        switch (value["type"]) {
            case 'string':
                return Context.pipeline.string(
                    name: key,
                    description: value['description'],
                    trim: value['trim'],
                    defaultValue: value['defaultValue']
                )
            case 'boolean':
                return Context.pipeline.booleanParam(
                    name: key,
                    description: value['description'],
                    defaultValue: value['defaultValue']
                )
            case 'text':
                return Context.pipeline.text(
                    name: key,
                    description: value['description'],
                    defaultValue: value['defaultValue']
                )
            case 'choice':
                return Context.pipeline.choice(
                    name: key,
                    description: value['description'],
                    choices: value['choices']
                )
            case 'password':
                return Context.pipeline.password(
                    name: key,
                    description: value['description'],
                )
        }
    }
}