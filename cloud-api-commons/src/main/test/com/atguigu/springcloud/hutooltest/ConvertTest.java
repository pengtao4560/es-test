package com.atguigu.springcloud.hutooltest;

import cn.hutool.core.convert.ConvertException;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.NumberUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.within;

class ConvertTest {

    @Test
    void testToStr1() {
        assertThat(Convert.toStr("value", "defaultValue")).isEqualTo("value");
    }

    @Test
    void testToStr2() {
        assertThat(Convert.toStr("value")).isEqualTo("value");
    }

    @Test
    void testToStrArray() {
        String[] actual = Convert.toStrArray("value1, value2, value3");
        String s = Arrays.toString(actual);
        System.out.println(s);
    }

    @Test
    void testToChar1() {
        assertThat(Convert.toChar("value", 'a')).isEqualTo('v');
    }

    @Test
    void testToChar2() {
        assertThat(Convert.toChar("value")).isEqualTo('v');
    }

    @Test
    void testToCharArray() {
        Character[] characters = {'v', 'a', 'l', 'u', 'e'};
        Character[] values = Convert.toCharArray("value");
        boolean equals = Arrays.equals(characters, values);
        Assert.assertTrue(equals);
    }

    @Test
    void testToByte1() {
        assertThat(Convert.toByte("value", (byte) 0b0)).isEqualTo((byte) 0b0);
    }

    @Test
    void testToByte2() {
        Byte result = Convert.toByte("value");
        System.out.println(result);
        assertThat(result).isNull();
    }

    @Test
    void testToByteArray() {

        byte[] bytes = "value".getBytes();
        System.out.println(Arrays.toString(bytes));
        Byte[] values = Convert.toByteArray(bytes);
        System.out.println(Arrays.toString(values));
    }

    @Test
    void testToPrimitiveByteArray() {

        byte[] contents = Convert.toPrimitiveByteArray("content".getBytes());
        String s = Arrays.toString(contents);
        System.out.println(s);
        assertThat(contents).isEqualTo("content".getBytes());
    }

    @Test
    void testToShort1() {
        assertThat(Convert.toShort("value", (short) 0)).isEqualTo((short) 0);
    }

    @Test
    void testToShort2() {
        assertThat(Convert.toShort("1")).isEqualTo((short) 1);
    }

    @Test
    void testToShortArray() {
        Short[] shortArray = {1, 2, 3, 4};
        Assert.assertTrue(Arrays.equals(shortArray, Convert.toShortArray("1, 2 , 3, 4")));
    }

    @Test
    void testToNumber1() {
        assertThat(Convert.toNumber("1234567", new BigDecimal("0.00"))).isEqualTo(new BigDecimal("1234567"));
    }

    @Test
    void testToNumber2() {
        Number actual = Convert.toNumber("35");
        Class<? extends Number> aClass = actual.getClass();
        System.out.println(aClass);
        System.out.println(actual instanceof BigDecimal);
        boolean equals = NumberUtil.equals((BigDecimal) actual, new BigDecimal("35.00"));
        Assert.assertTrue(equals);
    }

    @Test
    void testToNumberArray() {
        assertThat(Convert.toNumberArray("0.00")).isEqualTo(new Number[]{new BigDecimal("0.00")});
    }

    @Test
    void testToInt1() {
        assertThat(Convert.toInt("value", 0)).isEqualTo(0);
    }

    @Test
    void testToInt2() {
        assertThat(Convert.toInt("2")).isEqualTo(2);
    }

    @Test
    void testToIntArray() {
        Integer[] actual = Convert.toIntArray("1, 2, 3");
        assertThat(actual).isInstanceOf(Integer[].class);
    }

    @Test
    void testToLong1() {
        assertThat(Convert.toLong("value", 0L)).isEqualTo(0L);
    }

    @Test
    void testToLong2() {
        assertThat(Convert.toLong("0")).isEqualTo(0L);
    }

    @Test
    void testToLongArray() {
        assertThat(Convert.toLongArray("value")).isEqualTo(new Long[]{0L});
        assertThat(Convert.toLongArray("value")).isEqualTo(new Long[]{});
    }

    @Test
    void testToDouble1() {
        assertThat(Convert.toDouble("value", 0.0)).isEqualTo(0.0, within(0.0001));
    }

    @Test
    void testToDouble2() {
        assertThat(Convert.toDouble("value")).isEqualTo(0.0, within(0.0001));
    }

    @Test
    void testToDoubleArray() {
        assertThat(Convert.toDoubleArray("value")).isEqualTo(new Double[]{0.0});
        assertThat(Convert.toDoubleArray("value")).isEqualTo(new Double[]{});
    }

    @Test
    void testToFloat1() {
        assertThat(Convert.toFloat("value", 0.0f)).isEqualTo(0.0f, within(0.0001f));
    }

    @Test
    void testToFloat2() {
        assertThat(Convert.toFloat("value")).isEqualTo(0.0f, within(0.0001f));
    }

    @Test
    void testToFloatArray() {
        Float[] actual = Convert.toFloatArray("0.0, 1.0");
        System.out.println(Arrays.toString(actual));
        Arrays.stream(actual).forEach(System.out::println);

    }

    @Test
    void testToBool1() {
        assertThat(Convert.toBool("value", false)).isTrue();
    }

    @Test
    void testToBool2() {
        assertThat(Convert.toBool("value")).isTrue();
    }

    @Test
    void testToBooleanArray() {
        assertThat(Convert.toBooleanArray("value")).isEqualTo(new Boolean[]{false});
        assertThat(Convert.toBooleanArray("value")).isEqualTo(new Boolean[]{});
    }

    @Test
    void testToBigInteger1() {
        assertThat(Convert.toBigInteger("value", new BigInteger("100"))).isEqualTo(new BigInteger("100"));
    }

    @Test
    void testToBigInteger2() {
        assertThat(Convert.toBigInteger("value")).isEqualTo(new BigInteger("100"));
    }

    @Test
    void testToBigDecimal1() {
        assertThat(Convert.toBigDecimal("value", new BigDecimal("0.00"))).isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    void testToBigDecimal2() {
        assertThat(Convert.toBigDecimal("value")).isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    void testToDate1() {
        assertThat(Convert.toDate("value", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime()))
                .isEqualTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
    }

    @Test
    void testToLocalDateTime1() {
        assertThat(Convert.toLocalDateTime("value", LocalDateTime.of(2020, 1, 1, 0, 0, 0)))
                .isEqualTo(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
    }

    @Test
    void testToLocalDateTime2() {
        assertThat(Convert.toLocalDateTime("value")).isEqualTo(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
    }

    @Test
    void testToInstant() {
        assertThat(Convert.toInstant("value", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime()))
                .isEqualTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
    }

    @Test
    void testToDate2() {
        assertThat(Convert.toDate("value")).isEqualTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
    }

    @Test
    void testToEnum1() {
        // Setup
        final GenderEnum defaultValue = GenderEnum.MALE;

        // Run the test
        final GenderEnum result = Convert.toEnum(GenderEnum.class, "value", defaultValue);

        // Verify the results
    }

    @Test
    void testToEnum2() {
        // Setup
        // Run the test
        final GenderEnum result = Convert.toEnum(GenderEnum.class, "value");

        // Verify the results
    }

    @Test
    void testToCollection() {
        // Setup
        // Run the test
        final Collection<?> result = Convert.toCollection(Object.class, Object.class, "value");

        // Verify the results
    }

    @Test
    void testToList1() {
        // Setup
        // Run the test
        final List<?> result = Convert.toList("value");

        // Verify the results
    }

    @Test
    void testToList2() {
        // Setup
        // Run the test
        final List<String> result = Convert.toList(String.class, "value");

        // Verify the results
        System.out.println(result);
    }

    @Test
    void testToSet() {
        // Setup
        // Run the test
        final Set<String> result = Convert.toSet(String.class, "value");

        // Verify the results
        System.out.println(result);
    }

    @Test
    void testToMap() {
        // Setup
        // Run the test
        final Map<String, Object> result = Convert.toMap(String.class, Object.class, "value");

        // Verify the results
        System.out.println(result);
    }

    @Test
    void testConvertByClassName() {
        // Setup
        // Run the test
        final Double result = Convert.convertByClassName("Double", "1.0");

        // Verify the results
        System.out.println(result instanceof Double);
    }

    @Test
    void testConvertByClassName_ThrowsConvertException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> Convert.convertByClassName("className", "value")).isInstanceOf(ConvertException.class);
    }

    @Test
    void testConvert1() {
        // Setup
        // Run the test
        final Object result = Convert.convert(Object.class, "value");

        // Verify the results
        boolean b = result instanceof Object;
        System.out.println(b);
    }

    @Test
    void testConvert1_ThrowsConvertException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> Convert.convert(Object.class, "value")).isInstanceOf(ConvertException.class);
    }

    @Test
    void testConvert2() {
        // Setup
        final TypeReference<Double> reference = null;

        // Run the test
        final Double result = Convert.convert(reference, "1.0");

        // Verify the results
    }

    @Test
    void testConvert2_ThrowsConvertException() {
        // Setup
        final TypeReference<String> reference = null;

        // Run the test
        assertThatThrownBy(() -> Convert.convert(reference, "value")).isInstanceOf(ConvertException.class);
    }

    @Test
    void testConvert3() {
        // Setup
        final Type type = null;

        // Run the test
        final Object result = Convert.convert(type, "value");

        // Verify the results
    }

    @Test
    void testConvert3_ThrowsConvertException() {
        // Setup
        final Type type = null;

        // Run the test
        assertThatThrownBy(() -> Convert.convert(type, "value")).isInstanceOf(ConvertException.class);
    }

    @Test
    void testConvert4() {
        // Setup
        final Object defaultValue = null;

        // Run the test
        final Object result = Convert.convert(Object.class, "value", defaultValue);

        // Verify the results
    }

    @Test
    void testConvert4_ThrowsConvertException() {
        // Setup
        final Object defaultValue = null;

        // Run the test
        assertThatThrownBy(() -> Convert.convert(Object.class, "value", defaultValue))
                .isInstanceOf(ConvertException.class);
    }

    @Test
    void testConvert5() {
        // Setup
        final Type type = null;
        final Object defaultValue = null;

        // Run the test
        final Object result = Convert.convert(type, "value", defaultValue);

        // Verify the results
    }

    @Test
    void testConvert5_ThrowsConvertException() {
        // Setup
        final Type type = null;
        final Object defaultValue = null;

        // Run the test
        assertThatThrownBy(() -> Convert.convert(type, "value", defaultValue)).isInstanceOf(ConvertException.class);
    }

    @Test
    void testConvertQuietly1() {
        // Setup
        final Type type = null;

        // Run the test
        final Object result = Convert.convertQuietly(type, "value");

        // Verify the results
    }

    @Test
    void testConvertQuietly2() {
        // Setup
        final Type type = null;
        final Object defaultValue = null;

        // Run the test
        final Object result = Convert.convertQuietly(type, "value", defaultValue);

        // Verify the results
    }

    @Test
    void testConvertWithCheck() {
        // Setup
        final Type type = null;
        final Object defaultValue = null;

        // Run the test
        final Object result = Convert.convertWithCheck(type, "value", defaultValue, false);

        // Verify the results
    }

    @Test
    void testToSBC1() {
        assertThat(Convert.toSBC("input")).isEqualTo("result");
    }

    @Test
    void testToSBC2() {
        assertThat(Convert.toSBC("input", new HashSet<>(Arrays.asList('a')))).isEqualTo("result");
    }

    @Test
    void testToDBC1() {
        assertThat(Convert.toDBC("input")).isEqualTo("result");
    }

    @Test
    void testToDBC2() {
        assertThat(Convert.toDBC("text", new HashSet<>(Arrays.asList('a')))).isEqualTo("text");
    }

    @Test
    void testToHex1() {
        assertThat(Convert.toHex("str", StandardCharsets.UTF_8)).isEqualTo("result");
    }

    @Test
    void testToHex2() {
        assertThat(Convert.toHex("content".getBytes())).isEqualTo("result");
    }

    @Test
    void testHexToBytes() {
        assertThat(Convert.hexToBytes("src")).isEqualTo("content".getBytes());
    }

    @Test
    void testHexToStr() {
        assertThat(Convert.hexToStr("hexStr", StandardCharsets.UTF_8)).isEqualTo("result");
    }

    @Test
    void testStrToUnicode() {
        assertThat(Convert.strToUnicode("strText")).isEqualTo("result");
    }

    @Test
    void testUnicodeToStr() {
        assertThat(Convert.unicodeToStr("unicode")).isEqualTo("result");
    }

    @Test
    void testConvertCharset() {
        assertThat(Convert.convertCharset("str", "sourceCharset", "destCharset")).isEqualTo("str");
    }

    @Test
    void testConvertTime() {
        assertThat(Convert.convertTime(0L, TimeUnit.MILLISECONDS, TimeUnit.MILLISECONDS)).isEqualTo(0L);
    }

    @Test
    void testWrap() {
        assertThat(Convert.wrap(Object.class)).isEqualTo(Object.class);
    }

    @Test
    void testUnWrap() {
        assertThat(Convert.unWrap(Object.class)).isEqualTo(Object.class);
    }

    @Test
    void testNumberToWord() {
        assertThat(Convert.numberToWord(new BigDecimal("0.00"))).isEqualTo("result");
    }

    @Test
    void testNumberToSimple() {
        assertThat(Convert.numberToSimple(new BigDecimal("0.00"))).isEqualTo("result");
    }

    @Test
    void testNumberToChinese() {
        assertThat(Convert.numberToChinese(0.0, false)).isEqualTo("result");
    }

    @Test
    void testChineseToNumber() {
        assertThat(Convert.chineseToNumber("number")).isEqualTo(0);
    }

    @Test
    void testDigitToChinese() {
        assertThat(Convert.digitToChinese(new BigDecimal("0.00"))).isEqualTo("零");
    }

    @Test
    void testIntToByte() {
        assertThat(Convert.intToByte(0)).isEqualTo((byte) 0b0);
    }

    @Test
    void testByteToUnsignedInt() {
        assertThat(Convert.byteToUnsignedInt((byte) 0b0)).isEqualTo(0);
    }

    @Test
    void testBytesToShort() {
        assertThat(Convert.bytesToShort("content".getBytes())).isEqualTo((short) 0);
    }

    @Test
    void testShortToBytes() {
        assertThat(Convert.shortToBytes((short) 0)).isEqualTo("content".getBytes());
    }

    @Test
    void testBytesToInt() {
        assertThat(Convert.bytesToInt("content".getBytes())).isEqualTo(0);
    }

    @Test
    void testIntToBytes() {
        assertThat(Convert.intToBytes(0)).isEqualTo("content".getBytes());
    }

    @Test
    void testLongToBytes() {
        assertThat(Convert.longToBytes(0L)).isEqualTo("content".getBytes());
    }

    @Test
    void testBytesToLong() {
        assertThat(Convert.bytesToLong("content".getBytes())).isEqualTo(0L);
    }
}
