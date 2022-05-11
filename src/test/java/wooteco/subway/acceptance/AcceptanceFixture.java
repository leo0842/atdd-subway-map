package wooteco.subway.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.MediaType;
import wooteco.subway.dto.LineRequest;
import wooteco.subway.dto.LineResponse;
import wooteco.subway.dto.SectionRequest;

public class AcceptanceFixture {

    public static final Map<String, Object> 강남역_인자 = new HashMap<>() {{
        put("name", "강남역");
    }};
    public static final Map<String, Object> 역삼역_인자 = new HashMap<>() {{
        put("name", "역삼역");
    }};
    public static final Map<String, Object> 왕십리역_인자 = new HashMap<>() {{
        put("name", "왕십리역");
    }};
    public static final Map<String, Object> 서울숲역_인자 = new HashMap<>() {{
        put("name", "서울숲역");
    }};
    public static final Map<String, Object> LINE_2_인자 = new HashMap<>() {{
        put("name", "2호선");
        put("color", "초록이");
        put("upStationId", 1L);
        put("downStationId", 2L);
        put("distance", 5);
    }};
    public static final Map<String, Object> 경의중앙선_인자 = new HashMap<>() {{
        put("name", "경의중앙선");
        put("color", "하늘이");
    }};
    public static final String STATION_URL = "/stations";
    public static final String LINE_URL = "/lines";
    public static final String SECTION_URL = "/sections";
    public static final LineRequest LINE_2_요청 = new LineRequest("2호선", "초록이", 1L, 2L, 5);

    public static ExtractableResponse<Response> postMethodRequest(
            Map<String, Object> parameter, String path) {
        return RestAssured.given().log().all()
                .body(parameter)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post(path)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> postMethodRequest(
            LineRequest lineRequest, String path) {
        return RestAssured.given().log().all()
                .body(lineRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post(path)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> postMethodRequest(
            SectionRequest sectionRequest, String path) {
        return RestAssured.given().log().all()
                .body(sectionRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post(path)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> getMethodRequest(String path) {
        return RestAssured.given().log().all()
                .when()
                .get(path)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> putMethodRequest(Map<String, Object> parameter, String path) {
        return RestAssured.given().log().all()
                .body(parameter)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .put(path)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> deleteMethodRequest(String path) {
        return RestAssured.given().log().all()
                .when()
                .delete(path)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> deleteSectionMethodRequest(String path, int stationId) {
        return RestAssured.given().param("stationId", stationId).log().all()
                .when()
                .delete(path)
                .then().log().all()
                .extract();
    }

    public static List<Long> getExpectedLineIds(List<ExtractableResponse<Response>> createdResponses) {
        return createdResponses.stream()
                .map(it -> Long.parseLong(it.header("Location").split("/")[2]))
                .collect(Collectors.toList());
    }

    public static List<Long> getResultLineIds(ExtractableResponse<Response> response) {
        return response.jsonPath().getList(".", LineResponse.class).stream()
                .map(LineResponse::getId)
                .collect(Collectors.toList());
    }
}
