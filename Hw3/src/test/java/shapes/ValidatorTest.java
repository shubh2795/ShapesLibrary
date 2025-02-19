package shapes;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ValidatorTest {

    @Test
    public void testValidateDouble() throws ShapeException {
        Validator.validateDouble(123, "Test message");
        Validator.validateDouble(0.00123, "Test message");
        Validator.validateDouble(-123, "Test message");
        Validator.validateDouble(-0.00123, "Test messagee");

        try {
            Validator.validateDouble(Double.POSITIVE_INFINITY,"Invalid");
            fail("Expected exception not thrown");
        } catch (ShapeException ex) {
            assertEquals("Invalid", ex.getMessage());
        }

        try {
            Validator.validateDouble(Double.NEGATIVE_INFINITY,"Invalid");
            fail("Expected exception not thrown");
        } catch (ShapeException ex) {
            assertEquals("Invalid", ex.getMessage());
        }

        try {
            Validator.validateDouble(Double.NaN,"Invalid");
            fail("Expected exception not thrown");
        } catch (ShapeException ex) {
            assertEquals("Invalid", ex.getMessage());
        }
    }

    @Test
    public void testValidatePositiveDouble()  throws ShapeException {
        Validator.validatePositiveDouble(456, "Test message");
        Validator.validatePositiveDouble(0.34523, "Test message");

        try {
            Validator.validatePositiveDouble(-123, "Negative not allowed");
            fail("Expected exception not thrown");
        } catch (ShapeException ex) {
            assertEquals("Negative not allowed", ex.getMessage());
        }

        try {
            Validator.validatePositiveDouble(-0.123, "Negative not allowed");
            fail("Expected exception not thrown");
        } catch (ShapeException ex) {
            assertEquals("Negative not allowed", ex.getMessage());
        }

        try {
            Validator.validatePositiveDouble(Double.POSITIVE_INFINITY,"Invalid");
            fail("Expected exception not thrown");
        } catch (ShapeException ex) {
            assertEquals("Invalid", ex.getMessage());
        }

        try {
            Validator.validatePositiveDouble(Double.NEGATIVE_INFINITY,"Invalid");
            fail("Expected exception not thrown");
        } catch (ShapeException ex) {
            assertEquals("Invalid", ex.getMessage());
        }

        try {
            Validator.validatePositiveDouble(Double.NaN,"Invalid");
            fail("Expected exception not thrown");
        } catch (ShapeException ex) {
            assertEquals("Invalid", ex.getMessage());
        }
    }
    //TODO:: Test these
    @Test
    public void validateTriangleColinearity() {

    }

    @Test
    public void validateRectanglePerpendicularLines() {
    }
}