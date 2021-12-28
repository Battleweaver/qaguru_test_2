package com.gmail.battleweaver;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;

public class FormTest {
    @BeforeAll
    static void beforeAll(){
        Configuration.startMaximized = true;
    }

    @Test
    void fillFromTest(){
        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue("Apolinary");
        $("#lastName").setValue("Gostomislov");
        $("#userEmail").setValue("here@there.com");
        $("[for='gender-radio-3']").click();
        $("#userNumber").setValue("89997776655");

        $("#dateOfBirthInput").click();
        $("[class='react-datepicker__month-select']").selectOption("March");
        $("[class='react-datepicker__year-select']").selectOption("1980");
        $("[class*='react-datepicker__day--015']").click();

        $("#subjectsInput").setValue("Chemistry").pressEnter();
        $("#subjectsInput").setValue("Physics").pressEnter();

        $("[for='hobbies-checkbox-1']").parent().click();
        $("[for='hobbies-checkbox-2']").parent().click();

        File lesson = new File("src/test/java/com/gmail/battleweaver/files/Lesson_1.png");
        String path = lesson.getAbsolutePath();
        $("#uploadPicture").sendKeys(path);

        $("[placeholder='Current Address']").setValue("Prospect Lenina 10");
        $("[placeholder='Current Address']").scrollIntoView(true);
        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#react-select-4-input").setValue("Delhi").pressEnter();
        $("#submit").click();

        //Assert
        $$x("//*[@class='modal-body']//td[2]").shouldHave(CollectionCondition.exactTexts(
                "Apolinary Gostomislov", "here@there.com", "Other", "8999777665", "15 March,1980",
                "Chemistry, Physics", "Sports, Reading", "Lesson_1.png", "Prospect Lenina 10", "NCR Delhi"));

    }
}
