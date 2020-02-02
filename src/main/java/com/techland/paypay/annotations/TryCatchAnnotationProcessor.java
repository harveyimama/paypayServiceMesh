package com.techland.paypay.annotations;

import java.io.IOException;
import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;
import javax.tools.JavaFileObject;

@SupportedAnnotationTypes("com.javacodegeeks.advanced.processor.Immutable")
@SupportedSourceVersion(SourceVersion.RELEASE_8)

public class TryCatchAnnotationProcessor extends AbstractProcessor {

	@Override
	public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
		
		for( final Element element: roundEnv.getElementsAnnotatedWith( TryCatch.class ) ) {
			
			if( element instanceof TypeElement ) {
				
				final TypeElement typeElement = ( TypeElement )element;
				
				final String className = typeElement.getSimpleName() + "TryCatch";
				final JavaFileObject fileObject = processingEnv.getFiler().createSourceFile(typeElement.getQualifiedName() + "." + className);
				
				
				for( final Element eclosedElement: typeElement.getEnclosedElements() ) {
					
				ElementKind g = eclosedElement.getKind();
				
				if(g.equals(ElementKind.METHOD))
				{
					
					try( Writer writter = fileObject.openWriter() ) {
						writter.append( "package " + eclosedElement.getQualifiedName() + ";" );
						writter.append( "\\n\\n");
						writter.append( "public class " + className + " {" );
						writter.append( "\\n");
						writter.append( "}");
						}
						catch( final IOException ex ) {
						processingEnv.getMessager().printMessage(Kind.ERROR, ex.getMessage());
						}
				}
				
			}
			
		}
		
		
	}
		return true;
}
	
}
