package info.shelfunit.properties.sample.other

import spock.lang.Specification
import org.junit.Rule
import org.junit.rules.TestName

class CarTest extends Specification {
    def setup() {}       // run before every feature method
    def cleanup() {}     // run after every feature method
    def setupSpec() { }   // run before the first feature method
    def cleanupSpec() {} // run after the last feature method
    
    @Rule 
    TestName name = new TestName()
    
     def "first Test"() {
        println "--- Starting test ${name.methodName}"
        def car = new Car( [ miles: 50, year: 2008 ], true )
        // car.miles = 5
        boolean exceptionThrown = false
        println "Here is car: ${car.toString()}, exceptionThrown: ${exceptionThrown}"
        
        expect:
        
        car.year == 2008
        car.miles == 50
        
    } // first Test
    
    def "second Test"() {
        println "--- Starting test ${name.methodName}"
        when:
        def car = new Car( [ miles: 50, year: 1987 ], true, true )
        car.miles = 50
        boolean exceptionThrown = false
        println "Here is car: ${car.toString()}, exceptionThrown: ${exceptionThrown}"
        
        then:
        def ex = thrown( Exception )
        println "Here is ex.message:\n${ex.message}"
        ex.message == "Groovy validation exception: \n" +
        "1987 is a java.lang.Integer outside the range 1990 to 2147483647 or it is not divisible by anything in the set [1] "
        car == null
        // car.miles == 50
        // car.year == 1987
        
    } // first Test
    
    
    def "test Exception 001"() {
        println "\n--- Starting test ${name.methodName}"
        def car = new Car( [year: 2008, miles: 10], true )
        boolean exceptionThrown = false
        println "Here is car: ${car.toString()}, exceptionThrown: ${exceptionThrown}"
        
        try { 
            car.year = 2010
        } catch ( Exception e ) {
            exceptionThrown = true
        }
        println "Here is car: ${car.toString()}, exceptionThrown: ${exceptionThrown}"
        expect:
        exceptionThrown == true
        car.year == 2008
        car.miles == 10
        
    } // first Test
    
    /*
    def "test Exception"() {
        println "--- Starting test ${name.methodName}"
        def car = new Car( 208 )
        boolean exceptionThrown = false
        println "Here is car: ${car.toString()}, exceptionThrown: ${exceptionThrown}"
        
        try { 
            car.year = 2010
        } catch ( Exception e ) {
            exceptionThrown = true
        }
        expect:
        exceptionThrown == true
        car.year == 208
        car.miles == 0
        
    } // first Test
    
    
  /*
    def "test with annotations"() {
        println "--- Starting test ${name.methodName}"
        def person = new Person().with {
            firstName = "Robert"
            lastName = "LewandowskiLewandowski"
            age = 212
            it // got to have "it" here to return object
        }
        println "person is a ${person.class.name}"
        println "Here is person: ${person.toString()}"
        expect:
        person.firstName == "Robert"
        person.lastName == null
        person.age == null
    } // first Test
 
    */
} // end class


