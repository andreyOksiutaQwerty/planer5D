package com.planner5d.ui;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.Visible;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement enterToAccountAcrossGoogleAccountButton=$(".button.auth-google");
    private SelenideElement googleLoginField=$("#identifierId");
    private SelenideElement nextButton=$("#identifierNext");
    private SelenideElement passwordField=$("[name='Passwd']");
    private SelenideElement passwordNextButton=$("#passwordNext");


    public void choseGoogleEnterToAccount(){
        enterToAccountAcrossGoogleAccountButton.shouldBe(visible).click();
    }
    public void enterGoogleLoginAndClickNext(String login){
        googleLoginField.shouldBe(visible).setValue(login);
        nextButton.click();
    }

    public void enterGooglePasswordAndClickNext(String password){
        passwordField.shouldBe(visible).setValue(password);
        passwordNextButton.click();
    }
}
