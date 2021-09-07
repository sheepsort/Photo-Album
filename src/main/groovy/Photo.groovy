import grails.validation.Validateable

class Photo implements Validateable {
  Integer albumId
  Integer id
  String title
  String url
  String thumbnailUrl

  static constraints = {
    albumId min: 1
  }
}
