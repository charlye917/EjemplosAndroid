package com.charlye934.loginddagger.login

class LoginActivityModel(private val repository:LoginRepository) : LoginActivityMVP.Model{

    override fun createUser(firstName: String, lastName: String) {
        repository.saveUser(User(firstName,lastName))
    }

    override fun getUser(): User = repository.getUser()
}