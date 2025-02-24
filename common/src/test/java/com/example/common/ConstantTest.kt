package com.example.common

import junit.framework.TestCase
import org.junit.Assert.*
import org.junit.Test
import kotlinx.coroutines.test.runTest

class ConstantTest {

    @Test
    fun test_success_baseurl_validation() = runTest {
        assertEquals("https://api.npoint.io/",Constant.BASE_URL)
    }

    @Test
    fun test_success_baseurl_wrong_validation() = runTest {
        assertNotEquals("https://api.npoi/",Constant.BASE_URL)
    }

    @Test
    fun test_success_api_key_not_empty() = runTest {
        assertNotNull(Constant.API_ID)
    }
}