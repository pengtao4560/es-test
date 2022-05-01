package com.atguigu.springcloud.hutooltest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Convert工具类 test
 * Convert.toXXX
 */
public class ConvertTest2 {
    @Test
    public void testToLong1() {
        assertThat(Convert.toLong("value", 0L)).isEqualTo(0L);
    }

}
