package sapsan.core

import org.jenkinsci.plugins.pipeline.modeldefinition.Utils

final class Stage extends Context {

    enum Status {
        NOT_STARTED,
        SKIPPED,
        STARTED,
        SUCCESS,
        UNSTABLE,
        FAILED
    }

    static Status globalStatus = Status.NOT_STARTED

    String name
    Closure steps
    Status status = Status.NOT_STARTED

    Stage(String name, Closure steps) {
        this.name = name
        this.steps = steps
    }

    void execute() {
        pipeline.stage(name) {
            if (globalStatus == Status.FAILED) {
                status = Status.SKIPPED
                Utils.markStageSkippedForConditional(name)
                return
            }

            pipeline.catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                status = Status.STARTED
                steps()
                status = Status.SUCCESS
            }

            if (pipeline.currentBuild.result == 'FAILURE') {
                status = Status.FAILED
            }

            globalStatus = status
        }
    }
}