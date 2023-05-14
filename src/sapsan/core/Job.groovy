package sapsan.core

class Job extends Context {

    static String getName() {
        if (script.env.BRANCH_NAME)
            return script.env.JOB_NAME.split('/')[0..-2]
        return script.env.JOB_NAME
    }

    static String getBranch() {
        script.env.BRANCH_NAME
    }

    static String getUrl() {
        script.env.BUILD_URL
    }

    static String getBaseUrl() {
        url.split('/')[0..2].join('/')
    }

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
