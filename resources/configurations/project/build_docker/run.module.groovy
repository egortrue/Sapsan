import sapsan.core.Pipeline
import sapsan.module.Module

class Run extends Module {
    String name = "Run Docker"
    String image

    @Override
    protected void precheck() {
    }

    @Override
    protected void execute() {
        // Получаем имя образа из модуля сборки
        image = Module.getModule("Build Docker").image

        Pipeline.sh "docker run $image"
    }
}

return Run.class