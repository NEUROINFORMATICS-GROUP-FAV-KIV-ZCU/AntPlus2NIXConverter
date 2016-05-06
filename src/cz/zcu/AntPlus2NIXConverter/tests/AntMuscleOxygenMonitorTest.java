package cz.zcu.AntPlus2NIXConverter.tests;

import static org.junit.Assert.*;

import org.g_node.nix.valid.Result;
import org.g_node.nix.valid.Validator;
import org.junit.Before;
import org.junit.Test;

import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;
import cz.zcu.AntPlus2NIXConverter.Profiles.AntBikePower;
import cz.zcu.AntPlus2NIXConverter.Profiles.AntMuscleOxygenMonitor;

public class AntMuscleOxygenMonitorTest {

	AntMuscleOxygenMonitor msOM;
	@Before
	public void setUp() {
		msOM = new AntMuscleOxygenMonitor(new double[] { 1, 3, 5, 6 },new double[] { 1, 3, 5, 6 }, new OdMLData(33, 23, 4, 5, 2, 4, 4, 2, 5));
		msOM.createNixFile("testovaci.h5");
	}

	@Test
	public void testValidate() {
		Result result = Validator.validate(msOM.getBlock());

		assertTrue(result.getErrors().size() == 0);
		assertTrue(result.getWarnings().size() == 0);
	}

	@Test
	public void testName() {
		assertEquals(msOM.getBlock().getName(), "recording1");
	}

	@Test
	public void testType() {
		assertEquals(msOM.getBlock().getType(), "recording");
	}

	@Test
	public void testMetadataAccess() {

		assertNotNull(msOM.getBlock().getMetadata());

	}

	@Test
	public void testDataArrayAccess() {

		assertEquals(msOM.getBlock().getDataArrayCount(), 2);
	}
	
	@Test
    public void testValidateArrayHemoglobin() {
        Result result = Validator.validate(msOM.getDataHemoglobinConcentrate());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }

	@Test
    public void testIdHemoglobin() {
        assertEquals(msOM.getDataHemoglobinConcentrate().getId().length(), 36);
    }

    @Test
    public void testNameArrayHemoglobin() {
        assertEquals(msOM.getDataHemoglobinConcentrate().getName(), "hemoglobinConcentr1");
    }

    @Test
    public void testTypeArrayHemoglobin() {
        assertEquals(msOM.getDataHemoglobinConcentrate().getType(), "antMessage");
    }
    
    @Test
    public void testValidateArrayHemoglobinPer() {
        Result result = Validator.validate(msOM.getDataSaturatedHemoglPerc());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }

	@Test
    public void testIdHemoglobinPer() {
        assertEquals(msOM.getDataSaturatedHemoglPerc().getId().length(), 36);
    }

    @Test
    public void testNameArrayHemoglobinPer() {
        assertEquals(msOM.getDataSaturatedHemoglPerc().getName(), "saturatedHemoglPerc1");
    }

    @Test
    public void testTypeArrayHemoglobinPer() {
        assertEquals(msOM.getDataSaturatedHemoglPerc().getType(), "antMessage");
    }
    
    @Test
    public void testValidateSource() {
        Result result = Validator.validate(msOM.getSource());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }

    @Test
    public void testIdSource() {
        assertEquals(msOM.getSource().getId().length(), 36);
    }

    @Test
    public void testNameSource() {
        assertEquals(msOM.getSource(), " strideSpeedDistance1");
    }

    @Test
    public void testTypeSource() {
        assertEquals(msOM.getSource().getType(), "antMessage");
    }
    
    @Test
    public void testValidateSection() {
        Result result = Validator.validate(msOM.getSection());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }

    @Test
    public void testIdSection() {
        assertEquals(msOM.getSection().getId().length(), 36);
    }

    @Test
    public void testNameSection() {
        assertEquals(msOM.getSection().getName(), "AntMetaData");
    }

    @Test
    public void testTypeSection() {
        assertEquals(msOM.getSection().getType(), "metadata");
    }


}
