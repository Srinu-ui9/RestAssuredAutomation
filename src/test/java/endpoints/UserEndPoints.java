package endpoints;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.UserPOJO;

//To perform create, get, update and delete requests
public class UserEndPoints {

	public static Response createuser(UserPOJO payload) {

		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
			.when()
				.post(Routes.USER_POST);

		return response;
	}

	public static Response getuser(String username) {

		Response response = given()
				.pathParam("username" , username)
			.when()
				.get(Routes.USER_GET);
		return response;
	}

	public static Response updateuser(String username , UserPOJO payload) {

		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username" , username)
				.body(payload)
			.when()
				.put(Routes.USER_UPDATE);

		return response;
	}

	public static Response deleteuser(String username) {

		Response response = given()
				.pathParam("username" , username)
			.when()
				.delete(Routes.USER_DELETE);

		return response;
	}

}
