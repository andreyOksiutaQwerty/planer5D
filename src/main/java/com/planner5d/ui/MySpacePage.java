package com.planner5d.ui;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MySpacePage {
    private static  SelenideElement createProjectButton=$("button.spaces-header-create-button");
    private static final SelenideElement startProjectButton = $( byText("Start Project"));

    public static void createProject(){
        createProjectButton.shouldBe(visible).click();
        startProjectButton.shouldBe(visible).click();
    }


}
