package fr.maygo.citeinfini.api;

import fr.maygo.citeinfini.api.obj.teams.CiteInfiniTeamMember;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CiteInfiniRolesTest {

    @Test
    public void testRoles(){
        assertEquals(CiteInfiniTeamMember.CiteInfiniTeamMemberRole.values().length, 3);
    }

}
