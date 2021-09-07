import spock.lang.Specification
import spock.lang.Unroll

class PhotoSpec extends Specification {
  Map photoParams

  void beforeEach() {
    // example photo from https://jsonplaceholder.typicode.com/photos?id=1
    photoParams = [
        "albumId"     : 1,
        "id"          : 1,
        "title"       : "accusamus beatae ad facilis cum similique qui sunt",
        "url"         : "https://via.placeholder.com/600/92c952",
        "thumbnailUrl": "https://via.placeholder.com/150/92c952"
    ]
  }

  void 'A Photo will instantiate when given valid parameters'() {
    when:
    new Photo(photoParams)

    then:
    notThrown()
  }
}
