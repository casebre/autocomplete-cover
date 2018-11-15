package com.cover.technicalassessment.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringValidationUtilTest {

    @Test
    public void hasMinimumRequiredTrue() {
        String test = "123 Main Street";
        int minRequired = 8;

        boolean result = StringValidationUtil.hasMinimumRequired(test, minRequired);
        assertTrue(result);
    }

    @Test
    public void hasMinimumRequiredFalse() {
        String test = "123 Ma";
        int minRequired = 8;

        boolean result = StringValidationUtil.hasMinimumRequired(test, minRequired);
        assertFalse(result);
    }


    @Test
    public void filterStringList_empty() {
        List<String> list = new LinkedList<>();
        String keyword = "";
        List<String> filteredList = StringValidationUtil.filterStringList(list, keyword);
        Assert.assertEquals(list, filteredList);
    }


    @Test
    public void filterStringList_filtered() {
        List<String> list = Arrays.asList(getInsurerList());

        String keyword = "Zur";
        String[] expected = {"Zurich"};

        List<String> filteredList = StringValidationUtil.filterStringList(list, keyword);
        Assert.assertEquals(expected, filteredList.toArray());
    }

    @Test
    public void filterStringList_filtered_American() {
        List<String> list = Arrays.asList(getInsurerList());

        String keyword = "American";
        String[] expected = new String[] {
                "American Commerce",
                "American Family",
                "American Freedom Insurance Company",
                "American National",
                "First American",
                "Great American"};

        List<String> filteredList = StringValidationUtil.filterStringList(list, keyword);
        Assert.assertEquals(expected, filteredList.toArray());
    }

    @Test
    public void filterStringList_filtered_AmericanSize() {
        List<String> list = Arrays.asList(getInsurerList());

        String keyword = "American";

        List<String> filteredList = StringValidationUtil.filterStringList(list, keyword);
        Assert.assertEquals(6, filteredList.size());
    }

    @Test
    public void matchAny_empty() {
        List<String> list = Arrays.asList(getInsurerList());

        String keyword = "";

        boolean match = StringValidationUtil.matchesAny(list, keyword);
        Assert.assertFalse(match);
    }

    @Test
    public void matchAny_partial() {
        List<String> list = Arrays.asList(getInsurerList());

        String keyword = "Gei";

        boolean match = StringValidationUtil.matchesAny(list, keyword);
        Assert.assertFalse(match);
    }

    @Test
    public void matchAny_validString() {
        List<String> list = Arrays.asList(getInsurerList());

        String keyword = "Geico";

        boolean match = StringValidationUtil.matchesAny(list, keyword);
        Assert.assertTrue(match);
    }


    public String[] getInsurerList() {
        return new String[] { "21st Century",
                "A Central",
                "AAA",
                "AARP",
                "Acadia",
                "Access General",
                "Ace",
                "Acuity",
                "Adirondack Ins Exchange",
                "Aegis",
                "Affirmative",
                "AIC",
                "AIG",
                "Alfa Alliance",
                "Allied",
                "Allstate",
                "America First",
                "American Commerce",
                "American Family",
                "American Freedom Insurance Company",
                "American National",
                "Amerisure",
                "Amica",
                "Anchor General",
                "Arrowhead",
                "ASI Lloyds",
                "Atlantic Mutual",
                "Austin Mutual",
                "Autoone",
                "Auto Owners",
                "Auto Tex",
                "Badger Mutual",
                "Balboa",
                "Bankers",
                "Beacon National",
                "Bear River Mutual",
                "Brethern Mutual",
                "Bristol West",
                "Buckeye",
                "California Casualty",
                "Cameron Mutual",
                "Capital Insurance Group",
                "Celina",
                "Centennial",
                "Central Mutual of OH",
                "Charter",
                "Chubb",
                "Cincinnati",
                "Citizens",
                "CNA",
                "Colonial Penn",
                "Colorado Casualty",
                "Columbia",
                "Commerce West",
                "Constitutional Casualty",
                "Consumers",
                "Cornerstone",
                "Countrywide",
                "Country Insurance",
                "CSE",
                "Cumberland",
                "Dairyland",
                "Deerbrook",
                "Delta LloydsI nsurance Company",
                "Depositors",
                "Direct",
                "Direct General",
                "Discovery",
                "Donegal",
                "Drive",
                "Electric",
                "EMC",
                "Encompass",
                "Erie",
                "Esurance",
                "Eveready",
                "Explorer",
                "Farm Bureau",
                "Farmers",
                "Federated",
                "Fidelity",
                "Financial Indemnity",
                "Firemans Fund",
                "First Acceptance",
                "First American",
                "First Auto",
                "First Chicago",
                "First Connect",
                "Flagship Insurance",
                "Foremost",
                "Founders",
                "Frankenmuth",
                "Fred Loya",
                "Gateway",
                "Geico",
                "General Casualty",
                "Germantown Mutual",
                "GMAC",
                "Grange",
                "Great American",
                "GREGo America",
                "Grinnell",
                "Guide One",
                "Hallmark Insurance Company",
                "Hanover",
                "Harbor",
                "Harleysville",
                "Hartford OMNI",
                "Hartford",
                "Hastings Mutual",
                "Hawkeye Security",
                "HDI",
                "Horace Mann",
                "Houston General",
                "IFA",
                "Imperial Casualty",
                "IMT Ins",
                "Indiana Farmers",
                "Indiana",
                "Infinity",
                "Insuremax",
                "Insurequest",
                "Integon",
                "Integrity",
                "Kemper",
                "Kingsway",
                "Liberty Mutual",
                "Liberty Northwest",
                "MAIF",
                "Main Street America",
                "Mapfre",
                "Markel",
                "Maryland Auto Insurance",
                "Mendakota",
                "Mendota",
                "Merchants Group",
                "Mercury",
                "Met Life",
                "Metropolitan",
                "Mid Continent",
                "Midwestern Indemnity",
                "Montgomery",
                "Motorists Mutual",
                "MSA",
                "Mt Washington",
                "Mutual Benefit",
                "Mutual of Enumclaw",
                "National Lloyds Insurance Company",
                "Nationwide",
                "National General",
                "New York Central Mutual",
                "NJ Manufacturers",
                "NJ Skylands",
                "Nodak Mutual",
                "Northstar",
                "NYAIP",
                "Occidental",
                "Ocean Harbor",
                "Ohio Casualty",
                "Omaha PC",
                "Omni Insurance Co",
                "One Beacon",
                "Oregon Mutual",
                "Palisades",
                "Patriot",
                "Patrons Oxford",
                "Peerless Montgomery",
                "Pekin",
                "Pemco",
                "Penn National",
                "Phoenix Indemnity",
                "Plymouth Rock",
                "Preferred Mutual",
                "Proformance",
                "Progressive",
                "Prudential",
                "Republic",
                "Response",
                "Rockford Mutual",
                "Royaland Sun Alliance",
                "Safeco",
                "Safe Auto",
                "Safeway",
                "Sagamore",
                "SECURA",
                "Selective",
                "Sentry Ins",
                "Shelter Insurance",
                "Southern County",
                "Southern Mutual",
                "Southern Trust",
                "St Paul Travelers",
                "Standard Mutual",
                "Star Casualty",
                "State Auto",
                "State Farm",
                "Still Water",
                "Stonegate",
                "Titan",
                "Topa",
                "Tower",
                "Travelers",
                "TWFG",
                "Unigard",
                "United Automobile",
                "United Fire and Casualty",
                "Unitrin",
                "Universal",
                "USAA",
                "Utica National",
                "Victoria",
                "West Bend",
                "Western National",
                "Western Reserve Group",
                "Westfield",
                "White Mountains",
                "Wilshire",
                "Wilson Mutual",
                "Wisconsin Mutual",
                "Windsor",
                "Wind Haven",
                "Zurich"};
    }

}
