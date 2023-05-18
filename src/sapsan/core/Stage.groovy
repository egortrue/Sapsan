package sapsan.core

import sapsan.util.Log

final class Stage extends Context {

    enum Status {
        NOT_STARTED,
        SKIPPED,
        STARTED,
        SUCCESS,
        UNSTABLE,
        FAILED
    }

    String name
    Closure steps
    Status status = Status.NOT_STARTED

    Stage(String name, Closure steps) {
        this.name = name
        this.steps = steps
    }

    void execute() {
        status = Status.STARTED
        try {
            Context.pipeline.stage(name) {
                steps()
            }
            status = Status.SUCCESS
        } catch (Exception exception) {
            Log.error exception.message
            status = Status.FAILED
        }
    }

    void haveCondition(Closure<Boolean> condition) {
        if (condition.call() == false) {
            setStatus(Status.SKIPPED)
        }
    }
}