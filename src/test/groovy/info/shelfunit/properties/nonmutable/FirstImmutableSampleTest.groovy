package info.shelfunit.properties.nonmutable

import spock.lang.Specification
import org.junit.Rule
import org.junit.rules.TestName

import validation.AnnotationProcessor

class FirstImmutableSampleTest extends Specification { 
    
    def setup() {}       // run before every feature method
    def cleanup() {}     // run after every feature method
    def setupSpec() {
        AnnotationProcessor.process( FirstImmutableSample.class ) 
    }   // run before the first feature method
    def cleanupSpec() {} // run after the last feature method
    
    @Rule 
    TestName name = new TestName()
    
    // for some reason the first time you call a class it does not actually process the annotations
    // comment out the lines for the "junk" object and compare
    def "test the no arg constructor"() {
        println "--- Starting test ${name.methodName}"
        // println "About to make junk"
        // def junk = new FirstImmutableSample()
        println "About to make throwaway"
        def throwaway = new FirstImmutableSample( first: "Not Junk", // secondString: "Goodbye Junk", 
        firstInt: 21 )
        throwaway.first = "Throwaway"
        println "Just made throwaway, about to make bTest1"
        def bTest1 = new FirstImmutableSample( first: "Hello1", // secondString: "Goodbye", 
        firstInt: 21, secondInt: 2000 )
        println "In test ${name.methodName}, bTest1: ${bTest1.toString()}"
        expect:
        bTest1.first == "Hello1"
        bTest1.secondInt == null
        
    } // end "test the no arg constructor"
    
    
    def "test bTest2"() {
        println "--- Starting test ${name.methodName}"
        def bTest2 = new FirstImmutableSample( first: "Hello2", // secondString: "Goodbye, this is more than 20 characters", 
        firstInt: 22, secondInt: 20 )
        println "In test ${name.methodName}, bTest2: ${bTest2.toString()}"
        expect:
        bTest2.first == "Hello2"
    } // end "test bTest2"
    
    def "test the no arg constructor again"() {
        println "--- Starting test ${name.methodName}"
        /*
        def constructors = FirstImmutableSample.class.getConstructors()
        constructors.each { 
            println "Constructor: ${it.toString()}"
        }
        def methods = FirstImmutableSample.metaClass.getMetaMethods()
        methods.each {
            println "MetaMethod: ${it.toString()}"
        }
        */
        
        def bTest1 = new FirstImmutableSample( first: "Hello3", // secondString: "Goodbye", 
        firstInt: 21, secondInt: 401 )
        println "In test ${name.methodName}, bTest1: ${bTest1.toString()}"
        expect:
        bTest1.first == "Hello3"
        bTest1.secondInt == null
        
    } // end "test the no arg constructor again"

}

