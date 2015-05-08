package info.shelfunit.properties.annotations

import org.codehaus.groovy.ast.ASTNode
// import org.codehaus.groovy.ast.ClassNode 

import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation 

@GroovyASTTransformation( phase = CompilePhase.INSTRUCTION_SELECTION )
class FloatAnnotationTransform implements ASTTransformation {
    
    void visit( ASTNode[] astNodes, SourceUnit sourceUnit ) {
        
        if ( !astNodes ) { return }
        if ( !astNodes[ 0 ] ) { return }
        if ( !astNodes[ 1 ] ) { return }
        astNodes.eachWithIndex { theNode, x ->
            // println "theNode [${x}] is a ${theNode.class.name}"
        }
        def annotationNode = astNodes[ 0 ]
        def fieldNode = astNodes[ 1 ]
        // theNode [0] is a org.codehaus.groovy.ast.AnnotationNode
        // theNode [1] is a org.codehaus.groovy.ast.FieldNode
        // println "annotation is for ${annotationNode.classNode.name}"
        // println "field is for class ${fieldNode.getOwner().name} and field ${fieldNode.name}, so setter would be set${fieldNode.name.capitalize()}"
        
        def theAnnotation = annotationNode.classNode
        // println "methods of annotation  ${theAnnotation.name}:"
        theAnnotation.methods.each { methodNode ->
            print " ${methodNode.name}, "
        }
        def annotatedClass = fieldNode.getOwner() // the class
        // println "\nmethods of class ${annotatedClass.name}" // look for createValidatingConstructor from AstImmutableConstructorTransform
        def hasCreateValidatingConstructor = false
        def methodToRemove
        annotatedClass.methods.each { mNode ->
            // print " ${mNode.name}, "
            if ( mNode.name == "createValidatingConstructor" ) { hasCreateValidatingConstructor = true }
            if ( mNode.name == "set${fieldNode.name.capitalize()}" ) { methodToRemove = mNode }
        }
  
        // println "\n--------------------------------------\n\n"
        
        def min = annotationNode.getMember( 'minValue' ) ? annotationNode.getMember( 'minValue' ).getValue() : 0 
        def max = annotationNode.getMember( 'maxValue' ) ? annotationNode.getMember( 'maxValue' ).getValue() :  Float.MAX_VALUE 
        def throwEx = annotationNode.getMember( 'throwEx' ) ? annotationNode?.getMember( 'throwEx' ).getValue() : true
        
        def methodString = new StringBuffer()
        methodString << """
    public void set${fieldNode.name.capitalize()}( Object arg ) {
        if ( arg.getClass().getName() != "java.lang.Float" ) {
            // System.out.println( "Method set${fieldNode.name.capitalize()} called with arg " + arg + ", ignoring the love" );
        } else {
        """
         methodString << """
            if ( ( arg >= ${min} ) && ( arg <= ${max} ) && ( arg >= Float.MIN_VALUE ) && ( arg <= Float.MAX_VALUE ) ) {
                this.${fieldNode.getName()} = arg;
            """
                if ( throwEx ) {
                    methodString << """
             } else {
                throw new Exception(
                     arg + " is a Float outside the range ${min} to ${max}" )
                     """
                }
            methodString << """
            }
        }
    }
    """

        // println "here is the method string: ${methodString}"
        if ( !hasCreateValidatingConstructor ) {
            try {
                def ast = new AstBuilder().buildFromString( CompilePhase.INSTRUCTION_SELECTION, false, methodString.toString() )
                // println "ast[ 0 ] is a ${ast[ 0 ].class.name}, and ast[ 1 ] is a ${ast[ 1 ].class.name}"
                def someClassNode = ast[ 1 ]
                def methods = ast[ 1 ].methods
                annotatedClass.addMethod( methods.find { it.name == "set${fieldNode.name.capitalize()}" } )
                
            } catch ( Exception e ) {
                println "Some exception occured"
                e.printStackTrace()
            }
        }
        
    } // end method visit
    
} // end class  - line 208, 228, 211
