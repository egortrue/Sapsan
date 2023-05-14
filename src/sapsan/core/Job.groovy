package sapsan.core

class Job extends Context {

    @Lazy static public String name = script.env.JOB_NAME
    @Lazy static public String project = script.env.JOB_NAME
    @Lazy static public String branch = script.env.BRANCH_NAME
    @Lazy static public String path = name.split('/')[0..-1].join('/')
    @Lazy static public String url = script.env.BUILD_URL
    @Lazy static public String baseUrl = url.split('/')[0..2].join('/')

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
