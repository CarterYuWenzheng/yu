package com.carter.baselibrary.http

/**
 * 业务异常
 */
class ApiException(val errorMessage: String, val errorCode: Int) : Throwable()