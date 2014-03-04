/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends SimpleRobot {
    Joystick stick;
    Joystick stick2;
    Talon fl;
    Talon rl;
    Talon rr;
    Talon fr;
    Victor motor5;
    Victor motor6;
    Compressor c;
    Solenoid s;
    Solenoid s2;
    Solenoid s3;
    Solenoid s4;
    Solenoid s5;
    Solenoid s6;
    Solenoid s7;
    Solenoid s8;
    DigitalInput half;
    DigitalInput full;
    double off;
    Gyro gyro;
    AnalogChannel color;
    AnalogChannel u;
    double old;
    JoystickButton j1b1;
    JoystickButton j1b2;
    JoystickButton j1b3;
    JoystickButton j1b4;
    JoystickButton j1b5;
    JoystickButton j1b6;
    JoystickButton j1b7;
    JoystickButton j1b8;
    JoystickButton j1b9;
    JoystickButton j1b10;
    JoystickButton j2b2;
    JoystickButton j2b3;
    JoystickButton j2b4;
    JoystickButton j2b5;
    JoystickButton j2b7;
    JoystickButton j2b8;
    JoystickButton j2b9;
    JoystickButton j2b10;
    
    double multiplyer;
    boolean armsOut;
    boolean spindelOut;
    boolean wasPressedA;
    boolean wasPressedB;
    
    boolean wasClicked;
    boolean stopped;
    
    public RobotTemplate() {
        stick = new Joystick(1);
        stick2 = new Joystick(2);
        fl = new Talon(1);
        rl = new Talon(2);
        rr = new Talon(3);
        fr = new Talon(4);
        motor5 = new Victor(5);
        motor6 = new Victor(6);
        c = new Compressor(1,1);
        s = new Solenoid(1);
        s2 = new Solenoid(2);
        s3 = new Solenoid(3);
        s4 = new Solenoid(4);
        s5 = new Solenoid(5);
        s6 = new Solenoid(6);
        s7 = new Solenoid(7);
        s8 = new Solenoid(8);
        gyro = new Gyro(1);
        color = new AnalogChannel(2);
        old = 0;
        
        j1b1 = new JoystickButton(stick, 1);
        j1b2 = new JoystickButton(stick, 2);
        j1b3 = new JoystickButton(stick, 3);
        j1b4 = new JoystickButton(stick, 4);
        j1b5 = new JoystickButton(stick, 5);
        j1b6 = new JoystickButton(stick, 6);
        j1b7 = new JoystickButton(stick, 7);
        j1b8 = new JoystickButton(stick, 8);
        j1b9 = new JoystickButton(stick, 8);
        j1b10 = new JoystickButton(stick, 10);
        j2b2 = new JoystickButton(stick2, 2);
        j2b3 = new JoystickButton(stick2,3);
        j2b4 = new JoystickButton(stick2,4);
        j2b5 = new JoystickButton(stick2,5);
        j2b7 = new JoystickButton(stick2,7);
        j2b8 = new JoystickButton(stick2, 8);
        j2b9 = new JoystickButton(stick2, 9);
        j2b10 = new JoystickButton(stick2,10);
        
        multiplyer = 1;
        armsOut = false;
        spindelOut = false;
        wasPressedA = false;
        wasPressedB = false;
        
        wasClicked = true;
        stopped = true;
   }
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous()  {
        c.start();
        gyro.reset();
//        Timer.delay(3.0);
//        setSpindel(true,true,false);
//        setSpindel(false,true,false);
//        System.out.println("OUT!");
//        Timer.delay(2.0);
//        setSpindel(true,true,false);
//        setSpindel(false,true,false);
//        System.out.println("IN!");
//        Timer.delay(0.5);
//        System.out.println("REST!");
        setMotors(0,-0.5,0.05);
        setArms(true);
        Timer.delay(1.3);
        setMotors(0,0,0);
        setShooter(true,false);
        Timer.delay(1);
        while(!stopped && isAutonomous())
            setShooter(false,false);
        setMotors(0,0,0);
        System.out.println("Done!");
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        c.start();
        while(isOperatorControl()) {
            setArms(j1b1.get() || stick2.getTrigger());
            setSpindel(j1b2.get(), j1b5.get(), j1b6.get());
            setMotors(stick.getX(),stick.getY(),stick.getThrottle());
            
            if(j2b9.get()) {
                motor6.set(-1);
            }else if(j2b10.get()){
                motor6.set(0.9);
            }else if(j2b4.get()) {
                motor6.set(-0.2);
            }else if(j2b5.get()){
                motor6.set(0.2);
            }else {
                setShooter(j2b8.get(),j2b7.get());
            }
        }
    }
    
    public void disable() {
        s.set(false);
        s2.set(true);
        s3.set(false);
        s4.set(true);
        s5.set(false);
        s6.set(true);
    }
    
    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test() {
    
    }
    
    public void setMotors() {
            if(j1b7.get()) {
                gyro.reset();
            }
            
            if(gyro.getAngle() >= 360)
                gyro.reset();
            if(gyro.getAngle() <= -360)
                gyro.reset();
            
            double v = Math.sqrt(stick.getX()*stick.getX()+stick.getY()*stick.getY()); 
            double theta = Math.toDegrees(MathUtils.atan2(stick.getY(), stick.getX()));
            
            theta -= gyro.getAngle();
            theta = Math.toRadians(theta);
            
            //double z = 0;
            
//            if(Math.abs(stick.getThrottle())>0.15) {
                double z = stick.getThrottle();
//            }else{
//                z = (old-gyro.getAngle())/1.5;
//            }
            System.out.println(gyro.getAngle());
            setMotors(v*Math.cos(theta), v*Math.sin(theta), z);
            
            old = gyro.getAngle();
    }
    
    public void setMotors(double x, double y, double z) {
        fl.set(-y+x+z);
        rl.set(-y-x+z);
        rr.set(y-x+z);
        fr.set(y+x+z);
    }
    
    public void setSpindel(boolean a, boolean b, boolean c) {
        if(!wasPressedA && a) {
            if(spindelOut)
                spindelOut = false;
            else
                spindelOut = true;
            wasPressedA = true;
        }
        
        if(!a)
            wasPressedA = false;
        
        if(spindelOut) {
            s3.set(true);
            s4.set(false);
            s5.set(true);
            s6.set(false);
        }else{
            s3.set(false);
            s4.set(true);
            s5.set(false);
            s6.set(true);
        }
        
        if(b) {
            if(spindelOut)
                motor5.set(1);
            else
                motor5.set(0.5);
        }else if(c) {
            motor5.set(-1);
        }else{
            motor5.set(0);
        }
    }
    
    public void setArms(boolean a) {
        if(!wasPressedB && a) {
            if(armsOut)
                armsOut = false;
            else
                armsOut = true;
            wasPressedB = true;
        }
        
        if(!a)
            wasPressedB = false;
        
        if(armsOut) {
            s.set(true);
            s2.set(false);
        }else{
            s.set(false);
            s2.set(true);
        }
    }
    
    public void setShooter(boolean a, boolean b) {
        if(a) {
            stopped = false;
            motor6.set(0.8);
            wasClicked = color.getValue() > 90;
        }else{
            motor6.set(0);
            if(b)
                stopped = true;
        }
        if(!stopped) {
            if(color.getValue() > 90) {
                motor6.set(0.9);
            }else if(color.getValue() < 90 && wasClicked) {
                stopped = true;
                motor6.set(0);
            }
            wasClicked = color.getValue() > 90;
        }else{
            motor6.set(0);
        }
    }
}
