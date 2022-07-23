package review.jucCountdownLaunch;

import lombok.Getter;
import lombok.Setter;

/**
 * @author pt
 * @date 2022/5/11 0011 - 14:37
 */
public enum CountryEnum {

    ONE(1, "齐国"),
    TWO(2, "楚国"),
    THREE(3, "燕国"),
    FOUR(4, "韩国"),
    FIVE(5, "赵国"),
    SIX(6, "魏国");

    @Getter@Setter private int code;
    @Getter@Setter private String name;
    private CountryEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static CountryEnum foreachCountryEnum(int index) {
        // 枚举天生自带遍历自己的方法
        CountryEnum[] valueArray = CountryEnum.values();

        for (CountryEnum countryEnum : valueArray) {
            if (index == countryEnum.code) {
                return countryEnum;
            }
        }
        return null;
    }
}
