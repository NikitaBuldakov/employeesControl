
public class FeedbackTests {

    /*
    private GregorianCalendar dateOfCreating;
    private Feedback feedback;
    List<Feedback> feedbackList;

    @BeforeClass
    public static void init(){
        JDBCPostgreSQLConnector.initConnection();
    }

    @Before
    public void setUp(){
        dateOfCreating = new GregorianCalendar(2000, Calendar.APRIL, 24);
        feedback = new Feedback("All cool",dateOfCreating.getTime());
        feedbackList = new ArrayList<>();
    }

    @AfterClass
    public static void destroy() throws SQLException {
        JDBCPostgreSQLConnector.closeConnection();
    }

    @Test
    public void testAddedNewFeedback(){
        feedback.addNewFeedback();
        Feedback feedbackLastAdded = new Feedback();
        feedbackLastAdded.getLast();
        assertEquals(feedbackLastAdded.getId(),feedback.getId());
    }

    @Test
    public void testSelectFeedback(){
        feedback.selectFeedBack(2);
        assertEquals(2, feedback.getId());
    }

    @Test
    public void testDeleteFeedback(){
        feedback.addNewFeedback();
        feedback.getLast();
        assertTrue(feedback.deleteFeedback());
    }

    @Test
    public void tetsUpdateFeedback(){
        feedback.addNewFeedback();
        feedback.getLast();
        feedback.setDescription("All bad");
        assertTrue(feedback.updateFeedBack());
    }

    @Test
    public void testSelectAllFeedback(){
        feedbackList = feedback.selectAll();
        assertNotNull(feedbackList);
    }

     */
}
