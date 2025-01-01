# Introduction  
```
scala.collection.immutable 包中的集合对所有人都是不可变的。这样的集合在创建过后就不会改变。

scala.collection.mutable 包中的集合有一些操作可以当场修改集合。这些操作允许你自己来编写改变集合的代码。

而 scala.collection 包中的集合既可以是可变的也可以是不可变的。

sacla.collection.IndexedSeq[T］是 scala.collection.
immutbale.IndexedSeq[T］和 scala.collection.mutable.IndexedSeq[T] 的超类型 。一般而言，scala.collection 包中的根 （ root ）集合定义了跟不
可变集合相同的接口。而通常，scala.collection.mutable 包中的可变集合会在上述不可变接口基础上添加一些有副作用的修改操作 。
```

# Inheritance Relationship
```
Traversable
  Iterable
    Seq
      IndexedSeq
        Vector
        ResizableArray
        GenericArray
      Linear Seq
        MutableList
        List
        Stream
      Buffer
        ListBuffer
        ArrayBuffer
    Set
      SortedSet
        TreeSet
      HashSet （可变的）
      LinkedHashSet
      HashSet （不可变的）
      BitSet
      EmptySet, Setl, Set2, Set3, Set4
    Map
      SortedMap
        TreeMap
      HashMap （可变的）
      LinkedHashMap （可变的）
      HashMap （不可变的）
      EmptyMap, Mapl, Map2, Map3, Map4
```

# Traversable 特质
```
Traversable 特质唯一的抽象操作是 foreach:
def foreach[U](f: Elem => U)
实现 Traversable 的集合类只需要定义这个方法即可，其他方法都可以从 Traversable 继承 。
foreach 方法的本意是集合中的所有元素，并对每个元素应用给定的操作f。
该操作的类型为 Elem => U，其中 Elem 是集合元素的类型而 U 是任意的结果类型 。
对 f 的调用仅仅是为了副作用，事实上 foreach 会丢弃函数调用 f 的所有结果 。
```

## 添加
```
＋＋ 可以将两个 Traversable 加在一起，或将某个迭代器（iterator）的所有元素添加到 Traversable 。
```

## 映射操作
```
map 、 flatMap 和 collect ，通过对集合元素应用某个函数来产生一个新的集合 。

xs collect f : 通过对 XS 的每个元素应用偏函数 f 并将有定义的结果收集起来得到的集合 。
```

## 转换
```
tolndexedSeq 、tolterable 、toStream 、toArray 、toList 、toSeq 、
toSet 和 toMap ，将一个 Traversable 集合转换成更具体的集合。如果原集合
已经匹配了所需要的集合类型，所有这些转换就会直接返回原集合。
例如，对列表应用 toList 会交出列表本身 。
```

## 拷贝操作
```
xs copyToBuffer buf, 将集合的所有元素拷贝到缓冲 buf 中。
xs copyToArray(arr, s, len), 将 XS 中最多 len 个元素拷贝到 arr ，从下标 s 开始。
后两个人参是可选的 。
```

## 大小操作
```
isEmpty 、 nonEmpty 、 size 和 hasDefiniteSize 。 
能被遍历的集合可以是有限的也可以是无限的 。 比如，表示自然数的流 Strean. from(O）
就是一个无限可遍历集合 。 hasDefiniteSize 方法表示某个集合是否是无限的。如果
hasDefiniteSize 返回 true ，那么该集合当然是有限的。如果它返回 false ，那
么集合可能是无限的，这时 size 方法会报错或根本不返回。
```

## 元素获取操作
```
head 、 last 、 headOption 、 lastOption 和 find 这些操作会选中集合中
的首个或最后 一个元素，或者是首个满足给定条件的元素 。 不过注意，并非所
有集合都有定义得很清晰完整的“首个”和“最后一个”的含义 。 举例来说，
一个哈希集可能会根据元素的哈希键来存储元素，这个值可能会变 。这种情况
下，哈希集的“首个”元素可能也不同 。 如果某个集合总是以相同的顺序交出
元素，那么它就是有序的 （ordered）。 大多数集合都是有序的，不过有一些（比
如哈希集）并不是（放弃顺序能带来额外的一些性能优势）。 顺序通常对于可
重复执行的测试而言很重要，这也是为什么 Scala 集合提供了对所有集合类型
的有序版本。比如， HashSet 的有序版本是 ListedHashSet 。

XS find p, 以可选值表示的 XS 中满足 p 的首个元素，当 XS 为空时返回 None 。
```

## 子集合获取操作
```
takeWhile 、 tail 、 init 、 slice 、 take 、 drop 、 filter 、 dropWhile 、
filterNot 和 withFilter。这些操作都返回由满足某个下标区间或前提的子集合。

xs slice (from, to), 包含 XS 某个下标区间元素的集合（下标从 from 开始到 to，不包含to）。
xs takeWhile p, 集合中满足 p 的最长元素前缀 。
xs dropWhile p, 集合除满足 p 的最长元素前缀之外的部分 。
xs filter p, 包含 XS 中所有满足前提条件 p 的元素的集合 。
xs withFilter p, 对该集合的非严格过滤器。所有对结果过滤器的操作都只会应用于条件 p 为 true 的元素 。
xs filterNot p, 包含xs中所有不满足前提条件 p 的元素的集合 。
```

## 细分
```
splitAt、 span、 partition 和 groupBy。 将集合元素切分成若干子集合 。

XS splitAt n, 在指定位置切分 XS ，给出一对集合：（xs take n, xs drop n）。
xs span p, 根据前提切分 XS，给出一对集合：(xs takeWhile p, xs dropWhile p)
xs partition p, 将 XS 切分成一对集合，其中一个包含了满足前提条件 p 的元
素，另一个包含了不满足前提条件 p 的元素， 给出一对集合：(xs filter p, xs filterNot p ） 。
xs groupBy p, 根据区分函数 f 将 XS 分区成集合的映射 。
```

## 元素测试
```
exists、forall 和 count。用给定的前提对集合元素进行测试。
xs forall p, 表示是否 XS 所有元素都满足前提 p 的布尔值。
xs exists p, 表示是否 XS 中有元素满足前提 p 的布尔值。
xs count p, XS 中满足前提 p 的元素数量。
```

## 折叠
```
foldLef t、foldRight、/:、:\、reduceLeft 和 reduceRight 。
对连续的元素应用某个二元操作 。

(z /: xs)( op), 以 z 开始自左向右依次对 XS 中连续元素应用二元操作 op。
(xs :\ z)( op), 以 z 开始自右向左依次对 XS 中连续元素应用二元操作 op。
```

## 特殊折叠
```
sum,product、min和max，用于操作特定类型的集合（数值型或可比较类型）。
```

## 字符串操作
```
mkString、addString 和 stringPrefix。提供不同的方式将集合转换成字符串。

xs addString (b, start, sep, end), 将一个显示了 XS 所有元素的字符串添加到 StringBuilder
b 中，元素以 sep 分隔并包含在 start 和 end 当中。 start、sep 和 end均为可选 。
xs mkString (start, sep, end), 将集会转换成一个显示了 XS 所有元素的字符串，元素以 sep 分隔
并包含在 start 和 end 当中。start、sep 和 end 均为可选 。
xs.stringPrefix, 在 xs.toString 返回的字符串最开始的集合名称。
```

## 视图操作
```
xs.view, 产生一个对自的视图 。
xs view (from, to), 产生一个代表 XS 中某个下标区间的元素的视图 。
```

# lterable 特质
```
该特质的所有方法都是用抽象方法 iterator 来定义的，这个抽象方法的作用是逐个交出集合的元素 。
从 Traversable 继承下来的 foreach 方法在 Iterable 中的定义就用到了iterator。
实际的实现如下 ：

def foreach[U](f: Elem=> U): Unit= {
  val it = iterator
  while (it.hasNext) f(it.next())
}
```

## 迭代器
```
xs.iterator, 按照与 foreach 遍历元素的顺序交出 XS 中每个元素的迭代器 。
xs grouped size, 交出该集合固定大小“段”的迭代器。
xs sliding size, 交出固定大小滑动窗口的元素的迭代器。
```

## 子集合
```
xs takeRight n, 包含 XS 的后 n 个元素的集合（如果没有定义顺序那么就是任意的 n 个元素）。
xs dropRight n, 集合除 XS takeRight n 外的部分 。
```

## 拉链
```
xs zip ys, 由 xs 和 ys 对应元素的对偶组成的 Iterable 。
xs zipAll (ys, x, y), 由 xs 和 ys 对应元素的对偶组成的 Iterable，其中较短的序列用 x 或 y
的元素值延展成相同的长度 。
xs.zipWithlndex, 由 xs 中的元素及其下标的对偶组成的 Iterable。
```

## 比较
```
xs sameElements ys, 测试是否 xs 和 ys 包含相同顺序的相同元素。
```

# 序列型特质 Seq、IndexedSeq 和 LinearSeq
```
Seq 特质代表序列。序列是一种有 length（长度）且元素都有固定的从 0 开始的下标位置的迭代 。

Seq 特质有两个子特质， LinearSeq 和 IndexedSeq 。 这两个特质并没有
添加任何新的操作，不过它们各自拥有不同的性能特征。线性的序列拥有高效
的 head 和 tail 操作，而经过下标索引的序列拥有高效的 apply、length 和（如
果是可变的）update 操作。List 是一种常用的线性序列， Stream也是。 
而Array 和 ArrayBuffer 是两种常用的经过下标索引的序列。 Vector 类提供了
介于索引和线性访问之间的有趣的妥协。它既拥有从效果上讲常量时间的索引
开销，也拥有时间线性的访问开销。由于这个特点，向量（vector）是混用两
种访问模式（索引的和线性的）的一个好的基础 。
```

## 下标和长度操作
```
apply 、 isDefinedAt 、 length 、 indices 和 lengthCompare 。

xs(i),（或者展开写的 xs apply i ) xs 中下标为 i 的元素。
xs isDefinedAt i, 测试 i 是否包含在 xs.indices 中。
xs.lengthCompare ys, 如果 xs 比 ys 短返回 -1， 如果比 ys 长返回 +1，如果长度相同返回 0 。
对于其中一个序列的长度无限时仍有效 。
```

## 下标检索操作
```
indexOf、 lastlndexOf、 indexOfSlice、 lastlndexOfSlice、
indexWhere、 lastlndexWhere、 segmentLength 和 prefixLength。
返回与给定值相等或满足某个前提条件的元素的下标 。

xs indexOf x, xs 中首个等于 x 的 元素下标（允许多个存在）。
xs lastindexOf x, xs中最后一个等于 x 的元素下标（允许多个存在）。
xs indexOfSlice ys, xs 中首个满足自该元素起的连续元素能够构成 ys 序列的下标。
xs lastindexOfSlice ys, xs 中最后一个满足自该元素起的连续元素能够构成 ys 序列的下标 。
xs indexWhere p, xs 中首个满足 p 的元素下标（允许多个存在）。
xs segmentLength (p, i), xs 中自 xs(i）开始最长的连续满足 p 的片段的长度 。
xs prefixLength p, xs 中最长的连续满足 p 的前缀的长度 。
```

## 添加操作
```
＋:、:＋ 和 padTo ，返回通过在序列头部或尾部添加元素得到的新序列 。

x +: xs, 将 x 追加到 xs 头部得到的新序列 。
xs :+ x, 经 x 追加到 xs 尾部得到的新序列 。
xs padTo (len, x), 将 x 追加到 xs 直到长度达到 len 后得到的序列 。
```

## 更新操作
```
updated 和 patch。返回通过替换原始序列中某些元素后得到的新序列
如果序列是可变的，它会提供额外的带有副作用的 update 方法，允许序列元素被更新。
请注意 update 和 updated 的区别。update 方法当场修改某个序列元素的值，
且仅能用于可变序列。而updated方法对所有序列都可用，总是会返回新的序列，而不是修改原序列。

xs patch (i, ys, r), 将 xs 中从下标 i 开始的 r 个元素替换成 ys 得到的序列 。
xs updated (i, x), 下标 i 的元素被替换成 x 的对 xs 的拷贝 。
xs(i) = x,（或者展开写的 xs.update(i,x），但仅对 mutable.Seq 有效）将 xs
中下标 i 的元素更新为 x.
```

## 排序操作
```
sorted、sortWith 和 sortBy 。 根据不同的条件对序列元素进行排序。

xs.sorted, 用 xs 元素类型标准顺序对 xs 排序后得到的新序列。
xs sortWith lessThan, 以 lessThan 为比较操作对 xs 排序后得到的新序列。
xs sortBy f, 对 xs 元素排序后得到的新序列。两个元素间的比较通过间时应用 f 然
后比较其结果 。
```

## 反转操作
```
reverse、reverselterator 和 reverseMap。按倒序（从后往前）交出或处理序列元素。

xs.reverse, 跟 xs 顺序颠倒的序列 。
xs.reverseiterator, 以颠倒的顺序交出 xs 所有元素的迭代器 。
xs reverseMap f, 以颠倒的顺序对 xs 的元索映射 f 后得到的序列 。
```

## 比较操作
```
startsWith、endsWith、contains、corresponds 和 containsSlice 。
判断两个序列之间的关系或在序列中查找某个元素 。

xs startsWith ys, 测试 xs 是否以 ys 开始（允许多个存在）。
xs endsWith ys, 测试 xs 是否以 归 结尾（允许多个存在）。
xs contains x, 测试 xs 是否包含等于 x 的元素 。
xs containsSlice ys, 测试 xs 是否包含与 ys 相等的连续子序列 。
(xs corresponds ys)(p), 测试 xs 和 ys 对应元素是否满足二元前提条件 p 。
```

## 多重集操作
```
intersect、diff、union 和 distinct。对两个序列的元素执行集类操作或移除重复项 。

xs intersect ys, 序列 xs 和 ys 的交集，保持 xs 中的顺序 。
xs diff ys, 序列 xs 和 ys 的差集，保持 xs 中的顺序 。
xs union ys, 序列 xs 和 ys 的并集，等同于 xs ++ ys
xs.distinc, 不包含重复元素的 xs 的子序列 。
```

# 缓冲
```
可变序列的 一个重要子类目是缓冲。缓冲不仅允许对已有元素的更新，同
时还允许元素插入、移除和在缓冲末尾高效地添加新元素。缓冲支持的主要的
新方法有：用于为尾部添加元素的+=和++=，用于在头部添加元素的+=:和
++=:，用于插入元素的 insert 和 insertAll ，以及用于移除元素的 remove
和-=。
```

## 添加
```
buf += x, 将元素 x 追加到缓冲 buf 中，返回 buf 本身。
buf += (x, y, z), 将给定的元素追加到缓冲。
buf ++= xs, 将 xs 中的所有元素追加到缓冲 。
x +=: buf, 将元素 x 向前追加到缓冲头部 。
xs ++=: buf, 将 xs 中的所有元素向前追加到缓冲头部 。
buf insert (i，x), 对 将元素 x 插入到缓冲中下标 i 的位置 。
buf insertAll (i, xs), 将 xs 中的所有元素插入到缓冲中下标 i 的位置 。
```

## 移除
```
buf -= x, 移除缓冲中的 x。
buf remove i, 移除缓冲中下标为 i 的元素。
buf remove (i, n), 移除缓冲从下标 i 开始的 n 个元素。
buf trimStart n, 移除缓 冲 中前 n 个元素。
buf trimEnd n, 移除缓冲中后 n 个元素。
buf.clear(), 移除缓冲中的所有元素。
```

## 克隆
```
buf.clone, 跟 buf 拥有相同元素的新缓冲。
```

# 集
```
Set 是没有重复元素的 Iterable
```

## 测试
```
contains、apply 和 subsetOf。 contains 方法表示当前集是否包含某
个给定的元素。集的 apply 方法等同于 contains ，因此 set(elem）相当于
set contains elem。

xs contains x, 测试 x 是否为汩的元素 。
xs(x), 同 xs contains x。
xs subsetOf ys, 测试 xs 是否为 ys 的子集 。
```

## 添加
```
xs + x, 包含 xs 所有元素以及 x 的集 。
xs + (x, y, z), 包含泪所有元素以及给定的额外元素的集 。
xs ++ ys, 包含 xs 所有元素以及 ys 所有元素的集 。
```

## 移除
```
xs - x, 包含除 x 外 xs 所有元素的集 。
xs - (x, y, z), 包含除给定元素外 xs 所有元素的集 。
xs -- ys, 包含除 ys 元素外 xs 所有元素的集 。
xs.empty, 跟 xs 相同类的 空集 。
```

## 二元操作
```
xs & ys, xs 和 ys 的交集 。
xs intersect ys, 同 xs & ys 。
xs | ys, xs 和 ys 的 并集 。
xs union ys, 同 xs | ys 。
xs &- ys, xs 和 ys 的差集 。
xs diff ys, 同 xs &- ys 。
```

## mutable.Set特质包含的操作 
```
添加：
xs += x, 以副作用将 x 添加到 xs 并返回 xs 本身 。
xs += (x, y, z), 以副作用将给定元素添加到 xs 并返回 xs 本身 。
xs ++= ys 以副作用将 ys 所有元素添加到 xs 并返回 xs 本身 。
xs add x, 将元素 x 添加到 xs，如果 x 在此之前没有包含在集当中则返回 true ,
如果 x 在此之前已经包含在集当中则返回 false 。

移除：
xs -= x, 包含除 x 外 xs 所有元素的集 。
xs -= (x, y, z), 以副作用将给定元素从 xs 中移除并返回 xs 本身 。
xs --= ys, 以副作用将 ys 所有元素从 xs 中移除并返回 xs 本身 。
xs remove x, 将元素 x 从 xs 中移除，如果 x 在此之前包含在集当中则返回 true,
如果 x 在此之前没有包含在集当中则返回 false 。
xs retain p, 仅保留 xs 中那些满足前提条件 p 的元素 。
xs.clear(), 从 xs 中移除所有元素。

更新：
xs(x) = b,(或者展开写的 xs.update(x, b)), 如果布尔值人参 b 为 true ，将添加
x 到 xs ， 否则将 x 从 xs 中移除 。

克隆：
xs.clone, 与 xs 拥有相同元素的新的可变集 。
```

# 映射
```
映射的基本操作跟集的操作类似，可变映射提供额外的操作支持。映射操作归类如下
```

## 查找
```
apply 、 get 、 getOrElse 、 contains 和 isDefinedAt。这些操作将映射
转换成从键到值的偏函数。

映射基本的查找操作如下 ：
def get(key): Option[Value]
“m get key”这个操作首先测试该映射是否包含了指定键的关联， 如果是，
则以 Some 的形式返回关联的值，而如果在映射中并没有定义 key 这个键，则返回 None 。

ms get k, 以可选值表示的映射 ms 中跟键 k 关联的值，若无关联则返回 None。
ms(k),（或者展开写的 ms apply k ）映射 ms 中跟键 k 关联的值，若无关联则抛异常。
ms getOrElse (k, d), 映射 ms 中跟键 k 关联的值，若无关联则返回默认值 d 。
ms contains k, 测试 ms 是否包含键 k 的映射关系 。
ms isDefinedAt k, 同 contains
```

## 添加和更新
```
ms + (k -> v), 包含 ms 所有映射关系以及从键 k 到值 v 的映射关系的映射 。
ms + (k->v, l->w), 包含 ms 所有映射关系以及给定键值对表示的映射关系的映射 。
ms ++ kvs, 包含 ms 所有映射关系以及 kvs 表示的所有映射关系的映射。
ms updated (k, v） 同 ms + (k -> v ） 。
```

## 移除
```
ms - k, 包含 ms 除键 k 外所有映射关系的映射 。
ms - (k, l, m), 包含 ms 除给定键外所有映射关系的映射 。
ms -- ks, 包含 ms 除 ks 所有键外所有映射关系的映射 。
```

## 产生子集合
```
ms.keys, 包含 ms 中每个键的 Iterable 。
ms.keySet, 包含 ms 中每个键的集 。
ms.keysiterator, 交出 ms 中每个键的迭代器 。
ms.values, 包含 ms 中每个跟键有关联的值的 Iterable 。
ms.valuesiterator, 交出 ms 中每个跟键有关联的值的送代器 。
```

## 变换
```
ms filterKeys p, 只包含 ms 中那些键满足前提条件 p 的映射关系的映射视图 。
ms mapValues f, 通过对 ms 中每个跟键有关联的值应用函数 f 得到的映射视图 。
```

## mutable.Map特质包含的操作
```
添加和更新：
ms(k) = v,（或者展开写的 ms.update(k,v ））以副作用将键 k 到值 v 的映射关系
添加到映射 ms ， 覆盖之前的 k 映射关系。
ms += (k -> v), 以副作用将键 k 到值 v 的映射关系添加到映射 ms 并返回 ms 本身 。
ms += (k->v，l->w), 以副作用将给定的映射关系添加到映射 ms 并返回 ms 本身 。
ms ++＝ kvs, 以副作用将 kvs 中的映射关系添加到映射 ms 并返回 ms 本身 。
ms put (k, v), 将键 k 到值 v 的映射关系添加到 ms 并以可选值的形式返回之前与 k 关
联的值 。
ms getOrElseUpdate (k, d), 如果键 k 在映射 ms 中有定义，返回关联的值。
否则，用映射关系 k -> d 更新 ms 并返回 d 。

移除：
ms -= k, 以副作用从 ms 移除键 k 的映射关系并返回 ms 本身 。
ms -= (k, l, m), 以副作用从 ms 移除给定键的映射关系并返回 ms 本身 。
ms --= ks, 以副作用从 ms 移除 ks 中所有键的映射关系并返回 ms 本身 。
ms remove k, 从 ms 移除键 k 的映射关系并以可选值的形式返回键 k 之前的关联值 。
ms retain p, 仅保留 ms 中那些键满足前提条件 p 的映射关系 。
ms.clear(), 从 ms 移除所有映射关系 。

变换和克隆：
ms transform f, 用函数 f 变换 ms 中所有关联的值。
ms.clone, 返回跟 ms 包含相同映射关系的新的可变映射。
```

# 具体的不可变集合类 
```
Scala 提供了许多具体的不可变集合类供你选择。它们实现的特质各不相
同（映射、集 、序列），可以是无限的也可以是有限的，不同的操作有不同的性能表现 。
```

## 列表
```
列表是有限的不可变序列。它们提供常量时间的对首个元素和余下元素的
访问，以及常量时间的在列表头部添加新元素的操作。其他的许多操作都是线
性时间的 。
```

## 流
```
流跟列表很像，不过其元素是惰性计算的。正因如此，流可以是无限长的。
只有被请求到的元素会被计算。除此之外，流的性能特征跟列表是一样的。

列表通过：：操作符构造，而流则是通过看上去有些相似的＃：：来构造 。
scala> val str = 1 #:: 2 #:: 3 #:: Stream.empty
```

## 向量
```
当处理列表的算法值处理头部的时候，列表是非常高效的。访问、添加和
移除列表的头部都只需要常量的时间，而访问或修改更靠后的元素需要的时间
则跟元素出现在列表中的深度成正比。

向量是对头部之外的元素也提供高效访问的集合类型 。对向量的任何元素
的访问都消耗“从实效上讲的常量时间”稍后会有详细定义 。这个常量时间
比访问列表头部或从数组读取某个元素的常量时间要长，不过即使如此它也是
个常量。 这样一来，使用向量的算法不需要对只访问序列头部这一点格外小心 。
它们可以访问和修改任意位置的元素，因此编写起来要方便得多 。
```

## 不可变的栈
```
如果你需要一个后进先出的序列，可以使用 Stack 。 可以用 push 来压一
个元素人枝，用 pop 来弹一个元素出枝，以及用 top 来查看樵顶的元素而不将
它弹出钱。所有这些操作都是常量时间的。

scala> val stack = scala.collection.immutable.Stack.empty
scala> val hasOne = stack.push(1)
scala> hasOne.top
scala> hasOne.pop
```

## 不可变的队列
```
队列跟栈很像，只不过队列是先进先出而不是后进先出的。

scala> val empty= scala.collection.immutable.Queue[Int]()
scala> val hasl = empty.enqueue(l)
scala> val has123 = hasl.enqueue(List(2, 3))
scala> val (element, has23) = has123.dequeue
```

# 具体的可变集合类

## 数组缓冲
```
数组缓冲包括一个数组和一个大小 。
对数组缓冲的大部分操作都眼数组的速度一样，因为这些操作只是简单地访问
和修改底层的数组。数组缓冲可以在尾部高效地添加数据。对数组缓冲追加元
素需要的时间为平摊（amortized）的常量时间。因此，数组缓冲对于那些通过
往尾部追加新元素来高效构建大集合的场景而言非常有用。

scala> val buf = collection.mutable.ArrayBuffer.empty[Int]
scala> buf += l
scala> buf += 10
scala> buf.toArray
```

## 列表缓冲
```
列表缓冲跟数组缓冲很像，只不过它内部使用的是链表而不是数组。
如果你打算在构建完成后将缓冲转换成列表，就用列表缓冲吧 。

scala> val buf = collection.mutable.ListBuffer.empty[Int]
scala> buf += 1
scala> buf += 10
scala> buf.toList
```

## 字符串构建器
```
正如数组缓冲有助于构建数组，列表缓冲有助于构建列表，字符串构造器
有助于构建字符串。由于字符串构建器十分常用，它们已经被引人到默认的命
名空间当中。只需要简单地用 new StringBuilder 来创建即可:
scala> val buf = new StringBuilder
scala> buf += ' a '
scala> buf ++= "be def"
scala> buf.toString
```

## 链表
```
链表支持的最佳的操作是顺序操作。不仅如此，在链表中插人元素或其他链表十分容易 。
```


## 双向链表
```
DoubleLinkedList 眼前面描述的单向链表很像，主要好处是它让移除元素的操作变得非常快。
```

## 可变列表
```
MutableList 由 一个单向链表和一个指向该列表末端的空节点组成，这使
得往列表尾部的追加操作是常量时间的，因为它免除了遍历列表来找到末端的
需要。MutableList 目前是 Scala 的 mutable.LinearSeq 的标准实现 。
```

## 队列
```
Scala 除了不可变队列外也提供可变队列 。 可以像使用不可变队列那样使
用可变队列，不过不是用 enqueue 而是用 ＋＝ 和 ＋＋＝ 操作符来追加元素 。 另外，
对于可变队列而言 ， dequeue 方法只会简单地移除头部的元素并返回 。

scala> val queue =new scala.collection.mutable.Queue[String]
scala> queue +=” a ”
scala> queue ++= List (” b ”,’” c ”)
scala> queue.dequeue
```

## 数组序列
```
数组序列是固定大小的，内部使用 Array[AnyRef ］ 来存放其元素的可变
序列 。 Scala 中的实现是 ArraySeq 类 。
```

## 栈
```
校也有可变的版本，它的工作机制跟不可变的版本一样，只不过修改是当场发生的 。
```

# Iterator特质
```
Iterator 是一种抽象数据结构，用于按需一次性迭代访问数据。
一次性消费：迭代器中的数据只能被遍历一次。
惰性计算：元素在访问时才计算，节省内存。
轻量级：不存储所有数据，仅保留当前状态。

抽象方法：
it .next(), 返回迭代器中的下一个元素并推进 it 到下一步。
it.hasNext, 如果 it 能返回另一个元素则返回 true 。

变种 ：
it.buffered, 返回 it 所有元素的带缓冲的迭代器 。
it grouped size, 以固定大小的“段”交出 it 的元素的迭代器 。
it sliding size, 以固定大小的滑动窗口交出 it 的元素的迭代器 。

拷贝：
it copyToBuffer buf, 将 it 返回的所有元素拷贝到缓冲 buf 。
it copyToArray(arr, s, 1), 将 it 返回的最多 1 个元素拷贝到数组 arr，从下标 s 开始 。 后
两个人参为可选。

复制：
it.duplicate, 一对迭代器，每个都独立地返回川的所有元素 。

添加：
it ++ jt, 返回 it 所有元素以及 jt 所有元素的迭代器 。
it padTo (len, x), 返回 it 所有元素以及 x 的拷贝直到返回元素的总长度达到 len 0

映射：
it map f,通过对 i t 返回的每个元素应用函数 f 得到的迭代器 。
it flatMap f,通过对 it 返回的每个元素应用结果值为迭代器的函数 f ，并追加结果得到的迭代器 。
it collect f,通过对 it 返回的每个元素应用偏函数 f ，并将有定义的结果收集起来得到的迭代器 。

转换：
it.toArray, 将 it 返回的元素收集到数组 。
it.toList, 将 it 返回的元素收集到列表 。
it.toiterable, 将 it 返回的元素收集到 Iterable 。
it.toSeq, 将 it 返回的元素收集到序列 。
it.toindexSeq, 将 i t 返回的元素收集到带下标的序列 。
it.toStream, 将 i t 返回的元素收集到流 。
it.toSet, 将 i t 返回的元素收集到集 。
it.toMap, 将 it 返回的键／值对收集到映射 。

大小信息：
it.isEmpty, 测试迭代器是否为空（跟 hasNext 相反）。
it.nonEmpty, 测试集合是否包含元素（同 hasNext）。
it.size it, 返回的元素数量。 注意：该操作后 it 将位于末端 。
it.length, 同 it .size 。
it.hasDefiniteSize, 如果已知将返回有限多的元素则返回 true（默认同 isEmpty）。

元素获取和下标检索：
it find p, 以可选值返回 it 中首个满足 p 的元素，如果没有元素满足要求
则返回 None. 注意：迭代然会推进到刚好跳过首个满足 p 的元素，或者是末端，如果没有找到符合要求的元素 。
it indexOf x, it 中首个等于 x 的元素的下标。注意：迭代器会推进到刚好跳过首个等于 x 的元素。
it indexWhere p, it 中首个满足 p 的元素的下标。注意：迭代器会推进到刚好跳过该元素的位置。

子迭代器：
i t take n, 返回it的头 n 个元素的迭代器。注意：it 将会推进到第 n 个元
素之后的位置，或者如果少于 n 个元素，推进到来端。
i t drop n, 返回从 it 的第 n + 1 个元素开始的迭代掘。注意：it 会推进到相同的位置 。
it slice (m, n), 返回从 it 的第 m 个元素开始到第 n 个元素之前为止的元素的迭代器。
it takeWhile p, 返回 it 中连续满足前提条件 p 的元素的迭代器 。
i t dropWhile p, 返回跳过 it 连续满足前提条件 p 的元素的迭代器 。
i t filter p, 返回 it 中所有满足条件 p 的元素的迭代器。
it withFilter p, 同 it filter p。为了支持 for 表达式语法 。
it filterNot p, 返回 it 中所有不满足条件 p 的元素的迭代器 。

细分：
it partition p, 将 it 切分为两个迭代器：其中一个返回 it 中所有满足条件 p 的
元素，另一个返回 it 中所有不满足条件 p 的元素 。

元素条件：
it forall p, 表示是否 it 中所有元素都满足前提条件 p 的布尔值 。
it exists p, 表示是否 it 中有元素满足前提条件 p 的布尔值 。
it count p, it 中满足前提条件 p 的元素数 。

折叠：
(z /: it)(op), 以 z 开始自左向右依次对 it 中连续元素应用二元操作 op 。
(it :\ z)(op), 以 z 开始自右向左依次对 it 中连续元素应用二元操作 op 。
it.foldLeft(z)(op), 同（ z /: it)(op） 。
it.foldRight(z)(op), 同（ it :\ z)(op） 。
it reduceLeft op, 自左向右依次对非空迭代器 it 的连续元素应用二元操作 op。
it reduceRight op, 自右向左依次对非空迭代器 it 的 连续元索应用二元操作 op 。

特殊折叠：
it.sum, 迭代器 it 中数值元素值的利 。
it.product, 迭代器 it 中数值元素值的积 。
it.min, 迭代器 it 中有序元素值的最小值 。
it.max, 迭代器 it 中有序元素值的最大值 。

拉链：
it zip jt, it 和 jt 对应元素的对偶组成的迭代器 。
it zipAll (jt, x, y), 由 it 和 jt 对应元素的对偶组成的迭代器，其中较短的序列用 x
或 y 的元素值延展成相同的长度 。
it zipWithindex, 由 it 中的元素及其下标的对偶组成的迭代器。

更新：
it patch (i, jt, r), 将 it 中从位置 1 开始的 r 个元素替换成 jt 的元素得到 的迭代器 。

比较：
it sameElement jt, 测试是否 it 和 jt 包含相同顺序的相间元素 。

字符串：
it addString (b, start, sep, end), 将一个显示了 it 所有元素的字符串添加到 StringBuilder b 中，
元素以 sep 分隔并包含在 start 和 end 当中 。 start、sep 和 end 均为可选 。

it mkString (start, seq, end), 将迭代器转换成一个显示了 it 所有元素的字符串，元素以 sep
分隔并包含在 start 和 end 当中 。start 、 sep 和 end 均为可选 。
```