package sapsan.core

class Job extends Context {

    static String getName() {
        script.env.JOB_NAME
    }

    static String getProject() {
        if (script.env.BRANCH_NAME) {
            if (name.split('/').length <= 2)
                return null
            return name.split('/')[-3]
        } else {
            if (name.split('/').length <= 1)
                return null
            return name.split('/')[-2]
        }
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
        Job.project=$project
        Job.branch=$branch
        Job.url=$url
        Job.baseUrl=$baseUrl
        """.stripIndent()
    }
}
