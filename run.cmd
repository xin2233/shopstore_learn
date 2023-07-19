rem for 命令行运行spring 项目
rem by zjx

@echo off
@REM 设置编码为UTF-8
chcp 65001

@REM 开始执行命令
:start

@REM 返回上级
rem cd..
@REM 获取上级文件夹软件目录
rem set P_HOME=%cd%


rem 设置java home 目录
set P_HOME=%D:\work\java%

rem 设置工程根目录
set CLASS_PASS=".\store\lib"
set JRE="%P_HOME%\jre\bin\java.exe"

@REM 回到当前目录下
cd %~dp0

@REM 设置启动类入口
set Main_ClASS=org.springframework.boot.loader.JarLauncher

@REM 获取启动类的所有参数
set JAVA_ARGS=%*

@REM 打印jre环境位置
echo JRE = "%JRE%"

@REM 打印启动类入口
echo Main_ClASS = "%Main_ClASS%"

@REM 打印传入参数
echo JAVA_ARGS = "%JAVA_ARGS%"

@REM 执行命令

%JRE% %Main_ClASS% %JAVA_ARGS%

pause
