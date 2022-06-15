package fr.maygo.citeinfini.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.maygo.citeinfini.api.obj.teams.CiteInfinieTeam;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class CiteInfinieClient {

    private static final String ENDPOINT = "https://api.citedelinfini.fr/api/%s", //Endpoints
            TEAMS_ENDPOINT = String.format(ENDPOINT, "teams");
    public static final String USER_AGENT = "CiteInfinieAPI/1.0"; //Magic version

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private CiteInfinieClient(){
    }

    public static CiteInfinieClient newClient(){
        return new CiteInfinieClient();
    }

    public CompletableFuture<List<CiteInfinieTeam>> getTeamsInPage(int page){
        return CompletableFuture.supplyAsync(() -> {
            final String url = String.format(TEAMS_ENDPOINT+"?pagination[page]=%d", Math.max(page, 1));

            try {
                final Response response = Request.get(URI.create(url))
                        .userAgent(USER_AGENT)
                        .execute();

                final JsonObject jsonResponse = JsonParser.parseReader(new InputStreamReader(response.returnContent().asStream())).getAsJsonObject();
                final JsonElement dataElement = jsonResponse.get("data");
                if(!dataElement.isJsonNull()){
                    if(dataElement.isJsonArray()){
                        final JsonArray teamsArray = dataElement.getAsJsonArray();

                        final List<CiteInfinieTeam> teams = new ArrayList<>(teamsArray.size());
                        for (JsonElement jsonElement : teamsArray) {
                            final JsonObject teamObj = jsonElement.getAsJsonObject();

                            teams.add(CiteInfinieTeam.from(teamObj));
                        }

                        return teams;
                    }else{
                        throw new IllegalStateException("API returned data !instanceof JsonArray json="+ jsonResponse);
                    }
                }else{
                    throw new IllegalStateException("API returned data=null! json="+ jsonResponse);
                }
            } catch (IOException e) {
                throw new CompletionException(e);
            }
        }, EXECUTOR_SERVICE);
    }

}
