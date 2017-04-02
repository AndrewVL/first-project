package ru.avl.simpleweb.templater;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

/**
 * Created by Andrey on 01.04.2017.
 */
public class PageGenerator {

    private static final String TEMPLATE_DIR = "templates";

    private static PageGenerator pageGenerator;

    private static Configuration cfg;

    public static PageGenerator getInstance() {
        if (pageGenerator == null)
            return new PageGenerator();
        return pageGenerator;
    }

    public String getPage(String fileName, Map<String, Object> data) {
        Writer stream = new StringWriter();
        try {
            Template template = cfg.getTemplate(TEMPLATE_DIR + File.separator + fileName);
            template.process(data, stream);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return stream.toString();
    }

    private PageGenerator() {
        cfg = new Configuration();
    }
}
