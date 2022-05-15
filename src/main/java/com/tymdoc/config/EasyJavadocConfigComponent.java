package com.tymdoc.config;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.tymdoc.model.EasyJavadocConfiguration;
import com.tymdoc.model.EasyJavadocConfiguration.TemplateConfig;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.TreeMap;

/**
 * @author icepring
 * @date 2019/08/25
 */
@State(name = "easyJavadoc", storages = {@Storage("easyJavadoc.xml")})
public class EasyJavadocConfigComponent implements PersistentStateComponent<EasyJavadocConfiguration> {

    public static final String DEFAULT_DATE_FORMAT = "yyyy/MM/dd";
    private EasyJavadocConfiguration configuration;

    @Nullable
    @Override
    public EasyJavadocConfiguration getState() {
        if (configuration == null) {
            configuration = new EasyJavadocConfiguration();
            configuration.setAuthor(System.getProperty("user.name"));
            configuration.setDateFormat(DEFAULT_DATE_FORMAT);
            configuration.setSimpleFieldDoc(true);
            configuration.setMethodReturnType(EasyJavadocConfiguration.LINK_RETURN_TYPE);
            configuration.setWordMap(new TreeMap<>());
            configuration.setTranslator(Consts.GOOGLE_TRANSLATOR);
            configuration.setClassTemplateConfig(new TemplateConfig());
            configuration.setMethodTemplateConfig(new TemplateConfig());
            configuration.setFieldTemplateConfig(new TemplateConfig());
        }
        return configuration;
    }

    @Override
    public void loadState(@NotNull EasyJavadocConfiguration state) {
        XmlSerializerUtil.copyBean(state, Objects.requireNonNull(getState()));
    }
}
