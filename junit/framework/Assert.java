package junit.framework;

public class Assert {
    protected Assert() {
    }

    public static void assertTrue(String message, boolean condition) {
        if (!condition) {
            fail(message);
        }
    }

    public static void assertTrue(boolean condition) {
        assertTrue((String) null, condition);
    }

    public static void assertFalse(String message, boolean condition) {
        assertTrue(message, !condition);
    }

    public static void assertFalse(boolean condition) {
        assertFalse((String) null, condition);
    }

    public static void fail(String message) {
        throw new AssertionFailedError(message);
    }

    public static void fail() {
        fail((String) null);
    }

    public static void assertEquals(String message, Object expected, Object actual) {
        if (expected != null || actual != null) {
            if (expected == null || !expected.equals(actual)) {
                failNotEquals(message, expected, actual);
            }
        }
    }

    public static void assertEquals(Object expected, Object actual) {
        assertEquals((String) null, expected, actual);
    }

    public static void assertEquals(String message, String expected, String actual) {
        if (expected != null || actual != null) {
            if (expected == null || !expected.equals(actual)) {
                throw new ComparisonFailure(message, expected, actual);
            }
        }
    }

    public static void assertEquals(String expected, String actual) {
        assertEquals((String) null, expected, actual);
    }

    public static void assertEquals(String message, double expected, double actual, double delta) {
        if (Double.compare(expected, actual) != 0 && Math.abs(expected - actual) > delta) {
            failNotEquals(message, new Double(expected), new Double(actual));
        }
    }

    public static void assertEquals(double expected, double actual, double delta) {
        assertEquals((String) null, expected, actual, delta);
    }

    public static void assertEquals(String message, float expected, float actual, float delta) {
        if (Float.compare(expected, actual) != 0 && Math.abs(expected - actual) > delta) {
            failNotEquals(message, new Float(expected), new Float(actual));
        }
    }

    public static void assertEquals(float expected, float actual, float delta) {
        assertEquals((String) null, expected, actual, delta);
    }

    public static void assertEquals(String message, long expected, long actual) {
        assertEquals(message, (Object) new Long(expected), (Object) new Long(actual));
    }

    public static void assertEquals(long expected, long actual) {
        assertEquals((String) null, expected, actual);
    }

    public static void assertEquals(String message, boolean expected, boolean actual) {
        assertEquals(message, (Object) Boolean.valueOf(expected), (Object) Boolean.valueOf(actual));
    }

    public static void assertEquals(boolean expected, boolean actual) {
        assertEquals((String) null, expected, actual);
    }

    public static void assertEquals(String message, byte expected, byte actual) {
        assertEquals(message, (Object) new Byte(expected), (Object) new Byte(actual));
    }

    public static void assertEquals(byte expected, byte actual) {
        assertEquals((String) null, expected, actual);
    }

    public static void assertEquals(String message, char expected, char actual) {
        assertEquals(message, (Object) new Character(expected), (Object) new Character(actual));
    }

    public static void assertEquals(char expected, char actual) {
        assertEquals((String) null, expected, actual);
    }

    public static void assertEquals(String message, short expected, short actual) {
        assertEquals(message, (Object) new Short(expected), (Object) new Short(actual));
    }

    public static void assertEquals(short expected, short actual) {
        assertEquals((String) null, expected, actual);
    }

    public static void assertEquals(String message, int expected, int actual) {
        assertEquals(message, (Object) new Integer(expected), (Object) new Integer(actual));
    }

    public static void assertEquals(int expected, int actual) {
        assertEquals((String) null, expected, actual);
    }

    public static void assertNotNull(Object object) {
        assertNotNull((String) null, object);
    }

    public static void assertNotNull(String message, Object object) {
        assertTrue(message, object != null);
    }

    public static void assertNull(Object object) {
        assertNull("Expected: <null> but was: " + String.valueOf(object), object);
    }

    public static void assertNull(String message, Object object) {
        assertTrue(message, object == null);
    }

    public static void assertSame(String message, Object expected, Object actual) {
        if (expected != actual) {
            failNotSame(message, expected, actual);
        }
    }

    public static void assertSame(Object expected, Object actual) {
        assertSame((String) null, expected, actual);
    }

    public static void assertNotSame(String message, Object expected, Object actual) {
        if (expected == actual) {
            failSame(message);
        }
    }

    public static void assertNotSame(Object expected, Object actual) {
        assertNotSame((String) null, expected, actual);
    }

    public static void failSame(String message) {
        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }
        fail(formatted + "expected not same");
    }

    public static void failNotSame(String message, Object expected, Object actual) {
        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }
        fail(formatted + "expected same:<" + expected + "> was not:<" + actual + ">");
    }

    public static void failNotEquals(String message, Object expected, Object actual) {
        fail(format(message, expected, actual));
    }

    public static String format(String message, Object expected, Object actual) {
        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }
        return formatted + "expected:<" + expected + "> but was:<" + actual + ">";
    }
}
