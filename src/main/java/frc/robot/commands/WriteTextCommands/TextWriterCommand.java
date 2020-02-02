/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.WriteTextCommands;

import frc.robot.subsystems.DateTimeSubsystem;
import frc.robot.subsystems.FileWriterSubsystem;
import frc.robot.subsystems.FlywheelSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

import java.io.IOException;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Add your docs here.
 */
public class TextWriterCommand extends CommandBase {
    private final DateTimeSubsystem m_dateTimeSubsystem;
    private final FileWriterSubsystem m_fileWriterSubsystem;
    private final LimelightSubsystem m_limelightSubsystem;
    private final FlywheelSubsystem m_flywheelSubsystem;
    private final int buttonPress;
    private final String[] result = { "Over", "Outer", "Inner", "Under" };

    public TextWriterCommand(DateTimeSubsystem dateTimeS, FileWriterSubsystem fileWriterS,
            LimelightSubsystem lSubsystem, FlywheelSubsystem flywheelSubsystem, int buttonPressed) {
        m_dateTimeSubsystem = dateTimeS;
        m_fileWriterSubsystem = fileWriterS;
        m_limelightSubsystem = lSubsystem;
        m_flywheelSubsystem = flywheelSubsystem;
        buttonPress = buttonPressed;
        
    }

    public void initialize() {
        m_fileWriterSubsystem.createFile("this is machine specific format =  c://Users//...//TextFolder", m_dateTimeSubsystem.getDate());
    }

    public void execute() {
        String calculatedDistance = Double.toString(8 * Math.cos(m_limelightSubsystem.getYAngle()));
        try {
            m_fileWriterSubsystem.writeFile(Double.toString(m_limelightSubsystem.getYAngle()),
                    Double.toString(m_flywheelSubsystem.getVelocity()), result[buttonPress], calculatedDistance);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
