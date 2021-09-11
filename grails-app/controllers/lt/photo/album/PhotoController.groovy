package lt.photo.album

class PhotoController {
    PhotoService photoService

    static allowedMethods = [
        printPhotosByAlbum: 'GET'
    ]

    def printPhotosByAlbum() {
        photoService.printPhotosByAlbum()
        render status: 200
    }
}
