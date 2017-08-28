# jnitest
jni练习，熟悉（java传对象给native，native传对象给java）
const char * 转换为 char *
指向const的指针不能被赋给指向非const的指针,所以应该用strcpy，也就是另开一块内存，把字符一个个复制过去
const char *expr = "goodidea";
char *buf = new char[strlen(expr)+1];
strcpy(buf, expr);

根据.java 文件生成.hpp
在src/main/java下 运用javah命令
javah -jni  全类名

如果遇到.cpp 无法加入cradle sync now失效
在cmalelists.txt里，随表敲个空格，然后再sync now