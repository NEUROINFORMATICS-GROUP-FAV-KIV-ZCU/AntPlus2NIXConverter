package cz.zcu.AntPlus2NIXConverter.tests;

import static org.junit.Assert.*;

import org.g_node.nix.valid.Result;
import org.g_node.nix.valid.Validator;
import org.junit.Before;
import org.junit.Test;

import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;
import cz.zcu.AntPlus2NIXConverter.Profiles.AntBikePower;
import cz.zcu.AntPlus2NIXConverter.Profiles.AntWeightScale;

public class AntWeightScaleTest {

	AntWeightScale scale;

	@Before
	public void setUp() {
		scale = new AntWeightScale(new int[] { 1, 3, 5, 6 }, new OdMLData(33, 23, 4, 5, 2, 4, 4, 2, 5));
		scale.createNixFile("testovaci.h5");
	}

	@Test
	public void testValidate() {
		Result result = Validator.validate(scale.getBlock());

		assertTrue(result.getErrors().size() == 0);
		assertTrue(result.getWarnings().size() == 0);
	}

	@Test
	public void testName() {
		assertEquals(scale.getBlock().getName(), "recording1");
	}

	@Test
	public void testType() {
		assertEquals(scale.getBlock().getType(), "recording");
	}

	@Test
	public void testMetadataAccess() {

		assertNotNull(scale.getBlock().getMetadata());

	}

	@Test
	public void testDataArrayAccess() {

		assertEquals(scale.getBlock().getDataArrayCount(), 1);
	}
	
	@Test
    public void testValidateArray() {
        Result result = Validator.validate(scale.getDataWeight());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }

	@Test
    public void testId() {
        assertEquals(scale.getDataWeight().getId().length(), 36);
    }
	
	@Test
    public void testUnitArray() {
        assertEquals(scale.getDataWeight().getUnit(), "kg");
    }

    @Test
    public void testNameArray() {
        assertEquals(scale.getDataWeight().getName(), "weight	1");
    }

    @Test
    public void testTypeArray() {
        assertEquals(scale.getDataWeight().getType(), "antMessage");
    }
    
    @Test
    public void testValidateSource() {
        Result result = Validator.validate(scale.getSource());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }

    @Test
    public void testIdSource() {
        assertEquals(scale.getSource().getId().length(), 36);
    }

    @Test
    public void testNameSource() {
        assertEquals(scale.getSource(), "weightScale");
    }

    @Test
    public void testTypeSource() {
        assertEquals(scale.getSource().getType(), "antMessage");
    }
    
    @Test
    public void testValidateSection() {
        Result result = Validator.validate(scale.getSection());
        assertTrue(result.getErrors().size() == 0);
        assertTrue(result.getWarnings().size() == 0);
    }

    @Test
    public void testIdSection() {
        assertEquals(scale.getSection().getId().length(), 36);
    }

    @Test
    public void testNameSection() {
        assertEquals(scale.getSection().getName(), "AntMetaData");
    }

    @Test
    public void testTypeSection() {
        assertEquals(scale.getSection().getType(), "metadata");
    }
    

}
