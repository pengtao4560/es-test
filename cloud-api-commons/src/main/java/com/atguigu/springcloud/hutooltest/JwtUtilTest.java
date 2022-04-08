package com.atguigu.springcloud.hutooltest;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.signers.JWTSigner;
import com.atguigu.springcloud.utils.JwtUtil;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class JwtUtilTest {

    @Test
    void testCreateToken1() {
        // Setup
        final Map<String, Object> payload = new HashMap<>();

        // Run the test
        final String result = JwtUtil.createToken(payload, "content".getBytes());

        System.out.println();
        System.out.println(result);

        // Verify the results
        Assertions.assertThat(result).isEqualTo("result");
    }

    @Test
    void testCreateToken2() {
        // Setup
        final Map<String, Object> headers = new HashMap<>();
        final Map<String, Object> payload = new HashMap<>();

        // Run the test
        final String result = JwtUtil.createToken(headers, payload, "content".getBytes());

        // Verify the results
        Assertions.assertThat(result).isEqualTo("result");
    }

    @Test
    void testCreateToken3() {
        // Setup
        final Map<String, Object> payload = new HashMap<>();
        final JWTSigner signer = null;

        // Run the test
        final String result = JwtUtil.createToken(payload, signer);

        // Verify the results
        Assertions.assertThat(result).isEqualTo("result");
    }

    @Test
    void testCreateToken4() {
        // Setup
        final Map<String, Object> headers = new HashMap<>();
        final Map<String, Object> payload = new HashMap<>();
        final JWTSigner signer = null;

        // Run the test
        final String result = JwtUtil.createToken(headers, payload, signer);

        // Verify the results
        Assertions.assertThat(result).isEqualTo("result");
    }

    @Test
    void testParseToken() {
        // Setup
        // Run the test
        final JWT result = JwtUtil.parseToken("token");

        // Verify the results
    }

    @Test
    void testVerify1() {
        Assertions.assertThat(JwtUtil.verify("token", "content".getBytes())).isTrue();
    }

    @Test
    void testVerify2() {
        // Setup
        final JWTSigner signer = null;

        // Run the test
        final boolean result = JwtUtil.verify("token", signer);

        // Verify the results
        Assertions.assertThat(result).isTrue();
    }
}
