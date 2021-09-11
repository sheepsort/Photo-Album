package lt.photo.album

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification
import spock.lang.Unroll

import static javax.servlet.http.HttpServletResponse.*

class PhotoControllerSpec extends Specification implements ControllerUnitTest<PhotoController> {
    PhotoService photoService

    def setup() {
        photoService = Mock(PhotoService)
        controller.photoService = photoService
    }

    void "the controller accepts GET requests for the print method"() {
        given:
        request.method = 'GET'

        when:
        controller.printPhotosByAlbum()

        then:
        response.status == SC_OK
    }

    @Unroll
    void "the controller does not accept #method requests for the print method"(String method) {
        when:
        request.method = method

        and:
        controller.printPhotosByAlbum()

        then:
        response.status == SC_METHOD_NOT_ALLOWED

        where:
        method << ['PATCH', 'DELETE', 'POST', 'PUT']
    }

    void "printing photos routes to the correct service method for processing"() {
        given:
        request.method = 'GET'

        when:
        controller.printPhotosByAlbum()

        then:
        1 * photoService.printPhotosByAlbum() >> null
    }
}
