package com.planner5d.ui;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.id;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ProjectPage {
    private static final SelenideElement furnishButton = $("[data-testid='catalog-interior']");
    private static final SelenideElement livingRoomButton = $x("//div[contains(@class,'catalog-item') and .//b[text()='Living room']]");
    private static final SelenideElement sofasButton = $x("//div[contains(@class,'catalog-item') and .//b[text()='Sofas']]");
    private static final SelenideElement straightSofasButton = $x("//li[contains(., 'Straight')]");
    private static final ElementsCollection catalogSofas = $$(".catalog-grid > .catalog-item.is-draggable");
    private static final SelenideElement switchTo3DButton = $(byText("3D"));
    private static final SelenideElement allProjectsButton = $(".header-all-projects-button");

    public static void backToAllProject(){
        allProjectsButton.click();
    }
    public static void chooseFurnishButton() {
        furnishButton.shouldBe(visible).click();
    }

    public static void chooseRoomButton() {
        livingRoomButton.shouldBe(visible).click();
    }

    public static void chooseSofasButton() {
        sofasButton.shouldBe(visible).click();
    }

    public static void chooseStraightSofasButton() {
        straightSofasButton.shouldBe(visible).click();
    }

    public static String chooseFirstSofas() {
        String sofaId = catalogSofas.first().$("img").getAttribute("src").replaceAll(".*/(\\d+)\\.webp", "$1");
        catalogSofas.first().click();
        return sofaId;
    }

    public static String returnSofaPuId(String sofaId) {
        return Selenide.executeJavaScript("""
                const furniture = Object.values(Editor._project._allObjects)
                    .filter(item =>
                        item._type === 'Ns' &&
                        String(item.config?.id) === String(arguments[0])
                    );
                
                const sofa = furniture[furniture.length - 1];
                
                return sofa ? sofa._puid : null;
                """, sofaId);
    }

    public static void switchTo3d() {
        switchTo3DButton.shouldBe(visible).click();
    }

    public static long getFurnitureCount() {
        return Selenide.executeJavaScript("""
                return Object.values(Editor._project._allObjects)
                    .filter(item => item._type === 'Ns').length;
                """);
    }

    public static String getProjectKey() {
        return Selenide.executeJavaScript("""
                return new URLSearchParams(window.location.search).get('key');
                """);
    }

    public static boolean isFurnitureVisibleIn3D(String puid) {
        return Selenide.executeJavaScript("""
                const project = Editor._project;
                
                const furniture = Object.values(project._allObjects)
                    .filter(item =>
                        item._type === 'Ns' &&
                        String(item.config?.id) === String(arguments[0])
                    );
                
                return furniture.some(item =>
                    item._loaded3d === true &&
                    item._l3d != null &&
                    item._l3d.visible === true &&
                    item._l3d.parent?.type === 'Scene' &&
                    project._viewMode === '3d' &&
                    project._viewModeReady === true
                );
                """, puid);
    }
}
