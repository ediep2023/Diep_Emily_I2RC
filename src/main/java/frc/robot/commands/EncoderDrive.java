package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

public class EncoderDrive extends Command{
    private Drivetrain dt;
    private int setpoint;

    public EncoderDrive(Drivetrain dt, int setpoint){
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
        if (dt.tankDrive(0, 0)){
            return true;
        }
        return false;
    }
}

public class RobotContainer{
    private final Drivetrain dt = new Drivetrain();
    private final EncoderDrive drive = new EncoderDrive(dt, 2);

    public RobotContainer(){
        dt.setDefaultCommand(drive);
        configureBindings();
    }

    public Command getAutonomousCommand(){
        return drive;
    }
}