/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.text.SimpleDateFormat;
import java.util.Date;
import edu.wpi.first.wpilibj2.command.SubsystemBase; 


public class DateTimeSubsystem extends SubsystemBase{

    private final SimpleDateFormat formatter;
    private final Date date;
   

    public DateTimeSubsystem(){
        formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        date = new Date();
    }
    public String getDate(){
        String currentDate = formatter.format(date);
        return currentDate;
    }

}
