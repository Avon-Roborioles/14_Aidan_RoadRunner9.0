package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ServoTest {


    private boolean testmode = false;
    //lift:  set 0 as intake; set 0.5 as up then get pwm for 135 past up then set that as "mpl"
    //intake: set 0 as toward bot and check that 0.6 is directly away
    private Servo ls;
    private Servo is;
    private double rt;
    private double lt;
    private double lsp = 0;
    private double isp = 0;
    private boolean a;
    private boolean b;
    private boolean x;
    private boolean y;
    private double mdl = 270;
    private double mpl = 0.928;
    private double mdi = 300;
    private double mpi = 1;
    private double ms = 1;
    private double error = 0.15;
    //test vars
    private boolean test1 = false;
    private boolean test2 = false;
    private boolean test3 = false;
    private boolean test4 = false;
    public void init_servo(HardwareMap hardwareMap){
        ls = hardwareMap.get(Servo.class, "ls");
        is = hardwareMap.get(Servo.class, "is");
        ls.setDirection(Servo.Direction.FORWARD);
        ls.setDirection(Servo.Direction.FORWARD);
    }
    public void run_servo(Gamepad gamepad1, Telemetry telemetry){
        TelemetryPacket packet = new TelemetryPacket();
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        //control definition
        rt = gamepad1.right_trigger;
        lt = gamepad1.left_trigger;
        a = gamepad1.a;
        b = gamepad1.b;
        x = gamepad1.x;
        y = gamepad1.y;
        //when isp = 0 intake points toward the bot
        //when lsp = 0 it is all the way forward towards the intake side of bot
        //  y
        //x   b
        //  a
        if (testmode){
            if(a){
                ls.setPosition(0.5);
                lsp = 0.5;
            } else if(b){
                lsp +=0.001;
                ls.setPosition(lsp);
            }
            if(x){
                is.setPosition(0);
            } else if(y){
                is.setPosition(0.6);
            }
            test1 = true;
        } else {
            //running code when not in test mode
            test2 = true;
            if(a){
                lsp += ms;
            }else if(b){
                lsp -= ms;
            }
            if (lsp>135){
                isp = -lsp +375;
                test3 = true;
            }else {
                isp = 180;
                test4 = true;
            }
            // lsp and isp are in degrees
            ls.setPosition((lsp*(1/mdl)) * mpl);
            is.setPosition((isp*(1/mdi)) * mpi);
        }
        getTele(telemetry, dashboard, packet);
        telemetry.update();
    }
    public void getTele(Telemetry telemetry, FtcDashboard dashboard, TelemetryPacket packet){
        telemetry.addData("lift pos", ls.getPosition());
        telemetry.addData("Intake pos", is.getPosition());
        telemetry.addData("lift target", lsp);
        telemetry.addData("Intake target", isp);
        telemetry.addData("Lift target pwm", (lsp*(1/mdl))*mpl);
        telemetry.addData("Intake target pwm", (isp*(1/mdi)) * mpi);
        telemetry.addData("lift and intake target in degrees", String.valueOf(lsp)," Intake pos", (-lsp+375));
        telemetry.addData("y", y);
        telemetry.addData("b", b);
        telemetry.addData("x", x);
        telemetry.addData("a", a);
        telemetry.addLine("Tests:");
        telemetry.addData("test mode function check", test1);
        telemetry.addData("Not in test mode function check", test2);
        telemetry.addData("Lift is past vertical and equation is running", test3);
        telemetry.addData("Lift is on the intake side", test4);
        telemetry.update();
        dashboard.sendTelemetryPacket(packet);
    }
}
