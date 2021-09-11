package lt.photo.album

import grails.testing.services.ServiceUnitTest
import org.junit.Rule
import org.springframework.boot.test.rule.OutputCapture
import spock.lang.Specification

class PrintServiceSpec extends Specification implements ServiceUnitTest<PrintService>{

    @Rule
    OutputCapture capture = new OutputCapture()

    /**
     * A significant advantage to isolating a responsibility like logging is a clear boundary for testing.
     * I didn't have the time to design tests for a println statement myself, and instead pulled these from this blog:
     * https://blog.mrhaki.com/2015/02/spocklight-capture-and-assert-system.html
     * They're straightforward enough I feel comfortable using them as a starting point and making minimal changes.
     */
    void "the logToConsole method prints a string matching the value provided"() {
        given:
        String validString = "some great string"

        when:
        service.logToConsole(validString)

        then:
        capture.toString() == "some great string\r\n"
    }

    void "the logToConsole method raises an appropriate exception when given a null string to print"() {
        when:
        service.logToConsole(null)

        then:
        Exception ex = thrown()
        ex.message == 'Must provide a string to print.'
    }

    void "the logToConsole method raises an appropriate exception when given an empty string to print"() {
        when:
        service.logToConsole('')

        then:
        Exception ex = thrown()
        ex.message == 'Must provide a string to print.'
    }
}
