package cz.zcu.AntPlus2NIXConverter.tests;

import static org.junit.Assert.*;

import org.g_node.nix.valid.Result;
import org.g_node.nix.valid.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;
import cz.zcu.AntPlus2NIXConverter.Profiles.AntBikePower;

public class AntBikePowerTest {

	AntBikePower bikePower;

	@Before
	public void setUp() {
		bikePower = new AntBikePower(new double[] { 1.0, 3.2, 5.6, 6.8 }, new OdMLData(33, 23, 4, 5, 2, 4, 4, 2, 5));
		bikePower.createNixFile("testovaci.h5");
		bikePower.getFile().open("testovaci.h5");
		
	}

	@After
	public void tearDown(){
		
		String location = bikePower.getFile().getLocation();
		
		bikePower.getFile().close();
		
		java.io.File f = new java.io.File(location);
		f.delete();
	}
	
	@Test
	public void testValidate() {
		Result result = Validator.validate(bikePower.getBlock());

		assertTrue(result.getErrors().size() == 0);
		assertTrue(result.getWarnings().size() == 0);
	}

	@Test
	public void testName() {
		assertEquals(bikePower.getBlock().getName(), "recording" + bikePower.getIndex());
	}

	@Test
	public void testType() {
		assertEquals(bikePower.getBlock().getType(), "recording");
	}

	@Test
	public void testMetadataAccess() {

		assertNotNull(bikePower.getBlock().getMetadata());

	}

	@Test
	public void testDataArrayAccess() {

		assertEquals(bikePower.getBlock().getDataArrayCount(), 1);
	}
	
	@Test
    public void testValidateArray() {
        Result result = Validator.validate(bikePower.getDataArrayBikePower());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }

	@Test
    public void testId() {
        assertEquals(bikePower.getDataArrayBikePower().getId().length(), 36);
    }

    @Test
    public void testNameArray() {
        assertEquals(bikePower.getDataArrayBikePower().getName(), "powerOnly1");
    }

    @Test
    public void testTypeArray() {
        assertEquals(bikePower.getDataArrayBikePower().getType(), "antMessage");
    }
    
    @Test
    public void testValidateSource() {
        Result result = Validator.validate(bikePower.getSource());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }

    @Test
    public void testIdSource() {
        assertEquals(bikePower.getSource().getId().length(), 36);
    }

    @Test
    public void testNameSource() {
        assertEquals(bikePower.getSource().getName(), "bikePower" + bikePower.getIndex());
    }

    @Test
    public void testTypeSource() {
        assertEquals(bikePower.getSource().getType(), "antMessage");
    }
    
    @Test
    public void testValidateSection() {
        Result result = Validator.validate(bikePower.getSection());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }

    @Test
    public void testIdSection() {
        assertEquals(bikePower.getSection().getId().length(), 36);
    }

    @Test
    public void testNameSection() {
        assertEquals(bikePower.getSection().getName(), "AntMetaData");
    }

    @Test
    public void testTypeSection() {
        assertEquals(bikePower.getSection().getType(), "metadata");
    }
    
}
