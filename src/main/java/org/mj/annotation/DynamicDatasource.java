package org.mj.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 * author: jun.jiang
 * Date: 2024/1/30
 * Time: 13:53
 * Description:
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DynamicDatasource {
    /**
     * @return 数据源的key
     */
    String value();
}
