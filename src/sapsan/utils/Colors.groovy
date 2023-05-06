package sapsan.utils

import com.cloudbees.groovy.cps.NonCPS

final class Colors {

    static String reset = "\033[0m"
    static String redBg = "\033[101m"

    @NonCPS
    static String red(String message) {
        "$redBg$message$reset"
    }

}
