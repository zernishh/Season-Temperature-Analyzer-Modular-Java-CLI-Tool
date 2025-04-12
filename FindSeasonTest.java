public class FindSeasonTest {

    public static void main(String[] args) {
        testFindSeason();
    }

    public static void testFindSeason() {
        System.out.println("Testing findSeason method...");

        // Test cases
        String result1 = SeasonModule.findSeason("Australia", "January"); // Valid country, valid month
        assert result1.equals("Summer") : "Test case 1 failed. Expected: Summer, Actual: " + result1;

        String result2 = SeasonModule.findSeason("Australia", "February"); // Valid country, valid month
        assert result2.equals("Summer") : "Test case 2 failed. Expected: Summer, Actual: " + result2;

        String result3 = SeasonModule.findSeason("Australia", "March"); // Valid country, valid month
        assert result3.equals("Summer") : "Test case 3 failed. Expected: Summer, Actual: " + result3;

        // Add more test cases for other scenarios as needed

        System.out.println("All test cases passed successfully.");
    }
}

