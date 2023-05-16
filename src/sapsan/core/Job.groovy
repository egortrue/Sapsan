package sapsan.core

import com.cloudbees.groovy.cps.NonCPS
import groovy.transform.Memoized

class Job extends Context {

    /**
     *  Полный путь до джобы
     */
    @NonCPS
    @Memoized
    static String getName() {
        if (Context.pipeline.env.BRANCH_NAME)
            return Context.pipeline.env.JOB_NAME.split('/')[0..-2].join('/')
        return Context.pipeline.env.JOB_NAME
    }

    @NonCPS
    @Memoized
    static String getBranch() {
        Context.pipeline.env.BRANCH_NAME
    }

    @NonCPS
    @Memoized
    static String getUrl() {
        Context.pipeline.env.BUILD_URL
    }

    @NonCPS
    @Memoized
    static String getBaseUrl() {
        url.split('/')[0..2].join('/')
    }

    @NonCPS
    @Memoized
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
