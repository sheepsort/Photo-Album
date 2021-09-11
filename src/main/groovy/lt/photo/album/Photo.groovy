package lt.photo.album

import grails.validation.Validateable

class Photo implements Validateable {
  Integer albumId
  Integer id
  String title
  String url
  String thumbnailUrl

  static constraints = {
    albumId min: 1, nullable: false
    id min: 1, nullable: false
    title nullable: false, blank: false
    url nullable: false, blank: false
    thumbnailUrl nullable: false, blank: false
  }
}
