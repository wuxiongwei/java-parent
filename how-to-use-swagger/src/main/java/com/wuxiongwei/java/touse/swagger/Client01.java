package com.wuxiongwei.java.touse.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.SwaggerParseResult;

/**
 * TODO <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2019-07-30  17:21
 * <p>
 * Company: 苏州渠成易销网络科技有限公司
 * <p>
 *
 * @author xiongwei.wu@clickpaas.com
 * @version 1.0.0
 */
public class Client01 {
    public static void main(String[] args) {
        String json = "{\n" +
                "  \"openapi\" : \"3.0.1\",\n" +
                "  \"info\" : {\n" +
                "    \"title\":\"title1111\",\n" +
                "    \"version\":\"1.0.1\"\n" +
                "  },\n" +
                "  \"paths\" : {\n" +
                "    \"/foo\" : {\n" +
                "      \"description\" : \"the foo path\",\n" +
                "      \"get\" : {\n" +
                "        \"parameters\" : [ {\n" +
                "          \"in\" : \"query\",\n" +
                "          \"name\":\"222\",\n" +
                "          \"schema\" : {\n" +
                "            \"type\" : \"integer\"\n" +
                "          }\n" +
                "        } ],\n" +
                "        \"responses\" : {\n" +
                "          \"200\" : {\n" +
                "            \"description\" : \"it worked\"\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";

        OpenAPIV3Parser parser = new OpenAPIV3Parser();
        SwaggerParseResult result = parser.readContents(json,null,null);
        OpenAPI openAPI = result.getOpenAPI();
        System.out.println(openAPI.getInfo().getTitle());
    }

}
