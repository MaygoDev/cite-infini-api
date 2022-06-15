package fr.maygo.citeinfini.api.obj.teams;

import com.google.gson.JsonObject;
import fr.maygo.citeinfini.api.CiteInfinieClient;
import fr.maygo.citeinfini.api.obj.user.CiteInfinieSimpleUser;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.Date;

@Data
public final class CiteInfinieTeamMember {

    private final CiteInfinieTeamMemberRole role;
    private final Date joined;
    private final int id;
    private final CiteInfinieSimpleUser user;

    @SneakyThrows
    public static CiteInfinieTeamMember from(JsonObject memberObj) {
        if(memberObj == null) return null;
        return new CiteInfinieTeamMember(CiteInfinieTeamMemberRole.valueOf(memberObj.get("role").getAsString().toUpperCase()),
                CiteInfinieClient.DATE_FORMAT.parse(memberObj.get("createdAt").getAsString()),
                memberObj.get("id").getAsInt(),
                CiteInfinieSimpleUser.from(memberObj.get("user").getAsJsonObject()));
    }

    public enum CiteInfinieTeamMemberRole {
        CAPTAIN,
        CO_CAPTAIN,
        GUEST;
    }

}
