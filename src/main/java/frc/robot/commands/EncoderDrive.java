package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

public class EncoderDrive extends Command{
    private Drivetrain dt;
    private double setpoint;

    public EncoderDrive(Drivetrain dt, double setpoint);
        this.dt = dt;
        this.setpoint=setpoint;
        addRequirements(dt);
    }

    public void initialize(){
        dt.resetEncoders();
        dt.tankDrive(0,0);
    }

    @Override
    public void execute(){
        dt.tankDrive(.8, .8);
    }
    @Override
    public void end(boolean interrupted){
        dt.tankDrive(0, 0);
    }

    public boolean isFinished(){
      
        if (dt.getMeters() >= setpoint) {
            return true;
        }
        return false;
    }
}