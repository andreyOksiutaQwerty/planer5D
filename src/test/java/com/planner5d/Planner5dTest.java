package com.planner5d;


import com.planner5d.api.CRUDsAPI;

import com.planner5d.steps.AddSofaSteps;
import com.planner5d.steps.CreateProjectSteps;
import com.planner5d.ui.ProjectPage;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.codeborne.selenide.WebDriverRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Planner5dTest {

    private static WebDriver driver;
    private static String sofaId;
    private static String sofaPuId;
    public static String projectKey;

    @Test
    @Order(1)
    public void addsSofaItemToTheProjectTest(){

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption(
                "debuggerAddress",
                "127.0.0.1:9222"
        );

        driver = new ChromeDriver(options);
        WebDriverRunner.setWebDriver(driver);

        driver = new ChromeDriver(options);
        WebDriverRunner.setWebDriver(driver);
        driver.get("https://planner5d.com/");

        CreateProjectSteps.createProject();

        ProjectPage.chooseFurnishButton();

        long countSofaInProjectBeforeAdd=ProjectPage.getFurnitureCount();

        sofaId=AddSofaSteps.addSofaToProject();
        sofaPuId=ProjectPage.returnSofaPuId(sofaId);
        projectKey=ProjectPage.getProjectKey();


        long countSofaInProjectAfterAdded=ProjectPage.getFurnitureCount();

        assertThat(countSofaInProjectAfterAdded).isGreaterThan(countSofaInProjectBeforeAdd);
    }

    @Test
    @Order(2)
    public void sofaVisibleIn3DProjectTest(){

        ProjectPage.switchTo3d();
        boolean displayedSofaIn3d= ProjectPage.isFurnitureVisibleIn3D(sofaId);

        assertThat(displayedSofaIn3d).isEqualTo(true);
    }

    @Test
    @Order(3)
    public void getProjectDataJsonAPI(){

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException();
        }

        Response r= CRUDsAPI.getResponse(projectKey);

        r.then().statusCode(200);

        List<String> puIds = r.jsonPath().getList(
                "items[0].data.items.collectMany { it.items ?: [] }" +
                        ".findAll { it.className == 'Ns' }.collect { it.puid }"
        );
        assertThat(puIds).contains(sofaPuId);
    }


}
