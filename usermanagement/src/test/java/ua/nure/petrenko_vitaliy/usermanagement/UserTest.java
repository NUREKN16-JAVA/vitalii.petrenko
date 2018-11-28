package ua.nure.petrenko_vitaliy.usermanagement;
//--
import static org.junit.Assert.assertEquals;
//--
import org.junit.Before;
import org.junit.Test;
//--
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UserTest {
    // test consts
    private static final int BIRTH_Y = 1998;

    private static final String FIRST_NAME = "TEST";
    private static final String LAST_NAME = "NAME";
    private static final String FULL_NAME = LAST_NAME + ", " + FIRST_NAME;

    private static final String BIRTHDAY_TEMPLATE = "13-10-1998";
    private static final String DATE_FORMAT = "dd-MM-yyyy";
    //--
    private User user;
    private Calendar calendar;
    private Date birthday;
    //-------------------------------
    @Before
    public void setUp() throws ParseException {
        user = new User(
            1L,
            FIRST_NAME,
            LAST_NAME,
            new SimpleDateFormat(DATE_FORMAT).parse(BIRTHDAY_TEMPLATE)
        );

        calendar = Calendar.getInstance();
    }
    //-------------------------------
    @Test
    public void testGetFullName() {
        String expect = FULL_NAME;
        String actual = user.getFullName();

        assertEquals(expect, actual);
    }
    //-------------------------------
    @Test
    public void test_age_1() {
        calendar.set(
            BIRTH_Y,
            calendar.get(Calendar.MONTH),
            1
        );
        birthday = calendar.getTime();
        user.setBirthday(birthday);

        int actual = user.getAge();
        int expect = 20;
        //result
        assertEquals(expect, actual);
    }
    //-------------------------------
    @Test
    public void test_age_2() {
        calendar.add(Calendar.MONTH, 12);
        calendar.set(
            BIRTH_Y,
            calendar.get(Calendar.MONTH),
            13
        );
        birthday = calendar.getTime();
        user.setBirthday(birthday);

        int actual = user.getAge();
        int expect = 21;
        //result
        assertEquals(expect, actual);
    }
    //-------------------------------
    @Test
    public void test_age_3() {
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(
            BIRTH_Y,
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        );
        birthday = calendar.getTime();
        user.setBirthday(birthday);

        int actual = user.getAge();
        int expect = 20;
        //result
        assertEquals(expect, actual);
    }
    //-------------------------------
    @Test
    public void test_age_4() {
        calendar.set(
            BIRTH_Y,
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        );
        birthday = calendar.getTime();
        user.setBirthday(birthday);

        int actual = user.getAge();
        int expect = 20;
        //
        assertEquals(expect, actual);
    }
    //-------------------------------
    @Test
    public void test_age_5() {
        calendar.set(BIRTH_Y, calendar.get(Calendar.MONTH) - 5, 20);
        birthday = calendar.getTime();
        user.setBirthday(birthday);

        int actual = user.getAge();
        int expect = 19;
        //result
        assertEquals(expect, actual);
    }
}