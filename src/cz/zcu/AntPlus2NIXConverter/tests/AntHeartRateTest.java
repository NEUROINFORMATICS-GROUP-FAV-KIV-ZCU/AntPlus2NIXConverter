package cz.zcu.AntPlus2NIXConverter.tests;

import static org.junit.Assert.*;

import org.g_node.nix.valid.Result;
import org.g_node.nix.valid.Validator;
import org.junit.Before;
import org.junit.Test;

import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;
import cz.zcu.AntPlus2NIXConverter.Profiles.AntBikePower;
import cz.zcu.AntPlus2NIXConverter.Profiles.AntHeartRate;

public class AntHeartRateTest {

	AntHeartRate heartRate;
	@Before
	public void setUp() {
		heartRate = new AntHeartRate(new int[]{4,4,42,2}, new int[]{4,5,6,3}, new double[] {3,4,52,1}, new OdMLData(33, 23, 4, 5, 2, 4, 4, 2, 5));
		heartRate.createNixFile("testovaci.h5");
	}

	@Test
	public void testValidate() {
		Result result = Validator.validate(heartRate.getBlock());

		assertTrue(result.getErrors().size() == 0);
		assertTrue(result.getWarnings().size() == 0);
	}

	@Test
	public void testName() {
		assertEquals(heartRate.getBlock().getName(), "recording1");
	}

	@Test
	public void testType() {
		assertEquals(heartRate.getBlock().getType(), "recording");
	}

	@Test
	public void testMetadataAccess() {

		assertNotNull(heartRate.getBlock().getMetadata());

	}


	@Test
    public void testValidateArrayCounter() {
        Result result = Validator.validate(heartRate.getDataHeartBeatCounter());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }

	@Test
    public void testIdCounter() {
        assertEquals(heartRate.getDataHeartBeatCounter().getId().length(), 36);
    }

    @Test
    public void testNameArrayCounter() {
        assertEquals(heartRate.getDataHeartBeatCounter().getName(), "heartBeatCount1");
    }
    
    @Test
    public void testUnitArrayCounter() {
        assertEquals(heartRate.getDataHeartBeatCounter().getUnit(), "N/A");
    }

    @Test
    public void testTypeArrayCounter() {
        assertEquals(heartRate.getDataHeartBeatCounter().getType(), "antMessage");
    }
    
	@Test
	public void testDataArrayAccess() {

		assertEquals(heartRate.getBlock().getDataArrayCount(), 3);
	}
	
	@Test
    public void testValidateArrayComputed() {
        Result result = Validator.validate(heartRate.getDataComputedHeartRate());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }

	@Test
    public void testIdComputed() {
        assertEquals(heartRate.getDataComputedHeartRate().getId().length(), 36);
    }

	@Test
    public void testUnitArrayComputed() {
        assertEquals(heartRate.getDataComputedHeartRate().getUnit(), "bpm");
    }
	
    @Test
    public void testNameArrayComputed() {
        assertEquals(heartRate.getDataComputedHeartRate().getName(), "comluptedHeartRate1");
    }

    @Test
    public void testTypeArrayComputed() {
        assertEquals(heartRate.getDataComputedHeartRate().getType(), "antMessage");
    }
    
    
	
	@Test
    public void testValidateArrayTime() {
        Result result = Validator.validate(heartRate.getDataTimeOfPreviousHeartBeat());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }

	@Test
    public void testIdTime() {
        assertEquals(heartRate.getDataTimeOfPreviousHeartBeat().getId().length(), 36);
    }

    @Test
    public void testNameArrayTime() {
        assertEquals(heartRate.getDataTimeOfPreviousHeartBeat().getName(), "timeOfPreviousHeartBeat1");
    }
    
    @Test
    public void testUnitArrayTime() {
        assertEquals(heartRate.getDataTimeOfPreviousHeartBeat().getUnit(), "seconds");
    }

    @Test
    public void testTypeArrayTime() {
        assertEquals(heartRate.getDataTimeOfPreviousHeartBeat().getType(), "antMessage");
    }
        
    @Test
    public void testValidateSource() {
        Result result = Validator.validate(heartRate.getSource());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }

    @Test
    public void testIdSource() {
        assertEquals(heartRate.getSource().getId().length(), 36);
    }

    @Test
    public void testNameSource() {
        assertEquals(heartRate.getSource(), "HeartRate");
    }

    @Test
    public void testTypeSource() {
        assertEquals(heartRate.getSource().getType(), "antMessage");
    }
    
    @Test
    public void testValidateSection() {
        Result result = Validator.validate(heartRate.getSection());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }

    @Test
    public void testIdSection() {
        assertEquals(heartRate.getSection().getId().length(), 36);
    }

    @Test
    public void testNameSection() {
        assertEquals(heartRate.getSection().getName(), "AntMetaData");
    }

    @Test
    public void testTypeSection() {
        assertEquals(heartRate.getSection().getType(), "metadata");
    }

}
