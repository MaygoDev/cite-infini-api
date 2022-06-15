package fr.maygo.citeinfini.api.obj.teams;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.maygo.citeinfini.api.CiteInfiniClient;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Data
public final class CiteInfiniTeam {

    private final int id;
    private final String name, tag;
    private final Date creation, lastUpdate;
    private final List<CiteInfiniTeamMember> members;
    private final boolean connectUserWaiting;

    @SneakyThrows
    public static CiteInfiniTeam from(JsonObject jsonObject){
        if(jsonObject == null) return null;
        final JsonObject attributesObj = jsonObject.get("attributes").getAsJsonObject();
        return new CiteInfiniTeam(jsonObject.get("id").getAsInt(),
                attributesObj.get("name").getAsString(),
                attributesObj.get("tag").getAsString(),
                CiteInfiniClient.DATE_FORMAT.parse(attributesObj.get("createdAt").getAsString()),
                CiteInfiniClient.DATE_FORMAT.parse(attributesObj.get("updatedAt").getAsString()),
                membersFromJson(attributesObj.get("teamMembers").getAsJsonArray()),
                attributesObj.get("connectUserWaiting").getAsBoolean());
    }

    private static List<CiteInfiniTeamMember> membersFromJson(JsonArray membersArray){
        return StreamSupport.stream(membersArray.spliterator(), false)
                .map(jsonElement -> CiteInfiniTeamMember.from(jsonElement.getAsJsonObject()))
                .collect(Collectors.toList());
    }
}
