package com.techland.paypay.annotations;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ExecutableType;

@SupportedAnnotationTypes("com.javacodegeeks.advanced.processor.TryCatch")
@SupportedSourceVersion(SourceVersion.RELEASE_8)

public class TryCatchAnnotationProcessor extends AbstractProcessor {

	@Override
	public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {

		for (TypeElement annotation : annotations) {

			Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);

			Map<Boolean, List<Element>> annotatedMethods = annotatedElements.stream().collect(

					Collectors.partitioningBy(
							element -> {return true;}
									)
			);

			List<Element> methods = annotatedMethods.get(true);
			

			methods.stream().forEach(action->(
					 (ExecutableType)action.asType().toString()
					)
			      
			     
			    		

					);
			
			
			String className = ((TypeElement) methods.get(0).getEnclosingElement()).getQualifiedName().toString();
			
			Map<String,String> methodMap = methods.stream().collect(
					Collectors.toMap(method ->method.getSimpleName().toString(),
							method -> ((ExecutableType)method.asType()).getParameterTypes().get(0).toString()));

			
		}

		return true;
	}

}
