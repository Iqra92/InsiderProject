package Automation;

import Automation.model.CreatePetModel;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.openqa.selenium.json.Json;

import java.util.List;

public class FakeData {

    private final Faker faker;

    public FakeData() {
        this.faker = new Faker();
    }

    public String createFakePetData() {
        CreatePetModel petModel = new CreatePetModel();

        petModel.setId(faker.number().randomNumber());

        // Create a fake category
        CreatePetModel.Category category = new CreatePetModel.Category();
        category.setId(faker.number().randomNumber());
        category.setName(faker.lorem().word());
        petModel.setCategory(category);

        petModel.setName(faker.animal().name());

        // Create fake photoUrls (in this example, only one photo URL is generated)
        petModel.setPhotoUrls(List.of(faker.internet().url()));

        // Create a fake tag
        CreatePetModel.Tag tag = new CreatePetModel.Tag();
        tag.setId(faker.number().randomNumber());
        tag.setName(faker.lorem().word());
        petModel.setTags(List.of(tag));

        petModel.setStatus("available");
        Gson gson = new Gson();


        String petModelBody = gson.toJson(petModel);

        System.out.println("Json :" + petModelBody);

        Gson builder = new GsonBuilder().setPrettyPrinting().create();
        String employeePrettyJsonPayload = builder.toJson(petModel);
        System.out.println("Pretty Json :" + employeePrettyJsonPayload);

        return petModelBody;
    }


    public String createFakePetInvalidId() {
        String createPetInvalidIdBody = "{\n" +
                "  \"id\": ,\n" +  // invalid id
                "  \"category\": {\n" +
                "    \"id\": 32100122,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"tommy\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 32100122,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        return createPetInvalidIdBody;
    }
}
