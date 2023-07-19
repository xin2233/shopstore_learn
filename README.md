# shopstore_learn
for学习，电脑商城

## maven脚本使用方法：
```bash
No goals have been specified for this build. You must specify a valid lifecycle phase or a goal in the format <plugin-prefix>:<goal> or <plugin-group-id>:<plugin-artifact-id>[:<plugin-version>]:<goal>. Available lifecycle phases are: validate, initialize, generate-sources, process-sources, generate-resources, process-resources, compile, process-classes, generate-test-sources, process-test-sources, generate-test-resources, process-test-resources, test-compile, process-test-classes, test, prepare-package, package, pre-integration-test, integration-test, post-integration-test, verify, install, deploy, pre-clean, clean, post-clean, pre-site, site, post-site, site-deploy. -> [Help 1]

win环境需要编辑mvnw.cmd脚本，配置 JAVA_HOME=\path\to\java\,例如：set JAVA_HOME=D:\work\java

./mvnw.cmd clean
./mvnw.cmd install
./mvnw.cmd compile
./mvnw.cmd package
```

## win环境下命令行启动spring boot
mvn 不行的话，就改成mvnw.cmd
```bash
windows操作系统下命令行的启动方式

方式一：

1：cd+path进入的spring boot项目的根目录下

2：执行命令mvn spring-boot:run

方式二：

1：先编译项目执行命令 mvn install

2：编译完了之后进入到target目录下 cd target

找到jar文件
3：用java命令直接启动 java -jar girl-0.0.1-SNAPSHOT.jar

```

## win 命令行运行spring test
```
mvnw.cmd  test -Dtest=com.cy.store.mapper.UserMapperTests#print
mvnw.cmd test -Dtest=类名#方法名
# 类名可以带上包的路径名，也可以直接就写类的名字（只要类的名字唯一）
```