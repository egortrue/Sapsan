package sapsan.core

import com.cloudbees.groovy.cps.NonCPS

class Job extends Context {

    /**
     *  Уникальный путь до джобы
     */
    @NonCPS
    static String getName() {
        if (Context.pipeline.env.BRANCH_NAME)
            return Context.pipeline.env.JOB_NAME.split('/')[0..-2].join('/')
        return Context.pipeline.env.JOB_NAME
    }

    @NonCPS
    static String getBranch() {
        Context.pipeline.env.BRANCH_NAME
    }

    @NonCPS
    static String getUrl() {
        Context.pipeline.env.BUILD_URL
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
