package org.example;

import org.testng.annotations.Test;
import io.restassured.RestAssured;

import java.io.File;

import static org.hamcrest.core.IsEqual.equalTo;

public class FileUploadAndDownload {

    @Test(priority = 1)
    public void singleFileUpload() {
        File myFile = new File("C:\\Users\\Kalyani.Sajanpawar\\OneDrive - Scandia Company\\Documents\\abcFile.xlsx");

        RestAssured
                .given()
                .multiPart("file", myFile)
                .contentType("multipart/form-data")
                .when()
                .post("http://localhost:8080/uploadFile")
                .then()
                .statusCode(200)
                .body("fileName", equalTo("abcFile.xlsx"))
                .log().all();
    }

    @Test(priority = 2)
    public void multipleFileUpload() {
        File myFile1 = new File("C:\\Users\\Kalyani.Sajanpawar\\OneDrive - Scandia Company\\Documents\\InterverLatest.txt");
        File myFile2 = new File("C:\\Users\\Kalyani.Sajanpawar\\OneDrive - Scandia Company\\Documents\\Selenium TESTING QUESTIONS.docx");

        RestAssured
                .given()
                // ✅ One .multiPart() call per file, using the same field name
                .multiPart("files", myFile1)
                .multiPart("files", myFile2)
                .contentType("multipart/form-data")
                .when()
                .post("http://localhost:8080/uploadMultipleFiles")
                .then()
                .statusCode(200)
                // ✅ Response is an array — use index [0] to get the first file's name
                .body("[0].fileName", equalTo("InterverLatest.txt"))
                .log().all()
                .extract().path("[0].fileName");  // capture actual stored name;
    }

    @Test(priority = 3)
    public void fileDownload() {
        RestAssured
                .given()
                .when()
                .get("http://localhost:8080/downloadFile/InterverLatest.txt")
                .then()
                .statusCode(200)
                .log().body();
    }
}