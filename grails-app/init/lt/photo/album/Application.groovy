package lt.photo.album

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import grails.util.Holders

class Application extends GrailsAutoConfiguration {
    static void main(String[] args) {
        GrailsApp.run(Application, args)
        // kick off the operation normally exposed via RESTful endpoint in main() for simplicity's sake
        Holders.grailsApplication.mainContext.photoService.printPhotosByAlbum()
    }
}