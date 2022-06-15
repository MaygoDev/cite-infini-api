package fr.maygo.citeinfini.api.obj.user;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Data;

@Data
public class CiteInfiniSimpleUser {

    private final int id;
    private final String minecraftUniqueId, minecraftUsername,
            discordId, discordUsername, discordAvatar;

    public static CiteInfiniSimpleUser from(JsonObject userObj) {
        if (userObj == null) return null;
        final JsonElement discordAvatar = userObj.get("discord_avatar");
        return new CiteInfiniSimpleUser(userObj.get("id").getAsInt(),
                userObj.get("minecraft_uuid").getAsString(),
                userObj.get("minecraft_username").getAsString(),
                userObj.get("discord_id").getAsString(),
                userObj.get("discord_username").getAsString(),
                discordAvatar.isJsonNull() ? null : discordAvatar.getAsString()); //Can be null
    }
}
