# Git原理学习总结

## 一、git中的objects

git有3种objects：blob、tree、commit

```
git cat-file -t object名字的前几位  # 查看object类型
git cat-file -s object名字的前几位  # 查看object内容的大小（字符数）
git cat-file -p object名字的前几位  # 查看object内容
```

## 二、git add时发生的变化

1. 在.git/objects目录下生成blob对象
2. 在.git目录下生成（或更新）index文件

### blob对象

blob的名字（key）：“blob 字符数 \0 文件内容”的SHA1
blob的值（value）：“blob 字符数 \0 文件内容”被Git压缩后得到的二进制字符串（直接查看是乱码）

一般通过 `git cat-file -p` 查看object的值

**blob不记录文件名信息。** 当文件名不同但文件内容相同时，git不会生成新的blob对象，只会在index文件中增加新的文件名与原有的blob名字。

### index文件

index文件中写有已添加到暂存区和版本库的文件名、对应blob名字等信息（直接打开是乱码）

```
git ls-files -s  # 查看暂存区和版本库中的文件名和对应blob的名字
```

### git status的几种状态

- changes to be committed：已添加到暂存区，等待被commit
- untracked：工作区中新建的文件，从未被添加到暂存区
- modified：文件已添加到暂存区或版本库，之后工作区中的文件被修改，与暂存区或版本库中的同名文件内容不同
- deleted：文件已添加到暂存区或版本库，之后工作区中的文件被删除

当工作区中的文件为modified状态时，使用 `git add` 命令，会生成新的blob对象，旧的blob对象还是存在的，但更新了index文件，在index文件中，该文件名将与新的blob名字对应。

## 三、git commit时发生的变化

1. 在.git/objects目录下生成1个commit对象
2. 在.git/objects目录下生成1个或多个tree对象（如果有文件夹的话会生成多个tree对象）

### tree对象

tree对象中包含：1个或多个blob名字、对应的文件名/1个或多个tree名字、对应的文件夹名等信息

若 `git add` 某个文件夹下的文件，会在index文件中写入目录信息。通过 `git ls-files -s` 能看到blob名字和对应的“文件夹名/文件名”。

### commit对象

**1个commit就是1个版本**

commit对象中包含：parent commit名字、1个tree名字、作者、提交说明等信息

## 四、版本切换

版本切换就是将工作区还原到某个版本（commit）

```
git reset --hard commit名字/HEAD~n  # n代表回退几个版本
```

## 五、分支

### HEAD指针

.git/HEAD文件的值：指向当前分支，如refs/heads/master

### 某个分支

这里以master分支为例
.git/refs/heads/master文件的值：master分支的最新一次commit名字

### 分支操作

```
git branch  # 查看分支列表
git branch dev  # 创建dev分支
git checkout dev  # 切换到dev分支
```

```
git branch -d dev  # 删除dev分支（若没有merge，则不能删除）
git branch -D dev  # 强制删除dev分支
# 这2种操作只会删除dev指针，objects还是存在的
```

## 六、一些常用命令

查看commit记录

```
git log  # 若回退到较早的版本，git log是无法看到之后的版本的
git reflog  # 显示所有记录
```

删除暂存区中的文件（3种方法）

```
git rm --cached 文件名
git reset HEAD 文件名
git restore --staged 文件名
```

删除版本库中的文件（2种方法）
1. 先删除工作区中的文件，再用 `git add 删除文件名` ，然后 `git commit`
2. `git rm 文件名` （会自动删除工作区中的文件），然后 `git commit`

误删工作区的文件时，从暂存区或版本库恢复文件到工作区（2种方法）

```
git checkout 文件名
git restore 文件名
```
