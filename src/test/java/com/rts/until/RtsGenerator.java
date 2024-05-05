package com.rts.until;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class RtsGenerator {
    public static void main(String[] args) {
        String table = "t_user";
        // 创建生成器对象
        AutoGenerator ag = new AutoGenerator();
        // 获取项目路径
        String base_path = System.getProperty("user.dir");
        // 配置全局配置
        GlobalConfig gc = new GlobalConfig();
        // 输出路径
        gc.setOutputDir(base_path + "/src/main/java");
        gc.setAuthor("rts");
        gc.setOpen(false);
        // 去掉service接口名的I
        gc.setServiceName("%sService");
        ag.setGlobalConfig(gc);
        //  数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        // 判断数据库的种类
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/xiyue?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false");
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/db2024?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false");
        dsc.setUsername("root");
        dsc.setPassword("root");

        dsc.setTypeConvert(new ITypeConvert() {
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                String t = fieldType.toLowerCase();
                if (t.contains("timestamp") || t.contains("datetime")) {
                    return DbColumnType.DATE;
                }
                if (t.contains("tinyint")) {
                    return DbColumnType.BYTE;
                }

                return new MySqlTypeConvert().processTypeConvert(globalConfig,fieldType);
            }
        });
        ag.setDataSource(dsc);
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.rts");
        // 配置xml的路径
        Map<String,String > pathInfo = new HashMap<>();
        pathInfo.put("entity_path", base_path + "/src/main/java/com/rts/entity");
        pathInfo.put("mapper_path", base_path + "/src/main/java/com/rts/mapper");
        pathInfo.put("xml_path", base_path + "/src/main/resources/com/rts/mapper");
        pathInfo.put("service_path", base_path + "/src/main/java/com/rts/service");
        pathInfo.put("service_impl_path", base_path + "/src/main/java/com/rts/service/impl");
        pathInfo.put("controller_path", base_path + "/src/main/java/com/rts/controller");
        pc.setPathInfo(pathInfo);
        ag.setPackageInfo(pc);
        // 策略配置
        StrategyConfig sc = new StrategyConfig();
        // 表名在生成entity类型的时候下划线转驼峰
        sc.setNaming(NamingStrategy.underline_to_camel);
        // 列名在生成属性名的时候 下划线转驼峰
        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        // 是否加lombok注解
        sc.setEntityLombokModel(true);
        // 是否在controller层加RestController注解
        sc.setRestControllerStyle(true);
        // 需要生成的表名
        sc.setInclude(table);
        ag.setStrategy(sc);
        // 配置模板引擎
        ag.setTemplateEngine(new FreemarkerTemplateEngine());
        // 执行代码生成
        ag.execute();
    }
}
