package cz.zcu.AntPlus2NIXConverter.tests;

import static org.junit.Assert.*;

import org.g_node.nix.valid.Result;
import org.g_node.nix.valid.Validator;
import org.junit.Before;
import org.junit.Test;

import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;
import cz.zcu.AntPlus2NIXConverter.Profiles.AntBikePower;
import cz.zcu.AntPlus2NIXConverter.Profiles.AntBikeSpeed;

public class AntBikeSpeedTest {

	AntBikeSpeed bikeSpeed;
	@Before
	public void setUp() {
		bikeSpeed = new AntBikeSpeed(new int[] { 1, 3, 5, 6 }, new int[]{4,4,4,2},new OdMLData(33, 23, 4, 5, 2, 4, 4, 2, 5));
		bikeSpeed.createNixFile("testovaci.h5");
	}

	@Test
	public void testValidate() {
		Result result = Validator.validate(bikeSpeed.getBlock());

		assertTrue(result.getErrors().size() == 0);
		assertTrue(result.getWarnings().size() == 0);
	}

	@Test
	public void testName() {
		assertEquals(bikeSpeed.getBlock().getName(), "recording1");
	}

	@Test
	public void testType() {
		assertEquals(bikeSpeed.getBlock().getType(), "recording");
	}

	@Test
	public void testMetadataAccess() {

		assertNotNull(bikeSpeed.getBlock().getMetadata());

	}

	@Test
	public void testDataArrayAccess() {

		assertEquals(bikeSpeed.getBlock().getDataArrayCount(), 2);
	}
	
	@Test
    public void testValidateArrayCumWheel() {
        Result result = Validator.validate(bikeSpeed.getDataArrayCumWheelRew());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }
	
	@Test
    public void testValidateArrayLat() {
        Result result = Validator.validate(bikeSpeed.getDataArrayLatSpEvTime());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }

	@Test
    public void testIdCumWheel() {
        assertEquals(bikeSpeed.getDataArrayCumWheelRew().getId().length(), 36);
    }
	
	@Test
    public void testIdLat() {
        assertEquals(bikeSpeed.getDataArrayLatSpEvTime().getId().length(), 36);
    }

    @Test
    public void testNameArrayCumWheel() {
        assertEquals(bikeSpeed.getDataArrayCumWheelRew().getName(), "cumWheelRew1");
    }

    @Test
    public void testTypeArrayCumWheel() {
        assertEquals(bikeSpeed.getDataArrayCumWheelRew().getType(), "antMessage");
    }

    @Test
    public void testNameArrayLat() {
        assertEquals(bikeSpeed.getDataArrayLatSpEvTime().getName(), "latSpEvTime1");
    }

    @Test
    public void testTypeArrayLat() {
        assertEquals(bikeSpeed.getDataArrayLatSpEvTime().getType(), "antMessage");
    }
    @Test
    public void testValidateSource() {
        Result result = Validator.validate(bikeSpeed.getSource());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }

    @Test
    public void testIdSource() {
        assertEquals(bikeSpeed.getSource().getId().length(), 36);
    }

    @Test
    public void testNameSource() {
        assertEquals(bikeSpeed.getSource(), "bikeSpeed1");
    }

    @Test
    public void testTypeSource() {
        assertEquals(bikeSpeed.getSource().getType(), "antMessage");
    }
    
    @Test
    public void testValidateSection() {
        Result result = Validator.validate(bikeSpeed.getSection());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }

    @Test
    public void testIdSection() {
        assertEquals(bikeSpeed.getSection().getId().length(), 36);
    }

    @Test
    public void testNameSection() {
        assertEquals(bikeSpeed.getSection().getName(), "AntMetaData");
    }

    @Test
    public void testTypeSection() {
        assertEquals(bikeSpeed.getSection().getType(), "metadata");
    }
    

	
	
}
