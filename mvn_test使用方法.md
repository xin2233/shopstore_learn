## 1、需求

- 1、执行自动化测试用例的时候，只想指定某个测试类，或者某个方法，又或者某一类用例等，怎么办？
- 2、想要和Jenkins一起进行持续集成，可是用例又不可能在IDE里面执行，怎么办？
  这个时候就需要maven登场了，利用maven的maven-surefire-plugin插件可以帮助我们完成上述的目标！它可以通过命令行的形式来管理我们要执行的用例。

maven-surefire-plugin:

```
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>2.22.0</version>
        </plugin>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.8.1</version>
            <configuration>
                <source>8</source>
                <target>8</target>
            </configuration>
        </plugin>
    </plugins>
</build> 
```

指定编译jdk版本：

```
<properties>
    <aspectj.version>1.8.10</aspectj.version>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
</properties> 
```

## 2、指定测试类

现有如下结构的测试类，在此基础上进行演示

### 2.1 mvn test

使用mvn test命令可以一次性执行所有的用例，执行完之后可以看到控制台中的结果：

执行完之后我们打开target，所有maven执行完的结果都会在target目录下的surefire-reports下：

其中：

xml：可用于Jenkins解析，文件与测试类对应，里面的内容代表了测试用例的执行情况。
txt：测试结果的命令行输出

### 2.2 mvn -Dtest=${TestClass} test

使用mvn -Dtest=${TestClass}命令可以指定运行测试类，例如

我这里要运行MavenDemo1Test下的用例,就可以使用mvn -Dtest=MavenDemo1Test test：

```
import org.junit.jupiter.api.Test;

public class MavenDemo1Test {@Testvoid test1(){System.out.println("MavenDemo1Test-test1");}@Testvoid test2(){System.out.println("MavenDemo1Test-test2");}
} 
```

执行测试：

$ mvn -Dtest=MavenDemo1Test test 

测试结果：

### 2.3 mvn -Dtest=T e s t C l a s s 1 , {TestClass1},TestClass1,{TestClass2} test

我们还可以以逗号分隔指定多个测试类执行，例如这里我想执行MavenDemo1Test和MavenDemo2Test：

```
mvn -Dtest=MavenDemo1Test,MavenDemo2Test test 
```

测试结果：

### 2.4 mvn -Dtest=${TestCi*le} test

我们可以使用通配符来匹配要执行的测试类，例如这里我利用通配符同时指定测试类MavenDemo1Test和MavenDemo2Test：

```
$ mvn -Dtest=MavenDemo*Test test 
```

测试结果：

### 2.5 mvn -Dtest=KaTeX parse error: Undefined control sequence: \* at position 8: {TestCi\̲*̲le},{TestClass} test

当然，上述的测试方式组合起来也是可以的，例如这里利用通配符执行MavenDemo1Test和MavenDemo2Test，再精确指定执行MavenPackageDemoTest:

```
$ mvn -Dtest=MavenDemo*Test,MavenPackageDemoTest test 
```

测试结果：

## 3、指定测试方法

### 3.1 mvn -Dtest=TestCircle#mytest test

除了可以指定执行测试类，还可以指定测试方法，测试类和测试方法之间用#隔开即可例如这个我想要指定执行MavenDemo1Test中的test2方法：

```
$ mvn -Dtest=MavenDemo1Test#test2 test 
```

测试结果：

### 3.2 mvn -Dtest=TestCircle#test* test

方法依然可以使用通配符，例如这里要执行MavenPackageDemoTest中的方法MavenPackageTest,可以匹配以Test结尾：

```
$ mvn -Dtest=MavenPackageDemoTest#*Test test 
```

测试结果：

### 3.3 mvn -Dtest=TestCircle#testOne+testTwo test

mvn也可以指定某个测试类下面的多个方法，使用+号分隔方法，例如指定MavenDemo1Test下的方法test1()和test2().

```
$ mvn -Dtest=MavenDemo1Test#test1+test2 test 
```

## 4、失败重试

mvn还提供了失败重试，设置失败重试的次数，当用例执行时直到用例成功或者失败次数达到上限，命令如下：

```
mvn -Dsurefire.rerunFailingTestsCount=2 test 
```

可是遗憾的是，此功能只支持Junit4，官网给出的说明如下：

## 5、套件运行

在Junit5中，我们其实是可以利用@RunWith(JUnitPlatform.class)运行测试套件的，具体内容可参考文章：Junit5简介、构成、新特性及基本使用-常用注解、套件执行 然而当我们使用mvn来执行套件时： 套件：

```
import MavenTestPackage1.MavenPackageDemoTest;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SuiteDisplayName("测试套件")
@SelectClasses({MavenPackageDemoTest.class,MavenDemo1Test.class
}
)
public class TestSuite {

} 
```

mvn命令：

```
mvn -Dtest=TestSuite test 
```

会出现如下报错：

据说是因为surefire插件的版本问题，当我切换到2.19版本的时候，套件执行成功了，可是mvn test命令又失败了，这显然不是我们想要的结果，于是乎开始了Google之路。。。 最终找到了一个相对合理的解释！抱歉，又要失望了，Google到别人的解释如下(已翻译完成~)：

简单的说就是surefire插件暂时还不支持在Junit5下执行继承至Junit4的@RunWith,所以还是需要有心人出来解决这个问题才行了~~

## 6、Pom中指定默认执行

除了使用命令行，我们还可以在pom中指定我们默认需要执行的类或者不需要执行的类

例如这里我默认只想执行MavenPackageDemoTest，而另外两个MavenDemo开头的测试类我都不想执行，这个时候可以在pom中做如下配置，在surefire插件下添加configuration：

```
<plugin> <groupId>org.apache.maven.plugins</groupId> <artifactId>maven-surefire-plugin</artifactId> <version>3.0.0-M3</version> <configuration> <includes> <include>MavenPackageDemoTest.java</include> </includes> <excludes> <exclude>MavenDemo*.java</exclude> </excludes> </configuration>
</plugin> 
```

执行mvn test ，查看结果：

从结果中可以看到仅仅执行了我们默认指定的MavenPackageDemoTest中的case，其余以MavenDemo开头的测试类均未被执行。

## 7、写在最后

这里介绍了maven-surefire-plugin插件在测试执行中的一些常见用法，还有很多其他的用法，例如：

使用很复杂的格式匹配

控制在不同级别上的多线程和多进程执行

后续将慢慢研究补充，有兴趣也可直接参考官网学习：

maven-surefire-plugin官网参考地址：maven.apache.org/surefire/ma…
