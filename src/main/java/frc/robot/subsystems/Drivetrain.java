// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SPI;

public class Drivetrain extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  private int leftPort = 2;
  private int rightPort = 3;
  private final WPI_TalonSRX leftDriveTalon;
  private final WPI_TalonSRX rightDriveTalon;
  private AHRS NavX = new AHRS(SPI.Port.kMXP);

  public Drivetrain() {
    leftDriveTalon = new WPI_TalonSRX(leftPort);
    rightDriveTalon = new WPI_TalonSRX(rightPort);
    leftDriveTalon.setNeutralMode(NeutralMode.Coast);
    rightDriveTalon.setNeutralMode(NeutralMode.Coast);

  }
  public void tankDrive(double leftSpeed, double rightSpeed){
    leftDriveTalon.set(leftSpeed);
    rightDriveTalon.set(rightSpeed);

    leftDriveTalon.configFactoryDefault();
    rightDriveTalon.configFactoryDefault();

    leftDriveTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    rightDriveTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
  }
  public double getAngle(){
    return NavX.getAngle();
  }
  public void reset(){
    NavX.reset();
  }
  /* 
  private ShuffleboardTab DTTab = Shuffleboard.getTab(title:"DriveTrain");
  private GenericEntry LeftVoltage = DTTab.add()
  */


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Left Voltage", leftDriveTalon.getMotorOutputPercent());
    SmartDashboard.putNumber("Right Voltage", rightDriveTalon.getMotorOutputPercent());
    SmartDashboard.putNumber("Angle", NavX.getAngle());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
