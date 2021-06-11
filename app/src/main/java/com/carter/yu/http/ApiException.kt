package com.carter.yu.http

/**
 * 业务错误信息
 */
class ApiException(val errorMessage: String, val errorCode: Int) : Throwable()