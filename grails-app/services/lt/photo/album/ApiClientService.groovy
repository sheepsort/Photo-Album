package lt.photo.album

class ApiClientService {

    /**
     * Given zero time constraints, I would test drive the behavior of a service like this via a series of integration tests.
     * Its purpose is to wrap external API interactions in a common interface for the rest of the application.
     * Unit tests are a bit limited here, so I'd stand up some mock server and integration test the
     * types of methods we'd need in an API Client wrapper.
     *
     * It would also probably use a shared client, so it could pool the requests to perform more efficiently,
     * and offer guidance on how to run this method asynchronously.
     */
    String get(String url) {
        URL getURL = new URL(url)

        URLConnection getConnection = getURL.openConnection()

        getConnection.setRequestProperty('method', 'GET')

        // for quick development of side projects that require consuming 3rd party APIs,
        // I've had this SO post bookmarked for forever: https://stackoverflow.com/questions/2793150/how-to-use-java-net-urlconnection-to-fire-and-handle-http-requests
        // HTTP requests in Java are a solved problem; no need to be clever here IMO.
        InputStream response = getConnection.getInputStream()

        Scanner scanner = new Scanner(response)
        String responseBody = scanner.useDelimiter("\\A").next()

        responseBody
    }

}
