package org.firstinspires.ftc.teamcode.Call_Upon_Classes1;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LiftServo {
    //
    private Servo liftServo; //Servo for the lift
    private double liftServoPos = 0;
    private boolean button_a;
    private boolean button_b;

    //Init
    public void init_servo(HardwareMap hardwareMap){
        liftServo = hardwareMap.get(Servo.class,"liftServo");
    }
    public void run_servo(Gamepad gamepad1, Telemetry telemetry){
                //  y
                //x   b
                //  a
        button_a = gamepad1.a;
        button_b = gamepad1.b;

        //
        if(button_a){
            liftServoPos -= 0.001;
        }else if(button_b){
            liftServoPos += 0.001;
        }



        if(liftServoPos < 0){
            liftServoPos = 0;
        }else if (liftServoPos > 1){
            liftServoPos = 1;
        }
        liftServo.setPosition(liftServoPos);
        telemetry.addData("Lift Servo Position", liftServo.getPosition());
        telemetry.update();
    }
}
