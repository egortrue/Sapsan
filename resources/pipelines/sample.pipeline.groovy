@Library("sapsan@master") _
import sapsan.core.Config
import sapsan.core.Pipeline
import sapsan.module.Checkout
import sapsan.module.Module
import sapsan.util.Log

Pipeline.run(this, "agent") {

    // Использование статического модуля
    Module.execute(Checkout)

    // Использование динамического глобального модуля
    Module.execute(Config.getGlobalFile("sample.module.groovy"))

    // Использование динамического пользовательского модуля
    Module.execute(Config.getProjectFile("build.module.groovy"))

    // Реализация собственного шага
    Pipeline.stage("Simple stage") {
        Log.info("This is a simple step inside simple stage")
    }
}