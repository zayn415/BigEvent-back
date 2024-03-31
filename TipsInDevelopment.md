# 开发过程中遇到的知识点
### 1. Maven依赖的属性scope
> `scope` 是 Maven 依赖中的一个属性，它用于指定依赖的范围。它决定了依赖在哪些类路径下可用，以及它们在哪些阶段会被包含。

`scope` 的几种可能的值：
- `compile`：这是默认范围，如果没有指定 `scope`，则会使用此范围。编译范围依赖在所有的类路径（编译、测试、运行）中都可用。
- `provided`：这意味着你期望 JDK 或者一个容器来提供这个依赖。例如，当编译一个 web 应用程序时，你可能需要 Servlet API 和一些服务器提供的库。
- `runtime`：这个范围表示依赖不需要在编译时可用，它包含在运行和测试系统的类路径中。
- `test`：这个范围表示依赖只对于测试目的而可用，它们在编译主代码时不会被包含。
- `system`：这个范围类似于 `provided`，但是你必须显式提供一个对系统路径的引用。这不推荐使用，因为它依赖于你的机器上的文件。
- `import`：这个范围只用在依赖类型为 `pom` 在 `<dependencyManagement>` 中。这表示指定的 POM 将被替换为在 `<dependencyManagement>` 中指定的依赖列表。

### 2. 注解Validation
- 邮箱使用@Email注解
- 密码使用@Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$")注解，表示密码必须是6-16位数字和字母的组合

### 3. 自定义密码校验注解@Password
- [定义注解](src/main/java/com/zayn/bigevent/annotations/Password.java)
  - 元注解Target定义注解作用于参数
  - 元注解Retention定义注解的生命周期
  - 元注解Constraint定义注解的实现类为
  - 元注解Documented定义注解包含在JavaDoc中


- [注解处理器](src/main/java/com/zayn/bigevent/annotations/PasswordValidator.java)
  - 实现ConstraintValidator接口，重写initialize和isValid方法
  - initialize方法用于初始化注解，为空
  - isValid方法用于校验密码是否符合规则

### 4. 参数校验
- 不在数据访问层（mapper）中进行校验，而在控制层controller和服务层service进行校验

### 5. JWT
- JWT是一种用于双方之间传递安全信息的简洁的、URL安全的表述性声明规范。JWT作为一个开放的标准（RFC 7519），定义了一种简洁的，自包含的方法用于通信双方之间以JSON对象的形式安全的传递信息。因为数字签名的存在，这些信息是可信的，JWT可以使用HMAC算法或者是RSA的公私秘钥对进行签名。

#### JWT的组成
JWT的形式：header.payload.signature
- Header（头部）
  - 包含了两部分：token的类型（JWT）和使用的加密算法（HMAC SHA256或RSA）
  - 例如：{"alg": "HS256", "typ": "JWT"}
- Payload（负载）
  - 包含了claim，claim是关于实体（通常是用户）和其他数据的声明
  - 载荷不存储私密信息如密码，因为可以被解密
  - 例如：{"sub": "1234567890", "name": "John Doe", "admin": true}
- Signature（签名）
  - 使用base64编码的header和payload组合的字符串，加上一个密钥，使用header中指定的加密算法进行加密，生成签名
  - 例如：HMACSHA256(base64UrlEncode(header) + "." + base64UrlEncode(payload), secret)


### 测试
使用JUnit5测试时，需要在测试类上添加注解`@ExtendWith(SpringExtension.class)`，否则会报错`java.lang.IllegalStateException: Configuration error: found multiple declarations of @ExtendWith`。

同时，`@SpringBootTest`注解也需要一起使用，可以自动注入Spring容器中的Bean。

而使用JUnit4测试时，只需要在测试类上添加`@RunWith(SpringRunner.class)`注解即可。

测试方法使用注解`@Test`

PS: `@Component`注解是将类注册到容器，测试类用该注解会报错，需要使用`@SpringBootTest`注解

### 关于gitignore不生效的问题
> 即在gitignore中添加了的文件，但是在git status中还是会显示出来

解决方法：
- 清除缓存：`git rm -r --cached .`
- 重新添加文件：`git add .`
- 再次提交

### ThreadLocal
> ThreadLocal是一个线程内部的数据存储类，可以在每个线程中存储数据，数据存储以后，只有当前线程可以获取到这个数据，其他线程无法获取到这个数据。

- set存储数据
- get获取数据
- remove移除数据
- 线程安全：ThreadLocal是线程安全的，因为每个线程都有自己的ThreadLocalMap，互不干扰
- 