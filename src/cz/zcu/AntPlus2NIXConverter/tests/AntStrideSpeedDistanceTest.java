package cz.zcu.AntPlus2NIXConverter.tests;

import static org.junit.Assert.*;

import org.g_node.nix.valid.Result;
import org.g_node.nix.valid.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;
import cz.zcu.AntPlus2NIXConverter.Profiles.AntBikePower;
import cz.zcu.AntPlus2NIXConverter.Profiles.AntStrideSpeedDistance;

public class AntStrideSpeedDistanceTest {

	AntStrideSpeedDistance stridSpeedD;
	@Before
	public void setUp() {
		stridSpeedD = new AntStrideSpeedDistance(new long[]{2,3,5},new double[] { 1, 3, 5, 6 },new double[] { 1, 3, 5, 6 }, new OdMLData(33, 23, 4, 5, 2, 4, 4, 2, 5));
		stridSpeedD.createNixFile("testovaci.h5");
	}
	@After
	public void tearDown(){
		String location =stridSpeedD.getFile().getLocation();
		
		stridSpeedD.getFile().close();
		
		java.io.File f = new java.io.File(location);
		f.delete();
	}

	@Test
	public void testValidate() {
		Result result = Validator.validate(stridSpeedD.getBlock());

		assertTrue(result.getErrors().size() == 0);
		assertTrue(result.getWarnings().size() == 0);
	}

	@Test
	public void testName() {
		assertEquals(stridSpeedD.getBlock().getName(), "recording1");
	}

	@Test
	public void testType() {
		assertEquals(stridSpeedD.getBlock().getType(), "recording");
	}

	@Test
	public void testMetadataAccess() {

		assertNotNull(stridSpeedD.getBlock().getMetadata());

	}

	@Test
	public void testDataArrayAccess() {

		assertEquals(stridSpeedD.getBlock().getDataArrayCount(), 3);
	}
	
	@Test
    public void testValidateArrayDistance() {
        Result result = Validator.validate(stridSpeedD.getDataDistance());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }

	@Test
    public void testIdDistance() {
        assertEquals(stridSpeedD.getDataDistance().getId().length(), 36);
    }

    @Test
    public void testNameArrayDistance() {
        assertEquals(stridSpeedD.getDataDistance().getName(), "distance1");
    }

    @Test
    public void testTypeArrayDistance() {
        assertEquals(stridSpeedD.getDataDistance().getType(), "antMessage");
    }

    
    
	@Test
    public void testValidateArraySpeed() {
        Result result = Validator.validate(stridSpeedD.getDataSpeed());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }

	@Test
    public void testIdSpeed() {
        assertEquals(stridSpeedD.getDataSpeed().getId().length(), 36);
    }

    @Test
    public void testNameArraySpeed() {
        assertEquals(stridSpeedD.getDataSpeed().getName(), "speed1");
    }

    @Test
    public void testTypeArraySpeed() {
        assertEquals(stridSpeedD.getDataSpeed().getType(), "antMessage");
    }
    
    
	@Test
    public void testValidateArrayStride() {
        Result result = Validator.validate(stridSpeedD.getDataStrideCount());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }

	@Test
    public void testIdStride() {
        assertEquals(stridSpeedD.getDataStrideCount().getId().length(), 36);
    }

    @Test
    public void testNameArrayStride() {
        assertEquals(stridSpeedD.getDataStrideCount().getName(), "strideCount1");
    }

    @Test
    public void testTypeArrayStride() {
        assertEquals(stridSpeedD.getDataStrideCount().getType(), "antMessage");
    }

    
    @Test
    public void testValidateSource() {
        Result result = Validator.validate(stridSpeedD.getSource());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }

    @Test
    public void testIdSource() {
        assertEquals(stridSpeedD.getSource().getId().length(), 36);
    }

    @Test
    public void testNameSource() {
        assertEquals(stridSpeedD.getSource(), "strideSpeedDistance");
    }

    @Test
    public void testTypeSource() {
        assertEquals(stridSpeedD.getSource().getType(), "antMessage");
    }
    
    @Test
    public void testValidateSection() {
        Result result = Validator.validate(stridSpeedD.getSection());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }

    @Test
    public void testIdSection() {
        assertEquals(stridSpeedD.getSection().getId().length(), 36);
    }

    @Test
    public void testNameSection() {
        assertEquals(stridSpeedD.getSection().getName(), "AntMetaData");
    }

    @Test
    public void testTypeSection() {
        assertEquals(stridSpeedD.getSection().getType(), "metadata");
    }
    

}
