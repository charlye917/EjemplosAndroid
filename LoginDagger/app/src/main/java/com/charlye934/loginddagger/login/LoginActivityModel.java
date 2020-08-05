package com.charlye934.loginddagger.login;

public class LoginActivityModel implements LoginActivityMVP.Model{

    private LoginRepository repository;

    public LoginActivityModel(LoginRepository repository){
        this.repository = repository;
    }

    @Override
    public void createUser(String firstName, String lastName) {
        //Logica de busines
        repository.saveUser(new User(firstName,lastName));
    }

    @Override
    public User getUser() {
        //Logica de busines
        return repository.getUser();
    }
}
