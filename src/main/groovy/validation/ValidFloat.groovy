package validation

import org.codehaus.groovy.transform.GroovyASTTransformationClass

import java.lang.annotation.Retention
import java.lang.annotation.Target

import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy

/**
<p>This is an annotation to validate/constrain floating number fields in Plain Old Groovy Objects.</p>
<p>Here is an example on how to use it:</p>
<pre>
    @ValidFloat( minValue = 0f, maxValue = 1000f, throwEx = false )
    def firstNum
    @ValidFloat( maxValue = 1000f )
    float secondNum
</pre>
<p>If the field is defined as "float" and it is given a value in the first call to setX that is outside your constraints, then it will be set to 0. If the field is defined as "def" and it is given a value that is outside your constraints, then it will be set to null. If the field already has a valid value and it is sent an invalid one in a call to setX, the new, invalid value will be ignored.</p>

<p>An application, class or library that uses this annotation must also import {@link validation.ImmutableValidator} to use this in an immutable object.</p>

<p>You must append the "f" at the end of the number. If you set "throwException" to true for {@link validation.ImmutableValidator} and an exception is thrown, the "f" will not be printed as part of the number in the message.</p>

<p></p>
*/

@Retention( RetentionPolicy.RUNTIME ) 
@Target( ElementType.FIELD )
@GroovyASTTransformationClass( [ 'info.shelfunit.properties.annotations.ValidFloatTransform' ] )
public @interface ValidFloat {
    /**
    The lowest value you want this field to hold. The default is 0.0f. It could go as low as Float.MIN_VALUE.
    */
  public float minValue() default 0.0f
  /**
  The highest value you want this field to hold. The default is 3.4x10^38, which is the same as Float.MAX_VALUE.
  */
  public float maxValue() default 3.4028235E38f 
  /*
  A number that your variable can be divided by. If you want a number to be even, then would select 2.
  */
  // public float[] divisor() default [ 1.0f  ]
  
    /**
    A boolean declaring if an exception should be thrown if any of the contraints are violated. Defaults to true.
    This is not used by {@link validation.ImmutableValidator}.
    */
  public boolean throwEx() default true
}


