package com.only4play.codegen.query;

import com.only4play.codegen.BaseGenProcessor;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import java.util.Set;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import lombok.Data;

/**
 * @Author: Gim
 * @Date: 2019-10-08 17:14
 * @Description:
 */
@AutoService(Processor.class)
public class GenQueryProcessor extends BaseGenProcessor<GenQuery> {

  public GenQueryProcessor() {
    super(GenQuery.class);
  }

  @Override
  protected void genCode(TypeElement e, RoundEnvironment roundEnvironment) {
    Set<VariableElement> variableElements = filterFields(e.getEnclosedElements(),
        p -> Objects.nonNull(p.getAnnotation(QueryItem.class)));
    String packageName = e.getAnnotation(GenQuery.class).pkgName();
    String pathStr = e.getAnnotation(GenQuery.class).sourcePath();
    boolean override = e.getAnnotation(GenQuery.class).overrideSource();
    String className = e.getSimpleName() + "Query";
    TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder(className)
        .addModifiers(Modifier.PUBLIC)
        .addAnnotation(ApiModel.class)
        .addAnnotation(Data.class);
    for (VariableElement ve : variableElements) {
      FieldSpec.Builder fieldSpec = FieldSpec
          .builder(TypeName.get(ve.asType()), ve.getSimpleName().toString(), Modifier.PRIVATE)
          .addAnnotation(AnnotationSpec.builder(ApiModelProperty.class)
              .addMember("value", "$S", getFieldDesc(ve))
              .build());
      typeSpecBuilder.addField(fieldSpec.build());
    }
    genJavaFile(packageName, pathStr, typeSpecBuilder,override);
  }
}