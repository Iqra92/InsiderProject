package Automation.testCases;

import Automation.FakeData;
import Automation.model.CreatePetModel;
import Automation.model.ErrorResponseModel;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Automation.utilities.Constants.*;
import static io.restassured.RestAssured.given;


public class ApiTesting {

    private static long petId;
    private CreatePetModel createPetModel;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_API_URI;
    }

    @Test(priority = 5)
    public void createPet_successfully_onTheStore_200() {
        FakeData fakeData = new FakeData();
           String createPetBody = fakeData.createFakePetData();



        Response response = given().contentType(ContentType.JSON)
                .header(ACCEPT, NAME)
                .header(CONTENT_TYPE, NAME)
                .body(createPetBody).when()
                .post(CREATE_PET_ENDPOINT)
                .then()
                .extract()
                .response();

        if (response.statusCode() == 200) {
            CreatePetModel data = new Gson().fromJson(response.body().asString(), CreatePetModel.class);
            petId = data.getId();
            printMessage("Dog name is created on the store name is: "+ data.getName());
            printMessage(response.statusCode()+"");
        } else {
            System.out.println("Error: Found  " + response.getStatusCode());
        }
    }


    @Test(priority = 6)
    public void createPet_emptyBody_invalidException_405() {
        Response response = given().contentType(ContentType.JSON)
                .header(ACCEPT, NAME)
                .header(CONTENT_TYPE, NAME)
                .body("")
                .when()
                .post(CREATE_PET_ENDPOINT)
                .then()
                .extract()
                .response();
        if (response.statusCode() == 405) {
            ErrorResponseModel errorResponse = new Gson().fromJson(response.body().asString(), ErrorResponseModel.class);
            printMessage("Status Code: " + response.statusCode());
            printMessage("Error Message: " + errorResponse.getMessage());
            printMessage("Validation exception..");
        } else {
            printMessage("Status Code: " + response.statusCode());
        }
    }

    @Test(priority = 7)
    public void createPet_emptyBody_invalidException_400() {
        FakeData fakeData = new FakeData();
        String createPetInvalidIdBody = fakeData.createFakePetInvalidId();
        Response response = given()
                .contentType(ContentType.JSON)
                .header(ACCEPT, NAME)
                .header(CONTENT_TYPE, NAME)
                .body(createPetInvalidIdBody)
                .when()
                .post(CREATE_PET_ENDPOINT)
                .then()
                .extract()
                .response();

        if (response.statusCode() == 400) {
            ErrorResponseModel errorResponse = new Gson().fromJson(response.body().asString(), ErrorResponseModel.class);
            printMessage("Status Code: " + response.statusCode());
            printMessage("Error Message: " + errorResponse.getMessage());
            printMessage("Invalid Input Id is not correct..");
        } else {
            printMessage("Status Code: " + response.statusCode());
        }
    }



    @Test(priority = 8)
    public void getPet_successfully_200() {
        Response response = given().contentType(ContentType.JSON)
                .header(ACCEPT, NAME)
                .header(CONTENT_TYPE, NAME)
                .when()
                .get(GET_PET_ENDPOINT, petId)
                .then()
                .extract()
                .response();
        if (response.statusCode() == 200) {
            CreatePetModel data = new Gson().fromJson(response.body().asString(), CreatePetModel.class);
            System.out.println(data.getName() + " GET PET FOUND");
            createPetModel = data;
        }else {
            System.out.println("Error: Pet Not Found  " + response.statusCode());
        }
    }

    @Test(priority = 9)
    public void getPet_invalidID_PetNotFound_404() {
        Response response = given().contentType(ContentType.JSON)
                .header(ACCEPT, NAME)
                .header(CONTENT_TYPE, NAME)
                .when().get(GET_PET_ENDPOINT, 010f) // here invalid id supplied so pet not found
                .then().extract()
                .response();
        if (response.statusCode() == 404) {
            ErrorResponseModel errorResponse = new Gson().fromJson(response.body().asString(), ErrorResponseModel.class);
            System.out.println("Status Code: Correct " + response.statusCode());
            System.out.println("Error Type: " + errorResponse.getType());
            System.out.println("Error Message: " + errorResponse.getMessage());
        }else{
            System.out.println("Status Code: Correct " + response.statusCode());
        }
    }


    @Test(priority = 10)
    public void updatePet_successfully_200() {
       String previousDogName =  createPetModel.getName();
        createPetModel.setName("Tommy"); // dog name is updated
        Response response = given().contentType(ContentType.JSON)
                .header(ACCEPT, NAME)
                .header(CONTENT_TYPE, NAME)
                .body(createPetModel).when()
                .put("/pet").then()
                .extract().response();

        if (response.statusCode() == 200) {
            CreatePetModel data = new Gson().fromJson(response.body().asString(), CreatePetModel.class);
            if (data.getName() != null) {
                printMessage("previousDogName is: "+previousDogName+" Now is updated "+data.getName() + " Dog Name Is updated...");
            } else {
                printMessage(data.getName() + " Failed due to null name of pet");
            }
        }
    }

//
    @Test(priority = 11)
    public void updatePet_InvalidException_405() {

        Response response = given().contentType(ContentType.JSON)
                .header(ACCEPT, NAME)
                .header(CONTENT_TYPE, NAME)
                .body("").when()
                .put(UPDATE_PET_ENDPOINT)
                .then()
                .extract()
                .response();
        if (response.statusCode() == 405) {
            ErrorResponseModel errorResponse = new Gson().fromJson(response.body().asString(), ErrorResponseModel.class);
            printMessage("Status Code: " + response.statusCode());
            printMessage("Error Type: " + "Pet not found");
            printMessage("Error Message: " + errorResponse.getMessage());
        } else {
            printMessage("Error: Found  " + response.getStatusCode());
        }
    }

    @Test(priority = 12)
    public void updatePet_InvalidId_400() {
        FakeData fakeData = new FakeData();
        String createPetInvalidIdBody = fakeData.createFakePetInvalidId();
        Response response = given().contentType(ContentType.JSON)
                .header(ACCEPT, NAME)
                .header(CONTENT_TYPE, NAME)
                .body(createPetInvalidIdBody).when().put(UPDATE_PET_ENDPOINT)
                .then().extract().response();
        if (response.statusCode() == 400) {
            ErrorResponseModel errorResponse = new Gson().fromJson(response.body().asString(), ErrorResponseModel.class);
            printMessage("Status Code: " + response.statusCode());
            printMessage("Error Type: " + "Pet not found");
            printMessage("Error Message: " + errorResponse.getMessage());
        } else {
            printMessage("Error: Found  " + response.getStatusCode());
        }
    }

    @Test(priority = 13)
    public void deletePet_successfully_200() {
        Response response = given().contentType(ContentType.JSON)
                .header(ACCEPT, NAME)
                .header(CONTENT_TYPE, NAME)
                .when().delete(DELETE_PET_ENDPOINT, petId)
                .then().extract()
                .response();
        if (response.statusCode() == 200) {
            printMessage( " Delete PET Succesfully");
            printMessage(response.statusCode() + " Delete PET Succesfully");

        }
    }

//
    @Test(priority = 14)
    public void deletePet_InvalidId_400() {
        FakeData fakeData = new FakeData();
        String createPetInvalidBody = fakeData.createFakePetInvalidId();

        Response response = given().contentType(ContentType.JSON)
                .header(ACCEPT, NAME)
                .header(CONTENT_TYPE, NAME)
                .body(createPetInvalidBody).when()
                .put("/pet").then().extract().response();
        if (response.statusCode() == 400) {
            ErrorResponseModel errorResponse = new Gson().fromJson(response.body().asString(), ErrorResponseModel.class);
            printMessage("Status Code: " + response.statusCode());
            printMessage("Error Type: " + errorResponse.getType());
            printMessage("Error Message: " + "Invalid ID supplied");
        } else {
            printMessage("Error: Found  " + response.getStatusCode());
        }
    }

    private void printMessage(String message) {
        System.out.println(message);
    }

}
