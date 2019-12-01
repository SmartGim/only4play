package com.only4play.prome.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.only4play.prome.model.AlertRule;
import com.only4play.prome.model.Groups;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

public enum YamlUtils {
    /**
     *
     */
    INSTANCE;

    public static final Yaml yaml;

    static {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setCanonical(false);
        options.setExplicitStart(false);
        Representer representer = new Representer();
        representer.addClassTag(AlertRule.class, Tag.MAP);
        yaml = new Yaml(representer, options);
    }

    public void write(String groupName, String alertName, String expr, String time,
        Map<String, String> labels, Map<String, String> annotations, String filePath) {
        List<Groups> groupsList = Lists.newArrayList();
        Groups gp = new Groups();
        gp.setName("orderGroup");
        List<Map<String, Object>> rules = Lists.newArrayList();
        Map<String, Object> rule = Maps.newLinkedHashMap();
        labels.put("severity","error");
        rule.put("alert", alertName);
        rule.put("expr", expr);
        rule.put("for", time);
        rule.put("labels", labels);
        annotations.put("name",alertName);
        rule.put("annotations", annotations);
        rules.add(rule);
        gp.setRules(rules);
        groupsList.add(gp);
        AlertRule ar = new AlertRule();
        ar.setGroups(groupsList);
        FileWriter writer = null;
        String fileName = groupName + "_" + alertName + ".yml";
        try {
            writer = new FileWriter(new File(filePath + File.separator + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        yaml.dump(ar, writer);
    }
}