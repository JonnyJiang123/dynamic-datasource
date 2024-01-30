package org.mj.datasource;

import org.mj.manager.DynamicDatasourceManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 * author: jun.jiang
 * Date: 2024/1/30
 * Time: 13:49
 * Description:
 */
public class DynamicDatasource extends AbstractRoutingDataSource implements DataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDatasourceManager.currentDatasource();
    }
}
