```
目前问题：

emoj 编码表：
http://apps.timwhitlock.info/emoji/tables/unicode


content中 包含4字节字符，无法识别，导致插入数据库失败。

例如：

correct string value: '\xF0\x9F\x98\x84\xE5\x93...'

MAC 电脑上可以通过ctrl+command+space 打出emoj表情。
```

```
处理办法:

先处理4字节字符， 转成对应的emoj  utf8编码

如下：

// 过滤4字节字符
remark = Emoj4byte2Char.stripEmoj(remark);

// 替换emoj表情前准备，将4字节转成char
remark = Emoj4byte2Char.convert(remark);
// 过滤emoji表情
remark = EmojiReplacer.replaceEmoji(remark);


Emoj4byte2Char  目前没有枚举到所有的情况，后续 遇到新的再补充就行。

替换emoj，待验证：
source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", slipStr); 
```

```
mysql 支持emoj字符：
https://www.liaoxuefeng.com/article/00145803336427519ae82a6c5b5474682c0c4ba5b47fb33000

如果使用MySQL作为数据库，需要升级到5.5.3或更新的版本，然后，把默认编码从原来的utf8改为utf8mb4，在my.cnf或者my.ini配置文件中修改如下：
如果是java应用自身管理mysql连接，那么mysql connector版本高于5.1.13，
在高版本mysql jdbc中会将utf8识别为utf8mb4，但jdbc连接串不能直接指定utf8mb4.
在做系统初期设计的时候，需要思考自己的应用未来会不会遇到这一类编码问题，而提前将数据库编码设置为utf8mb4，从后续成本上来看，如果应用要做移动产品的用户文本存储，那么势必直接使用utf8mb4是最明智的行为。


[client]
default-character-set = utf8mb4

[mysqld]
character-set-server = utf8mb4
collation-server     = utf8mb4_general_ci
```

``
参数样例：

show variables like '%char%'

character_set_client=utf8mb4
character_set_connection=utf8mb4
character_set_server=utf8mb4


show variables like '%coll%'
collation_connection=utf8mb4_general_ci
collation_database=utf8_general_ci
collation_server=utf8mb4_general_ci
```
