package com.tymdoc.service.generator.impl;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import com.intellij.psi.javadoc.PsiDocComment;
import com.tymdoc.config.EasyJavadocConfigComponent;
import com.tymdoc.model.EasyJavadocConfiguration;
import com.tymdoc.service.TranslatorService;
import com.tymdoc.service.VariableGeneratorService;
import com.tymdoc.service.generator.DocGenerator;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * 属性文档生成器
 *
 * @author icepring
 * @date 2019/11/12
 */
public class FieldDocGenerator implements DocGenerator {

    private TranslatorService translatorService = ServiceManager.getService(TranslatorService.class);
    private EasyJavadocConfiguration config = ServiceManager.getService(EasyJavadocConfigComponent.class).getState();
    private VariableGeneratorService variableGeneratorService = ServiceManager.getService(VariableGeneratorService.class);

    @Override
    public String generate(PsiElement psiElement) {
        if (!(psiElement instanceof PsiField)) {
            return StringUtils.EMPTY;
        }
        PsiField psiField = (PsiField) psiElement;
        if (config != null && config.getFieldTemplateConfig() != null
                && Boolean.TRUE.equals(config.getFieldTemplateConfig().getIsDefault())) {
            return defaultGenerate(psiField);
        } else {
            return customGenerate(psiField);
        }

    }

    /**
     * 默认的生成
     *
     * @param psiField 当前属性
     * @return {@link String}
     */
    private String defaultGenerate(PsiField psiField) {
        if (BooleanUtils.isTrue(config.getSimpleFieldDoc())) {
            return genSimpleDoc(psiField.getName());
        } else {
            return genNormalDoc(psiField, psiField.getName());
        }
    }

    /**
     * 自定义生成
     *
     * @param psiField 当前属性
     * @return {@link String}
     */
    private String customGenerate(PsiField psiField) {
        return variableGeneratorService.generate(psiField);
    }

    /**
     * 生成正常的文档
     *
     * @param psiField 属性
     * @param name     名字
     * @return {@link String}
     */
    private String genNormalDoc(PsiField psiField, String name) {
        PsiDocComment comment = psiField.getDocComment();
        if (comment != null) {
            List<PsiElement> elements = Lists.newArrayList(comment.getChildren());

            // 注释
            String desc = translatorService.translate(name);
            List<String> commentItems = Lists.newLinkedList();
            for (PsiElement element : elements) {
                commentItems.add(element.getText());
            }
            commentItems.add(1, buildDesc(elements, desc));
            return Joiner.on(StringUtils.EMPTY).skipNulls().join(commentItems);
        }
        return String.format("/**%s* %s%s */%s", "\n", translatorService.translate(name), "\n",
            "\n");
    }

    /**
     * 构建描述
     *
     * @param elements 元素
     * @param desc     描述
     * @return {@link String}
     */
    private String buildDesc(List<PsiElement> elements, String desc) {
        for (PsiElement element : elements) {
            if (!"PsiDocToken:DOC_COMMENT_DATA".equalsIgnoreCase(element.toString())) {
                continue;
            }
            String source = element.getText().replaceAll("[/* \n]+", StringUtils.EMPTY);
            if (Objects.equals(source, desc)) {
                return null;
            }
        }
        return desc;
    }

    /**
     * 生成简单的文档
     *
     * @param name 的名字
     * @return {@link String}
     */
    private String genSimpleDoc(String name) {
        return String.format("/** %s */", translatorService.translate(name));
    }

}
