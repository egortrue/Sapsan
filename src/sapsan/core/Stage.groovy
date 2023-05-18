package sapsan.core

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

            if (Context.pipeline.currentBuild.result == 'FAILURE') {
                status = Status.FAILED
            }

            globalStatus = status
        }
    }
}