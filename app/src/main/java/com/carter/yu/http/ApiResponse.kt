package com.carter.yu.http

import java.io.Serializable

class ApiResponse<T> : Serializable {

    private var data: T? = null

    private var errorMessage = ""

    private var errorCode = 0

    fun data(): T {
        when (errorCode) {
            //请求成功
            0, 200 -> {
                return data!!
            }
            //未登录状态发起请求
            -1001 -> {
                throw ApiException(errorMessage, errorCode)
            }
            -1 -> {
                throw ApiException(errorMessage, errorCode)
            }
            //登录失败
        }
        throw ApiException(errorMessage, errorCode)
    }

    fun data(clazz: Class<T>): T {
        when (errorCode) {
            //请求成功
            0, 200 -> {
                //避免业务层做null判断,通过反射将null替换为T类型空对象
                if (data == null) {
                    data = clazz.newInstance()
                }
                return data!!
            }
            //未登陆请求需要用户信息的接口
            -1001 -> {
                throw ApiException(errorMessage, errorCode)
            }
            //登录失败
            -1 -> {
                throw ApiException(errorMessage, errorCode)
            }
        }
        //其他错误
        throw ApiException(errorMessage, errorCode)
    }
}