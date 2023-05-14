package sapsan.module.checkout

import sapsan.core.Configuration
import sapsan.core.Job
import sapsan.core.Pipeline
import sapsan.module.Module
import sapsan.util.Log

class Custom extends Module {

    static Script checkoutScript
    static String checkoutScriptFile = "checkout.groovy"
    static String checkoutScriptPath = ".ci/$checkoutScriptFile"

    @Override
    void initProperties() {
        checkoutScript.initProperties(properties)
    }

    @Override
    void checkProperties() {
        checkoutScript.checkProperties(properties)
    }

    /**
     * Запуск кастомного скрипта для сборки проекта
     * @param name название шага пайплайна
     */
    @Override
    def execute() {
        String checkoutScriptText = ""

        try {
            checkoutScriptText = script.libraryResource("$Configuration.root/$Job.name/$checkoutScriptFile")
            script.prependToFile(file: checkoutScriptPath, content: checkoutScriptText)
            checkoutScript = script.load checkoutScriptPath
        } catch (Exception e) {
            Log.error("Checkout overriding is thrown exception: $e.message")
        }

        if (checkoutScriptText.size() == 0 || checkoutScript == null)
            Log.error("No custom Checkout found!")

        Log.var checkoutScriptText.size()

        checkProperties(Configuration.properties["build"])
        Pipeline.stage(checkoutScript.name) {
            initProperties(Configuration.properties["build"])
            checkoutScript.execute(properties)
        }
    }
}
