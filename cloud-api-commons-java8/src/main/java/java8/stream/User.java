package java8.stream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @description:
 * @author: peng tao
 * @create: 2021-11-03 17:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    int sortNo;
    String userName;
    String gender;
    int age;
    String dept;
    BigDecimal salary;
}
