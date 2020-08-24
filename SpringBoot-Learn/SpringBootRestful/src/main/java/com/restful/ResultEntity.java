package com.restful;

import net.sf.json.JSONObject;

import java.io.Serializable;
import java.util.Map;

public class ResultEntity implements Serializable {

    JSONObject jsonObject;

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public ResultEntity() {
        this.jsonObject = new JSONObject();
    }

    public ResultEntity(JSONObject object) {
        this.jsonObject = object;
    }

    public ResultEntity(int code, String message) {
        this();
        this.jsonObject.put("code", code);
        this.jsonObject.put("message", message);
    }

    public ResultEntity(int code, String message, String description) {
        this();
        this.jsonObject.put("code", code);
        this.jsonObject.put("message", message);
        if (description != null) {
            this.jsonObject.put("description", description);
        }
    }

    public ResultEntity put(String key, Object value) {
        this.jsonObject.put(key, value);
        return this;
    }

    public ResultEntity put(Map<String, Object> map) {
        for (String key : map.keySet()) {
            this.jsonObject.put(key, map.get(key));
        }
        return this;
    }

    public ResultEntity putNest(String key, Object value) {
        if (!jsonObject.containsKey("data")) {
            JSONObject dataObj = new JSONObject();
            jsonObject.put("data", dataObj);
        }
        this.jsonObject.getJSONObject("data").put(key, value);
        return this;
    }

    public ResultEntity putNest(Map<String, Object> map) {
        if (!jsonObject.containsKey("data")) {
            JSONObject dataObj = new JSONObject();
            jsonObject.put("data", dataObj);
        }
        for (String key : map.keySet()) {
            this.jsonObject.getJSONObject("data").put(key, map.get(key));
        }
        return this;
    }

    public String toJsonString() {
        return this.jsonObject.toString();
    }

    public Map<String, Object> totMap() {
        return this.jsonObject;
    }
}