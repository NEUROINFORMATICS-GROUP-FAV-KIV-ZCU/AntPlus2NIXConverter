package cz.zcu.AntPlus2NIXConverter.tests;

import static org.junit.Assert.*;

import org.g_node.nix.valid.Result;
import org.g_node.nix.valid.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;
import cz.zcu.AntPlus2NIXConverter.Profiles.AntBikePower;
import cz.zcu.AntPlus2NIXConverter.Profiles.AntMultiSportSpeedDist;

public class AntMultiSportSpeedDistTest {

	AntMultiSportSpeedDist sportDist;

	@Before
	public void setUp() {
		sportDist = new AntMultiSportSpeedDist(new double[] { 1, 3, 5, 6 },new double[] { 1, 3, 5, 6 }, new OdMLData(33, 23, 4, 5, 2, 4, 4, 2, 5));
		sportDist.createNixFile("testovaci.h5");
	}

	@After
	public void tearDown(){
		String location =sportDist.getFile().getLocation();
		
		sportDist.getFile().close();
		
		java.io.File f = new java.io.File(location);
		f.delete();
	}
	@Test
	public void testValidate() {
		Result result = Validator.validate(sportDist.getBlock());

		assertTrue(result.getErrors().size() == 0);
		assertTrue(result.getWarnings().size() == 0);
	}

	@Test
	public void testName() {
		assertEquals(sportDist.getBlock().getName(), "recording1");
	}

	@Test
	public void testType() {
		assertEquals(sportDist.getBlock().getType(), "recording");
	}

	@Test
	public void testMetadataAccess() {

		assertNotNull(sportDist.getBlock().getMetadata());

	}

	@Test
	public void testDataArrayAccess() {

		assertEquals(sportDist.getBlock().getDataArrayCount(), 2);
	}
	
	@Test
    public void testValidateArrayDistance() {
        Result result = Validator.validate(sportDist.getDataDistance());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }

	@Test
    public void testIdDistance() {
        assertEquals(sportDist.getDataDistance().getId().length(), 36);
    }

    @Test
    public void testNameArrayTime() {
        assertEquals(sportDist.getDataDistance().getName(), "distance1");
    }

    @Test
    public void testTypeArrayTime() {
        assertEquals(sportDist.getDataDistance().getType(), "antMessage");
    }
    
    @Test
    public void testValidateArrayTime() {
        Result result = Validator.validate(sportDist.getDataTime());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }

	@Test
    public void testIdTime() {
        assertEquals(sportDist.getDataTime().getId().length(), 36);
    }

    @Test
    public void testNameArrayDistance() {
        assertEquals(sportDist.getDataTime().getName(), "timeStamp1");
    }

    @Test
    public void testTypeArrayDistance() {
        assertEquals(sportDist.getDataTime().getType(), "antMessage");
    }
    
    @Test
    public void testValidateSourceDistance() {
        Result result = Validator.validate(sportDist.getSource());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }

    @Test
    public void testIdSource() {
        assertEquals(sportDist.getSource().getId().length(), 36);
    }

    @Test
    public void testNameSource() {
        assertEquals(sportDist.getSource(), "multiSportSpeedDist1");
    }

    @Test
    public void testTypeSource() {
        assertEquals(sportDist.getSource().getType(), "antMessage");
    }
    
    @Test
    public void testValidateSection() {
        Result result = Validator.validate(sportDist.getSection());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }

    @Test
    public void testIdSection() {
        assertEquals(sportDist.getSection().getId().length(), 36);
    }

    @Test
    public void testNameSection() {
        assertEquals(sportDist.getSection().getName(), "AntMetaData");
    }

    @Test
    public void testTypeSection() {
        assertEquals(sportDist.getSection().getType(), "metadata");
    }


}
