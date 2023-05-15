package sapsan.core

import com.cloudbees.groovy.cps.NonCPS

class Job extends Context {

    /**
     *  Уникальный путь до джобы
     */
    @NonCPS
    static String getName() {
        if (script.env.BRANCH_NAME)
            return script.env.JOB_NAME.split('/')[0..-2].join('/')
        return script.env.JOB_NAME
    }

    @NonCPS
    static String getBranch() {
        script.env.BRANCH_NAME
    }

    @NonCPS
    static String getUrl() {
        script.env.BUILD_URL
    }

    @NonCPS
    static String getBaseUrl() {
        url.split('/')[0..2].join('/')
    }

    @NonCPS
    static String getInfo() {
        """
        [Job Information]
        Job.name=$name
        Job.branch=$branch
        Job.url=$url
        Job.baseUrl=$baseUrl
        """.stripIndent()
    }
}
