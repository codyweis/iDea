package apps.luck3y.com.idea;

import android.app.DownloadManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Cody Weisenberger on 2/24/2016.
 */
public class SessionId extends AppCompatActivity{

    String id;

    public String getSessionId(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Config.SERVER_ADDRESS + "GetSessionId.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject = null;
                        try{
                            jsonObject = new JSONObject(response);

                            id = response;
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        return id;
    }

}
