package sapsan.core

import sapsan.utils.Colors
import sapsan.utils.Logging

class SapsanException extends Exception {

    SapsanException(String message) {
        Logging.error(Colors.red("SAPSAN: $message"))
        super(message)
    }

}
