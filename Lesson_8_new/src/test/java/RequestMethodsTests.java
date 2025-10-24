import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RequestMethodsTests {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    public void testGet() {
        given()
                .param("foo1", "bar1")
                .param("foo2", "bar2")
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .body("url", containsString("https://postman-echo.com/get"))
                .body("headers", notNullValue());
    }

    @Test
    public void testPostJson() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"test\":\"value\"}")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("json.test", equalTo("value"))
                .body("url", equalTo("https://postman-echo.com/post"))
                .body("data.test", equalTo("value")) // Изменено здесь
                .body("headers", notNullValue());
    }

    @Test
    public void testPostForm() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("foo1", "bar1")
                .formParam("foo2", "bar2")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("form.foo1", equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"))
                .body("url", equalTo("https://postman-echo.com/post"))
                .body("headers", notNullValue());
    }

    @Test
    public void testPut() {
        given()
                .contentType(ContentType.TEXT)
                .body("test text")
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .body("data", equalTo("test text"))
                .body("url", equalTo("https://postman-echo.com/put"))
                .body("headers", notNullValue());
    }

    @Test
    public void testDelete() {
        given()
                .contentType(ContentType.TEXT)
                .body("test text")
                .when()
                .delete("/delete")
                .then()
                .statusCode(200)
                .body("data", equalTo("test text"))
                .body("url", equalTo("https://postman-echo.com/delete"))
                .body("headers", notNullValue());
    }
}