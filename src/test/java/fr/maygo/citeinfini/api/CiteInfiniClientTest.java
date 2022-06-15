package fr.maygo.citeinfini.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CiteInfiniClientTest {

    @Test
    public void testUserAgent(){
        assertEquals(CiteInfiniClient.USER_AGENT, "CiteInfiniAPI/1.0");
    }

    @Test
    public void testDateFormat(){
        assertEquals(CiteInfiniClient.DATE_FORMAT.toPattern(), "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    }

}
