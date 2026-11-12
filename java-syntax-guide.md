### `public static int[] twoSum(int[] nums, int target)`
- `static`：静态方法，可以通过类名调用，不必先创建对象
- `int[]`：返回类型，表示方法返回一个整数数组
- `int[] nums`：参数 nums 是整数数组

---

### `Map<Integer, Integer> indexMap = new HashMap<>();` 声明并创建一个哈希表
- `Map<K, V>` 表示键值对集合，K是键的类型，V是值的类型
- `Integer, Integer` 表示这个表里，键和值都是整数
- 这里用数字作为键、数字的下标作为值
- `new HashMap<>()` 创建实际的哈希表
- `<>`是菱形语法，Java会根据左边的 `Map<Integer, Integer>` 推断类型

---

### `HashMap` 的内置函数：
- `containsKey(complement)`：检查哈希表中是否已经有键complement
- `indexMap.get(complement)`：取出补数对应的下标
- `new int[]{...}`：创建一个新的整数数组，并用花括号里的值初始化
- return立刻结束方法并**返回数组**，返回的两个下标分别是：之前找到的补数下标，以及当前数字的下标
- `indexMap.put(nums[i], i)`作用：如果还没找到配对，就把当前数字和它的下标放进哈希表

### `List<>`
`List`是Java中表示**有序集合**的接口，它可以按顺序保存多个元素，并允许通过下标访问，常用实现是`ArrayList`
````
    List<Integer> result = new ArrayList<>();
    result.add(5);
    result.add(6);
    System.out.println(result.get(0)); // 5
````
![alt List和数组的区别](List和数组的区别.png)

### `toString()` 函数
当函数返回的是`List<String>`时，`System.out.println`会调用它的`toString()`方法，所以**输出不用写 for 循环**

当然也可以写，如下：
````
    List<String> result = buildArray(target, n);
    for(int i = 0; i < result.size(); i++){
        System.out.println(result.get(i));
    }
````

### 【自增自减】在用数组当栈的用法中的注意事项
- `data[--top] = value;`   //先自减,再使用
- `data[++top] = value;`   //先自增,再使用
- `data[top--] = value;`   //先使用,再自减
- `data[top++] = value;`   //先使用,再自增

### 总结【加入操作】方法
- `list.add()`
- `stack.push()`
- `arr[index++]`

### 总结【查看操作】方法
- `list.get()`
- `stack.peek()`  查看**栈顶**元素
- `arr[index]`

### 关于得到一个【数据结构的大小】方法
- `list.size()`
- `stack.size()`
- `arr.length`

### 普通for循环和增加for循环
#### 普通for循环
````
    for(int i = 0; i < students.length; i++){
        int student = students[i];
        System.out.println(student);
    }
````
#### 增强for循环
`for(int student : students)`是Java的增强for循环，也叫*foreach*，它会依次取出students数组里的每个值，并把当前值放进变量student
````
    int[] students = {1, 1, 0, 0};
    for(int student : students){
        System.out.println(student);
    }
````

---

### `import java.util.*;`

````
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayDeque;
import java.util.Deque;
````

这四行分别导入了两组集合类型：

- `ArrayList`和`List`：用于顶点的邻接表，保存它相连的顶点
- `ArrayDeque`和`Deque`：用于DFS的栈，暂存待访问的顶点

虽然变量名叫stack，但“栈”描述的是后进先出的使用方式，不一定要用名为Stack的类。`Deque`是**双端队列**接口，也能用`push`、`pop`来实现栈操作

---

### Java-接口和实现类
#### 接口规定“能做什么”，实现类负责“具体怎么做”
#### 接口理解为一份“操作规范”，把实现类理解为“按这份规范做出来的具体工具”
````
import java.util.ArrayList;
import java.util.List;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Deque;

import java.util.HashMap;
import java.util.Map;

````

![alt 接口和实现类](interface.png)

#### 【须知】
- `List` 规定了列表应该支持添加、读取、删除元素等操作
- `ArrayList` 则用动态数组来实现这些操作，接口本身不能直接创建对象
- 要创建具体对象，需要用实现类

#### 【示例】
````
List<String> names = new List<>(); // 错误：List是接口，接口本身不可以创建对象
````
````
List<String> names = new ArrayList<>(); // 正确：要创建具体对象，需要用实现类
names.add("小明");
System.out.println(names.get(0));
````
- 这里变量类型是`List`，实际创建的对象是`ArrayList`
- 所以names可以调用`List`接口规定的方法
- 而这些方法具体由`ArrayList`来执行

#### 【注意】：变量声明为接口类型后，只能直接调用该接口提供的方法，不能直接调用实现类独有的方法！