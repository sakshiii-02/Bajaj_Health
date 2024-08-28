package com.app;
import java.util.Random;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class HashGenerator {
	public static void main(String[] args) {
		        String PRNNumber = args[0].toLowerCase();
		        try {
		            JSONObject jsonObj = new JSONObject();
		            String destVal = traverseJson(jsonObj, "destination");
		            String random = generateRandomString(8);
		            String hash = generateHash(PRNNumber, destVal, random);
		            String result = hash + ";" + random;
		        } catch (Exception e) {
		            System.err.println("Error: " + e.getMessage());
		            System.exit(1);
		        }
		    }
		    private static String traverseJson(JSONObject jsonobj, String key) {
		        if (jsonobj.has(key)) {
		            return jsonobj.getString(key);
		        } else {
		            for (String jsonKey : jsonobj.keySet()) {
		                Object value = jsonobj.get(jsonKey);
		                if (value instanceof JSONObject) {
		                    String result = traverseJson((JSONObject) value, key);
		                    if (result != null) {
		                        return result;
		                    }
		                    for (Object val : (JSONArray) value) {
		                        if (val instanceof JSONObject) {
		                            String show = traverseJson((JSONObject) val, key);
		                            if (show != null) {
		                                return show;
		                            }
		                        }
		                    }
		                }
		            }
		        }
		        return null;
		    }
		    private static String generateRandomString(int length) {
		        String characters = "0123456789abcdefghijklmnopqrstuvwxyzQWERTYUIOPASDFGHJKLZXCVBNM";
		        StringBuilder randomString = new StringBuilder();
		        Random rnd = new Random();
		        for (int i = 0; i < length; i++) {
		            randomString.append(characters.charAt(rnd.nextInt(characters.length())));
		        }
		        return randomString.toString();
		    }
		    private static String generateHash(String prnnumber, String destval, String rndstring) {
				String concatenated = prnnumber + destval + rndstring;
		        return DigestUtils.md5Hex(concatenated);
		    }
}
	
