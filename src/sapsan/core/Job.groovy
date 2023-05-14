package sapsan.core

class Job extends Context {

    @Lazy static String name = script.env.JOB_NAME
    @Lazy static String project = script.env.JOB_NAME
    @Lazy static String branch = script.env.BRANCH_NAME
    @Lazy static String path = name.split('/')[0..-1].join('/')
    @Lazy static String url = script.env.BUILD_URL
    @Lazy static String baseUrl = url.split('/')[0..2].join('/')

    static String getInfo() {
        """
        [Job Information]
        Job.name=$name
        Job.branch=$branch
        Job.path=$path
        Job.url=$url
        Job.baseUrl=$baseUrl
        """.stripIndent()
    }
}
