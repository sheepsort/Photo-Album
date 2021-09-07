import spock.lang.Specification

class PhotoSpec extends Specification {
  void 'A Photo will instantiate when given valid parameters'() {
    given:
    // https://jsonplaceholder.typicode.com/photos?id=1
    Map photoParams = [
        "albumId"     : 1,
        "id"          : 1,
        "title"       : "accusamus beatae ad facilis cum similique qui sunt",
        "url"         : "https://via.placeholder.com/600/92c952",
        "thumbnailUrl": "https://via.placeholder.com/150/92c952"
    ]

    when:
    Photo photo = new Photo(photoParams)

    then:
    notThrown()
  }
}
