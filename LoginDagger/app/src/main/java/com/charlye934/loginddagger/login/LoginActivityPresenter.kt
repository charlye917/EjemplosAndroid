package com.charlye934.loginddagger.login

import androidx.annotation.Nullable

class LoginActivityPresenter(private val model: LoginActivityMVP.Model): LoginActivityMVP.Presenter {

    @Nullable
    private lateinit var view: LoginActivityMVP.View

    override fun setView(view: LoginActivityMVP.View){
        this.view = view
    }

    override fun loginButtonClicked() {
        if(view != null){
            if(view.getFirstName.trim() == "" || view.getLastName.trim() == ""){
                view.showInputError()
            }else{
                model.createUser(view.getFirstName.trim(), view.getLastName.trim())
                view.showUserSaved()
            }
        }
    }

    override fun getCurrentUser() {
        val user = model.getUser()
        if(user == null){
            if(view != null){
                view.showUserNotAvaible()
            }
        }else{
            if(view != null){
                view.getFirstName = user.firstName
                view.getLastName = user.lastName
            }
        }
    }

}