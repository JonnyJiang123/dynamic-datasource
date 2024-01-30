package org.mj.manager;

import org.springframework.core.NamedInheritableThreadLocal;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * author: jun.jiang
 * Date: 2024/1/30
 * Time: 13:51
 * Description:
 */
public class DynamicDatasourceManager {
    /**
     * 使用栈来记录当前数据源，防止中途执行其他数据源的方法的时候切走了
     */
    private static final ThreadLocal<List<String>> CURRENT_DATASOURCE = new NamedInheritableThreadLocal<>("currentDataSource"){
        @Override
        protected List<String> initialValue() {
            return new LinkedList<>();
        }
    };
    public static void setCurrentDatasource(String datasource){
        CURRENT_DATASOURCE.get().add(datasource);
    }
    public static void removeCurrentDatasource(){
        if (CURRENT_DATASOURCE.get() != null && !CURRENT_DATASOURCE.get().isEmpty()){
            CURRENT_DATASOURCE.get().remove(CURRENT_DATASOURCE.get().size()-1);
        }
    }

    public static String currentDatasource(){
        if (CURRENT_DATASOURCE.get() == null || CURRENT_DATASOURCE.get().isEmpty()){
            return null;
        }
        return CURRENT_DATASOURCE.get().get(CURRENT_DATASOURCE.get().size()-1);
    }

}
