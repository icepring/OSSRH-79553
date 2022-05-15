package com.tymdoc.config;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * 常量
 *
 * @author icepring
 * @date 2019/12/08
 */
public class Consts {
    /**
     * 基础类型集
     */
    public static final Set<String> BASE_TYPE_SET = Sets.newHashSet("byte", "short", "int", "long", "char", "float",
            "double", "boolean");
    /** 停止词 */
    public static final Set<String> STOP_WORDS = Sets.newHashSet("the");
    /**
     * 腾讯翻译
     */
    public static final String TENCENT_TRANSLATOR = "腾讯翻译";
    /**
     * 百度翻译
     */
    public static final String BAIDU_TRANSLATOR = "百度翻译";
    /**
     * 有道翻译
     */
    public static final String YOUDAO_TRANSLATOR = "有道翻译";
    /**
     * 谷歌翻译
     */
    public static final String GOOGLE_TRANSLATOR = "谷歌翻译";
    /**
     * 金山翻译
     */
    public static final String JINSHAN_TRANSLATOR = "金山翻译";
    /**
     * 关闭翻译
     */
    public static final String CLOSE_TRANSLATOR = "关闭（只使用自定义翻译）";
}
