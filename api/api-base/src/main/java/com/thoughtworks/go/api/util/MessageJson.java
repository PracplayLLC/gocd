/*
 * Copyright 2018 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.thoughtworks.go.api.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.thoughtworks.go.api.base.JsonOutputWriter;
import com.thoughtworks.go.api.base.OutputWriter;

import java.io.StringWriter;

public class MessageJson {

    private final String message;
    private final JsonNode data;

    private MessageJson(String message, JsonNode data) {
        this.message = message;
        this.data = data;
    }

    public static String create(String message, JsonNode data) {
        return new MessageJson(message, data).toString();
    }

    public static String create(String message) {
        return create(message, null);
    }

    public String toString() {
        StringWriter buffer = new StringWriter(1024);
        new JsonOutputWriter(buffer, null).forTopLevelObject((OutputWriter writer) -> {
            writer.add("message", message);
            if (data != null) {
                writer.add("data", data);
            }
        });

        return buffer.toString();
    }
}
