package lt.photo.album

class PrintService {

    /**
     * In real life, it's unlikely the express purpose of an API would be to print some text.
     * More likely, we'd be transmitting it somewhere. Given that, I think a reasonable
     * concern to abstract from the rest of the application would (in this case) be logging.
     * Printing or logging is a distinct purpose compared with the manipulation or retrieval of a Photo.
     * @param consoleText
     */
    void logToConsole(String consoleText) {
        if (!consoleText) {
            throw new Exception('Must provide a string to print.')
        }
        println consoleText
    }
}
