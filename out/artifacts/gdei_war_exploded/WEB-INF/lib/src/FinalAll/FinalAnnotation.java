package FinalAll;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 常量注解类
 */
@Target(ElementType.FIELD)//域(属性)声明
@Retention(RetentionPolicy.RUNTIME)
public @interface FinalAnnotation {
    String value();//常量的作用
}
