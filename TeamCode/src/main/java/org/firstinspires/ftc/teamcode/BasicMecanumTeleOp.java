package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Call_Upon_Classes.BasicMecanumDrive;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.ServoTest;

@TeleOp
//@Autonomous
public class BasicMecanumTeleOp extends LinearOpMode {

    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.BasicMecanumDrive driveMotors = new BasicMecanumDrive();
    private final  org.firstinspires.ftc.teamcode.Call_Upon_Classes.ServoTest servo = new ServoTest();
    public void runOpMode() throws InterruptedException {
        //init
        //driveMotors.init_drive_motors(hardwareMap);
        servo.init_servo(hardwareMap);
        waitForStart();
        while(opModeIsActive()){
            //run
            //driveMotors.run_drive_motors(gamepad1, telemetry);
            servo.run_servo(gamepad1, telemetry);
            telemetry.update();
        }

    }
}





