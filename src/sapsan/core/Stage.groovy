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
        Context.pipeline.stage(name) {
            if (globalStatus == Status.FAILED) {
                status = Status.SKIPPED
                Utils.markStageSkippedForConditional(name)
                return
            }

            Context.pipeline.catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                status = Status.STARTED
                steps()
                status = Status.SUCCESS
            }

            if (currentBuild.result == 'FAILURE') {
                status = Status.FAILED
            }

            globalStatus = status
        }
    }

    void haveCondition(Closure<Boolean> condition) {
        if (condition.call() == false) {
            setStatus(Status.SKIPPED)
        }
    }
}