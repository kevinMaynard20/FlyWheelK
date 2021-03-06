/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.io.FileWriter;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.subsystems.*;
import frc.robot.Constants.ControllerConstants;
import frc.robot.Constants.FlywheelConstants;
import frc.robot.Constants.ControllerConstants.*;
import frc.robot.commands.DrivetrainCommands.ArcadeDriveCommand;
import frc.robot.commands.WriteTextCommands.TextWriterCommand;
import frc.robot.commands.limelightcommands.LimelightTargetCommand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

public class RobotContainer {
	private final CarouselSubsystem m_carousel = new CarouselSubsystem();
	private final DrivetrainSubsystem m_drivetrain = new DrivetrainSubsystem();
	private final FeederSubsystem m_feeder = new FeederSubsystem();
	private final FlywheelSubsystem m_flywheel = new FlywheelSubsystem();
	private final LimelightSubsystem m_limelight = new LimelightSubsystem();
	private final Joystick m_driverController = new Joystick(ControllerConstants.kDriverControllerPort);
	private final DateTimeSubsystem m_date = new DateTimeSubsystem();
	private final FileWriterSubsystem m_file=  new FileWriterSubsystem();

	public RobotContainer() {
		configureButtonBindings();

		// Drivetrain
		m_drivetrain.setDefaultCommand(
				new ArcadeDriveCommand(m_drivetrain, () -> m_driverController.getRawAxis(Axis.kLeftY),
						() -> (m_driverController.getRawAxis(Axis.kLeftTrigger) + 1) / 2,
						() -> (m_driverController.getRawAxis(Axis.kRightTrigger) + 1) / 2));

	}

	private void configureButtonBindings() {
		// Flywheel
		new POVButton(m_driverController, DPad.kDown).whenPressed(() -> m_flywheel.setSetpoint(1000), m_flywheel);
		new POVButton(m_driverController, DPad.kLeft).whenPressed(() -> m_flywheel.setSetpoint(1200), m_flywheel);
		new POVButton(m_driverController, DPad.kUp).whenPressed(() -> m_flywheel.setSetpoint(1400), m_flywheel);
		new POVButton(m_driverController, DPad.kRight).whenPressed(() -> m_flywheel.setSetpoint(1600), m_flywheel);
		new JoystickButton(m_driverController, Button.kLeftBumper).whileHeld(
				() -> m_flywheel.setSetpoint(-m_driverController.getRawAxis(Axis.kRightY) * FlywheelConstants.kMaxRPM),
				m_flywheel);
		new JoystickButton(m_driverController, Button.kRightBumper).whileHeld(() -> m_flywheel.setSetpoint(0),
				m_flywheel);

		// Carousel
		new JoystickButton(m_driverController, Button.kSquare).whenPressed(() -> m_carousel.rotate(1), m_carousel);
		new JoystickButton(m_driverController, Button.kTriangle).whenPressed(() -> m_carousel.rotate(0), m_carousel);

		// Feeder
		new JoystickButton(m_driverController, Button.kX).whenPressed(() -> m_feeder.feed(1), m_feeder);
		new JoystickButton(m_driverController, Button.kCircle).whenPressed(() -> m_feeder.feed(0), m_feeder);

		// Limelight
		new JoystickButton(m_driverController, Button.kTrackpad)
				.whenPressed(new LimelightTargetCommand(m_limelight, m_drivetrain));
		
		// Textwriter
		new JoystickButton(m_driverController, Button.kX)
		.and(new POVButton(m_driverController, DPad.kDown)).whenActive(new TextWriterCommand(m_date, m_file, m_limelight, m_flywheel, 3)); 
		new JoystickButton(m_driverController, Button.kX)
		.and(new POVButton(m_driverController, DPad.kLeft)).whenActive(new TextWriterCommand(m_date, m_file, m_limelight, m_flywheel, 1)); 
		new JoystickButton(m_driverController, Button.kX)
		.and(new POVButton(m_driverController, DPad.kRight)).whenActive(new TextWriterCommand(m_date, m_file, m_limelight, m_flywheel, 2)); 
		new JoystickButton(m_driverController, Button.kX)
		.and(new POVButton(m_driverController, DPad.kUp)).whenActive(new TextWriterCommand(m_date, m_file, m_limelight, m_flywheel, 0)); 
	}

	public Command getAutonomousCommand() {
		return null;
	}
}
