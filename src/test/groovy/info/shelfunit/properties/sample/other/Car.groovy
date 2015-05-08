package info.shelfunit.properties.sample.other

import groovy.transform.ToString
import groovy.transform.TupleConstructor 
import validation.IntAnnotation
import validation.AstImmutableConstructor

@ToString( includeNames = true )
// @AstImmutableConstructor
@TupleConstructor
class Car {
    @IntAnnotation( minValue = 10 )
    int miles
    // @IntAnnotation( minValue = 1990 )
    final int year
    
    Car( theYear ) { year = theYear }
}

