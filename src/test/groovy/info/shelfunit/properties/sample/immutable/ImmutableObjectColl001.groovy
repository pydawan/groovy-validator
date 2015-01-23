package info.shelfunit.properties.sample.immutable

import info.shelfunit.properties.annotations.ImmutableValidator
import info.shelfunit.properties.annotations.IntAnnotation
import info.shelfunit.properties.annotations.StringAnnotation

@ImmutableValidator
class ImmutableObjectColl001 {
    // @StringAnnotation( minLength = 5, maxLength = 10 )
    String firstString
    @IntAnnotation( minValue = 10, maxValue = 100 )
    int firstInt
}

