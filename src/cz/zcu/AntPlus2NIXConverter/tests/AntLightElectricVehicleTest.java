package cz.zcu.AntPlus2NIXConverter.tests;

import static org.junit.Assert.*;

import org.g_node.nix.valid.Result;
import org.g_node.nix.valid.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cz.zcu.AntPlus2NIXConverter.Data.OdMLData;
import cz.zcu.AntPlus2NIXConverter.Profiles.AntBikePower;
import cz.zcu.AntPlus2NIXConverter.Profiles.AntLightElectricVehicle;

public class AntLightElectricVehicleTest {

	AntLightElectricVehicle vehicle;

	@Before
	public void setUp() {
		vehicle = new AntLightElectricVehicle(new double[]{}, new boolean[] {}, new int[] {}, new int[]{},
				new OdMLData(33, 23, 4, 5, 2, 4, 4, 2, 5));
		vehicle.createNixFile("testovaci.h5");
	}

	@After
	public void tearDown(){
		String location =vehicle.getFile().getLocation();
		
		vehicle.getFile().close();
		
		java.io.File f = new java.io.File(location);
		f.delete();
	}
	@Test
	public void testValidate() {
		Result result = Validator.validate(vehicle.getBlock());

		assertTrue(result.getErrors().size() == 0);
		assertTrue(result.getWarnings().size() == 0);
	}

	@Test
	public void testName() {
		assertEquals(vehicle.getBlock().getName(), "recording1");
	}

	@Test
	public void testType() {
		assertEquals(vehicle.getBlock().getType(), "recording");
	}

	@Test
	public void testMetadataAccess() {

		assertNotNull(vehicle.getBlock().getMetadata());

	}

	@Test
	public void testDataArrayAccess() {

		assertEquals(vehicle.getBlock().getDataArrayCount(), 4);
		}

	@Test
	public void testValidateArrayBat() {
		Result result = Validator.validate(vehicle.getDataBatStatus());
		assertTrue(result.getErrors().size() == 0);
		assertTrue(result.getWarnings().size() == 0);
	}

	@Test
	public void testIdBat() {
		assertEquals(vehicle.getDataBatStatus().getId().length(), 36);
	}

	@Test
	public void testNameArrayBat() {
		assertEquals(vehicle.getDataBatStatus().getName(), "batStatus1");
	}

	@Test
	public void testTypeArrayBat() {
		assertEquals(vehicle.getDataBatStatus().getType(), "antMessage");
	}
	
	
	
	@Test
	public void testValidateArrayMode() {
		Result result = Validator.validate(vehicle.getDataMode());
		assertTrue(result.getErrors().size() == 0);
		assertTrue(result.getWarnings().size() == 0);
	}

	@Test
	public void testIdMode() {
		assertEquals(vehicle.getDataMode().getId().length(), 36);
	}

	@Test
	public void testNameArrayMode() {
		assertEquals(vehicle.getDataMode().getName(), "mode1");
	}

	@Test
	public void testTypeArrayMode() {
		assertEquals(vehicle.getDataMode().getType(), "antMessage");
	}


	
	@Test
	public void testValidateArrayGear() {
		Result result = Validator.validate(vehicle.getDataSysGearState());
		assertTrue(result.getErrors().size() == 0);
		assertTrue(result.getWarnings().size() == 0);
	}

	@Test
	public void testIdGear() {
		assertEquals(vehicle.getDataSysGearState().getId().length(), 36);
	}

	@Test
	public void testNameArrayGear() {
		assertEquals(vehicle.getDataSysGearState().getName(), "sysGearState1");
	}

	@Test
	public void testTypeArrayGear() {
		assertEquals(vehicle.getDataSysGearState().getType(), "antMessage");
	}

	
	@Test
	public void testValidateArraySpeed() {
		Result result = Validator.validate(vehicle.getDataSpeedDistance());
		assertTrue(result.getErrors().size() == 0);
		assertTrue(result.getWarnings().size() == 0);
	}

	@Test
	public void testIdSpeed() {
		assertEquals(vehicle.getDataSpeedDistance().getId().length(), 36);
	}

	@Test
	public void testNameArraySpeed() {
		assertEquals(vehicle.getDataSpeedDistance().getName(), "speedDistance1");
	}

	@Test
	public void testTypeArraySpeed() {
		assertEquals(vehicle.getDataSpeedDistance().getType(), "antMessage");
	}

	

	@Test
	public void testValidateSource() {
		Result result = Validator.validate(vehicle.getSource());
		assertTrue(result.getErrors().size() == 0);
		assertTrue(result.getWarnings().size() == 0);
	}
	
	

	@Test
	public void testIdSource() {
		assertEquals(vehicle.getSource().getId().length(), 36);
	}

	@Test
	public void testNameSource() {
		assertEquals(vehicle.getSource(), "lightElVeh1");
	}

	@Test
	public void testTypeSource() {
		assertEquals(vehicle.getSource().getType(), "antMessage");
	}

	@Test
	public void testValidateSection() {
		Result result = Validator.validate(vehicle.getSection());
		assertTrue(result.getErrors().size() == 0);
		assertTrue(result.getWarnings().size() == 0);
	}

	@Test
	public void testIdSection() {
		assertEquals(vehicle.getSection().getId().length(), 36);
	}

	@Test
	public void testNameSection() {
		assertEquals(vehicle.getSection().getName(), "AntMetaData");
	}

	@Test
	public void testTypeSection() {
		assertEquals(vehicle.getSection().getType(), "metadata");
	}
}
