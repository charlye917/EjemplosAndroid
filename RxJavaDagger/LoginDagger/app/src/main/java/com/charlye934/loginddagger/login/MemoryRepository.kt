package com.charlye934.loginddagger.login

class MemoryRepository : LoginRepository{

    private var user: User? = null

    override fun saveUser(user: User) {
        if(this.user == null){
            this.user = getUser()
        }

        this.user = user
    }

    override fun getUser(): User {
        return if(user == null){
            user = User("Carlos", "Arteaga")
            user as User
        }else{
            user as User
        }
    }

}