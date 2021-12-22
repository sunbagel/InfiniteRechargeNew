package frc.robot.theory6PID;

//import com.team1241.frc2016.NumberConstants;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Simple PID Controller that assumes regular loop intervals .
 *
 * @author Team 1241
 */
public class PIDController {

	/** The p gain. */
	double pGain;
	double iGain;
	double dGain;

	double pOut;
	double iOut;
	double dOut;

	double error;
	double errorSum = 0;
	double lastError = 0;

	double dProcessVar;

	double output = 0;

	double previousValue = 0;

	double previousAverage = 0;

	double currentAverage;

	/** The average. */
	double average;

	/** The at target. */
	boolean atTarget = false;

	/**
	 * Instantiates a new PID controller.
	 *
	 * @param p
	 *            the p
	 * @param i
	 *            the i
	 * @param d
	 *            the d
	 */
	public PIDController(double p, double i, double d) {
		errorSum = 0; // initialize errorSum to 0
		lastError = 0; // initialize lastError to 0
		pGain = p;
		iGain = i;
		dGain = d;
	}

	/**
	 * Reset integral.
	 */
	public void resetIntegral() {
		errorSum = 0.0;
	}

	/**
	 * Reset derivative.
	 */
	public void resetDerivative() {
		lastError = 0.0;
	}

	/**
	 * Reset PID.
	 */
	public void resetPID() {
		resetIntegral();
		resetDerivative();
		atTarget = false;
	}

	/**
	 * Change PID gains.
	 *
	 * @param kP
	 *            the k P
	 * @param kI
	 *            the k I
	 * @param kD
	 *            the k D
	 */
	public void changePIDGains(double kP, double kI, double kD) {
		pGain = kP;
		iGain = kI;
		dGain = kD;
	}

	/**
	 * Calc PID.
	 *
	 * @param setPoint
	 *            the set point
	 * @param currentValue
	 *            the current value
	 * @param epsilon
	 *            the epsilon
	 * @return the double
	 */
	public double calcPID(double setPoint, double currentValue, double epsilon) {
		error = setPoint - currentValue; //sets error to difference between current and desired points

        /* Epsilon = accepted uncertainty. This checks whether the position is
        within the epsilon value of the desired position; if it is, the robot
        is considered at the target.*/
		if (Math.abs(error) <= epsilon) {
			atTarget = true;
		} else {
			atTarget = false;
		}

		// returns proportional as error times P coefficient
		pOut = pGain * error;

		// adds error to total error, returns integral as total error times I coefficient
		errorSum += error;
		iOut = iGain * errorSum;

		// returns difference in error (between last and current iteration) times D coefficient
		dProcessVar = (error - lastError);
		dOut = dGain * dProcessVar;

        //Sets the current error as the last error (in prep for next iteration)
		lastError = error;

		// Outputs PID as sum of P + I + d
		output = pOut + iOut + dOut;

		// Scale output to be between 1 and -1 (idk how this work, too lazy to read)
		if (output != 0.0)
			output = output / Math.abs(output) * (1.0 - Math.pow(0.1, (Math.abs(output))));

		return output;
	}

	/**
	 * Calc PID drive.
	 *
	 * @param setPoint
	 *            the set point
	 * @param currentValue
	 *            the current value
	 * @param epsilon
	 *            the epsilon
	 * @return the double
	 */

    //No idea how this is different in practice from calcPID, the final scaling is different
    //but still no idea why that should be or how it works
	public double calcPIDDrive(double setPoint, double currentValue, double epsilon) {
		error = setPoint - currentValue;

		if (Math.abs(error) <= epsilon) {
			atTarget = true;
		} else {
			atTarget = false;
		}

		// P
		pOut = pGain * error;

		// I
		errorSum += error;
		iOut = iGain * errorSum;

		// D
		dProcessVar = (error - lastError);
		dOut = dGain * dProcessVar;

		lastError = error;

		// PID Output
		output = pOut + iOut + dOut;

		// Scale output to be between 1 and -1
		if (output != 0.0)
			output = output / Math.abs(output) * (1.0 - Math.pow(0.6, (Math.abs(output))));

		return output;
	}

	/**
	 * Calc PID velocity.
	 *
	 * @param setPoint
	 *            the set point
	 * @param currentValue
	 *            the current value
	 * @param epsilon
	 *            the epsilon
	 * @param iStart
	 *            the i start
	 * @return the double
	 */
	public double calcPIDVelocity(double setPoint, double currentValue, double epsilon, double iStart) {
		error = setPoint - currentValue;

		if (Math.abs(error) <= epsilon) {
			atTarget = true;
		} else {
			atTarget = false;
		}

		// P
		pOut = pGain * error;

		// I

		// 0.60
		if (currentValue >= setPoint * iStart) {
			errorSum += error;
			iOut = iGain * errorSum;

			currentAverage = (previousValue + currentValue) / 2;
			average = (currentAverage + previousAverage) / 2;
			SmartDashboard.putNumber("average", average);
		} else {
			iOut = 0;
		}

		// D
		dProcessVar = (error - lastError);
		dOut = dGain * dProcessVar;

		lastError = error;
		previousValue = currentValue;
		previousAverage = currentAverage;

		// PID Output
		output = pOut + iOut + dOut;

		// Scale output to be between 1 and -1
		if (output != 0.0)
			output = output / Math.abs(output) * (1.0 - Math.pow(0.1, (Math.abs(output))));

		return output;
	}

	/**
	 * Checks if is done.
	 *
	 * @return true, if is done
	 */
	public boolean isDone() {
		return atTarget;
	}

	/**
	 * Gets the p gain.
	 *
	 * @return the p gain
	 */
	public double getPGain() {
		return pGain;
	}

	/**
	 * Gets the i gain.
	 *
	 * @return the i gain
	 */
	public double getIGain() {
		return iGain;
	}

	/**
	 * Gets the d gain.
	 *
	 * @return the d gain
	 */
	public double getDGain() {
		return dGain;
	}
}