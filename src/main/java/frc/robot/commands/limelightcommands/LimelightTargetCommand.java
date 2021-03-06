package frc.robot.commands.limelightcommands;

import frc.robot.Constants.LimelightConstants;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class LimelightTargetCommand extends CommandBase {

	private final LimelightSubsystem m_limelightSubsystem;
	private final DrivetrainSubsystem m_drivetrainSubsystem;
	private PIDController distanceController, turnController;

	public LimelightTargetCommand(LimelightSubsystem limelightSubsystem, DrivetrainSubsystem drivetrainSubsystem) {
		m_limelightSubsystem = limelightSubsystem;
		m_drivetrainSubsystem = drivetrainSubsystem;
		addRequirements(drivetrainSubsystem);
	}

	public void initialize() {
		distanceController = new PIDController(LimelightConstants.kDisP, LimelightConstants.kDisI,
				LimelightConstants.kDisD);
		distanceController.setTolerance(.05);

		turnController = new PIDController(LimelightConstants.kTurnP, LimelightConstants.kTurnI,
				LimelightConstants.kTurnD);
		turnController.setTolerance(.05);
	}

	public void execute() {
		if (m_limelightSubsystem.isTargetVisible()) {
			double turnSpeed = turnController.calculate(m_limelightSubsystem.getXAngle());
			double distanceSpeed = distanceController.calculate(m_limelightSubsystem.getYAngle());
			m_drivetrainSubsystem.arcadeDrive(distanceSpeed, turnSpeed, 0);
		}
	}

	public boolean isFinished() {
		return turnController.atSetpoint() && distanceController.atSetpoint();
	}
}
