package sapsan.utils

import com.cloudbees.groovy.cps.NonCPS

class Colors {

    static String reset = "\\e[0m"

    @NonCPS
    static String red(String message) {
        return "\\e[31m$message$reset"
    }

}
