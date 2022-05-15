package com.tymdoc.service.variable;

import com.intellij.psi.PsiElement;

/**
 * 变量生成器
 *
 * @author icepring
 * @date 2019/12/07
 */
public interface VariableGenerator {
    /**
     * 生成
     *
     * @param element 元素
     * @return {@link String}
     */
    String generate(PsiElement element);
}
