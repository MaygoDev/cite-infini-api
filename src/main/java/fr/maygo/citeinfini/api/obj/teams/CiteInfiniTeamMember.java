package fr.maygo.citeinfini.api.obj.teams;

import com.google.gson.JsonObject;
import fr.maygo.citeinfini.api.CiteInfiniClient;
import fr.maygo.citeinfini.api.obj.user.CiteInfiniSimpleUser;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.Date;

@Data
public final class CiteInfiniTeamMember {

    private final CiteInfiniTeamMemberRole role;
    private final Date joined;
    private final int id;
    private final CiteInfiniSimpleUser user;

    @SneakyThrows
    public static CiteInfiniTeamMember from(JsonObject memberObj) {
        if(memberObj == null) return null;
        return new CiteInfiniTeamMember(CiteInfiniTeamMemberRole.valueOf(memberObj.get("role").getAsString().toUpperCase()),
                CiteInfiniClient.DATE_FORMAT.parse(memberObj.get("createdAt").getAsString()),
                memberObj.get("id").getAsInt(),
                CiteInfiniSimpleUser.from(memberObj.get("user").getAsJsonObject()));
    }

    public enum CiteInfiniTeamMemberRole {
        CAPTAIN,
        CO_CAPTAIN,
        GUEST;
    }

}
