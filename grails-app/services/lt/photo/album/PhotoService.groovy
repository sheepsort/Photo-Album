package lt.photo.album

import groovy.json.JsonSlurper

class PhotoService {
    ApiClientService apiClientService
    PrintService printService

    protected static final PHOTO_URL = 'https://jsonplaceholder.typicode.com/photos'

    /**
     * The printPhotosByAlbum method is the entry point to interaction with our Photo Service.
     * It will reach out to a static URL for all results there.
     * It then normalizes those values into a list of Photos, and prints each by album number.
     */
    void printPhotosByAlbum() {
        ArrayList<Photo> photos = getPhotos()

        Map<Integer, ArrayList<Photo>> photosByAlbum = transformPhotoArray(photos)

        photosByAlbum.each { Map.Entry<Integer, ArrayList<Photo>> it ->
            printAlbum(it.key, it.value)
        }
    }

    private ArrayList<Photo> getPhotos() {
        String photosJSONString = apiClientService.get(PHOTO_URL)

        if (!photosJSONString) {
            throw new Exception("No photos could be retrieved at this time. Response code: 404")
        }

        ArrayList<Photo> processedPhotos = processPhotoString(photosJSONString)

        processedPhotos
    }

    private ArrayList<Photo> processPhotoString(String photosToProcess) {
        JsonSlurper jsonSlurper = new JsonSlurper()

        ArrayList<Photo> photoArray = []

        jsonSlurper.parseText(photosToProcess).each {
            photoArray += new Photo(it as Map)
        }

        return photoArray
    }

    private Map<Integer, ArrayList<Photo>> transformPhotoArray(ArrayList<Photo> photos) {
        // find the upper and lower bound for the album numbers
        int maxAlbumId = photos.collect { it.albumId }.max()
        int minAlbumId = photos.collect { it.albumId }.min()

        // instantiate a placeholder for mapping corresponding photos to their album as key-value pairs
        Map<Integer, ArrayList<Photo>> transformedPhotos = [:]

        // iterate over each album ID, from the smallest to the largest
        for (int i = minAlbumId; i <= maxAlbumId; i++) {
            // in each iteration, put the corresponding values into the Map
            ArrayList<Photo> matchingPhotos = photos.findAll { it.albumId == i }

            // only add to the response map if values were found that matched the required ID
            if (matchingPhotos && matchingPhotos.size()) {
                transformedPhotos << [(i): matchingPhotos]
            }
        }

        transformedPhotos
    }

    private void printAlbum(int albumId, ArrayList<Photo> photos) {
        printService.logToConsole("photo-album ${albumId}")

        photos.each { Photo photo ->
            printService.logToConsole("[${photo.id}] ${photo.title}")
        }
    }
}
