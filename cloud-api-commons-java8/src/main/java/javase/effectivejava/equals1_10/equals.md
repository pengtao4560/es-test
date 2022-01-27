1-10 重写 equals 方法时遵守通用约定

当你重写 equals 方法时，必须遵守它的通用约定。Object 的规范如下：equals 方法实现了一个等价关系
（equivalence relation）。它有以下这些属性:

    • 自反性：对于任何非空引用 x，x.equals(x) 必须返回 true。
    • 对称性：对于任何非空引用 x 和 y，如果且仅当 y.equals(x) 返回 true 时 x.equals(y) 必须返回
    true。
    • 传递性：对于任何非空引用 x、y、z，如果 x.equals(y) 返回 true，y.equals(z) 返回 true，则
    x.equals(z) 必须返回 true。
    • 一致性：对于任何非空引用 x 和 y，如果在 equals 比较中使用的信息没有修改，则 x.equals(y) 的多
    次调用必须始终返回 true 或始终返回 false。
    • 对于任何非空引用 x，x.equals(null) 必须返回 false。
