package lt.photo.album

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class PhotoServiceSpec extends Specification implements ServiceUnitTest<PhotoService> {
    ApiClientService apiClientService
    PrintService printService

    String stringifiedPhotoResponse = "[\n" +
        "  {\n" +
        "    \"albumId\": 1,\n" +
        "    \"id\": 1,\n" +
        "    \"title\": \"accusamus beatae ad facilis cum similique qui sunt\",\n" +
        "    \"url\": \"https://via.placeholder.com/600/92c952\",\n" +
        "    \"thumbnailUrl\": \"https://via.placeholder.com/150/92c952\"\n" +
        "  }\n" +
        "]"

    def setup() {
        apiClientService = Mock(ApiClientService) {
            // This mock response is the JSON string I expect back from the first photo returned.
            (0..1) * get(PhotoService.PHOTO_URL) >> stringifiedPhotoResponse
        }

        printService = Mock(PrintService) {
            // mocking this prevents blowing up the console every time the tests run.
            logToConsole(_ as String) >> null
        }

        service.apiClientService = apiClientService
        service.printService = printService
    }

    void "when printPhotosByAlbum is called, an api request is made to fetch the photos with the right URL"() {
        when:
        service.printPhotosByAlbum()

        then:
        1 * apiClientService.get(PhotoService.PHOTO_URL) >> stringifiedPhotoResponse
    }

    void "when printPhotosByAlbum is called and no photos are available, a helpful exception is raised"() {
        when:
        service.printPhotosByAlbum()

        then:
        1 * apiClientService.get(PhotoService.PHOTO_URL) >> null
        Exception ex = thrown()
        ex.message == "No photos could be retrieved at this time. Response code: 404"
    }

    void "when printPhotosByAlbum is called and photos are found, the print service logs their album ID"() {
        when:
        service.printPhotosByAlbum()

        then:
        // This ID value comes from the mock, which is copied values from the API request.
        1 * printService.logToConsole("photo-album 1")
    }

    void "when printPhotosByAlbum is called and photos are found, the print service logs each photo's id and title"() {
        when:
        service.printPhotosByAlbum()

        then:
        1 * printService.logToConsole("[1] accusamus beatae ad facilis cum similique qui sunt")
    }
}
