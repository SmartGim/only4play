package com.only4play.codegen;

import com.google.common.collect.Sets;
import com.only4play.FieldDesc;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;

/**
 * @Author: Gim
 * @Date: 2019-10-08 16:33
 * @Description:
 */
public abstract class BaseGenProcessor<T extends Annotation> extends AbstractProcessor {

  protected final Class processAnnotation;
  protected Filer filer;

  public BaseGenProcessor(Class<T> tClass) {
    this.processAnnotation = tClass;
  }

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    return Sets.newHashSet(processAnnotation.getCanonicalName());
  }

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latestSupported();
  }

  @Override
  public synchronized void init(ProcessingEnvironment processingEnv) {
    super.init(processingEnv);
    this.filer = processingEnv.getFiler();
  }

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    Set<Element> annotatedClass = roundEnv.getElementsAnnotatedWith(processAnnotation);
    for (TypeElement e : ElementFilter.typesIn(annotatedClass)) {
      genCode(e, roundEnv);
    }
    return false;
  }

  /**
   * filter fields
   */
  public Set<VariableElement> filterFields(List<? extends Element> elements,
      Predicate<Element> predicate) {
    Set<VariableElement> variableElements = ElementFilter.fieldsIn(elements).stream()
        .filter(predicate).collect(Collectors.toSet());
    return variableElements;
  }

  public void genJavaFile(String packageName, String pathStr, TypeSpec.Builder typeSpecBuilder,boolean override) {
    JavaFile javaFile = JavaFile.builder(packageName, typeSpecBuilder.build()).build();
    System.out.println("************Java Source File***********");
    System.out.println(javaFile);
    try {
      Path path = Paths.get(pathStr);
      File file = new File(path.toFile().getAbsolutePath());
      javaFile.writeTo(file);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * gen source code to target
   */
  public void genJavaFileToTarget(String packageName, TypeSpec.Builder typeSpecBuilder) {
    JavaFile javaFile = JavaFile.builder(packageName, typeSpecBuilder.build()).build();
    try {
      javaFile.writeTo(filer);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  protected String getFieldDesc(VariableElement ve) {
    return Optional.ofNullable(ve.getAnnotation(FieldDesc.class))
        .map(s -> s.name()).orElse(ve.getSimpleName().toString());
  }

  public TypeElement getSuperClass(TypeElement element) {
    TypeMirror parent = element.getSuperclass();
    if (parent instanceof DeclaredType) {
      Element elt = ((DeclaredType) parent).asElement();
      if (elt instanceof TypeElement) {
        return (TypeElement) elt;
      }
    }
    return null;
  }

  /**
   * gen code logic
   */
  protected abstract void genCode(TypeElement e, RoundEnvironment roundEnvironment);
}