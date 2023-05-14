package sapsan.core

import com.cloudbees.groovy.cps.NonCPS

class Job extends Context {

    @Lazy(soft = true) static name = script.env.JOB_NAME
    @Lazy(soft = true) static project = script.env.JOB_NAME
    @Lazy(soft = true) static branch = script.env.BRANCH_NAME
    @Lazy(soft = true) static url = script.env.BUILD_URL

    static String getPath() {
        name.split('/')[0..-1].join('/')
    }

    static String getBaseUrl() {
        url.split('/')[0..2].join('/')
    }

    @NonCPS
    static String getInfo() {
        """
        [Job Information]
        Job.name=$name
        Job.name=$project
        Job.branch=$branch
        Job.path=$path
        Job.url=$url
        Job.baseUrl=$baseUrl
        """.stripIndent()
    }
}
