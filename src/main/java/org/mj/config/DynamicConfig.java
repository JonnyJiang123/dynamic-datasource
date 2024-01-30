package org.mj.config;

import org.mj.datasource.DynamicDatasource;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * author: jun.jiang
 * Date: 2024/1/30
 * Time: 16:10
 * Description:
 */
//@Configuration
public class DynamicConfig {
    @Bean
    public DataSource dataSource() {
        Map<Object,Object> targetDatasource = new HashMap<>();
        targetDatasource.put("datasource1", "datasource");
        DynamicDatasource dynamicDatasource = new DynamicDatasource();
        dynamicDatasource.setTargetDataSources(targetDatasource);
        dynamicDatasource.setDefaultTargetDataSource(targetDatasource.get("default"));
        return dynamicDatasource;
    }
}
