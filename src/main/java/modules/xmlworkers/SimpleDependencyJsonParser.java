package modules.xmlworkers;

import org.json.*;

import java.util.ArrayList;
import java.util.List;

public class SimpleDependencyJsonParser implements DependencyJsonParserInterface{
    @Override
    public List<String> getDependency(String jsonString) {
        JSONObject obj = new JSONObject(jsonString);
        JSONArray jsonArray = obj.getJSONObject("response").getJSONArray("docs");
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            ans.add(jsonArray.getJSONObject(i).getString("g"));
        }
        //System.out.println(ans);
        return ans;
    }
}
