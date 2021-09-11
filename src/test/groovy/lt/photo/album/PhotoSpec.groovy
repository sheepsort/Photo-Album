package lt.photo.album

import spock.lang.Specification
import spock.lang.Unroll

class PhotoSpec extends Specification {
  Map photoParams

  void setup() {
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

  void 'A Photo will pass validation when given valid parameters'() {
    when:
    Photo photo = new Photo(photoParams)

    then:
    photo.validate()
  }

  @Unroll
  void "When the Photo's #attribute is #testValue, validation is #isValid with exception #reason"(
      String attribute,
      def testValue,
      boolean isValid,
      String reason
  ) {
    given: "A new photo is created"
    Photo photo = new Photo(photoParams)

    when: "The photo's attribute is set to the value under test"
    photo."$attribute" = testValue

    then: "The photo should pass or fail validation"
    photo.validate() == isValid

    and: "The photo has the correct error message, if necessary"
    Integer reasonIndex = 0
    if (!isValid) {
      reasonIndex = photo.errors.getFieldError("$attribute").defaultMessage.indexOf(reason)
    }
    reasonIndex > -1

    where: "Some value is assigned to the attribute"
    attribute      | testValue || isValid | reason
    'albumId'      | 0         || false   | 'minimum value'
    'albumId'      | null      || false   | 'cannot be null'
    'id'           | 0         || false   | 'minimum value'
    'id'           | null      || false   | 'cannot be null'
    'title'        | null      || false   | 'cannot be null'
    'title'        | ''        || false   | 'cannot be blank'
    'url'          | null      || false   | 'cannot be null'
    'url'          | ''        || false   | 'cannot be blank'
    'thumbnailUrl' | null      || false   | 'cannot be null'
    'thumbnailUrl' | ''        || false   | 'cannot be blank'
  }
}
