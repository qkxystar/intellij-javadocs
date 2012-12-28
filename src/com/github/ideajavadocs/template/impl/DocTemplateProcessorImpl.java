package com.github.ideajavadocs.template.impl;

import com.github.ideajavadocs.template.DocTemplateProcessor;
import com.github.ideajavadocs.transformation.XmlUtils;
import com.intellij.openapi.components.ProjectComponent;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

/**
 * The type Doc template processor impl.
 *
 * @author Sergey Timofiychuk
 */
public class DocTemplateProcessorImpl implements DocTemplateProcessor, ProjectComponent {

    @Override
    public void projectOpened() {
    }

    @Override
    public void projectClosed() {
    }

    @Override
    public void initComponent() {
    }

    @Override
    public void disposeComponent() {
    }

    @NotNull
    @Override
    public String getComponentName() {
        return COMPONENT_NAME;
    }

    @NotNull
    @Override
    public String merge(@Nullable Template template, @NotNull Map<String, Object> params) {
        if (template == null) {
            // TODO throw exception and catch it at top level of app
            return StringUtils.EMPTY;
        }
        Context context = new VelocityContext(params);
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        try {
            return XmlUtils.normalizeTemplate(writer.toString());
        } catch (IOException e) {
            // TODO throw runtime exception and catch it at top level app
            throw new RuntimeException(e);
        }
    }

    @NotNull
    @Override
    public String buildDescription(@NotNull String description) {
        if (StringUtils.isBlank(description)) {
            return StringUtils.EMPTY;
        }
        return buildDescription(description, 0);
    }

    @NotNull
    @Override
    public String buildPartialDescription(@NotNull String description) {
        if (StringUtils.isBlank(description)) {
            return StringUtils.EMPTY;
        }
        return buildDescription(description, 1);
    }

    private String buildDescription(String description, int firstElement) {
        String[] parts = StringUtils.splitByCharacterTypeCamelCase(description);
        StringBuilder result = new StringBuilder();
        for (int i = firstElement; i < parts.length; i++) {
            if (i == firstElement) {
                result.append(StringUtils.capitalize(parts[i]));
            } else {
                result.append(StringUtils.uncapitalize(parts[i]));
            }
            if (i < parts.length - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }

}
