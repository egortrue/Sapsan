package sapsan.core

class Job extends Context {

    @Lazy static String branch = "${script.env.BRANCH_NAME}"
    @Lazy static String url = "${script.env.BUILD_URL}"

    /**
     *  Уникальный путь до джобы
     */
    static String getName() {
        if (script.env.BRANCH_NAME)
            return script.env.JOB_NAME.split('/')[0..-2].join('/')
        return script.env.JOB_NAME
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
