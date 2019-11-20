package com.shura.test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Generator {
    public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        File file = new File("F:\\SSM\\ssm-template\\src\\main\\resources\\generator.xml");
        List<String> warnings = new ArrayList<String>();
        ConfigurationParser cp
                = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(file);
        DefaultShellCallback callBack = new DefaultShellCallback(true);
        MyBatisGenerator generator = new MyBatisGenerator(config, callBack, warnings);
        generator.generate(null);
    }
}
