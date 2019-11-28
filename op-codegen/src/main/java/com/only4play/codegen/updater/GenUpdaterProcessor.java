package com.only4play.codegen.updater;

import com.google.auto.service.AutoService;
import com.google.common.base.CaseFormat;
import com.only4play.codegen.BaseGenProcessor;
import com.only4play.codegen.constants.ErrorConstants;
import com.only4play.codegen.mapper.GenMapper;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.CodeBlock.Builder;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;
import lombok.Data;

/**
 * @Author: Gim
 * @Date: 2019/11/28 19:33
 * @Description:
 */

@AutoService(Processor.class)
public class GenUpdaterProcessor extends BaseGenProcessor<GenUpdater> {

  private static final String PREFIX = "Base";
  private static final String SUFFIX = "Updater";

  public GenUpdaterProcessor() {
    super(GenUpdater.class);
  }

  @Override
  protected void genCode(TypeElement e, RoundEnvironment roundEnvironment) {
    Set<VariableElement> variableElements = filterFields(e.getEnclosedElements(),
        p -> Objects.isNull(p.getAnnotation(IgnoreUpdater.class)));
    String packageName = e.getAnnotation(GenUpdater.class).pkgName();
    String pathStr = e.getAnnotation(GenUpdater.class).sourcePath();
    String className = PREFIX + e.getSimpleName() + SUFFIX;
    String sourceName = e.getSimpleName() + SUFFIX;
    boolean override = e.getAnnotation(GenMapper.class).overrideSource();
    TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder(className)
        .addModifiers(Modifier.PUBLIC)
        .addAnnotation(ApiModel.class)
        .addAnnotation(Data.class);
    for (VariableElement ve : variableElements) {
      if (ve.asType().getKind().isPrimitive()) {
        messager.printMessage(
            Diagnostic.Kind.ERROR,
            ErrorConstants.PRIMITIVE_NOT_ALLOW, e);
        return;
      }
      FieldSpec.Builder fieldSpec = FieldSpec
          .builder(TypeName.get(ve.asType()), ve.getSimpleName().toString(), Modifier.PRIVATE)
          .addAnnotation(AnnotationSpec.builder(ApiModelProperty.class)
              .addMember("value", "$S", getFieldDesc(ve))
              .build());
      typeSpecBuilder.addField(fieldSpec.build());
    }
    Builder builder = CodeBlock.builder();
    for (VariableElement ve : variableElements) {
      builder.addStatement("$T.ofNullable($L()).ifPresent(v -> param.$L(v))", Optional.class,
          "get" + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, ve.getSimpleName().toString()),
          "set" + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, ve.getSimpleName().toString()));
    }
    MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("update" + e.getSimpleName())
        .addModifiers(Modifier.PUBLIC)
        .addParameter(TypeName.get(e.asType()), "param")
        .addCode(builder.build())
        .returns(void.class);
    typeSpecBuilder.addMethod(methodBuilder.build());
    TypeSpec.Builder source = TypeSpec.classBuilder(sourceName)
        .superclass(ClassName.get(packageName, className))
        .addModifiers(Modifier.PUBLIC)
        .addAnnotation(ApiModel.class)
        .addAnnotation(Data.class);
    genJavaFileToTarget(packageName, typeSpecBuilder);
    genJavaFile(packageName, pathStr, source, override);
  }
}