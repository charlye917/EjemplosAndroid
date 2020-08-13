package com.charlye934.loginddagger.login

interface LoginRepository {
    fun saveUser(user:User)
    fun getUser(): User
}