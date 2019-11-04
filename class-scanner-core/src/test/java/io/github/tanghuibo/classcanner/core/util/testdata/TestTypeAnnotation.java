package io.github.tanghuibo.classcanner.core.util.testdata;

import java.lang.annotation.*;

/**
 * @author tanghuibo
 * @date 19-11-5上午12:09
 */
@Target({ElementType.TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface TestTypeAnnotation {
    String value();
}
