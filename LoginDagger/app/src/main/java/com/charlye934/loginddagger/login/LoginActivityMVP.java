package com.charlye934.loginddagger.login;

public interface LoginActivityMVP {

    interface View{
        String getFirstName();
        String getLastName();

        void showUserNotAvaible();
        void showInputError();
        void showUserSaved();

        void setFirstName(String firstName);
        void setLasNmae(String lastName);
    }

    interface  Presenter{
        void setView(LoginActivityMVP.View view);
        void loginButtonClicked();
        void getCurrentUser();
    }

    interface Model{
        void createUser(String firstName, String lastName);
        User getUser();
    }
}
