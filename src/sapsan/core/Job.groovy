package sapsan.core

class Job extends Context {

    @Lazy static String name = script.env.JOB_NAME
    @Lazy static String branch = script.env.BRANCH_NAME
    @Lazy static String url = script.env.BUILD_URL


    static String getPath() {
        name.split('/')[0..-1].join('/')
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
        Job.path=$path
        Job.url=$url
        Job.baseUrl=$baseUrl
        """.stripIndent()
    }
}
