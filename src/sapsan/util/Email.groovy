package sapsan.util

import groovy.text.GStringTemplateEngine
import groovy.text.Template
import sapsan.core.Config
import sapsan.core.Context
import sapsan.core.Job
import sapsan.core.Stage

/**
 * Статический класс для поддержки цветного вывода.
 * Требует расширения https://plugins.jenkins.io/email-ext/
 */
class Email extends Context {

    private static String create() {
        Template template = new GStringTemplateEngine().createTemplate(pipeline.libraryResource("templates/email.html") as String)
        return template.make([
            jobResult  : pipeline.currentBuild.currentResult,
            information: [
                "Pipeline Name": Job.name,
                "Pipeline Type": pipeline.getClass().getName()
            ],
            properties : [
                "Job Name"    : pipeline.currentBuild.displayName,
                "Status"      : pipeline.currentBuild.currentResult,
                "Execute Time": pipeline.currentBuild.durationString.replace(' and counting', ''),
                "Link"        : Job.url
            ],
            parameters : pipeline.params
        ])
    }

    static void send() {
        pipeline.emailext(
            subject: "${Job.name} :: ${Stage.globalStatus.toString()}",
            body: "${create()}",
            to: Config.parameters["EMAIL_RECIPIENTS"],
            attachLog: Stage.globalStatus != Stage.Status.SUCCESS,
            mimeType: 'text/html',
        )
    }


}
