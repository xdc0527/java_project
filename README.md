# Git项目设计文档

## Hash类

- 描述
  实现计算SHA1哈希值的功能

- 数据域

- 构造方法

- 方法

```
public static String getSHA1(byte[] bytes)
功能：对Value（字节数组）计算SHA1值
参数：Value（字节数组）
返回值：Key（16进制的SHA1字符串）

public static String byteArrayToHexString(byte[] bytes)
功能：将字节数组转换为16进制的字符串
参数：字节数组
返回值：16进制的字符串
```

## KeyValue类

- 描述
  
  KeyValue是Blob,Tree,Commit的父类,提供了一些三者都需要的公共功能

- 数据域

```
public static File path
根目录的路径

public static File gitFolder
Git版本库所在目录，相当于.git文件夹

public String key
Git对象（Blob、Tree、Commit）的Key，即根据Value计算得到的SHA1值

public byte[] value
Git对象（Blob、Tree、Commit）的Value，本项目没有使用字符串作为Value的类型，而是采用了字节数组作为Value的类型

public File KVfile
Git对象（Blob、Tree、Commit）在gitFolder中的文件路径
```

- 构造方法

- 方法

```
public static void setPath(String pathString)
功能：设置工作区路径
参数：字符串类型的目录路径
返回值：无

public static boolean checkIfKeyExists(String key)
功能：判断gitFolder中是否存在文件名为Key的文件
参数：Key（SHA1字符串）
返回值：True/False

private static File getFileInGitFolder(String key)
功能：在gitFolder文件夹中取得以Key为文件名的文件
参数：Key（SHA1字符串）
返回值：Key对应的File类型文件

public static byte[] getValue(String key)
功能：根据Key，取得对应的Value
参数：Key（SHA1字符串）
返回值：Value（字节数组）

public String genKey()
功能：调用Hash类的getSHA1方法，根据Value生成Key
参数：无
返回值：Key（SHA1字符串）

public void setKVfile()
功能：如果gitFolder中以Key为文件名的文件不存在，则根据Key和Value生成对应的文件
参数：无
返回值：无

public void setKVfileHard()
功能：如果gitFolder中以Key为文件名的文件存在，则删除原文件，再根据Key和Value生成新文件
参数：无
返回值：无

public boolean checkIfKeyExists()
功能：判断gitFolder中以Key为文件名的文件是否存在
参数：无
返回值：True/False
```

## Blob类

- 描述
  继承了KeyValue类，实现了Blob的Key，Value存储

- 数据域

- 构造方法

```
public Blob(File file)
功能：读取文件并在gitFolder中生成对应的Blob文件（Key为"B"+根据Value生成的SHA1，Value为读取文件的内容）
参数：File类型的文件
```

- 方法

```
public byte[] genValue(File file)
功能：读取文件并生成Blob的Value
参数：File类型的文件
返回值：Blob的Value（字节数组），内容与读取文件的内容一致
```

## Tree类

- 描述
  继承了KeyValue类，实现了Tree的Key，Value存储

- 数据域

- 构造方法

```
public Tree(File dir)
功能：根据文件夹路径，在gitFolder中生成对应的Tree文件（Key为"T"+根据Value生成的SHA1，Value包括子文件的Key、子文件的文件名、子文件夹的Key、子文件夹的Value）
参数：File类型的文件夹
```

- 方法

```
public byte[] genValue(File dir)
功能：根据文件夹路径，生成Tree的Value
参数：File类型的文件夹
返回值：Tree的Value（字节数组），其中包括子文件的Key、子文件的文件名、子文件夹的Key、子文件夹的Value
```

## Commit类

- 描述
  继承了KeyValue类，实现了Commit的Key，Value存储

- 数据域

```
public static File config = new File(gitFolder,"config")
config为配置文件，包含作者信息
```

- 构造方法

```
public Commit(String message)
功能：更新HEAD指针指向最新这次Commit，并在gitFolder中生成Commit文件（Key为"C"+根据Value生成的SHA1，Value包括Commit对应Tree的Key、前一次Commit的key、提交信息、时间、作者）
参数：字符串类型的提交信息
```

- 方法

```
public byte[] genValue(String message)
功能：生成Commit的Value
参数：字符串类型的提交信息
返回值：Commit的Value（字节数组），其中包括Commit对应Tree的Key、前一次Commit的key、提交信息、时间、作者
```

## Branch类

- 描述
  继承了KeyValue类，实现了分支的Key，Value存储

- 数据域

- 构造方法

```
public Branch(String branchName, String CommitKey)
功能：在gitFolder中生成Branch文件（Key为"R"+分支名称，Value为最新Commit的Key）
参数：字符串类型的分支名称、字符串类型的Commit的Key
```

- 方法

```
public static String getCommitKey(String branchName)
功能：取得某分支的最新Commit的Key
参数：字符串类型的分支名称
返回值：该分支对应的最新Commit的Key
```

## Head类

- 描述
  继承了KeyValue类，实现了HEAD指针的Key，Value存储以及分支切换和Commit切换

- 数据域

```
public final String key = "HEAD"
Head类的Key为"HEAD"
```

- 构造方法

```
public Head(String StringValue)
功能：在gitFolder中生成HEAD文件（Key为HEAD，Value为分支名称）
参数：字符串类型的分支名称
```

- 方法

```
public static String getCommitKey()
功能：获取HEAD指针对应Commit的Key或对应分支中存储的Commit的Key
参数：无
返回值：字符串类型的Commit的Key

public static void checkoutBranch(String branchKey)
功能：切换分支
参数：字符串类型的分支名称
返回值：无

public static void checkoutCommit(String commitKey)
功能：切换Commit版本
参数：字符串类型的Commit的Key
返回值：无

public static void update(String newCommitKey)
功能：更新HEAD指针对应Commit的Key或对应分支中存储的Commit的Key
参数：字符串类型的新的Commit的Key
返回值：无
```

## Command类

- 描述
  实现命令行相关操作

- 数据域

```
public static File path
根目录的路径
```

- 构造方法

- 方法

```
public static void setPath(File path)
功能：设置命令行中根目录的路径
参数：根目录路径
返回值：无

public void init()
功能：让用户在命令行中输入根目录路径和作者信息
参数：无
返回值：无

public static void checkout(String branchOrCommit)
功能：用户切换分支或Commit，若不存在对应分支或Commit，则打印提示信息
参数：字符串类型的分支名称或Commit的Key
返回值：无
```

