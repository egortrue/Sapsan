package sapsan.util

import groovy.text.GStringTemplateEngine
import groovy.text.Template
import sapsan.core.Config
import sapsan.core.Context
import sapsan.core.Job
import sapsan.core.Stage

class Email extends Context {

    private static String create() {
        Template template = new GStringTemplateEngine().createTemplate(Context.pipeline.libraryResource("templates/email.html") as String)
        return template.make([
            jobResult  : Context.pipeline.currentBuild.currentResult,
            information: [
                "Pipeline Name": Job.name,
                "Pipeline Type": Context.pipeline.getClass().getName()
            ],
            properties : [
                "Job Name"    : Context.pipeline.currentBuild.displayName,
                "Status"      : Context.pipeline.currentBuild.currentResult,
                "Execute Time": Context.pipeline.currentBuild.durationString.replace(' and counting', ''),
                "Link"        : Job.url
            ],
            parameters : Context.pipeline.params
        ])
    }

    static void send() {
        Context.pipeline.emailext(
            subject: "${Job.name} :: ${Stage.globalStatus.toString()}",
            body: "${create()}",
            to: Config.parameters["EMAIL_RECIPIENTS"],
            attachLog: Stage.globalStatus != Stage.Status.SUCCESS,
            mimeType: 'text/html',
        )
    }


}
