package io.github.tanghuibo.classcanner.core.util.testdata;

import java.lang.annotation.*;

/**
 * @author tanghuibo
 * @date 19-11-5上午12:09
 */
@Target({ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface TestMethodAnnotation {
    String value();
}
