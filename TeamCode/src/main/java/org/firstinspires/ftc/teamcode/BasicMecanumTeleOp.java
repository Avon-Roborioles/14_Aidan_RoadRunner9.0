package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.BasicMecanumDrive;
@TeleOp
public class BasicMecanumTeleOp extends LinearOpMode {

    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.BasicMecanumDrive driveMotors = new BasicMecanumDrive();
    public void runOpMode() throws InterruptedException {
        //init
        driveMotors.init_drive_motors(hardwareMap);
        waitForStart();
        while(opModeIsActive()){
            //run
            driveMotors.run_drive_motors(gamepad1, telemetry);
            telemetry.update();
        }

    }
}





