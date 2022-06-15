package fr.maygo.citeinfini.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CiteInfinieClientTest {

    @Test
    public void testUserAgent(){
        assertEquals(CiteInfinieClient.USER_AGENT, "CiteInfinieAPI/1.0");
    }

    @Test
    public void testDateFormat(){
        assertEquals(CiteInfinieClient.DATE_FORMAT.toPattern(), "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    }

}
