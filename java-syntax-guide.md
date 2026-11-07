### public static int[] twoSum(int[] nums, int target)
- static：静态方法，可以通过类名调用，不必先创建对象
- int[]：返回类型，表示方法返回一个整数数组
- int[] nums：参数 nums 是整数数组

---

### Map<Integer, Integer> indexMap = new HashMap<>(); 声明并创建一个哈希表
- Map<K, V> 表示键值对集合，K是键的类型，V是值的类型
- Integer, Integer 表示这个表里，键和值都是整数
- 这里用数字作为键、数字的下标作为值
- new HashMap<>() 创建实际的哈希表
- <> 是菱形语法，Java会根据左边的 Map<Integer, Integer> 推断类型

---

### HashMap 的内置函数：
- containsKey(complement)：检查哈希表中是否已经有键complement
- indexMap.get(complement)：取出补数对应的下标
- new int[]{...}：创建一个新的整数数组，并用花括号里的值初始化
- return立刻结束方法并**返回数组**，返回的两个下标分别是：之前找到的补数下标，以及当前数字的下标
- indexMap.put(nums[i], i)作用：如果还没找到配对，就把当前数字和它的下标放进哈希表