package com.planner5d.steps;

import com.planner5d.ui.LoginPage;

public class LoginSteps {
    private static LoginPage loginPage=new LoginPage();

    public static void loginToAccountAcrossGoogleAccount() {
        loginPage.choseGoogleEnterToAccount();
        loginPage.enterGoogleLoginAndClickNext("andreykaandreykh@gmail.com");
        loginPage.enterGooglePasswordAndClickNext("123Qazwsx");
    }
}
