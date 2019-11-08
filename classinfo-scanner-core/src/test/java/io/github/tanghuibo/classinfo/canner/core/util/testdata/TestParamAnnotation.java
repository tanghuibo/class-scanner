package io.github.tanghuibo.classinfo.canner.core.util.testdata;

import java.lang.annotation.*;

/**
 * @author tanghuibo
 * @date 19-11-5上午12:09
 */
@Target({ElementType.PARAMETER})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface TestParamAnnotation {
    String value();
}
