// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.*;

public class Robot extends TimedRobot {

  Timer timer = new Timer();
  Drive tachanka = new Drive();
  Shooter shooter = new Shooter();
  Pivision vision = new Pivision();
  LimelightVision limeVision = new LimelightVision();
  Pickup pickup = new Pickup();
  Climb climb = new Climb();
  //navex navex = new navex();

  @Override
  public void disabledPeriodic () {
    shooter.limeDisable();
  }
  @Override
  public void robotInit() {
    vision.init();
  }

  @Override
  public void robotPeriodic() {
    vision.printVisionMessage();
  }
  
  @Override
  public void teleopInit() {
    tachanka.driveInit();
    climb.climbInit();
    shooter.shooterInit();
    pickup.pickupInit();
  }

  @Override
  public void teleopPeriodic() {
    if(climb.counter % 2 == 0) {
      shooter.song();
    }else {
      shooter.shooterPeriodic();
    }
    tachanka.drivePeriodic();
 
    limeVision.periodic();
    pickup.pickupPeriodic();
    //navex.navexPeriodic();
   climb.climbPeriodic();
  }

  // auto
  @Override
  public void autonomousInit() {
          timer.reset();

      }
  

  @Override
  public void autonomousPeriodic() {
    //autonomous.autonomousPeriodic();
    tachanka.driveInit();
    tachanka.drivePeriodic();
    timer.start();
    if (timer.get() < 2) {
      tachanka.controllerMove = 0.5;
      System.out.println(timer.get());
    if (timer.get() > 2) {
      tachanka.controllerMove = 0;
      timer.stop();
      timer.reset();
    }

  }
  tachanka.tank();

  }
}

