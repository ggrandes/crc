# crc

A cyclic redundancy check (CRC) is an error-detecting code commonly used to detect accidental changes to digital data. Code writen in pure Java. Open Source project under Apache License v2.0

### Current Stable Version is [1.0.0](https://search.maven.org/#search|ga|1|g%3Aorg.javastack%20a%3Acrc)

---

## DOC

#### Supported CRCs

| name | poly | check | init | refin | refout | xorout |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| CRC32 | 0x04c11db7 | 0xcbf43926 | 0xffffffff | true | true | 0xffffffff |
| CRC32C | 0x1edc6f41 | 0xe3069283 | 0xffffffff | true | true | 0xffffffff |
| CRC64 | 0x42f0e1eba9ea3693 | 0x6c40df5f0b497347 | 0x0 | false | false | 0x0 |

#### Usage Example

```java
import org.javastack.crc.*;

public class HelloWorld {
	public static void main(final String[] args) throws Throwable {
		final CRC32C crc = new CRC32C();
		crc.reset();
		crc.update("test".getBytes());
		System.out.println("86a072c0=" + Long.toHexString(crc.getValue()));
		crc.reset();
		crc.update("hello world".getBytes());
		System.out.println("c99465aa=" + Long.toHexString(crc.getValue()));
	}
}
```

---

## MAVEN

Add the dependency to your pom.xml:

    <dependency>
        <groupId>org.javastack</groupId>
        <artifactId>crc</artifactId>
        <version>1.0.0</version>
    </dependency>

---
Inspired in [CRC RevEng](https://reveng.sourceforge.io/crc-catalogue/), this code is Java-minimalistic version.
