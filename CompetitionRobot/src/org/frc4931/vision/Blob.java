package org.frc4931.vision;

import edu.wpi.first.wpilibj.PIDSource;

public class Blob implements PIDSource{
	//Placeholder class for Blob
	private int xCenter;
//	private int yCenter;
	public Blob() {
	}

	public double pidGet() {
		return xCenter;
	}

}
