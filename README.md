# 实现一个简单的版本控制工具

---

## 任务1 实现 key-value 存储

- 最简单的 key-value 存储方式（filename ---> content of file）
  - key 作为文件名，文件内容作为 value
  - 给定 value: "hello world"
  - hash("hello world") == 34234234
  - 创建文件 objects/34234234 ---> hello world
  - 给定 34234234，要找到 value 的值

- 支持以下功能
  - 给定 value，向存储中添加对应的 key-value
  - 给定 key，查找得到对应的 value 值

- 封装成class对外提供接口

- 单元测试

## 任务2 将一个文件夹转化成 key, value

- 给定一个文件夹目录，将其转化成若干 tree 和 blob
  - 深度优先遍历此目录
    - 遇到子文件就转化成 blob 并保存
    - 遇到子文件夹就递归调用其内部的子文件/文件夹最后构造 tree 并保存

- 使用任务1提供的接口 --- hash表

- 单元测试

## 开发方式

- 永远使用 Pull Request 来更新主分支
- commit 描述和 PR 描述尽可能详细
- 使用 issue 来讨论/记录开发计划、分工以及问题/ bug
- 每个 issue 和 PR 都 @ 助教
