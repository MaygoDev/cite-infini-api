package fr.maygo.citeinfini.api;

import fr.maygo.citeinfini.api.obj.teams.CiteInfinieTeamMember;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CiteInfinieRolesTest {

    @Test
    public void testRoles(){
        assertEquals(CiteInfinieTeamMember.CiteInfinieTeamMemberRole.values().length, 3);
    }

}
