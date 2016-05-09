package cz.zcu.AntPlus2NIXConverter.tests;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.g_node.nix.valid.Result;
import org.g_node.nix.valid.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;
import cz.zcu.AntPlus2NIXConverter.Profiles.AntBikePower;
import cz.zcu.AntPlus2NIXConverter.Profiles.AntBloodPressure;

public class AntBloodPressureTest {

	AntBloodPressure blPres;

	@Before
	public void setUp() {
		blPres = new AntBloodPressure(new int[] { 43 }, new int[] { 3, 5, 67, 3 }, new int[] { 4, 5, 6, 4 },
				new GregorianCalendar[] {}, new OdMLData(33, 23, 4, 5, 2, 4, 4, 2, 5));
		blPres.createNixFile("testovaci.h5");
	}
	
	@After
	public void tearDown(){
		String location = blPres.getFile().getLocation();
		
		blPres.getFile().close();
		
		java.io.File f = new java.io.File(location);
		f.delete();
	}
	
	
	@Test
	public void testValidate() {
		Result result = Validator.validate(blPres.getBlock());

		assertTrue(result.getErrors().size() == 0);
		assertTrue(result.getWarnings().size() == 0);
	}

	@Test
	public void testName() {
		assertEquals(blPres.getBlock().getName(), "recording" + blPres.getIndex());
	}

	@Test
	public void testType() {
		assertEquals(blPres.getBlock().getType(), "recording");
	}

	@Test
	public void testMetadataAccess() {

		assertNotNull(blPres.getBlock().getMetadata());

	}


	@Test
    public void testValidateArrayDistolic() {
        Result result = Validator.validate(blPres.getDataDistolic());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }

	@Test
    public void testId() {
        assertEquals(blPres.getDataDistolic().getId().length(), 36);
    }

    @Test
    public void testNameArray() {
        assertEquals(blPres.getDataDistolic().getName(), "diastolicBloodPress1");
    }

    @Test
    public void testTypeArray() {
        assertEquals(blPres.getDataDistolic().getType(), "antMessage");
    }
    
	@Test
	public void testDataArrayAccess() {

		assertEquals(blPres.getBlock().getDataArrayCount(), 4);
	}
	
	@Test
    public void testValidateArraySystolic() {
        Result result = Validator.validate(blPres.getDataSystolic());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }

	@Test
    public void testIdSystolic() {
        assertEquals(blPres.getDataSystolic().getId().length(), 36);
    }

    @Test
    public void testNameArraySystolic() {
        assertEquals(blPres.getDataSystolic().getName(), "systolicBloodPress1");
    }

    @Test
    public void testTypeArraySystolic() {
        assertEquals(blPres.getDataSystolic().getType(), "antMessage");
    }
    
    
	
	@Test
    public void testValidateArrayHeart() {
        Result result = Validator.validate(blPres.getDataHeartRate());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }

	@Test
    public void testIdHeart() {
        assertEquals(blPres.getDataHeartRate().getId().length(), 36);
    }

    @Test
    public void testNameArrayHeart() {
        assertEquals(blPres.getDataHeartRate().getName(), "heartRate1");
    }

    @Test
    public void testTypeArrayHeart() {
        assertEquals(blPres.getDataHeartRate().getType(), "antMessage");
    }
    
	@Test
    public void testValidateArrayStamp() {
        Result result = Validator.validate(blPres.getDataTime());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }

	@Test
    public void testIdStamp() {
        assertEquals(blPres.getDataTime().getId().length(), 36);
    }

    @Test
    public void testNameArrayStamp() {
        assertEquals(blPres.getDataTime().getName(), "timestamp1");
    }

    @Test
    public void testTypeArrayStamp() {
        assertEquals(blPres.getDataTime().getType(), "antMessage");
    }
    
    @Test
    public void testValidateSource() {
        Result result = Validator.validate(blPres.getSource());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }

    @Test
    public void testIdSource() {
        assertEquals(blPres.getSource().getId().length(), 36);
    }

    @Test
    public void testNameSource() {
        assertEquals(blPres.getSource(), "bloodPressure");
    }

    @Test
    public void testTypeSource() {
        assertEquals(blPres.getSource().getType(), "antMessage");
    }
    
    @Test
    public void testValidateSection() {
        Result result = Validator.validate(blPres.getSection());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }

    @Test
    public void testIdSection() {
        assertEquals(blPres.getSection().getId().length(), 36);
    }

    @Test
    public void testNameSection() {
        assertEquals(blPres.getSection().getName(), "AntMetaData");
    }

    @Test
    public void testTypeSection() {
        assertEquals(blPres.getSection().getType(), "metadata");
    }

}
