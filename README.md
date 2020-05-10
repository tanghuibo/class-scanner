# class-scanner
通过java动态编译获取类信息，不依赖反射（所以不需要下载第三jar包即可解析）。

## classinfo-scanner-core

读取`class`信息（通过javassist）

### 特点
- 代码需要先编译（但编译后不要第三方jar包）。
- 对注解更友好。
- 接口拿不到具体参数名称。

## javadoc-scanner-core

读取`java`文件信息（通过com.sun.tools.javadoc.Main），

### 特点
- 代码不需要编译。
- 能获取到注释信息
- 能获取到接口参数名
- 对注解不友好