package io.github.tanghuibo.classcanner.core.util.testdata;

/**
 * @author tanghuibo
 * @date 19-11-5上午12:13
 */
@TestTypeAnnotation("testClass")
public interface TestAnnotationClass {

    @TestMethodAnnotation("testMethod")
    void test( String testArg0,@TestParamAnnotation("arg1") String testArg1,
                     String testArg2,  @TestParamAnnotation("arg3") String testArg3, String testArg4 );
}
