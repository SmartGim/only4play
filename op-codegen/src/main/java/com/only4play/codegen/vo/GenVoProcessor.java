package com.only4play.codegen.vo;

import com.google.auto.service.AutoService;
import com.google.common.reflect.TypeToken;
import com.only4play.codegen.BaseGenProcessor;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
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
 * @Date: 2019/11/25 14:20
 * @Description:
 */
@AutoService(Processor.class)
public class GenVoProcessor extends BaseGenProcessor<GenVo> {

  private static final String PREFIX = "Base";
  private static final String SUFFIX = "Vo";

  public GenVoProcessor() {
    super(GenVo.class);
  }

  @Override
  protected void genCode(TypeElement e, RoundEnvironment roundEnvironment) {
    Set<VariableElement> variableElements = filterFields(e.getEnclosedElements(),
        p -> Objects.isNull(p.getAnnotation(IgnoreVo.class)) && !voIgnore(p));
    Set<VariableElement> parentElements = filterFields(getSuperClass(e).getEnclosedElements(),p -> Objects.isNull(p.getAnnotation(IgnoreVo.class)));
    variableElements.addAll(parentElements);
    //以上处理父子类的所有属性
    String packageName = e.getAnnotation(GenVo.class).pkgName();
    String pathStr = e.getAnnotation(GenVo.class).sourcePath();
    boolean override = e.getAnnotation(GenVo.class).overrideSource();
    String className = PREFIX + e.getSimpleName() + SUFFIX;
    String sourceName = e.getSimpleName() + SUFFIX;
    TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder(className)
        .addModifiers(Modifier.PUBLIC)
        .addAnnotation(ApiModel.class)
        .addAnnotation(Data.class);
    for (VariableElement ve : variableElements) {
      TypeName typeName;
      if(Objects.nonNull(ve.getAnnotation(VoType.class))){
        typeName = TypeName.get(TypeToken.of(ve.getAnnotation(VoType.class).toType()).getType());
      }else {
        typeName = TypeName.get(ve.asType());
      }
      FieldSpec.Builder fieldSpec = FieldSpec
          .builder(typeName, ve.getSimpleName().toString(), Modifier.PRIVATE)
          .addAnnotation(AnnotationSpec.builder(ApiModelProperty.class)
              .addMember("value", "$S", getFieldDesc(ve))
              .build());
      typeSpecBuilder.addField(fieldSpec.build());
    }
    TypeSpec.Builder source = TypeSpec.classBuilder(sourceName)
        .superclass(ClassName.get(packageName, className))
        .addModifiers(Modifier.PUBLIC)
        .addAnnotation(ApiModel.class)
        .addAnnotation(Data.class);
    /**
     * generate base class
     */
    genJavaFileToTarget(packageName, typeSpecBuilder);
    /**
     * generate source file
     */
    genJavaFile(packageName, pathStr, source,override);
  }

  private boolean voIgnore(Element p) {
    return false;
  }
}
