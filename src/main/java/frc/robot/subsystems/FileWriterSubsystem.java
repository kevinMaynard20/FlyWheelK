/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;


public class FileWriterSubsystem {
    private  File file;
    private FileWriter writer;
    public FileWriterSubsystem() {

    }

    public void createFile(String Path, String fileName) {
        String pathFile = Path + fileName + ".txt";
        file = new File(pathFile);

    }

    public void writeFile(String Angle, String RPM, String Result, String calculatedDistance) throws IOException {
         writer = new FileWriter(file);
         writer.write(Angle + ',' + RPM + ',' + Result + ',' + calculatedDistance + "\n");        
    }

}
