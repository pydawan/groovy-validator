package info.shelfunit.properties.sample.divisor

import java.lang.reflect.Method
import spock.lang.Specification
import org.junit.Rule
import org.junit.rules.TestName
import info.shelfunit.properties.annotations.AnnotationProcessor

class IntDivisorSpec extends Specification { 
    def setup() {}          // run before every feature method
    def cleanup() {}        // run after every feature method
    def setupSpec() {
        AnnotationProcessor.process( IntDivisor )
    }     // run before the first feature method
    def cleanupSpec() {}   // run after the last feature method
    
    @Rule 
    TestName name = new TestName()
    
    def "test both int fields"() {
        println "--- Starting test ${name.methodName}"
        
        def rid = new IntDivisor(  )
        when:
        rid.intWithDiv = 15
        rid.intWithDiv002 = 14
        
        then:
        rid.intWithDiv == 15
        rid.intWithDiv002 == 14
        
        when:
        rid.intWithDiv = 5
        rid.intWithDiv002 = 13
        
        then:
        rid.intWithDiv == 15
        rid.intWithDiv002 == 14
        
    } // end "test both int fields"
    

    
} // IntDivisorSpec

