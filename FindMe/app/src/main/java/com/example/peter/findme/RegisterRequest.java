package com.example.peter.findme;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.lang.*;
import java.util.*;
/**
 * Created by Peter on 10/29/16.
 */
public class RegisterRequest extends StringRequest
{
    private static final String REGISTER_REQUEST_URL = "http://findme.webutu.com/Register.php";
    private Map<String, String> params;
    // constructor of RegisterRequest
    public RegisterRequest(String name, String username, String email, String password,
                           Response.Listener<String> listener)
    {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("username", username);
        params.put("password", password);
        params.put("email", email);
    }
    @Override
    public Map<String, String> getParams()
    {
        return this.params;
    }
    @Override
    public String toString()
    {
        return "In RegisterRequest: "+params.get("name")+"\n"+params.get("username");
    }

}
