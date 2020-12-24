package com.charlye934.loginddagger.login

interface LoginActivityMVP {

    interface View{
        var getFirstName:String
        var getLastName:String

        fun showUserNotAvaible()
        fun showInputError()
        fun showUserSaved()

        fun setFirstName(firstName: String)
        fun setLastName(lastName: String)
    }

    interface Presenter{
        fun setView(view:LoginActivityMVP.View)
        fun loginButtonClicked()
        fun getCurrentUser()

    }

    interface Model{
        fun createUser(firstName:String, lastName:String)
        fun getUser():User
    }
}