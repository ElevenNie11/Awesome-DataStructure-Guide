# 哈希映射-HashMap
### 以键值对(key-value)的形式存储数据，并通过键瞬间检索到对应的值（输入键，立刻得到值）
### 将键传入一个哈希函数Hash()（也叫哈希方法）：哈希函数的内部通过特定的数学公式将键计算为一串数字，这串数字指向内存中一个固定的槽位

---

- Dictionary = generic way to map keys to values
- Hash Table = implementation of a dictionary using a hash function

````java
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

Map<String, Integer> scores = new HashMap<>();
scores.put("小林", 95);
scores.put("小周", 88);

int score = scores.get("小林");      // 查找
boolean hasKey = scores.containsKey("小周");

Set<String> names = new HashSet<>();
names.add("小林");
names.add("小林");                    // 重复元素不会被重复保存
boolean exists = names.contains("小林");
````