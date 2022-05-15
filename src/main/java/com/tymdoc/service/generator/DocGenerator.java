package com.tymdoc.service.generator;

import com.intellij.psi.PsiElement;

/**
 * 文档生成器
 *
 * @author icepring
 * @date 2019/11/12
 */
public interface DocGenerator {
    /**
     * 生成
     *
     * @param psiElement psiElement
     * @return java.lang.String
     */
    String generate(PsiElement psiElement);
}
