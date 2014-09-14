package info.shelfunit.properties.annotations

import java.lang.annotation.Retention
import java.lang.annotation.Target

import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy

/**
<p>This is an annotation to validate/constrain integer fields in Plain Old Groovy Objects.</p>
<p>Here is an example on how to use it:</p>
<pre>
    @IntAnnotation( minValue = 30, maxValue = 400 )
    int firstInt
    @IntAnnotation( maxValue = 400 )
    def secondInt
    @IntAnnotation( minValue = 10, divisor = [ 3, 5 ] )
    int dasFizzbuzz
</pre>
<p>If the field is defined as "int" and it is given a value in the first call to setX that is outside your constraints, then it will be set to 0. If the field is defined as "def" and it is given a value that is outside your constraints, then it will be set to null. If the field already has a valid value and it is sent an invalid one in a call to setX, the new, invalid value will be ignored.</p>

<p>An application, class or library that uses this annotation must also import {@link info.shelfunit.properties.annotations.AnnotationProcessor} (for a POGO) or {@link info.shelfunit.properties.annotations.ImmutableValidator} (for an immutable object).</p>
<p></p>
*/

@Retention( RetentionPolicy.RUNTIME ) 
@Target( ElementType.FIELD )
public @interface IntAnnotation {
    /**
    The lowest value you want this field to hold. The default is 0. It could go as low as Integer.MIN_VALUE.
    */
  public int minValue() default 0
  /**
  The highest value you want this field to hold. The default is 2,147,483,647, which is the same as Integer.MAX_VALUE.
  */
  public int maxValue() default 2147483647
  
  /**
  A number that your variable can be divided by. If you want a number to be even, then would select [ 2 ]. If you wanted to do fizzbuzz, you would set it to [ 3, 5 ]. The default is [1]. This must be specified using the list notation. If you simply provide a number things will not work as expected.
  */
  public int[] divisor() default [1]
}


